import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.io.PrintWriter
import java.net.Socket

fun main() {
    val serverAddress = "127.0.0.1"
    val serverPort = 80

    try {
        val socket = Socket(serverAddress, serverPort)

        // Enviar requisição HTTP
        val outputStream = PrintWriter(OutputStreamWriter(socket.getOutputStream()), true)
        outputStream.println("GET / HTTP/1.1")
        outputStream.println("Host: $serverAddress")
        outputStream.println("Connection: close")
        outputStream.println() // Linha em branco obrigatória para finalizar o cabeçalho HTTP

        // Ler a resposta do servidor
        val inputStream = BufferedReader(InputStreamReader(socket.getInputStream()))
        println("Resposta do servidor:")
        var responseLine: String?
        while (inputStream.readLine().also { responseLine = it } != null) {
            println(responseLine)
        }

        // Fechar recursos
        inputStream.close()
        outputStream.close()
        socket.close()
    } catch (e: Exception) {
        println("Erro ao conectar ao servidor: ${e.message}")
    }
}
