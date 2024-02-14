package ac.dnd.bookkeeping.android.presentation.ui.main.home.schedule

import ac.dnd.bookkeeping.android.domain.model.feature.schedule.Schedule
import ac.dnd.bookkeeping.android.domain.model.feature.schedule.ScheduleRelation
import ac.dnd.bookkeeping.android.domain.model.feature.schedule.ScheduleRelationGroup
import ac.dnd.bookkeeping.android.presentation.R
import ac.dnd.bookkeeping.android.presentation.common.theme.Body0
import ac.dnd.bookkeeping.android.presentation.common.theme.Body1
import ac.dnd.bookkeeping.android.presentation.common.theme.Caption2
import ac.dnd.bookkeeping.android.presentation.common.theme.Gray000
import ac.dnd.bookkeeping.android.presentation.common.theme.Gray600
import ac.dnd.bookkeeping.android.presentation.common.theme.Gray700
import ac.dnd.bookkeeping.android.presentation.common.theme.Headline3
import ac.dnd.bookkeeping.android.presentation.common.theme.Icon24
import ac.dnd.bookkeeping.android.presentation.common.theme.Shapes
import ac.dnd.bookkeeping.android.presentation.common.view.confirm.ConfirmButton
import ac.dnd.bookkeeping.android.presentation.common.view.confirm.ConfirmButtonProperties
import ac.dnd.bookkeeping.android.presentation.common.view.confirm.ConfirmButtonSize
import ac.dnd.bookkeeping.android.presentation.common.view.confirm.ConfirmButtonType
import ac.dnd.bookkeeping.android.presentation.model.history.HistoryEventType
import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.minus
import kotlinx.datetime.todayIn

@Composable
fun ScheduleScreenItem(
    schedule: Schedule,
    onClickSchedule: (Schedule) -> Unit,
    onClickInvitation: (Schedule) -> Unit
) {
    val now = Clock.System.todayIn(TimeZone.currentSystemDefault())
    val dDay = (schedule.day - now).days
    val color = Color(HistoryEventType.getEventTypeColor(schedule.event))
    val iconRes = HistoryEventType.getEventIconRes(schedule.relation.group.name)
    val formattedTime = Unit.let {
        schedule.time?.let {
            val fixedHour = if (it.hour == 0) 24 else it.hour
            val timeHour = (fixedHour - 1) % 12 + 1
            val timeMinute = it.minute
            val timeAmPm = if (fixedHour < 12) "오전" else "오후"
            val format = "%s %02d:%02d"
            runCatching {
                String.format(format, timeAmPm, timeHour, timeMinute)
            }.getOrDefault("시간 없음")
        } ?: "시간 없음"
    }
    Row {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(Gray000),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    modifier = Modifier.width(20.dp),
                    painter = painterResource(id = iconRes),
                    contentDescription = null
                )
            }
            if (dDay in (1..30)) {
                Text(
                    text = "D-$dDay",
                    style = Caption2.merge(Gray600),
                )
            }
        }
        Spacer(modifier = Modifier.width(8.dp))
        Card(
            modifier = Modifier.fillMaxWidth(),
            backgroundColor = Gray000,
            shape = Shapes.medium,
            elevation = 0.dp
        ) {
            Row {
                Box(
                    modifier = Modifier
                        .width(6.dp)
                        .background(color = color)
                )
            }
            Column(
                modifier = Modifier.clickable {
                    onClickSchedule(schedule)
                }
            ) {
                Spacer(modifier = Modifier.height(12.dp))
                Row(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = schedule.relation.name,
                        style = Headline3
                    )
                    Text(
                        text = "・",
                        style = Body0.merge(color = Gray700)
                    )
                    Text(
                        text = schedule.relation.group.name,
                        style = Body0.merge(color = Gray700)
                    )
                    Spacer(modifier = Modifier.width(2.dp))
                    Icon(
                        modifier = Modifier.size(Icon24),
                        painter = painterResource(id = R.drawable.ic_chevron_right),
                        contentDescription = null,
                        tint = Gray600
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Card(
                        backgroundColor = color,
                        shape = RoundedCornerShape(100.dp),
                        elevation = 0.dp
                    ) {
                        Box(
                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 2.dp)
                        ) {
                            Text(
                                text = schedule.event,
                                style = Body1.merge(Gray000)
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.height(6.dp))
                if (schedule.time != null) {
                    Spacer(modifier = Modifier.height(6.dp))
                    ScheduleScreenItemDescription(
                        iconRes = R.drawable.ic_clock,
                        text = formattedTime
                    )
                }
                if (schedule.location.isNotEmpty()) {
                    Spacer(modifier = Modifier.height(6.dp))
                    ScheduleScreenItemDescription(
                        iconRes = R.drawable.ic_location,
                        text = schedule.location
                    )
                }
                // TODO : memo
                Spacer(modifier = Modifier.height(16.dp))
                if (schedule.link.isNotEmpty()) {
                    ConfirmButton(
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                            .fillMaxWidth(),
                        properties = ConfirmButtonProperties(
                            size = ConfirmButtonSize.Medium,
                            type = ConfirmButtonType.Secondary
                        ),
                        onClick = {
                            onClickInvitation(schedule)
                        }
                    ) { style ->
                        Icon(
                            modifier = Modifier.size(16.dp),
                            painter = painterResource(id = R.drawable.ic_link),
                            contentDescription = null,
                            tint = Gray600
                        )
                        Text(
                            text = "초대장 보기",
                            style = style
                        )
                    }
                    Spacer(modifier = Modifier.height(6.dp))
                }
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}

@Composable
fun ScheduleScreenItemDescription(
    @DrawableRes iconRes: Int,
    text: String
) {
    Spacer(modifier = Modifier.height(6.dp))
    Row(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier.size(12.dp),
            painter = painterResource(id = iconRes),
            contentDescription = null,
            tint = Gray600
        )
        Spacer(modifier = Modifier.width(6.dp))
        Text(
            text = text,
            style = Body1.merge(color = Gray700)
        )
    }
}

@Preview
@Composable
private fun ScheduleScreenItemPreview() {
    ScheduleScreenItem(
        schedule = Schedule(
            id = 4830,
            relation = ScheduleRelation(
                id = 7220,
                name = "Marietta Justice",
                group = ScheduleRelationGroup(
                    id = 2824,
                    name = "Allen O'Neil"
                )
            ),
            day = LocalDate(2024, 2, 25),
            event = "돌잔치",
            time = null,
            link = "graeco",
            location = "aliquet"
        ),
        onClickSchedule = {},
        onClickInvitation = {}
    )
}