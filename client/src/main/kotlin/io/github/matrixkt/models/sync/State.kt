package io.github.matrixkt.models.sync

import io.github.matrixkt.models.events.MatrixEvent
import kotlinx.serialization.Serializable

@Serializable
data class State(
    /**
     * List of events.
     */
    val events: List<MatrixEvent> = emptyList()
)
