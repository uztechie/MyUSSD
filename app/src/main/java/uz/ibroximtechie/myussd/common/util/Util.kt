package uz.ibroximtechie.myussd.common.util

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.telecom.PhoneAccountHandle
import android.telecom.TelecomManager
import androidx.core.app.ActivityCompat

object Util {

    fun callUssd(context: Context, code:String?){
        if (code.isNullOrEmpty()) {
            return
        }
        var ussd = code
        if (code.contains("#")){
            ussd = code.replace("#", "")
        }
        val uri = Uri.parse("tel:${ussd}${Uri.encode("#")}")

        println("callUssd code=$ussd,  uri=$uri")
        val intent = Intent(Intent.ACTION_DIAL)
        intent.setData(uri)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        intent.putExtra("com.android.phone.force.slot", true)
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED){
            context.startActivity(intent)
        }
        else{
            ActivityCompat.requestPermissions(context as Activity, arrayOf(Manifest.permission.CALL_PHONE), 1)
        }


//        val telecomManager = context.getSystemService(Context.TELECOM_SERVICE) as TelecomManager
//
//        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED){
//            val phoneAccountHandleList: List<PhoneAccountHandle> = telecomManager.getCallCapablePhoneAccounts()
//
//            if (phoneAccountHandleList != null && phoneAccountHandleList.size > 0 && simSlotPosition == 1) {
//                intent.putExtra("android.telecom.extra.PHONE_ACCOUNT_HANDLE", phoneAccountHandleList[0])
//            } else if (phoneAccountHandleList != null && phoneAccountHandleList.size > 1 && simSlotPosition == 2) {
//                intent.putExtra("android.telecom.extra.PHONE_ACCOUNT_HANDLE", phoneAccountHandleList[1])
//            }



//        }




    }

    fun share(context: Context, data: String?){
        val intent = Intent(Intent.ACTION_SEND)
        intent.setType("text/plain")
        intent.putExtra(Intent.EXTRA_SUBJECT, "Ussd kod")
        intent.putExtra(Intent.EXTRA_TEXT, "$data")
        context.startActivity(Intent.createChooser(intent, "Ussd kod"))
    }
}