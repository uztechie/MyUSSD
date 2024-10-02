package uz.ibroximtechie.myussd.presentation.ussdservices

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import uz.ibroximtechie.myussd.common.CategoryType
import uz.ibroximtechie.myussd.common.util.SharedPref
import uz.ibroximtechie.myussd.data.db.UssdRepository

class UssdServiceViewModel(val repository: UssdRepository):ViewModel() {

    private val _ussdServiceState = MutableStateFlow(UssdServiceState())
    val ussdServiceState = _ussdServiceState.asStateFlow()


    fun onEvent(event: UssdServiceEvent){
        when(event){
            is UssdServiceEvent.OnCategorySelected ->{
                val catPosition = event.categoryPosition
                val catId = _ussdServiceState.value.categories[catPosition].itemId

                when (event.typeId){
                    CategoryType.INTERNET.typeId ->{
                        getInternet(catId)
                    }
                    CategoryType.MINUTE.typeId ->{
                        getMinutes(catId)
                    }
                    else ->{}
                }
            }

            is UssdServiceEvent.OnCategoryTypeSelected ->{
                val typeId = event.typeId
                getCategories(typeId)
            }
        }
    }


    private fun getCategories(typeId:Int){
        viewModelScope.launch {
            repository.getCategories(companyId = SharedPref.companyId, typeId = typeId)
                .collectLatest { categories->
                    _ussdServiceState.update {
                        it.copy(
                            categories = categories
                        )
                    }
                }
        }
    }

    private fun getInternet(catId:Int){
        viewModelScope.launch {
            repository.getInternet(catId = catId)
                .collectLatest { internetList->
                    _ussdServiceState.update {
                        it.copy(
                            internetList = internetList
                        )
                    }
                }
        }
    }
    private fun getMinutes(catId:Int){
        viewModelScope.launch {
            repository.getMinutes(catId = catId)
                .collectLatest { minuteList->
                    _ussdServiceState.update {
                        it.copy(
                            minuteList = minuteList
                        )
                    }
                }
        }
    }


}