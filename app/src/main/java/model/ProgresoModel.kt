package model

class ProgresoModel(
    var imagenId: Int,
    var mensajeProgreso: String,
    var porcentaje: Int = 0
) {
    // Método para formatear el progreso
    fun getFormattedProgress(): String {
        return "Progreso: $mensajeProgreso"
    }


    fun updatePorcentaje(nuevoPorcentaje: Int) {
        if (nuevoPorcentaje in 0..100) {
            porcentaje = nuevoPorcentaje
        } else {
            throw IllegalArgumentException("El porcentaje debe estar entre 0 y 100.")
        }
    }


    fun getProgressMessage(): String {
        return when (porcentaje) {
            in 0..25 -> "Inicio: $mensajeProgreso"
            in 26..50 -> "En progreso: $mensajeProgreso"
            in 51..75 -> "Casi allí: $mensajeProgreso"
            in 76..100 -> "¡Completado! $mensajeProgreso"
            else -> "Progreso desconocido"
        }
    }

    fun resetProgress() {
        porcentaje = 0
        mensajeProgreso = "Tu progreso ha sido reiniciado."
    }
}
