import java.util.Properties

plugins {
	alias(libs.plugins.android.library)
	alias(libs.plugins.kotlin.android)
	alias(libs.plugins.hilt.android)
	alias(libs.plugins.ksp)
}

val localProperties = Properties()
localProperties.load(project.rootProject.file("local.properties").inputStream())

val apiServiceNYTimesApiKey = localProperties["API_SERVICE_NYTIMES_API_KEY"] as? String

val apiServiceNYTimesApiBaseUrl = localProperties["API_SERVICE_NYTIMES_API_BASE_URL"] as? String

android {
	namespace = "my.ym.feature_shared"
	compileSdk = 35

	defaultConfig {
		minSdk = 24

		testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
		consumerProguardFiles("consumer-rules.pro")

		buildConfigField("String", "API_SERVICE_NYTIMES_API_KEY", "\"$apiServiceNYTimesApiKey\"")
		buildConfigField("String", "API_SERVICE_NYTIMES_API_BASE_URL", "\"$apiServiceNYTimesApiBaseUrl\"")
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

	buildFeatures {
		buildConfig = true
	}
}

dependencies {

	// Java 8+ API desugaring support
	coreLibraryDesugaring(libs.android.desugar.jdk)

	// Local Modules
	implementation(projects.data.shared)

	// Androidx
	implementation(libs.androidx.core.ktx)
	implementation(libs.androidx.hilt.navigation.compose)

	// Hilt
	implementation(libs.hilt.android)
	ksp(libs.hilt.android.compiler)

	// Retrofit
	implementation(libs.retrofit2.retrofit)
	implementation(libs.retrofit2.converter.gson)

	// OkHttp Logging Interceptor
	implementation(libs.okhttp3.logging.interceptor)

	// Test
	testImplementation(libs.junit)

	// Test Android
	androidTestImplementation(libs.androidx.junit)
	androidTestImplementation(libs.androidx.espresso.core)

}

hilt {
	enableAggregatingTask = true
}
