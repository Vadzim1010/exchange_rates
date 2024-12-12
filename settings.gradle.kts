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

rootProject.name = "Exchange Rates"
include(":app")
include(":data")
include(":domain")

//core
include(":core:common")
include(":core:network")

// feature
include(":feature:main:root")
include(":feature:main:currancies")
include(":feature:main:favorite")