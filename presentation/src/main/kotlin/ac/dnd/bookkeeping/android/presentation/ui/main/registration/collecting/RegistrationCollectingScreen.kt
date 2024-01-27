package ac.dnd.bookkeeping.android.presentation.ui.main.registration.collecting

import ac.dnd.bookkeeping.android.presentation.common.util.LaunchedEffectWithLifecycle
import ac.dnd.bookkeeping.android.presentation.common.util.coroutine.event.EventFlow
import ac.dnd.bookkeeping.android.presentation.common.util.coroutine.event.MutableEventFlow
import ac.dnd.bookkeeping.android.presentation.common.util.coroutine.event.eventObserve
import ac.dnd.bookkeeping.android.presentation.ui.main.ApplicationState
import ac.dnd.bookkeeping.android.presentation.ui.main.rememberApplicationState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.CoroutineExceptionHandler

@Composable
fun RegistrationCollectingScreen(
    appState: ApplicationState,
    model: RegistrationCollectingModel,
    event: EventFlow<RegistrationCollectingEvent>,
    intent: (RegistrationCollectingIntent) -> Unit,
    handler: CoroutineExceptionHandler
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Black),
    ) {
        Text(
            text = "Collecting Screen",
            fontSize = 20.sp,
            color = Color.White,
            modifier = Modifier.align(Alignment.Center)
        )

        Surface(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(30.dp)
                .fillMaxWidth()
                .clickable {
                    intent(RegistrationCollectingIntent.OnClickSubmit)
                },
            shape = RoundedCornerShape(10.dp),
            color = Color.White
        ) {
            Text(
                text = "next",
                fontSize = 20.sp,
                color = Color.Black,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(5.dp)
            )
        }
    }

    LaunchedEffectWithLifecycle(event, handler) {
        event.eventObserve { event ->
            when (event) {
                is RegistrationCollectingEvent.GoToNextStep -> {}
            }
        }
    }
}

@Preview
@Composable
fun RegistrationCollectingScreenPreview() {
    RegistrationCollectingScreen(
        appState = rememberApplicationState(),
        model = RegistrationCollectingModel(
            state = RegistrationCollectingState.Init
        ),
        event = MutableEventFlow(),
        intent = {},
        handler = CoroutineExceptionHandler { _, _ -> }
    )
}