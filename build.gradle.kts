plugins {
    java
}

group = "dev.tonimatas"
version = "1.0.0"

tasks.jar {
    manifest {
        attributes(
            "Main-Class" to "dev.tonimatas.Main"
        )
    }
}
