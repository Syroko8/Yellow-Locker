package com.example.nicolaspuebla_proyecto_final_android.ui.screens.createTeam

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
import com.example.nicolaspuebla_proyecto_final_android.ui.components.ErrorDialog
import com.example.nicolaspuebla_proyecto_final_android.ui.components.LocalitySheet
import com.example.nicolaspuebla_proyecto_final_android.utils.SessionManager

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateTeamScreen(viewModel: CreateTeamScreenViewModel = hiltViewModel()){

    val errorMsg by viewModel.errorMessage.collectAsState()
    val userId = SessionManager.user?.id
    val sheetState = rememberModalBottomSheetState()

    LaunchedEffect(Unit) {
        println(">>>>>>>>>>>>>getting")
        viewModel.getLocalities()
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
        SaveButton(viewModel)

        if(viewModel.localitySheetVisibility.value){
            LocalitySheet(
                onDismiss = {},
                state = sheetState,
                viewModel
            )
        }

        if(errorMsg != ""){
            ErrorDialog(
                err = errorMsg,
                onOk = { viewModel.setErrMsg("") },
                onRetry = { userId?.let { viewModel.createTeam(userId) } }
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
            text = stringResource(R.string.new_team),
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
fun Form(viewModel: CreateTeamScreenViewModel){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(top = 40.dp, start = 20.dp, end = 20.dp),
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.Start
    ) {
        LabelsColumn()
        FieldsColumn(viewModel)
    }
}

@Composable
fun LabelsColumn(){
    Column(
        modifier = Modifier
            .wrapContentSize()
            .padding(top = 10.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = stringResource(R.string.name),
            fontFamily = FontFamily(Font(R.font.jura_semi_bold)),
            color = Color.Black,
            fontSize = 16.sp
        )
        Spacer(Modifier.height(40.dp))
        Text(
            text = stringResource(R.string.locality),
            fontFamily = FontFamily(Font(R.font.jura_semi_bold)),
            color = Color.Black,
            fontSize = 16.sp
        )
        Spacer(Modifier.height(40.dp))
        Text(
            text = stringResource(R.string.sport),
            fontFamily = FontFamily(Font(R.font.jura_semi_bold)),
            color = Color.Black,
            fontSize = 16.sp
        )
        Spacer(Modifier.height(40.dp))
        Text(
            text = stringResource(R.string.logo),
            fontFamily = FontFamily(Font(R.font.jura_semi_bold)),
            color = Color.Black,
            fontSize = 16.sp
        )
        Text(
            text = "(${stringResource(R.string.optional)})",
            fontFamily = FontFamily(Font(R.font.jura_regular)),
            color = Color.Black,
            fontSize = 12.sp
        )
    }
}

@Composable
fun FieldsColumn(viewModel: CreateTeamScreenViewModel){
    Column(
        modifier = Modifier
            .wrapContentSize()
            .padding(start = 20.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        NameTextField(viewModel)
        LocalityButton(viewModel)
        SportTextField(viewModel)
        LogoButton(viewModel)
    }
}

@Composable
fun NameTextField(viewModel: CreateTeamScreenViewModel){
    BasicTextField(
        value = viewModel.nameTextFieldValue.value,
        onValueChange = { viewModel.nameTextFieldValue.value = it },
        singleLine = true,
        modifier = Modifier
            .padding(bottom = 20.dp)
            .height(40.dp)
            .fillMaxWidth()
            .background(Color(217, 217, 217), shape = RoundedCornerShape(20.dp))
            .padding(horizontal = 12.dp, vertical = 8.dp),
        textStyle = LocalTextStyle.current.copy(
            color = Color.Black,
            fontSize = 16.sp,
            fontFamily = FontFamily(Font(R.font.jura_semi_bold))
        ),
        decorationBox = { innerTextField ->
            innerTextField()
        }
    )
}

@Composable
fun LocalityButton(viewModel: CreateTeamScreenViewModel){
    Button(
        modifier = Modifier
            .padding(bottom = 20.dp)
            .wrapContentSize(),
        onClick = { viewModel.localitySheetVisibility.value = true },
        shape = RoundedCornerShape(20.dp),
        colors = ButtonDefaults.buttonColors(
            contentColor = Color.Black,
            containerColor = Color(217,217,217)
        )
    ) {
        Text(
            text = if(viewModel.selectedLocality.value == null) stringResource(R.string.select) else
                viewModel.selectedLocality.value!!.name,
            fontFamily = FontFamily(Font(R.font.jura_semi_bold)),
            color = Color.Black,
            fontSize = 16.sp
        )
    }
}

@Composable
fun SportTextField(viewModel: CreateTeamScreenViewModel){
    BasicTextField(
        value = viewModel.sportTextFieldValue.value,
        onValueChange = { viewModel.sportTextFieldValue.value = it },
        singleLine = true,
        modifier = Modifier
            .padding(bottom = 20.dp)
            .height(40.dp)
            .fillMaxWidth()
            .background(Color(217, 217, 217), shape = RoundedCornerShape(20.dp))
            .padding(horizontal = 12.dp, vertical = 8.dp),
        textStyle = LocalTextStyle.current.copy(
            color = Color.Black,
            fontSize = 16.sp,
            fontFamily = FontFamily(Font(R.font.jura_semi_bold))
        ),
        decorationBox = { innerTextField ->
            innerTextField()
        }
    )
}

@Composable
fun LogoButton(viewModel: CreateTeamScreenViewModel){
    Button(
        modifier = Modifier
            .wrapContentSize(),
        onClick = {
            viewModel.logoSelected.value = true
        },
        shape = RoundedCornerShape(20.dp),
        colors = ButtonDefaults.buttonColors(
            contentColor = Color.Black,
            containerColor = Color(217,217,217)
        )
    ) {
        Text(
            text = if(viewModel.logoSelected.value) stringResource(R.string.change) else
                stringResource(R.string.select),
            fontFamily = FontFamily(Font(R.font.jura_semi_bold)),
            color = Color.Black,
            fontSize = 16.sp
        )
    }
}

@Composable
fun SaveButton(viewModel: CreateTeamScreenViewModel){
    val loading by viewModel.isLoading.collectAsState()

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

