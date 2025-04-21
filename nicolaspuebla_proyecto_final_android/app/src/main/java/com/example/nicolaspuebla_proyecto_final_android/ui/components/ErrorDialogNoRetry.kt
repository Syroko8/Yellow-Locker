package com.example.nicolaspuebla_proyecto_final_android.ui.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.nicolaspuebla_proyecto_final_android.R

@Composable
fun ErrorDialogNoRetry(
    err: String,
    onOk: () -> Unit,
) {
    AlertDialog(
        onDismissRequest = { },
        text = { Text(err) },
        confirmButton = { },
        dismissButton = {
            Button(
                onClick = { onOk() }
            ) {
                Text("OK")
            }
        }
    )
}