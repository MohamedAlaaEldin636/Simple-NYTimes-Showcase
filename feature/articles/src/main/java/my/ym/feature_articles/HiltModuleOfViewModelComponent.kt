package my.ym.feature_articles

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import my.ym.data_articles.remote.articles.RemoteDataSourceArticles
import my.ym.data_articles.remote.articles.RepoArticlesImpl
import my.ym.domain_articles.repos.RepoArticles
import my.ym.domain_articles.useCases.GetMostPopularViewedArticlesLastDayUseCase

@Module
@InstallIn(ViewModelComponent::class)
data object HiltModuleOfViewModelComponent {

	@ViewModelScoped
	@Provides
	fun provideRepoArticles(
		remoteDataSourceArticles: RemoteDataSourceArticles,
	): RepoArticles {
		return RepoArticlesImpl(remoteDataSourceArticles = remoteDataSourceArticles)
	}

	@ViewModelScoped
	@Provides
	fun provideGetMostPopularViewedArticlesLastDayUseCase(
		repoArticles: RepoArticles
	): GetMostPopularViewedArticlesLastDayUseCase {
		return GetMostPopularViewedArticlesLastDayUseCase(repoArticles)
	}

}