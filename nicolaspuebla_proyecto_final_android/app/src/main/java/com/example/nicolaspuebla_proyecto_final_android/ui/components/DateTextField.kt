package com.example.nicolaspuebla_proyecto_final_android.ui.components

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.withStyle
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.TimeZone

/**
 * Campo de texto personalizado para ingresar fechas con formato dd-MM-yyyy.
 *
 * @param value Valor actual del campo de texto.
 * @param modifier Modificador Compose para aplicar estilos y comportamiento.
 * @param onValueChange Callback que se ejecuta cuando el contenido del campo cambia.
 */
@Composable
fun DateTextField(
    value: TextFieldValue,
    modifier: Modifier = Modifier,
    onValueChange: (TextFieldValue) -> Unit
) {

    var isBackspacePressed by remember { mutableStateOf(false) }

    OutlinedTextField(
        value = value,
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Color.Transparent,
            unfocusedBorderColor = Color.Transparent
        ),
        visualTransformation = DateFormatVisualTransformation(LocalTextStyle.current),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Done
        ),
        onValueChange = {
            val date = it.text.takeDigitString()

            if (date.length < 9) {
                val selection = if (!isBackspacePressed) {
                    when (date.length) {
                        3, 5 -> it.selection.start + 1
                        else -> it.selection.start
                    }
                } else it.selection.start

                onValueChange(
                    it.copy(
                        text = TextFieldDateFormatter.format(it),
                        selection = TextRange(selection)
                    )
                )
            }
        },
        modifier = modifier
            .onKeyEvent { event ->
                if (event.key == Key.Backspace) {
                    isBackspacePressed = true
                    return@onKeyEvent true
                } else {
                    isBackspacePressed = false
                }

                false
            }
    )
}

/**
 * Transformación visual del texto para mostrar el contenido de fecha con estilo personalizado.
 *
 * @param textStyle Estilo de texto aplicado al campo.
 */
class DateFormatVisualTransformation(
    private val textStyle: TextStyle
): VisualTransformation {

    override fun filter(text: AnnotatedString): TransformedText {
        return TransformedText(
            text = buildAnnotatedString {
                for (word in text) {
                    withStyle(
                        textStyle.copy(
                            color = if (word.isDigit() || word == '-') Color.Gray
                            else Color.Gray
                        ).toSpanStyle()
                    ) {
                        append(word)
                    }
                }
            },
            offsetMapping = OffsetMapping.Identity
        )
    }
}

/**
 * Objeto utilitario para dar formato a fechas escritas en campos de texto.
 */
object TextFieldDateFormatter {

    private const val ddMMyyyy = "ddMMyyyy"

    /**
     * Da formato al contenido de un campo de texto, asegurando estructura dd-MM-yyyy.
     *
     * @param fieldValue Valor actual del campo.
     * @param minYear Año mínimo permitido.
     * @param maxYear Año máximo permitido.
     * @return Cadena formateada como fecha.
     */
    fun format(
        fieldValue: TextFieldValue,
        minYear: Int = 2000,
        maxYear: Int = 3000
    ): String {
        val builder = StringBuilder()

        val s = fieldValue.text.replace(
            oldValue = listOf(" ", ".", ",", "-", "d", "M", "y"),
            newValue = ""
        )

        if (s.length != 8) {
            for (i in 0 until 8) {
                builder.append(
                    try {
                        s[i]
                    } catch (e: Exception) {
                        ddMMyyyy[i]
                    }
                )
            }
        } else builder.append(s)

        repeat(3) {
            when (it) {
                0 -> {
                    val day = try {
                        "${builder[0]}${builder[1]}".toInt()
                    } catch (e: Exception) {
                        null
                    }

                    if (day != null) {
                        val dayMax = day
                            .coerceIn(
                                minimumValue = 1,
                                maximumValue = 31,
                            )
                            .toString()

                        builder.replace(
                            0,
                            2,
                            if (dayMax.length == 1) "0$dayMax" else dayMax
                        )
                    }
                }
                1 -> {
                    val month = try {
                        "${builder[2]}${builder[3]}".toInt()
                    } catch (e: Exception) {
                        null
                    }

                    if (month != null) {
                        val monthMax = month
                            .coerceIn(
                                minimumValue = 1,
                                maximumValue = 12,
                            )
                            .toString()

                        builder.replace(
                            2,
                            4,
                            if (monthMax.length == 1) "0$monthMax" else monthMax
                        )
                    }
                }
                2 -> {
                    val year = try {
                        "${builder[4]}${builder[5]}${builder[6]}${builder[7]}".toInt()
                    } catch (e: Exception) {
                        null
                    }

                    if (year != null) {
                        val yearMaxMin = year.coerceIn(
                            minimumValue = minYear,
                            maximumValue = maxYear
                        ).toString()

                        builder.replace(4, 6, yearMaxMin.substring(0, 2))
                        builder.replace(6, 8, yearMaxMin.substring(2, 4))
                    }
                }
            }
        }

        return builder.toString()
            .addStringBefore("-", 2)  // dd-MMyyyy
            .addStringBefore("-", 5)	// dd-MM-yyyy
    }

    /**
     * Verifica si una cadena representa una fecha válida.
     *
     * @param formattedDate Fecha formateada (ej. "01-01-2000").
     * @return `true` si es válida, `false` en caso contrario.
     */
    fun isValid(formattedDate: String): Boolean {
        return formattedDate.takeDigitString().length == 8
    }

    /**
     * Parsea una fecha formateada a tiempo en milisegundos.
     *
     * @param formattedDate Fecha en formato "dd-MM-yyyy".
     * @return Fecha como timestamp en milisegundos.
     */
    fun parse(formattedDate: String): Long {
        val date = "${formattedDate[6]}${formattedDate[7]}${formattedDate[8]}${formattedDate[9]}-"
            .plus("${formattedDate[3]}${formattedDate[4]}-")
            .plus("${formattedDate[0]}${formattedDate[1]}")
            .plus("T00:00")

        return LocalDateTime.parse(date)
            .atZone(ZoneId.of(TimeZone.getDefault().id, ZoneId.SHORT_IDS))
            .toInstant()
            .toEpochMilli()
    }

}

/**
 * Extrae solo los dígitos de una cadena.
 *
 * @receiver Cadena original.
 * @return Cadena que contiene solo dígitos.
 */
fun String.takeDigitString(): String {
    var builder = ""

    forEach { if (it.isDigit()) builder += it }

    return builder
}

/**
 * Inserta una cadena antes de la posición especificada.
 *
 * @receiver Cadena original.
 * @param s Cadena a insertar.
 * @param index Posición donde insertar.
 * @return Nueva cadena con la inserción.
 */
fun String.addStringBefore(s: String, index: Int): String {
    val result = StringBuilder()
    forEachIndexed { i, c ->
        if (i == index) {
            result.append(s)
            result.append(c)
        } else result.append(c)
    }

    return result.toString()
}

/**
 * Reemplaza múltiples valores en la cadena con otro valor.
 *
 * @receiver Cadena original.
 * @param oldValue Lista de valores a reemplazar.
 * @param newValue Valor por el que se reemplazan.
 * @param ignoreCase Ignorar mayúsculas/minúsculas si es `true`.
 * @return Cadena modificada.
 */
fun String.replace(
    oldValue: List<String>,
    newValue: String,
    ignoreCase: Boolean = false
): String {
    var result = this

    oldValue.forEach { s ->
        if (this.contains(s)) {
            result = result.replace(s, newValue, ignoreCase)
        }
    }

    return result
}