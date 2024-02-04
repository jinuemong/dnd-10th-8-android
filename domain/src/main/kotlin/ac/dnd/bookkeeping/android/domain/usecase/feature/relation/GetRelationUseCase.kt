package ac.dnd.bookkeeping.android.domain.usecase.feature.relation

import ac.dnd.bookkeeping.android.domain.model.relation.RelationDetail
import ac.dnd.bookkeeping.android.domain.repository.RelationRepository
import javax.inject.Inject

class GetRelationUseCase @Inject constructor(
    private val relationRepository: RelationRepository
) {
    suspend operator fun invoke(
        id: Long
    ): Result<RelationDetail> {
        return relationRepository.getRelation(
            id = id
        )
    }
}
