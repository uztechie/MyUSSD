package uz.ibroximtechie.myussd.presentation.home

import androidx.compose.ui.graphics.Color
import com.example.ussd.domain.model.Dealer
import uz.ibroximtechie.myussd.domain.model.Company
import uz.ibroximtechie.myussd.ui.theme.ColorUzTelecom
import uz.ibroximtechie.myussd.ui.theme.ColorUztelecomDark

data class HomeState(
    val company:Company = Company(_id = 0),
    val dealer: Dealer = Dealer()
)
