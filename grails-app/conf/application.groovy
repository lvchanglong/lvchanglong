html.title.suffix = ' | &#x5415;&#x5E38;&#x9F99;'

grails {
    controllers {
        upload {
            maxFileSize = 1000000
            maxRequestSize = 1000000
        }
    }
    mime {
        types {
            xls = "application/vnd.ms-excel"
            xlsx = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"
        }
    }
}