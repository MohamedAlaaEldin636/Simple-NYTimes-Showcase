package my.ym.core_kotlin

inline fun <reified T, R> List<T>.toPairOfSameTypeOrNull(
	startingIndex: Int = 0,
	conversion: (T) -> R?,
): Pair<R, R>? {
	var index = startingIndex
	return Pair(
		conversion(getOrNull(index = index++) ?: return null) ?: return null,
		conversion(getOrNull(index = index++) ?: return null) ?: return null,
	)
}
