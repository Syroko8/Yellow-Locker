package com.example.nicolaspuebla_proyecto_final_android.ui.screens.teamCalendar

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.laboratorio_b.ui.navigation.Destinations
import com.example.nicolaspuebla_proyecto_final_android.R
import com.example.nicolaspuebla_proyecto_final_android.data.model.dataClases.Event
import com.example.nicolaspuebla_proyecto_final_android.ui.components.ErrorDialogNoRetry
import com.example.nicolaspuebla_proyecto_final_android.ui.components.EventSheet
import com.example.nicolaspuebla_proyecto_final_android.utils.SessionManager
import com.example.nicolaspuebla_proyecto_final_android.utils.TeamRoles
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

/**
 * Pantalla del calendario de eventos del equipo.
 *
 * @param onNav Función de navegación.
 * @param viewModel ViewModel que gestiona la lógica del calendario.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TeamCalendarScreen(onNav: (String) -> Unit, viewModel: TeamCalendarScreenViewModel = hiltViewModel()){

    val teamId by SessionManager.actualTeamId.collectAsState()
    val events by viewModel.events.collectAsState()
    val sheetState = rememberModalBottomSheetState()
    val bottomSheetVisibility by viewModel.sheetVisibility.collectAsState()
    val selectedEvents by viewModel.selectedEvents.collectAsState()
    val scope = rememberCoroutineScope()
    val actualTeamRole by SessionManager.actualTeamRole.collectAsState()
    val loading by viewModel.isLoading.collectAsState()
    val err by viewModel.errorMessage.collectAsState()
    val logout by viewModel.logout.collectAsState()

    LaunchedEffect(Unit) {
        teamId?.let { viewModel.getEvents(it) }
    }

    LaunchedEffect(logout) {
        if(logout){
            SessionManager.setLogOut(true)
        }
    }

    Column(
         modifier = Modifier
             .fillMaxSize()
             .background((Color(244, 235, 235))),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Title()
        CalendarWithEvents(
            events,
            viewModel
        )
        if(actualTeamRole == TeamRoles.Captain || actualTeamRole == TeamRoles.Coach){
            ModifyEvents(onNav = {onNav(Destinations.MODIFY_EVENTS)})
        }
        if(bottomSheetVisibility){
            EventSheet(
                dismiss = {
                    scope.launch {
                        sheetState.hide()
                    }.invokeOnCompletion {
                        viewModel.hideSheet()
                    }
                },
                state = sheetState,
                eventList = selectedEvents
            )
        }
        if(err != ""){
            ErrorDialogNoRetry(
                onOk = { viewModel.unsetErr() },
                err = err
            )
        }
        if(loading){
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(top = 40.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                CircularProgressIndicator()
            }
        }
    }
}

/**
 * Función que muestra el título para la pantalla de calendario.
 */
@Composable
fun Title(){
    Row(
        modifier = Modifier
            .padding(top = 20.dp)
            .fillMaxWidth()
            .wrapContentHeight(),
        horizontalArrangement = Arrangement.Center
    ){
        Text(
            text = stringResource(R.string.events),
            fontSize = 35.sp,
            fontFamily = FontFamily(Font(R.font.jura_bold)),
            color = Color.Black,
            softWrap = true,
            lineHeight = 50.sp
        )
    }
}

/**
 * Calendario interactivo con paginación mensual.
 *
 * @param events Lista de eventos a mostrar.
 * @param viewModel ViewModel para manejar interacciones.
 */
@OptIn(ExperimentalPagerApi::class)
@Composable
fun CalendarWithEvents(
    events: List<Event>,
    viewModel: TeamCalendarScreenViewModel
) {
    val pagerState = rememberPagerState(initialPage = Int.MAX_VALUE / 2)
    val initialMonth = remember { Calendar.getInstance() }

    HorizontalPager(
        count = Int.MAX_VALUE,
        state = pagerState,
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(top = 20.dp),
        verticalAlignment = Alignment.Top
    ) { page ->

        val currentMonth = initialMonth.clone() as Calendar
        val pageOffset = page - (Int.MAX_VALUE / 2)
        currentMonth.add(Calendar.MONTH, pageOffset)

        Column(
            Modifier
                .padding(16.dp)
                .wrapContentHeight()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(horizontal = 20.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(
                    text = "${currentMonth.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault())} ${currentMonth.get(Calendar.YEAR)}",
                    color = Color.Black,
                    fontFamily = FontFamily(Font(R.font.jura_regular)),
                    fontSize = 24.sp
                )
                CalendarControls(pagerState)
            }
            HorizontalDivider(
                color = Color.LightGray,
                thickness = 2.dp,
                modifier = Modifier.fillMaxWidth().padding(top = 20.dp, bottom = 10.dp)
            )

            CalendarGrid(
                events,
                currentMonth,
                viewModel
            )
        }
    }
}

/**
 * Controles de navegación entre meses.
 *
 * @param pagerState Estado del paginador para controlar animaciones
 */
