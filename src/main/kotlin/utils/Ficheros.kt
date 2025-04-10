package es.iesraprog2425.pruebaes.utils

import es.iesraprog2425.pruebaes.ui.Consola
import java.io.File
import java.io.IOException

class Ficheros(consola: Consola) : IUtilFicheros {

    override fun leerLineasDeArchivo(ruta: String): List<String> {
        val archivo = File(ruta)
        if (!archivo.exists()) {
            throw IOException("Archivo no encontrado: $ruta")
        }
        return archivo.readLines()
    }

    override fun escribirLinea(ruta: String, texto: String): Boolean {
        return try {
            val archivo = File(ruta)
            archivo.appendText("$texto\n")
            true
        } catch (ex: IOException) {
            println("Error escribiendo en archivo: ${ex.message}")
            false
        }
    }

    override fun existeArchivo(ruta: String): Boolean {
        return File(ruta).isFile
    }

    override fun esDirectorioValido(ruta: String): Boolean {
        return File(ruta).isDirectory
    }
}