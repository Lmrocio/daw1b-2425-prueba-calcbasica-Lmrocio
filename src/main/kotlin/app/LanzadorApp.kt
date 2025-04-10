package es.iesraprog2425.pruebaes.app

import es.iesraprog2425.pruebaes.model.Operacion
import es.iesraprog2425.pruebaes.model.Operadores
import es.iesraprog2425.pruebaes.ui.IEntradaSalida
import es.iesraprog2425.pruebaes.utils.FechaUtils
import es.iesraprog2425.pruebaes.utils.IUtilFicheros
import java.io.File

class LanzadorApp(
    private val ui: IEntradaSalida,
    private val gestorLogs: RegistroOperaciones
) {
    fun lanzar(args: Array<String>) {
        when (args.size) {
            0 -> gestorLogs.mostrarUltimoLog("./log")
            1 -> gestorLogs.mostrarUltimoLog(args[0])
            4 -> {
                val ruta = args[0]
                val num1 = args[1].toDoubleOrNull()
                val operadorChar = args[2].firstOrNull()
                val num2 = args[3].toDoubleOrNull()

                if (num1 == null || num2 == null || operadorChar == null) {
                    ui.mostrarError("Error en los argumentos: asegúrate de que los números y el operador son válidos.")
                    return
                }

                val operador = Operadores.getOperador(operadorChar)
                if (operador == null) {
                    ui.mostrarError("Operador no válido.")
                    return
                }

                val resultado = when (operador) {
                    Operadores.SUMA -> num1 + num2
                    Operadores.RESTA -> num1 - num2
                    Operadores.MULTIPLICACION -> num1 * num2
                    Operadores.DIVISION -> if (num2 != 0.0) num1 / num2 else null
                }

                val fechaHora = FechaUtils.obtenerFechaFormateada()
                val linea = if (resultado != null) {
                    ui.mostrar("Resultado: $resultado")
                    "$fechaHora - $num1 ${operador.simbolos[0]} $num2 = $resultado"
                } else {
                    ui.mostrarError("Error: división por cero.")
                    "$fechaHora - Error en operación: $num1 ${operador.simbolos[0]} $num2"
                }

                gestorLogs.guardarOperacion(ruta, linea)
            }
            else -> ui.mostrarError("Número de argumentos inválido. Deben ser 0, 1 o 4.")
        }

        ui.mostrar("Presiona ENTER para continuar...")
        readln()
    }
}