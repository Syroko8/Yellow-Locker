package com.example.nicolaspuebla_proyecto_final_android.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nicolaspuebla_proyecto_final_android.R
import com.example.nicolaspuebla_proyecto_final_android.data.model.dataClases.Event
import com.example.nicolaspuebla_proyecto_final_android.data.model.dataClases.Match
import com.example.nicolaspuebla_proyecto_final_android.data.model.dataClases.Training
import com.example.nicolaspuebla_proyecto_final_android.ui.screens.modifyEvents.ModifyEventsScreenViewModel
import java.time.OffsetDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

/**
 * Función que muestra la información de un evento permitiendo a un usuario modificarla.
 *
 * @param event El evento a modificar.
 * @param viewModel ViewModel que gestiona la lógica de eventos.
 * @param onShowDatePicker Función para mostrar el selector de fecha.
 * @param onShowTimePicker Función para mostrar el selector de hora.
 * @param onChangeOpponent Función que se ejecuta al cambiar el oponente (solo en Match).
 * @param onDelete Función que se ejecuta al eliminar el evento.
 * @param onLocationChange Función que se ejecuta al cambiar la ubicación del evento.
 */
@Composable
fun ModifyEventCard(
    event: Event,
    viewModel: ModifyEventsScreenViewModel,
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
                viewModel,
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

/**
 * Muestra el formulario para modificar un evento de tipo Match.
 *
 * @param event El partido a modificar.
 * @param viewModel ViewModel con la lógica para guardar cambios.
 * @param showDatePicker Callback para abrir el selector de fecha.
 * @param showTimePicker Callback para abrir el selector de hora.
 * @param changeOpponent Callback para cambiar el oponente.
 * @param onDelete Callback para eliminar el partido.
 * @param onLocationChange Callback para cambiar la ubicación.
 */
@Composable
fun ModifyMatchCard(
    event: Match,
    viewModel: ModifyEventsScreenViewModel,
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
        ScoreBlock(event, viewModel)
    }
}

/**
 * Muestra el formulario para modificar un evento de tipo Training.
 *
 * @param event El entrenamiento a modificar.
 * @param showDatePicker Callback para abrir el selector de fecha.
 * @param showTimePicker Callback para abrir el selector de hora.
 * @param onDelete Callback para eliminar el entrenamiento.
 * @param onLocationChange Callback para cambiar la ubicación.
 */
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
        LocationBlock(
            event,
            onLocationChange = { onLocationChange() }
        )
    }
}

/**
 * Muestra un botón para eliminar un evento, junto con su tipo (Match o Training).
 *
 * @param event El evento a eliminar.
 * @param onDelete Callback a ejecutar al presionar el botón.
 */
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

/**
 * Muestra la información del oponente para un partido, con opción de cambiarlo.
 *
 * @param event Partido del cual se muestra el oponente.
 * @param changeOpponent Callback para modificar el oponente.
 */
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

/**
 * Muestra los campos para modificar la fecha y hora del evento.
 *
 * @param event Evento cuya fecha y hora se muestran.
 * @param showDatePicker Callback para abrir el selector de fecha.
 * @param showTimePicker Callback para abrir el selector de hora.
 */
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

/**
 * Muestra y permite modificar la ubicación del evento, incluyendo un botón y mapa.
 *
 * @param event Evento del cual se desea modificar la ubicación.
 * @param onLocationChange Callback para seleccionar nueva ubicación.
 */
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

/**
 * Muestra el bloque de puntaje del partido si este ya ha finalizado, permitiendo modificarlo.
 *
 * @param event Partido del cual se muestra y edita el puntaje.
 * @param viewModel ViewModel donde se actualiza el puntaje.
 */
