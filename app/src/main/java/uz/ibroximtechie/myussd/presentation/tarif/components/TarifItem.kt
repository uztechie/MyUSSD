package uz.ibroximtechie.myussd.presentation.tarif.components

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.ImageLoader
import coil.compose.AsyncImage
import com.example.ussd.domain.model.Tarif
import uz.ibroximtechie.myussd.R
import uz.ibroximtechie.myussd.common.ColorState
import uz.ibroximtechie.myussd.common.Constants
import uz.ibroximtechie.myussd.common.util.LanguageType
import uz.ibroximtechie.myussd.common.util.SharedPref
import uz.ibroximtechie.myussd.ui.theme.contentTextSize
import uz.ibroximtechie.myussd.ui.theme.titleLarge

@Composable
fun TarifItem(
    modifier: Modifier,
    colorState: ColorState,
    tarif: Tarif
){

    var title = ""
    var desc = ""
    val image = "${Constants.BASE_URL}images/${tarif.image}"
    println("TarifIMage=$image")

    when(SharedPref.language){
        LanguageType.UZ->{
            title = tarif.title_uz?:""
            desc = tarif.desc_uz?:""
        }
        LanguageType.RU->{
            title = tarif.title_ru?:""
            desc = tarif.desc_ru?:""
        }
        LanguageType.KR->{
            title = tarif.title_kr?:""
            desc = tarif.desc_kr?:""
        }
    }

    Card(
        modifier = modifier
            .padding(vertical = 7.dp, horizontal = 14.dp),
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
        Column(
            modifier = Modifier
                .animateContentSize()
                .fillMaxWidth()
                .padding(15.dp)
        ) {
            
            Row (
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ){
                AsyncImage(
                    model = image,
                    contentDescription = "",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .clip(RoundedCornerShape(10.dp))
                        .size(80.dp),
                    error = painterResource(id = R.drawable.no_image)
                )

                Spacer(modifier = Modifier.size(15.dp))

                Text(
                    text = title,
                    color = colorState.primaryColor,
                    fontSize = titleLarge,
                    fontWeight = FontWeight.Bold,
                )
            }

            Box(
                modifier = Modifier
                    .padding(vertical = 10.dp)
                    .fillMaxWidth()
                    .height(2.dp)
                    .background(colorState.primaryColor)
            )

            Text(
                text = desc,
                color = Color.Black,
                fontSize = contentTextSize,
                maxLines = 4,
                overflow = TextOverflow.Ellipsis
            )

        }

    }
}

@Preview
@Composable
fun TarifItemPre() {
    TarifItem(
        modifier = Modifier,
        colorState = ColorState(),
        tarif = Tarif(summa = 0.0)
    )
}

