package uz.ibroximtechie.myussd.presentation.ussd_codes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import uz.ibroximtechie.myussd.common.util.SharedPref
import uz.ibroximtechie.myussd.data.db.UssdRepository
import uz.ibroximtechie.myussd.presentation.home.HomeState

class UssdViewModel(val repository: UssdRepository):ViewModel() {
    private val _state = MutableStateFlow(UssdState())
    val state = _state.asStateFlow()

    init {
    }


    fun onEvent(event: UssdEvent){
        when(event){
            UssdEvent.getCodes->{
                getUSSDCodes()
            }
        }
    }


    private fun getUSSDCodes(){
        viewModelScope.launch {
            repository.getCodes(SharedPref.companyId).collectLatest { codeList->
                println("getUSSDCodes list=$codeList")
                _state.update {
                    it.copy(
                        codes = codeList
                    )
                }
            }
        }
    }




}