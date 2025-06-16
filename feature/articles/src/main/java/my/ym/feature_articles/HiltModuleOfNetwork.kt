package my.ym.feature_articles

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import my.ym.data_articles.remote.articles.ApiServiceArticles
import javax.inject.Singleton
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
data object HiltModuleOfApiServices {

	@Provides
	@Singleton
	fun provideApiServiceArticles(retrofit: Retrofit): ApiServiceArticles {
		return retrofit.create(ApiServiceArticles::class.java)
	}

}
