package com.example.nicolaspuebla_proyecto_final_android.ui.screens.signup

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Circle
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.nicolaspuebla_proyecto_final_android.R
import com.example.nicolaspuebla_proyecto_final_android.ui.components.DateTextField

@Composable
fun SignUpScreen(innerPadding: PaddingValues, viewModel: SignUpScreenViewModel = hiltViewModel()){

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
            .background(color = Color.Black),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Upper()
        Lower(viewModel)
    }
}

@Composable
fun Upper(){

    Column(
        modifier = Modifier
            .padding(top = 50.dp)
            .wrapContentHeight()
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Text(
            text = stringResource(R.string.title),
            //"0xFFF1CD2F".toColorInt()
            color = Color.Yellow,
            fontSize = 40.sp,
            fontFamily = FontFamily(Font(R.font.jura_regular))
        )

        Text(
            text = stringResource(R.string.signup),
            fontFamily = FontFamily(Font(R.font.jura_regular)),
            fontSize = 30.sp,
            modifier = Modifier.padding(top = 10.dp, bottom = 20.dp)
        )
    }
}

@Composable
fun Lower(viewModel: SignUpScreenViewModel){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .clip(shape = RoundedCornerShape(topStart = 50.dp))
            .background(Color.White)
            .padding(bottom = 20.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Top
    ) {
        LoginButtonRow()
        NameTextField(viewModel)
        SurnameTextField(viewModel)
        SecondSurnameTextField(viewModel)
        MailTextField(viewModel)
        BirthDateTextField(viewModel)
        ProgressionCircles()
        NextUpButton(viewModel)
    }
}

@Composable
fun LoginButtonRow(){
    Row(
        modifier = Modifier
            .wrapContentWidth()
            .padding(top = 20.dp, start = 20.dp, end = 40.dp)
            .wrapContentHeight(),
        horizontalArrangement = Arrangement.Start
    ) {
        Button(
            onClick = { TODO() },
            colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent, contentColor = Color.Black)
        ) {
            Text(
                text = stringResource(R.string.login),
                fontFamily = FontFamily(Font(R.font.jura_bold)),
                fontSize = 18.sp,
                modifier = Modifier.drawBehind {
                    val strokeWidthPx = 2.dp.toPx()
                    val verticalOffset = size.height - 2.sp.toPx()
                    drawLine(
                        color = Color.Black,
                        strokeWidth = strokeWidthPx,
                        start = Offset(0f, verticalOffset),
                        end = Offset(size.width, verticalOffset)
                    )
                }
            )
        }
    }
}

@Composable
fun NameTextField(viewModel: SignUpScreenViewModel){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 30.dp)
            .padding(horizontal = 25.dp)
            .wrapContentHeight(),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = stringResource(R.string.name),
            color = Color.Black,
            modifier = Modifier.padding(start = 15.dp),
            fontFamily = FontFamily(Font(R.font.jura_bold))
        )
        TextField(
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            ),
            placeholder = {
                Text(
                    text = "adress@gmail.com"
                )
            },
            onValueChange = { viewModel.nameTextFieldVal.value = it },
            value = viewModel.nameTextFieldVal.value,
            singleLine = true
        )

        HorizontalDivider(color = Color.Yellow, thickness = 2.dp, modifier = Modifier.padding(start = 15.dp, end = 15.dp))
    }
}

@Composable
fun SurnameTextField(viewModel: SignUpScreenViewModel){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 20.dp)
            .padding(horizontal = 25.dp)
            .wrapContentHeight(),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = stringResource(R.string.surname),
            color = Color.Black,
            modifier = Modifier.padding(start = 15.dp),
            fontFamily = FontFamily(Font(R.font.jura_bold))
        )
        TextField(
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            ),
            placeholder = {
                Text(
                    text = "adress@gmail.com"
                )
            },
            onValueChange = { viewModel.surnameTextFieldVal.value = it },
            value = viewModel.surnameTextFieldVal.value,
            singleLine = true
        )

        HorizontalDivider(color = Color.Yellow, thickness = 2.dp, modifier = Modifier.padding(start = 15.dp, end = 15.dp))
    }
}

@Composable
fun SecondSurnameTextField(viewModel: SignUpScreenViewModel){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 30.dp)
            .padding(horizontal = 25.dp)
            .wrapContentHeight(),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = stringResource(R.string.second_surname),
            color = Color.Black,
            modifier = Modifier.padding(start = 15.dp),
            fontFamily = FontFamily(Font(R.font.jura_bold))
        )
        TextField(
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            ),
            placeholder = {
                Text(
                    text = "adress@gmail.com"
                )
            },
            onValueChange = { viewModel.secondSurnameTextFieldVal.value = it },
            value = viewModel.secondSurnameTextFieldVal.value,
            singleLine = true
        )

        HorizontalDivider(color = Color.Yellow, thickness = 2.dp, modifier = Modifier.padding(start = 15.dp, end = 15.dp))
    }
}

@Composable
fun MailTextField(viewModel: SignUpScreenViewModel){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 30.dp)
            .padding(horizontal = 25.dp)
            .wrapContentHeight(),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = stringResource(R.string.email_adress),
            color = Color.Black,
            modifier = Modifier.padding(start = 15.dp),
            fontFamily = FontFamily(Font(R.font.jura_bold))
        )
        TextField(
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            ),
            placeholder = {
                Text(
                    text = "adress@gmail.com"
                )
            },
            onValueChange = { viewModel.mailTextFieldVal.value = it },
            value = viewModel.mailTextFieldVal.value,
            singleLine = true
        )

        HorizontalDivider(color = Color.Yellow, thickness = 2.dp, modifier = Modifier.padding(start = 15.dp, end = 15.dp))
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun BirthDateTextField(viewModel: SignUpScreenViewModel){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 30.dp)
            .padding(horizontal = 25.dp)
            .wrapContentHeight(),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = stringResource(R.string.birth_date),
            color = Color.Black,
            modifier = Modifier.padding(start = 15.dp),
            fontFamily = FontFamily(Font(R.font.jura_bold))
        )

        DateTextField(
            value = viewModel.dateTextFieldVal.value,
            onValueChange = {viewModel.dateTextFieldVal.value = it },
        )

        HorizontalDivider(color = Color.Yellow, thickness = 2.dp, modifier = Modifier.padding(start = 15.dp, end = 15.dp))
    }
}

@Composable
fun ProgressionCircles(){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(top = 40.dp, bottom = 10.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        Icon(
            imageVector = Icons.Filled.Circle,
            contentDescription = stringResource(R.string.circle_icon_description),
            tint = Color.Black,
            modifier = Modifier.size(10.dp)
        )

        Spacer(Modifier.width(10.dp))

        Icon(
            imageVector = Icons.Filled.Circle,
            contentDescription = stringResource(R.string.circle_icon_description),
            tint = Color(197,191,191),
            modifier = Modifier.size(10.dp)
        )
    }
}

@Composable
fun NextUpButton(viewModel: SignUpScreenViewModel){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(horizontal = 40.dp),
        horizontalArrangement = Arrangement.Center
    ){
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .background(Color.Transparent)
                .clip(RoundedCornerShape(20.dp)),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Black, contentColor = Color.White),
            onClick = { TODO() }
        ) {
            Text(
                text = stringResource(R.string.next_mayus),
                fontSize = 25.sp,
            )
        }
    }
}