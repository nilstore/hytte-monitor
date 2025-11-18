package com.example.hyttemonitor

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import fi.iki.elonen.NanoHTTPD


class MainActivity : AppCompatActivity() {

    // Webserveren
    lateinit var server: SimpleWebServer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

// Start server på port 8080
        server = SimpleWebServer(8080)
        server.start()
        Toast.makeText(this, "Server startet på port 8080", Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        server.stop() // Stopper server når appen lukkes
    }

    // Enkel webserver med login og Refresh-knapp
    inner class SimpleWebServer(port: Int) : NanoHTTPD(port) {
        private val PASSWORD = "1234" // Bytt til eget passord
        var tempValue: String = "0.0"

        override fun serve(session: IHTTPSession): Response {
            val params = session.parms
            val uri = session.uri

            return when (uri) {
                "/" -> {
// Login side
                    newFixedLengthResponse(
                        """
<html>
<body>
<h2>Hytte Monitor Login</h2>
<form method="get" action="/monitor">
Passord: <input type="password" name="pass">
<input type="submit" value="Login">
</form>
</body>
</html>
""".trimIndent()
                    )
                }
                "/monitor" -> {
                    val pass = params["pass"] ?: ""
                    if (pass != PASSWORD) {
                        newFixedLengthResponse("Feil passord!")
                    } else {
                        newFixedLengthResponse(getMonitorPage())
                    }
                }
                "/refresh" -> {
                    tempValue = readTempFromArduino() // Foreløpig dummy
                    newFixedLengthResponse(tempValue)
                }
                else -> newFixedLengthResponse("Side ikke funnet")
            }
        }

        private fun getMonitorPage(): String {
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
// Foreløpig dummy-verdi
            return "21.5"
        }
    }
}