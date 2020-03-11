fun <T> T?.ifNull(nonNull: (T) -> Unit = {}, nullVal: () -> Unit): T? {
    if (this == null) nullVal() else nonNull(this)
    return this
}