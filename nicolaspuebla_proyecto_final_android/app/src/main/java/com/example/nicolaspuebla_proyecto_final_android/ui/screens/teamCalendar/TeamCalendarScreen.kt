package com.example.nicolaspuebla_proyecto_final_android.ui.screens.teamCalendar

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TeamCalendarScreen(onNav: (String) -> Unit, viewModel: TeamCalendarScreenViewModel = hiltViewModel()){

    val events by viewModel.events.collectAsState()
    val sheetState = rememberModalBottomSheetState()
    val bottomSheetVisibility by viewModel.sheetVisibility.collectAsState()
    val selectedEvents by viewModel.selectedEvents.collectAsState()
    val scope = rememberCoroutineScope()
    val actualTeamRole by SessionManager.actualTeamRole.collectAsState()

    Column(
         modifier = Modifier
             .fillMaxSize()
             .background(Color.White),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Title()
        CalendarWithEvents(
            events,
            viewModel
        )
        if(true){
            println(">>>>>>>>>>${actualTeamRole}")
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
    }
}

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

@OptIn(ExperimentalPagerApi::class)
@Composable
fun CalendarWithEvents(
    events: List<Event>,
    viewModel: TeamCalendarScreenViewModel
) {
    val pagerState = rememberPagerState(initialPage = Int.MAX_VALUE / 2)
    val initialMonth = remember { Calendar.getInstance() } // Mes base de referencia

    HorizontalPager(
        count = Int.MAX_VALUE,
        state = pagerState,
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(top = 20.dp),
        verticalAlignment = Alignment.Top
    ) { page ->
        // Calcula el mes actual EN TIEMPO REAL basado en la página
        val currentMonth = initialMonth.clone() as Calendar
        val pageOffset = page - (Int.MAX_VALUE / 2) // Diferencia respecto a la página inicial
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
                // Controles del calendario
                CalendarControls(pagerState)
            }
            HorizontalDivider(
                color = Color.LightGray,
                thickness = 2.dp,
                modifier = Modifier.fillMaxWidth().padding(top = 20.dp, bottom = 10.dp)
            )

            // Grid del calendario (pasa currentMonth calculado)
            CalendarGrid(
                events,
                currentMonth,
                viewModel
            )
        }
    }
}

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
                // Retrocede 1 mes
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
                // Avanza 1 mes
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

@Composable
fun CalendarGrid(
    events: List<Event>,
    currentMonth: Calendar,
    viewModel: TeamCalendarScreenViewModel
) {
    val daysInMonth = currentMonth.getActualMaximum(Calendar.DAY_OF_MONTH)
    val firstDayOfMonth = currentMonth.clone() as Calendar
    firstDayOfMonth.set(Calendar.DAY_OF_MONTH, 1)
    val startingDayOfWeek = firstDayOfMonth.get(Calendar.DAY_OF_WEEK) - 1 // Adjusting for zero-based indexing
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

    LazyVerticalGrid(GridCells.Fixed(7)) {
        items(weekInitials.size) { index ->
            val day = weekInitials[index]
            val textColor = Color.Black

            // Display the day initials
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
            // Determine the events for this day
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
            // Display the day and events
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

fun stringToDate(date: String): Date {
    val formated = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    return try {
        formated.parse(date)!!
    } catch (e: Exception) {
        e.printStackTrace()
        throw Error(e.message)
    }
}

@Preview
@Composable
fun TeamCalendarScreenPreview(){
    TeamCalendarScreen()
}

