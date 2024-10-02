package uz.ibroximtechie.myussd

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import androidx.room.Room
import com.example.ussd.domain.model.Tarif
import uz.ibroximtechie.myussd.common.CategoryType
import uz.ibroximtechie.myussd.common.ColorViewModel
import uz.ibroximtechie.myussd.common.ColorViewModelFactory
import uz.ibroximtechie.myussd.data.db.UssdDatabase
import uz.ibroximtechie.myussd.data.db.UssdRepositoryImpl
import uz.ibroximtechie.myussd.domain.navigation.Screen
import uz.ibroximtechie.myussd.domain.navigation.TarifNavType
import uz.ibroximtechie.myussd.presentation.home.HomeViewModel
import uz.ibroximtechie.myussd.presentation.home.HomeViewModelFactory
import uz.ibroximtechie.myussd.presentation.home.component.HomeScreen
import uz.ibroximtechie.myussd.presentation.tarif.TarifViewModel
import uz.ibroximtechie.myussd.presentation.tarif.TarifViewModelFactory
import uz.ibroximtechie.myussd.presentation.tarif.components.TarifDetailScreen
import uz.ibroximtechie.myussd.presentation.tarif.components.TarifScreen
import uz.ibroximtechie.myussd.presentation.ussd_codes.UssdViewModel
import uz.ibroximtechie.myussd.presentation.ussd_codes.UssdViewModelFactory
import uz.ibroximtechie.myussd.presentation.ussd_codes.component.CodeScreen
import uz.ibroximtechie.myussd.presentation.ussdservices.UssdServiceViewModel
import uz.ibroximtechie.myussd.presentation.ussdservices.UssdServiceViewModelFactory
import uz.ibroximtechie.myussd.presentation.ussdservices.component.UssdServiceScreen
import uz.ibroximtechie.myussd.ui.theme.MyUSSDTheme
import kotlin.reflect.typeOf

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val repository = UssdRepositoryImpl(UssdDatabase.getInstance(this).dao())
        val homeViewModel by viewModels<HomeViewModel> (
            factoryProducer = { HomeViewModelFactory(repository) }
        )
        val ussdViewModel by viewModels<UssdViewModel> (
            factoryProducer = { UssdViewModelFactory(repository) }
        )
        val colorViewModel by viewModels<ColorViewModel> (
            factoryProducer = { ColorViewModelFactory() }
        )
        val tarifViewModel by viewModels<TarifViewModel> (
            factoryProducer = { TarifViewModelFactory(repository) }
        )
        val ussdServiceViewModel by viewModels<UssdServiceViewModel> (
            factoryProducer = { UssdServiceViewModelFactory(repository) }
        )



        setContent {
            MyUSSDTheme {

                val navController = rememberNavController()
                val colorState by colorViewModel.state.collectAsState()
                val ussdState by ussdViewModel.state.collectAsState()
                val tarifState by tarifViewModel.tarifState.collectAsState()
                val ussdServiceState by ussdServiceViewModel.ussdServiceState.collectAsState()
                NavHost(
                    navController = navController,
                    startDestination = Screen.HomeScreen,
                    enterTransition = { EnterTransition.None},
                    exitTransition = { ExitTransition.None },
                ){
                    composable<Screen.HomeScreen> {
                        HomeScreen(
                            navController = navController,
                            colorState = colorState,
                            colorEvent = colorViewModel::onEvent
                        )
                    }
                    composable<Screen.CodeScreen> {
                        CodeScreen(
                            navController = navController,
                            colorState = colorState,
                            ussdState = ussdState,
                            ussdEvent = ussdViewModel::onEvent
                        )
                    }
                    composable<Screen.TarifScreen> {
                        TarifScreen(
                            navController = navController,
                            colorState = colorState,
                            tarifState = tarifState,
                            tarifEvent = tarifViewModel::onTarifEvent
                        )
                    }

                    composable<Screen.TarifDetailScreen>(
                        typeMap = mapOf(
                            typeOf<Tarif>() to TarifNavType.TarifType
                        )
                    ) {
                        val arguments = it.toRoute<Screen.TarifDetailScreen>()
                        TarifDetailScreen(
                            navController = navController,
                            colorState = colorState,
                            tarif = arguments.tarif
                        )
                    }

                    composable<Screen.UssdServiceScreen> {
                        val arguments = it.toRoute<Screen.UssdServiceScreen>()
                        UssdServiceScreen(
                            navController = navController,
                            colorState = colorState,
                            ussdServiceState = ussdServiceState,
                            ussdServiceEvent = ussdServiceViewModel::onEvent,
                            categoryTypeId = arguments.categoryTypeId
                        )
                    }

                }
            }
        }
    }
}

