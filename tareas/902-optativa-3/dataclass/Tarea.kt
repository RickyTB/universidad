data class Tarea(val id: String, val nombre: String, val fechaRegistro: Date, val racha: Int) {
    constructor(id: String, nombre: String) : this(id, nombre, new Date(), 0)
    constructor(id: String, nombre: String, racha: Int) : this(id, nombre, new Date(), racha) 
}

val tarea = Tarea("1", "Tarea de ejemplo", 1)
val (id, nombre, _, racha) = tarea
