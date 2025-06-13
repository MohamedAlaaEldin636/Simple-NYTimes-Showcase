package my.ym.core_kotlin

inline fun <reified T, R> List<T>.toTripleOfSameTypeOrNull(
	startingIndex: Int = 0,
	conversion: (T) -> R?,
): Triple<R, R, R>? {
	var index = startingIndex
	return Triple(
		conversion(getOrNull(index = index++) ?: return null) ?: return null,
		conversion(getOrNull(index = index++) ?: return null) ?: return null,
		conversion(getOrNull(index = index++) ?: return null) ?: return null,
	)
}

class TripleUtils
