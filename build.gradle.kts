plugins {
    kotlin("jvm") version "1.6.21"
    application
    id("io.kotest") version "0.3.8"
}

group = "org.example"
version = "1.0-SNAPSHOT"
var jacksonVersion = "2.13.2"

application {
    mainClass.set("org.example.MainKt")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.0")

    testImplementation("io.kotest:kotest-assertions-core-jvm:5.2.2")
    testImplementation("io.kotest:kotest-framework-engine-jvm:5.2.2")
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
    kotlinOptions.jvmTarget = "11"
    kotlinOptions.allWarningsAsErrors = true
}

