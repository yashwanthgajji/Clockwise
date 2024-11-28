package com.yash.apps.clockwise.presentation.navgraph

sealed class Route(val route: String) {
    object TimelineScreen: Route(route = "timelineScreen")
    object AllTasksScreen: Route(route = "allTasksScreen")
    object ReportsScreen: Route(route = "reportsScreen")
    object TaskDetailScreen: Route(route = "taskDetailScreen")
    object SubTaskDetailScreen: Route(route = "subTaskDetailScreen")
    object NewRecordScreen: Route(route = "newRecordScreen")
}