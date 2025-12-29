plugins {
    `java-library`
    id("com.diffplug.spotless") version "8.1.0"
}

group = "club.minnced"

version = "1.0-SNAPSHOT"

repositories {
    mavenLocal()
    mavenCentral()
}

dependencies {
    implementation("org.slf4j:slf4j-api:2.0.17")

    // TODO: Fix this version on proper release
    compileOnly("net.dv8tion:JDA:6.3.0_DEV")

    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    testImplementation("ch.qos.logback:logback-classic:1.5.23")
}

tasks.test {
    useJUnitPlatform()

    jvmArgs = listOf("--enable-native-access=ALL-UNNAMED")
}

spotless {
    kotlinGradle { ktfmt().kotlinlangStyle() }

    java {
        palantirJavaFormat()

        removeUnusedImports()
        trimTrailingWhitespace()
    }
}
