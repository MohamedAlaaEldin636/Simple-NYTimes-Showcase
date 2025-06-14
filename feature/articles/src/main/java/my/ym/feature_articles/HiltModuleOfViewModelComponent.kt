package my.ym.feature_articles

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import my.ym.domain_articles.models.AppSnapshotOfArticles
import my.ym.domain_articles.models.AppViewedArticle
import my.ym.domain_articles.repos.RepoArticles
import my.ym.domain_shared.models.AppResult
import java.time.LocalDate
import java.time.LocalDateTime
import kotlin.time.Duration.Companion.seconds

@Module
@InstallIn(ViewModelComponent::class)
data object HiltModuleOfViewModelComponent {

	private val fakeRepoArticles get() = object : RepoArticles {
		override fun getMostPopularViewedArticles(periodInDays: Int): Flow<AppResult<AppSnapshotOfArticles>> {
			return flow {
				emit(AppResult.Loading())

				delay(5.seconds)

				val dummyArticles = List(size = 20) {
					AppViewedArticle(
						id = it.inc().toLong(),
						title = "Title $it",
						summary = "Summary $it",
						section = "Section $it",
						byLine = "By Name $it",
						publishedAt = LocalDate.now().minusDays(it.inc().toLong()),
						lastUpdatedAt = LocalDateTime.now().minusDays(it.toLong()),
						keywords = List(size = 10) { innerIt -> "Keyword $innerIt" },
						media = emptyList()
					)
				}

				emit(
					AppResult.Success(
						data = AppSnapshotOfArticles(
							fetchedFromApiAt = LocalDateTime.now(),
							fetchFailureReason = null,
							articles = dummyArticles
						)
					)
				)
			}
		}
	}

	@Provides
	fun provideRepoArticles(): RepoArticles {
		return fakeRepoArticles
	}

}