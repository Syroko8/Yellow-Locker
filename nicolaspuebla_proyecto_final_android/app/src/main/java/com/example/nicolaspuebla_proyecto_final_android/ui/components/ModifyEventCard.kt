package com.example.nicolaspuebla_proyecto_final_android.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nicolaspuebla_proyecto_final_android.R
import com.example.nicolaspuebla_proyecto_final_android.data.model.dataClases.Event
import com.example.nicolaspuebla_proyecto_final_android.data.model.dataClases.Match
import com.example.nicolaspuebla_proyecto_final_android.data.model.dataClases.Training
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter

@Composable
fun ModifyEventCard(
    event: Event,
    onShowDatePicker: (Event) -> Unit,
    onShowTimePicker: (Event) -> Unit,
    onChangeOpponent: (Event) -> Unit,
    onDelete: (Event) -> Unit,
    onLocationChange: (Event) -> Unit
){
    Card(
        modifier = Modifier
            .wrapContentSize(),
        elevation = CardDefaults.cardElevation(5.dp),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
    ) {
        if (event is Match) {
            ModifyMatchCard(
                event,
                showDatePicker = { onShowDatePicker(event) },
                showTimePicker = { onShowTimePicker(event) },
                changeOpponent = { onChangeOpponent(event) },
                onDelete = { onDelete(event) },
                onLocationChange = { onLocationChange(event) }
            )
        } else if (event is Training) {
            ModifyTrainingCard(
                event,
                showDatePicker = { onShowDatePicker(event) },
                showTimePicker = { onShowTimePicker(event) },
                onDelete = { onDelete(event) },
                onLocationChange = { onLocationChange(event) }
            )
        }
    }
}

@Composable
fun ModifyMatchCard(
    event: Match,
    showDatePicker: () -> Unit,
    showTimePicker: () -> Unit,
    changeOpponent: () -> Unit,
    onDelete: () -> Unit,
    onLocationChange: () -> Unit
){
    Column(
        Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(vertical = 10.dp, horizontal = 20.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        DeleteButton(event, onDelete = { onDelete() })
        DateBlock(
            event,
            showDatePicker = { showDatePicker() },
            showTimePicker = { showTimePicker() }
        )
        OpponentBlock(
            event,
            changeOpponent = { changeOpponent() }
        )
        LocationBlock(
            event,
            onLocationChange = { onLocationChange() }
        )
    }
}

@Composable
fun ModifyTrainingCard(
    event: Training,
    showDatePicker: () -> Unit,
    showTimePicker: () -> Unit,
    onDelete: () -> Unit,
    onLocationChange: () -> Unit
){
    Column(
        Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(vertical = 10.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        DeleteButton(event, onDelete = { onDelete() })
        DateBlock(
            event,
            showDatePicker = { showDatePicker() },
            showTimePicker = { showTimePicker() }
        )
        LocationBlock(
            event,
            onLocationChange = { onLocationChange() }
        )
    }
}

@Composable
fun DeleteButton(event: Event, onDelete: () -> Unit){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(vertical = 10.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(if(event is Match) R.string.match else R.string.training ),
            fontFamily = FontFamily(Font(R.font.jura_semi_bold)),
            color = Color.Black,
            fontSize = 16.sp
        )
        Button(
            onClick = { onDelete() }
        ) {
            Text(
                text = stringResource(R.string.delete_event),
                fontFamily = FontFamily(Font(R.font.jura_semi_bold)),
                color = Color.Black,
                fontSize = 16.sp
            )
        }
    }
}

@Composable
fun OpponentBlock(event: Match, changeOpponent: () -> Unit){
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
            onClick = { changeOpponent() },
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
                text = event.opponent.name,
                fontFamily = FontFamily(Font(R.font.jura_semi_bold)),
                color = Color.Black,
                fontSize = 16.sp
            )
        }
    }
}

@Composable
fun DateBlock(
    event: Event,
    showDatePicker: () -> Unit,
    showTimePicker: () -> Unit
){
    val eventDate = getEventDate(event)

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        horizontalAlignment = Alignment.Start
    ){
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
                onClick = { showDatePicker() },
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
                        text = "${eventDate.dayOfMonth}-${eventDate.monthValue}-${eventDate.year}",
                        fontFamily = FontFamily(Font(R.font.jura_semi_bold)),
                        color = Color.Black,
                        fontSize = 16.sp
                    )
                    Icon(Icons.Default.CalendarMonth, contentDescription = stringResource(R.string.calendar_icon_description))
                }
            }
        }
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
                onClick = { showTimePicker() },
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
                    text = "${eventDate.hour}:${if (eventDate.minute == 0) "00" else eventDate.minute}",
                    fontFamily = FontFamily(Font(R.font.jura_semi_bold)),
                    color = Color.Black,
                    fontSize = 16.sp
                )
                Spacer(Modifier.width(20.dp))
                Icon(Icons.Default.Schedule, contentDescription = stringResource(R.string.schedule_icon_description))
            }
        }
    }
}

@Composable
fun LocationBlock(event: Event, onLocationChange: () -> Unit){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
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
                onClick = { onLocationChange() },
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
                        text = stringResource(R.string.change_location),
                        fontFamily = FontFamily(Font(R.font.jura_semi_bold)),
                        color = Color.Black,
                        fontSize = 16.sp
                    )
                    Icon(Icons.Default.LocationOn, contentDescription = stringResource(R.string.calendar_icon_description))
                }
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(top = 10.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            MapPreview(
                latitude = event.latitude,
                longitude = event.longitude,
                context = LocalContext.current
            )
        }
    }
}

fun getEventDate(event: Event): OffsetDateTime{
    val formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME
    val dateTime = OffsetDateTime.parse(event.date, formatter)

    return dateTime
}