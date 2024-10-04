package uz.ibroximtechie.myussd.presentation.tarif.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.ussd.domain.model.Tarif
import uz.ibroximtechie.myussd.R
import uz.ibroximtechie.myussd.common.ColorState
import uz.ibroximtechie.myussd.common.components.CustomDetailsDialog
import uz.ibroximtechie.myussd.common.components.Header
import uz.ibroximtechie.myussd.common.util.LanguageType
import uz.ibroximtechie.myussd.common.util.SharedPref
import uz.ibroximtechie.myussd.common.util.Util
import uz.ibroximtechie.myussd.common.util.Util.findActivity
import uz.ibroximtechie.myussd.ui.theme.backgroundColor
import uz.ibroximtechie.myussd.ui.theme.contentTextSize
import uz.ibroximtechie.myussd.ui.theme.contentTextSizeMedium

@Composable
fun TarifDetailScreen(
    navController: NavController,
    colorState: ColorState,
    tarif: Tarif
) {

    val context = LocalContext.current

    var showDialog by remember {
        mutableStateOf(false)
    }



    var title = ""
    var desc = ""
    var fullDesc = ""
    when(colorState.language){
        LanguageType.UZ->{
            title = tarif.title_uz?:""
            desc = tarif.desc_uz?:""
            fullDesc = tarif.full_desc_uz?:""

        }
        LanguageType.RU->{
            title = tarif.title_ru?:""
            desc = tarif.desc_ru?:""
            fullDesc = tarif.full_desc_ru?:""
        }
        LanguageType.KR->{
            title = tarif.title_kr?:""
            desc = tarif.desc_kr?:""
            fullDesc = tarif.full_desc_kr?:""
        }
    }

    if (showDialog){
        CustomDetailsDialog(
            title = title,
            desc = fullDesc,
            code = tarif.kod,
            colorState = colorState,
            onBackPressed = {
                showDialog = false
            }
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
    ) {
        Header(
            title = title,
            backgroundColor = colorState.primaryColor) {
            navController.popBackStack()
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp)
        ) {
            Text(
                text = desc,
                fontSize = contentTextSizeMedium,
                color = Color.Black,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .verticalScroll(rememberScrollState())
            )
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Button(
                    modifier = Modifier
                        .weight(1f),
                    onClick = {
                              Util.callUssd(context.findActivity(), tarif.kod)
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorState.primaryColor,
                        contentColor = Color.White
                    )
                ) {
                    Text(text = stringResource(R.string.faollashtirish))
                }

                Spacer(modifier = Modifier.size(20.dp))

                TextButton(
                    modifier = Modifier
                        .weight(1f),
                    onClick = {
                              showDialog = true
                    },
                    colors = ButtonDefaults.textButtonColors(
                        contentColor = colorState.primaryColor
                    )
                ) {
                    Text(text = stringResource(R.string.batafsil))
                }
            }
        }


    }

}

@Preview
@Composable
fun TarifDetailScreenPre() {
    TarifDetailScreen(
        navController = rememberNavController(),
        colorState = ColorState(),
        tarif = Tarif(
            summa = 0.0,
            desc_uz = "sasa"
        )
    )
}