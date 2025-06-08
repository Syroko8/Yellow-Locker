package com.example.nicolaspuebla_proyecto_final_android.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nicolaspuebla_proyecto_final_android.R
import com.example.nicolaspuebla_proyecto_final_android.ui.screens.teamMembers.TeamMembersScreenViewModel
import com.example.nicolaspuebla_proyecto_final_android.utils.SessionManager
import com.example.nicolaspuebla_proyecto_final_android.utils.TeamRoles

/**
 * Función que permite modificar el rol de un miembro, al iguál que su posición, en caso de ser jugador.
 *
 * @param dismiss Callback que se ejecuta al cerrar el desplegable.
 * @param state Estado del desplegable.
 * @param viewModel View model con que gestiona la información.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ModifyMemberSheet(
    dismiss: () -> Unit,
    state: SheetState,
    viewModel: TeamMembersScreenViewModel
){
    val actualRol by SessionManager.actualTeamRole.collectAsState()

    ModalBottomSheet(
        onDismissRequest = { dismiss() },
        sheetState = state,
        containerColor = (Color(244,235,235))
    ){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(244, 235, 235))
                .padding(vertical = 20.dp, horizontal = 20.dp)
        ) {
            SaveButton(viewModel)
            Body(viewModel, actualRol ?: TeamRoles.Coach)
            if(actualRol == TeamRoles.Captain){
                ExpelMemberButton(viewModel)
            }
        }
    }
}

/**
 * Función que ofrece al usuario la posibilidad de guardar los cambios efectuados.
 *
 * @param viewModel View model que gestionará la eliminación del usuario.
 */
@Composable
fun SaveButton(viewModel: TeamMembersScreenViewModel){
    val loading by viewModel.isLoading.collectAsState()

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        horizontalArrangement = Arrangement.End
    ){
        Button(
            onClick = {
                if(!loading){
                    viewModel.team.value?.id?.let { viewModel.saveChanges(it) }
                    viewModel.modifyMemberSheetVisibility.value = false
                }
            },
            modifier = Modifier.wrapContentSize(),
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(
                contentColor = Color.White,
                containerColor = Color(80, 203, 67, 255)
            )
        ){
            if(loading){
                CircularProgressIndicator(modifier = Modifier.size(20.dp))
            } else {
                Text(
                    text = stringResource(R.string.save_changes),
                    fontSize = 16.sp,
                    fontFamily = FontFamily(Font(R.font.jura_bold)),
                    color = Color.Black
                )
            }
        }
    }
}

@Composable
fun Body(viewModel: TeamMembersScreenViewModel, actualRol: TeamRoles){

    Column(
        modifier = Modifier
            .padding(top = 20.dp, end = 20.dp)
            .fillMaxWidth()
            .wrapContentHeight()
    ){
        if(actualRol == TeamRoles.Captain){
            CaptainBody(viewModel)
        } else if(actualRol == TeamRoles.Coach){
            CoachBody(viewModel)
        }
    }
}

