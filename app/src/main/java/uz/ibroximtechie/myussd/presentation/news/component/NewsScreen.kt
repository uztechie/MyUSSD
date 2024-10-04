package uz.ibroximtechie.myussd.presentation.news.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import uz.ibroximtechie.myussd.common.ColorState

@Composable
fun NewsScreen(
    navController: NavController,
    colorState: ColorState
) {

}

@Preview
@Composable
fun NewsScreenPre() {
    NewsScreen(
        navController = rememberNavController(),
        colorState = ColorState()
    )
}

