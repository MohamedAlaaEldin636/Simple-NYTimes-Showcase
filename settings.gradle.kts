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

include(":app")

include(":feature:articles:domain", ":feature:articles:data")
