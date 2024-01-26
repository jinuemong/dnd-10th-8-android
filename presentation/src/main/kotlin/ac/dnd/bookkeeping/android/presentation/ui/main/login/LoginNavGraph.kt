package ac.dnd.bookkeeping.android.presentation.ui.main.login

import ac.dnd.bookkeeping.android.presentation.ui.main.ApplicationState
import ac.dnd.bookkeeping.android.presentation.ui.main.login.main.LoginMainConstant
import ac.dnd.bookkeeping.android.presentation.ui.main.login.main.loginMainDestination
import ac.dnd.bookkeeping.android.presentation.ui.main.login.onboarding.loginOnBoardingDestination
import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation

fun NavGraphBuilder.loginNavGraph(
    appState: ApplicationState
) {
    navigation(
        startDestination = LoginMainConstant.ROUTE,
        route = LoginConstant.ROUTE
    ) {
        loginOnBoardingDestination(appState = appState)
        loginMainDestination(appState = appState)
    }
}
