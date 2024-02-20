package ac.dnd.mour.android.presentation.ui.main.home.history

import ac.dnd.mour.android.domain.model.feature.schedule.UnrecordedSchedule
import ac.dnd.mour.android.domain.model.feature.schedule.UnrecordedScheduleRelation
import ac.dnd.mour.android.domain.model.feature.schedule.UnrecordedScheduleRelationGroup
import ac.dnd.mour.android.presentation.R
import ac.dnd.mour.android.presentation.common.theme.Body1
import ac.dnd.mour.android.presentation.common.theme.Gray000
import ac.dnd.mour.android.presentation.common.theme.Gray500
import ac.dnd.mour.android.presentation.common.theme.Gray700
import ac.dnd.mour.android.presentation.common.theme.Headline1
import ac.dnd.mour.android.presentation.common.theme.Primary2
import ac.dnd.mour.android.presentation.common.theme.Primary3
import ac.dnd.mour.android.presentation.common.theme.Primary4
import ac.dnd.mour.android.presentation.common.theme.Space8
import ac.dnd.mour.android.presentation.common.view.textfield.TypingTextField
import ac.dnd.mour.android.presentation.common.view.textfield.TypingTextFieldType
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalTime

@Composable
fun HistoryBackgroundComponent(
    model: HistoryModel,
    searchText: String,
    isTextFieldFocused: Boolean,
    isViewUnrecordedState: Boolean,
    onFocusChange: (Boolean) -> Unit,
    alarmState: Boolean = true,
    onClickUnrecorded: () -> Unit,
    onDeleteUnrecorded: () -> Unit,
    onClickAlarm: () -> Unit,
    onSearchValueChange: (String) -> Unit
) {
    val currentColorState = animateColorAsState(
        targetValue = if (isTextFieldFocused) Primary2 else Color.Transparent,
        label = "color state"
    )

    @Composable
    fun getAlarmImage(): Painter {
        return painterResource(if (alarmState) R.drawable.ic_notification_on else R.drawable.ic_notification)
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Primary3)
    ) {
        Box(
            modifier = Modifier
                .height(56.dp)
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
        ) {
            Image(
                painter = painterResource(R.drawable.ic_logo),
                contentDescription = null,
                modifier = Modifier.align(Alignment.CenterStart)
            )
            Image(
                painter = getAlarmImage(),
                contentDescription = null,
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .clickable {
                        onClickAlarm()
                    }
            )
        }
        Box(
            modifier = Modifier
                .height(287.dp)
                .fillMaxWidth()
        ) {
            Box(
                modifier = Modifier
                    .padding(
                        top = 2.dp,
                    )
                    .offset(x = 16.dp)
                    .align(Alignment.TopEnd)
            ) {
                Image(
                    painter = painterResource(R.drawable.ic_star),
                    modifier = Modifier
                        .size(83.dp),
                    contentDescription = null,
                    colorFilter = ColorFilter.tint(Color(0x26FFFFFF))
                )
            }
            Box(
                modifier = Modifier
                    .padding(top = 82.dp)
                    .offset(x = (-34).dp)
                    .align(Alignment.TopStart)
            ) {
                Image(
                    painter = painterResource(R.drawable.ic_star),
                    modifier = Modifier
                        .size(72.dp),
                    contentDescription = null,
                    colorFilter = ColorFilter.tint(Color(0x26FFFFFF))
                )
            }
            Box(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(
                        top = 8.dp,
                        end = 24.62.dp
                    )
            ) {
                Image(
                    painter = painterResource(R.drawable.ic_history_flower),
                    contentDescription = null,
                    modifier = Modifier.rotate(-2.18f)
                )
            }
            Column(
                modifier = Modifier
                    .padding(
                        top = 40.dp,
                        bottom = 24.dp,
                        start = 20.dp,
                        end = 20.dp
                    )
            ) {
                Column(modifier = Modifier.height(60.dp)) {
                    Text(
                        text = "총 ${model.groups.size}번의 마음을 \n주고 받았어요",
                        style = Headline1.merge(
                            color = Gray000,
                            fontWeight = FontWeight.SemiBold
                        ),
                        lineHeight = 30.sp
                    )
                }
                Spacer(modifier = Modifier.height(32.dp))
                if (model.unrecordedSchedule.isNotEmpty() && isViewUnrecordedState) {
                    Card(shape = RoundedCornerShape(16.dp)) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    onClickUnrecorded()
                                }
                                .padding(16.dp)
                        ) {
                            Image(
                                painter = painterResource(R.drawable.ic_close_circle),
                                contentDescription = null,
                                modifier = Modifier
                                    .align(Alignment.TopEnd)
                                    .clickable {
                                        onDeleteUnrecorded()
                                    }
                            )
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Box(
                                    modifier = Modifier
                                        .background(
                                            shape = CircleShape,
                                            color = Color(0xFFDBF6FF)
                                        )
                                        .size(40.dp),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Image(
                                        painter = painterResource(R.drawable.ic_schedule),
                                        contentDescription = null
                                    )
                                }
                                Spacer(modifier = Modifier.width(Space8))
                                Column {
                                    Text(
                                        text = "지난 일정 ${model.unrecordedSchedule.size}개",
                                        style = Body1.merge(
                                            color = Gray700,
                                            fontWeight = FontWeight.SemiBold
                                        )
                                    )
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        Text(
                                            text = "한번에 기록하기",
                                            style = Body1.merge(
                                                color = Gray500,
                                                fontWeight = FontWeight.Medium
                                            )
                                        )
                                        Image(
                                            painter = painterResource(R.drawable.ic_chevron_right),
                                            contentDescription = null,
                                            modifier = Modifier.size(16.dp)
                                        )
                                    }
                                }
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(12.dp))
                }
                Surface(
                    shape = RoundedCornerShape(100.dp),
                    border = BorderStroke(
                        width = 2.dp,
                        color = currentColorState.value
                    ),
                    modifier = Modifier.height(45.dp),
                ) {
                    TypingTextField(
                        textType = TypingTextFieldType.Basic,
                        text = searchText,
                        backgroundColor = Color(0xFFEEABFF),
                        onValueChange = onSearchValueChange,
                        fieldHeight = 45.dp,
                        contentPadding = PaddingValues(
                            start = if (!isTextFieldFocused && searchText.isEmpty()) 6.dp else 16.dp,
                        ),
                        cursorColor = Primary4,
                        basicBorderColor = Color.Transparent,
                        hintText = "이름을 입력하세요.",
                        hintTextColor = Gray000,
                        textStyle = Body1.merge(
                            color = Gray000,
                            fontWeight = FontWeight.Medium
                        ),
                        leadingIconContent = {
                            if (!isTextFieldFocused && searchText.isEmpty()) {
                                Box(modifier = Modifier.padding(start = 16.dp)) {
                                    Image(
                                        painter = painterResource(R.drawable.ic_search),
                                        contentDescription = null,
                                        modifier = Modifier.size(16.dp),
                                        colorFilter = ColorFilter.tint(Gray000)
                                    )
                                }
                            }
                        },
                        trailingIconContent = if (searchText.isNotEmpty()) {
                            {
                                Image(
                                    painter = painterResource(R.drawable.ic_close_circle),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .size(20.dp)
                                        .clickable {
                                            onSearchValueChange("")
                                        }
                                )
                            }
                        } else null,
                        onTextFieldFocusChange = onFocusChange
                    )
                }
            }
        }
    }
}

