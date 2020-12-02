package com.superherosquadmaker.core

import okhttp3.internal.and
import java.security.MessageDigest

fun String.toMd5(): String {
    val md5Encoder: MessageDigest = MessageDigest.getInstance("MD5")
    val md5Bytes: ByteArray = md5Encoder.digest(this.toByteArray())
    val md5 = StringBuilder()

    for (i in md5Bytes.indices) {
        md5.append(Integer.toHexString(md5Bytes[i] and 0xFF or 0x100).substring(1, 3))
    }

    return md5.toString()
}