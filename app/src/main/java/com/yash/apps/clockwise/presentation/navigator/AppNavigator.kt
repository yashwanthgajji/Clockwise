package com.yash.apps.clockwise.presentation.navigator

import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
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
import com.yash.apps.clockwise.domain.model.SubTask
import com.yash.apps.clockwise.domain.model.Task
import com.yash.apps.clockwise.presentation.alltasks.AllTaskScreen
import com.yash.apps.clockwise.presentation.alltasks.AllTaskViewModel
import com.yash.apps.clockwise.presentation.navgraph.Route
import com.yash.apps.clockwise.presentation.navigator.components.AppBottomNavigation
import com.yash.apps.clockwise.presentation.navigator.components.BottomNavigationItem
import com.yash.apps.clockwise.presentation.subtaskdetails.SubTaskDetailScreen
import com.yash.apps.clockwise.presentation.subtaskdetails.SubTaskDetailViewModel
import com.yash.apps.clockwise.presentation.taskdetails.TaskDetailScreen
import com.yash.apps.clockwise.presentation.taskdetails.TaskDetailViewModel
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
    var selectedItem by rememberSaveable {
        mutableIntStateOf(0)
    }
    val bottomBar: @Composable () -> Unit = {
        AppBottomNavigation(
            items = bottomNavigationItems,
            selectedItem = selectedItem,
            onItemClick = { index ->
                selectedItem = index
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
        composable(
            route = Route.TimelineScreen.route,
            enterTransition = {
                fadeIn(animationSpec = tween(500, easing = FastOutLinearInEasing))
            },
            exitTransition = {
                fadeOut(animationSpec = tween(500, easing = FastOutLinearInEasing))
            }
        ) {
            val viewModel: TimelineViewModel = hiltViewModel()
            val uiState = viewModel.timelineUiState.collectAsState()
            TimelineScreen(timelineUiState = uiState.value, bottomBarContent = bottomBar)
        }
        composable(
            route = Route.AllTasksScreen.route,
            enterTransition = {
                fadeIn(animationSpec = tween(500, easing = FastOutLinearInEasing))
            },
            exitTransition = {
                fadeOut(animationSpec = tween(500, easing = FastOutLinearInEasing))
            }
        ) {
            val viewModel: AllTaskViewModel = hiltViewModel()
            AllTaskScreen(
                viewModel = viewModel,
                onTaskClick = { navigateToTaskDetails(navController, task = it) },
                bottomBarContent = bottomBar
            )
        }
        composable(route = Route.ReportsScreen.route) {

        }
        composable(route = Route.TaskDetailScreen.route) {
            val viewModel: TaskDetailViewModel = hiltViewModel()
            navController.previousBackStackEntry?.savedStateHandle?.get<Task?>("taskDetail")
                ?.let { task ->
                    TaskDetailScreen(
                        viewModel = viewModel,
                        task = task,
                        onSubTaskClick = { navigateToSubTaskDetails(navController, subTask = it) }
                    )
                }
        }
        composable(route = Route.SubTaskDetailScreen.route) {
            val viewModel: SubTaskDetailViewModel = hiltViewModel()
            navController.previousBackStackEntry?.savedStateHandle?.get<SubTask?>("subTaskDetail")
                ?.let { subTask ->
                    SubTaskDetailScreen(
                        viewModel = viewModel,
                        subTask = subTask
                    )
                }
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

private fun navigateToTaskDetails(navController: NavController, task: Task) {
    navController.currentBackStackEntry?.savedStateHandle?.set("taskDetail", task)
    navController.navigate(route = Route.TaskDetailScreen.route)
}

private fun navigateToSubTaskDetails(navController: NavController, subTask: SubTask) {
    navController.currentBackStackEntry?.savedStateHandle?.set("subTaskDetail", subTask)
    navController.navigate(route = Route.SubTaskDetailScreen.route)
}