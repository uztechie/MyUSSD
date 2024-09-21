package uz.ibroximtechie.myussd.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import uz.ibroximtechie.myussd.data.db.UssdRepository

class HomeViewModelFactory(val repository:UssdRepository):ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return HomeViewModel(repository) as T
    }
}