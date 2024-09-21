package uz.ibroximtechie.myussd.presentation.ussd_codes

sealed interface UssdEvent {
    object getCodes : UssdEvent
}