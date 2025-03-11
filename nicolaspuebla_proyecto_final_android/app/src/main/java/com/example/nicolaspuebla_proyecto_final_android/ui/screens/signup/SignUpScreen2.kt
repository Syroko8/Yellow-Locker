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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Circle
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.nicolaspuebla_proyecto_final_android.R

@Composable
fun SignUp2Screen(innerPadding: PaddingValues, viewModel: SignUpScreenViewModel = hiltViewModel()){

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
            .background(color = Color.Black),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Upper2()
        Lower2(viewModel)
    }
}

@Composable
fun Upper2(){
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
fun Lower2(viewModel: SignUpScreenViewModel){

    Column(
        modifier = Modifier
            .fillMaxSize()
            .clip(shape = RoundedCornerShape(topStart = 50.dp))
            .background(Color(255,244,235))
            .padding(bottom = 20.dp),
        verticalArrangement = Arrangement.Top
    ) {
        LoginButtonRow2()
        ReturnButton()

        Spacer(Modifier.height(20.dp))
        Text(
            text = stringResource(R.string.set_passwd),
            fontFamily = FontFamily(Font(R.font.jura_bold)),
            fontSize = 18.sp,
            color = Color.Black,
            modifier = Modifier.padding(start = 40.dp, top = 20.dp)
        )
        Spacer(Modifier.height(40.dp))

        PasswdTextField(viewModel)
        PasswdTextField2(viewModel)

        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Bottom
        ) {
            ProgressionCircles2()
            SignUpButton(viewModel)
        }
    }
}

@Composable
fun LoginButtonRow2(){
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
fun ReturnButton(){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 20.dp, start = 20.dp, end = 40.dp)
            .wrapContentHeight(),
        horizontalArrangement = Arrangement.Start
    ) {
        Button(
            onClick = { TODO() },
            colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent, contentColor = Color.Black),
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = stringResource(R.string.return_button_description)
            )

            Spacer(Modifier.width(10.dp))

            Text(
                text = stringResource(R.string.return_to_signup1),
                fontFamily = FontFamily(Font(R.font.jura_bold)),
                fontSize = 18.sp,
                color = Color.Black
            )
        }
    }
}


@Composable
fun PasswdTextField(viewModel: SignUpScreenViewModel){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 25.dp)
            .wrapContentHeight(),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = stringResource(R.string.passwd),
            color = Color.Black,
            modifier = Modifier.padding(start = 15.dp),
            fontFamily = FontFamily(Font(R.font.jura_bold))
        )

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            ),
            value = viewModel.passwdTextFieldVal.value,
            onValueChange = { viewModel.passwdTextFieldVal.value = it },
            placeholder = { Text("********") },
            visualTransformation = if(viewModel.passwdVisibility.value) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon ={

                val icon = if (viewModel.passwdVisibility.value) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
                val description = if (viewModel.passwdVisibility.value) "Password off" else "Password on"

                IconButton(onClick = { viewModel.passwdVisibility.value = !viewModel.passwdVisibility.value }){
                    Icon(imageVector = icon, contentDescription = description)
                }
            },
            singleLine = true
        )

        HorizontalDivider(color = Color.Yellow, thickness = 2.dp, modifier = Modifier.padding(start = 15.dp, end = 15.dp))
    }
}

@Composable
fun PasswdTextField2(viewModel: SignUpScreenViewModel){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 25.dp)
            .wrapContentHeight(),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = stringResource(R.string.repeat_passwd),
            color = Color.Black,
            modifier = Modifier.padding(start = 15.dp),
            fontFamily = FontFamily(Font(R.font.jura_bold))
        )

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            ),
            value = viewModel.passwdTextFiel2dVal.value,
            onValueChange = { viewModel.passwdTextFiel2dVal.value = it },
            placeholder = { Text("********") },
            visualTransformation = if(viewModel.passwdVisibility2.value) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon ={

                val icon = if (viewModel.passwdVisibility2.value) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
                val description = if (viewModel.passwdVisibility2.value) "Password off" else "Password on"

                IconButton(onClick = { viewModel.passwdVisibility2.value = !viewModel.passwdVisibility2.value }){
                    Icon(imageVector = icon, contentDescription = description)
                }
            },
            singleLine = true
        )

        HorizontalDivider(color = Color.Yellow, thickness = 2.dp, modifier = Modifier.padding(start = 15.dp, end = 15.dp))
    }
}

@Composable
fun ProgressionCircles2(){
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
            tint = Color(197,191,191),
            modifier = Modifier.size(10.dp)
        )

        Spacer(Modifier.width(10.dp))

        Icon(
            imageVector = Icons.Filled.Circle,
            contentDescription = stringResource(R.string.circle_icon_description),
            tint = Color.Black,
            modifier = Modifier.size(10.dp)
        )
    }
}

@Composable
fun SignUpButton(viewModel: SignUpScreenViewModel){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(horizontal = 40.dp)
            .padding(bottom = 20.dp),
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
                text = stringResource(R.string.signup_mayusc),
                fontSize = 25.sp,
            )
        }
    }
}