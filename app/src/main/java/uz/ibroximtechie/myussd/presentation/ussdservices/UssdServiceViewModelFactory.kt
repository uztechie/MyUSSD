package uz.ibroximtechie.myussd.presentation.ussdservices

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import uz.ibroximtechie.myussd.data.db.UssdRepository

class UssdServiceViewModelFactory(val repository: UssdRepository):ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return UssdServiceViewModel(repository) as T
    }
}