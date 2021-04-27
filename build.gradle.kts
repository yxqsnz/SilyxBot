/*#############################################################################
 # Author: yxqsnz                                                             #
 # Github: https://github.com/yxqsnz                                          #
 # License: GNU GPL v3                                                        #
 #############################################################################*/

import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.4.32"
    application
}

group = "me.yxqsnz"
version = "2021-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://m2.dv8tion.net/releases")
}

dependencies {
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.4.3")
    // Klaxon para JSON
    implementation("com.beust:klaxon:5.5")
    // MongoDb para Banco de dados
    implementation("org.mongodb:mongodb-driver-sync:4.2.3")
    // Discord
    implementation("net.dv8tion:JDA:4.2.1_261")
    // Csv reader
    implementation("com.github.doyaaaaaken:kotlin-csv-jvm:0.15.2")
    // OkHttp para requests
    implementation("com.squareup.okhttp3:okhttp:4.9.0")
    
    testImplementation(kotlin("test-junit"))
}

tasks.test {
    useJUnit()
}

tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "11"
}
val fatJar = task("fatJar", type = Jar::class) {
    manifest {
        attributes["Main-Class"] = "SilyxLauncherKt"
    }
    from(configurations.runtimeClasspath.get().map { if (it.isDirectory) it else {
        if (!it.name.endsWith("pom")) {
            zipTree(it)
        } else { it }
    }
    })
    with(tasks.jar.get() as CopySpec)
}

tasks {
    "build" {
        println("Compilando o Silyx...Isso pode demorar")
        dependsOn(fatJar)
    }
}

application {
    mainClass.set("SilyxLauncherKt")
}
