package com.example.nicolaspuebla_proyecto_final_android.ui.screens.teamMembers

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
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
import com.example.nicolaspuebla_proyecto_final_android.ui.components.ErrorDialogNoRetry
import com.example.nicolaspuebla_proyecto_final_android.ui.components.MemberCard
import com.example.nicolaspuebla_proyecto_final_android.ui.components.ModifyMemberSheet
import com.example.nicolaspuebla_proyecto_final_android.ui.components.PositionCreationDialog
import com.example.nicolaspuebla_proyecto_final_android.utils.SessionManager
import com.example.nicolaspuebla_proyecto_final_android.utils.TeamRoles

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TeamMembersScreen(viewModel: TeamMembersScreenViewModel = hiltViewModel()){
    val teamId by SessionManager.actualTeamId.collectAsState()
    val err by viewModel.errorMessage.collectAsState()
    val sheetState = rememberModalBottomSheetState()

    LaunchedEffect(Unit) {
        teamId?.let {
            viewModel.getMembers(it)
            viewModel.getTeam(it)
            viewModel.getTeamPositions(it)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background((Color(244, 235, 235)))
            .padding(top = 20.dp, bottom = 20.dp)
    ) {
        Title()
        List(viewModel)

        if(err != ""){
            if(err == stringResource(R.string.constraint_err)){
                ErrorDialogNoRetry(
                    err,
                    onOk = { viewModel.unsetError() }
                )
            } else {
                ErrorDialog(
                    err,
                    onRetry = { teamId?.let { viewModel.getMembers(it) } },
                    onOk = { viewModel.unsetError() }
                )
            }
        }
        if(viewModel.modifyMemberSheetVisibility.value){
            ModifyMemberSheet(
                state = sheetState,
                dismiss = { viewModel.modifyMemberSheetVisibility.value = false },
                viewModel = viewModel
            )
        }
        if(viewModel.creatingNewPosition.value){
            viewModel.modifyMemberSheetVisibility.value = false

            PositionCreationDialog(
                onDismiss = { viewModel.creatingNewPosition.value = false },
                onConfirm = {
                    teamId?.let {
                        viewModel.createTeamPosition(it)
                        viewModel.creatingNewPosition.value = false
                        viewModel.createdPositionName.value = ""
                    }
                },
                viewModel = viewModel
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
            text = stringResource(R.string.members),
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
fun List(viewModel: TeamMembersScreenViewModel){

    val members by viewModel.members.collectAsState()
    val teamId by SessionManager.actualTeamId.collectAsState()
    val actualTeamRole by SessionManager.actualTeamRole.collectAsState()
    val userId = SessionManager.user?.id

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 20.dp, start = 40.dp, end = 40.dp, bottom = 20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if(members.isNotEmpty()){
            items(items = members){
                MemberCard(
                    member = it,
                    teamId = teamId!!,
                    onClick = { member ->
                        viewModel.selectedMember.value = member

                        if(actualTeamRole == TeamRoles.Captain){
                            if(userId != member.user.id){
                                viewModel.modifyMemberSheetVisibility.value = true
                            }
                        } else if(actualTeamRole == TeamRoles.Coach){
                            if((userId != member.user.id) && (member.rolType == TeamRoles.Player.name)){
                                viewModel.modifyMemberSheetVisibility.value = true
                            }
                        }
                    }
                )
            }
        } else {
            item {
                NoMembers()
            }
        }
    }
}

@Composable
fun NoMembers(){
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(R.string.no_members),
            color = Color.Black,
            fontSize = 20.sp,
            fontFamily = FontFamily(Font(R.font.jura_bold))
        )
    }
}