package uz.ibroximtechie.myussd.presentation.tarif

import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ussd.domain.model.Tarif
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

import uz.ibroximtechie.myussd.common.CategoryType
import uz.ibroximtechie.myussd.common.util.SharedPref
import uz.ibroximtechie.myussd.data.db.UssdRepository
import uz.ibroximtechie.myussd.data.db.UssdRepositoryImpl

class TarifViewModel(val repository: UssdRepository):ViewModel() {

    private val _tarifState = MutableStateFlow(TarifState())

    val tarifState = _tarifState.asStateFlow()




    fun onTarifEvent(event: TarifEvent){
        when(event){
            is TarifEvent.OnCategorySelected -> {
                val catPosition = event.categoryPosition
                val category = _tarifState.value.categories[catPosition]
                getTarifList(category.itemId)
            }
            TarifEvent.OnTypeSelected->{
                getTarifCategories()
            }
        }

    }



    private fun getTarifCategories(){
        viewModelScope.launch {
            repository.getCategories(companyId = SharedPref.companyId, typeId = CategoryType.TARIF.typeId)
                .collectLatest { categories->
                    _tarifState.update {
                        it.copy(
                            categories = categories
                        )
                    }
                }
        }
    }
    private fun getTarifList(catId:Int){
        viewModelScope.launch {
            repository.getTarif(catId).collectLatest { tarifList->
                _tarifState.update {
                    it.copy(
                        tarifList = tarifList
                    )
                }
            }
        }
    }

}