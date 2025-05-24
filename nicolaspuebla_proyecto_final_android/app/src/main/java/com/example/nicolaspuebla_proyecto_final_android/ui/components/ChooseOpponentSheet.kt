package com.example.nicolaspuebla_proyecto_final_android.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.QrCode2
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import com.example.nicolaspuebla_proyecto_final_android.R
import com.example.nicolaspuebla_proyecto_final_android.ui.screens.createEvent.CreateEventScreenViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChooseOpponentSheet(
    dismiss: () -> Unit,
    state: SheetState,
    viewModel: CreateEventScreenViewModel
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

@Composable
fun SearchBar(viewModel: CreateEventScreenViewModel){
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
            value = viewModel.searchBarQuery.value,
            onValueChange = {
                viewModel.searchBarQuery.value = it
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

@Composable
fun List(viewModel: CreateEventScreenViewModel) {
    val loading by viewModel.isLoading.collectAsState()

    if (loading) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CircularProgressIndicator()
        }
    } else if(viewModel.searchBarQuery.value == "") {
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
    } else if((viewModel.searchBarQuery.value != "") && (viewModel.filteredTeamList.value.isEmpty())){
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
            items(viewModel.filteredTeamList.value, key = { it.id }){
                TeamCard(
                    it,
                    onClick = { team ->
                        viewModel.opponent.value = team
                        viewModel.showTeamChoosingSheet.value = false
                    }
                )
            }
        }
    }
}
