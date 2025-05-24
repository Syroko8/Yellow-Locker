package com.example.nicolaspuebla_proyecto_final_android.ui.screens.modifyEvents

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.DisplayMode
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TimePicker
import androidx.compose.material3.TimePickerState
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.laboratorio_b.ui.navigation.Destinations
import com.example.nicolaspuebla_proyecto_final_android.R
import com.example.nicolaspuebla_proyecto_final_android.data.model.dataClases.Event
import com.example.nicolaspuebla_proyecto_final_android.ui.components.ChangeOpponentSheet
import com.example.nicolaspuebla_proyecto_final_android.ui.components.ErrorDialog
import com.example.nicolaspuebla_proyecto_final_android.ui.components.ModifyEventCard
import com.example.nicolaspuebla_proyecto_final_android.utils.LocationChoosingInfo
import com.example.nicolaspuebla_proyecto_final_android.utils.MapAction
import com.example.nicolaspuebla_proyecto_final_android.utils.SessionManager
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.selects.whileSelect
import java.time.Instant
import java.time.OffsetDateTime
import java.time.ZoneId
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ModifyEventsScreen(onNav: (String) -> Unit, viewModel: ModifyEventsScreenViewModel = hiltViewModel()){

    val teamId by SessionManager.actualTeamId.collectAsState()
    val err by viewModel.errorMessage.collectAsState()
    val loading by viewModel.isLoading.collectAsState()
    val scope = rememberCoroutineScope()
    val changeOpponentSheetState = rememberModalBottomSheetState()
    val changeOpponentSheetVisibility by viewModel.changeOpponentVisibility.collectAsState()
    val datePickerVisibility by viewModel.datePickerVisibility.collectAsState()
    val timePickerVisibility by viewModel.timePickerVisibility.collectAsState()
    val chosenEvent by viewModel.chosenEventToEdit.collectAsState()
    val mapVisibility by viewModel.mapVisibility.collectAsState()
    val locationWasChosen by LocationChoosingInfo.chosen.collectAsState()
    val chosenLocation by LocationChoosingInfo.selectedMapPosition.collectAsState()
    val chosenEventForLocationChange by LocationChoosingInfo.event.collectAsState()

    LaunchedEffect(Unit) {
        teamId?.let { viewModel.getEvents(it) }
        viewModel.getTeams()
    }

    LaunchedEffect(mapVisibility) {
        if(mapVisibility){
            LocationChoosingInfo.action.value = MapAction.ChangeOpponent
            onNav(Destinations.MAP)
        }
    }

    LaunchedEffect(locationWasChosen) {
        if (locationWasChosen) {
            teamId?.let {
                val job = viewModel.getEvents(it)
                job.join()

                if (chosenLocation != null && chosenEventForLocationChange != null) {
                    viewModel.setEventLocation(chosenEventForLocationChange!!, chosenLocation!!)
                }
                LocationChoosingInfo.setChosen(false)
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background((Color(244, 235, 235)))
            .padding(top = 20.dp)
    ) {
        Title()
        if(loading){
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CircularProgressIndicator()
            }
        } else {
            List(viewModel)
        }
        if(err != ""){
            ErrorDialog(
                err =  err,
                onRetry = {  teamId?.let { viewModel.getEvents(it) } },
                onOk = { viewModel.unsetErr() }
            )
        }
        if(changeOpponentSheetVisibility){
            ChangeOpponentSheet(
                dismiss = {
                    scope.launch {
                        changeOpponentSheetState.hide()
                    }.invokeOnCompletion {
                        viewModel.hideChangeOpponentSheet()
                    }
                },
                state = changeOpponentSheetState,
                viewModel
            )
        }
        if(datePickerVisibility){
            chosenEvent?.let { EventDatePicker(it, viewModel) }
        }
        if(timePickerVisibility){
            chosenEvent?.let { EventTimePicker(it, viewModel) }
        }
    }
}

@Composable
fun Title(){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(horizontal = 17.dp),
        horizontalArrangement = Arrangement.Start
    ) {
        Text(
            text = stringResource(R.string.events),
            fontFamily = FontFamily(Font(R.font.jura_bold)),
            fontSize = 28.sp,
            color = Color.Black
        )
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp)
    ) {
        HorizontalDivider(color = Color.Black, thickness = 2.dp, modifier = Modifier.padding(start = 15.dp, end = 15.dp))
    }
}

