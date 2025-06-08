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
 * @param onRetry Callback que se ejecuta al pulsar dismissButton.
 */
@Composable
fun ErrorDialog(
    err: String,
    onOk: () -> Unit,
    onRetry: () -> Unit
) {
    AlertDialog(
        onDismissRequest = { },
        text = { Text(err) },
        confirmButton = {
            Button(
                onClick = { onRetry() }
            ) {
                Text(stringResource(R.string.retry))
            }
        },
        dismissButton = {
            Button(
                onClick = { onOk() }
            ) {
                Text("OK")
            }
        }
    )
}