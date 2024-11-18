package identities

class Persona {
    private var _nombre: String = ""
    private var _apellidos: String = ""
    private var _edad: Int = 0
    private var _genero: String = ""
    private var _nacionalidad: String = ""
    private var _peso: Double = 0.0

    constructor()

    constructor(nombre: String, apellidos: String, edad: Int, genero: String, nacionalidad: String, peso: Double) {
        this._nombre = nombre
        this._apellidos = apellidos
        this._edad = edad
        this._genero = genero
        this._nacionalidad = nacionalidad
        this._peso = peso
    }


    var Nombre: String
        get() = _nombre
        set(value) { _nombre = value }

    var Apellidos: String
        get() = _apellidos
        set(value) { _apellidos = value }

    var Edad: Int
        get() = _edad
        set(value) { _edad = value }

    var Genero: String
        get() = _genero
        set(value) { _genero = value }

    var Nacionalidad: String
        get() = _nacionalidad
        set(value) { _nacionalidad = value }

    var Peso: Double
        get() = _peso
        set(value) { _peso = value }


    val NombreCompleto: String
        get() = "$_nombre $_apellidos"
}
