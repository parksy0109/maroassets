import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    id("org.springframework.boot") version "2.7.17"
    id("io.spring.dependency-management") version "1.0.15.RELEASE"
    kotlin("jvm") version "1.6.21"
    kotlin("kapt") version "1.6.21"
    kotlin("plugin.spring") version "1.6.21" apply false
    kotlin("plugin.jpa") version "1.6.21" apply false
}

allprojects {
    group = "com.maro"
    version = "0.0.1-SNAPSHOT"

    repositories {
        mavenCentral()
    }
}

subprojects {
    apply(plugin = "java")

    apply(plugin = "io.spring.dependency-management")
    apply(plugin = "org.springframework.boot")
    apply(plugin = "org.jetbrains.kotlin.plugin.spring")
    apply(plugin = "org.jetbrains.kotlin.jvm")
    apply(plugin = "kotlin")
    apply(plugin = "kotlin-kapt")
    apply(plugin = "kotlin-spring") //all-open

    dependencies {
        //spring boot
        implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
        developmentOnly("org.springframework.boot:spring-boot-devtools")

        //kotlin
        implementation("org.jetbrains.kotlin:kotlin-reflect")
        implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

        //jasypt
        implementation("com.github.ulisesbocchio:jasypt-spring-boot-starter:3.0.4")

        // Spring Data JPA
        implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    }

    dependencyManagement {
        imports {
            mavenBom(org.springframework.boot.gradle.plugin.SpringBootPlugin.BOM_COORDINATES)
        }

        dependencies {
            dependency("net.logstash.logback:logstash-logback-encoder:6.6")
        }
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict")
            jvmTarget = "11"
        }
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }

    configurations {
        compileOnly {
            extendsFrom(configurations.annotationProcessor.get())
        }
    }
}
project(":maro-assets-web") {
    apply(plugin = "kotlin-jpa")

    dependencies {
        implementation(project(":maro-assets-domain"))

        implementation("org.springframework.boot:spring-boot-starter-data-jpa")

        implementation("org.springframework.boot:spring-boot-starter-web")

        implementation("org.springframework.boot:spring-boot-starter-test")

        implementation("org.springframework.boot:spring-boot-starter")
        implementation("org.springframework.boot:spring-boot-starter-websocket")

        implementation("org.springframework.boot:spring-boot-starter-security")

        // swagger 관련 의존성
        implementation("io.springfox:springfox-boot-starter:3.0.0")

        // coroutines
        implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.0")
        runtimeOnly("org.jetbrains.kotlinx:kotlinx-coroutines-core-jvm:1.6.0")
        implementation("org.jetbrains.kotlinx:kotlinx-coroutines-slf4j:1.6.0")

        implementation("com.google.code.gson:gson:2.8.5")

        implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
    }
}


project(":maro-assets-domain") {
    val jar: Jar by tasks
    val bootJar: BootJar by tasks

    bootJar.enabled = false
    jar.enabled = true

    apply(plugin = "kotlin-jpa")

    dependencies {
        // https://mvnrepository.com/artifact/org.apache.tomcat/tomcat-util
        implementation("org.apache.tomcat:tomcat-util:9.0.56")

        //region dependency related Database
//        implementation("mysql:mysql-connector-java:8.0.28")
        implementation("org.mariadb.jdbc:mariadb-java-client:2.7.4")
        implementation("org.springframework.boot:spring-boot-starter-data-jpa")
        //endregion

        //region querydsl dependency
        implementation("com.querydsl:querydsl-jpa:5.0.0")
        kapt("com.querydsl:querydsl-apt:5.0.0:jpa")
        kapt("org.springframework.boot:spring-boot-configuration-processor")
        //endregion

        implementation("com.google.apis:google-api-services-androidpublisher:v3-rev20211125-1.32.1")
        implementation("com.google.auth:google-auth-library-oauth2-http:0.17.1")
    }

    sourceSets["main"].withConvention(org.jetbrains.kotlin.gradle.plugin.KotlinSourceSet::class) {
        kotlin.srcDir("$buildDir/generated/source/kapt/main")
    }

}