package com.yash.apps.clockwise.presentation.navigator

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.yash.apps.clockwise.R
import com.yash.apps.clockwise.domain.model.SubTask
import com.yash.apps.clockwise.domain.model.Task
import com.yash.apps.clockwise.presentation.alltasks.AllTaskScreen
import com.yash.apps.clockwise.presentation.alltasks.AllTaskViewModel
import com.yash.apps.clockwise.presentation.common.CurrentTaskCard
import com.yash.apps.clockwise.presentation.navgraph.Route
import com.yash.apps.clockwise.presentation.navigator.components.AppBottomNavigation
import com.yash.apps.clockwise.presentation.navigator.components.BottomNavigationItem
import com.yash.apps.clockwise.presentation.newrecord.NewRecordScreen
import com.yash.apps.clockwise.presentation.newrecord.NewRecordViewModel
import com.yash.apps.clockwise.presentation.reports.ReportsScreen
import com.yash.apps.clockwise.presentation.reports.ReportsViewModel
import com.yash.apps.clockwise.presentation.subtaskdetails.SubTaskDetailScreen
import com.yash.apps.clockwise.presentation.subtaskdetails.SubTaskDetailViewModel
import com.yash.apps.clockwise.presentation.taskdetails.TaskDetailScreen
import com.yash.apps.clockwise.presentation.taskdetails.TaskDetailViewModel
import com.yash.apps.clockwise.presentation.timeline.TimelineScreen
import com.yash.apps.clockwise.presentation.timeline.TimelineViewModel
import com.yash.apps.clockwise.util.Constants.DURATION_FORMAT
import com.yash.apps.clockwise.util.Constants.NEW_RECORD_SUB_TASK
import com.yash.apps.clockwise.util.Constants.NEW_RECORD_TASK
import com.yash.apps.clockwise.util.Constants.SUB_TASK_DETAIL_SUB_TASK
import com.yash.apps.clockwise.util.Constants.SUB_TASK_DETAIL_TASK
import com.yash.apps.clockwise.util.Constants.TASK_DETAIL_TASK
import com.yash.apps.clockwise.util.DateFormatter

