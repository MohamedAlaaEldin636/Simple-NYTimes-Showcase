package my.ym.feature_articles

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import my.ym.data_articles.BuildConfig
import my.ym.data_articles.remote.articles.ApiServiceArticles
import okhttp3.Interceptor
import javax.inject.Singleton
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
@InstallIn(SingletonComponent::class)
data object HiltModuleOfNetwork {

	@Provides
	@Singleton
	fun provideApiServiceArticles(retrofit: Retrofit): ApiServiceArticles {
		return retrofit.create(ApiServiceArticles::class.java)
	}

	@Provides
	@Singleton
	fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
		return Retrofit.Builder()
			.baseUrl(BuildConfig.API_SERVICE_NYTIMES_API_BASE_URL)
			.client(okHttpClient)
			.addConverterFactory(GsonConverterFactory.create())
			.build()
	}

	@Singleton
	@Provides
	fun provideOkHttpClient(): OkHttpClient {
		val loggingInterceptor = HttpLoggingInterceptor().apply {
			level = HttpLoggingInterceptor.Level.BODY
		}

		val fixedQueryInterceptor = Interceptor { chain ->
			val originalRequest = chain.request()

			val urlWithFixedQuery = originalRequest.url
				.newBuilder()
				.addQueryParameter("api-key", BuildConfig.API_SERVICE_NYTIMES_API_KEY)
				.build()

			val newRequest = originalRequest.newBuilder()
				.url(urlWithFixedQuery)
				.build()

			chain.proceed(newRequest)
		}

		return OkHttpClient.Builder()
			.addInterceptor(loggingInterceptor)
			.addInterceptor(fixedQueryInterceptor)
			.connectTimeout(10, TimeUnit.SECONDS)
			.readTimeout(10, TimeUnit.SECONDS)
			.writeTimeout(10, TimeUnit.SECONDS)
			.build()
	}

}
