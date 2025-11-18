import fi.iki.elonen.NanoHTTPD

class SimpleWebServer(val port: Int) : NanoHTTPD(port) {
    var tempValue: String = "0.0"

    override fun serve(session: IHTTPSession): Response {
        return when (session.uri) {
            "/refresh" -> {
                tempValue = readTempFromArduino()
                newFixedLengthResponse(tempValue)
            }
            else -> {
                newFixedLengthResponse(getMainPage())
            }
        }
    }

    private fun getMainPage(): String {
        return """
<html>
<body>
<h1>Hytte Monitor</h1>
<p>Temperatur: <span id="temp">$tempValue</span> °C</p>
<button onclick="refreshTemp()">Refresh</button>
<script>
function refreshTemp() {
fetch('/refresh')
.then(response => response.text())
.then(data => document.getElementById('temp').innerText = data);
}
</script>
</body>
</html>
""".trimIndent()
    }

    private fun readTempFromArduino(): String {
// Her kommer Bluetooth-kode senere
        return "21.5" // dummyverdi foreløpig
    }
}