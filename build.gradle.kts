import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val kotlinVersion: String by project
val kotlinCoroutinesVersion: String by project
val kotlinSerializationVersion: String by project
val ktorVersion: String by project
val kodeinVersion: String by project
val kmongoVersion: String by project
val logbackVersion: String by project

plugins {
    application
    kotlin("jvm") version "1.4.10"
    kotlin("plugin.serialization") version "1.4.0"
    id("com.bnorm.power.kotlin-power-assert") version "0.5.3"
}

group = "nl.kelsinga.bmpserver"
version = "0.0.1"

application {
    mainClassName = "io.ktor.server.netty.EngineMain"
}

repositories {
    maven ("https://dl.bintray.com/kotlin/kotlin-eap")
    maven ("https://kotlin.bintray.com/kotlinx")
    google()
    mavenCentral()
    mavenLocal()
    jcenter()
    maven ("https://kotlin.bintray.com/ktor")
}

dependencies {
    //kotlin
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$kotlinCoroutinesVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:$kotlinSerializationVersion")

//    implementation("com.github.jershell:kbson:0.3.0")

    //logging
    implementation("ch.qos.logback:logback-classic:$logbackVersion")

    //ktor
    implementation("io.ktor:ktor-server-netty:$ktorVersion")
    implementation("io.ktor:ktor-server-core:$ktorVersion")
    implementation("io.ktor:ktor-locations:$ktorVersion")
    implementation("io.ktor:ktor-serialization:$ktorVersion")
    implementation("io.ktor:ktor-server-host-common:$ktorVersion")

    //Database
    implementation("org.litote.kmongo:kmongo-coroutine-serialization:$kmongoVersion")
//    implementation("org.litote.kmongo:kmongo-id:$kmongoVersion") // is this needed? frontend only I think
    implementation("org.litote.kmongo:kmongo-id-serialization:$kmongoVersion")

    //Dependency Injection
    implementation("org.kodein.di:kodein-di-framework-ktor-server-jvm:$kodeinVersion")

    //tests
    testImplementation("io.ktor:ktor-server-tests:$ktorVersion")
    testImplementation(kotlin("test-junit5"))
}

kotlin.sourceSets["main"].kotlin.srcDirs("src")
kotlin.sourceSets["test"].kotlin.srcDirs("test")

sourceSets["main"].resources.srcDirs("resources")
sourceSets["test"].resources.srcDirs("testresources")

tasks.withType<KotlinCompile>().all {
    kotlinOptions.jvmTarget = "13"
    kotlinOptions.freeCompilerArgs += "-Xopt-in=kotlin.RequiresOptIn"
}

tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions {
        useIR = true
    }
}



