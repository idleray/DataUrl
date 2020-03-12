package com.rayject.dataurl

data class MimeMap(var type: String, var ext: String)

val MimeMaps = arrayOf(
    MimeMap("audio/aac", ".aac"),
    MimeMap("application/x-abiword", ".abw"),
    MimeMap("application/x-freearc", ".arc"),
    MimeMap("video/x-msvideo", ".avi"),
    MimeMap("application/vnd.amazon.ebook", ".azw"),
    MimeMap("application/octet-stream", ".bin"),
    MimeMap("image/bmp", ".bmp"),
    MimeMap("application/x-bzip", ".bz"),
    MimeMap("application/x-bzip2", ".bz2"),
    MimeMap("application/x-csh", ".csh"),
    MimeMap("text/css", ".css"),
    MimeMap("text/csv", ".csv"),
    MimeMap("application/msword", ".doc"),
    MimeMap("application/vnd.openxmlformats-officedocument.wordprocessingml.document", ".docx"),
    MimeMap("application/vnd.ms-fontobject", ".eot"),
    MimeMap("application/epub+zip", ".epub"),
    MimeMap("application/gzip", ".gz"),
    MimeMap("image/gif", ".gif"),
    MimeMap("text/html", ".html"),
    MimeMap("image/vnd.microsoft.icon", ".ico"),
    MimeMap("text/calendar", ".ics"),
    MimeMap("application/java-archive", ".jar"),
    MimeMap("image/jpeg", ".jpg"),
    MimeMap("text/javascript", ".js"),
    MimeMap("application/json", ".json"),
    MimeMap("application/ld+json", ".jsonld"),
    MimeMap("audio/midi audio/x-midi", ".mid"),
    MimeMap("text/javascript", ".mjs"),
    MimeMap("audio/mpeg", ".mp3"),
    MimeMap("video/mpeg", ".mpeg"),
    MimeMap("application/vnd.apple.installer+xml", ".mpkg"),
    MimeMap("application/vnd.oasis.opendocument.presentation", ".odp"),
    MimeMap("application/vnd.oasis.opendocument.spreadsheet", ".ods"),
    MimeMap("application/vnd.oasis.opendocument.text", ".odt"),
    MimeMap("audio/ogg", ".oga"),
    MimeMap("video/ogg", ".ogv"),
    MimeMap("application/ogg", ".ogx"),
    MimeMap("audio/opus", ".opus"),
    MimeMap("font/otf", ".otf"),
    MimeMap("image/png", ".png"),
    MimeMap("application/pdf", ".pdf"),
    MimeMap("application/php", ".php"),
    MimeMap("application/vnd.ms-powerpoint", ".ppt"),
    MimeMap("application/vnd.openxmlformats-officedocument.presentationml.presentation", ".pptx"),
    MimeMap("application/vnd.rar", ".rar"),
    MimeMap("application/rtf", ".rtf"),
    MimeMap("application/x-sh", ".sh"),
    MimeMap("image/svg+xml", ".svg"),
    MimeMap("application/x-shockwave-flash", ".swf"),
    MimeMap("application/x-tar", ".tar"),
    MimeMap("image/tiff", ".tiff"),
    MimeMap("video/mp2t", ".ts"),
    MimeMap("font/ttf", ".ttf"),
    MimeMap("text/plain", ".txt"),
    MimeMap("application/vnd.visio", ".vsd"),
    MimeMap("audio/wav", ".wav"),
    MimeMap("audio/webm", ".weba"),
    MimeMap("video/webm", ".webm"),
    MimeMap("image/webp", ".webp"),
    MimeMap("font/woff", ".woff"),
    MimeMap("font/woff2", ".woff2"),
    MimeMap("application/xhtml+xml", ".xhtml"),
    MimeMap("application/vnd.ms-excel", ".xls"),
    MimeMap("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet", ".xlsx"),
    MimeMap("application/xml", ".xml"),
    MimeMap("text/xml", ".xml"),
    MimeMap("application/vnd.mozilla.xul+xml", ".xul"),
    MimeMap("application/zip", ".zip"),
    MimeMap("video/3gpp", ".3gp"),
    MimeMap("audio/3gpp", ".3gp"),
    MimeMap("video/3gpp2", ".3g2"),
    MimeMap("audio/3gpp2", ".3g2"),
    MimeMap("application/x-7z-compressed", ".7z")
)

fun getExtension(type: String): String {
    return MimeMaps.find{
        it.type == type
    }?.ext ?: ""
}

fun getType(ext: String): String {
    return MimeMaps.find{
        it.ext == ext
    }?.type ?: ""
}

/**
 * Fetch from
 * https://developer.mozilla.org/en-US/docs/Web/HTTP/Basics_of_HTTP/MIME_types/Complete_list_of_MIME_types
  */
/*
1、 Open DevTools in chrome，and run code in console：
var element = document.getElementsByTagName("tbody")[0]
var all = []
for(var i = 0; i < element.children.length; i++) {
    var child = element.children[i]
    all.push({
        type: child.children[0].textContent,
        ext: child.children[2].textContent
    })

}
console.log(all)

2、Right-click the object and select "Store as global variable"
3、The console will print the new variable's name, for example:
temp1
4、Run code in console：
copy(temp1)
5、The data is in clipboard
 */