@Composable
fun CaptainBody(viewModel: TeamMembersScreenViewModel){
    Row(
        modifier = Modifier
            .padding(end = 20.dp)
            .fillMaxWidth()
            .wrapContentHeight(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Text(
            text = stringResource(R.string.rol),
            fontSize = 18.sp,
            fontFamily = FontFamily(Font(R.font.jura_bold)),
            color = Color.Black
        )
        RolPicker(viewModel)
    }

    if(viewModel.selectedMember.value?.rolType == TeamRoles.Player.name){
        Row(
            modifier = Modifier
                .padding(top = 20.dp, end = 20.dp)
                .fillMaxWidth()
                .wrapContentHeight(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Text(
                text = stringResource(R.string.position),
                fontSize = 18.sp,
                fontFamily = FontFamily(Font(R.font.jura_bold)),
                color = Color.Black
            )
            PositionPicker(viewModel)
        }
    }
}

@Composable
fun CoachBody(viewModel: TeamMembersScreenViewModel){
    Row(
        modifier = Modifier
            .padding(top = 20.dp, end = 20.dp)
            .fillMaxWidth()
            .wrapContentHeight(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Text(
            text = stringResource(R.string.position),
            fontSize = 18.sp,
            fontFamily = FontFamily(Font(R.font.jura_bold)),
            color = Color.Black
        )
        PositionPicker(viewModel)
    }
}


@Composable
fun RolPicker(viewModel: TeamMembersScreenViewModel){
    var expanded by remember { mutableStateOf(false) }
    val rolPlaceholder = viewModel.selectedMember.value?.rolType ?: stringResource(R.string.select)

    Column(
        modifier = Modifier.wrapContentSize()
    ) {
        Button(
            onClick = { expanded = true },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(169, 169, 165, 255),
                contentColor = Color.Black
            )
        ) {
            Text(
                text = viewModel.newRol.value?.name ?: rolPlaceholder,
                fontSize = 18.sp,
                fontFamily = FontFamily(Font(R.font.jura_bold)),
                color = Color.Black
            )
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.background(Color.White)
        ) {
            TeamRoles.entries.forEach { rol ->
                DropdownMenuItem(
                    text = {
                        Text(
                            text = rol.name,
                            fontSize = 16.sp,
                            fontFamily = FontFamily(Font(R.font.jura_bold)),
                            color = Color.Black
                        )
                    },
                    onClick = {
                        viewModel.newRol.value = rol
                        expanded = false
                    }
                )
                if(rol != TeamRoles.Captain){
                    HorizontalDivider()
                }
            }
        }
    }
}

@Composable
fun PositionPicker(viewModel: TeamMembersScreenViewModel){
    var expanded by remember { mutableStateOf(false) }
    val positions by viewModel.positions.collectAsState()
    val team by viewModel.team.collectAsState()
    val position = team?.id?.let { getPosition(viewModel.selectedMember.value!!, it) }?: stringResource(R.string.no_position)

    Column(
        modifier = Modifier.wrapContentSize()
    ) {
        Button(
            onClick = { expanded = true },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(169, 169, 165, 255),
                contentColor = Color.Black
            )
        ) {
            Text(
                text = viewModel.newPosition.value?.name ?: position,
                fontSize = 18.sp,
                fontFamily = FontFamily(Font(R.font.jura_bold)),
                color = Color.Black
            )
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .heightIn(max = 150.dp)
                .background(Color.White)
        ) {

            positions.forEach { position ->
                DropdownMenuItem(
                    onClick = {
                        viewModel.newPosition.value = position
                        expanded = false
                    },
                    text = {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(end = 8.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = position.name,
                                fontSize = 16.sp,
                                fontFamily = FontFamily(Font(R.font.jura_bold)),
                                color = Color.Black
                            )
                            Icon(
                                imageVector = Icons.Default.Remove,
                                contentDescription = stringResource(R.string.delete_position_icon),
                                tint = Color(231, 97, 97, 255),
                                modifier = Modifier
                                    .size(40.dp)
                                    .padding(start = 8.dp)
                                    .clickable {
                                       viewModel.deletePosition(position)
                                    }
                            )
                        }
                    }
                )
                HorizontalDivider()
            }
            DropdownMenuItem(
                text = {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start
                    ) {
                        Text(
                            text = stringResource(R.string.create_position),
                            fontSize = 16.sp,
                            fontFamily = FontFamily(Font(R.font.jura_bold)),
                            color = Color.Black
                        )
                        Spacer(Modifier.width(15.dp))
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = stringResource(R.string.dropdown_new_position_description),
                            tint = Color(63, 115, 224, 255)
                        )
                    }
                },
                onClick = {
                    viewModel.creatingNewPosition.value = true
                    expanded = false
                }
            )
        }
    }
}

@Composable
fun ExpelMemberButton(viewModel: TeamMembersScreenViewModel){
    Row(
        modifier = Modifier
            .padding(top = 20.dp)
            .fillMaxWidth()
            .wrapContentHeight(),
        horizontalArrangement = Arrangement.Start
    ){
        Button(
            onClick = {
                viewModel.expelMember()
                viewModel.modifyMemberSheetVisibility.value = false
            },
            modifier = Modifier.wrapContentSize(),
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(
                contentColor = Color.White,
                containerColor = Color(236, 53, 81, 255)
            )
        ){
            Text(
                text = stringResource(R.string.expel_member),
                fontSize = 16.sp,
                fontFamily = FontFamily(Font(R.font.jura_bold)),
                color = Color.Black
            )
        }
    }
}