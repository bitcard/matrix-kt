package io.github.matrixkt.models.events.contents.msginfo

import io.github.matrixkt.models.EncryptedFile
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FileInfo(
    /**
     * The mimetype of the file e.g. `application/msword`.
     */
    @SerialName("mimetype")
    val mimeType: String? = null,

    /**
     * Size of the file in bytes.
     */
    val size: Long? = null,

    /**
     * 	The URL (typically [MXC URI](https://matrix.org/docs/spec/client_server/r0.5.0#mxc-uri)) to a thumbnail of the file.
     * 	Only present if the thumbnail is unencrypted.
     */
    @SerialName("thumbnail_url")
    val thumbnailUrl: String? = null,

    /**
     * Information on the encrypted thumbnail file, as specified in [End-to-end encryption](https://matrix.org/docs/spec/client_server/r0.5.0#encrypted-files).
     * Only present if the thumbnail is encrypted.
     */
    @SerialName("thumbnail_file")
    val thumbnailFile: EncryptedFile? = null,

    /**
     * Metadata about the image referred to in [thumbnailUrl].
     */
    @SerialName("thumbnail_info")
    val thumbnailInfo: ThumbnailInfo? = null
)
