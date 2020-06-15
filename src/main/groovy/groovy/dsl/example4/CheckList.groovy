package groovy.dsl.example4

import groovy.xml.MarkupBuilder


class CheckList {

    String suite
    String specification
    String feature
    String description
    String toFile = null
    Map order
    Map checklist
    def sections = []

    def static make(closure) {
        CheckList checklistDsl = new CheckList()
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

    def order(Map orderMap) {
        this.order = orderMap
    }

    def checklist(Map checklistMap) {
        this.checklist = checklistMap
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

    def getHtml() {
        def _html = doHtml(this)
        if(toFile) writeFile(toFile, _html)
        _html
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

    private static doHtml(CheckList checklist) {
        def writer = new StringWriter()
        def xml = new MarkupBuilder(writer)
        xml.html() {
            head {
                title("CheckList")
            }
            body {
                h1("CheckList")
                p("Suite: ${checklist.suite}")
                p("Specification: ${checklist.specification}")
                p("Feature: ${checklist.feature}")
                p(checklist.description)
                br()
                h3('General Data')
                for (s in checklist.sections) {
                    p("${s.title} - ${s.body}")
                }
                br()
                h3('Your Order')
                checklist.order.each { key, value ->
                    p { b(key) }
                    if(value instanceof List) {
                        ol { value.each { li(it) } }
                    } else if(value instanceof Map) {
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
                br()
                h3('Check List')
                checklist.checklist.each { key, value ->
                    p { b(key) }
                    if(value instanceof List) {
                        ol { value.each { li(it) } }
                    } else if(value instanceof Map) {
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
}
