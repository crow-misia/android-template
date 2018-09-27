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
