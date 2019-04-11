fun String.firstLetterUpper(): String {
    if (isNullOrEmpty()) return this
    if (length == 1) return this[0].toUpperCase().toString()
    return this[0].toUpperCase() + substring(1)
}