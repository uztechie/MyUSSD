package uz.ibroximtechie.myussd.presentation.tarif

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import uz.ibroximtechie.myussd.data.db.UssdRepository

class TarifViewModelFactory(val repository: UssdRepository):ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return TarifViewModel(repository) as T
    }
}