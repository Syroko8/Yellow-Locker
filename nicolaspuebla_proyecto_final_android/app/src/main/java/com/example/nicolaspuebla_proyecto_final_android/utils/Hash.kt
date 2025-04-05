package com.example.nicolaspuebla_proyecto_final_android.utils

import java.security.MessageDigest

object Hash {

    val md: MessageDigest = MessageDigest.getInstance("SHA-256")

    fun hashPasswd(passwd: String): String{
        // Obtenemos los bytes de la contraseña.
        val passwdBytes: ByteArray = passwd.toByteArray();
        // Introducimos la contraseña en el objeto MessajeDigest.
        md.update(passwdBytes);
        // Obtenemos la huella.
        val hashedPasswd:ByteArray = md.digest();
        // Devolvemos la huella.
        return bytesToHex(hashedPasswd);
    }

    // Función para convertir bytes a String hexadecimal
    private fun bytesToHex(bytes: ByteArray): String {
        val hexString = StringBuilder()
        for (byte in bytes) {
            val hex = String.format("%02x", byte)
            hexString.append(hex)
        }
        return hexString.toString()
    }
}