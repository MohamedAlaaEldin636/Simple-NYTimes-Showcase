package my.ym.simple_nytimes_showcase

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import my.ym.ui_shared.BuildConfig
import timber.log.Timber
import timber.log.Timber.DebugTree


@HiltAndroidApp
class MyApp : Application() {

	override fun onCreate() {
		super.onCreate()

		if (BuildConfig.DEBUG) { // Make sure this only runs in debug builds
			Timber.plant(DebugTree())
		}
	}

}
