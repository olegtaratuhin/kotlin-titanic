plugins {
    kotlin("jvm") version "1.3.70"
}

group = "personal.otaratukhin"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    jcenter()
    maven("https://dl.bintray.com/kotlin/kotlin-numpy")
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))

    // for data csv loading, data wrangling
    implementation("de.mpicbg.scicomp:krangl:0.11")

    // for models
    implementation("org.jetbrains:kotlin-numpy:0.1.4")
    implementation("ai.catboost:catboost-prediction:0.22")

    testCompile("junit", "junit", "4.12")
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
}
tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
    compileTestKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
}