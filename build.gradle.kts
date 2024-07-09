import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.9.23"
    id("org.springframework.boot") version "3.1.4"
    id("io.spring.dependency-management") version "1.1.3"
    kotlin("plugin.spring") version "1.8.22"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    implementation("org.jdbi:jdbi3-core:3.45.1")
    implementation("org.jdbi:jdbi3-kotlin:3.45.1")
    implementation("org.jdbi:jdbi3-postgres:3.45.2")
    implementation("org.postgresql:postgresql:42.7.3")

    // To use Kotlin specific date and time functions
    implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.5.0")

    implementation("org.json:json:20230227")

    implementation ("com.squareup.okhttp3:okhttp:4.9.1")

    // To get password encode
    implementation("org.springframework.security:spring-security-core:6.3.0")

    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.boot:spring-boot-starter-webflux")

}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}