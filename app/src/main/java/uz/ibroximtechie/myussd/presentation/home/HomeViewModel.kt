package uz.ibroximtechie.myussd.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import uz.ibroximtechie.myussd.common.util.SharedPref
import uz.ibroximtechie.myussd.data.db.UssdRepository

class HomeViewModel(val repository: UssdRepository):ViewModel() {
    private val _state = MutableStateFlow(HomeState())
    val state = _state.asStateFlow()

    init {
        onEvent(HomeEvent.OnCompanyButtonClick(SharedPref.companyId))
        getDealer()
    }

    fun onEvent(event: HomeEvent){
        when(event){
            is HomeEvent.OnCompanyButtonClick->{
                getCompany(event.companyId)
            }
        }
    }

    fun getCompany(companyId:Int){
        viewModelScope.launch {
            repository.getCompany(companyId).collectLatest { company ->
                company?.let { nonNullCompany->
                    _state.update {
                        it.copy(
                            company = nonNullCompany
                        )
                    }
                }
            }
        }
    }

    fun getDealer(){
        viewModelScope.launch {
            repository.getDealer().collectLatest { dealer ->
                dealer?.let { nonNullDealer->
                    _state.update {
                        it.copy(
                            dealer = nonNullDealer
                        )
                    }
                }
            }
        }
    }

}