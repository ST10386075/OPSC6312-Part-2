// Top-level build file

plugins {
    kotlin("jvm") version "1.9.10" apply false
    id("com.android.application") version "8.8.1" apply false
    id("com.google.devtools.ksp") version "1.9.10-1.0.13" apply false
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
