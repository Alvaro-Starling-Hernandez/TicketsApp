package com.aliens.ticketsapp.utils

sealed class Screen(val route: String){
    object RegistroRespuesta: Screen("RegistroRespuesta"){
        val id = "id"
    }
    object ConsultaRespuesta: Screen("ConsultaRespuesta")
    object RegistroTiempo: Screen("RegistroTiempo"){
        val id = "id"
    }
    object ConsultaTiempo: Screen("ConsultaTiempo")
    object RegistroTecnico: Screen("RegistroTecnico"){
        val id = "id"
    }
    object ConsultaTecnico: Screen("ConsultaTecnico")

    object RegistroCliente: Screen("RegistroCliente"){
        val id = "id"
    }
    object ConsultaCliente: Screen("ConsultaCliente")

    object RegistroTicket: Screen("RegistroTicket"){
        val id = "id"
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
