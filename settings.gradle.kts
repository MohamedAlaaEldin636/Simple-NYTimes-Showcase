pluginManagement {
	repositories {
		google {
			content {
				includeGroupByRegex("com\\.android.*")
				includeGroupByRegex("com\\.google.*")
				includeGroupByRegex("androidx.*")
			}
		}
		mavenCentral()
		gradlePluginPortal()
	}
}

dependencyResolutionManagement {
	repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
	repositories {
		google()
		mavenCentral()
	}
}

// Used to be able to use local module via projects.* in gradle inshallah
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

rootProject.name = "SimpleNYTimesShowcase"

// Core Code ( Kotlin - Android )
include(":core:kotlin"/*, ":core:android"*/)

// Shared code between features modules
include(":domain:shared", ":data:shared")

// Feature ( article )
include(":feature:articles", ":ui:articles", ":data:articles", ":domain:articles")

// Apps
include(":app")
