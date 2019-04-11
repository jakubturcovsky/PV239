import java.util.concurrent.atomic.AtomicInteger

fun <T, S> Iterable<T?>.mixEach(other: Iterable<S?>, action : (T?, S?) -> Unit) {
    forEach {src: T? ->
        other.forEach {
            action(src, it)
        }
    }
}

fun <T> List<T>.indefiniteIterator() = CarouselIterator<T>(this)

class CarouselIterator<T>(val list: List<T>): Iterator<T> {

    private val pos = AtomicInteger()

    override fun hasNext(): Boolean = list.isNotEmpty()

    override fun next(): T = synchronized(this) {
        if (list.isEmpty()) throw ArrayIndexOutOfBoundsException(pos.get())

        val actualPos = pos.getAndIncrement()
        if (actualPos < list.size) {
            return list[actualPos]
        } else {
            pos.set(0)
            return list[0]
        }
    }

}