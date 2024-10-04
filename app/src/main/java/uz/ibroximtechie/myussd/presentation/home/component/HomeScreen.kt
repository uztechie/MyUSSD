package uz.ibroximtechie.myussd.presentation.home.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CreditCard
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.SimCard
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Language
import androidx.compose.material.icons.outlined.StarOutline
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.launch
import uz.ibroximtechie.myussd.R
import uz.ibroximtechie.myussd.common.CategoryType
import uz.ibroximtechie.myussd.common.ColorEvent
import uz.ibroximtechie.myussd.common.ColorState
import uz.ibroximtechie.myussd.common.Constants
import uz.ibroximtechie.myussd.common.util.CompanyType
import uz.ibroximtechie.myussd.common.util.Util
import uz.ibroximtechie.myussd.common.util.Util.findActivity
import uz.ibroximtechie.myussd.domain.navigation.Screen
import uz.ibroximtechie.myussd.presentation.home.HomeEvent
import uz.ibroximtechie.myussd.presentation.home.HomeState
import uz.ibroximtechie.myussd.ui.theme.ColorBeelineDark
import uz.ibroximtechie.myussd.ui.theme.ColorMobiuzDark
import uz.ibroximtechie.myussd.ui.theme.ColorUcellDark
import uz.ibroximtechie.myussd.ui.theme.ColorUztelecomDark
import uz.ibroximtechie.myussd.ui.theme.backgroundColor
import uz.ibroximtechie.myussd.ui.theme.dividerColor
import uz.ibroximtechie.myussd.ui.theme.mainDividerHeight
import uz.ibroximtechie.myussd.ui.theme.mainDividerSpacer

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController,
    colorState: ColorState,
    homeState: HomeState,
    colorEvent: (ColorEvent) -> Unit,
    homeEvent: (HomeEvent) -> Unit
) {

    val context = LocalContext.current

    var selectedIndex by remember {
        mutableStateOf(0)
    }
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val score = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerContent = {
            ModalDrawerSheet(
                drawerContainerColor = Color.White
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(0.8f)
                ) {
                    Spacer(modifier = Modifier.size(20.dp))
                    drawerItems().forEachIndexed { index, navigationItem ->
                        NavigationDrawerItem(
                            colors = NavigationDrawerItemDefaults.colors(
                                selectedTextColor = Color.White,
                                unselectedTextColor = Color.Black,
                                selectedIconColor = Color.White,
                                unselectedIconColor = Color.Black,
                                unselectedContainerColor = Color.Transparent,
                                selectedContainerColor = colorState.primaryColor.copy(0.5f)
                            ),
                            label = {
                                Text(text = navigationItem.title)
                            },
                            selected = index == selectedIndex,
                            onClick = {
                                selectedIndex = index
                                score.launch {
                                    drawerState.close()
                                }
                            },
                            icon = {
                                Icon(
                                    imageVector = if (selectedIndex == index)
                                        navigationItem.selectedIcon
                                    else
                                        navigationItem.unSelectedIcon,
                                    contentDescription = navigationItem.title
                                )
                            },

                            )

                    }
                }



            }
        },
        drawerState = drawerState,
        gesturesEnabled = true
    ) {
        Scaffold(
//            topBar = {
//                TopAppBar(
//                    colors = TopAppBarDefaults.mediumTopAppBarColors(
//                        containerColor = colorState.primaryColor,
//                        actionIconContentColor = Color.White,
//                        titleContentColor = Color.White,
//                        navigationIconContentColor = Color.White,
//                    ),
//                    title = { Text(text = "Salom") },
//                    navigationIcon = {
//                        IconButton(
//                            onClick = {
//                                score.launch {
//                                    drawerState.apply {
//                                        if (isClosed) open() else close()
//                                    }
//                                }
//                            }
//                        ) {
//                            Icon(
//                                imageVector = Icons.Filled.Menu,
//                                contentDescription = "Menu"
//                            )
//                        }
//                    }
//                )
//            }

        ) { padding ->
            Column(
                Modifier
                    .padding(padding)
                    .background(backgroundColor)
                    .fillMaxSize()
            ) {

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp)
                        .background(colorState.primaryColor)
                        .padding(horizontal = 10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(
                        colors = IconButtonDefaults.iconButtonColors(
                            contentColor = Color.White
                        ),
                        onClick = {
                            score.launch {
                                drawerState.apply {
                                    if (isClosed) open() else close()
                                }
                            }
                        },
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Menu,
                            contentDescription = "Menu",
//                                tint = Color.White
                        )
                    }

                    Spacer(modifier = Modifier
                        .weight(1f))

                    IconButton(
                        onClick = { },
                    ) {
                        Icon(
                            modifier = Modifier.size(25.dp),
                            imageVector = Icons.Default.SimCard,
                            contentDescription = "",
                            tint = Color.White
                        )
                    }
                    IconButton(
                        onClick = { },
                    ) {
                        Icon(
                            modifier = Modifier.size(25.dp),
                            imageVector = Icons.Default.CreditCard,
                            contentDescription = "",
                            tint = Color.White
                        )
                    }
                    IconButton(
                        onClick = {
                                  Util.openWebPage(
                                      context,
                                      homeState.dealer.user_telegram
                                  )
                        },
                    ) {
                        Icon(
                            modifier = Modifier.size(25.dp),
                            painter = painterResource(id = R.drawable.telegram),
                            contentDescription = "",
                            tint = Color.White
                        )
                    }
                    IconButton(
                        onClick = {
                                  Util.share(
                                      context,
                                      Constants.APP_URL
                                  )
                        },
                    ) {
                        Icon(
                            modifier = Modifier.size(25.dp),
                            imageVector = Icons.Default.Share,
                            contentDescription = "",
                            tint = Color.White
                        )
                    }
                    IconButton(
                        onClick = { },
                    ) {
                        Icon(
                            modifier = Modifier.size(25.dp),
                            imageVector = Icons.Default.Notifications,
                            contentDescription = "",
                            tint = Color.White
                        )
                    }

                }

                Column(modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                ) {

                    Box(
                        contentAlignment = Alignment.BottomCenter,
                    ) {
                        BottomArc(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(150.dp),
                            color = colorState.primaryColor
                        )
                        Text(
                            text = colorState.companyName,
                            modifier = Modifier.padding(bottom = 40.dp),
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                            letterSpacing = 1.sp
                        )

                    }


                    Column(modifier = Modifier
                        .fillMaxWidth()
                        .padding(15.dp)
                        .weight(1f),
                        verticalArrangement = Arrangement.Center
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            MainActionButton(
                                modifier = Modifier
                                    .weight(1f)
                                    .clickable {
                                        navController.navigate(Screen.CodeScreen)
                                    },
                                icon = painterResource(id = R.drawable.ic_panjara),
                                iconColor = colorState.primaryColor,
                                text = stringResource(id = R.string.qisqa_buyruqlar)
                            )
                            Box (modifier = Modifier
                                .height(mainDividerHeight)
                                .width(0.5.dp)
                                .background(dividerColor)
                            )
                            MainActionButton(
                                modifier = Modifier
                                    .weight(1f)
                                    .clickable {
                                        navController.navigate(Screen.TarifScreen)
                                    },
                                icon = painterResource(id = R.drawable.ic_card),
                                iconColor = colorState.primaryColor,
                                text = stringResource(id = R.string.tarif_rejalari)
                            )
                            Box (modifier = Modifier
                                .height(mainDividerHeight)
                                .width(0.5.dp)
                                .background(dividerColor)
                            )
                            MainActionButton(
                                modifier = Modifier
                                    .weight(1f)
                                    .clickable {
                                        navController
                                            .navigate(Screen.UssdServiceScreen(CategoryType.INTERNET.typeId))
                                    },
                                icon = painterResource(id = R.drawable.ic_world),
                                iconColor = colorState.primaryColor,
                                text = stringResource(id = R.string.internet_paketlar)
                            )


                        }
                        Spacer(modifier = Modifier.size(mainDividerSpacer))
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(0.5.dp)
                                .background(dividerColor)
                        )
                        Spacer(modifier = Modifier.size(mainDividerSpacer))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            MainActionButton(
                                modifier = Modifier
                                    .weight(1f)
                                    .clickable {
                                        navController
                                            .navigate(Screen.UssdServiceScreen(CategoryType.MINUTE.typeId))
                                    },
                                icon = painterResource(id = R.drawable.ic_access_time_black_24dp),
                                iconColor = colorState.primaryColor,
                                text = stringResource(id = R.string.daqiqalar)
                            )
                            Box (modifier = Modifier
                                .height(mainDividerHeight)
                                .width(0.5.dp)
                                .background(dividerColor)
                            )
                            MainActionButton(
                                modifier = Modifier
                                    .weight(1f)
                                    .clickable {
                                        navController
                                            .navigate(Screen.UssdServiceScreen(CategoryType.SMS.typeId))
                                    },
                                icon = painterResource(id = R.drawable.ic_message2),
                                iconColor = colorState.primaryColor,
                                text = stringResource(id = R.string.sms_toplamlar)
                            )
                            Box (modifier = Modifier
                                .height(mainDividerHeight)
                                .width(0.5.dp)
                                .background(dividerColor)
                            )
                            MainActionButton(
                                modifier = Modifier
                                    .weight(1f)
                                    .clickable {
                                        navController
                                            .navigate(Screen.UssdServiceScreen(CategoryType.SERVICE.typeId))
                                    },
                                icon = painterResource(id = R.drawable.ic_drawer),
                                iconColor = colorState.primaryColor,
                                text = stringResource(id = R.string.xizlatlar)
                            )

                        }
                        Spacer(modifier = Modifier.size(mainDividerSpacer))
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(0.5.dp)
                                .background(dividerColor)
                        )
                        Spacer(modifier = Modifier.size(mainDividerSpacer))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            MainActionButton(
                                modifier = Modifier
                                    .weight(1f)
                                    .clickable {
                                               Util.openWebPage(
                                                   context = context,
                                                   url = homeState.company.cabinet
                                               )
                                    },
                                icon = painterResource(id = R.drawable.ic_security),
                                iconColor = colorState.primaryColor,
                                text = stringResource(id = R.string.kabinet)
                            )
                            Box (modifier = Modifier
                                .height(mainDividerHeight)
                                .width(0.5.dp)
                                .background(dividerColor)
                            )
                            MainActionButton(
                                modifier = Modifier
                                    .weight(1f)
                                    .clickable {
                                               Util.callUssd(
                                                   context.findActivity(),
                                                   homeState.company.u_balans
                                               )
                                    },
                                icon = painterResource(id = R.drawable.baseline_attach_money_24),
                                iconColor = colorState.primaryColor,
                                text = stringResource(id = R.string.balans)
                            )
                            Box (modifier = Modifier
                                .height(mainDividerHeight)
                                .width(0.5.dp)
                                .background(dividerColor)
                            )
                            MainActionButton(
                                modifier = Modifier
                                    .weight(1f)
                                    .clickable {
                                        Util.callUssd(
                                            context.findActivity(),
                                            homeState.company.callcenter
                                        )
                                    },
                                icon = painterResource(id = R.drawable.operator_white),
                                iconColor = colorState.primaryColor,
                                text = stringResource(id = R.string.operator)
                            )

                        }
                    }

                }




                BottomMenu(
                    modifier = Modifier
                        .padding(horizontal = 10.dp)
                        .fillMaxWidth(),
                    colorState = colorState,
                    colorEvent = colorEvent,
                    homeEvent = homeEvent

                )

            }
        }
    }
}

