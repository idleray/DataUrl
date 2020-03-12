package com.rayject.dataurl

import java.io.File
import java.nio.charset.Charset

class Generator {

    fun generate(filePath: String): String {
        val file = File(filePath)
        return generate(file)
    }

    fun generate(file: File, base64: Boolean = true, charset: String = ""): String {
        val ext = file.extension
        val type = getType(".$ext")

        val dataByte = file.readBytes()
        return generate(dataByte, base64, type, charset)
    }

    fun generate(dataByte: ByteArray, base64: Boolean = true, type: String = "", charset: String = ""): String {
        val encodeData = if(base64) {
            Base64.encode(dataByte, Base64.NO_WRAP).toString(Charset.forName("US-ASCII"))
        } else {
            val dataCharset = if(charset.isNotEmpty()) charset else "US-ASCII"
//            URLEncoder.encode(dataByte.toString(Charset.forName(dataCharset)), dataCharset)
            UriCodec.encode(dataByte.toString(Charset.forName(dataCharset)), dataCharset)
        }
        return generateDataUrl(encodeData, base64, type, charset)
    }

    fun generate(data: String, base64: Boolean = true, type: String = "", charset: String = ""): String {
        val dataCharset = if(charset.isNotEmpty()) charset else "US-ASCII"
        val encodeData = if(base64) {
            Base64.encode(data.toByteArray(Charset.forName(dataCharset)), Base64.NO_WRAP).toString(Charset.forName("US-ASCII"))
        } else {
//            URLEncoder.encode(data, dataCharset)
            UriCodec.encode(data, dataCharset)
        }
        return generateDataUrl(encodeData, base64, type, charset)
    }

    private fun generateDataUrl(data: String, base64: Boolean = true, type: String = "", charset: String = ""): String {
        var sb = StringBuffer("data:")
        sb.run {

            append(type)
            if(charset.isNotEmpty()) {
                append(";")
                append("charset=$charset")
            }

            if(base64) {
                append(";")
                append("base64")
            }

            append(",")
            append(data)
        }

        return sb.toString()
    }

}