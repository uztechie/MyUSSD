package uz.ibroximtechie.myussd.presentation.home.component

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import uz.ibroximtechie.myussd.R

@Composable
fun HomeCompanyButton(
    modifier: Modifier,
    backgroundColor:Color,
    shape: Shape,
    image:Painter,
    isPressed:Boolean = true
) {

    val bgColor = if (isPressed) backgroundColor else Color.Transparent
    val sizeScale by animateFloatAsState(
        targetValue = if (isPressed) 1.2f else 1.0f,
        label = ""
    )

    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = bgColor
        ),
        shape = shape,
    ) {
        Box(modifier = Modifier
            .fillMaxSize(),
            contentAlignment = Alignment.Center
            ){
            Image(
                painter = image,
                contentDescription = "",
                modifier = Modifier
                    .graphicsLayer { 
                        scaleX = sizeScale
                        scaleY = sizeScale
                    }
                    .padding(10.dp),

            )
        }

    }
}

@Preview
@Composable
fun HomeCompanyButtonPreview() {
    HomeCompanyButton(
        modifier = Modifier.size(60.dp),
        backgroundColor = Color.Green,
        shape = RoundedCornerShape(10.dp),
        image = painterResource(id = R.drawable.beeline_stroke)

    )
}