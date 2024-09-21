package uz.ibroximtechie.myussd.common

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import uz.ibroximtechie.myussd.data.db.UssdRepository

class ColorViewModelFactory:ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ColorViewModel() as T
    }
}