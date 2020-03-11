fun <T> List<T>.combine(withOrder: Boolean = false): List<Pair<T, T>> {
    val pairs = arrayListOf<Pair<T, T>>()
    for(id in 0 until size) {
        for (id2 in id + 1 until size) {
            pairs.add(this[id] to this[id2])
            if (withOrder) {
                pairs.add(this[id2] to this[id])
            }
        }
    }
    return pairs
}