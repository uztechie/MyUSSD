package uz.ibroximtechie.myussd.presentation.ussd_codes.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uz.ibroximtechie.myussd.common.util.LanguageType
import uz.ibroximtechie.myussd.common.util.SharedPref
import uz.ibroximtechie.myussd.domain.model.USSDCode

@Composable
fun CodeItemView(
    modifier: Modifier = Modifier,
    ussdCode: USSDCode,
    color: Color
) {


    var title = ussdCode.title_uz
    var code = if (ussdCode.code == null){
        ""
    }
    else{
        "${ussdCode.code}#"
    }
    LaunchedEffect(Unit){
        title = when(SharedPref.language){
            LanguageType.UZ->{
                ussdCode.title_uz
            }
            LanguageType.RU->{
                ussdCode.title_ru
            }
            LanguageType.KR->{
                ussdCode.title_kr
            }

        }
    }



    Card(
        shape = RoundedCornerShape(10.dp),
        modifier = modifier
            .padding(5.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        )
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(horizontal = 15.dp, vertical = 10.dp)
        ) {
            Text(
                text = code,
                color = Color.Black,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
            Box(modifier = Modifier
                .padding(vertical = 10.dp)
                .fillMaxWidth()
                .height(0.5.dp)
                .background(color)
            )
            Text(
                text = title?:"",
                color = Color.Black,
                fontSize = 16.sp,
            )

        }
    }
}

@Preview(showBackground = true)
@Composable
fun CodeItemPre() {
    CodeItemView(
        ussdCode = USSDCode(
            title_uz = "KOdlar",
            code = "123456456",
        ),
        color = Color.Red
    )
}