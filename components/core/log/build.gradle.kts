plugins {
    id("com.android.library")
    id("kotlin-android")
}
apply<com.flipperdevices.gradle.ConfigurationPlugin>()

dependencies {
    api(Libs.TIMBER)
}