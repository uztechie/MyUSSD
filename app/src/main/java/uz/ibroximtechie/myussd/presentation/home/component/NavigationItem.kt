package uz.ibroximtechie.myussd.presentation.home.component

import android.graphics.drawable.Icon
import androidx.compose.ui.graphics.vector.ImageVector

data class NavigationItem(
    val title:String,
    val selectedIcon:ImageVector,
    val unSelectedIcon:ImageVector,
)
