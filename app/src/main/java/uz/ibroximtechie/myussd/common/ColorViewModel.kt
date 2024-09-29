package uz.ibroximtechie.myussd.common

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import uz.ibroximtechie.myussd.common.util.CompanyType
import uz.ibroximtechie.myussd.common.util.SharedPref
import uz.ibroximtechie.myussd.ui.theme.ColorBeeline
import uz.ibroximtechie.myussd.ui.theme.ColorBeelineDark
import uz.ibroximtechie.myussd.ui.theme.ColorMobiuz
import uz.ibroximtechie.myussd.ui.theme.ColorMobiuzDark
import uz.ibroximtechie.myussd.ui.theme.ColorUcell
import uz.ibroximtechie.myussd.ui.theme.ColorUcellDark
import uz.ibroximtechie.myussd.ui.theme.ColorUzTelecom
import uz.ibroximtechie.myussd.ui.theme.ColorUztelecomDark

class ColorViewModel:ViewModel() {
    private val _state = MutableStateFlow(ColorState())
    val state = _state.asStateFlow()

    init {
        onEvent(ColorEvent.OnCompanyButtonClick(SharedPref.companyId))
    }


    fun onEvent(event: ColorEvent){
        when(event){
            is ColorEvent.OnCompanyButtonClick->{
                SharedPref.companyId = event.companyId
                var primaryColor = ColorUzTelecom
                var primaryColorDark = ColorUztelecomDark
                var companyName = "UzTelecom"
                when (event.companyId){
                    CompanyType.MOBIUZ.companyId ->{
                        primaryColor = ColorMobiuz
                        primaryColorDark = ColorMobiuzDark
                        companyName = "Mobiuz"
                    }

                    CompanyType.UZTELECOM.companyId ->{
                        primaryColor = ColorUzTelecom
                        primaryColorDark = ColorUztelecomDark
                        companyName = "UzTelecom"
                    }

                    CompanyType.BEELINE.companyId ->{
                        primaryColor = ColorBeeline
                        primaryColorDark = ColorBeelineDark
                        companyName = "Beeline"
                    }
                    CompanyType.UCELL.companyId ->{
                        primaryColor = ColorUcell
                        primaryColorDark = ColorUcellDark
                        companyName = "Ucell"
                    }
                }
                _state.update {
                    it.copy(
                        primaryColor = primaryColor,
                        primaryColorDark = primaryColorDark,
                        currentCompany = event.companyId,
                        companyName = companyName,
                        language = SharedPref.language
                    )
                }
            }
        }
    }

}