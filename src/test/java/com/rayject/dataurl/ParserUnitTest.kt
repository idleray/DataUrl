package com.rayject.dataurl

import org.junit.Test

import org.junit.Assert.*

class ParserUnitTest {
    @Test
    fun test0() {
        val dataUrl = "data:,"
        val parser = Parser(dataUrl)
        val mediaType = parser.mediaType
        val data = parser.getData(false)

        assertEquals("text", mediaType.type)
        assertEquals("plain", mediaType.subType)
        assertEquals(false, parser.base64)
        assertEquals(".txt", parser.getFileNameExtension())
        assertEquals("", data)
    }

    @Test
    fun test10() {
        val dataUrl = "data:,A%20brief%20note"
        val parser = Parser(dataUrl)
        val mediaType = parser.mediaType
        val data = parser.getData(false)

        assertEquals("text", mediaType.type)
        assertEquals("plain", mediaType.subType)
        assertEquals(false, parser.base64)
        assertEquals(".txt", parser.getFileNameExtension())
        assertEquals("A%20brief%20note", data)
    }

    @Test
    fun test11() {
        val dataUrl = "data:text/plain;charset=iso-8859-7,%be%dc%bf"
        val parser = Parser(dataUrl)
        val mediaType = parser.mediaType
        val data = parser.getData(true)

        assertEquals("text", mediaType.type)
        assertEquals("plain", mediaType.subType)
        assertEquals("iso-8859-7", mediaType.charset)
        assertEquals(false, parser.base64)
        assertEquals("ΎάΏ", data)
    }

    @Test
    fun test12() {
        val dataUrl = "data:application/vnd-xxx-query,select_vcount,fcol_from_fieldtable/local"
        val parser = Parser(dataUrl)
        val mediaType = parser.mediaType
        val data = parser.getData(false)

        assertEquals("application", mediaType.type)
        assertEquals("vnd-xxx-query", mediaType.subType)
        assertEquals("US-ASCII", mediaType.charset)
        assertEquals(false, parser.base64)
        assertEquals("select_vcount,fcol_from_fieldtable/local", data)
    }


    @Test
    fun test20() {
        val dataUrl = "data:image/png;base64,R0lGODdhMAAwAPAAAAAAAP///ywAAAAAMAAw\n" +
                "AAAC8IyPqcvt3wCcDkiLc7C0qwyGHhSWpjQu5yqmCYsapyuvUUlvONmOZtfzgFz\n" +
                "ByTB10QgxOR0TqBQejhRNzOfkVJ+5YiUqrXF5Y5lKh/DeuNcP5yLWGsEbtLiOSp\n" +
                "a/TPg7JpJHxyendzWTBfX0cxOnKPjgBzi4diinWGdkF8kjdfnycQZXZeYGejmJl\n" +
                "ZeGl9i2icVqaNVailT6F5iJ90m6mvuTS4OK05M0vDk0Q4XUtwvKOzrcd3iq9uis\n" +
                "F81M1OIcR7lEewwcLp7tuNNkM3uNna3F2JQFo97Vriy/Xl4/f1cf5VWzXyym7PH\n" +
                "hhx4dbgYKAAA7"
        val parser = Parser(dataUrl)
        val mediaType = parser.mediaType
//        parser.saveToFile("parserTest20${parser.getFileNameExtension()}")

        assertEquals("image", mediaType.type)
        assertEquals("png", mediaType.subType)
        assertEquals(".png", parser.getFileNameExtension())
        assertEquals(true, parser.base64)
    }
    @Test
    fun test21() {
        val dataUrl = "data:text/plain;base64,SGVsbG8sIFdvcmxkIQ"
        val parser = Parser(dataUrl)
        val mediaType = parser.mediaType

        assertEquals("text", mediaType.type)
        assertEquals("plain", mediaType.subType)
        assertEquals(true, parser.base64)
        assertEquals("Hello, World!", parser.getData(true))
    }
    @Test
    fun test22() {
        val dataUrl = "data:text/html,%3Ch1%3EHello%2C%20World!%3C%2Fh1%3E"
        val parser = Parser(dataUrl)
        val mediaType = parser.mediaType

        assertEquals("text", mediaType.type)
        assertEquals("html", mediaType.subType)
        assertEquals(".html", parser.getFileNameExtension())
        assertEquals(false, parser.base64)
        assertEquals("<h1>Hello, World!</h1>", parser.getData(true))
    }
}
