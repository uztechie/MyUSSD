package uz.ibroximtechie.myussd.domain.navigation

import android.net.Uri
import android.os.Bundle
import androidx.navigation.NavType
import com.example.ussd.domain.model.Tarif
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

object TarifNavType {
    val TarifType = object :NavType<Tarif>(
        isNullableAllowed = false
    ){
        override fun get(bundle: Bundle, key: String): Tarif? {
            return Json.decodeFromString(bundle.getString(key) ?: return null)
        }

        override fun parseValue(value: String): Tarif {
            return Json.decodeFromString(Uri.decode(value))
        }

        override fun serializeAsValue(value: Tarif): String {
            val result = Uri.encode(Json.encodeToString(value))
            println("a serializeAsValue=$result")
            return result
        }

        override fun put(bundle: Bundle, key: String, value: Tarif) {
            bundle.putString(key, Json.encodeToString(value))
        }

    }
}