@OptIn(ExperimentalPagerApi::class)
@Composable
fun CalendarControls(pagerState: PagerState) {
    val scope = rememberCoroutineScope()

    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.wrapContentSize()
    ) {
        IconButton(onClick = {
            scope.launch {
                pagerState.animateScrollToPage(pagerState.currentPage - 1)
            }
        }) {
            Icon(
                Icons.Default.ArrowBackIosNew,
                "Previous month",
                tint = Color.Gray
            )
        }

        IconButton(onClick = {
            scope.launch {
                pagerState.animateScrollToPage(pagerState.currentPage + 1)
            }
        }) {
            Icon(
                Icons.Default.ArrowForwardIos,
                "Next month",
                tint = Color.Gray
            )
        }
    }
}

/**
 * Cuadrícula de días del mes con eventos destacados.
 *
 * @param events Lista completa de eventos.
 * @param currentMonth Mes actual siendo mostrado.
 * @param viewModel ViewModel para manejar selección de eventos.
 */
@Composable
fun CalendarGrid(
    events: List<Event>,
    currentMonth: Calendar,
    viewModel: TeamCalendarScreenViewModel
) {
    // Cálculo de estructura del calendario.
    val daysInMonth = currentMonth.getActualMaximum(Calendar.DAY_OF_MONTH)
    val firstDayOfMonth = currentMonth.clone() as Calendar
    firstDayOfMonth.set(Calendar.DAY_OF_MONTH, 1)
    val startingDayOfWeek = (firstDayOfMonth.get(Calendar.DAY_OF_WEEK) + 5) % 7
    // Listas para días del mes y relleno.
    val days = (1..daysInMonth).toList()
    val paddingDaysBefore = List(startingDayOfWeek) { -1 }
    val paddingDaysAfter = List((7 - (startingDayOfWeek + daysInMonth) % 7) % 7) { -1 }
    val allDays = paddingDaysBefore + days + paddingDaysAfter
    val weekInitials = listOf(
        stringResource(R.string.monday_initials),
        stringResource(R.string.tuesday_initials),
        stringResource(R.string.wednesday_initials),
        stringResource(R.string.thursday_initials),
        stringResource(R.string.friday_initials),
        stringResource(R.string.saturday_initials),
        stringResource(R.string.sunday_initials)
    )

    // Encabezado con días de la semana
    LazyVerticalGrid(GridCells.Fixed(7)) {
        items(weekInitials.size) { index ->
            val day = weekInitials[index]
            val textColor = Color.Black

            Column(
                modifier = Modifier
                    .padding(2.dp),
                    horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = day,
                    fontFamily = FontFamily(Font(R.font.jura_semi_bold)),
                    color = textColor,
                )
            }
        }
    }

    LazyVerticalGrid(GridCells.Fixed(7)) {
        items(allDays.size) { index ->
            val day = allDays[index]
            var textColor = if (day <= 0) Color.Transparent else Color.Black
            val currentDay = if (day <= 0) "" else day.toString()
            val eventsForDay = events.filter { event ->
                val eventCalendar = Calendar.getInstance()
                eventCalendar.time = stringToDate(event.date)
                eventCalendar.get(Calendar.DAY_OF_MONTH) == day && eventCalendar.get(Calendar.MONTH) == currentMonth.get(
                    Calendar.MONTH
                )
            }
            var backgroundColor = Color.Transparent
            if(eventsForDay.isNotEmpty()) {
                backgroundColor = Color(255, 181, 37)
                textColor = Color.White
            }

            Column(
                modifier = Modifier
                    .padding(8.dp)
                    .clickable {
                        viewModel.setSelectedEvents(eventsForDay)
                        if(eventsForDay.isNotEmpty()){
                            viewModel.showSheet()
                        }
                    }
                    .clip(RoundedCornerShape(12.dp))
                    .background(backgroundColor)
                ,
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,

            ) {
                Text(
                    text = currentDay,
                    color = textColor,
                    modifier = Modifier
                        .padding(5.dp)
                )
            }
        }
    }
}

/**
 * Botón para acceder a la modificación de eventos.
 * Visible solo para capitanes/entrenadores.
 *
 * @param onNav Función de navegación.
 */
@Composable
fun ModifyEvents(onNav: () -> Unit){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(start = 20.dp)
    ) {
        Button(
            modifier = Modifier
                .wrapContentHeight()
                .wrapContentWidth(),
            onClick = { onNav() },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(64,230, 83),
                contentColor = Color.White
            ),
            shape = RoundedCornerShape(10.dp)
        ) {
            Text(
                text = stringResource(R.string.modify_events),
                fontFamily = FontFamily(Font(R.font.jura_bold)),
                fontSize = 14.sp,
                color = Color.White
            )
        }
    }
}

/**
 * Convierte cadena de fecha a objeto Date.
 *
 * @param date Cadena en formato "yyyy-MM-dd".
 * @return Objeto Date correspondiente.
 * @throws Exception Si hay error en el parseo.
 */
fun stringToDate(date: String): Date {
    val formated = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    return try {
        formated.parse(date)!!
    } catch (e: Exception) {
        e.printStackTrace()
        throw Exception(e.message)
    }
}

@Preview
@Composable
fun TeamCalendarScreenPreview(){
    TeamCalendarScreen(onNav = {})
}