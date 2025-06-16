package my.ym.core_kotlin

inline fun <T> T?.executeIfNotNull(block: T.() -> Unit) {
	if (this != null) {
		block()
	}
}
