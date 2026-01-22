plugins {
   id("com.android.library")
   alias(libs.plugins.kotlin.android)
}
android {
   namespace = "com.example.domain"
   compileSdk {
      version = release(36)
   }

   defaultConfig {
      minSdk = 31
      testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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

   kotlin.compilerOptions.optIn.add("kotlin.uuid.ExperimentalUuidApi")
}

dependencies {
   implementation(project(":features:applications:data"))

   implementation(libs.kotlinx.coroutines.core)
   implementation(libs.javax.inject)
   testImplementation(libs.junit)
   androidTestImplementation(libs.androidx.junit)
}
