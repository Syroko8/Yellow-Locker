package com.example.nicolaspuebla_proyecto_final_android.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
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
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EventSheet(
    dismiss: () -> Unit,
    state: SheetState,
    eventList: List<Event>
){
    ModalBottomSheet(
        onDismissRequest = { dismiss() },
        sheetState = state,
        containerColor = Color(255, 181, 37),
        dragHandle = {  BottomSheetDefaults.DragHandle(color = Color(31,28,28)) }
    ){
        val date = parseEventDate(eventList[0])

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(255, 181, 37))
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "${date.dayOfMonth} ${stringResource(R.string.of)} ${date.month}",
                    color = Color.White,
                    fontFamily = FontFamily(Font(R.font.jura_semi_bold)),
                    fontSize = 28.sp
                )
            }

           EventList(eventList, date)
        }
    }
}

@Composable
fun EventList(eventList: List<Event>, date: OffsetDateTime){
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 30.dp, start = 20.dp, end = 20.dp, bottom = 20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if(eventList.isNotEmpty()){
            items(items = eventList.sortedWith(
                compareByDescending<Event> {it is Match}
                    .thenBy { parseEventDate(it) }
                )
            ){
                if(it is Match){
                    Match(it, date)
                } else if(it is Training){
                    Training(it, date)
                }
            }
        }
    }
}

@Composable
fun Match(match: Match, date: OffsetDateTime){

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
    ) {
        Text(
            text = stringResource(R.string.match),
            color = Color.White,
            fontFamily = FontFamily(Font(R.font.jura_semi_bold)),
            fontSize = 24.sp,
            modifier = Modifier.padding(start = 25.dp)
        )
        Spacer(Modifier.height(10.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .background(Color.Transparent)
                .border(color = Color.White, width = 3.dp, shape = RoundedCornerShape(10.dp))
                .padding(16.dp)
        ) {
            Text(
                text = "${stringResource(R.string.opponent)}: ${match.opponent.name}",
                color = Color.White,
                fontFamily = FontFamily(Font(R.font.jura_semi_bold)),
                fontSize = 18.sp
            )
            Text(
                text = "${stringResource(R.string.hour)}: " +
                        "${date.hour} : " +
                        if(date.minute == 0) "00" else "0",
                color = Color.White,
                fontFamily = FontFamily(Font(R.font.jura_semi_bold)),
                fontSize = 18.sp
            )
            Text(
                text = "${stringResource(R.string.status)}: ${getMatchStatus(event = match)}",
                color = Color.White,
                fontFamily = FontFamily(Font(R.font.jura_semi_bold)),
                fontSize = 18.sp
            )
            if(getMatchStatus(match) == stringResource(R.string.finished)){
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(start = 20.dp)
                ) {
                    Text(
                        text = "${match.own_goals} - ${match.opponent_goals}",
                        color = Color.White,
                        fontFamily = FontFamily(Font(R.font.jura_semi_bold)),
                        fontSize = 14.sp
                    )
                }
            }
            Text(
                text = "${stringResource(R.string.location)}:",
                color = Color.White,
                fontFamily = FontFamily(Font(R.font.jura_semi_bold)),
                fontSize = 18.sp
            )
            Spacer(Modifier.height(10.dp))
            Text(
                text = stringResource(R.string.see_map),
                color = Color.White,
                fontFamily = FontFamily(Font(R.font.jura_semi_bold)),
                fontSize = 14.sp
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                MapPreview(
                    match.latitude,
                    match.longitude,
                    context = LocalContext.current
                )
            }
        }
    }
}

@Composable
fun Training(training: Training, date: OffsetDateTime){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
    ) {
        Text(
            text = stringResource(R.string.training),
            color = Color.White,
            fontFamily = FontFamily(Font(R.font.jura_semi_bold)),
            fontSize = 24.sp,
            modifier = Modifier.padding(start = 25.dp)
        )
        Spacer(Modifier.height(10.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .background(Color.Transparent)
                .border(color = Color.White, width = 3.dp, shape = RoundedCornerShape(10.dp))
                .padding(16.dp)
        ) {
            Text(
                text = "${stringResource(R.string.hour)}: " +
                        "${date.hour} : " +
                        if(date.minute == 0) "00" else "0",
                color = Color.White,
                fontFamily = FontFamily(Font(R.font.jura_semi_bold)),
                fontSize = 18.sp
            )
            Text(
                text = "${stringResource(R.string.location)}:",
                color = Color.White,
                fontFamily = FontFamily(Font(R.font.jura_semi_bold)),
                fontSize = 18.sp
            )
            Spacer(Modifier.height(10.dp))
            Text(
                text = stringResource(R.string.see_map),
                color = Color.White,
                fontFamily = FontFamily(Font(R.font.jura_semi_bold)),
                fontSize = 14.sp
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                MapPreview(
                    training.latitude,
                    training.longitude,
                    context = LocalContext.current
                )
            }
        }
    }
}

fun parseEventDate(event: Event): OffsetDateTime{
    val formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME
    val dateTime = OffsetDateTime.parse(event.date, formatter)
    return dateTime.withOffsetSameInstant(ZoneOffset.UTC)
}

@Composable
fun getMatchStatus(event: Event): String {
    val formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME
    val eventDateTime = OffsetDateTime.parse(event.date, formatter).withOffsetSameInstant(ZoneOffset.UTC)
    val actualDateTime = OffsetDateTime.now()

    if(actualDateTime > eventDateTime){
        return stringResource(R.string.expectant)
    } else {
        return stringResource(R.string.finished)
    }
}