val MimeTypesJson = """[
    {
        "ext": ".aac",
        "type": "audio/aac"
    },
    {
        "ext": ".abw",
        "type": "application/x-abiword"
    },
    {
        "ext": ".arc",
        "type": "application/x-freearc"
    },
    {
        "ext": ".avi",
        "type": "video/x-msvideo"
    },
    {
        "ext": ".azw",
        "type": "application/vnd.amazon.ebook"
    },
    {
        "ext": ".bin",
        "type": "application/octet-stream"
    },
    {
        "ext": ".bmp",
        "type": "image/bmp"
    },
    {
        "ext": ".bz",
        "type": "application/x-bzip"
    },
    {
        "ext": ".bz2",
        "type": "application/x-bzip2"
    },
    {
        "ext": ".csh",
        "type": "application/x-csh"
    },
    {
        "ext": ".css",
        "type": "text/css"
    },
    {
        "ext": ".csv",
        "type": "text/csv"
    },
    {
        "ext": ".doc",
        "type": "application/msword"
    },
    {
        "ext": ".docx",
        "type": "application/vnd.openxmlformats-officedocument.wordprocessingml.document"
    },
    {
        "ext": ".eot",
        "type": "application/vnd.ms-fontobject"
    },
    {
        "ext": ".epub",
        "type": "application/epub+zip"
    },
    {
        "ext": ".gz",
        "type": "application/gzip"
    },
    {
        "ext": ".gif",
        "type": "image/gif"
    },
    {
        "ext": ".html",
        "type": "text/html"
    },
    {
        "ext": ".ico",
        "type": "image/vnd.microsoft.icon"
    },
    {
        "ext": ".ics",
        "type": "text/calendar"
    },
    {
        "ext": ".jar",
        "type": "application/java-archive"
    },
    {
        "ext": ".jpg",
        "type": "image/jpeg"
    },
    {
        "ext": ".js",
        "type": "text/javascript"
    },
    {
        "ext": ".json",
        "type": "application/json"
    },
    {
        "ext": ".jsonld",
        "type": "application/ld+json"
    },
    {
        "ext": ".mid",
        "type": "audio/midi audio/x-midi"
    },
    {
        "ext": ".mjs",
        "type": "text/javascript"
    },
    {
        "ext": ".mp3",
        "type": "audio/mpeg"
    },
    {
        "ext": ".mpeg",
        "type": "video/mpeg"
    },
    {
        "ext": ".mpkg",
        "type": "application/vnd.apple.installer+xml"
    },
    {
        "ext": ".odp",
        "type": "application/vnd.oasis.opendocument.presentation"
    },
    {
        "ext": ".ods",
        "type": "application/vnd.oasis.opendocument.spreadsheet"
    },
    {
        "ext": ".odt",
        "type": "application/vnd.oasis.opendocument.text"
    },
    {
        "ext": ".oga",
        "type": "audio/ogg"
    },
    {
        "ext": ".ogv",
        "type": "video/ogg"
    },
    {
        "ext": ".ogx",
        "type": "application/ogg"
    },
    {
        "ext": ".opus",
        "type": "audio/opus"
    },
    {
        "ext": ".otf",
        "type": "font/otf"
    },
    {
        "ext": ".png",
        "type": "image/png"
    },
    {
        "ext": ".pdf",
        "type": "application/pdf"
    },
    {
        "ext": ".php",
        "type": "application/php"
    },
    {
        "ext": ".ppt",
        "type": "application/vnd.ms-powerpoint"
    },
    {
        "ext": ".pptx",
        "type": "application/vnd.openxmlformats-officedocument.presentationml.presentation"
    },
    {
        "ext": ".rar",
        "type": "application/vnd.rar"
    },
    {
        "ext": ".rtf",
        "type": "application/rtf"
    },
    {
        "ext": ".sh",
        "type": "application/x-sh"
    },
    {
        "ext": ".svg",
        "type": "image/svg+xml"
    },
    {
        "ext": ".swf",
        "type": "application/x-shockwave-flash"
    },
    {
        "ext": ".tar",
        "type": "application/x-tar"
    },
    {
        "ext": ".tiff",
        "type": "image/tiff"
    },
    {
        "ext": ".ts",
        "type": "video/mp2t"
    },
    {
        "ext": ".ttf",
        "type": "font/ttf"
    },
    {
        "ext": ".txt",
        "type": "text/plain"
    },
    {
        "ext": ".vsd",
        "type": "application/vnd.visio"
    },
    {
        "ext": ".wav",
        "type": "audio/wav"
    },
    {
        "ext": ".weba",
        "type": "audio/webm"
    },
    {
        "ext": ".webm",
        "type": "video/webm"
    },
    {
        "ext": ".webp",
        "type": "image/webp"
    },
    {
        "ext": ".woff",
        "type": "font/woff"
    },
    {
        "ext": ".woff2",
        "type": "font/woff2"
    },
    {
        "ext": ".xhtml",
        "type": "application/xhtml+xml"
    },
    {
        "ext": ".xls",
        "type": "application/vnd.ms-excel"
    },
    {
        "ext": ".xlsx",
        "type": "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"
    },
    {
        "ext": ".xml",
        "type": "application/xml"
    },
    {
        "ext": ".xml",
        "type": "text/xml"
    },
    {
        "ext": ".xul",
        "type": "application/vnd.mozilla.xul+xml"
    },
    {
        "ext": ".zip",
        "type": "application/zip"
    },
    {
        "ext": ".3gp",
        "type": "video/3gpp"
    },
    {
        "ext": ".3gp",
        "type": "audio/3gpp"
    },
    {
        "ext": ".3g2",
        "type": "video/3gpp2"
    },
    {
        "ext": ".3g2",
        "type": "audio/3gpp2"
    },
    {
        "ext": ".7z",
        "type": "application/x-7z-compressed"
    }
]"""