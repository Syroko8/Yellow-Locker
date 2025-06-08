package com.example.nicolaspuebla_proyecto_final_android.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.nicolaspuebla_proyecto_final_android.R
import com.example.nicolaspuebla_proyecto_final_android.ui.screens.teamMembers.TeamMembersScreenViewModel

/**
 * Diálogo que permite al usuario especificar el nombre de la nueva posición para el equipo.
 *
 * @param onDismiss Callback que se ejecuta cuando se cancela la acción.
 * @param onConfirm Callback que se ejecuta al confirmar la acción.
 * @param viewModel View model que gestiona la información.
 */
@Composable
fun PositionCreationDialog(
    onDismiss: () -> Unit,
    onConfirm: () -> Unit,
    viewModel: TeamMembersScreenViewModel
){
    val placeholder = stringResource(R.string.name)
    AlertDialog(
        title = {
            Text(
                text = stringResource(R.string.new_position),
                color = Color.Black
            )
        },
        text = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
            ) {
                Text(
                    text = stringResource(R.string.new_position_name_petition),
                    modifier = Modifier.padding(bottom = 8.dp),
                    color = Color.Black
                )
                TextField(
                    value = viewModel.createdPositionName.value,
                    onValueChange = { viewModel.createdPositionName.value = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .background(Color.White, RoundedCornerShape(8.dp)),
                    placeholder = { Text(placeholder) },
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,
                        disabledContainerColor = Color.Transparent,
                        focusedTextColor = Color.Black,
                        unfocusedTextColor = Color.Black
                    ),
                    singleLine = true,
                )
            }
        },
        onDismissRequest = {
            onDismiss()
        },
        confirmButton = {
            TextButton(
                onClick = {
                    if(viewModel.createdPositionName.value != ""){
                        onConfirm()
                    }
                }
            ) {
                Text(
                    stringResource(R.string.confirm),
                    color = Color.Black
                )
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    onDismiss()
                }
            ) {
                Text(
                    stringResource(R.string.cancel),
                    color = Color.Black
                )
            }
        },
        shape = RoundedCornerShape(16.dp),
        containerColor = Color(222, 216, 216, 255)
    )
}