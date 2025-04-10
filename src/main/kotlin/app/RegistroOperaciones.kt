package es.iesraprog2425.pruebaes.app

import es.iesraprog2425.pruebaes.ui.IEntradaSalida
import es.iesraprog2425.pruebaes.utils.FechaUtils
import es.iesraprog2425.pruebaes.utils.IUtilFicheros
import java.io.File

class RegistroOperaciones(private val io: IEntradaSalida, private val ficheros: IUtilFicheros) {

    fun inicializarDirectorio(ruta: String): Boolean {
        val carpeta = File(ruta)
        if (!ficheros.esDirectorioValido(ruta)) {
            if (carpeta.mkdirs()) {
                io.mostrar("Directorio $ruta creado.")
            } else {
                io.mostrarError("No se pudo crear el directorio $ruta.")
                return false
            }
        }
        return true
    }

    fun mostrarUltimoLog(ruta: String) {
        if (!inicializarDirectorio(ruta)) return

        val archivos = File(ruta).listFiles()?.filter { it.isFile } ?: emptyList()
        if (archivos.isEmpty()) {
            io.mostrar("No existen ficheros de Log")
            return
        }

        val ultimo = archivos.maxByOrNull { it.lastModified() } ?: return
        io.mostrar("Log más reciente: ${ultimo.name}")
        ficheros.leerLineasDeArchivo(ultimo.absolutePath).forEach { io.mostrar(it) }
    }

    fun guardarOperacion(ruta: String, descripcion: String) {
        if (!inicializarDirectorio(ruta)) return
        val nombreArchivo = "log${FechaUtils.obtenerFechaParaNombre()}.txt"
        val rutaCompleta = "$ruta/$nombreArchivo"
        ficheros.escribirLinea(rutaCompleta, descripcion)
        io.mostrar("Operación guardada en: $rutaCompleta")
    }
}