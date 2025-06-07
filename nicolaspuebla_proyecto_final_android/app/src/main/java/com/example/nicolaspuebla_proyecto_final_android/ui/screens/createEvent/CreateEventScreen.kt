package com.example.nicolaspuebla_proyecto_final_android.ui.screens.createEvent

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.AlertDialogDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.DisplayMode
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import com.example.nicolaspuebla_proyecto_final_android.ui.components.ChooseOpponentSheet
import com.example.nicolaspuebla_proyecto_final_android.ui.components.ErrorDialog
import com.example.nicolaspuebla_proyecto_final_android.ui.components.MapPreview
import com.example.nicolaspuebla_proyecto_final_android.utils.LocationChoosingInfo
import com.example.nicolaspuebla_proyecto_final_android.utils.MapAction
import com.example.nicolaspuebla_proyecto_final_android.utils.SessionManager
import java.time.Instant
import java.time.OffsetDateTime
import java.time.ZoneOffset
import java.util.Calendar
import java.util.TimeZone

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateEventScreen(onNav: (String) -> Unit, viewModel: CreateEventScreenViewModel = hiltViewModel()){
    val sheetState = rememberModalBottomSheetState()
    val teamId = SessionManager.actualTeamId.collectAsState()
    val locationWasChosen by LocationChoosingInfo.chosen.collectAsState()
    val chosenLocation by LocationChoosingInfo.selectedMapPosition.collectAsState()
    val errMsg by viewModel.errorMessage.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getTeams()
        viewModel.getLocationChoosingCreationInfo()
    }

    LaunchedEffect(viewModel.mapVisibility.value) {
        if(viewModel.mapVisibility.value){
            LocationChoosingInfo.action.value = MapAction.ChooseOpponent
            onNav(Destinations.MAP)
        }
    }

    LaunchedEffect(locationWasChosen) {
        if(locationWasChosen){
            teamId?.let {
                if (chosenLocation != null) viewModel.location.value = chosenLocation
                LocationChoosingInfo.setChosen(false)
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background((Color(244, 235, 235)))
            .padding(top = 20.dp, start = 20.dp, end = 20.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Title()
        Form(viewModel)

        if(viewModel.showTeamChoosingSheet.value){
            ChooseOpponentSheet(
                dismiss = { viewModel.showTeamChoosingSheet.value = false },
                viewModel = viewModel,
                state = sheetState
            )
        }

        if(viewModel.showDatePicker.value){
            CreationDatePicker(viewModel)
        }

        if(viewModel.showTimePicker.value){
            CreationTimePicker(viewModel)
        }

        if(errMsg != ""){
            ErrorDialog(
                err = errMsg,
                onOk = { viewModel.unsetErr() },
                onRetry = {
                    teamId.value?.let {
                        viewModel.saveEvent(it)
                    }
                    viewModel.unsetErr()
                }
            )
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
            text = stringResource(R.string.new_event),
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
fun Form(viewModel: CreateEventScreenViewModel){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(top = 20.dp, start = 20.dp, end = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        EventTypeInput(viewModel)
        DateRow(viewModel)
        TimeRow(viewModel)
        if(viewModel.eventType.value == EventType.Match){
            OpponentRow(viewModel)
        }
        DirectionRow(viewModel)
        if(viewModel.location.value != null){
            DirectionPreview(viewModel)
        }
        SaveButton(viewModel)
    }
}

@Composable
fun EventTypeInput(viewModel: CreateEventScreenViewModel){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(bottom = 40.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        ToggleButton(
            text = stringResource(R.string.match),
            selected = viewModel.eventType.value == EventType.Match,
            onClick = { viewModel.eventType.value = EventType.Match },
            side = true
        )
        ToggleButton(
            text = stringResource(R.string.training),
            selected = viewModel.eventType.value == EventType.Training,
            onClick = { viewModel.eventType.value = EventType.Training },
            side = false
        )
    }
}

@Composable
fun ToggleButton(text: String, selected: Boolean, onClick: () -> Unit, side: Boolean) {
    val selectedColor =  Color(124, 154, 231)
    val leftButtonRounded = RoundedCornerShape(topStart = 8.dp, bottomStart = 8.dp)
    val rightButtonRounded = RoundedCornerShape(topEnd = 8.dp, bottomEnd = 8.dp)

    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = if (selected) selectedColor else Color.White,
            contentColor = if (selected) Color.White else selectedColor
        ),
        shape = if(side) leftButtonRounded else rightButtonRounded,
        border = if (!selected) ButtonDefaults.outlinedButtonBorder else null,
        modifier = Modifier
            .height(40.dp)
            .padding(end = 4.dp)
    ) {
        Text(text = text)
    }
}

@Composable
fun DateRow(viewModel: CreateEventScreenViewModel){

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Text(
                text = "${stringResource(R.string.date)}:",
                fontFamily = FontFamily(Font(R.font.jura_semi_bold)),
                color = Color.Black,
                fontSize = 16.sp
            )
            Button(
                onClick = { viewModel.showDatePicker.value = true },
                colors = ButtonDefaults.buttonColors(
                    contentColor = Color.Black,
                    containerColor = Color(220, 219, 219, 255)
                ),
                modifier = Modifier
                    .padding(start = 20.dp)
                    .wrapContentSize(),
                shape = RoundedCornerShape(10.dp)
            ){
                Row(
                    modifier = Modifier
                        .wrapContentSize(),
                    horizontalArrangement = Arrangement.spacedBy(20.dp)
                ) {
                    Text(
                        text = if(viewModel.date.value != null) {
                            "${viewModel.date.value?.dayOfMonth}-${viewModel.date.value?.monthValue}-${viewModel.date.value?.year}"
                        } else{
                            stringResource(R.string.set_date)
                        },
                        fontFamily = FontFamily(Font(R.font.jura_semi_bold)),
                        color = Color.Black,
                        fontSize = 16.sp
                    )
                    Icon(Icons.Default.CalendarMonth, contentDescription = stringResource(R.string.calendar_icon_description))
                }
            }
        }
}

@Composable
fun TimeRow(viewModel: CreateEventScreenViewModel){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ){
        Text(
            text = "${stringResource(R.string.hour)}:",
            fontFamily = FontFamily(Font(R.font.jura_semi_bold)),
            color = Color.Black,
            fontSize = 16.sp
        )
        Button(
            onClick = { viewModel.showTimePicker.value = true },
            colors = ButtonDefaults.buttonColors(
                contentColor = Color.Black,
                containerColor = Color(220, 219, 219, 255)
            ),
            modifier = Modifier
                .padding(start = 20.dp)
                .wrapContentSize(),
            shape = RoundedCornerShape(10.dp)
        ){
            Text(
                text = if(viewModel.date.value != null) {
                    "${viewModel.date.value?.hour}:${if (viewModel.date.value?.minute == 0) "00" else viewModel.date.value?.minute}"
                } else {
                    stringResource(R.string.set_time)
                },
                fontFamily = FontFamily(Font(R.font.jura_semi_bold)),
                color = Color.Black,
                fontSize = 16.sp
            )
            Spacer(Modifier.width(20.dp))
            Icon(Icons.Default.Schedule, contentDescription = stringResource(R.string.schedule_icon_description))
        }
    }
}

@Composable
fun OpponentRow(viewModel: CreateEventScreenViewModel){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ){
        Text(
            text = "${stringResource(R.string.opponent)}:",
            fontFamily = FontFamily(Font(R.font.jura_semi_bold)),
            color = Color.Black,
            fontSize = 16.sp
        )
        Button(
            onClick = { viewModel.showTeamChoosingSheet.value = true },
            colors = ButtonDefaults.buttonColors(
                contentColor = Color.Black,
                containerColor = Color(220, 219, 219, 255)
            ),
            modifier = Modifier
                .padding(start = 20.dp)
                .wrapContentSize(),
            shape = RoundedCornerShape(10.dp)
        ){
            Text(
                text = viewModel.opponent.value?.name ?: stringResource(R.string.select),
                fontFamily = FontFamily(Font(R.font.jura_semi_bold)),
                color = Color.Black,
                fontSize = 16.sp
            )
        }
    }
}

