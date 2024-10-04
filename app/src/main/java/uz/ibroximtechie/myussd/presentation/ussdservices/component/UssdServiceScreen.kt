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
import uz.ibroximtechie.myussd.R
import uz.ibroximtechie.myussd.common.CategoryType
import uz.ibroximtechie.myussd.common.ColorState
import uz.ibroximtechie.myussd.common.components.CustomDetailsDialog
import uz.ibroximtechie.myussd.common.components.CustomTabIndicator
import uz.ibroximtechie.myussd.common.components.Header
import uz.ibroximtechie.myussd.common.util.CompanyType
import uz.ibroximtechie.myussd.common.util.LanguageType
import uz.ibroximtechie.myussd.common.util.SharedPref
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


    if (ussdServiceState.showDetailsDialog){
        var title = ""
        var desc = ""
        var code = ""
        when(colorState.language){
            LanguageType.UZ->{
                when(categoryTypeId){
                    CategoryType.INTERNET.typeId->{
                        title = ussdServiceState.internet.title_uz?:""
                        desc = ussdServiceState.internet.desc_uz?:""
                        code = ussdServiceState.internet.kod?:""
                    }
                    CategoryType.MINUTE.typeId->{
                        title = ussdServiceState.minute.title_uz?:""
                        desc = ussdServiceState.minute.desc_uz?:""
                        code = ussdServiceState.minute.kod?:""
                    }
                    CategoryType.SMS.typeId->{
                        title = ussdServiceState.sms.title_uz?:""
                        desc = ussdServiceState.sms.desc_uz?:""
                        code = ussdServiceState.sms.kod?:""
                    }
                    CategoryType.SERVICE.typeId->{
                        title = ussdServiceState.service.title_uz?:""
                        desc = ussdServiceState.service.desc_uz?:""
                        code = ussdServiceState.service.kod?:""
                    }

                }
            }
            LanguageType.RU->{
                when(categoryTypeId){
                    CategoryType.INTERNET.typeId->{
                        title = ussdServiceState.internet.title_ru?:""
                        desc = ussdServiceState.internet.desc_ru?:""
                        code = ussdServiceState.internet.kod?:""
                    }
                    CategoryType.MINUTE.typeId->{
                        title = ussdServiceState.minute.title_ru?:""
                        desc = ussdServiceState.minute.desc_ru?:""
                        code = ussdServiceState.minute.kod?:""
                    }
                    CategoryType.SMS.typeId->{
                        title = ussdServiceState.sms.title_ru?:""
                        desc = ussdServiceState.sms.desc_ru?:""
                        code = ussdServiceState.sms.kod?:""
                    }
                    CategoryType.SERVICE.typeId->{
                        title = ussdServiceState.service.title_ru?:""
                        desc = ussdServiceState.service.desc_ru?:""
                        code = ussdServiceState.service.kod?:""
                    }

                }
            }
            LanguageType.KR->{
                when(categoryTypeId){
                    CategoryType.INTERNET.typeId->{
                        title = ussdServiceState.internet.title_kr?:""
                        desc = ussdServiceState.internet.desc_kr?:""
                        code = ussdServiceState.internet.kod?:""
                    }
                    CategoryType.MINUTE.typeId->{
                        title = ussdServiceState.minute.title_kr?:""
                        desc = ussdServiceState.minute.desc_kr?:""
                        code = ussdServiceState.minute.kod?:""
                    }
                    CategoryType.SMS.typeId->{
                        title = ussdServiceState.sms.title_kr?:""
                        desc = ussdServiceState.sms.desc_kr?:""
                        code = ussdServiceState.sms.kod?:""
                    }
                    CategoryType.SERVICE.typeId->{
                        title = ussdServiceState.service.title_kr?:""
                        desc = ussdServiceState.service.desc_kr?:""
                        code = ussdServiceState.service.kod?:""
                    }

                }
            }
        }


        CustomDetailsDialog(
            title = title,
            desc = desc,
            code = code,
            colorState = colorState
        ) {
            ussdServiceEvent(UssdServiceEvent.DismissDetailsDialog)
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
                                        ussdServiceEvent(UssdServiceEvent.OnInternetSelected(internet))
                                    },
                                colorState = colorState,
                                title = title,
                                price = price,
                                amount = amount
                            )
                        }
                    }
                    CategoryType.MINUTE.typeId ->{
                        items(ussdServiceState.minuteList){ minute->
                            var title = ""
                            var amount = ""
                            when(colorState.language){
                                LanguageType.UZ->{
                                    title = minute.title_uz?:""
                                    amount = minute.amount_uz?:""
                                }
                                LanguageType.KR->{
                                    title = minute.title_kr?:""
                                    amount = minute.amount_kr?:""
                                }
                                LanguageType.RU->{
                                    title = minute.title_ru?:""
                                    amount = minute.amount_ru?:""
                                }
                            }
                            val price = "${minute.price} ${stringResource(id = R.string.som)}"


                            UssdServiceItem(
                                modifier = Modifier
                                    .clickable {
                                        ussdServiceEvent(UssdServiceEvent.OnMinuteSelected(minute))
                                    },
                                colorState = colorState,
                                title = title,
                                price = price,
                                amount = amount
                            )
                        }
                    }
                    CategoryType.SMS.typeId ->{
                        items(ussdServiceState.smsList){ sms->
                            var title = ""
                            var amount = ""
                            when(colorState.language){
                                LanguageType.UZ->{
                                    title = sms.title_uz?:""
                                    amount = sms.amount_uz?:""
                                }
                                LanguageType.KR->{
                                    title = sms.title_kr?:""
                                    amount = sms.amount_kr?:""
                                }
                                LanguageType.RU->{
                                    title = sms.title_ru?:""
                                    amount = sms.amount_ru?:""
                                }
                            }
                            val price = "${sms.price} ${stringResource(id = R.string.som)}"


                            UssdServiceItem(
                                modifier = Modifier
                                    .clickable {
                                        ussdServiceEvent(UssdServiceEvent.OnSmsSelected(sms))
                                    },
                                colorState = colorState,
                                title = title,
                                price = price,
                                amount = amount
                            )
                        }
                    }
                    CategoryType.SERVICE.typeId ->{
                        items(ussdServiceState.serviceList){ service->
                            var title = ""
                            var amount = ""
                            when(colorState.language){
                                LanguageType.UZ->{
                                    title = service.title_uz?:""
                                    amount = service.amount_uz?:""
                                }
                                LanguageType.KR->{
                                    title = service.title_kr?:""
                                    amount = service.amount_kr?:""
                                }
                                LanguageType.RU->{
                                    title = service.title_ru?:""
                                    amount = service.amount_ru?:""
                                }
                            }
                            val price = "${service.price} ${stringResource(id = R.string.som)}"


                            UssdServiceItem(
                                modifier = Modifier
                                    .clickable {
                                        ussdServiceEvent(UssdServiceEvent.OnServiceSelected(service))
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