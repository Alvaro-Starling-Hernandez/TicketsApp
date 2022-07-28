package com.aliens.ticketsapp.utils

sealed class Screen(val route: String){
    object RegistroRespuesta: Screen("RegistroRespuesta"){
        const val id = "id"
        const val idTicket = "idTicket"
    }
    object ConsultaRespuesta: Screen("ConsultaRespuesta"){
        const val id = "id"
    }
    object RegistroTiempo: Screen("RegistroTiempo"){
        const val id = "id"
        const val idTicket = "idTicket"
    }
    object ConsultaTiempo: Screen("ConsultaTiempo"){
        const val id = "id"
    }
    object RegistroTecnico: Screen("RegistroTecnico"){
        const val id = "id"
    }
    object ConsultaTecnico: Screen("ConsultaTecnico")

    object RegistroCliente: Screen("RegistroCliente"){
        const val id = "id"
    }
    object ConsultaCliente: Screen("ConsultaCliente")

    object ConsultaTicket: Screen("ConsultaTicket")

    object RegistroTicket: Screen("RegistroTicket"){
        const val id = "id"
    }

    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach{ arg ->
                append("/$arg")
            }
        }
    }

    fun withArgsFormat(vararg args: String) : String {
        return buildString {
            append(route)
            args.forEach{ arg ->
                append("/{$arg}")
            }
        }
    }
}