@Composable
fun DirectionRow(viewModel: CreateEventScreenViewModel){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        Text(
            text = "${stringResource(R.string.location)}:",
            fontFamily = FontFamily(Font(R.font.jura_semi_bold)),
            color = Color.Black,
            fontSize = 16.sp
        )
        Button(
            onClick = {
                viewModel.setCreationInfoLocationChoosing()
                viewModel.mapVisibility.value = true
            },
            colors = ButtonDefaults.buttonColors(
                contentColor = Color.Black,
                containerColor = Color(220, 219, 219, 255)
            ),
            modifier = Modifier
                .padding(start = 20.dp)
                .wrapContentSize(),
            shape = RoundedCornerShape(10.dp)
        ){
            Row(
                modifier = Modifier
                    .wrapContentSize(),
                horizontalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                Text(
                    text = if(viewModel.location.value != null) stringResource(R.string.change_location) else
                        stringResource(R.string.search_location),
                    fontFamily = FontFamily(Font(R.font.jura_semi_bold)),
                    color = Color.Black,
                    fontSize = 16.sp
                )
                Icon(Icons.Default.LocationOn, contentDescription = stringResource(R.string.calendar_icon_description))
            }
        }
    }
}

@Composable
fun DirectionPreview(viewModel: CreateEventScreenViewModel){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(top = 30.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        MapPreview(
            latitude = viewModel.location.value?.latitude!!,
            longitude = viewModel.location.value?.longitude!!,
            context = LocalContext.current
        )
    }
}

