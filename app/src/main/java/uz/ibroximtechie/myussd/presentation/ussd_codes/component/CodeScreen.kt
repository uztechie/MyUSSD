package uz.ibroximtechie.myussd.presentation.ussd_codes.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import uz.ibroximtechie.myussd.R
import uz.ibroximtechie.myussd.common.ColorState
import uz.ibroximtechie.myussd.common.components.Header
import uz.ibroximtechie.myussd.common.util.SharedPref
import uz.ibroximtechie.myussd.common.util.Util
import uz.ibroximtechie.myussd.presentation.ussd_codes.UssdEvent
import uz.ibroximtechie.myussd.presentation.ussd_codes.UssdState
import uz.ibroximtechie.myussd.presentation.ussd_codes.UssdViewModel
import uz.ibroximtechie.myussd.ui.theme.backgroundColor

@Composable
fun CodeScreen(
    navController: NavController,
    colorState: ColorState,
    ussdState: UssdState,
    ussdEvent: (UssdEvent) -> Unit
) {

    val context = LocalContext.current

    LaunchedEffect(Unit){
        println("CodeScreen ussdEvent")
        ussdEvent(UssdEvent.getCodes)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
    ) {
        Header(
            title = stringResource(id = R.string.ussd_kodlar),
            backgroundColor = colorState.primaryColor
        ) {
            
        }
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ){
           items(ussdState.codes){
               CodeItemView(
                   modifier = Modifier
                       .clickable {
                                  Util.callUssd(context, it.code)
                       },
                   ussdCode = it,
                   color = colorState.primaryColor
               )
           }
        }


    }

}



@Preview(showBackground = true)
@Composable
fun CodeScreenPre() {
    CodeScreen(
        navController = rememberNavController(),
        colorState = ColorState(),
        ussdState = UssdState(),
        ussdEvent = {}
    )
}