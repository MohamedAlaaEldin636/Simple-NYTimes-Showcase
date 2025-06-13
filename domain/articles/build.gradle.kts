plugins {
	id("java-library")
	alias(libs.plugins.jetbrains.kotlin.jvm)
	// Used to fix warning shown when using Mockk with Junit 5 in testing.
	alias(libs.plugins.javaagent.test)
}
java {
	sourceCompatibility = JavaVersion.VERSION_11
	targetCompatibility = JavaVersion.VERSION_11
}
kotlin {
	compilerOptions {
		jvmTarget = org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_11
	}
}
dependencies {
	// Local Modules
	api(projects.domain.shared)

	// Kotlin Coroutines
	implementation(libs.kotlinx.coroutines.core)

	// Test
	testImplementation(libs.mockk.mockk)
	testImplementation(platform(libs.org.junit.bom))
	testImplementation(libs.org.junit.jupiter)
	testRuntimeOnly(libs.org.junit.platform.launcher)
	// Used to fix warning shown when using Mockk with Junit 5 in testing.
	testJavaagent(libs.byte.buddy.agent)
	testImplementation(libs.kotlinx.coroutines.test)
}

tasks.test {
	useJUnitPlatform()
	testLogging {
		events("passed", "skipped", "failed")
	}
}
