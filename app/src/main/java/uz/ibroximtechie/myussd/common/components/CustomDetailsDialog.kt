package uz.ibroximtechie.myussd.common.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.ussd.domain.model.Tarif
import uz.ibroximtechie.myussd.R
import uz.ibroximtechie.myussd.common.ColorState
import uz.ibroximtechie.myussd.common.util.Util
import uz.ibroximtechie.myussd.ui.theme.contentTextSizeMedium
import uz.ibroximtechie.myussd.ui.theme.titleMedium

@Composable
fun CustomDetailsDialog(
    title:String,
    desc:String,
    code:String?,
    colorState: ColorState,
    onBackPressed: () -> Unit
) {
    Dialog(
        onDismissRequest = onBackPressed,
    ) {
        val data = "${title}\n${code}#"
        val context = LocalContext.current
        Card(
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.9f)
                    .padding(15.dp)
            ) {

                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = title,
                        color = Color.Black,
                        fontSize = titleMedium,
                        modifier = Modifier
                            .weight(1f)
                    )
                    Spacer(modifier = Modifier.size(15.dp))
                    IconButton(
                        onClick = {
                                  Util.share(context, data)
                        },
                        colors = IconButtonDefaults.iconButtonColors(
                            contentColor = Color.Black
                        ),
                        modifier = Modifier.size(20.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Share,
                            contentDescription = "Share"
                        )
                    }
                    Spacer(modifier = Modifier.size(15.dp))
                    IconButton(
                        onClick = onBackPressed,
                        colors = IconButtonDefaults.iconButtonColors(
                            contentColor = Color.Black
                        ),
                        modifier = Modifier.size(20.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Share"
                        )
                    }

                }

                Box(modifier = Modifier
                    .padding(vertical = 15.dp)
                    .fillMaxWidth()
                    .height(2.dp)
                    .background(colorState.primaryColor)
                )

                Text(
                    text = desc,
                    color = Color.Black,
                    fontSize = contentTextSizeMedium,
                    modifier = Modifier
                        .weight(1f)
                        .verticalScroll(rememberScrollState())
                )


                Button(
                    modifier = Modifier
                        .fillMaxWidth(),
                    onClick = {
                        Util.callUssd(context, code)
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorState.primaryColor,
                        contentColor = Color.White
                    )
                ) {
                    Text(text = stringResource(R.string.faollashtirish))
                }

            }

        }
    }

}

@Preview
@Composable
fun CustomDetailsDialogPre() {
    CustomDetailsDialog(
        title = "Title",
        desc = "Desc",
        code = "122121",
        colorState = ColorState(),
        onBackPressed = {}
    )
}