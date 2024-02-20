package ac.dnd.mour.android.presentation.ui.main.home.schedule.add

import ac.dnd.mour.android.presentation.common.util.ErrorObserver
import ac.dnd.mour.android.presentation.ui.main.ApplicationState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

fun NavGraphBuilder.scheduleAddDestination(
    appState: ApplicationState
) {
    composable(
        route = ScheduleAddConstant.ROUTE_STRUCTURE,
        arguments = listOf(
            navArgument(ScheduleAddConstant.ROUTE_ARGUMENT_SCHEDULE_ID) {
                type = NavType.LongType
                defaultValue = -1L
            }
        )
    ) {
        val viewModel: ScheduleAddViewModel = hiltViewModel()

        val model: ScheduleAddModel = let {
            val state by viewModel.state.collectAsStateWithLifecycle()
            val isEdit = viewModel.isEdit

            ScheduleAddModel(
                state = state,
                isEdit = isEdit
            )
        }

        ErrorObserver(viewModel)

        ScheduleAddScreen(
            appState = appState,
            model = model,
            event = viewModel.event,
            intent = viewModel::onIntent,
            handler = viewModel.handler
        )
    }
}