@Composable
fun BottomMenu(
    modifier: Modifier,
    colorState: ColorState,
    colorEvent: (ColorEvent) -> Unit,
    homeEvent: (HomeEvent) -> Unit
) {

    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(80.dp),
        colors = CardDefaults.cardColors(
            containerColor = colorState.primaryColor
        ),
        shape = RoundedCornerShape(10.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxSize()
        ) {


            HomeCompanyButton(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(0.dp)
                    .weight(1f)
                    .clickable {
                        colorEvent(ColorEvent.OnCompanyButtonClick(CompanyType.MOBIUZ.companyId))
                        homeEvent(HomeEvent.OnCompanyButtonClick(CompanyType.MOBIUZ.companyId))
                    },
                backgroundColor = ColorMobiuzDark,
                shape = RoundedCornerShape(10.dp),
                image = painterResource(id = R.drawable.mobiuz_storke),
                isPressed = colorState.currentCompany == CompanyType.MOBIUZ.companyId
            )
            HomeCompanyButton(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(0.dp)
                    .weight(1f)
                    .clickable {
                        colorEvent(ColorEvent.OnCompanyButtonClick(CompanyType.UZTELECOM.companyId))
                        homeEvent(HomeEvent.OnCompanyButtonClick(CompanyType.UZTELECOM.companyId))
                    },
                backgroundColor = ColorUztelecomDark,
                shape = RoundedCornerShape(10.dp),
                image = painterResource(id = R.drawable.uzmobile_storke),
                isPressed = colorState.currentCompany == CompanyType.UZTELECOM.companyId
            )
            HomeCompanyButton(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(0.dp)
                    .weight(1f)
                    .clickable {
                        colorEvent(ColorEvent.OnCompanyButtonClick(CompanyType.BEELINE.companyId))
                        homeEvent(HomeEvent.OnCompanyButtonClick(CompanyType.BEELINE.companyId))
                    },
                backgroundColor = ColorBeelineDark,
                shape = RoundedCornerShape(10.dp),
                image = painterResource(id = R.drawable.beeline_stroke),
                isPressed = colorState.currentCompany == CompanyType.BEELINE.companyId
            )
            HomeCompanyButton(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(0.dp)
                    .weight(1f)
                    .clickable {
                        colorEvent(ColorEvent.OnCompanyButtonClick(CompanyType.UCELL.companyId))
                        homeEvent(HomeEvent.OnCompanyButtonClick(CompanyType.UCELL.companyId))
                    },
                backgroundColor = ColorUcellDark,
                shape = RoundedCornerShape(10.dp),
                image = painterResource(id = R.drawable.ucell_stroke),
                isPressed = colorState.currentCompany == CompanyType.UCELL.companyId
            )


        }
    }
}


@Composable
fun drawerItems(): List<NavigationItem> {
    val list = listOf<NavigationItem>(
        NavigationItem(
            title = stringResource(id = R.string.tilni_o_zgartirish),
            selectedIcon = Icons.Filled.Language,
            unSelectedIcon = Icons.Outlined.Language
        ),
        NavigationItem(
            title = stringResource(id = R.string.dasturni_baholash),
            selectedIcon = Icons.Filled.Star,
            unSelectedIcon = Icons.Outlined.StarOutline
        ),
    )
    return list
}

@Preview(
)
@Composable
fun HomeScreenPreview(

) {
    HomeScreen(
        navController = rememberNavController(),
        colorState = ColorState(),
        homeState = HomeState(),
        colorEvent = {},
        homeEvent = {}
    )
//    BottomMenu()
}