@Composable
fun ScoreBlock(event: Match, viewModel: ModifyEventsScreenViewModel){

    var tempOwnGoals by remember { mutableIntStateOf(event.ownGoals)}
    var tempOpponentGoals by remember { mutableIntStateOf(event.opponentGoals)}
    var showSaveButton by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(top = 20.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = "${stringResource(R.string.status)}: ${getMatchStatus(event)}",
            fontFamily = FontFamily(Font(R.font.jura_semi_bold)),
            color = Color.Black,
            fontSize = 16.sp
        )

        if(getMatchStatus(event) == stringResource(R.string.finished)){
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(top = 20.dp, start = 20.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = "${stringResource(R.string.score)}:",
                    fontFamily = FontFamily(Font(R.font.jura_semi_bold)),
                    color = Color.Black,
                    fontSize = 16.sp
                )
                Spacer(Modifier.height(20.dp))
                ScoreTextField(
                    stringResource(R.string.your_team),
                    tempOwnGoals,
                    onValueChange = {
                        tempOwnGoals = it
                        showSaveButton = setButtonState(event, tempOwnGoals, tempOpponentGoals)
                    }
                )
                ScoreTextField(
                    stringResource(R.string.opponent),
                    tempOpponentGoals,
                    onValueChange = {
                        tempOpponentGoals = it
                        showSaveButton = setButtonState(event, tempOwnGoals, tempOpponentGoals)
                    }
                )
                if(showSaveButton) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                            .padding(top = 20.dp),
                        horizontalArrangement = Arrangement.Center
                    ){
                        Button(
                            onClick = {
                                viewModel.setNewScore(event, tempOwnGoals, tempOpponentGoals)
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
                            Text(
                                text = stringResource(R.string.save_scores),
                                fontFamily = FontFamily(Font(R.font.jura_semi_bold)),
                                color = Color.Black,
                                fontSize = 16.sp
                            )
                        }
                    }
                }
            }
        }
    }
}

/**
 * Campo de texto personalizado para ingresar el puntaje de un equipo.
 *
 * @param text Etiqueta del campo.
 * @param value Valor actual del puntaje.
 * @param onValueChange Callback a ejecutar cuando el valor cambia.
 */
@Composable
fun ScoreTextField(text: String, value: Int, onValueChange: (Int) -> Unit){
    var color by remember {
        mutableStateOf(false)
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "${text}:",
            fontFamily = FontFamily(Font(R.font.jura_semi_bold)),
            color = Color.Black,
            fontSize = 16.sp
        )
        TextField(
            value = value.toString(),
            onValueChange = {
                try{
                    val newScore = it.toInt()
                    onValueChange(newScore)
                    color = false
                } catch (e: Exception){
                    color = true
                }
            },
            colors = TextFieldDefaults.colors(
                unfocusedTextColor = Color.Black,
                focusedTextColor = Color.Black,
                focusedContainerColor = if(!color) Color(220, 219, 219, 255)
                    else Color(238, 124, 124, 255),
                unfocusedContainerColor = if(!color) Color(220, 219, 219, 255)
                else Color(238, 124, 124, 255)
            ),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            ),
            singleLine = true,
            modifier = Modifier.width(100.dp)
        )
    }
}

/**
 * Parsea la fecha del evento desde su formato en string a un objeto OffsetDateTime.
 *
 * @param event Evento cuya fecha será parseada.
 * @return Fecha del evento en formato OffsetDateTime.
 */
fun getEventDate(event: Event): OffsetDateTime{
    val formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME
    val dateTime = OffsetDateTime.parse(event.date, formatter).withOffsetSameInstant(ZoneOffset.UTC)

    return dateTime
}

/**
 * Determina si debe habilitarse el botón de guardar, comparando puntajes actuales y temporales.
 *
 * @param event Partido a evaluar.
 * @param tempOwnGoals Goles temporales del equipo propio.
 * @param teamOpponentGoals Goles temporales del oponente.
 * @return true si los puntajes han cambiado, false en caso contrario.
 */
fun setButtonState(event: Match, tempOwnGoals: Int, teamOpponentGoals: Int): Boolean{
    return if(event.ownGoals != tempOwnGoals || event.opponentGoals != teamOpponentGoals) true else false
}