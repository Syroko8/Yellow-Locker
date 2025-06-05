package com.example.nicolaspuebla_proyecto_final_android.ui.screens.signup2

import android.content.Context
import android.view.LayoutInflater
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material.icons.filled.Circle
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.laboratorio_b.ui.navigation.Destinations
import com.example.nicolaspuebla_proyecto_final_android.R
import kotlinx.coroutines.delay


@Composable
fun SignUp2Screen(onNav: (String) -> Unit, viewModel: SignUp2ScreenViewModel = hiltViewModel()){

    val signed by viewModel.signed.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(signed) {
        if(signed) showToast(context, onNav = { onNav(it) })
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Black),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Upper2()
        Lower2(
            viewModel,
            onNav = { onNav(it) }
        )
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
            color = Color(241, 205, 47),
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
fun Lower2(viewModel: SignUp2ScreenViewModel, onNav: (String) -> Unit){

    Column(
        modifier = Modifier
            .fillMaxSize()
            .clip(shape = RoundedCornerShape(topStart = 50.dp))
            .background(Color(244, 235, 235))
            .padding(bottom = 20.dp),
        verticalArrangement = Arrangement.Top
    ) {
        LoginButtonRow2(onNav = { onNav(it) })
        ReturnButton(onNav = { onNav(it) })

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
            ProgressionCircles2(viewModel)
            SignUpButton(viewModel)
        }
    }
}

@Composable
fun LoginButtonRow2(onNav: (String) -> Unit){
    Row(
        modifier = Modifier
            .wrapContentWidth()
            .padding(top = 20.dp, start = 20.dp, end = 40.dp)
            .wrapContentHeight(),
        horizontalArrangement = Arrangement.Start
    ) {
        Button(
            onClick = { onNav(Destinations.LOGIN) },
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
fun ReturnButton(onNav: (String) -> Unit){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 20.dp, start = 20.dp, end = 40.dp)
            .wrapContentHeight(),
        horizontalArrangement = Arrangement.Start
    ) {
        Button(
            onClick = { onNav(Destinations.SIGN_UP) },
            colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent, contentColor = Color.Black),
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = stringResource(R.string.return_button_description)
            )

            Spacer(Modifier.width(10.dp))

            Text(
                text = stringResource(R.string.return_str),
                fontFamily = FontFamily(Font(R.font.jura_bold)),
                fontSize = 18.sp,
                color = Color.Black
            )
        }
    }
}


@Composable
fun PasswdTextField(viewModel: SignUp2ScreenViewModel){

    val passwd by viewModel.getPasswd().collectAsState()

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
                disabledIndicatorColor = Color.Transparent,
                focusedTextColor = Color.Gray,
                unfocusedTextColor = Color.Gray,
                focusedPlaceholderColor = Color.Gray,
                unfocusedPlaceholderColor = Color.Gray
            ),
            value = passwd,
            onValueChange = { viewModel.updatePasswd(it) },
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

        HorizontalDivider(color = Color(241, 205, 47), thickness = 2.dp, modifier = Modifier.padding(start = 15.dp, end = 15.dp))
        Spacer(Modifier.height(20.dp))
    }
}

@Composable
fun PasswdTextField2(viewModel: SignUp2ScreenViewModel){

    val checkPasswd by viewModel.getPasswdCheck().collectAsState()
    val passwd1 by viewModel.getPasswd().collectAsState()

    var fontColor = if ((checkPasswd != passwd1) && checkPasswd != "") Color.Red else Color.Black

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 25.dp)
            .wrapContentHeight(),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = if(fontColor == Color.Black) stringResource(R.string.repeat_passwd) else
                stringResource(R.string.repeat_passwd_exclamation),
            color = fontColor,
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
                disabledIndicatorColor = Color.Transparent,
                focusedTextColor = Color.Gray,
                unfocusedTextColor = Color.Gray,
                focusedPlaceholderColor = Color.Gray,
                unfocusedPlaceholderColor = Color.Gray
            ),
            value = checkPasswd,
            onValueChange = { viewModel.updatePasswdCheck(it) },
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

        HorizontalDivider(color = Color(241, 205, 47), thickness = 2.dp, modifier = Modifier.padding(start = 15.dp, end = 15.dp))
    }
}

@Composable
fun ProgressionCircles2(viewModel: SignUp2ScreenViewModel){

    val loading by viewModel.isLoading.collectAsState()
    val signed by viewModel.signed.collectAsState()

    if(!loading && !signed){
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
}

@Composable
fun SignUpButton(viewModel: SignUp2ScreenViewModel){

    val loading by viewModel.isLoading.collectAsState()
    val passwd by viewModel.getPasswd().collectAsState()
    val passwdCheck by viewModel.getPasswdCheck().collectAsState()
    val signed by viewModel.signed.collectAsState()
    val mustFillMsg = stringResource(R.string.must_fill)
    val mustValidEmailMsg = stringResource(R.string.must_valid_email)

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(horizontal = 40.dp)
            .padding(bottom = 20.dp),
        horizontalArrangement = Arrangement.Center
    ){

        if(loading && !signed){
            CircularProgressIndicator()
            Spacer(Modifier.height(40.dp))
        } else if(!signed){
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .background(Color.Transparent)
                    .clip(RoundedCornerShape(20.dp)),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Black, contentColor = Color.White),
                onClick = {
                    if(viewModel.filledFields() && (passwd == passwdCheck)){
                        if(viewModel.validateEmail()){
                            viewModel.signUp()
                        } else {
                            viewModel.setErr(mustValidEmailMsg)
                        }
                    } else {
                        viewModel.setErr(mustFillMsg)
                    }
                }
            ) {
                Text(
                    text = stringResource(R.string.signup_mayusc),
                    fontSize = 25.sp,
                )
            }
        }
    }
}

suspend fun showToast(context: Context, onNav: (String) -> Unit) {
    val toast = Toast(context).apply {
        duration = Toast.LENGTH_LONG
        view = LayoutInflater.from(context).inflate(R.layout.achieved_signup_toast_layout, null)
    }
    toast.show()
    returnToLoging(onNav = { onNav(it) })
}

suspend fun returnToLoging(onNav: (String) -> Unit){
    delay(300)
    onNav(Destinations.LOGIN)
}