package com.example.nicolaspuebla_proyecto_final_android.utils

import java.security.MessageDigest

/**
 * Objeto empleado para crear un hash de las contraseñas de inicio de sesión y registro.
 */
object Hash {

    val md: MessageDigest = MessageDigest.getInstance("SHA-256")

    /**
     * Función que crea un hash de la contraseña.
     *
     * @param passwd Cadena de la que se generará el hash.
     */
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

    /**
     * Función que convierte bytes a String hexadecimal.
     *
     * @param bytes Bytes que se deben convertir a cadena de texto.
     */
    private fun bytesToHex(bytes: ByteArray): String {
        val hexString = StringBuilder()
        for (byte in bytes) {
            val hex = String.format("%02x", byte)
            hexString.append(hex)
        }
        return hexString.toString()
    }
}