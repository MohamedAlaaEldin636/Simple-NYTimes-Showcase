package my.ym.data_articles.models.utils

import my.ym.data_articles.models.ApiDayPeriodOfMostPopularArticles
import my.ym.domain_articles.models.AppDayPeriodOfMostPopularArticles
import my.ym.domain_articles.models.AppMedia

internal fun ApiDayPeriodOfMostPopularArticles.toAppDayPeriodOfMostPopularArticles(): AppDayPeriodOfMostPopularArticles {
	return when (this) {
		ApiDayPeriodOfMostPopularArticles.LastDay -> AppDayPeriodOfMostPopularArticles.LastDay
		ApiDayPeriodOfMostPopularArticles.LastWeek -> AppDayPeriodOfMostPopularArticles.LastWeek
		ApiDayPeriodOfMostPopularArticles.LastMonth -> AppDayPeriodOfMostPopularArticles.LastMonth
	}
}

internal fun AppDayPeriodOfMostPopularArticles.toApiDayPeriodOfMostPopularArticles(): ApiDayPeriodOfMostPopularArticles {
	return when (this) {
		AppDayPeriodOfMostPopularArticles.LastDay -> ApiDayPeriodOfMostPopularArticles.LastDay
		AppDayPeriodOfMostPopularArticles.LastWeek -> ApiDayPeriodOfMostPopularArticles.LastWeek
		AppDayPeriodOfMostPopularArticles.LastMonth -> ApiDayPeriodOfMostPopularArticles.LastMonth
	}
}
