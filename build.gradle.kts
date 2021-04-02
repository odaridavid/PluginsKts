plugins {
    kotlin("jvm") version "1.4.32"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
}

class GreetingPlugin : Plugin<Project> {
    override fun apply(target: Project) {

        val greetingExtension = target.extensions.create<GreetingPluginExtensions>("greeting")
        val someCoolExtension = target.extensions.create<SomeReallyCoolExtension>("cool")

        target.task("greetUser") {
            doLast {
                println("Hello David")
                println(greetingExtension.message)
                println(someCoolExtension.message + " with code " + someCoolExtension.statusCode)
            }
        }
    }
}

open class GreetingPluginExtensions : java.io.Serializable {
    var message: String = "Custom Message"
}

open class SomeReallyCoolExtension : java.io.Serializable {
    var message: String = "Custom Message"
    var statusCode: Int = 0
}

apply<GreetingPlugin>()

the<GreetingPluginExtensions>().message = "Testing Plugin Extensions"

configure<SomeReallyCoolExtension> {
    message = "Successfully Done Cool Stuff"
    statusCode = 200
}