@Composable
fun List(viewModel: ModifyEventsScreenViewModel){

    val events by viewModel.events.collectAsState()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 20.dp, start = 40.dp, end = 40.dp, bottom = 20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if(events.isNotEmpty()){
            var lastEventDate: OffsetDateTime? = null

            items(items = events.sortedBy { it.date }){

                val eventDate = parseEventDate(it.date)
                if((lastEventDate == null) || (eventDate.dayOfYear != lastEventDate?.dayOfYear)){
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                            .padding(start = 10.dp, bottom = 15.dp)
                    ) {
                        Text(
                            text = getFormatedDate(it.date),
                            fontFamily = FontFamily(Font(R.font.jura_bold)),
                            fontSize = 18.sp,
                            color = Color(61, 147, 247)
                        )
                    }
                }

                ModifyEventCard(
                    event = it,
                    viewModel,
                    onShowDatePicker = { event ->
                        viewModel.setChosenEventToEdit(event)
                        viewModel.setDatePickerVisibility(true)
                    },
                    onShowTimePicker = { event ->
                        viewModel.setChosenEventToEdit(event)
                        viewModel.setTimePickerVisibility(true)
                    },
                    onChangeOpponent = { event ->
                        viewModel.showChangeOpponentSheet()
                        viewModel.setChosenEventToEdit(event)
                    },
                    onDelete = { event -> viewModel.deleteEvent(event.id) },
                    onLocationChange = { event ->
                        LocationChoosingInfo.setEvent(event)
                        viewModel.setMapVisibility(true)
                    }
                )

                lastEventDate = parseEventDate(it.date)
            }
        } else {
            item {
                NoEvents()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EventDatePicker(chosenEvent: Event, viewModel: ModifyEventsScreenViewModel){
    val datePickerState = rememberDatePickerState(
        initialDisplayMode = DisplayMode.Picker,
        initialSelectedDateMillis = dateToMillis(chosenEvent)
    )
    DatePickerDialog(
        onDismissRequest = { viewModel.setDatePickerVisibility(false) } ,
        confirmButton = {
            TextButton(onClick = {
                viewModel.setDatePickerVisibility(false)
                viewModel.setSelectedDate(getChosenDate(chosenEvent, datePickerState))
            }) {
                Text(stringResource(R.string.confirm))
            }
        },
        dismissButton = {
            TextButton(onClick = {viewModel.setDatePickerVisibility(false)}) {
                Text(stringResource(R.string.cancel))
            }
        }
    ) {
        DatePicker(state = datePickerState)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EventTimePicker(event: Event, viewModel: ModifyEventsScreenViewModel){
    val eventDate = parseEventDate(event.date)

    val timePickerState = rememberTimePickerState(
        initialHour = eventDate.hour,
        initialMinute = eventDate.minute,
        is24Hour = true,
    )

    BasicAlertDialog(
        onDismissRequest = { viewModel.setTimePickerVisibility(false) }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(30.dp)
                .clip(RoundedCornerShape(20.dp))
        ) {
            TimePicker(
                state = timePickerState,
            )
            Button(onClick = {
                viewModel.setSelectedDate(getNewDate(event, timePickerState))
                viewModel.setTimePickerVisibility(false)
            }) {
                Text(stringResource(R.string.confirm))
            }
            Button(onClick = {
                viewModel.setTimePickerVisibility(false)
            }) {
                Text(stringResource(R.string.cancel))
            }
        }
    }
}

@Composable
fun NoEvents(){
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(R.string.no_events),
            color = Color.Black,
            fontSize = 20.sp,
            fontFamily = FontFamily(Font(R.font.jura_bold))
        )
    }
}


fun parseEventDate(date: String): OffsetDateTime{
    val formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME
    val eventDate = OffsetDateTime.parse(date, formatter)
    return  eventDate.withOffsetSameInstant(ZoneOffset.UTC)
}

@Composable
fun getFormatedDate(dateString: String): String {
    return try {
        val formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME
        val dateTime = OffsetDateTime.parse(dateString, formatter)

        "${dateTime.dayOfMonth} ${dateTime.month} - ${dateTime.dayOfWeek}"
    } catch (e: Exception) {
        stringResource(R.string.invalid_date)
    }
}

fun dateToMillis(event: Event): Long {
    val formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME
    val dateTime = OffsetDateTime.parse(event.date, formatter)

    return dateTime.toInstant().toEpochMilli()
}

@OptIn(ExperimentalMaterial3Api::class)
fun getChosenDate(event: Event, state: DatePickerState): OffsetDateTime{
    val newDate = OffsetDateTime.ofInstant(
        state.selectedDateMillis?.let { Instant.ofEpochMilli(it) },
        ZoneId.of("UTC")
    )
    val olfDate = parseEventDate(event.date)
    return newDate
        .withHour(olfDate.hour)
        .withMinute(olfDate.minute)
        .withSecond(0)
}

@OptIn(ExperimentalMaterial3Api::class)
fun getNewDate(chosenEvent: Event, timePickerState: TimePickerState): OffsetDateTime {
    val oldDateStr = chosenEvent.date
    val formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME
    val oldDate = OffsetDateTime.parse(oldDateStr, formatter).withOffsetSameInstant(ZoneOffset.UTC)
    return oldDate
        .withHour(timePickerState.hour)
        .withMinute(timePickerState.minute)
        .withSecond(0)
}
