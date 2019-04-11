import java.io.InputStream

fun InputStream.readAsString() = this.bufferedReader().use { it.readText() }