package my.ym.data_articles.models

enum class ApiDayPeriodOfMostPopularArticles(val numOfDays: Int) {
	LastDay(1),
	LastWeek(7),
	LastMonth(30),
}
