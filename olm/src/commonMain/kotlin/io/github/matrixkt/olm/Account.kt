package io.github.matrixkt.olm

import kotlin.random.Random


/**
 * Account class used to create Olm sessions in conjunction with [Session] class.
 *
 * [Account] provides APIs to retrieve the Olm keys.
 *
 * Detailed implementation guide is available at [Implementing End-to-End Encryption in Matrix clients](http://matrix.org/docs/guides/e2e_implementation.html).
 */
expect class Account(random: Random = Random.Default) {
    fun clear()

    /**
     * Return the identity keys (identity and fingerprint keys) in a dictionary.
     * @sample
     * {
     *  "curve25519":"Vam++zZPMqDQM6ANKpO/uAl5ViJSHxV9hd+b0/fwRAg",
     *  "ed25519":"+v8SOlOASFTMrX3MCKBM4iVnYoZ+JIjpNt1fi8Z9O2I"
     * }
     * @return identity keys dictionary if operation succeeds, null otherwise
     */
    val identityKeys: Map<String, String>

    /**
     * Return the largest number of "one time keys" this account can store.
     * @return the max number of "one time keys", -1 otherwise
     */
    val maxNumberOfOneTimeKeys: Long

    /**
     * Generate a number of new one time keys.
     * If total number of keys stored by this account exceeds [maxNumberOfOneTimeKeys],
     * the old keys are discarded.
     * The corresponding keys are retrieved by [oneTimeKeys].
     * @param numberOfKeys number of keys to generate
     */
    fun generateOneTimeKeys(numberOfKeys: Long, random: Random = Random.Default)

    /**
     * Return the "one time keys" in a dictionary.
     * The number of "one time keys", is specified by [generateOneTimeKeys]
     * @sample
     * { "curve25519":
     *  {
     *      "AAAABQ":"qefVZd8qvjOpsFzoKSAdfUnJVkIreyxWFlipCHjSQQg",
     *      "AAAABA":"/X8szMU+p+lsTnr56wKjaLgjTMQQkCk8EIWEAilZtQ8",
     *      "AAAAAw":"qxNxxFHzevFntaaPdT0fhhO7tc7pco4+xB/5VRG81hA",
     *  }
     * }
     * Note: these keys are to be published on the server.
     * @return one time keys in string dictionary.
     */
    val oneTimeKeys: Map<String, Map<String, String>>

    /**
     * Remove the "one time keys" that the session used from the account.
     * @param session session instance
     */
    fun removeOneTimeKeys(session: Session)

    /**
     * Marks the current set of "one time keys" as being published.
     */
    fun markOneTimeKeysAsPublished()

    /**
     * Sign a message with the ed25519 fingerprint key for this account.
     *
     * The signed message is returned by the method.
     * @param message message to sign
     * @return the signed message
     */
    fun sign(message: String): String

    /**
     * Return an account as a bytes buffer.
     *
     * The account is serialized and encrypted with [key].
     *
     * @param[key] encryption key
     * @return the account as bytes buffer
     */
    fun pickle(key: ByteArray): String

    companion object {
        /**
         * Loads an account from a pickle.
         *
         * @see[pickle]
         * @param[key] key used to encrypt
         * @param[pickle] Base64 pickle
         * @exception Exception the exception
         */
        fun unpickle(key: ByteArray, pickle: String): Account
    }
}
