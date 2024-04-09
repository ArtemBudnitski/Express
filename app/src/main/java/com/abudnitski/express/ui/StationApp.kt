package com.abudnitski.express.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.abudnitski.express.ui.list.ListScreen
import com.abudnitski.express.ui.main.MainScreen


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StationApp(
    navController: NavHostController = rememberNavController()
) {
    Scaffold(
        topBar = {
            if (navController.equals((Screen.List.route))) {
                TopAppBar(title = { })
            }
        }
    ) { padding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Main.route,
            modifier = Modifier.padding(padding)
        ) {
            composable(Screen.Main.route) {
                MainScreen(
                    onClickScreenFrom = {
                        navController.navigate(
                            Screen.List.route.replace(
                                oldValue = "{$STATION_FIELD_KEY}",
                                newValue = "${1}"
                            )
                        )
                    },
                    onClickScreenTo = {
                        navController.navigate(
                            Screen.List.route.replace(
                                oldValue = "{$STATION_FIELD_KEY}",
                                newValue = "${2}"
                            )
                        )
                    }
                )
            }
            composable(Screen.List.route) { it ->
                val stationField = it.arguments?.getString(STATION_FIELD_KEY)
                ListScreen(
                    onClick = {
                        navController.navigate(
                            Screen.Main.route
                                .replace(
                                    oldValue = "{$STATION_FIELD_KEY}",
                                    newValue = "$stationField"
                                )
                                .replace(
                                    oldValue = "{$STATION_ID_KEY}",
                                    newValue = "$it"
                                )
                        )
                    },
                    onBackClick = { navController.popBackStack() })
            }
        }
    }
}

const val STATION_FIELD_KEY = "stationField"
const val STATION_ID_KEY = "stationId"

enum class Screen(val route: String) {
    List("list/{$STATION_FIELD_KEY}"),
    Main(route = "main?stationField={$STATION_FIELD_KEY}&stationId={$STATION_ID_KEY}")
}