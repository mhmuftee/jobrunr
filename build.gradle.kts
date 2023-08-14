plugins {
	java
	id("org.springframework.boot") version "3.1.2"
	id("io.spring.dependency-management") version "1.1.2"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-batch")
	// https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-web
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	// https://mvnrepository.com/artifact/org.jobrunr/jobrunr-spring-boot-starter
	implementation("org.jobrunr:jobrunr-spring-boot-starter:5.3.3")
	runtimeOnly("com.h2database:h2")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.springframework.batch:spring-batch-test")
	testImplementation("org.awaitility:awaitility:4.2.0")
}

tasks.withType<Test> {
	useJUnitPlatform()
}
