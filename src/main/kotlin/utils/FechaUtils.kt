package es.iesraprog2425.pruebaes.utils


import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

object FechaUtils {
    fun obtenerFechaFormateada(): String {
        val fechaHora = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        return fechaHora.format(formatter)
    }

    fun obtenerFechaParaNombre(): String {
        val fechaHora = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss")
        return fechaHora.format(formatter)
    }
}
