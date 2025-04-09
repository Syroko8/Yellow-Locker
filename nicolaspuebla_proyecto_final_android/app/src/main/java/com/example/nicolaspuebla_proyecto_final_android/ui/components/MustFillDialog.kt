package com.example.nicolaspuebla_proyecto_final_android.ui.components

import android.app.AlertDialog
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.nicolaspuebla_proyecto_final_android.R

@Composable
fun MustFillDialog(onOk: () -> Unit) {
    AlertDialog(
        onDismissRequest = { onOk() },
        text = {
            Text(stringResource(R.string.must_fill))
        },
        properties = DialogProperties(
            dismissOnClickOutside = true
        ),
        confirmButton = {
            Button(
                onClick = { onOk() }
            ) {
                Text(stringResource(R.string.ok))
            }
        }
    )
}