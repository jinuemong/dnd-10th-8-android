package ac.dnd.bookkeeping.android.data.remote.network.model.heart

import ac.dnd.bookkeeping.android.data.remote.mapper.DataMapper
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AddUnrecordedHeartRes(
    @SerialName("result")
    val result: Long
) : DataMapper<Long> {
    override fun toDomain(): Long {
        return result
    }
}

