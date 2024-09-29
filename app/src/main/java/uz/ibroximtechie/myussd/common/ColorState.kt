package uz.ibroximtechie.myussd.common

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import uz.ibroximtechie.myussd.common.util.LanguageType
import uz.ibroximtechie.myussd.ui.theme.ColorUzTelecom
import uz.ibroximtechie.myussd.ui.theme.ColorUztelecomDark

data class ColorState(
    val primaryColor: Color = ColorUzTelecom,
    val primaryColorDark: Color = ColorUztelecomDark,
    val currentCompany: Int = 1,
    val companyName:String = "UzTelecom",
    val language:LanguageType = LanguageType.UZ

)
