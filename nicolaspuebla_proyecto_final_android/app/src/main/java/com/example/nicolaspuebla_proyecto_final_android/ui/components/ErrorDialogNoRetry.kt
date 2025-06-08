package com.example.nicolaspuebla_proyecto_final_android.ui.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.nicolaspuebla_proyecto_final_android.R

/**
 * Diálogo en el que se muestra un error, empleado cuando se produce una excepción en la aplicación.
 *
 * @param err Mensaje de error a mostrar.
 * @param onOk Callback que se ejecuta al pulsar confirmButton.
 */
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