import java.io.File

data class Message(val address: String?, val topic: String?, val message: String?, val htmlFile: File) {
    fun toHTML() {
        var template = (
                "<head>" +
                "<style>" +
                "body {" +
                "font-size: 20px;" +
                "}" +
                "table {" +
                "margin: 20% auto;" +
                "background: black;" +
                "width: 100%;" +
                "padding: 6px;" +
                "}" +
                "td {" +
                "background: gray;" +
                "margin: 0 auto;" +
                "padding: 12px;" +
                "}" +
                "</style>" +
                "</head>" +
                "<body>" +
                "<table>"
        )
        with(address) {
            if (!address.isNullOrBlank()) {
                template += "<tr><td>Address:</td><td>$address</td></tr>"
            }
        }
        with(topic) {
            if (!topic.isNullOrBlank()) {
                template += "<tr><td>Topic:</td><td>$topic</td></tr>"
            }
        }
        with(message) {
            if (!message.isNullOrBlank()) {
                template += "<tr><td>Message:</td><td>$message</td></tr>"
            }
        }
        template += "</table>" + "</body>"
        htmlFile.writeText(template)
    }
}

fun main() {
    val f = File("index.html")
    val m = Message("test@test.com", "Test", "Test", f)
    println(m.toHTML())
}