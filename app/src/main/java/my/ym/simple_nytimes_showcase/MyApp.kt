package my.ym.simple_nytimes_showcase

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import my.ym.ui_shared.BuildConfig
import timber.log.Timber

@HiltAndroidApp
class MyApp : Application() {

	override fun onCreate() {
		super.onCreate()

		if (BuildConfig.DEBUG) {
			Timber.plant(Timber.DebugTree())
		}
	}

}
