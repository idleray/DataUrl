package com.rayject.dataurl

import org.junit.Test

import org.junit.Assert.*

class GeneratorUnitTest {
    @Test
    fun test10() {
        val generator = Generator()
        val dataUrl = generator.generate("A brief note", false)

        assertEquals("data:,A%20brief%20note", dataUrl)
    }

    @Test
    fun test11() {
        val generator = Generator()
        val dataUrl = generator.generate("ΎάΏ", false, type="text/plain", charset="iso-8859-7")

        assertEquals("data:text/plain;charset=iso-8859-7,%BE%DC%BF", dataUrl)
    }

    @Test
    fun test12() {
        val generator = Generator()
        val dataUrl = generator.generate("select_vcount,fcol_from_fieldtable/local", false, type="application/vnd-xxx-query" )

        assertEquals("data:application/vnd-xxx-query,select_vcount%2Cfcol_from_fieldtable%2Flocal", dataUrl)
    }


    @Test
    fun test20() {
//        val expect = "data:image/png;base64,R0lGODdhMAAwAPAAAAAAAP///ywAAAAAMAAw" +
//                "AAAC8IyPqcvt3wCcDkiLc7C0qwyGHhSWpjQu5yqmCYsapyuvUUlvONmOZtfzgFz" +
//                "ByTB10QgxOR0TqBQejhRNzOfkVJ+5YiUqrXF5Y5lKh/DeuNcP5yLWGsEbtLiOSp" +
//                "a/TPg7JpJHxyendzWTBfX0cxOnKPjgBzi4diinWGdkF8kjdfnycQZXZeYGejmJl" +
//                "ZeGl9i2icVqaNVailT6F5iJ90m6mvuTS4OK05M0vDk0Q4XUtwvKOzrcd3iq9uis" +
//                "F81M1OIcR7lEewwcLp7tuNNkM3uNna3F2JQFo97Vriy/Xl4/f1cf5VWzXyym7PH" +
//                "hhx4dbgYKAAA7"
//        val generator = Generator()
//        val dataUrl = generator.generate("parserTest20.png")
//
//        assertEquals(expect, dataUrl)
    }
    @Test
    fun test21() {
        val generator = Generator()
        val dataUrl = generator.generate("Hello, World!", type="text/plain" )

        assertEquals("data:text/plain;base64,SGVsbG8sIFdvcmxkIQ==", dataUrl)
    }
    @Test
    fun test22() {
        val generator = Generator()
        val dataUrl = generator.generate("<h1>Hello, World!</h1>", false, type="text/html")

        assertEquals("data:text/html,%3Ch1%3EHello%2C%20World!%3C%2Fh1%3E", dataUrl)
    }
}
