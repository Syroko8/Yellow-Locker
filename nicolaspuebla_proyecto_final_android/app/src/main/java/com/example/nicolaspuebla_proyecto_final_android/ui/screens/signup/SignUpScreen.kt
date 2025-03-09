package com.example.nicolaspuebla_proyecto_final_android.ui.screens.signup

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.nicolaspuebla_proyecto_final_android.R
import com.example.nicolaspuebla_proyecto_final_android.ui.screens.login.Bottom
import com.example.nicolaspuebla_proyecto_final_android.ui.screens.login.Upper

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

    ) {
        Text(
            text = stringResource(R.string.title),
            //"0xFFF1CD2F".toColorInt()
            color = Color.Yellow,
            fontSize = 40.sp,
            fontFamily = FontFamily(Font(R.font.jura_regular))
        )

        Spacer(Modifier.height(40.dp))

        Text(
            text = stringResource(R.string.singUp),
            fontFamily = FontFamily(Font(R.font.jura_regular)),
            fontSize = 30.sp
        )
    }
}

@Composable
fun Lower(viewModel: SignUpScreenViewModel){
    
}