@Composable
fun SaveButton(viewModel: CreateEventScreenViewModel){
    val loading by viewModel.isLoading.collectAsState()
    val teamId by SessionManager.actualTeamId.collectAsState()
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(top = 30.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        if(loading){
            CircularProgressIndicator()
        } else {
            Button(
                onClick = {
                    if(
                        viewModel.date.value != null &&
                        viewModel.location.value != null
                    ){
                        if(viewModel.eventType.value == EventType.Training){
                            teamId?.let {
                                viewModel.saveEvent(it)
                            }
                        } else if(viewModel.eventType.value == EventType.Match && viewModel.opponent.value != null){
                            teamId?.let {
                                viewModel.saveEvent(it)
                            }
                        }
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    contentColor = Color.Black,
                    containerColor = Color(124, 154, 231)
                ),
                modifier = Modifier
                    .padding(start = 20.dp)
                    .wrapContentSize(),
                shape = RoundedCornerShape(10.dp)
            ){
                Row(
                    modifier = Modifier
                        .wrapContentSize(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = stringResource(R.string.create),
                        fontFamily = FontFamily(Font(R.font.jura_semi_bold)),
                        color = Color.Black,
                        fontSize = 16.sp
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreationDatePicker(viewModel: CreateEventScreenViewModel) {
    val calendar = Calendar.getInstance()
    val datePickerState = rememberDatePickerState(
        initialDisplayMode = DisplayMode.Picker,
        initialSelectedDateMillis = calendar.toInstant().toEpochMilli()
    )
    DatePickerDialog(
        onDismissRequest = { viewModel.showDatePicker.value = false } ,
        confirmButton = {
            TextButton(onClick = {
                viewModel.date.value = getOffsetDate(datePickerState)
                viewModel.showDatePicker.value = false
            }) {
                Text(stringResource(R.string.confirm))
            }
        },
        dismissButton = {
            TextButton(onClick = { viewModel.showDatePicker.value = false }) {
                Text(stringResource(R.string.cancel))
            }
        }
    ) {
        DatePicker(state = datePickerState)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreationTimePicker(viewModel: CreateEventScreenViewModel) {
    val calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"))

    val timePickerState = rememberTimePickerState(
        initialHour = calendar.get(Calendar.HOUR_OF_DAY),
        initialMinute = calendar.get(Calendar.MINUTE),
        is24Hour = true,
    )
    AlertDialog(
        onDismissRequest = { viewModel.showTimePicker.value = false },
        confirmButton = {
            TextButton(onClick = {
                viewModel.date.value = getDateWithNewTime(timePickerState, viewModel)
                viewModel.showTimePicker.value = false
            }) {
                Text(stringResource(R.string.confirm))
            }
        },
        dismissButton = {
            TextButton(onClick = {
                viewModel.showTimePicker.value = false
            }) {
                Text(stringResource(R.string.cancel))
            }
        },
        text = {
            TimePicker(state = timePickerState)
        },
        shape = MaterialTheme.shapes.extraLarge,
        containerColor = MaterialTheme.colorScheme.surface,
        tonalElevation = AlertDialogDefaults.TonalElevation
    )
}

@OptIn(ExperimentalMaterial3Api::class)
fun getOffsetDate(date: DatePickerState): OffsetDateTime{
    val millis = date.selectedDateMillis!!

    return OffsetDateTime.ofInstant(Instant.ofEpochMilli(millis), ZoneOffset.UTC)
}

@OptIn(ExperimentalMaterial3Api::class)
fun getDateWithNewTime(timePickerState: TimePickerState, viewModel: CreateEventScreenViewModel): OffsetDateTime{
    val calendar = Calendar.getInstance()

    if(viewModel.date.value != null){
        return viewModel.date.value
            ?.withHour(timePickerState.hour)
            ?.withMinute(timePickerState.minute)
            ?.withSecond(0)!!
    } else {
        return OffsetDateTime.
        of(
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH),
            timePickerState.hour,
            timePickerState.minute,
            0, 0, ZoneOffset.UTC)
    }
}

