package uz.ibroximtechie.myussd.presentation.ussd_codes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import uz.ibroximtechie.myussd.data.db.UssdRepository

class UssdViewModelFactory(val repository:UssdRepository):ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return UssdViewModel(repository) as T
    }
}