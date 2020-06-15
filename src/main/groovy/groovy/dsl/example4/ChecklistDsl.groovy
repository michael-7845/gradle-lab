package groovy.dsl.example4

import groovy.xml.MarkupBuilder


class ChecklistDsl {

    String suite
    String specification
    String feature
    String description
    String toFile = null
    Map content
    def sections = []

    def static make(closure) {
        ChecklistDsl checklistDsl = new ChecklistDsl()
        closure.delegate = checklistDsl
        closure()
    }

    def suite(String suiteText) {
        this.suite = suiteText
    }

    def specification(String specificationText) {
        this.specification = specificationText
    }

    def feature(String featureText) {
        this.feature = featureText
    }

    def description(String descriptionText) {
        this.description = descriptionText
    }

    def content(Map contentMap) {
        this.content = contentMap
    }

    def toFile(String toFileText) {
        this.toFile = toFileText
    }

    static class Section {
        def title
        def body
    }

    def methodMissing(String methodName, args) {
        def section = new Section(title: methodName, body: args[0])
        sections << section
    }

    def getXml() {
        def _xml = doXml(this)
        if(toFile) writeFile(toFile, _xml)
        _xml
    }

    def getHtml() {
        def _html = doHtml(this)
        if(toFile) writeFile(toFile, _html)
        _html
    }

    def getText() {
        def _text = doText(this)
        if(toFile) writeFile(toFile, _text)
        _text
    }

    static def writeFile(fileName, content) {
        def file = new File(fileName)
        if (file.exists()) file.delete()
        println file.absolutePath
        def printWriter = file.newPrintWriter()
        printWriter.write(content)
        printWriter.flush()
        printWriter.close()
    }

    private static doXml(ChecklistDsl checklistDsl) {
        def writer = new StringWriter()
        def xml = new MarkupBuilder(writer)
        xml.checklist() {
            suite(checklistDsl.suite)
            specification(checklistDsl.specification)
            feature(checklistDsl.feature)
            description(checklistDsl.description)
//            "Check List" { checklistDsl.content.each { key, value ->
//                "$key" value
//            } }
            for (s in checklistDsl.sections) {
                "$s.title"(s.body)
            }
        }
        writer.toString()
    }

    private static doHtml(ChecklistDsl checklistDsl) {
        def writer = new StringWriter()
        def xml = new MarkupBuilder(writer)
        xml.html() {
            head {
                title("CheckList")
            }
            body {
                h1("CheckList")
                h2("Suite: ${checklistDsl.suite}")
                h3("Specification: ${checklistDsl.specification}")
                h4("Feature: ${checklistDsl.feature}")
                p(checklistDsl.description)
                for (s in checklistDsl.sections) {
                    p {
                        b(s.title.toUpperCase())
                    }
                    p(s.body)
                }

                table(border: '1') {
                    for (s in checklistDsl.sections) {
                        tr {
                            td(s.title.toUpperCase())
                            td(s.body)
                        }
                    }
                }

                checklistDsl.content.each { key, value ->
                    p {
                        b(key)
                    }

                    if(value instanceof List) {
                        value.each { p(it) }
                    } else if(value instanceof Map) {
//                        table(width: "100%", border: "0", cellspacing: "0", cellpadding: "0", align: "center", ) {
                        table(border: '1', cellspacing: '1', cellpadding: '0') {
                            value.each { key2, value2 ->
                                tr {
                                    td(key2)
                                    td(value2)
                                }
                            }
                        }
                    } else {
                        p(value)
                    }

                }
            }
        }
        writer.toString()
    }

    private static doText(ChecklistDsl checklistDsl) {
        String template = "Checklist\nSuite: ${checklistDsl.suite}\nSpecification: ${checklistDsl.specification}\n" +
                "Feature: ${checklistDsl.feature}\n${checklistDsl.description}\n"
        def sectionStrings =""
        for (s in checklistDsl.sections) {
            sectionStrings += s.title.toUpperCase() + "\n" + s.body + "\n"
        }
        template += sectionStrings
        template
    }
}
