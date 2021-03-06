package io.github.matrixkt.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MSISDNValidationRequest(
    /**
     * A unique string generated by the client, and used to identify the validation attempt.
     * It must be a string consisting of the characters `[0-9a-zA-Z.=_-]`.
     * Its length must not exceed 255 characters and it must not be empty.
     */
    @SerialName("client_secret")
    val clientSecret: String,

    /**
     * The two-letter uppercase ISO country code that the number in [phoneNumber] should be parsed as if it were dialled from.
     */
    val country: String,

    /**
     * The phone number to validate.
     */
    @SerialName("phone_number")
    val phoneNumber: String,

    /**
     * The server will only send an email if the [sendAttempt] is a number greater than
     * the most recent one which it has seen, scoped to that email + client_secret pair.
     * This is to avoid repeatedly sending the same email in the case of request retries
     * between the POSTing user and the identity server.
     * The client should increment this value if they desire a new email (e.g. a reminder) to be sent.
     * If they do not, the server should respond with success but not resend the email.
     */
    @SerialName("send_attempt")
    val sendAttempt: Int,

    /**
     * Optional. When the validation is completed, the identity server will redirect the user to this URL.
     * This option is ignored when submitting 3PID validation information through a POST request.
     */
    @SerialName("next_link")
    val nextLink: String? = null,

    /**
     * The hostname of the identity server to communicate with.
     * May optionally include a port.
     * This parameter is ignored when the homeserver handles 3PID verification.
     */
    @SerialName("id_server")
    val idServer: String
)
