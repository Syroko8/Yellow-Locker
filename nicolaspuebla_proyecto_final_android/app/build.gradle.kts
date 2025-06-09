import com.google.android.libraries.mapsplatform.secrets_gradle_plugin.SecretsPluginExtension

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("com.google.devtools.ksp")
    id("com.google.dagger.hilt.android")
    id("com.google.android.libraries.mapsplatform.secrets-gradle-plugin")
}

val secrets = Properties().apply {
    val secretsFile = rootProject.file("secrets.properties")
    if (secretsFile.exists()) {
        load(secretsFile.inputStream())
    } else {
        println("⚠️ secrets.properties not found at: ${secretsFile.absolutePath}")
    }
}

android {
    namespace = "com.example.nicolaspuebla_proyecto_final_android"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.nicolaspuebla_proyecto_final_android"
        minSdk = 26
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        resValue("string", "google_maps_key", project.properties["MAPS_API_KEY"] as? String ?: "")
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
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.2"
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
    implementation(libs.androidx.espresso.core)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.material.icons.extended)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
    // Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.11.0")
    implementation("com.squareup.retrofit2:converter-gson:2.11.0")
    implementation("com.squareup.retrofit2:converter-scalars:2.11.0")
    // Hilt
    ksp ("com.google.dagger:dagger-compiler:2.48")
    ksp ("com.google.dagger:hilt-compiler:2.48")
    ksp("com.google.dagger:hilt-android-compiler:2.48")
    implementation("com.google.dagger:hilt-android:2.48")
    // Navigation
    implementation("androidx.hilt:hilt-navigation-compose:1.2.0")
    implementation("androidx.hilt:hilt-navigation-compose:1.2.0")
    implementation(libs.androidx.navigation.compose)
    // Coil
    implementation("io.coil-kt:coil-compose:2.7.0")
    // Gson
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.15.2")
    // Calendar
    implementation("androidx.activity:activity-compose:1.4.0")
    implementation("androidx.compose.foundation:foundation:1.1.0-alpha05")
    implementation("androidx.compose.material:material:1.1.0-alpha05")
    implementation("androidx.compose.ui:ui:1.1.0-alpha05")
    implementation("androidx.compose.ui:ui-tooling-preview:1.1.0-alpha05")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:1.0.0-alpha07")
    implementation("androidx.compose.runtime:runtime-livedata:1.1.0-alpha05")
    implementation("com.google.accompanist:accompanist-pager:0.20.0")
    implementation("com.google.accompanist:accompanist-pager:0.28.0")
    // Maps
    implementation("com.google.maps.android:maps-compose:4.3.3")
    implementation("com.google.android.gms:play-services-maps:18.2.0")
    implementation("com.google.maps.android:maps-compose-widgets:4.3.3")
    implementation("com.google.android.libraries.places:places:3.3.0")
    // DataStore
    implementation("androidx.datastore:datastore-preferences:1.0.0")
    implementation("androidx.datastore:datastore-core:1.0.0")
    // ZXing
    implementation("com.journeyapps:zxing-android-embedded:4.3.0")
    // LightSpark
    implementation ("com.lightspark:compose-qr-code:1.0.1")
}