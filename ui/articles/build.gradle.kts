plugins {
	alias(libs.plugins.android.library)
	alias(libs.plugins.kotlin.android)
	alias(libs.plugins.kotlin.compose)
	alias(libs.plugins.hilt.android)
	alias(libs.plugins.ksp)
	alias(libs.plugins.kotlin.plugin.serialization)
}

android {
	namespace = "my.ym.ui_articles"
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

		freeCompilerArgs += "-Xwhen-guards"
	}
}

dependencies {

	// Java 8+ API desugaring support
	coreLibraryDesugaring(libs.android.desugar.jdk)

	// Local Modules
	implementation(projects.ui.shared)
	implementation(projects.domain.articles)
	implementation(projects.core.kotlin)

	// Androidx
	implementation(libs.androidx.core.ktx)
	implementation(libs.androidx.lifecycle.runtime.ktx)
	implementation(libs.androidx.activity.compose)
	implementation(libs.androidx.navigation.compose)
	implementation(libs.androidx.hilt.navigation.compose)

	// Androidx Compose
	implementation(platform(libs.androidx.compose.bom))
	implementation(libs.androidx.compose.ui)
	implementation(libs.androidx.compose.ui.graphics)
	implementation(libs.androidx.compose.ui.tooling.preview)
	implementation(libs.androidx.compose.material3)
	// Androidx Compose ( Debug )
	debugImplementation(libs.androidx.compose.ui.tooling)
	debugImplementation(libs.androidx.compose.ui.test.manifest)

	// Kotlinx JSON Serialization
	implementation(libs.kotlinx.serialization.json)

	// Hilt
	implementation(libs.hilt.android)
	ksp(libs.hilt.android.compiler)

	// Test
	testImplementation(libs.junit)

	// Test Android
	androidTestImplementation(libs.androidx.junit)
	androidTestImplementation(libs.androidx.espresso.core)

}