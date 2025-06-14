plugins {
	alias(libs.plugins.android.library)
	alias(libs.plugins.kotlin.android)
}

android {
	namespace = "my.ym.data_shared"
	compileSdk = 35

	defaultConfig {
		minSdk = 24

		testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
		consumerProguardFiles("consumer-rules.pro")
	}

	buildTypes {
		release {
			isMinifyEnabled = false
			proguardFiles(
				getDefaultProguardFile("proguard-android-optimize.txt"),
				"proguard-rules.pro"
			)
		}
	}
	compileOptions {
		// Java 8+ API desugaring support
		isCoreLibraryDesugaringEnabled = true

		sourceCompatibility = JavaVersion.VERSION_11
		targetCompatibility = JavaVersion.VERSION_11
	}
	kotlinOptions {
		jvmTarget = "11"
	}
}

dependencies {

	// Java 8+ API desugaring support
	coreLibraryDesugaring(libs.android.desugar.jdk)

	// Local Modules
	implementation(projects.core.kotlin)
	implementation(projects.domain.articles)

	// Androidx
	implementation(libs.androidx.core.ktx)

	// Test
	testImplementation(libs.junit)

	// Test Android
	androidTestImplementation(libs.androidx.junit)
	androidTestImplementation(libs.androidx.espresso.core)

}