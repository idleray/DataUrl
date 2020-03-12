package com.rayject.dataurl

import java.io.File
import java.net.MalformedURLException
import java.nio.charset.Charset

class Mime {
    var type: String = "text"
    var subType: String = "plain"
    var charset: String = "US-ASCII"
}

//https://tools.ietf.org/html/rfc2397
class Parser(private val dataUrl: String) {
    var mediaType = Mime()
    var base64: Boolean = false
    var data: String = ""
    private var decodedData: String = ""

    init {
        if(!parse()) {
            throw MalformedURLException("invalid data url: $dataUrl")
        }
    }

    fun getData(decoded: Boolean = true): String {
        if(decoded) {
            return decode()
        } else {
            return data
        }
    }

    fun getDecodedBase64Data(): ByteArray {
        return Base64.decode(data, Base64.DEFAULT)
    }

    /**
     * @return extension of file name
     */
    fun getFileNameExtension(): String {
        val type = "${mediaType.type}/${mediaType.subType}"
        return getExtension(type)
    }

    fun saveToFile(filePath: String) {
        val file = File(filePath)
        val parent = file.parentFile
        if(parent != null && !parent.exists()) {
            parent.mkdirs()
        }
        if(!file.exists()) {
            file.createNewFile()
        }
        file.outputStream().use {
            if(base64) {
                it.write(getDecodedBase64Data())
            } else {
                it.write(getData().toByteArray())
            }
        }

    }

    private fun parse(): Boolean {
        if(dataUrl.length < 0) {
            return false
        }
        var start = 0
        var end = dataUrl.length
        while(dataUrl[start].isWhitespace()) {
            start++
        }
        while(dataUrl[end-1].isWhitespace()) {
            end--
        }
        if(start >= end) {
            return false
        }

        //Parse scheme
        if(!dataUrl.regionMatches(start, "data:", 0, 5, true)) {
            return false
        }
        start += 5

        val commaIndex = dataUrl.indexOf(",")
        if(commaIndex == -1) {
            return false
        }

        if(start < commaIndex) {
            val header = dataUrl.substring(start, commaIndex)
            val splitArr = header.split(";")
            setType(splitArr[0])
            splitArr.forEach {
                if(it.startsWith("charset=", true)) {
                    setCharset(it)
                } else if(it.startsWith("base64", true)) {
                    base64 = true
                }
            }
        }

        data = dataUrl.substring(commaIndex + 1)

        return true
    }

    private fun setType(typeStr: String) {
        val types = typeStr.split("/")
        mediaType.type = types[0]
        mediaType.subType = if(types.size > 1) types[1] else ""
    }

    private fun setCharset(paraStr: String) {
        val parameter = paraStr.split("=")
        mediaType.charset = if (parameter.size > 1) parameter[1] else ""

    }

    private fun decode(): String {
        if(decodedData.isEmpty()) {
            if(base64) {
                val decodeByte = Base64.decode(data, Base64.DEFAULT)
                decodedData = decodeByte.toString(Charset.forName(mediaType.charset))
            } else {
                decodedData = UriCodec.decode(data, mediaType.charset)
            }
        }

        return decodedData
    }
}