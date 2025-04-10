package es.iesraprog2425.pruebaes.utils

interface IUtilFicheros {
    fun leerLineasDeArchivo(ruta: String): List<String>
    fun escribirLinea(ruta: String, texto: String): Boolean
    fun existeArchivo(ruta: String): Boolean
    fun esDirectorioValido(ruta: String): Boolean
}