package com.yash.apps.clockwise.presentation.navigator

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.yash.apps.clockwise.R
import com.yash.apps.clockwise.presentation.allTasks.AllTaskScreen
import com.yash.apps.clockwise.presentation.allTasks.AllTaskViewModel
import com.yash.apps.clockwise.presentation.navgraph.Route
import com.yash.apps.clockwise.presentation.navigator.components.AppBottomNavigation
import com.yash.apps.clockwise.presentation.navigator.components.BottomNavigationItem
import com.yash.apps.clockwise.presentation.timeline.TimelineScreen
import com.yash.apps.clockwise.presentation.timeline.TimelineViewModel

@Composable
fun AppNavigator(modifier: Modifier = Modifier) {
    val bottomNavigationItems = remember {
        listOf(
            BottomNavigationItem(icon = R.drawable.timeline_icon, text = "Timeline"),
            BottomNavigationItem(icon = R.drawable.tasks_icon, text = "All Tasks"),
            BottomNavigationItem(icon = R.drawable.chart_icon, text = "Reports")
        )
    }
    val navController = rememberNavController()
    val backStackState = navController.currentBackStackEntryAsState().value
    var selectedItem by rememberSaveable {
        mutableIntStateOf(0)
    }
    selectedItem = remember(key1 = backStackState) {
        when (backStackState?.destination?.route) {
            Route.TimelineScreen.route -> 0
            Route.AllTasksScreen.route -> 1
            Route.ReportsScreen.route -> 2
            else -> 0
        }
    }
    val bottomBar: @Composable () -> Unit = {
        AppBottomNavigation(
            items = bottomNavigationItems,
            selectedItem = selectedItem,
            onItemClick = { index ->
                when (index) {
                    0 -> navigateToTab(
                        navController = navController,
                        route = Route.TimelineScreen.route
                    )
                    1 -> navigateToTab(
                        navController = navController,
                        route = Route.AllTasksScreen.route
                    )
                    2 -> navigateToTab(
                        navController = navController,
                        route = Route.ReportsScreen.route
                    )
                }
            }
        )
    }
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = Route.TimelineScreen.route
    ) {
        composable(route = Route.TimelineScreen.route) {
            val viewModel: TimelineViewModel = hiltViewModel()
            val uiState = viewModel.timelineUiState.collectAsState()
            TimelineScreen(timelineUiState = uiState.value, bottomBarContent = bottomBar)
        }
        composable(route = Route.AllTasksScreen.route) {
            val viewModel: AllTaskViewModel = hiltViewModel()
            AllTaskScreen(viewModel = viewModel, bottomBarContent = bottomBar)
        }
        composable(route = Route.ReportsScreen.route) {

        }
    }
}

private fun navigateToTab(navController: NavController, route: String) {
    navController.navigate(route) {
        navController.graph.startDestinationRoute?.let { homeScreen ->
            popUpTo(homeScreen) {
                saveState = true
            }
            restoreState = true
            launchSingleTop = true
        }
    }
}

//private fun navigateToDetails(navController: NavController, fountain: Fountain) {
//    navController.currentBackStackEntry?.savedStateHandle?.set("fountain", fountain)
//    navController.navigate(route = Route.TaskDetailScreen.route)
//}