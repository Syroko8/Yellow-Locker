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
import com.example.laboratorio_b.ui.navigation.Destinations
import com.example.nicolaspuebla_proyecto_final_android.R
import com.example.nicolaspuebla_proyecto_final_android.ui.components.ErrorDialog
import com.example.nicolaspuebla_proyecto_final_android.ui.components.ErrorDialogNoRetry
import com.example.nicolaspuebla_proyecto_final_android.ui.components.LocalitySheet
import com.example.nicolaspuebla_proyecto_final_android.utils.SessionManager

/**
 * Pantalla para la creación de equipos.
 *
 * @param onNav Función de navegación que se ejecuta tras la creación exitosa del equipo.
 * @param viewModel ViewModel que maneja el estado de la pantalla.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateTeamScreen(onNav:(String) -> Unit, viewModel: CreateTeamScreenViewModel = hiltViewModel()){

    val errorMsg by viewModel.errorMessage.collectAsState()
    val userId = SessionManager.user?.id
    val sheetState = rememberModalBottomSheetState()

    LaunchedEffect(Unit) {
        viewModel.getLocalities()
    }

    if(viewModel.creationResult.value){
        onNav(Destinations.LANDING_SCREEN)
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
                onDismiss = { viewModel.localitySheetVisibility.value = false},
                state = sheetState,
                viewModel
            )
        }

        if(errorMsg != ""){
            if(errorMsg == stringResource(R.string.must_fill)){
                ErrorDialogNoRetry(
                    onOk = { viewModel.setErrMsg("") },
                    err = errorMsg
                )
            } else {
                ErrorDialog(
                    err = errorMsg,
                    onOk = { viewModel.setErrMsg("") },
                    onRetry = { userId?.let { viewModel.createTeam(userId) } }
                )
            }
        }
    }
}

/**
 * Muestra el título principal de la pantalla.
 */
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

/**
 * Muestra el formulario de creación del equipo, que contiene etiquetas y campos correspondientes.
 *
 * @param viewModel ViewModel que proporciona los valores y acciones del formulario.
 */
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

/**
 * Muestra la columna con las etiquetas para los campos del formulario.
 */
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

/**
 * Muestra la columna con los campos editables del formulario.
 *
 * @param viewModel ViewModel que gestiona el estado de los campos.
 */
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

/**
 * Campo de texto para ingresar el nombre del equipo.
 *
 * @param viewModel ViewModel que contiene el valor del campo y su modificador.
 */
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

/**
 * Botón que abre el panel de selección de localidad.
 *
 * @param viewModel ViewModel que gestiona la visibilidad del panel y la localidad seleccionada.
 */
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

/**
 * Campo de texto para ingresar el nombre del deporte del equipo.
 *
 * @param viewModel ViewModel que contiene el valor del campo y su modificador.
 */
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

/**
 * Botón para seleccionar o cambiar el logo del equipo.
 *
 * @param viewModel ViewModel que maneja el estado de selección del logo.
 */
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

/**
 * Botón que lanza la acción de crear el equipo.
 *
 * @param viewModel ViewModel que maneja la creación del equipo y el estado de carga.
 */
@Composable
fun SaveButton(viewModel: CreateTeamScreenViewModel){
    val loading by viewModel.isLoading.collectAsState()
    val userId = SessionManager.user?.id
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
                    userId?.let { viewModel.createTeam(userId = it) }
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

