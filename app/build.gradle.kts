import java.util.Properties


plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
}
val localProperties = Properties()
localProperties.load(rootProject.file("local.properties").inputStream())


android {
    namespace = "com.example.munjangzip"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.munjangzip"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        buildConfigField(
            "String",
            "BASE_URL",
            "\"${localProperties.getProperty("BASE_URL")}\""
        )
        buildFeatures {
            buildConfig = true
        }
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    //카카오 SDK
    implementation ("com.kakao.sdk:v2-user:2.18.0")

    //구글 SDK
    implementation ("com.google.android.gms:play-services-auth:20.7.0")

    implementation("androidx.navigation:navigation-compose:2.7.7")

    // Retrofit core
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")

    // JSON 변환용 Gson Converter
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")

    // 코루틴을 쓸 경우 (옵션)
    implementation ("com.squareup.retrofit2:adapter-rxjava2:2.9.0")

}