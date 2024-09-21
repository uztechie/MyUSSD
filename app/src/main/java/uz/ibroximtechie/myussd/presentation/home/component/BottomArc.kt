package uz.ibroximtechie.myussd.presentation.home.component

import androidx.annotation.FloatRange
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.DrawScope.Companion.DefaultBlendMode
import androidx.compose.ui.graphics.drawscope.DrawStyle
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.drawscope.withTransform
import androidx.compose.ui.text.drawText
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun BottomArc(
    modifier: Modifier,
    color:Color
) {


    Canvas(
        modifier = modifier
    ) {

        drawRect(
            color = color,
            size = Size(width = size.width, height = size.height/2)
        )


        drawArc(
            color,
            0f,
            180f,
            useCenter = true,
            size = Size(size.width, size.height),
            topLeft = Offset(x = 0f, y = 0f)
        )
    }
}



@Preview
@Composable
fun BottomArcPreview() {
    BottomArc(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp),
        color = Color.Green
    )
}