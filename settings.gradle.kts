pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
//        maven("https://jitpack.io") // Añadir para nueva forma de gestion de dependencias
    }
}

rootProject.name = "LoginMVVM"
include(":app")
