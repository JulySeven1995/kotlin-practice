tasks.getByName<org.springframework.boot.gradle.tasks.bundling.BootJar>("bootJar") {
	enabled = false
}

tasks.getByName<Jar>("jar") {
	enabled = true
	baseName = "julyseven-common"
}

dependencies {

	api(kotlin("reflect"))
	api(kotlin("stdlib-jdk8"))
	api("org.springframework.boot:spring-boot-starter")
	api("org.springframework.boot:spring-boot-starter-data-jpa")
	runtimeOnly("com.h2database:h2")
	testApi("com.h2database:h2")

	testImplementation("org.springframework.boot:spring-boot-starter-test")

}