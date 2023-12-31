plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
    id ("kotlin-parcelize")
}

android {
    namespace ="com.example.testapplication"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.testapplication"
        minSdk =26
        versionCode= 1
        versionName ="1.0"

        testInstrumentationRunner= "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                    getDefaultProguardFile("proguard-android-optimize.txt"),
                    "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility =JavaVersion.VERSION_18
        targetCompatibility =JavaVersion.VERSION_18
    }
    kotlinOptions {
        jvmTarget = "18"
    }

    buildFeatures {
        viewBinding= true
    }
}

dependencies {

    implementation ("androidx.core:core-ktx:1.12.0")
    implementation ("androidx.appcompat:appcompat:1.6.1")
    implementation ("com.google.android.material:material:1.10.0")
    testImplementation ("junit:junit:4.13.2")
    androidTestImplementation ("androidx.test.ext:junit:1.1.5")
    androidTestImplementation ("androidx.test.espresso:espresso-core:3.5.1")

    HotelSource()
    NumberSource()
    BookingSource()
    PaidSource()
    ApiDataSource()
    UtilSource()

    room()
    retrofit()
    glide()
    navigation()
    dagger()
}