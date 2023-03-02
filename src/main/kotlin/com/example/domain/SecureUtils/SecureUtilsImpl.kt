package com.example.domain.SecureUtils

import java.security.MessageDigest
import java.util.UUID

class SecureUtilsImpl : SecureUtils {
    override fun getHash(data: ByteArray): String {
        val digest: MessageDigest = MessageDigest.getInstance("SHA-512")
        val hashBytes: ByteArray = digest.digest(data)
        val hashString: StringBuilder = StringBuilder()

        for (aMessageDigest:Byte in hashBytes) {
            var h: String = Integer.toHexString(0xFF and aMessageDigest.toInt())
            while (h.length < 2)
                h = "0$h"
            hashString.append(h)
        }

        return hashString.toString()
    }

    override fun getHash(string: String): String {
        return getHash(string.toByteArray())
    }

    override fun generateSalt(): String {
        return getHash(UUID.randomUUID().toString()).dropLast(96)
    }

    override fun generateToken(): String {
         return getHash(UUID.randomUUID().toString())
    }
}