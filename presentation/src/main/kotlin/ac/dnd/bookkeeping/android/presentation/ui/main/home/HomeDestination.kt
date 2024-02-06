package ac.dnd.bookkeeping.android.presentation.ui.main.home

import ac.dnd.bookkeeping.android.presentation.common.util.ErrorObserver
import ac.dnd.bookkeeping.android.presentation.ui.main.ApplicationState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

fun NavGraphBuilder.homeDestination(
    appState: ApplicationState
) {
    composable(
        route = HomeConstant.ROUTE
    ) {
        val viewModel: HomeViewModel = hiltViewModel()

        val model: HomeModel = let {
            val state by viewModel.state.collectAsStateWithLifecycle()

            HomeModel(
                state = state
            )
        }

        ErrorObserver(viewModel)

        HomeScreen(
            appState = appState,
            model = model,
            event = viewModel.event,
            intent = viewModel::onIntent,
            handler = viewModel.handler
        )
    }
}
