package uz.ibroximtechie.myussd.presentation.tarif

import com.example.ussd.domain.model.Category
import com.example.ussd.domain.model.Tarif

data class TarifState(
    val tarifList:List<Tarif> = emptyList(),
    val categories:List<Category> = emptyList()
)
