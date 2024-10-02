package uz.ibroximtechie.myussd.presentation.ussdservices.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabPosition
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.ussd.domain.model.Internet
import uz.ibroximtechie.myussd.R
import uz.ibroximtechie.myussd.common.CategoryType
import uz.ibroximtechie.myussd.common.ColorState
import uz.ibroximtechie.myussd.common.components.CustomTabIndicator
import uz.ibroximtechie.myussd.common.components.Header
import uz.ibroximtechie.myussd.common.util.CompanyType
import uz.ibroximtechie.myussd.common.util.LanguageType
import uz.ibroximtechie.myussd.common.util.SharedPref
import uz.ibroximtechie.myussd.domain.navigation.Screen
import uz.ibroximtechie.myussd.presentation.tarif.TarifEvent
import uz.ibroximtechie.myussd.presentation.tarif.components.TarifItem
import uz.ibroximtechie.myussd.presentation.ussdservices.UssdServiceEvent
import uz.ibroximtechie.myussd.presentation.ussdservices.UssdServiceState
import uz.ibroximtechie.myussd.ui.theme.backgroundColor

@Composable
fun UssdServiceScreen(
    navController: NavController,
    categoryTypeId: Int,
    colorState: ColorState,
    ussdServiceState: UssdServiceState,
    ussdServiceEvent: (UssdServiceEvent) -> Unit
) {


    val title = when (colorState.currentCompany) {
        CompanyType.MOBIUZ.companyId -> {
            "${CompanyType.MOBIUZ.companyName} ${stringResource(id = R.string.internet)}"
        }

        CompanyType.UZTELECOM.companyId -> {
            "${CompanyType.UZTELECOM.companyName} ${stringResource(id = R.string.internet)}"
        }

        CompanyType.BEELINE.companyId -> {
            "${CompanyType.BEELINE.companyName} ${stringResource(id = R.string.internet)}"
        }

        CompanyType.UCELL.companyId -> {
            "${CompanyType.UCELL.companyName} ${stringResource(id = R.string.internet)}"
        }

        else -> {
            stringResource(id = R.string.internet)
        }
    }


    var selectedTabIndex by remember {
        mutableStateOf(0)
    }

    val pagerState = rememberPagerState(initialPage = 0) {
        ussdServiceState.categories.size
    }

    LaunchedEffect(Unit){
        ussdServiceEvent(UssdServiceEvent.OnCategoryTypeSelected(categoryTypeId))
    }


    LaunchedEffect(selectedTabIndex, ussdServiceState.categories) {
        if (ussdServiceState.categories.isNotEmpty()) {
            ussdServiceEvent(
                UssdServiceEvent.OnCategorySelected(
                    typeId = categoryTypeId,
                    categoryPosition = selectedTabIndex
                )
            )
        }
    }

    LaunchedEffect(selectedTabIndex) {
        pagerState.scrollToPage(selectedTabIndex)
    }

    println("CurrentPAge=${pagerState.currentPage}")
    LaunchedEffect(pagerState.currentPage) {
        selectedTabIndex = pagerState.currentPage
    }

    val indicator = @Composable { tapPositions: List<TabPosition> ->
        CustomTabIndicator(
            tabPosition = tapPositions,
            pagerState = pagerState,
            color = colorState.primaryColorDark
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
    ) {

        Header(title = title, backgroundColor = colorState.primaryColor) {
            navController.popBackStack()
        }


        ScrollableTabRow(
            selectedTabIndex = selectedTabIndex,
            containerColor = colorState.primaryColor,
            contentColor = Color.White,
            indicator = indicator
        ) {
            ussdServiceState.categories.forEachIndexed { index, category ->
                var title = ""
                when (SharedPref.language) {
                    LanguageType.UZ -> {
                        title = category.cat_uz ?: ""
                    }

                    LanguageType.RU -> {
                        title = category.cat_ru ?: ""
                    }

                    LanguageType.KR -> {
                        title = category.cat_kr ?: ""
                    }
                }


                Tab(
                    modifier = Modifier.zIndex(6f),
                    selected = selectedTabIndex == index,
                    onClick = {
                        selectedTabIndex = index
                    },
                    text = {
                        Text(
                            text = title,
                            fontSize = 15.sp
                        )
                    },

                    )
            }
        }

        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxSize(),
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                when(categoryTypeId){
                    CategoryType.INTERNET.typeId->{
                        items(ussdServiceState.internetList){ internet->
                            var title = ""
                            var amount = ""
                            when(colorState.language){
                                LanguageType.UZ->{
                                    title = internet.title_uz?:""
                                    amount = internet.amount_uz?:""
                                }
                                LanguageType.KR->{
                                    title = internet.title_kr?:""
                                    amount = internet.amount_kr?:""
                                }
                                LanguageType.RU->{
                                    title = internet.title_ru?:""
                                    amount = internet.amount_ru?:""
                                }
                            }
                            val price = "${internet.price} ${stringResource(id = R.string.som)}"


                            UssdServiceItem(
                                modifier = Modifier
                                    .clickable {

                                    },
                                colorState = colorState,
                                title = title,
                                price = price,
                                amount = amount
                            )
                        }
                    }
                    CategoryType.MINUTE.typeId ->{
                        items(ussdServiceState.minuteList){ minutes->
                            var title = ""
                            var amount = ""
                            when(colorState.language){
                                LanguageType.UZ->{
                                    title = minutes.title_uz?:""
                                    amount = minutes.amount_uz?:""
                                }
                                LanguageType.KR->{
                                    title = minutes.title_kr?:""
                                    amount = minutes.amount_kr?:""
                                }
                                LanguageType.RU->{
                                    title = minutes.title_ru?:""
                                    amount = minutes.amount_ru?:""
                                }
                            }
                            val price = "${minutes.price} ${stringResource(id = R.string.som)}"


                            UssdServiceItem(
                                modifier = Modifier
                                    .clickable {

                                    },
                                colorState = colorState,
                                title = title,
                                price = price,
                                amount = amount
                            )
                        }
                    }
                }


            }
        }
    }


}


@Preview
@Composable
fun UssdServiceScreenPre() {
    UssdServiceScreen(
        navController = rememberNavController(),
        colorState = ColorState(),
        ussdServiceEvent = {},
        ussdServiceState = UssdServiceState(),
        categoryTypeId = CategoryType.INTERNET.typeId
    )
}