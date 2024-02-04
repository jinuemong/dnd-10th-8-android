package ac.dnd.bookkeeping.android.domain.usecase.feature.heart

import ac.dnd.bookkeeping.android.domain.repository.HeartRepository
import kotlinx.datetime.LocalDate
import javax.inject.Inject

class EditHeartUseCase @Inject constructor(
    private val heartRepository: HeartRepository
) {
    suspend operator fun invoke(
        id: Long,
        money: Long,
        day: LocalDate,
        event: String,
        memo: String,
        tags: List<String>
    ): Result<Unit> {
        return heartRepository.editHeart(
            id = id,
            money = money,
            day = day,
            event = event,
            memo = memo,
            tags = tags
        )
    }
}
