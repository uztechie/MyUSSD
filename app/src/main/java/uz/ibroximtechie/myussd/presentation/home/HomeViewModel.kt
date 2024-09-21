package uz.ibroximtechie.myussd.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import uz.ibroximtechie.myussd.data.db.UssdRepository

class HomeViewModel(val repository: UssdRepository):ViewModel() {
    private val _state = MutableStateFlow(HomeState())
    val state = _state.asStateFlow()


    fun onEvent(event: HomeEvent){
        when(event){
            is HomeEvent.OnCompanyButtonClick->{
                repository.getCompanies()
            }
        }
    }

}