package com.example.nicolaspuebla_proyecto_final_android.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.QrCode2
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.nicolaspuebla_proyecto_final_android.R
import com.example.nicolaspuebla_proyecto_final_android.data.model.dataClases.Team
import com.example.nicolaspuebla_proyecto_final_android.ui.screens.modifyEvents.ModifyEventsScreenViewModel

/**
 * ModalBottomSheet desplegada cuando un usuario quiera cambiar el oponente en la página de modificación de eventos.
 *
 * @param dismiss Callback que se ejecutará al cerrarse el desplegable.
 * @param state Estado que se le pasa al componente, indicando cuando debe hacerse visible.
 * @param viewModel View model de la vista donde se almacena información relacionada al desplegable.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChangeOpponentSheet(
    dismiss: () -> Unit,
    state: SheetState,
    viewModel: ModifyEventsScreenViewModel
){
    ModalBottomSheet(
        onDismissRequest = { dismiss() },
        sheetState = state,
        containerColor = (Color(244,235,235) )
    ){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 20.dp, horizontal = 20.dp)
        ) {
            SearchBar(viewModel)
            List(viewModel)
        }
    }
}

/**
 * Barra de búsqueda.
 *
 * @param viewModel View model de la vista donde se almacenará el input en el TextField.
 */
@Composable
fun SearchBar(viewModel: ModifyEventsScreenViewModel){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .wrapContentHeight()
            .clip(RoundedCornerShape(10.dp))
            .background(Color(217, 217, 217))
            .clip(RoundedCornerShape(10.dp)),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        TextField(
            value = viewModel.teamName.value,
            onValueChange = {
                viewModel.teamName.value = it
                viewModel.filterTeams()
            },
            placeholder = {
                Text(
                    text = stringResource(R.string.team_name),
                    fontFamily = FontFamily(Font(R.font.jura_bold)),
                    color = Color.Black

                )
            },
            trailingIcon = {
                Icon(
                    imageVector = Icons.Rounded.Search,
                    contentDescription = stringResource(R.string.search_icon_description),
                    tint = Color.Black
                )
            },
            singleLine = true,
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.Black
            ),
            modifier = Modifier.weight(4f)
        )

        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
                .background(Color(89, 159, 229))
        ) {
            Button(
                onClick = {  },
                modifier = Modifier.fillMaxSize(),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                shape = RoundedCornerShape(10),
                contentPadding = PaddingValues(0.dp)
            ) {
                Icon(
                    imageVector =  Icons.Rounded.QrCode2,
                    contentDescription = stringResource(R.string.qr_icon_description),
                    modifier = Modifier.size(36.dp)
                )
            }
        }
    }
}

/**
 * Lista de los equipos cuyos nombres contienen lo introducido en la barra de búsqueda.
 *
 * @param viewModel View model de la vista donde se almacena la lista a mostrar.
 */
@Composable
fun List(viewModel: ModifyEventsScreenViewModel) {
    val loading by viewModel.isLoading.collectAsState()
    val list by viewModel.filteredTeamList.collectAsState()
    val teamName = viewModel.teamName.value

    if (loading) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CircularProgressIndicator()
        }
    } else if(teamName == "") {
        Column(
            modifier = Modifier
                .padding(top = 20.dp, start = 40.dp, end = 40.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = stringResource(R.string.search_team_demand),
                fontFamily = FontFamily(Font(R.font.jura_regular)),
                fontSize = 18.sp,
                color = Color.Black
            )
        }
    } else if((teamName != "") && (list.isEmpty())){
        Column(
            modifier = Modifier
                .padding(top = 20.dp, start = 40.dp, end = 40.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = stringResource(R.string.search_team_empty),
                fontFamily = FontFamily(Font(R.font.jura_regular)),
                fontSize = 18.sp,
                color = Color.Black
            )
        }
    } else {
        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            contentPadding = PaddingValues(top = 15.dp, bottom = 8.dp)
        ) {
            items(list, key = { it.id }){
                TeamCard(
                    it,
                    onClick = { team ->
                        viewModel.setChosenOpponent(team)
                        viewModel.hideChangeOpponentSheet()
                    }
                )
            }
        }
    }
}

/**
 * Elemento usado para mostrar la información individual de cada equipo.
 *
 * @param team Equipo cuya información se quiere mostrar.
 * @param onClick Callback que se ejecutará al pulsar sobre el elemento.
 */
@Composable
fun TeamCard(team: Team, onClick: (Team) -> Unit){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp),
        elevation = CardDefaults.cardElevation(5.dp),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        onClick = { onClick(team) }
    ){
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp)
        ) {
            Box(
                modifier = Modifier
                    .width(80.dp)
                    .height(80.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .background(Color.LightGray)
            ){
                AsyncImage(
                    model = team.logo,
                    contentDescription = stringResource(R.string.team_logo_description),
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(20.dp))
                )
            }

            Spacer(Modifier.width(20.dp))

            Column(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = team.name,
                    fontFamily = FontFamily(Font(R.font.jura_semi_bold)),
                    color = Color.Black,
                    fontSize = 16.sp
                )

                Text(
                    text = team.locality.name,
                    fontFamily = FontFamily(Font(R.font.jura_semi_bold)),
                    color = Color.Black,
                    fontSize = 16.sp
                )
                Text(
                    text = team.sport,
                    fontFamily = FontFamily(Font(R.font.jura_semi_bold)),
                    color = Color.Black,
                    fontSize = 16.sp
                )
            }
        }
    }
}