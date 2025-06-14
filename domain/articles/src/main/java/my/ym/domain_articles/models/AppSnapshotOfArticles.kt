package my.ym.domain_articles.models

import my.ym.domain_shared.models.AppResult
import java.time.LocalDateTime

data class AppSnapshotOfArticles(
	val fetchedFromApiAt: LocalDateTime,
	val fetchFailureReason: AppResult.Failure.Reason?,
	val articles: List<AppViewedArticle>,
) {
	val usedCache: Boolean get() = fetchFailureReason != null && articles.isNotEmpty()
}
