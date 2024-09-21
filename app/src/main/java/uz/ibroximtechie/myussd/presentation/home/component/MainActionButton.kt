package uz.ibroximtechie.myussd.presentation.home.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uz.ibroximtechie.myussd.R

@Composable
fun MainActionButton(
    modifier: Modifier,
    icon:Painter,
    iconColor: Color,
    text:String,

    ) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .clip(CircleShape)
                .background(iconColor)
                .padding(8.dp)
        ) {
            Image(
                painter = icon,
                contentDescription = null,
                modifier = Modifier
                    .size(30.dp),
                colorFilter = ColorFilter.tint(Color.White)
                )
        }
        Spacer(modifier = Modifier.size(5.dp))
        Text(
            text = text,
            color = Color.Black,
            fontSize = 10.sp,
            textAlign = TextAlign.Center,
        )

    }

}

@Preview(showBackground = true)
@Composable
fun MainActionButtonPre() {
    MainActionButton(
        modifier = Modifier.size(100.dp),
        icon = painterResource(id = R.drawable.ic_panjara),
        iconColor = Color.Red,
        text = "Internet paketlar"
    )
}