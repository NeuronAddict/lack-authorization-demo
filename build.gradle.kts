plugins {
    java
    id("io.quarkus")
}

repositories {
    mavenCentral()
    mavenLocal()
}

val quarkusPlatformGroupId: String by project
val quarkusPlatformArtifactId: String by project
val quarkusPlatformVersion: String by project
val mapstructVersion = "1.6.3"

dependencies {
    implementation(enforcedPlatform("${quarkusPlatformGroupId}:${quarkusPlatformArtifactId}:${quarkusPlatformVersion}"))
    implementation("io.quarkiverse.qute.web:quarkus-qute-web")
    implementation("io.quarkus:quarkus-rest-qute")
    implementation("io.quarkus:quarkus-oidc")
    implementation("io.quarkus:quarkus-arc")

    implementation("io.quarkus:quarkus-hibernate-orm-panache")
    implementation("io.quarkus:quarkus-jdbc-postgresql")

    implementation("org.mapstruct:mapstruct:${mapstructVersion}")
    implementation("io.quarkus:quarkus-container-image-docker")
    annotationProcessor("org.mapstruct:mapstruct-processor:${mapstructVersion}")

    testImplementation("io.quarkus:quarkus-test-security-oidc")
    testImplementation("io.rest-assured:rest-assured")
    testImplementation("io.quarkus:quarkus-junit5")
    testAnnotationProcessor("org.mapstruct:mapstruct-processor:${mapstructVersion}")

}

group = "org.neuronaddict"
version = "1.0-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
}

tasks.withType<Test> {
    systemProperty("java.util.logging.manager", "org.jboss.logmanager.LogManager")
    jvmArgs("--add-opens", "java.base/java.lang=ALL-UNNAMED")
}
tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
    options.compilerArgs.add("-parameters")
}
