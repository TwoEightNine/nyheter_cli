package global.msnthrp.nyheter.utils

import android.util.Base64
import java.math.BigInteger
import java.security.MessageDigest
import java.security.SecureRandom
import java.util.*

/**
 * Created by twoeightnine on 2/6/18.
 */

fun toBase64(bytes: ByteArray): String = Base64.encodeToString(bytes, Base64.NO_WRAP)

fun fromBase64(text: String) = Base64.decode(text, Base64.NO_WRAP)