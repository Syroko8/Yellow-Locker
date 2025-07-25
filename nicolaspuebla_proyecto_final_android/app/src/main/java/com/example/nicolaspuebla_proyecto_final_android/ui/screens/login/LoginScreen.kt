package com.example.nicolaspuebla_proyecto_final_android.ui.screens.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.nicolaspuebla_proyecto_final_android.R


@Composable
fun LoginScreen(innerPadding: PaddingValues, viewModel: LoginScreenViewModel = hiltViewModel()){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
            .background(color = Color.White),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Upper()
        Bottom(viewModel)
    }
}

@Composable
fun Upper(){
    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(bottomEnd = 50.dp))
            .background(Color.Black)
            .padding(bottom = 20.dp)
            .wrapContentHeight()
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(top = 50.dp),
            horizontalArrangement = Arrangement.Center

        ) {
            Text(
                text = stringResource(R.string.title),
                //"0xFFF1CD2F".toColorInt()
                color = Color.Yellow,
                fontSize = 40.sp,
                fontFamily = FontFamily(Font(R.font.jura_regular))
            )
        }

        Image(
            painter = painterResource(R.drawable.lockerlogo),
            contentDescription = stringResource(R.string.login_image_description),
            modifier = Modifier.size(250.dp)
        )

        Text(
            text = stringResource(R.string.login),
            fontFamily = FontFamily(Font(R.font.jura_regular)),
            fontSize = 30.sp
        )
    }
}

@Composable
fun Bottom(viewModel: LoginScreenViewModel){
    Box(
        modifier = Modifier
            .background(Color.Black)
            .fillMaxSize()
    ){
        Formulary(viewModel)
    }
}

@Composable
fun Formulary(viewModel: LoginScreenViewModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .clip(shape = RoundedCornerShape(topStart = 50.dp))
            .background(Color.White),
        verticalArrangement = Arrangement.Top,
    ) {
        MailTextField(viewModel)
        Spacer(modifier = Modifier.height(30.dp))
        PasswdTextField(viewModel)
        ButtonRow()
    }
}

@Composable
fun MailTextField(viewModel: LoginScreenViewModel){
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
            modifier = Modifier.padding(start = 15.dp)
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

        HorizontalDivider(color = Color.Yellow, thickness = 2.dp, modifier = Modifier.padding(start = 15.dp))
    }
}

@Composable
fun PasswdTextField(viewModel: LoginScreenViewModel){
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
            modifier = Modifier.padding(start = 15.dp)
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

        HorizontalDivider(color = Color.Yellow, thickness = 2.dp, modifier = Modifier.padding(start = 15.dp))
    }
}

@Composable
fun ButtonRow(){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentWidth(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ){
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp, start = 40.dp, end = 40.dp)
                .wrapContentHeight(),
            horizontalArrangement = Arrangement.Start
        ) {
            Text(
                text = stringResource(R.string.accountQuestion),
                color = Color.Black
            )
        }

        Row(
            modifier = Modifier
                .wrapContentWidth()
                .padding(start = 40.dp, end = 40.dp)
                .wrapContentHeight(),
            horizontalArrangement = Arrangement.Start
        ) {
            Button(
                onClick = { TODO() },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent, contentColor = Color.Black)
            ) {
                Text(
                    text = stringResource(R.string.singUp),
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

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(horizontal = 40.dp)
                .padding(top = 10.dp),
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
                    text = stringResource(R.string.login_mayusc),
                    fontSize = 25.sp,
                )
            }
        }

    }

}
