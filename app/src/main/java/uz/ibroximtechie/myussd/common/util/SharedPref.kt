package uz.ibroximtechie.myussd.common.util

import com.chibatching.kotpref.KotprefModel
import com.chibatching.kotpref.enumpref.enumValuePref

object SharedPref:KotprefModel() {

    var companyId by intPref(1)
    var language by enumValuePref(LanguageType.UZ)
}