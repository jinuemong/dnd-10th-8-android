package ac.dnd.bookkeeping.android.presentation.ui.main.home.statistics.me.event

import ac.dnd.bookkeeping.android.presentation.common.util.ErrorObserver
import ac.dnd.bookkeeping.android.presentation.ui.main.ApplicationState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

fun NavGraphBuilder.statisticsMeEventDestination(
    appState: ApplicationState
) {
    composable(
        route = StatisticsMeEventConstant.ROUTE_STRUCTURE,
        arguments = listOf(
            navArgument(StatisticsMeEventConstant.ROUTE_ARGUMENT_EVENT_ID) {
                type = NavType.LongType
                defaultValue = -1L
            }
        )
    ) {
        val viewModel: StatisticsMeEventViewModel = hiltViewModel()

        val model: StatisticsMeEventModel = let {
            val state by viewModel.state.collectAsStateWithLifecycle()

            StatisticsMeEventModel(
                state = state
            )
        }

        ErrorObserver(viewModel)

        StatisticsMeEventScreen(
            appState = appState,
            model = model,
            event = viewModel.event,
            intent = viewModel::onIntent,
            handler = viewModel.handler
        )
    }
}