@Preview(apiLevel = 33)
@Composable
private fun HistoryBackgroundComponent1Preview() {
    HistoryBackgroundComponent(
        model = HistoryModel(
            state = HistoryState.Init,
            groups = emptyList(),
            unrecordedSchedule = listOf()
        ),
        searchText = "",
        onClickAlarm = {},
        onSearchValueChange = {},
        onClickUnrecorded = {},
        onDeleteUnrecorded = {},
        isTextFieldFocused = true,
        isViewUnrecordedState = true,
        onFocusChange = {},
    )
}

@Preview(apiLevel = 33)
@Composable
private fun HistoryBackgroundComponent2Preview() {
    HistoryBackgroundComponent(
        model = HistoryModel(
            state = HistoryState.Init,
            groups = emptyList(),
            unrecordedSchedule = listOf(
                UnrecordedSchedule(
                    0,
                    UnrecordedScheduleRelation(
                        0,
                        "김진우",
                        UnrecordedScheduleRelationGroup(
                            0,
                            "친척"
                        )
                    ),
                    day = LocalDate(2024, 1, 1),
                    event = "결혼",
                    time = LocalTime(12, 12),
                    link = "",
                    location = ""
                ),
                UnrecordedSchedule(
                    1,
                    UnrecordedScheduleRelation(
                        0,
                        "김진우",
                        UnrecordedScheduleRelationGroup(
                            0,
                            "가족"
                        )
                    ),
                    day = LocalDate(2024, 2, 2),
                    event = "돌잔치",
                    time = LocalTime(12, 12),
                    link = "",
                    location = ""
                ),
            )
        ),
        searchText = "",
        onClickAlarm = {},
        onSearchValueChange = {},
        onClickUnrecorded = {},
        onDeleteUnrecorded = {},
        isTextFieldFocused = true,
        isViewUnrecordedState = true,
        onFocusChange = {},
    )
}