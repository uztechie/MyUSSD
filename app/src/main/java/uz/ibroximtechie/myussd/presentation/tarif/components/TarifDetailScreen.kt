package uz.ibroximtechie.myussd.presentation.tarif.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.ussd.domain.model.Tarif
import uz.ibroximtechie.myussd.common.ColorState
import uz.ibroximtechie.myussd.common.components.Header
import uz.ibroximtechie.myussd.ui.theme.backgroundColor

@Composable
fun TarifDetailScreen(
    navController: NavController,
    colorState: ColorState,
    tarif: Tarif
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
    ) {
        Header(
            title = tarif.title_uz?:"",
            backgroundColor = colorState.primaryColor) {

        }


    }

}

@Preview
@Composable
fun TarifDetailScreenPre() {
    TarifDetailScreen(
        navController = rememberNavController(),
        colorState = ColorState(),
        tarif = Tarif(summa = 0.0)
    )
}