

package uz.ibroximtechie.myussd.presentation.tarif.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

import uz.ibroximtechie.myussd.R
import uz.ibroximtechie.myussd.common.ColorState
import uz.ibroximtechie.myussd.common.util.CompanyType
import uz.ibroximtechie.myussd.common.components.CustomTabIndicator
import uz.ibroximtechie.myussd.common.components.Header
import uz.ibroximtechie.myussd.common.util.LanguageType
import uz.ibroximtechie.myussd.common.util.SharedPref
import uz.ibroximtechie.myussd.domain.navigation.Screen
import uz.ibroximtechie.myussd.presentation.tarif.TarifEvent
import uz.ibroximtechie.myussd.presentation.tarif.TarifState
import uz.ibroximtechie.myussd.ui.theme.backgroundColor

@Composable
fun TarifScreen(
    navController: NavController,
    colorState: ColorState,
    tarifState: TarifState,
    tarifEvent: (TarifEvent) -> Unit
) {

    val title = when(colorState.currentCompany){
        CompanyType.MOBIUZ.companyId->{
            "${CompanyType.MOBIUZ.companyName} ${stringResource(id = R.string.tarif)}"
        }
        CompanyType.UZTELECOM.companyId->{
            "${CompanyType.UZTELECOM.companyName} ${stringResource(id = R.string.tarif)}"
        }
        CompanyType.BEELINE.companyId->{
            "${CompanyType.BEELINE.companyName} ${stringResource(id = R.string.tarif)}"
        }
        CompanyType.UCELL.companyId->{
            "${CompanyType.UCELL.companyName} ${stringResource(id = R.string.tarif)}"
        }
        else ->{stringResource(id = R.string.tarif)}
    }
    LaunchedEffect(Unit){
        tarifEvent(TarifEvent.OnTypeSelected)
    }

    var selectedTabIndex by remember {
        mutableStateOf(0)
    }

    val pagerState = rememberPagerState(initialPage = 0){
        tarifState.categories.size
    }


    LaunchedEffect(selectedTabIndex, tarifState.categories){
        if (tarifState.categories.isNotEmpty()){
            tarifEvent(TarifEvent.OnCategorySelected(categoryPosition = selectedTabIndex))
        }
    }

    LaunchedEffect(selectedTabIndex){
        pagerState.scrollToPage(selectedTabIndex)
    }

    println("CurrentPAge=${pagerState.currentPage}")
    LaunchedEffect(pagerState.currentPage){
        selectedTabIndex = pagerState.currentPage
        if (!pagerState.isScrollInProgress){
        }
    }
    
    val indicator = @Composable { tapPositions:List<TabPosition> ->
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
            tarifState.categories.forEachIndexed { index, category ->
                var title = ""
                when(SharedPref.language){
                    LanguageType.UZ->{
                        title = category.cat_uz?:""
                    }
                    LanguageType.RU->{
                        title = category.cat_ru?:""
                    }
                    LanguageType.KR->{
                        title = category.cat_kr?:""
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
            ){
                items(tarifState.tarifList){ tarif->
                    TarifItem(
                        modifier = Modifier
                            .clickable {
                                       navController.navigate(Screen.TarifDetailScreen(
                                           tarif
                                       ))
                            },
                        colorState = colorState,
                        tarif = tarif
                    )
                }
            }
        }


    }

}



@Preview
@Composable
fun TarifScreenPre() {
    TarifScreen(
        navController = rememberNavController(),
        colorState = ColorState(),
        tarifState = TarifState(),
        tarifEvent = {}
    )
}