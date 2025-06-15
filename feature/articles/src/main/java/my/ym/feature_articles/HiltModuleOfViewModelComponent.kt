package my.ym.feature_articles

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import my.ym.data_articles.local.LocalDataSourceArticles
import my.ym.data_articles.remote.articles.RemoteDataSourceArticles
import my.ym.data_articles.repos.RepoArticlesImpl
import my.ym.domain_articles.repos.RepoArticles
import my.ym.domain_articles.useCases.GetMostPopularViewedArticlesLastDayUseCase

@Module
@InstallIn(ViewModelComponent::class)
data object HiltModuleOfViewModelComponent {

	@ViewModelScoped
	@Provides
	fun provideRepoArticles(
		remoteDataSourceArticles: RemoteDataSourceArticles,
		localDataSourceArticles: LocalDataSourceArticles,
	): RepoArticles {
		return RepoArticlesImpl(
			remoteDataSourceArticles = remoteDataSourceArticles,
			localDataSourceArticles = localDataSourceArticles,
		)
	}

	@ViewModelScoped
	@Provides
	fun provideGetMostPopularViewedArticlesLastDayUseCase(
		repoArticles: RepoArticles
	): GetMostPopularViewedArticlesLastDayUseCase {
		return GetMostPopularViewedArticlesLastDayUseCase(repoArticles)
	}

}