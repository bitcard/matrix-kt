package io.github.matrixkt.models.thirdparty

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject

@Serializable
class ProtocolInstance(
    /**
     * A human-readable description for the protocol, such as the name.
     */
    val desc: String,

    /**
     * An optional content URI representing the protocol.
     * Overrides the one provided at the higher level [Protocol] object.
     */
    val icon: String? = null,

    /**
     * Preset values for [fields] the client may use to search by.
     */
    val fields: JsonObject,

    /**
     * A unique identifier across all instances.
     */
    @SerialName("network_id")
    val networkId: String
)
