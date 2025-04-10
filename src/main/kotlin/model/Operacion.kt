package es.iesraprog2425.pruebaes.model

import es.iesraprog2425.pruebaes.model.Operadores

data class Operacion(val num1: Double, val operador: Operadores, val num2: Double) {
    fun ejecutar(): Double = when (operador) {
        Operadores.SUMA -> num1 + num2
        Operadores.RESTA -> num1 - num2
        Operadores.MULTIPLICACION -> num1 * num2
        Operadores.DIVISION -> num1 / num2
    }
}
