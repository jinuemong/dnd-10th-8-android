package ac.dnd.bookkeeping.android.domain.usecase.feature.relation

import ac.dnd.bookkeeping.android.domain.repository.RelationRepository
import javax.inject.Inject

class DeleteRelationUseCase @Inject constructor(
    private val relationRepository: RelationRepository
) {
    suspend operator fun invoke(
        id: Long
    ): Result<Unit> {
        return relationRepository.deleteRelation(
            id = id
        )
    }
}
