package uz.ibroximtechie.myussd.domain.navigation

import com.example.ussd.domain.model.Tarif
import kotlinx.serialization.Serializable

sealed class Screen {

    @Serializable
    object HomeScreen
    @Serializable
    object CodeScreen
    @Serializable
    object TarifScreen

    @Serializable
    data class TarifDetailScreen(val tarif: Tarif)

    @Serializable
    data class UssdServiceScreen(val categoryTypeId:Int)
}