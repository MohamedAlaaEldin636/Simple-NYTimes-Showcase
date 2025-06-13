package my.ym.domain_articles

import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.single
import my.ym.domain_articles.models.AppSnapshotOfArticles
import my.ym.domain_articles.useCases.GetMostPopularViewedArticlesUseCase
import my.ym.domain_shared.models.AppResult
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.assertInstanceOf

/** Or run ./gradlew :domain:articles:test */
@ExtendWith(MockKExtension::class)
@MockKExtension.ConfirmVerification
class TestGetMostPopularViewedArticlesUseCase {

	@MockK
	private lateinit var useCase: GetMostPopularViewedArticlesUseCase

	@RelaxedMockK
	private lateinit var snapshotOfArticles: AppSnapshotOfArticles

	@Test
	fun `All AppResult Cases`() = runTest {
		every { useCase(any()) } returns flow { emit(AppResult.Failure.NoInternetConnection()) }

		every { useCase(match { it == 1 || it == 7}) } returns flow { emit(AppResult.Loading()) }

		every { useCase(30) } returns flow { emit(AppResult.Success(snapshotOfArticles)) }

		val incorrectParam = 444

		assertInstanceOf<AppResult.Failure<AppSnapshotOfArticles>>(
			actualValue = useCase(incorrectParam).single()
		)

		assertInstanceOf<AppResult.Loading<AppSnapshotOfArticles>>(
			actualValue = useCase(7).single()
		)

		assertInstanceOf<AppResult.Success<AppSnapshotOfArticles>>(
			actualValue = useCase(30).single()
		)

		verify(exactly = 3) { useCase(any()) }
	}

}