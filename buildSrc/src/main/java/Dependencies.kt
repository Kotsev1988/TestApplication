import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.kotlin.dsl.project


object Dependencies {

    const val daggerAndroid = "com.google.dagger:dagger:${Versions.dagger}"
    const val daggerCompiler = "com.google.dagger:dagger-compiler:${Versions.dagger}"

    const val okHttpLoggingInterceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.okHttp}"

    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"

    const val retrofitConverter = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"

    const val navigationFragment = "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
    const val navigationUi = "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"

    const val glide = "com.github.bumptech.glide:glide:${Versions.glide}"
    const val glideCompiler = "com.github.bumptech.glide:compiler:${Versions.glide}"

    const val roomRuntime = "androidx.room:room-runtime:${Versions.room}"
    const val roomCompiler = "androidx.room:room-compiler:${Versions.room}"
    const val roomKtx = "androidx.room:room-ktx:${Versions.room}"
}

fun DependencyHandler.room() {
    implementation(Dependencies.roomRuntime)
    implementation(Dependencies.roomKtx)
    kapt(Dependencies.roomCompiler)
}

fun DependencyHandler.retrofit() {
    implementation(Dependencies.retrofit)
    implementation(Dependencies.retrofitConverter)
    implementation(Dependencies.okHttpLoggingInterceptor)
}

fun DependencyHandler.glide() {
    implementation(Dependencies.glide)
    kapt(Dependencies.glideCompiler)
}

fun DependencyHandler.dagger() {
    implementation(Dependencies.daggerAndroid)
    kapt(Dependencies.daggerCompiler)
}

fun DependencyHandler.navigation() {
    implementation(Dependencies.navigationFragment)
    implementation(Dependencies.navigationUi)
}


fun DependencyHandler.ApiDataSource() {
    implementation(project(":api"))
}

fun DependencyHandler.UtilSource() {
    implementation(project(":util"))
}

fun DependencyHandler.HotelSource() {
    implementation(project(":hotel"))
}

fun DependencyHandler.NumberSource() {
    implementation(project(":number"))
}

fun DependencyHandler.BookingSource() {
    implementation(project(":booking"))
}

fun DependencyHandler.PaidSource() {
    implementation(project(":paid"))
}