import com.abacatepay.client.AbacatePayClient

suspend fun main() {
    val abacatePayClient = AbacatePayClient(apiKey = "your_key")
    abacatePayClient.listBillings()
}