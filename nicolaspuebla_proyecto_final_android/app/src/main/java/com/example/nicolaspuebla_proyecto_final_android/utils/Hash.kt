package com.example.nicolaspuebla_proyecto_final_android.utils

import java.security.MessageDigest

object Hash {

    val md: MessageDigest = MessageDigest.getInstance("SHA2-256")

    fun hashPasswd(passwd: String): String{
        // Obtenemos los bytes de la contraseña.
        val passwdBytes: ByteArray = passwd.toByteArray();
        // Introducimos la contraseña en el objeto MessajeDigest.
        md.update(passwdBytes);
        // Obtenemos la huella.
        val hashedPasswd:ByteArray = md.digest();
        // Devolvemos la huella.
        return String(hashedPasswd);
    }
}