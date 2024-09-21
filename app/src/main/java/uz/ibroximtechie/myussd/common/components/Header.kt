package uz.ibroximtechie.myussd.common.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Header(
    icon:ImageVector = Icons.Default.ArrowBack,
    title:String,
    backgroundColor:Color,
    onBackPressed:() -> Unit
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .background(backgroundColor)
            .padding(horizontal = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            onClick = onBackPressed
        ) {
            Icon(
                imageVector = icon,
                contentDescription = "Back",
                tint = Color.White
            )
        }

        Text(
            text = title,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            letterSpacing = 1.sp
        )
    }
}

@Preview(showBackground = true)
@Composable
fun HeaderPre() {
    Header(
        title = "Ussd Kodlar",
        backgroundColor = Color.Green,
        onBackPressed = {}
    )
}