@Composable
fun AppNavigator(
    modifier: Modifier = Modifier,
    appNavigatorViewModel: AppNavigatorViewModel = hiltViewModel()
) {
    val appNavigatorUiState by appNavigatorViewModel.appNavigatorUiState.collectAsState()
    val timer = appNavigatorViewModel.timeInSeconds.collectAsState()
    val bottomNavigationItems = remember {
        listOf(
            BottomNavigationItem(icon = R.drawable.timeline_icon, text = "Timeline"),
            BottomNavigationItem(icon = R.drawable.tasks_icon, text = "All Tasks"),
            BottomNavigationItem(icon = R.drawable.chart_icon, text = "Reports")
        )
    }
    val navController = rememberNavController()
    val currentTaskCard: @Composable () -> Unit = {
        CurrentTaskCard(
            taskName = appNavigatorUiState.task?.tName ?: "",
            subTaskName = appNavigatorUiState.subTask?.sName,
            duration = DateFormatter.formatDuration(timer.value, DURATION_FORMAT),
            onStopPressed = appNavigatorViewModel::stopActiveSession
        )
    }
    val bottomBar: @Composable () -> Unit = {
        AppBottomNavigation(
            items = bottomNavigationItems,
            selectedItem = appNavigatorUiState.selectedBottomNavigationTab,
            onItemClick = { index ->
                appNavigatorViewModel.update(
                    appNavigatorUiState.copy(
                        selectedBottomNavigationTab = index
                    )
                )
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
                fadeIn(animationSpec = tween(500, easing = LinearEasing))
            },
            exitTransition = {
                fadeOut(animationSpec = tween(500, easing = LinearEasing))
            }
        ) {
            val viewModel: TimelineViewModel = hiltViewModel()
            val uiState = viewModel.timelineUiState.collectAsState()
            TimelineScreen(
                timelineUiState = uiState.value,
                bottomBarContent = bottomBar,
                isActiveSession = appNavigatorUiState.isActiveSession,
                activeSessionComponent = currentTaskCard
            )
        }
        composable(
            route = Route.AllTasksScreen.route,
            enterTransition = {
                fadeIn(animationSpec = tween(500, easing = LinearEasing))
            },
            exitTransition = {
                fadeOut(animationSpec = tween(500, easing = LinearEasing))
            }
        ) {
            val viewModel: AllTaskViewModel = hiltViewModel()
            AllTaskScreen(
                viewModel = viewModel,
                onTaskClick = { navigateToTaskDetails(navController, task = it) },
                onSubTaskClick = { task, subTask ->
                    navigateToSubTaskDetails(navController, task = task, subTask = subTask)
                },
                bottomBarContent = bottomBar,
                onStartClick = appNavigatorViewModel::startActiveSession,
                isActiveSession = appNavigatorUiState.isActiveSession,
                activeSessionComponent = currentTaskCard
            )
        }
        composable(
            route = Route.ReportsScreen.route,
            enterTransition = {
                fadeIn(animationSpec = tween(500, easing = LinearEasing))
            },
            exitTransition = {
                fadeOut(animationSpec = tween(500, easing = LinearEasing))
            }
        ) {
            val viewModel: ReportsViewModel = hiltViewModel()
            ReportsScreen(viewModel = viewModel, bottomBarContent = bottomBar)
        }
        composable(
            route = Route.TaskDetailScreen.route,
            enterTransition = {
                fadeIn(animationSpec = tween(500, easing = LinearEasing))
            },
            exitTransition = {
                fadeOut(animationSpec = tween(500, easing = LinearEasing))
            }
        ) {
            val viewModel: TaskDetailViewModel = hiltViewModel()
            val task =
                navController.previousBackStackEntry?.savedStateHandle?.get<Task?>(TASK_DETAIL_TASK)
            task?.let { it1 -> viewModel.setTask(it1) }
            TaskDetailScreen(
                viewModel = viewModel,
                onNewRecordClick = {
                    navigateToNewRecord(navController = navController, task = it)
                },
                onSubTaskClick = { t, s ->
                    navigateToSubTaskDetails(navController, task = t, subTask = s)
                },
                onStartClick = appNavigatorViewModel::startActiveSession,
                isActiveSession = appNavigatorUiState.isActiveSession,
                activeSessionComponent = currentTaskCard
            )
        }
        composable(
            route = Route.SubTaskDetailScreen.route,
            enterTransition = {
                fadeIn(animationSpec = tween(500, easing = LinearEasing))
            },
            exitTransition = {
                fadeOut(animationSpec = tween(500, easing = LinearEasing))
            }
        ) {
            val viewModel: SubTaskDetailViewModel = hiltViewModel()
            val task = navController.previousBackStackEntry?.savedStateHandle?.get<Task?>(
                SUB_TASK_DETAIL_TASK
            )
            val subTask = navController.previousBackStackEntry?.savedStateHandle?.get<SubTask?>(
                SUB_TASK_DETAIL_SUB_TASK
            )
            if (task != null && subTask != null) {
                viewModel.setSubTask(task, subTask)
            }
            SubTaskDetailScreen(
                viewModel = viewModel,
                onNewRecordClick = { t, s ->
                    navigateToNewRecord(
                        navController = navController,
                        task = t,
                        subTask = s
                    )
                },
                onStartClick = appNavigatorViewModel::startActiveSession,
                isActiveSession = appNavigatorUiState.isActiveSession,
                activeSessionComponent = currentTaskCard
            )
        }
        composable(
            route = Route.NewRecordScreen.route,
            enterTransition = {
                fadeIn(animationSpec = tween(500, easing = LinearEasing))
            },
            exitTransition = {
                fadeOut(animationSpec = tween(500, easing = LinearEasing))
            }
        ) {
            val viewModel: NewRecordViewModel = hiltViewModel()
            val task =
                navController.previousBackStackEntry?.savedStateHandle?.get<Task?>(NEW_RECORD_TASK)
            val subTask = navController.previousBackStackEntry?.savedStateHandle?.get<SubTask?>(
                NEW_RECORD_SUB_TASK
            )
            task?.let { it1 ->
                viewModel.setTaskAndSubTask(it1, subTask)
            }
            NewRecordScreen(
                viewModel = viewModel,
                onBackPress = { navController.navigateUp() }
            )
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
    navController.currentBackStackEntry?.savedStateHandle?.set(TASK_DETAIL_TASK, task)
    navController.navigate(route = Route.TaskDetailScreen.route)
}

private fun navigateToSubTaskDetails(navController: NavController, task: Task, subTask: SubTask) {
    navController.currentBackStackEntry?.savedStateHandle?.set(SUB_TASK_DETAIL_TASK, task)
    navController.currentBackStackEntry?.savedStateHandle?.set(SUB_TASK_DETAIL_SUB_TASK, subTask)
    navController.navigate(route = Route.SubTaskDetailScreen.route)
}

private fun navigateToNewRecord(
    navController: NavController,
    task: Task,
    subTask: SubTask? = null
) {
    navController.currentBackStackEntry?.savedStateHandle?.set(NEW_RECORD_TASK, task)
    subTask?.let {
        navController.currentBackStackEntry?.savedStateHandle?.set(NEW_RECORD_SUB_TASK, subTask)
    }
    navController.navigate(route = Route.NewRecordScreen.route)
}