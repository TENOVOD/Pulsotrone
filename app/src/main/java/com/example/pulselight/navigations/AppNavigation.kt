package com.example.pulselight.navigations

import android.app.Application
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.pulselight.ui.screens.HeartMeasuringScreen
import com.example.pulselight.ui.screens.HistoryScreen
import com.example.pulselight.ui.screens.LoadingScreen
import com.example.pulselight.ui.screens.OnboardingScreen
import com.example.pulselight.ui.screens.ResultScreen
import com.example.pulselight.ui.screens.WelcomeHomepageScreen
import com.example.pulselight.viewmodels.HistoryViewModel
import com.example.pulselight.viewmodels.HistoryViewModelFactory
import com.example.pulselight.viewmodels.OnboardingViewModel
import com.example.pulselight.viewmodels.PulseDetectorViewModel
import com.example.pulselight.viewmodels.PulseDetectorViewModelFactory
import com.example.pulselight.viewmodels.ResultViewModel
import com.example.pulselight.viewmodels.ResultViewModelFactory


@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val context = LocalContext.current
    val application = context.applicationContext as Application


    NavHost(navController = navController, startDestination = "loadingScreen") {
        composable("loadingScreen") { LoadingScreen(navController = navController) }
        composable("OnboardingScreen") {
            val onboardingViewModel: OnboardingViewModel = viewModel()
            OnboardingScreen(
                navController = navController,
                onboardingViewModel
            )
        }
        composable("HomepageMeasuring") { WelcomeHomepageScreen(navController = navController) }
        composable("MeasuringPage") { backStackEntry ->
            val measuringViewModel: PulseDetectorViewModel = viewModel(
                factory = PulseDetectorViewModelFactory(application),
                viewModelStoreOwner = backStackEntry
            )
            HeartMeasuringScreen(
                navController = navController,
            )
        }
        composable(
            "ResultScreen/{recordId}",
            arguments = listOf(navArgument("recordId") { type = NavType.LongType })
        ) { navBackStackEntry ->
            val recordId = navBackStackEntry.arguments!!.getLong("recordId")
            val resultViewModel: ResultViewModel = viewModel(
                factory = ResultViewModelFactory(application),
                viewModelStoreOwner = navBackStackEntry
            )
            ResultScreen(navController = navController, vm = resultViewModel, recordId = recordId)
        }
        composable("HistoryScreen") { backStackEntry ->
            val historyViewModel: HistoryViewModel = viewModel(
                factory = HistoryViewModelFactory(application),
                viewModelStoreOwner = backStackEntry
            )
            HistoryScreen(navController = navController, vm = historyViewModel)
        }
    }
    /*owner?.let {


        val onboardingViewModel: OnboardingViewModel = viewModel()
        val measuringViewModel: PulseDetectorViewModel = viewModel(
            factory = PulseDetectorViewModelFactory(application),
            viewModelStoreOwner = it
        )
        val resultViewModel:ResultViewModel= viewModel(
            factory = ResultViewModelFactory(application),
            viewModelStoreOwner = it
        )
        val historyViewModel:HistoryViewModel = viewModel(
            factory = HistoryViewModelFactory(application),
            viewModelStoreOwner = it
        )



        NavHost(navController = navController, startDestination = "loadingScreen") {
            composable("loadingScreen") { LoadingScreen(navController = navController) }
            composable("OnboardingScreen") {
                OnboardingScreen(
                    navController = navController,
                    onboardingViewModel
                )
            }
            composable("HomepageMeasuring") { WelcomeHomepageScreen(navController = navController) }
            composable("MeasuringPage") {
                HeartMeasuringScreen(
                    navController = navController,
                    measuringViewModel
                )
            }
            composable("ResultScreen/{recordId}",
                arguments = listOf(navArgument("recordId"){type=NavType.LongType})
            ){
                navBackStackEntry ->
                val recordId = navBackStackEntry.arguments!!.getLong("recordId")
                ResultScreen(navController = navController,vm= resultViewModel,recordId=recordId)
            }
            composable("HistoryScreen"){ HistoryScreen(navController=navController,vm=historyViewModel)}


        }
    }*/
}