package my.ym.domain_articles.models

enum class AppDayPeriodOfMostPopularArticles(val numOfDays: Int) {
	LastDay(1),
	LastWeek(7),
	LastMonth(30),
}
