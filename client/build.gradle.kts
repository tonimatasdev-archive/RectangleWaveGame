repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":shared"))
    implementation("io.netty:netty-all:4.1.65.Final")
}

tasks.jar {
    manifest {
        attributes(
            "Main-Class" to "dev.tonimatas.Main"
        )
    }
}