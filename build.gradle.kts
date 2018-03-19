import org.gradle.api.tasks.Delete
import org.gradle.kotlin.dsl.extra
import org.gradle.kotlin.dsl.maven
import org.gradle.kotlin.dsl.repositories

allprojects {
    repositories {
        google()
        jcenter()
        maven(url="https://plugins.gradle.org/m2/")
        maven(url="https://maven.fabric.io/public")
    }
}

task("clean", Delete::class) {
    delete = setOf(rootProject.buildDir)
}

plugins {
    id("io.gitlab.arturbosch.detekt") version Versions.detekt
}

detekt {
    version = Versions.detekt
    profile("main", Action {
        input = "$projectDir/app"
        output = "$projectDir/app/build/reports/detekt"
        config = "$projectDir/quality/detekt.yml"
        filters = ".*test.*,.*/resources/.*,.*/tmp/.*"
    })
}
