package my.ym.domain_articles

import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import my.ym.domain_articles.models.AppMediaMetadata
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

/** Or run ./gradlew :domain:articles:test */
@ExtendWith(MockKExtension::class)
@MockKExtension.ConfirmVerification
class TestMediaMetadata {

	companion object {
		private const val FAKE_URL = "Https"
	}

	@MockK
	private lateinit var appMediaMetadata: AppMediaMetadata

	@RelaxedMockK
	private lateinit var appMediaMetadataRelaxed: AppMediaMetadata

	@Test
	fun `Simple Mockk`() {
		every { appMediaMetadata.url } returns FAKE_URL

		Assertions.assertEquals(appMediaMetadata.url, FAKE_URL)

		verify(exactly = 1) { appMediaMetadata.url }
	}

	@Test
	fun `Simple Relaxed Mockk`() {
		println(appMediaMetadataRelaxed.url)

		verify(exactly = 1) { appMediaMetadataRelaxed.url }
	}

}