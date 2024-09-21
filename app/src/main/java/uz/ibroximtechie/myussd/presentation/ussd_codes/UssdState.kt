package uz.ibroximtechie.myussd.presentation.ussd_codes

import uz.ibroximtechie.myussd.domain.model.USSDCode

data class UssdState(
    val codes:List<USSDCode> = emptyList()
)
