## build.gradle.kts
`$lang
// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false
}
```

## settings.gradle.kts
`$lang
pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "UrbanPlanner"
include(":app")
```

## gradle.properties
`$lang
# Project-wide Gradle settings.
# IDE (e.g. Android Studio) users:
# Gradle settings configured through the IDE *will override*
# any settings specified in this file.
# For more details on how to configure your build environment visit
# http://www.gradle.org/docs/current/userguide/build_environment.html
# Specifies the JVM arguments used for the daemon process.
# The setting is particularly useful for tweaking memory settings.
org.gradle.jvmargs=-Xmx2048m -Dfile.encoding=UTF-8
# When configured, Gradle will run in incubating parallel mode.
# This option should only be used with decoupled projects. For more details, visit
# https://developer.android.com/r/tools/gradle-multi-project-decoupled-projects
# org.gradle.parallel=true
# AndroidX package structure to make it clearer which packages are bundled with the
# Android operating system, and which are packaged with your app's APK
# https://developer.android.com/topic/libraries/support-library/androidx-rn
android.useAndroidX=true
# Kotlin code style for this project: "official" or "obsolete":
kotlin.code.style=official
# Enables namespacing of each library's R class so that its R class includes only the
# resources declared in the library itself and none from the library's dependencies,
# thereby reducing the size of the R class for that library
android.nonTransitiveRClass=true
android.defaults.buildfeatures.resvalues=true
android.sdk.defaultTargetSdkToCompileSdkIfUnset=false
android.enableAppCompileTimeRClass=false
android.usesSdkInManifest.disallowed=false
android.uniquePackageNames=false
android.dependency.useConstraints=true
android.r8.strictFullModeForKeepRules=false
android.r8.optimizedResourceShrinking=false
android.builtInKotlin=false
android.newDsl=false
```

## gradle\libs.versions.toml
`$lang
[versions]
agp = "9.2.1"
kotlin = "2.2.10"
coreKtx = "1.17.0"
junit = "4.13.2"
junitVersion = "1.3.0"
espressoCore = "3.7.0"
lifecycleRuntimeKtx = "2.10.0"
activityCompose = "1.12.1"
composeBom = "2024.09.00"
maplibreAndroid = "9.6.2"
navigationRuntimeKtx = "2.9.6"
material3 = "1.4.0"
foundation = "1.10.0"
navigationCompose = "2.9.6"

[libraries]
androidx-core-ktx = { group = "androidx.core", name = "core-ktx", version.ref = "coreKtx" }
junit = { group = "junit", name = "junit", version.ref = "junit" }
androidx-junit = { group = "androidx.test.ext", name = "junit", version.ref = "junitVersion" }
androidx-espresso-core = { group = "androidx.test.espresso", name = "espresso-core", version.ref = "espressoCore" }
androidx-lifecycle-runtime-ktx = { group = "androidx.lifecycle", name = "lifecycle-runtime-ktx", version.ref = "lifecycleRuntimeKtx" }
androidx-activity-compose = { group = "androidx.activity", name = "activity-compose", version.ref = "activityCompose" }
androidx-compose-bom = { group = "androidx.compose", name = "compose-bom", version.ref = "composeBom" }
androidx-ui = { group = "androidx.compose.ui", name = "ui" }
androidx-ui-graphics = { group = "androidx.compose.ui", name = "ui-graphics" }
androidx-ui-tooling = { group = "androidx.compose.ui", name = "ui-tooling" }
androidx-ui-tooling-preview = { group = "androidx.compose.ui", name = "ui-tooling-preview" }
androidx-ui-test-manifest = { group = "androidx.compose.ui", name = "ui-test-manifest" }
androidx-ui-test-junit4 = { group = "androidx.compose.ui", name = "ui-test-junit4" }
androidx-material3 = { group = "androidx.compose.material3", name = "material3" }
androidx-navigation-runtime-ktx = { group = "androidx.navigation", name = "navigation-runtime-ktx", version.ref = "navigationRuntimeKtx" }
maplibre-android = { module = "org.maplibre.gl:maplibre-android", version.ref = "maplibreAndroid" }
material3 = { group = "androidx.compose.material3", name = "material3", version.ref = "material3" }
androidx-foundation = { group = "androidx.compose.foundation", name = "foundation", version.ref = "foundation" }
androidx-navigation-compose = { group = "androidx.navigation", name = "navigation-compose", version.ref = "navigationCompose" }

[plugins]
android-application = { id = "com.android.application", version.ref = "agp" }
kotlin-android = { id = "org.jetbrains.kotlin.android", version.ref = "kotlin" }
kotlin-compose = { id = "org.jetbrains.kotlin.plugin.compose", version.ref = "kotlin" }
```

## .gitignore
`$lang
# Built application files
*.apk
*.aar
*.ap_
*.aab

# Files for the ART/Dalvik VM
*.dex

# Java class files
*.class

# Generated files
bin/
gen/
out/

# Gradle files
.gradle/
build/
*/build/

# Local configuration file (sdk path, API keys, secrets, etc) - CRITICAL: DO NOT COMMIT
local.properties

# Proguard folder generated by Eclipse
proguard/

# Log Files
*.log

# Android Studio Navigation editor temp files
.navigation/

# Android Studio captures folder
captures/

# IntelliJ / Android Studio
*.iml
.idea/
.idea/workspace.xml
.idea/tasks.xml
.idea/gradle.xml
.idea/assetWizardSettings.xml
.idea/dictionaries
.idea/libraries
.idea/caches
# Keeps the layout/nav editors config (uncomment to track)
# .idea/navEditor.xml
# .idea/layoutdir.xml

# Keystore files (DO NOT COMMIT YOUR RELEASE KEYSTORES)
*.jks
*.keystore

# External native build folder generated in Android Studio 2.2 and later
.externalNativeBuild/
.cxx/

# Google Services (e.g. APIs or Firebase)
google-services.json

# Mac OS X
.DS_Store
```

## api.json
`$lang
{"openapi":"3.1.0","info":{"title":"GeoAI Urban Planner","version":"0.1.0"},"paths":{"/api/v1/auth/signup":{"post":{"tags":["auth"],"summary":"Signup","description":"Create a new user account with Supabase Auth.\n\nFor B2B users, status will be 'pending' until admin approval.\nFor B2C users, status is 'active' immediately.","operationId":"signup_api_v1_auth_signup_post","requestBody":{"content":{"application/json":{"schema":{"$ref":"#/components/schemas/SignupRequest"}}},"required":true},"responses":{"200":{"description":"Successful Response","content":{"application/json":{"schema":{"$ref":"#/components/schemas/AuthResponse"}}}},"422":{"description":"Validation Error","content":{"application/json":{"schema":{"$ref":"#/components/schemas/HTTPValidationError"}}}}}}},"/api/v1/auth/login":{"post":{"tags":["auth"],"summary":"Login","description":"Authenticate user and return JWT tokens.","operationId":"login_api_v1_auth_login_post","requestBody":{"content":{"application/json":{"schema":{"$ref":"#/components/schemas/LoginRequest"}}},"required":true},"responses":{"200":{"description":"Successful Response","content":{"application/json":{"schema":{"$ref":"#/components/schemas/AuthResponse"}}}},"422":{"description":"Validation Error","content":{"application/json":{"schema":{"$ref":"#/components/schemas/HTTPValidationError"}}}}}}},"/api/v1/auth/refresh":{"post":{"tags":["auth"],"summary":"Refresh Token","description":"Refresh access token using refresh token.","operationId":"refresh_token_api_v1_auth_refresh_post","requestBody":{"content":{"application/json":{"schema":{"$ref":"#/components/schemas/RefreshRequest"}}},"required":true},"responses":{"200":{"description":"Successful Response","content":{"application/json":{"schema":{}}}},"422":{"description":"Validation Error","content":{"application/json":{"schema":{"$ref":"#/components/schemas/HTTPValidationError"}}}}}}},"/api/v1/profiles/me":{"get":{"tags":["profiles"],"summary":"Read Users Me","operationId":"read_users_me_api_v1_profiles_me_get","responses":{"200":{"description":"Successful Response","content":{"application/json":{"schema":{"$ref":"#/components/schemas/ProfileResponse"}}}}},"security":[{"HTTPBearer":[]}]}},"/api/v1/agent/chat":{"post":{"tags":["agent"],"summary":"Chat With Agent","description":"Chat with the GeoAI agent using Gemini.","operationId":"chat_with_agent_api_v1_agent_chat_post","requestBody":{"content":{"application/json":{"schema":{"$ref":"#/components/schemas/ChatRequest"}}},"required":true},"responses":{"200":{"description":"Successful Response","content":{"application/json":{"schema":{"$ref":"#/components/schemas/ChatResponse"}}}},"422":{"description":"Validation Error","content":{"application/json":{"schema":{"$ref":"#/components/schemas/HTTPValidationError"}}}}}}},"/api/v1/agent/session/{session_id}":{"delete":{"tags":["agent"],"summary":"Clear Session","operationId":"clear_session_api_v1_agent_session__session_id__delete","parameters":[{"name":"session_id","in":"path","required":true,"schema":{"type":"string","title":"Session Id"}}],"responses":{"200":{"description":"Successful Response","content":{"application/json":{"schema":{}}}},"422":{"description":"Validation Error","content":{"application/json":{"schema":{"$ref":"#/components/schemas/HTTPValidationError"}}}}}}},"/api/v1/solar/analyze":{"post":{"tags":["solar"],"summary":"Analyze Solar","description":"Directly analyze solar potential for a location and generate coverage grid.\nUsed for manual mode in the frontend.","operationId":"analyze_solar_api_v1_solar_analyze_post","requestBody":{"content":{"application/json":{"schema":{"$ref":"#/components/schemas/SolarRequest"}}},"required":true},"responses":{"200":{"description":"Successful Response","content":{"application/json":{"schema":{"$ref":"#/components/schemas/SolarResponse"}}}},"422":{"description":"Validation Error","content":{"application/json":{"schema":{"$ref":"#/components/schemas/HTTPValidationError"}}}}}}},"/api/v1/amenities/analyze":{"post":{"tags":["amenities"],"summary":"Analyze Location","description":"Analyze the livability of a location based on preferences.","operationId":"analyze_location_api_v1_amenities_analyze_post","requestBody":{"content":{"application/json":{"schema":{"$ref":"#/components/schemas/AnalysisRequest"}}},"required":true},"responses":{"200":{"description":"Successful Response","content":{"application/json":{"schema":{"$ref":"#/components/schemas/AnalysisResponse"}}}},"422":{"description":"Validation Error","content":{"application/json":{"schema":{"$ref":"#/components/schemas/HTTPValidationError"}}}}}}},"/api/v1/amenities/personas":{"get":{"tags":["amenities"],"summary":"Get Personas","description":"Get a list of preset personas with their preferences.","operationId":"get_personas_api_v1_amenities_personas_get","responses":{"200":{"description":"Successful Response","content":{"application/json":{"schema":{"items":{"$ref":"#/components/schemas/Persona"},"type":"array","title":"Response Get Personas Api V1 Amenities Personas Get"}}}}}}},"/api/v1/compliance/illegal-societies":{"get":{"tags":["compliance"],"summary":"Get Illegal Societies","description":"Get a list of all known illegal housing societies with valid coordinates.","operationId":"get_illegal_societies_api_v1_compliance_illegal_societies_get","responses":{"200":{"description":"Successful Response","content":{"application/json":{"schema":{}}}}}}},"/api/v1/hazards/active":{"get":{"tags":["hazards"],"summary":"Get Active Hazards","description":"Retrieve live physical hazards from NASA EONET v3.","operationId":"get_active_hazards_api_v1_hazards_active_get","parameters":[{"name":"category","in":"query","required":false,"schema":{"anyOf":[{"type":"string"},{"type":"null"}],"description":"Filter by category, e.g. severeStorms, floods, wildfires","title":"Category"},"description":"Filter by category, e.g. severeStorms, floods, wildfires"}],"responses":{"200":{"description":"Successful Response","content":{"application/json":{"schema":{"type":"object","additionalProperties":true,"title":"Response Get Active Hazards Api V1 Hazards Active Get"}}}},"422":{"description":"Validation Error","content":{"application/json":{"schema":{"$ref":"#/components/schemas/HTTPValidationError"}}}}}}},"/api/v1/hazards/analyze":{"post":{"tags":["hazards"],"summary":"Analyze Hazard Overlap","description":"Endpoint for performing spatial intersection of EONET hazards with project geometries.","operationId":"analyze_hazard_overlap_api_v1_hazards_analyze_post","responses":{"200":{"description":"Successful Response","content":{"application/json":{"schema":{"additionalProperties":true,"type":"object","title":"Response Analyze Hazard Overlap Api V1 Hazards Analyze Post"}}}}}}},"/api/v1/windrose/analyze":{"get":{"tags":["windrose"],"summary":"Analyze Windrose","description":"Fetch historical wind speed/direction and parse it into a Windrose radial matrix","operationId":"analyze_windrose_api_v1_windrose_analyze_get","parameters":[{"name":"lat","in":"query","required":true,"schema":{"type":"number","description":"Latitude of the location","title":"Lat"},"description":"Latitude of the location"},{"name":"lon","in":"query","required":true,"schema":{"type":"number","description":"Longitude of the location","title":"Lon"},"description":"Longitude of the location"},{"name":"years","in":"query","required":false,"schema":{"type":"integer","description":"Number of years of historical data to fetch (max 10)","default":1,"title":"Years"},"description":"Number of years of historical data to fetch (max 10)"},{"name":"season","in":"query","required":false,"schema":{"type":"string","description":"Season to filter by (all, winter, spring, summer, fall)","default":"all","title":"Season"},"description":"Season to filter by (all, winter, spring, summer, fall)"}],"responses":{"200":{"description":"Successful Response","content":{"application/json":{"schema":{"type":"object","additionalProperties":true,"title":"Response Analyze Windrose Api V1 Windrose Analyze Get"}}}},"422":{"description":"Validation Error","content":{"application/json":{"schema":{"$ref":"#/components/schemas/HTTPValidationError"}}}}}}},"/api/v1/geocode/":{"get":{"tags":["geocode"],"summary":"Geocode Location","description":"Convert a text address or location name into latitude and longitude coordinates.","operationId":"geocode_location_api_v1_geocode__get","parameters":[{"name":"address","in":"query","required":true,"schema":{"type":"string","description":"The name or address of the location to geocode","title":"Address"},"description":"The name or address of the location to geocode"}],"responses":{"200":{"description":"Successful Response","content":{"application/json":{"schema":{}}}},"422":{"description":"Validation Error","content":{"application/json":{"schema":{"$ref":"#/components/schemas/HTTPValidationError"}}}}}}},"/api/v1/change-detection/detect":{"post":{"tags":["change-detection"],"summary":"Detect changes between two GeoTIFF images","description":"Upload two temporal GeoTIFF images and receive a binary change-mask GeoTIFF.\n\nThe endpoint saves the uploads to a temp directory, runs ChangeStarDetection\ninference on the GPU (DirectML), and returns the resulting change mask.","operationId":"detect_changes_api_v1_change_detection_detect_post","requestBody":{"content":{"multipart/form-data":{"schema":{"$ref":"#/components/schemas/Body_detect_changes_api_v1_change_detection_detect_post"}}},"required":true},"responses":{"200":{"description":"Successful Response","content":{"application/json":{"schema":{}}}},"422":{"description":"Validation Error","content":{"application/json":{"schema":{"$ref":"#/components/schemas/HTTPValidationError"}}}}}}},"/api/v1/change-detection/download/{job_id}":{"get":{"tags":["change-detection"],"summary":"Download a generated change mask GeoTIFF","operationId":"download_mask_api_v1_change_detection_download__job_id__get","parameters":[{"name":"job_id","in":"path","required":true,"schema":{"type":"string","title":"Job Id"}}],"responses":{"200":{"description":"Successful Response","content":{"application/json":{"schema":{}}}},"422":{"description":"Validation Error","content":{"application/json":{"schema":{"$ref":"#/components/schemas/HTTPValidationError"}}}}}}},"/health":{"get":{"summary":"Health Check","operationId":"health_check_health_get","responses":{"200":{"description":"Successful Response","content":{"application/json":{"schema":{}}}}}}}},"components":{"schemas":{"AmenityItem":{"properties":{"name":{"type":"string","title":"Name","default":""},"lat":{"type":"number","title":"Lat"},"lon":{"type":"number","title":"Lon"},"distance":{"type":"number","title":"Distance"},"category":{"type":"string","title":"Category"},"type":{"type":"string","title":"Type"}},"type":"object","required":["lat","lon","distance","category","type"],"title":"AmenityItem"},"AnalysisRequest":{"properties":{"latitude":{"type":"number","title":"Latitude"},"longitude":{"type":"number","title":"Longitude"},"radius":{"type":"integer","title":"Radius","default":1000},"preferences":{"additionalProperties":{"type":"integer"},"type":"object","title":"Preferences"}},"type":"object","required":["latitude","longitude","preferences"],"title":"AnalysisRequest"},"AnalysisResponse":{"properties":{"overall_score":{"type":"integer","title":"Overall Score"},"radar_chart_data":{"additionalProperties":{"type":"integer"},"type":"object","title":"Radar Chart Data"},"amenities_found":{"items":{"$ref":"#/components/schemas/AmenityItem"},"type":"array","title":"Amenities Found"}},"type":"object","required":["overall_score","radar_chart_data","amenities_found"],"title":"AnalysisResponse"},"AuthResponse":{"properties":{"access_token":{"type":"string","title":"Access Token"},"refresh_token":{"type":"string","title":"Refresh Token"},"user":{"additionalProperties":true,"type":"object","title":"User"}},"type":"object","required":["access_token","refresh_token","user"],"title":"AuthResponse"},"Body_detect_changes_api_v1_change_detection_detect_post":{"properties":{"image_pre":{"type":"string","contentMediaType":"application/octet-stream","title":"Image Pre","description":"Pre-change GeoTIFF image"},"image_post":{"type":"string","contentMediaType":"application/octet-stream","title":"Image Post","description":"Post-change GeoTIFF image"}},"type":"object","required":["image_pre","image_post"],"title":"Body_detect_changes_api_v1_change_detection_detect_post"},"ChatRequest":{"properties":{"query":{"type":"string","title":"Query"},"session_id":{"anyOf":[{"type":"string"},{"type":"null"}],"title":"Session Id"},"model_name":{"type":"string","title":"Model Name","default":"gemini-2.0-flash"}},"type":"object","required":["query"],"title":"ChatRequest"},"ChatResponse":{"properties":{"response":{"type":"string","title":"Response"},"session_id":{"type":"string","title":"Session Id"},"map_data":{"anyOf":[{"additionalProperties":true,"type":"object"},{"type":"null"}],"title":"Map Data"}},"type":"object","required":["response","session_id"],"title":"ChatResponse"},"HTTPValidationError":{"properties":{"detail":{"items":{"$ref":"#/components/schemas/ValidationError"},"type":"array","title":"Detail"}},"type":"object","title":"HTTPValidationError"},"LoginRequest":{"properties":{"email":{"type":"string","format":"email","title":"Email"},"password":{"type":"string","title":"Password"}},"type":"object","required":["email","password"],"title":"LoginRequest"},"Persona":{"properties":{"id":{"type":"string","title":"Id"},"name":{"type":"string","title":"Name"},"description":{"type":"string","title":"Description"},"preferences":{"additionalProperties":{"type":"integer"},"type":"object","title":"Preferences"}},"type":"object","required":["id","name","description","preferences"],"title":"Persona"},"ProfileResponse":{"properties":{"id":{"type":"string","format":"uuid","title":"Id"},"full_name":{"anyOf":[{"type":"string"},{"type":"null"}],"title":"Full Name"},"organization_name":{"anyOf":[{"type":"string"},{"type":"null"}],"title":"Organization Name"},"role":{"type":"string","title":"Role"},"status":{"type":"string","title":"Status"}},"type":"object","required":["id","full_name","organization_name","role","status"],"title":"ProfileResponse"},"RefreshRequest":{"properties":{"refresh_token":{"type":"string","title":"Refresh Token"}},"type":"object","required":["refresh_token"],"title":"RefreshRequest"},"SignupRequest":{"properties":{"email":{"type":"string","format":"email","title":"Email"},"password":{"type":"string","title":"Password"},"full_name":{"type":"string","title":"Full Name"},"role":{"type":"string","title":"Role","default":"b2c"},"organization_name":{"anyOf":[{"type":"string"},{"type":"null"}],"title":"Organization Name"}},"type":"object","required":["email","password","full_name"],"title":"SignupRequest"},"SolarRequest":{"properties":{"latitude":{"type":"number","title":"Latitude"},"longitude":{"type":"number","title":"Longitude"},"radius_km":{"anyOf":[{"type":"number"},{"type":"null"}],"title":"Radius Km","default":0.5},"step_size_km":{"anyOf":[{"type":"number"},{"type":"null"}],"title":"Step Size Km","default":0.1},"system_size_kw":{"anyOf":[{"type":"number"},{"type":"null"}],"title":"System Size Kw"}},"type":"object","required":["latitude","longitude"],"title":"SolarRequest"},"SolarResponse":{"properties":{"latitude":{"type":"number","title":"Latitude"},"longitude":{"type":"number","title":"Longitude"},"analysis":{"additionalProperties":true,"type":"object","title":"Analysis"},"heatmap_grid":{"items":{"additionalProperties":true,"type":"object"},"type":"array","title":"Heatmap Grid"}},"type":"object","required":["latitude","longitude","analysis","heatmap_grid"],"title":"SolarResponse"},"ValidationError":{"properties":{"loc":{"items":{"anyOf":[{"type":"string"},{"type":"integer"}]},"type":"array","title":"Location"},"msg":{"type":"string","title":"Message"},"type":{"type":"string","title":"Error Type"},"input":{"title":"Input"},"ctx":{"type":"object","title":"Context"}},"type":"object","required":["loc","msg","type"],"title":"ValidationError"}},"securitySchemes":{"HTTPBearer":{"type":"http","scheme":"bearer"}}}}
```

## app\build.gradle.kts
`$lang
@file:Suppress("DEPRECATION")

import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinJvmCompile
import java.util.Properties
import java.io.FileInputStream

val localProperties = Properties().apply{
    val localPropertiesFile = rootProject.file("local.properties")
    if (localPropertiesFile.exists()) {
        load(FileInputStream(localPropertiesFile))
    }
}

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)

    kotlin("plugin.serialization") version "2.0.21"
}

android {
    namespace = "com.SemiColon.urbanplanner"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.SemiColon.urbanplanner"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
        val geminiKey = localProperties.getProperty("GEMINI_API_KEY") ?: ""
        buildConfigField("String", "GEMINI_API_KEY", "\"$geminiKey\"")

        val supabaseUrl = localProperties.getProperty("SUPABASE_URL") ?: ""
        buildConfigField("String", "SUPABASE_URL", "\"$supabaseUrl\"")

        val supabaseKey = localProperties.getProperty("SUPABASE_KEY") ?: ""
        buildConfigField("String", "SUPABASE_KEY", "\"$supabaseKey\"")
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

    kotlin {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }

    buildFeatures {
        buildConfig = true
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
    implementation(libs.androidx.navigation.runtime.ktx)
    implementation(libs.material3)
    implementation(libs.androidx.foundation)
    implementation(libs.androidx.navigation.compose)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    implementation("org.maplibre.gl:android-sdk:12.3.0")
    implementation("androidx.compose.material:material-icons-extended:1.7.6")

    // 1. Supabase & Network
    val supabaseVersion = "2.1.3" // Check for latest, but this is stable
    val ktorVersion = "2.3.8"

    implementation("io.github.jan-tennert.supabase:gotrue-kt:$supabaseVersion") // Auth
    implementation("io.github.jan-tennert.supabase:postgrest-kt:$supabaseVersion") // Database
    implementation("io.ktor:ktor-client-android:$ktorVersion") // Network Engine
    implementation("io.ktor:ktor-client-core:$ktorVersion")
    implementation("io.ktor:ktor-client-content-negotiation:$ktorVersion")
    implementation("io.ktor:ktor-serialization-kotlinx-json:$ktorVersion")
    implementation("io.ktor:ktor-client-auth:$ktorVersion")

    // 2. Serialization (Required for Supabase)
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.3")

    implementation("com.google.ai.client.generativeai:generativeai:0.9.0")
}
```

## app\proguard-rules.pro
`$lang
# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile
```

## app\src\main\AndroidManifest.xml
`$lang
<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android">

    <uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />
    <uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION" />
    <uses-permission android:name="android.permission.INTERNET" />

    <application
        android:usesCleartextTraffic="true"
        android:allowBackup="true"
        android:dataExtractionRules="@xml/data_extraction_rules"
        android:fullBackupContent="@xml/backup_rules"
        android:icon="@drawable/ic_launcher_foreground"
        android:label="@string/app_name"
        android:roundIcon="@drawable/ic_launcher_foreground"
        android:supportsRtl="true"
        android:theme="@style/Theme.UrbanPlanner">
        <activity
            android:name=".MainActivity"
            android:exported="true"
            android:windowSoftInputMode="adjustResize"
            android:theme="@style/Theme.UrbanPlanner">
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />
                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity>
    </application>

</manifest>
```

## app\src\main\java\com\SemiColon\urbanplanner\MainActivity.kt
`$lang
package com.SemiColon.urbanplanner

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.SemiColon.urbanplanner.navigation.AppNavigation
import org.maplibre.android.MapLibre


import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import com.SemiColon.urbanplanner.utils.PreferencesManager
import com.SemiColon.urbanplanner.ui.theme.UrbanPlannerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MapLibre.getInstance(this)
        SupabaseClient.initialize(this)
        enableEdgeToEdge()
        setContent {
            val preferencesManager = remember { PreferencesManager(this) }
            val currentTheme by preferencesManager.appTheme.collectAsState()
            val navController = rememberNavController()

            UrbanPlannerTheme(appTheme = currentTheme, darkTheme = true) {
                AppNavigation(navController = navController, preferencesManager = preferencesManager)
            }
        }
    }
}
```

## app\src\main\java\com\SemiColon\urbanplanner\SupabaseClient.kt
`$lang
package com.SemiColon.urbanplanner

import android.content.Context
import androidx.core.content.edit
import com.SemiColon.urbanplanner.BuildConfig
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.gotrue.Auth
import io.github.jan.supabase.gotrue.SessionManager
import io.github.jan.supabase.gotrue.user.UserSession
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

object SupabaseClient {
    private val SUPABASE_URL = BuildConfig.SUPABASE_URL
    private val SUPABASE_KEY = BuildConfig.SUPABASE_KEY

    lateinit var client: SupabaseClient

    fun initialize(context: Context) {
        client = createSupabaseClient(
            supabaseUrl = SUPABASE_URL,
            supabaseKey = SUPABASE_KEY
        ) {
            install(Auth) {
                // Tells Supabase to save login details to this file
                sessionManager = AndroidSessionManager(context)
            }
        }
    }
}

// --- Helper Class to Save/Load Login Data ---
class AndroidSessionManager(context: Context) : SessionManager {
    private val prefs = context.getSharedPreferences("supabase_auth", Context.MODE_PRIVATE)
    private val json = Json { ignoreUnknownKeys = true }

    override suspend fun saveSession(session: UserSession) {
        val sessionStr = json.encodeToString(session)
        // Clean KTX syntax (Auto-applies changes)
        prefs.edit {
            putString("session", sessionStr)
        }
    }

    override suspend fun loadSession(): UserSession? {
        val sessionStr = prefs.getString("session", null) ?: return null
        return try {
            json.decodeFromString(sessionStr)
        } catch (e: Exception) {
            android.util.Log.e("SupabaseAuth", "Failed to load saved session", e)
            null
        }
    }

    override suspend fun deleteSession() {
        // Clean KTX syntax (Auto-applies changes)
        prefs.edit {
            remove("session")
        }
    }
}
```

## app\src\main\java\com\SemiColon\urbanplanner\dashboard.kt
`$lang
package com.SemiColon.urbanplanner

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.DarkMode
import androidx.compose.material.icons.outlined.LightMode
import androidx.compose.material.icons.outlined.SettingsSystemDaydream
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.SemiColon.urbanplanner.ui.theme.ThemeManager
import com.SemiColon.urbanplanner.ui.theme.ThemeMode

data class DashboardAction(
    val title: String,
    val icon: ImageVector,
    val onClick: () -> Unit
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(
    onNavigateToMap: () -> Unit,
    onNavigateToChat: () -> Unit
) {
    val context = LocalContext.current
    val themeManager = remember { ThemeManager.getInstance(context) }
    val currentThemeMode by themeManager.themeMode.collectAsState()
    var showThemeMenu by remember { mutableStateOf(false) }

    val dashboardActions = listOf(
        DashboardAction("Start Mapping", Icons.Default.AddLocationAlt, onNavigateToMap),
        DashboardAction("AI Assistant", Icons.Default.Chat, onNavigateToChat),
        DashboardAction("Project History", Icons.Default.History) { /* History */ },
        DashboardAction("Analytics", Icons.Default.Analytics) { /* Analytics */ }
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("UrbanPlanner", fontWeight = FontWeight.Bold) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background,
                    titleContentColor = MaterialTheme.colorScheme.onBackground
                ),
                actions = {
                    Box {
                        IconButton(onClick = { showThemeMenu = true }) {
                            Icon(
                                imageVector = when (currentThemeMode) {
                                    ThemeMode.LIGHT -> Icons.Outlined.LightMode
                                    ThemeMode.DARK -> Icons.Outlined.DarkMode
                                    ThemeMode.SYSTEM -> Icons.Outlined.SettingsSystemDaydream
                                },
                                contentDescription = "Theme Toggle",
                                tint = MaterialTheme.colorScheme.onBackground
                            )
                        }
                        DropdownMenu(
                            expanded = showThemeMenu,
                            onDismissRequest = { showThemeMenu = false }
                        ) {
                            DropdownMenuItem(
                                text = { Text("Light") },
                                onClick = {
                                    themeManager.setThemeMode(ThemeMode.LIGHT)
                                    showThemeMenu = false
                                },
                                leadingIcon = { Icon(Icons.Outlined.LightMode, contentDescription = null) }
                            )
                            DropdownMenuItem(
                                text = { Text("Dark") },
                                onClick = {
                                    themeManager.setThemeMode(ThemeMode.DARK)
                                    showThemeMenu = false
                                },
                                leadingIcon = { Icon(Icons.Outlined.DarkMode, contentDescription = null) }
                            )
                            DropdownMenuItem(
                                text = { Text("System") },
                                onClick = {
                                    themeManager.setThemeMode(ThemeMode.SYSTEM)
                                    showThemeMenu = false
                                },
                                leadingIcon = { Icon(Icons.Outlined.SettingsSystemDaydream, contentDescription = null) }
                            )
                        }
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(padding)
                .padding(horizontal = 16.dp)
        ) {
            Spacer(modifier = Modifier.height(8.dp))
            // Greeting Card
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 24.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
                shape = RoundedCornerShape(20.dp)
            ) {
                Column(modifier = Modifier.padding(24.dp)) {
                    Text(
                        text = "Good Afternoon!",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Where shall we plan today?",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.8f)
                    )
                }
            }

            Text(
                text = "Quick Actions",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                items(dashboardActions) { item ->
                    DashboardActionButton(item)
                }
            }
        }
    }
}

@Composable
fun DashboardActionButton(item: DashboardAction) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
            .clip(RoundedCornerShape(20.dp))
            .clickable { item.onClick() },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .size(56.dp)
                    .background(
                        color = MaterialTheme.colorScheme.primary.copy(alpha = 0.1f),
                        shape = RoundedCornerShape(16.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = item.icon,
                    contentDescription = item.title,
                    modifier = Modifier.size(32.dp),
                    tint = MaterialTheme.colorScheme.primary
                )
            }
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = item.title,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}
```

## app\src\main\java\com\SemiColon\urbanplanner\agent\AgentRepository.kt
`$lang
package com.SemiColon.urbanplanner.agent

import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.content
import android.util.Log
import com.SemiColon.urbanplanner.BuildConfig
import kotlin.coroutines.cancellation.CancellationException
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.postgrest
import io.github.jan.supabase.gotrue.auth

class AgentRepository(private val supabase: SupabaseClient = com.SemiColon.urbanplanner.SupabaseClient.client) {

    private val generativeModel = GenerativeModel(
        modelName = "gemini-3.5-flash",
        apiKey = BuildConfig.GEMINI_API_KEY,
        systemInstruction = content {
            text(
                """
                You are GeoAI, a specialized digital assistant for the 'Urban Planner' application. 
                Your expertise is strictly limited to urban planning, land analysis, solar potential, 
                geographic hazards, real estate compliance, and city livability.
                
                CRITICAL RULES:
                1. You must ONLY answer questions related to land, geography, and urban planning.
                2. If a user asks about ANY unrelated topic (such as food, sports, movies, general trivia, or personal advice), you must politely refuse to answer.
                3. When refusing, briefly remind the user that you are specifically an Urban Planning assistant.
                4. Keep your answers concise and professional.
                """.trimIndent()
            )
        }
    )

    suspend fun sendMessage(query: String): String? {
        return try {
            val response = generativeModel.generateContent(query)
            response.text
        } catch (e: Exception) {
            if (e is CancellationException) throw e
            Log.e("AgentAPI", "Gemini error: ${e.message}", e)
            null
        }
    }

    suspend fun fetchChatHistory(): List<ChatMessage> {
        return try {
            val currentUser = supabase.auth.currentUserOrNull()?.id ?: return emptyList()
            
            val entities = supabase.postgrest["chat_messages"]
                .select { 
                    filter { eq("user_id", currentUser) }
                    order("created_at", order = io.github.jan.supabase.postgrest.query.Order.ASCENDING)
                }
                .decodeList<ChatMessageEntity>()

            entities.map { 
                ChatMessage(
                    id = it.id ?: "", 
                    text = it.text, 
                    isUser = it.isUser
                ) 
            }
        } catch (e: Exception) {
            Log.e("AgentAPI", "Failed to load history: ${e.message}")
            emptyList()
        }
    }

    suspend fun saveMessageToDb(text: String, isUser: Boolean) {
        try {
            val currentUser = supabase.auth.currentUserOrNull()?.id ?: return
            
            val entity = ChatMessageEntity(
                userId = currentUser,
                text = text,
                isUser = isUser
            )
            
            supabase.postgrest["chat_messages"].insert(entity)
        } catch (e: Exception) {
            Log.e("AgentAPI", "Failed to save message: ${e.message}")
        }
    }
}
```

## app\src\main\java\com\SemiColon\urbanplanner\agent\AgentViewModel.kt
`$lang
package com.SemiColon.urbanplanner.agent

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AgentViewModel : ViewModel() {
    
    private val repository = AgentRepository()

    private val _messages = MutableStateFlow<List<ChatMessage>>(emptyList())
    val messages: StateFlow<List<ChatMessage>> = _messages.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    init {
        loadHistory()
    }

    private fun loadHistory() {
        viewModelScope.launch {
            _isLoading.value = true
            val history = withContext(Dispatchers.IO) {
                repository.fetchChatHistory()
            }
            _messages.value = history
            _isLoading.value = false
        }
    }

    fun sendMessage(query: String) {
        if (query.isBlank()) return

        // 1. Update UI immediately
        val userMessage = ChatMessage(text = query, isUser = true)
        _messages.update { it + userMessage }

        viewModelScope.launch {
            _isLoading.value = true

            // 2. Save user message to Supabase
            launch(Dispatchers.IO) { repository.saveMessageToDb(query, true) }

            // 3. Request Gemini response
            val responseText = withContext(Dispatchers.IO) {
                repository.sendMessage(query)
            }

            if (responseText != null) {
                // 4. Update UI with AI response
                _messages.update {
                    it + ChatMessage(text = responseText, isUser = false)
                }
                // 5. Save AI response to Supabase
                launch(Dispatchers.IO) { repository.saveMessageToDb(responseText, false) }
            } else {
                _messages.update {
                    it + ChatMessage(
                        text = "Connection failed. Please check your network.",
                        isUser = false
                    )
                }
            }
            _isLoading.value = false
        }
    }
    
    fun clearSession() {
        _messages.value = emptyList()
    }
}
```

## app\src\main\java\com\SemiColon\urbanplanner\agent\ChatMessage.kt
`$lang
package com.SemiColon.urbanplanner.agent

import kotlinx.serialization.json.JsonObject

data class ChatMessage(
    val id: String = java.util.UUID.randomUUID().toString(),
    val text: String,
    val isUser: Boolean,
    val mapData: JsonObject? = null,
    val timestamp: Long = System.currentTimeMillis()
)
```

## app\src\main\java\com\SemiColon\urbanplanner\agent\ChatMessageEntity.kt
`$lang
package com.SemiColon.urbanplanner.agent

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ChatMessageEntity(
    @SerialName("id") val id: String? = null,
    @SerialName("user_id") val userId: String,
    @SerialName("text") val text: String,
    @SerialName("is_user") val isUser: Boolean,
    @SerialName("created_at") val createdAt: String? = null
)
```

## app\src\main\java\com\SemiColon\urbanplanner\agent\ChatScreen.kt
`$lang
package com.SemiColon.urbanplanner.agent

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.DeleteOutline
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.serialization.json.JsonObject
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatScreen(
    modifier: Modifier = Modifier,
    viewModel: AgentViewModel = viewModel(),
    onMapDataReceived: (JsonObject) -> Unit = {}
) {
    val messages by viewModel.messages.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    var inputText by remember { mutableStateOf("") }
    val listState = rememberLazyListState()

    // Side effect to trigger map data
    LaunchedEffect(messages) {
        val lastMessage = messages.lastOrNull()
        if (lastMessage != null && !lastMessage.isUser && lastMessage.mapData != null) {
            onMapDataReceived(lastMessage.mapData)
        }
    }

    // Auto-scroll to bottom when new messages arrive
    LaunchedEffect(messages.size) {
        if (messages.isNotEmpty()) {
            listState.animateScrollToItem(messages.size - 1)
        }
    }

    Scaffold(
        modifier = modifier
            .fillMaxSize()
            .systemBarsPadding()
            .imePadding(),
        topBar = {
            TopAppBar(
                title = { 
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.AutoAwesome,
                            contentDescription = "AI",
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(24.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("GeoAI Agent", fontWeight = FontWeight.SemiBold)
                    }
                },
                actions = {
                    IconButton(onClick = { viewModel.clearSession() }) {
                        Icon(
                            imageVector = Icons.Default.DeleteOutline,
                            contentDescription = "Clear Chat",
                            tint = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surfaceColorAtElevation(2.dp)
                )
            )
        },
        bottomBar = {
            Surface(
                tonalElevation = 4.dp,
                shadowElevation = 8.dp
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .navigationBarsPadding() // Add padding for bottom navigation bars
                        .padding(horizontal = 16.dp, vertical = 12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    OutlinedTextField(
                        value = inputText,
                        onValueChange = { inputText = it },
                        modifier = Modifier.weight(1f),
                        placeholder = { Text("Ask about an area...") },
                        shape = RoundedCornerShape(24.dp),
                        keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Sentences),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = MaterialTheme.colorScheme.primary,
                            unfocusedBorderColor = MaterialTheme.colorScheme.outline.copy(alpha = 0.5f)
                        ),
                        maxLines = 3
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    IconButton(
                        onClick = {
                            if (inputText.isNotBlank()) {
                                viewModel.sendMessage(inputText)
                                inputText = ""
                            }
                        },
                        modifier = Modifier
                            .size(48.dp)
                            .background(
                                color = if (inputText.isNotBlank()) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surfaceVariant,
                                shape = CircleShape
                            ),
                        enabled = inputText.isNotBlank()
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.Send,
                            contentDescription = "Send",
                            tint = if (inputText.isNotBlank()) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.5f)
                        )
                    }
                }
            }
        }
    ) { paddingValues ->
        LazyColumn(
            state = listState,
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(MaterialTheme.colorScheme.background)
                .padding(horizontal = 16.dp),
            contentPadding = PaddingValues(vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            if (messages.isEmpty() && !isLoading) {
                item {
                    Box(
                        modifier = Modifier
                            .fillParentMaxSize()
                            .padding(32.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Icon(
                                imageVector = Icons.Default.AutoAwesome,
                                contentDescription = null,
                                modifier = Modifier.size(64.dp),
                                tint = MaterialTheme.colorScheme.primary.copy(alpha = 0.6f)
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                            Text(
                                text = "Hi! I'm GeoAI. Ask me about urban planning, solar potential, or land analysis.",
                                style = MaterialTheme.typography.bodyLarge,
                                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }
            }

            items(messages) { message ->
                ChatBubble(message)
            }
            
            if (isLoading) {
                item {
                    AnimatedVisibility(
                        visible = true,
                        enter = fadeIn(),
                        exit = fadeOut()
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Start,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(36.dp)
                                    .clip(CircleShape)
                                    .background(MaterialTheme.colorScheme.primaryContainer),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    imageVector = Icons.Default.AutoAwesome,
                                    contentDescription = "AI Loading",
                                    tint = MaterialTheme.colorScheme.onPrimaryContainer,
                                    modifier = Modifier.size(20.dp)
                                )
                            }
                            Spacer(modifier = Modifier.width(8.dp))
                            Surface(
                                shape = RoundedCornerShape(16.dp, 16.dp, 16.dp, 0.dp),
                                color = MaterialTheme.colorScheme.surfaceVariant,
                            ) {
                                Row(
                                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp),
                                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    CircularProgressIndicator(
                                        modifier = Modifier.size(16.dp),
                                        strokeWidth = 2.dp,
                                        color = MaterialTheme.colorScheme.primary
                                    )
                                    Spacer(modifier = Modifier.width(4.dp))
                                    Text(
                                        "Thinking...",
                                        style = MaterialTheme.typography.bodyMedium,
                                        color = MaterialTheme.colorScheme.onSurfaceVariant
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ChatBubble(message: ChatMessage) {
    val isUser = message.isUser
    val timeFormatted = remember(message.timestamp) {
        SimpleDateFormat("hh:mm a", Locale.getDefault()).format(Date(message.timestamp))
    }

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = if (isUser) Arrangement.End else Arrangement.Start,
        verticalAlignment = Alignment.Bottom
    ) {
        if (!isUser) {
            Box(
                modifier = Modifier
                    .size(36.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primaryContainer),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.AutoAwesome,
                    contentDescription = "AI",
                    tint = MaterialTheme.colorScheme.onPrimaryContainer,
                    modifier = Modifier.size(20.dp)
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
        }

        Column(
            horizontalAlignment = if (isUser) Alignment.End else Alignment.Start
        ) {
            Surface(
                shape = RoundedCornerShape(
                    topStart = 20.dp,
                    topEnd = 20.dp,
                    bottomStart = if (isUser) 20.dp else 4.dp,
                    bottomEnd = if (isUser) 4.dp else 20.dp
                ),
                color = if (isUser) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surfaceVariant,
                modifier = Modifier.widthIn(max = 280.dp)
            ) {
                Text(
                    text = message.text,
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp),
                    color = if (isUser) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurfaceVariant,
                    style = MaterialTheme.typography.bodyLarge
                )
            }
            
            Spacer(modifier = Modifier.height(4.dp))
            
            Text(
                text = timeFormatted,
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f),
                modifier = Modifier.padding(horizontal = 4.dp)
            )
        }
    }
}
```

## app\src\main\java\com\SemiColon\urbanplanner\analysis\AmenitiesRepository.kt
`$lang
package com.SemiColon.urbanplanner.analysis

import com.SemiColon.urbanplanner.network.ApiClient
import com.SemiColon.urbanplanner.network.models.AnalysisRequest
import com.SemiColon.urbanplanner.network.models.AnalysisResponse
import com.SemiColon.urbanplanner.network.models.Persona
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody

class AmenitiesRepository {

    suspend fun getPersonas(): List<Persona> {
        return try {
            val response = ApiClient.client.get("${ApiClient.BASE_URL}/api/v1/amenities/personas")
            response.body<List<Persona>>()
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }

    suspend fun analyzeLocation(lat: Double, lon: Double, preferences: Map<String, Int>): AnalysisResponse? {
        return try {
            val request = AnalysisRequest(
                latitude = lat,
                longitude = lon,
                preferences = preferences
            )
            val response = ApiClient.client.post("${ApiClient.BASE_URL}/api/v1/amenities/analyze") {
                setBody(request)
            }
            response.body<AnalysisResponse>()
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}
```

## app\src\main\java\com\SemiColon\urbanplanner\analysis\AmenitiesViewModel.kt
`$lang
package com.SemiColon.urbanplanner.analysis

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.SemiColon.urbanplanner.network.models.AnalysisResponse
import com.SemiColon.urbanplanner.network.models.Persona
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AmenitiesViewModel : ViewModel() {
    private val repository = AmenitiesRepository()

    private val _personas = MutableStateFlow<List<Persona>>(emptyList())
    val personas: StateFlow<List<Persona>> = _personas.asStateFlow()

    private val _selectedPersona = MutableStateFlow<Persona?>(null)
    val selectedPersona: StateFlow<Persona?> = _selectedPersona.asStateFlow()

    // Map & Analysis State
    private val _selectedLocation = MutableStateFlow<Pair<Double, Double>?>(null)
    val selectedLocation: StateFlow<Pair<Double, Double>?> = _selectedLocation.asStateFlow()

    private val _analysisResult = MutableStateFlow<AnalysisResponse?>(null)
    val analysisResult: StateFlow<AnalysisResponse?> = _analysisResult.asStateFlow()

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    init {
        loadPersonas()
    }

    private fun loadPersonas() {
        viewModelScope.launch(kotlinx.coroutines.Dispatchers.IO) {
            android.util.Log.d("URBAN_DEBUG", "Attempting to fetch personas from backend...")
            try {
                val fetched = repository.getPersonas()
                
                if (fetched.isEmpty()) {
                    android.util.Log.e("URBAN_DEBUG", "Persona list is empty! Backend unreachable or DB is empty.")
                } else {
                    android.util.Log.d("URBAN_DEBUG", "Successfully loaded ${fetched.size} personas!")
                }
                
                _personas.value = fetched
                if (fetched.isNotEmpty()) {
                    _selectedPersona.value = fetched.first()
                }
            } catch (e: Exception) {
                android.util.Log.e("URBAN_DEBUG", "Failed to fetch personas: ${e.message}", e)
            }
        }
    }

    fun selectPersona(persona: Persona) {
        _selectedPersona.value = persona
        // Removed auto-trigger to allow explicit "Run Analysis" from bottom sheet
    }

    fun onMapLongClick(lat: Double, lon: Double) {
        _selectedLocation.value = Pair(lat, lon)
        analyzeDroppedPin(lat, lon)
    }

    // Keep the old signature for compatibility with maps.kt if needed, or change it
    fun analyzeLocation(lat: Double, lon: Double) {
        onMapLongClick(lat, lon)
    }

    private fun analyzeDroppedPin(lat: Double, lon: Double) {
        val currentPersona = _selectedPersona.value ?: return

        // ADDED Dispatchers.IO HERE!
        viewModelScope.launch(kotlinx.coroutines.Dispatchers.IO) {
            // Also added the "Sending" log so you can track it
            android.util.Log.d("URBAN_DEBUG", "Sending coordinates to backend...")

            _isLoading.value = true
            _errorMessage.value = null

            try {
                val result = repository.analyzeLocation(lat, lon, currentPersona.preferences)
                android.util.Log.d("URBAN_DEBUG", "Received response: $result")

                if (result != null) {
                    _analysisResult.value = result
                } else {
                    _errorMessage.value = "Failed to analyze location. The response might have been invalid or empty."
                }
            } catch (e: Exception) {
                android.util.Log.e("URBAN_DEBUG", "Network crashed: ${e.message}", e)
                _errorMessage.value = "Network error: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun clearResultsOnly() {
        _analysisResult.value = null
        _errorMessage.value = null
    }

    fun clearEntireMap() {
        _analysisResult.value = null
        _errorMessage.value = null
        _selectedLocation.value = null
    }
}
```

## app\src\main\java\com\SemiColon\urbanplanner\analysis\HazardsRepository.kt
`$lang
package com.SemiColon.urbanplanner.analysis

import com.SemiColon.urbanplanner.network.ApiClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.serialization.json.JsonObject

class HazardsRepository {

    suspend fun getActiveHazards(): JsonObject? {
        return try {
            val response = ApiClient.client.get("${ApiClient.BASE_URL}/api/v1/hazards/active")
            response.body<JsonObject>()
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    suspend fun getIllegalSocieties(): JsonObject? {
        return try {
            val response = ApiClient.client.get("${ApiClient.BASE_URL}/api/v1/compliance/illegal-societies")
            response.body<JsonObject>()
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}
```

## app\src\main\java\com\SemiColon\urbanplanner\analysis\HazardsViewModel.kt
`$lang
package com.SemiColon.urbanplanner.analysis

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.serialization.json.JsonObject

class HazardsViewModel : ViewModel() {
    private val repository = HazardsRepository()

    private val _hazardsData = MutableStateFlow<JsonObject?>(null)
    val hazardsData: StateFlow<JsonObject?> = _hazardsData.asStateFlow()

    private val _illegalSocietiesData = MutableStateFlow<JsonObject?>(null)
    val illegalSocietiesData: StateFlow<JsonObject?> = _illegalSocietiesData.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    fun fetchData() {
        if (_hazardsData.value != null && _illegalSocietiesData.value != null) return
        
        viewModelScope.launch(kotlinx.coroutines.Dispatchers.IO) {
            _isLoading.value = true
            try {
                _hazardsData.value = repository.getActiveHazards()
                _illegalSocietiesData.value = repository.getIllegalSocieties()
            } catch (e: Exception) {
                android.util.Log.e("URBAN_DEBUG", "Hazards Network crashed: ${e.message}", e)
            } finally {
                _isLoading.value = false
            }
        }
    }
}
```

## app\src\main\java\com\SemiColon\urbanplanner\analysis\RadarChart.kt
`$lang
package com.SemiColon.urbanplanner.analysis

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun RadarChart(
    data: Map<String, Int>,
    modifier: Modifier = Modifier,
    maxScore: Float = 100f
) {
    val textMeasurer = rememberTextMeasurer()
    val primaryColor = MaterialTheme.colorScheme.primary
    val onSurfaceColor = MaterialTheme.colorScheme.onSurface

    Canvas(modifier = modifier.fillMaxSize()) {
        val center = Offset(size.width / 2f, size.height / 2f)
        val radius = (size.minDimension / 2f) * 0.7f // leave room for text
        val keys = data.keys.toList()
        val numPoints = keys.size

        if (numPoints < 3) return@Canvas

        val angleStep = (2 * Math.PI / numPoints).toFloat()

        // Draw background web
        for (i in 1..5) {
            val r = radius * (i / 5f)
            val webPath = Path().apply {
                for (j in 0 until numPoints) {
                    val angle = j * angleStep - (Math.PI / 2).toFloat()
                    val x = center.x + r * cos(angle)
                    val y = center.y + r * sin(angle)
                    if (j == 0) moveTo(x, y) else lineTo(x, y)
                }
                close()
            }
            drawPath(
                path = webPath,
                color = onSurfaceColor.copy(alpha = 0.2f),
                style = Stroke(width = 1.dp.toPx())
            )
        }

        // Draw axes and labels
        for (i in 0 until numPoints) {
            val angle = i * angleStep - (Math.PI / 2).toFloat()
            val endX = center.x + radius * cos(angle)
            val endY = center.y + radius * sin(angle)
            
            drawLine(
                color = onSurfaceColor.copy(alpha = 0.2f),
                start = center,
                end = Offset(endX, endY),
                strokeWidth = 1.dp.toPx()
            )

            val labelRadius = radius * 1.2f
            val labelX = center.x + labelRadius * cos(angle)
            val labelY = center.y + labelRadius * sin(angle)

            val label = keys[i]
            val textLayoutResult = textMeasurer.measure(
                text = label,
                style = TextStyle(fontSize = 12.sp, color = onSurfaceColor)
            )

            drawText(
                textLayoutResult = textLayoutResult,
                topLeft = Offset(
                    x = labelX - textLayoutResult.size.width / 2f,
                    y = labelY - textLayoutResult.size.height / 2f
                )
            )
        }

        // Draw data polygon
        val dataPath = Path().apply {
            for (i in 0 until numPoints) {
                val score = (data[keys[i]]?.toFloat() ?: 0f) / maxScore
                val r = radius * score
                val angle = i * angleStep - (Math.PI / 2).toFloat()
                val x = center.x + r * cos(angle)
                val y = center.y + r * sin(angle)
                if (i == 0) moveTo(x, y) else lineTo(x, y)
            }
            close()
        }

        drawPath(
            path = dataPath,
            color = primaryColor.copy(alpha = 0.4f),
            style = Fill
        )
        drawPath(
            path = dataPath,
            color = primaryColor,
            style = Stroke(width = 2.dp.toPx())
        )
    }
}
```

## app\src\main\java\com\SemiColon\urbanplanner\analysis\SolarRepository.kt
`$lang
package com.SemiColon.urbanplanner.analysis

import com.SemiColon.urbanplanner.network.ApiClient
import com.SemiColon.urbanplanner.network.models.SolarRequest
import com.SemiColon.urbanplanner.network.models.SolarResponse
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import kotlinx.serialization.json.JsonObject

class SolarRepository {

    suspend fun analyzeSolar(lat: Double, lon: Double, systemSizeKw: Double): SolarResponse? {
        return try {
            val request = SolarRequest(
                latitude = lat,
                longitude = lon,
                systemSizeKw = systemSizeKw
            )
            val response = ApiClient.client.post("${ApiClient.BASE_URL}/api/v1/solar/analyze") {
                setBody(request)
            }
            response.body<SolarResponse>()
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    suspend fun getWindrose(lat: Double, lon: Double): JsonObject? {
        return try {
            val response = ApiClient.client.get("${ApiClient.BASE_URL}/api/v1/windrose/analyze") {
                parameter("lat", lat)
                parameter("lon", lon)
            }
            response.body<JsonObject>()
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}
```

## app\src\main\java\com\SemiColon\urbanplanner\analysis\SolarViewModel.kt
`$lang
package com.SemiColon.urbanplanner.analysis

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.SemiColon.urbanplanner.network.models.SolarResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.serialization.json.JsonObject

class SolarViewModel : ViewModel() {
    private val repository = SolarRepository()

    private val _systemSizeKw = MutableStateFlow(6.2)
    val systemSizeKw: StateFlow<Double> = _systemSizeKw.asStateFlow()

    private val _solarResult = MutableStateFlow<SolarResponse?>(null)
    val solarResult: StateFlow<SolarResponse?> = _solarResult.asStateFlow()

    private val _windroseResult = MutableStateFlow<JsonObject?>(null)
    val windroseResult: StateFlow<JsonObject?> = _windroseResult.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    fun updateSystemSize(size: Double) {
        _systemSizeKw.value = size
    }

    fun analyzeLocation(lat: Double, lon: Double) {
        viewModelScope.launch(kotlinx.coroutines.Dispatchers.IO) {
            _isLoading.value = true
            try {
                _solarResult.value = repository.analyzeSolar(lat, lon, _systemSizeKw.value)
                _windroseResult.value = repository.getWindrose(lat, lon)
            } catch (e: Exception) {
                android.util.Log.e("URBAN_DEBUG", "Solar Network crashed: ${e.message}", e)
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun clearAnalysis() {
        _solarResult.value = null
        _windroseResult.value = null
    }
}
```

## app\src\main\java\com\SemiColon\urbanplanner\database\AuthViewModel.kt
`$lang
package com.SemiColon.urbanplanner.database

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.SemiColon.urbanplanner.SupabaseClient
import io.github.jan.supabase.gotrue.auth
import io.github.jan.supabase.gotrue.providers.builtin.Email
import kotlinx.coroutines.launch
import android.util.Log
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put

class AuthViewModel : ViewModel() {

    var fullName by mutableStateOf("")
    var organizationName by mutableStateOf("")
    var email by mutableStateOf("")
    var password by mutableStateOf("")

    var isLoading by mutableStateOf(false)
    var errorMessage by mutableStateOf<String?>(null)
    var loginSuccess by mutableStateOf(false)

    fun onSignUp(onSuccess: () -> Unit) {
        if (email.isBlank() || password.isBlank()) {
            errorMessage = "Please enter both email and password."
            return
        }
        if (password.length < 6) {
            errorMessage = "Password must be at least 6 characters long."
            return
        }
        viewModelScope.launch {
            isLoading = true
            errorMessage = null
            try {
                Log.d("SupabaseAuth", "Attempting Signup for: $email")
                val metadata = buildJsonObject {
                    if (fullName.isNotBlank()) put("full_name", fullName.trim())
                    if (organizationName.isNotBlank()) put("organization_name", organizationName.trim())
                    put("role", "b2c")
                }
                SupabaseClient.client.auth.signUpWith(Email) {
                    email = this@AuthViewModel.email.trim()
                    password = this@AuthViewModel.password
                    data = metadata
                }
                Log.d("SupabaseAuth", "Signup Successful!")
                onSuccess()
            } catch (e: Exception) {
                Log.e("SupabaseAuth", "Signup Failed: ${e.message}")
                errorMessage = e.message ?: "An unexpected error occurred during signup."
            } finally {
                isLoading = false
            }
        }
    }

    fun onLogin(onSuccess: () -> Unit) {
        if (email.isBlank() || password.isBlank()) {
            errorMessage = "Please enter both email and password."
            return
        }
        viewModelScope.launch {
            isLoading = true
            errorMessage = null
            try {
                Log.d("SupabaseAuth", "Attempting Login for: $email")
                SupabaseClient.client.auth.signInWith(Email) {
                    email = this@AuthViewModel.email.trim()
                    password = this@AuthViewModel.password
                }
                Log.d("SupabaseAuth", "Login Successful!")
                loginSuccess = true
                onSuccess()
            } catch (e: Exception) {
                Log.e("SupabaseAuth", "Login Failed: ${e.message}")
                errorMessage = "Login failed: ${e.message}"
            } finally {
                isLoading = false
            }
        }
    }
}
```

## app\src\main\java\com\SemiColon\urbanplanner\login\Login.kt
`$lang
package com.SemiColon.urbanplanner.login

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.SemiColon.urbanplanner.database.AuthViewModel

@Composable
fun LoginScreen(
    onNavigateToSignup: () -> Unit = {},
    onNavigateToDashboard: () -> Unit = {},
    viewModel: AuthViewModel = viewModel()
) {
    val context = LocalContext.current

    LaunchedEffect(viewModel.errorMessage) {
        viewModel.errorMessage?.let { error ->
            Toast.makeText(context, error, Toast.LENGTH_LONG).show()
            viewModel.errorMessage = null
        }
    }

    LaunchedEffect(viewModel.loginSuccess) {
        if (viewModel.loginSuccess) {
            viewModel.loginSuccess = false
            onNavigateToDashboard()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Aesthetic Header
        Text(
            text = "Welcome Back",
            style = MaterialTheme.typography.displaySmall,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Login to continue mapping the future.",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f)
        )
        Spacer(modifier = Modifier.height(32.dp))

        // Glassy / Premium Card
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(24.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Email Field
                OutlinedTextField(
                    value = viewModel.email,
                    onValueChange = { viewModel.email = it },
                    label = { Text("Email") },
                    leadingIcon = { Icon(Icons.Default.Email, contentDescription = "Email") },
                    shape = RoundedCornerShape(12.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = MaterialTheme.colorScheme.primary,
                        unfocusedBorderColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f)
                    ),
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Password Field
                OutlinedTextField(
                    value = viewModel.password,
                    onValueChange = { viewModel.password = it },
                    label = { Text("Password") },
                    leadingIcon = { Icon(Icons.Default.Lock, contentDescription = "Password") },
                    visualTransformation = PasswordVisualTransformation(),
                    shape = RoundedCornerShape(12.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = MaterialTheme.colorScheme.primary,
                        unfocusedBorderColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f)
                    ),
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(32.dp))

                // Login Button
                Button(
                    onClick = {
                        viewModel.onLogin(onSuccess = {
                            Toast.makeText(context, "Login Successful!", Toast.LENGTH_SHORT).show()
                            onNavigateToDashboard()
                        })
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        contentColor = MaterialTheme.colorScheme.onPrimary
                    ),
                    enabled = !viewModel.isLoading
                ) {
                    if (viewModel.isLoading) {
                        CircularProgressIndicator(
                            color = MaterialTheme.colorScheme.onPrimary,
                            modifier = Modifier.size(24.dp)
                        )
                    } else {
                        Text(text = "Login", style = MaterialTheme.typography.titleMedium)
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Sign Up Link
        Text(
            text = "Don't have an account? Sign Up",
            color = MaterialTheme.colorScheme.primary,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier
                .clickable { onNavigateToSignup() }
                .padding(8.dp)
        )
    }
}
```

## app\src\main\java\com\SemiColon\urbanplanner\signup\Signup.kt
`$lang
package com.SemiColon.urbanplanner.signup

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.SemiColon.urbanplanner.database.AuthViewModel

@Composable
fun SignupScreen(
    onNavigateToLogin: () -> Unit = {},
    viewModel: AuthViewModel = viewModel()
) {
    val context = LocalContext.current
    var confirmPassword by remember { mutableStateOf("") }

    LaunchedEffect(viewModel.errorMessage) {
        viewModel.errorMessage?.let { error ->
            Toast.makeText(context, error, Toast.LENGTH_LONG).show()
            viewModel.errorMessage = null
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Aesthetic Header
        Text(
            text = "Create Account",
            style = MaterialTheme.typography.displaySmall,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Join us and map the future.",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f)
        )
        Spacer(modifier = Modifier.height(32.dp))

        // Glassy / Premium Card
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(24.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Full Name Field
                OutlinedTextField(
                    value = viewModel.fullName,
                    onValueChange = { viewModel.fullName = it },
                    label = { Text("Full Name") },
                    leadingIcon = { Icon(Icons.Default.Person, contentDescription = "Full Name") },
                    shape = RoundedCornerShape(12.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = MaterialTheme.colorScheme.primary,
                        unfocusedBorderColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f)
                    ),
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Email Field
                OutlinedTextField(
                    value = viewModel.email,
                    onValueChange = { viewModel.email = it },
                    label = { Text("Email") },
                    leadingIcon = { Icon(Icons.Default.Email, contentDescription = "Email") },
                    shape = RoundedCornerShape(12.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = MaterialTheme.colorScheme.primary,
                        unfocusedBorderColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f)
                    ),
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Password Field
                OutlinedTextField(
                    value = viewModel.password,
                    onValueChange = { viewModel.password = it },
                    label = { Text("Password") },
                    leadingIcon = { Icon(Icons.Default.Lock, contentDescription = "Password") },
                    visualTransformation = PasswordVisualTransformation(),
                    shape = RoundedCornerShape(12.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = MaterialTheme.colorScheme.primary,
                        unfocusedBorderColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f)
                    ),
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Confirm Password Field
                OutlinedTextField(
                    value = confirmPassword,
                    onValueChange = { confirmPassword = it },
                    label = { Text("Confirm Password") },
                    leadingIcon = { Icon(Icons.Default.Lock, contentDescription = "Confirm Password") },
                    visualTransformation = PasswordVisualTransformation(),
                    shape = RoundedCornerShape(12.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = MaterialTheme.colorScheme.primary,
                        unfocusedBorderColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f)
                    ),
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )

                Spacer(modifier = Modifier.height(32.dp))

                // Signup Button
                Button(
                    onClick = {
                        if (viewModel.password != confirmPassword) {
                            Toast.makeText(context, "Passwords do not match!", Toast.LENGTH_SHORT).show()
                        } else {
                            viewModel.onSignUp(onSuccess = {
                                Toast.makeText(context, "Account created! Please login.", Toast.LENGTH_LONG).show()
                                onNavigateToLogin()
                            })
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        contentColor = MaterialTheme.colorScheme.onPrimary
                    ),
                    enabled = !viewModel.isLoading
                ) {
                    if (viewModel.isLoading) {
                        CircularProgressIndicator(
                            color = MaterialTheme.colorScheme.onPrimary,
                            modifier = Modifier.size(24.dp)
                        )
                    } else {
                        Text(text = "Sign Up", style = MaterialTheme.typography.titleMedium)
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Sign In Link
        Text(
            text = "Already have an account? Sign In",
            color = MaterialTheme.colorScheme.primary,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier
                .clickable { onNavigateToLogin() }
                .padding(8.dp)
        )
    }
}
```

## app\src\main\java\com\SemiColon\urbanplanner\map\GeocodingService.kt
`$lang
package com.SemiColon.urbanplanner.map

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.android.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.client.statement.bodyAsText
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

object GeocodingService {
    private val client = HttpClient(Android) {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
            })
        }
    }

    suspend fun searchPlace(query: String): List<NominatimResult> {
        return try {
            val response = client.get("https://nominatim.openstreetmap.org/search") {
                url {
                    parameters.append("q", query)
                    parameters.append("format", "json")
                    parameters.append("limit", "5")
                    // Lock the search strictly to Pakistan!
                    parameters.append("countrycodes", "pk") 
                }
                header(io.ktor.http.HttpHeaders.UserAgent, "UrbanPlannerApp/1.0 (hamzamasood420@gmail.com")
            }
            if (response.status.value in 200..299) {
                response.body()
            } else {
                val errorText = response.bodyAsText()
                android.util.Log.e("URBAN_DEBUG", "Geocoding API error ${response.status}: $errorText")
                emptyList()
            }
        } catch (e: kotlinx.coroutines.CancellationException) {
            // Rethrow CancellationException so coroutines can cancel properly!
            throw e
        } catch (e: Exception) {
            android.util.Log.e("URBAN_DEBUG", "Geocoding Search Failed: ${e.message}", e)
            emptyList()
        }
    }
}
```

## app\src\main\java\com\SemiColon\urbanplanner\map\NominatimResult.kt
`$lang
package com.SemiColon.urbanplanner.map

import kotlinx.serialization.Serializable

@Serializable
data class NominatimResult(
    val place_id: Long,
    val lat: String,
    val lon: String,
    val display_name: String
)
```

## app\src\main\java\com\SemiColon\urbanplanner\map\maps.kt
`$lang
package com.SemiColon.urbanplanner.map

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.isSystemInDarkTheme
import com.SemiColon.urbanplanner.ui.theme.ThemeManager
import com.SemiColon.urbanplanner.ui.theme.ThemeMode
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.Layers
import androidx.compose.material.icons.filled.Map
import androidx.compose.material.icons.filled.MyLocation
import androidx.compose.material.icons.filled.Navigation
import androidx.compose.material.icons.filled.Public
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.material.icons.filled.HomeWork
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material.icons.filled.LocalHospital
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.Park
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Place
import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.lifecycle.viewmodel.compose.viewModel
import com.SemiColon.urbanplanner.analysis.AmenitiesViewModel
import com.SemiColon.urbanplanner.analysis.HazardsViewModel
import com.SemiColon.urbanplanner.analysis.RadarChart
import com.SemiColon.urbanplanner.analysis.SolarViewModel
import kotlinx.serialization.json.doubleOrNull
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.app.ActivityCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import org.maplibre.android.MapLibre
import org.maplibre.android.camera.CameraPosition
import org.maplibre.android.camera.CameraUpdateFactory
import org.maplibre.android.geometry.LatLng
import org.maplibre.android.location.LocationComponentActivationOptions
import org.maplibre.android.location.modes.CameraMode
import org.maplibre.android.location.modes.RenderMode
import org.maplibre.android.maps.MapLibreMap
import org.maplibre.android.maps.MapView
import org.maplibre.android.maps.Style
import org.maplibre.android.annotations.MarkerOptions
import org.maplibre.android.annotations.PolygonOptions
import org.maplibre.android.geometry.LatLng as MapLibreLatLng

enum class AnalysisMode { AMENITIES, SOLAR, HAZARDS }

// Updated Data Class to include Icons
data class MapStyle(
    val name: String,
    val url: String,
    val icon: ImageVector
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MapsScreen(
    amenitiesViewModel: AmenitiesViewModel = viewModel(),
    solarViewModel: SolarViewModel = viewModel(),
    hazardsViewModel: HazardsViewModel = viewModel()
) {
    val context = LocalContext.current

    val myApiKey = "u3q70g01iGsukPfVW10m" // Keep secret!

    // Define Styles with Icons
    val mapStyles = listOf(
        MapStyle("Street", "https://api.maptiler.com/maps/streets/style.json?key=$myApiKey", Icons.Default.Map),
        MapStyle("Satellite", "https://api.maptiler.com/maps/satellite/style.json?key=$myApiKey", Icons.Default.Public),
        MapStyle("Dark", "https://api.maptiler.com/maps/basic-v2-dark/style.json?key=$myApiKey", Icons.Default.DarkMode)
    )

    var currentStyle by remember { mutableStateOf(mapStyles[0]) }
    var isMenuExpanded by remember { mutableStateOf(false) }
    var mapInstance by remember { mutableStateOf<MapLibreMap?>(null) }
    var currentBearing by remember { mutableFloatStateOf(0f) } // Track rotation for compass icon
    
    var analysisMode by remember { mutableStateOf(AnalysisMode.AMENITIES) }

    // Track what the user wants to do
    var selectedLocation by remember { mutableStateOf<Pair<Double, Double>?>(null) }
    var showConfigSheet by remember { mutableStateOf(false) }
    
    val personas by amenitiesViewModel.personas.collectAsState()
    val selectedPersona by amenitiesViewModel.selectedPersona.collectAsState()

    // Permissions
    var hasLocationPermission by remember {
        mutableStateOf(ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED)
    }
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { isGranted -> hasLocationPermission = isGranted }
    )

    // Search State
    var searchQuery by remember { mutableStateOf("") }
    var searchResults by remember { mutableStateOf<List<NominatimResult>>(emptyList()) }

    // Debounced search via LaunchedEffect
    LaunchedEffect(searchQuery) {
        if (searchQuery.length > 2) {
            delay(500)
            // Push the network call to the background IO thread!
            searchResults = kotlinx.coroutines.withContext(kotlinx.coroutines.Dispatchers.IO) {
                GeocodingService.searchPlace(searchQuery)
            }
        } else {
            searchResults = emptyList()
        }
    }

    val themeManager = remember { ThemeManager.getInstance(context) }
    val currentThemeMode by themeManager.themeMode.collectAsState()
    val isSystemDark = isSystemInDarkTheme()
    val isAppInDarkMode = when (currentThemeMode) {
        ThemeMode.DARK -> true
        ThemeMode.LIGHT -> false
        ThemeMode.SYSTEM -> isSystemDark
    }

    LaunchedEffect(isAppInDarkMode) {
        if (currentStyle.name == "Street" || currentStyle.name == "Dark") {
             currentStyle = if (isAppInDarkMode) mapStyles[2] else mapStyles[0]
             mapInstance?.setStyle(currentStyle.url)
        }
    }

    // Track Map Bearing (Rotation) to spin the compass icon
    LaunchedEffect(mapInstance) {
        // Map is ready, setup long click
        mapInstance?.addOnMapLongClickListener { point ->
            // Drop a pin
            mapInstance?.clear() // Clear old markers
            mapInstance?.addMarker(MarkerOptions().position(point).title("Selected Location"))
            
            android.util.Log.d("URBAN_DEBUG", "Pin dropped! Opening configuration sheet...")
            
            selectedLocation = Pair(point.latitude, point.longitude)
            showConfigSheet = true
            true
        }
    }

    val analysisResult by amenitiesViewModel.analysisResult.collectAsState()
    val isAnalyzingAmenities by amenitiesViewModel.isLoading.collectAsState()
    
    val solarResult by solarViewModel.solarResult.collectAsState()
    val isAnalyzingSolar by solarViewModel.isLoading.collectAsState()
    
    val hazardsData by hazardsViewModel.hazardsData.collectAsState()
    val isAnalyzingHazards by hazardsViewModel.isLoading.collectAsState()
    
    var showBottomSheet by remember { mutableStateOf(false) }

    LaunchedEffect(analysisMode) {
        if (analysisMode == AnalysisMode.HAZARDS) {
            hazardsViewModel.fetchData()
        }
    }

    val errorMessage by amenitiesViewModel.errorMessage.collectAsState()

    LaunchedEffect(analysisResult, errorMessage, isAnalyzingAmenities, solarResult, isAnalyzingSolar) {
        if (analysisMode != AnalysisMode.HAZARDS && (isAnalyzingAmenities || analysisResult != null || errorMessage != null || isAnalyzingSolar || solarResult != null)) {
            showBottomSheet = true
        }
    }
    
    // Watch for solarResult to draw heatmap
    LaunchedEffect(solarResult) {
        if (solarResult != null) {
            val grid = solarResult!!.heatmapGrid
            grid.forEach { cell ->
                val cellLat = cell["latitude"]?.jsonPrimitive?.doubleOrNull ?: 0.0
                val cellLon = cell["longitude"]?.jsonPrimitive?.doubleOrNull ?: 0.0
                val score = cell["solar_score"]?.jsonPrimitive?.doubleOrNull ?: 0.0
                if (cellLat != 0.0 && cellLon != 0.0) {
                    val halfSize = 0.0005 // roughly 50m
                    val polygon = PolygonOptions()
                        .add(MapLibreLatLng(cellLat - halfSize, cellLon - halfSize))
                        .add(MapLibreLatLng(cellLat - halfSize, cellLon + halfSize))
                        .add(MapLibreLatLng(cellLat + halfSize, cellLon + halfSize))
                        .add(MapLibreLatLng(cellLat + halfSize, cellLon - halfSize))
                        .add(MapLibreLatLng(cellLat - halfSize, cellLon - halfSize))
                        .fillColor(if (score > 80) android.graphics.Color.parseColor("#88FFCC00") else android.graphics.Color.parseColor("#88FF6600"))
                    
                    mapInstance?.addPolygon(polygon)
                }
            }
        }
    }
    
    // Watch for hazardsData to draw markers
    LaunchedEffect(hazardsData) {
        if (analysisMode == AnalysisMode.HAZARDS && hazardsData != null) {
            val events = hazardsData!!["events"]?.jsonArray
            events?.forEach { event ->
                val title = event.jsonObject["title"]?.jsonPrimitive?.content ?: "Hazard"
                val geometries = event.jsonObject["geometries"]?.jsonArray
                geometries?.forEach { geo ->
                    val coords = geo.jsonObject["coordinates"]?.jsonArray
                    if (coords != null && coords.size >= 2) {
                        val lon = coords[0].jsonPrimitive.doubleOrNull ?: 0.0
                        val lat = coords[1].jsonPrimitive.doubleOrNull ?: 0.0
                        if (lat != 0.0 && lon != 0.0) {
                            mapInstance?.addMarker(
                                MarkerOptions()
                                    .position(MapLibreLatLng(lat, lon))
                                    .title("Hazard: $title")
                            )
                        }
                    }
                }
            }
        }
    }

    val mapView = rememberMapViewWithLifecycle()

    Box(modifier = Modifier.fillMaxSize()) {

        // 1. THE MAP
        AndroidView(
            factory = { mapView },
            modifier = Modifier.fillMaxSize(),
            update = { updatedMapView ->
                updatedMapView.getMapAsync { map ->
                    mapInstance = map
                    
                    // Set default zoomed-in view instantly
                    map.cameraPosition = CameraPosition.Builder()
                        .target(LatLng(33.573, 73.039)) 
                        .zoom(15.0)
                        .build()
                        
                    map.uiSettings.isLogoEnabled = false
                    map.uiSettings.isAttributionEnabled = false
                    map.uiSettings.isCompassEnabled = false // Hiding default compass to use custom one

                    if (map.style == null) {
                        map.setStyle(currentStyle.url) { style ->
                            enableLocationComponent(style, map, context)
                        }
                    }
                }
            }
        )

        // 4. SEARCH UI (Top Center)
        Column(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .fillMaxWidth()
                .padding(top = 16.dp, start = 16.dp, end = 16.dp) // Full width search bar
        ) {
            Surface(
                modifier = Modifier.fillMaxWidth().height(56.dp),
                shape = RoundedCornerShape(28.dp), // Pill shape
                shadowElevation = 6.dp,
                color = MaterialTheme.colorScheme.surface
            ) {
                TextField(
                    value = searchQuery,
                    onValueChange = { searchQuery = it },
                    placeholder = { Text("Search here", style = MaterialTheme.typography.bodyLarge, color = MaterialTheme.colorScheme.onSurface.copy(alpha=0.5f)) },
                    leadingIcon = { Icon(Icons.Default.Search, contentDescription = "Search", tint = MaterialTheme.colorScheme.onSurface.copy(alpha=0.7f)) },
                    modifier = Modifier.fillMaxSize(),
                    singleLine = true,
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,
                        disabledContainerColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        cursorColor = MaterialTheme.colorScheme.primary
                    ),
                    textStyle = MaterialTheme.typography.bodyLarge.copy(color = MaterialTheme.colorScheme.onSurface)
                )
            }

            if (searchResults.isNotEmpty()) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
                ) {
                    LazyColumn(
                        modifier = Modifier.heightIn(max = 200.dp)
                    ) {
                        items(searchResults) { result ->
                            Text(
                                text = result.display_name,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable {
                                        searchQuery = ""
                                        searchResults = emptyList()
                                        val lat = result.lat.toDoubleOrNull() ?: 0.0
                                        val lon = result.lon.toDoubleOrNull() ?: 0.0
                                        if (lat != 0.0 && lon != 0.0) {
                                            mapInstance?.animateCamera(
                                                CameraUpdateFactory.newLatLngZoom(
                                                    LatLng(lat, lon),
                                                    15.0
                                                ),
                                                1000
                                            )
                                        }
                                    }
                                    .padding(16.dp),
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                            HorizontalDivider(color = MaterialTheme.colorScheme.onSurface.copy(alpha=0.1f))
                        }
                    }
                }
            }

            // Persona Selector moved to Bottom Sheet
        }

// 2. LAYER BUTTON (Top Right - Floating nicely under search)
        Box(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(top = 90.dp, end = 16.dp)
        ) {
            FloatingActionButton(
                onClick = { isMenuExpanded = true },
                containerColor = MaterialTheme.colorScheme.surface,
                contentColor = MaterialTheme.colorScheme.onSurface,
                shape = RoundedCornerShape(14.dp),
                modifier = Modifier.size(48.dp),
                elevation = FloatingActionButtonDefaults.elevation(defaultElevation = 4.dp)
            ) {
                Icon(Icons.Default.Layers, contentDescription = "Layers")
            }

            // The Dropdown (Icons Only)
            MaterialTheme(shapes = MaterialTheme.shapes.copy(extraSmall = RoundedCornerShape(16.dp))) {
                DropdownMenu(
                    expanded = isMenuExpanded,
                    onDismissRequest = { isMenuExpanded = false },
                    modifier = Modifier
                        .background(MaterialTheme.colorScheme.surface)
                        .width(64.dp) // Force it to be narrow (icon width)
                ) {
                    mapStyles.forEach { style ->
                        DropdownMenuItem(
                            // Put the Icon here in the main 'text' slot to center it
                            text = {
                                Box(
                                    modifier = Modifier.fillMaxWidth(),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Icon(
                                        imageVector = style.icon,
                                        contentDescription = style.name,
                                        tint = if (currentStyle == style) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface.copy(alpha=0.6f)
                                    )
                                }
                            },
                            onClick = {
                                currentStyle = style
                                mapInstance?.setStyle(style.url)
                                isMenuExpanded = false
                            },
                            contentPadding = PaddingValues(vertical = 0.dp) // Tighten the spacing
                        )
                    }
                }
            }
        }
        // 3. NAVIGATION STACK (Bottom Right - Compass + GPS)
        Column(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(end = 16.dp, bottom = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(12.dp) // Space between North and GPS
        ) {

            // -- NORTH PIN BUTTON --
            FloatingActionButton(
                onClick = {
                    mapInstance?.animateCamera(
                        CameraUpdateFactory.newCameraPosition(
                            CameraPosition.Builder()
                                .bearing(0.0) // Reset to North
                                .build()
                        ),
                        500
                    )
                },
                containerColor = MaterialTheme.colorScheme.surface,
                contentColor = MaterialTheme.colorScheme.onSurface,
                shape = RoundedCornerShape(14.dp),
                modifier = Modifier.size(48.dp),
                elevation = FloatingActionButtonDefaults.elevation(defaultElevation = 4.dp)
            ) {
                // We can rotate this icon based on actual map bearing if we had the listener set up
                Icon(
                    imageVector = Icons.Default.Navigation,
                    contentDescription = "Reset North",
                    modifier = Modifier.rotate(-currentBearing) // Counter-rotate to point North
                )
            }

            // -- GPS LOCATION BUTTON (Robust Version) --
            FloatingActionButton(
                onClick = {
                    if (hasLocationPermission) {
                        val loc = mapInstance?.locationComponent

                        if (loc != null && loc.isLocationComponentActivated) {

                            // 1. Activate Tracking Mode First
                            // This forces the map to lock onto the user as soon as the hardware gets a GPS fix.
                            loc.cameraMode = CameraMode.TRACKING
                            loc.renderMode = RenderMode.COMPASS // COMPASS is generally better for the main GPS button

                            // 2. Check for immediate location for the smooth zoom animation
                            val userLocation = loc.lastKnownLocation

                            if (userLocation != null) {
                                mapInstance?.animateCamera(
                                    CameraUpdateFactory.newLatLngZoom(
                                        LatLng(userLocation.latitude, userLocation.longitude),
                                        15.0
                                    ),
                                    1000
                                )
                            } else {
                                // It will still automatically move to the user once the GPS locks on,
                                // but this lets them know the hardware is currently searching.
                                Toast.makeText(context, "Acquiring GPS lock...", Toast.LENGTH_SHORT).show()
                            }
                        } else {
                            val style = mapInstance?.style
                            if (mapInstance != null && style != null) {
                                enableLocationComponent(style, mapInstance!!, context)
                                Toast.makeText(context, "Location enabled. Pinpointing...", Toast.LENGTH_SHORT).show()
                            } else {
                                Toast.makeText(context, "Map not ready.", Toast.LENGTH_SHORT).show()
                            }
                        }
                    } else {
                        launcher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
                    }
                },
                containerColor = MaterialTheme.colorScheme.surface,
                contentColor = MaterialTheme.colorScheme.primary,
                shape = CircleShape, // Classic Google Maps GPS button is a perfect circle
                elevation = FloatingActionButtonDefaults.elevation(defaultElevation = 6.dp)
            ) {
                Icon(Icons.Default.MyLocation, contentDescription = "My Location")
            }
        }
        
        // 5. BOTTOM SHEET FOR SPATIAL ANALYSIS
        if (showBottomSheet) {
            ModalBottomSheet(
                onDismissRequest = { 
                    showBottomSheet = false 
                    amenitiesViewModel.clearResultsOnly()
                    solarViewModel.clearAnalysis()
                    mapInstance?.clear()
                },
                sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = false)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    if (isAnalyzingAmenities || isAnalyzingSolar) {
                        CircularProgressIndicator()
                        Spacer(modifier = Modifier.height(16.dp))
                        Text("Analyzing area...", style = MaterialTheme.typography.bodyLarge)
                    } else if (analysisMode == AnalysisMode.AMENITIES && analysisResult != null) {
                        val result = analysisResult!!
                        Text(
                            text = "Livability Score: ${result.overallScore ?: 0}/100",
                            style = MaterialTheme.typography.headlineMedium,
                            color = MaterialTheme.colorScheme.primary
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        
                        // Radar Chart
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(250.dp)
                                .padding(16.dp)
                        ) {
                            RadarChart(data = result.radarChartData ?: emptyMap())
                        }
                        
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = "Amenities Found: ${result.amenitiesFound?.size ?: 0}",
                            style = MaterialTheme.typography.titleMedium
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        
                        LazyColumn(modifier = Modifier.fillMaxWidth().heightIn(max = 300.dp)) {
                            items(result.amenitiesFound ?: emptyList()) { amenity ->
                                ListItem(
                                    headlineContent = { Text(amenity.name?.ifBlank { "Unknown ${amenity.type?.replace("_", " ")?.capitalize()}" } ?: "Unknown") },
                                    supportingContent = { Text("${(amenity.distance ?: 0.0).toInt()} meters away") },
                                    leadingContent = { 
                                        val icon = when (amenity.category) {
                                            "healthcare" -> Icons.Default.LocalHospital
                                            "education" -> Icons.Default.School
                                            "park", "recreation" -> Icons.Default.Park
                                            "food", "restaurant" -> Icons.Default.Restaurant
                                            "commercial", "shop" -> Icons.Default.ShoppingCart
                                            else -> Icons.Default.Place
                                        }
                                        Icon(icon, contentDescription = amenity.category, tint = MaterialTheme.colorScheme.primary)
                                    }
                                )
                                HorizontalDivider(color = MaterialTheme.colorScheme.onSurface.copy(alpha=0.1f))
                            }
                        }
                        Spacer(modifier = Modifier.height(32.dp))
                    } else if (analysisMode == AnalysisMode.AMENITIES && amenitiesViewModel.errorMessage.collectAsState().value != null) {
                        Text(
                            text = "Analysis Failed",
                            style = MaterialTheme.typography.headlineMedium,
                            color = MaterialTheme.colorScheme.error
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = amenitiesViewModel.errorMessage.collectAsState().value ?: "Unknown Error",
                            style = MaterialTheme.typography.bodyLarge
                        )
                        Spacer(modifier = Modifier.height(32.dp))
                    } else if (analysisMode == AnalysisMode.SOLAR && solarResult != null) {
                        val result = solarResult!!
                        val annualKwh = result.analysis["annual_kwh_produced"]?.jsonPrimitive?.doubleOrNull ?: 0.0
                        val savingsUsd = result.analysis["financial_savings_usd"]?.jsonPrimitive?.doubleOrNull ?: 0.0
                        
                        Text(
                            text = "Solar Potential",
                            style = MaterialTheme.typography.headlineMedium,
                            color = MaterialTheme.colorScheme.primary
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = "Annual Output: ${annualKwh} kWh",
                            style = MaterialTheme.typography.titleLarge
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "Estimated Savings: $${savingsUsd} / year",
                            style = MaterialTheme.typography.titleMedium,
                            color = MaterialTheme.colorScheme.secondary
                        )
                        Spacer(modifier = Modifier.height(32.dp))
                    } else if (analysisMode == AnalysisMode.HAZARDS) {
                        Text(
                            text = "Hazards & Compliance",
                            style = MaterialTheme.typography.headlineMedium,
                            color = MaterialTheme.colorScheme.error
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        if (isAnalyzingHazards) {
                            CircularProgressIndicator()
                            Spacer(modifier = Modifier.height(16.dp))
                            Text("Fetching active hazards and societies...")
                        } else {
                            val activeHazardsCount = hazardsData?.get("events")?.jsonArray?.size ?: 0
                            Text(
                                text = "Active NASA Hazards: $activeHazardsCount",
                                style = MaterialTheme.typography.titleLarge
                            )
                            Spacer(modifier = Modifier.height(32.dp))
                        }
                    }
                }
            }
        }
        // 6. ANALYSIS CONFIGURATION SHEET
        if (showConfigSheet) {
            AnalysisConfigurationSheet(
                personas = personas,
                selectedMode = analysisMode,
                onModeChange = { analysisMode = it },
                selectedPersona = selectedPersona,
                onPersonaChange = { amenitiesViewModel.selectPersona(it) },
                onRunAnalysis = {
                    showConfigSheet = false
                    selectedLocation?.let { loc ->
                        if (analysisMode == AnalysisMode.AMENITIES) {
                            selectedPersona?.let { persona ->
                                amenitiesViewModel.selectPersona(persona)
                                amenitiesViewModel.analyzeLocation(loc.first, loc.second)
                            }
                        } else if (analysisMode == AnalysisMode.SOLAR) {
                            solarViewModel.analyzeLocation(loc.first, loc.second)
                        } else if (analysisMode == AnalysisMode.HAZARDS) {
                            hazardsViewModel.fetchData()
                        }
                    }
                },
                onDismiss = { showConfigSheet = false }
            )
        }
    }
}

// ... (Keep existing enableLocationComponent and rememberMapViewWithLifecycle functions exactly the same)
private fun enableLocationComponent(style: Style, map: MapLibreMap, context: Context) {
    if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
        try {
            val locationComponent = map.locationComponent
            val options = LocationComponentActivationOptions.builder(context, style)
                .useDefaultLocationEngine(true)
                .build()
            locationComponent.activateLocationComponent(options)
            locationComponent.isLocationComponentEnabled = true
            locationComponent.cameraMode = CameraMode.TRACKING
            locationComponent.renderMode = RenderMode.COMPASS
            locationComponent.zoomWhileTracking(15.0)
        } catch (e: Exception) { e.printStackTrace() }
    }
}

@Composable
fun rememberMapViewWithLifecycle(): MapView {
    val context = LocalContext.current
    val mapView = remember { MapView(context) }
    val lifecycle = LocalLifecycleOwner.current.lifecycle
    val observer = remember {
        LifecycleEventObserver { _, event ->
            when (event) {
                Lifecycle.Event.ON_CREATE -> mapView.onCreate(Bundle())
                Lifecycle.Event.ON_START -> mapView.onStart()
                Lifecycle.Event.ON_RESUME -> mapView.onResume()
                Lifecycle.Event.ON_PAUSE -> mapView.onPause()
                Lifecycle.Event.ON_STOP -> mapView.onStop()
                Lifecycle.Event.ON_DESTROY -> mapView.onDestroy()
                else -> {}
            }
        }
    }
    DisposableEffect(lifecycle) {
        lifecycle.addObserver(observer)
        onDispose { lifecycle.removeObserver(observer) }
    }
    return mapView
}

@Preview(showBackground = true)
@Composable
fun MapsScreenPreview() {
    MapsScreen()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AnalysisConfigurationSheet(
    personas: List<com.SemiColon.urbanplanner.network.models.Persona>,
    selectedMode: AnalysisMode,
    onModeChange: (AnalysisMode) -> Unit,
    selectedPersona: com.SemiColon.urbanplanner.network.models.Persona?,
    onPersonaChange: (com.SemiColon.urbanplanner.network.models.Persona) -> Unit,
    onRunAnalysis: () -> Unit,
    onDismiss: () -> Unit
) {
    ModalBottomSheet(onDismissRequest = onDismiss) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = "Configure Analysis",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = androidx.compose.ui.text.font.FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(16.dp))

            // 1. Select Analysis Type (Amenities, Solar, Hazards)
            Text("Analysis Type", style = MaterialTheme.typography.labelLarge)
            androidx.compose.foundation.lazy.LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(AnalysisMode.values()) { mode ->
                    FilterChip(
                        selected = selectedMode == mode,
                        onClick = { onModeChange(mode) },
                        label = { Text(mode.name.lowercase().replaceFirstChar { it.uppercase() }) }
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // 2. Select Persona (Only show if Amenities is selected!)
            if (selectedMode == AnalysisMode.AMENITIES) {
                Text("Select Persona", style = MaterialTheme.typography.labelLarge)
                if (personas.isEmpty()) {
                    Text(
                        text = "Loading personas...",
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                } else {
                    androidx.compose.foundation.lazy.LazyRow(
                        modifier = Modifier.padding(top = 8.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(personas) { persona ->
                            FilterChip(
                                selected = selectedPersona == persona,
                                onClick = { onPersonaChange(persona) },
                                label = { Text(persona.name) }
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.height(24.dp))
            } else {
                // Add some spacing if persona row is hidden
                Spacer(modifier = Modifier.height(16.dp))
            }

            // 3. The Run Button
            Button(
                onClick = onRunAnalysis,
                modifier = Modifier.fillMaxWidth(),
                enabled = selectedMode != AnalysisMode.AMENITIES || selectedPersona != null
            ) {
                Text("Run Analysis")
            }
            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}
```

## app\src\main\java\com\SemiColon\urbanplanner\navigation\Navigation.kt
`$lang
package com.SemiColon.urbanplanner.navigation
import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.outlined.Chat
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.NavHostController
import com.SemiColon.urbanplanner.DashboardScreen
import com.SemiColon.urbanplanner.settings.SettingsScreen
import com.SemiColon.urbanplanner.settings.ProfileScreen
import com.SemiColon.urbanplanner.login.LoginScreen
import com.SemiColon.urbanplanner.map.MapsScreen
import com.SemiColon.urbanplanner.signup.SignupScreen
import com.SemiColon.urbanplanner.utils.PreferencesManager
import com.SemiColon.urbanplanner.agent.ChatScreen
object Routes {
    const val LOGIN_SCREEN = "login_screen"
    const val SIGNUP_SCREEN = "signup_screen"
    const val DASHBOARD_SCREEN = "dashboard_screen"
    const val MAP_SCREEN = "map_screen"
    const val SPLASH_SCREEN = "splash_screen"
    const val SETTINGS_SCREEN = "settings_screen"
    const val PROFILE_SCREEN = "profile_screen"
    const val CHAT_SCREEN = "chat_screen"
}

@Composable
fun AppNavigation(navController: NavHostController, preferencesManager: PreferencesManager) {
    // Get current destination to highlight the correct bottom nav item
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    // List of screens that should NOT show the bottom bar (e.g., Splash, Login, Signup, Profile)
    val hideBottomBarRoutes = listOf(Routes.SPLASH_SCREEN, Routes.LOGIN_SCREEN, Routes.SIGNUP_SCREEN, Routes.PROFILE_SCREEN)
    Scaffold(
        bottomBar = {
            // Only show the BottomBar if we are on a main app screen
            if (currentRoute !in hideBottomBarRoutes) {
                NavigationBar {
                    NavigationBarItem(
                        selected = currentRoute == Routes.DASHBOARD_SCREEN,
                        onClick = {
                            navController.navigate(Routes.DASHBOARD_SCREEN) {
                                popUpTo(0)
                                launchSingleTop = true
                            }
                        },
                        label = { Text("Home") },
                        icon = { Icon(Icons.Outlined.Home, contentDescription = "Home") }
                    )
                    NavigationBarItem(
                        selected = currentRoute == Routes.MAP_SCREEN,
                        onClick = {
                            navController.navigate(Routes.MAP_SCREEN) {
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        label = { Text("Map") },
                        icon = { Icon(Icons.Outlined.LocationOn, contentDescription = "Map") }
                    )
                    NavigationBarItem(
                        selected = currentRoute == Routes.CHAT_SCREEN,
                        onClick = {
                            navController.navigate(Routes.CHAT_SCREEN) {
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        label = { Text("AI Chat") },
                        icon = { Icon(Icons.Outlined.Chat, contentDescription = "AI Chat") }
                    )
                    NavigationBarItem(
                        selected = currentRoute == Routes.SETTINGS_SCREEN,
                        onClick = {
                            navController.navigate(Routes.SETTINGS_SCREEN) {
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        label = { Text("Settings") },
                        icon = { Icon(Icons.Outlined.Settings, contentDescription = "Settings") }
                    )
                }
            }
        }
    ) { innerPadding ->
        // The innerPadding ensures content (like the Map) isn't covered by the BottomBar
        NavHost(
            navController = navController,
            startDestination = Routes.SPLASH_SCREEN,
            modifier = Modifier.padding(innerPadding),
            enterTransition = { fadeIn(animationSpec = tween(300)) + slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Left, tween(300)) },
            exitTransition = { fadeOut(animationSpec = tween(300)) + slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Left, tween(300)) },
            popEnterTransition = { fadeIn(animationSpec = tween(300)) + slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Right, tween(300)) },
            popExitTransition = { fadeOut(animationSpec = tween(300)) + slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Right, tween(300)) }
        ) {
            // --- Login Screen Route ---
            composable(Routes.LOGIN_SCREEN) {
                LoginScreen(
                    onNavigateToSignup = { navController.navigate(Routes.SIGNUP_SCREEN) },
                    onNavigateToDashboard = {
                        navController.navigate(Routes.DASHBOARD_SCREEN) {
                            popUpTo(Routes.LOGIN_SCREEN) { inclusive = true }
                        }
                    }
                )
            }
            // --- Signup Screen Route ---
            composable(Routes.SIGNUP_SCREEN) {
                SignupScreen(
                    onNavigateToLogin = {
                        navController.navigate(Routes.LOGIN_SCREEN) {
                            popUpTo(Routes.LOGIN_SCREEN) { inclusive = true }
                        }
                    }
                )
            }
            composable(Routes.DASHBOARD_SCREEN) {
                DashboardScreen(
                    onNavigateToMap = { navController.navigate(Routes.MAP_SCREEN) },
                    onNavigateToChat = { navController.navigate(Routes.CHAT_SCREEN) }
                )
            }
            // --- Map Screen Route ---
            composable(Routes.MAP_SCREEN) {
                MapsScreen()
            }
            // --- Settings Screen Route ---
            composable(Routes.SETTINGS_SCREEN) {
                SettingsScreen(navController = navController, preferencesManager = preferencesManager)
            }
            // --- Profile Screen Route ---
            composable(Routes.PROFILE_SCREEN) {
                ProfileScreen(navController = navController)
            }
            // --- Splash Screen Route ---
            composable(Routes.SPLASH_SCREEN) {
                SplashScreen(
                    onNavigateToDashboard = {
                        navController.navigate(Routes.DASHBOARD_SCREEN) {
                            popUpTo(Routes.SPLASH_SCREEN) { inclusive = true }
                        }
                    },
                    onNavigateToLogin = {
                        navController.navigate(Routes.LOGIN_SCREEN) {
                            popUpTo(Routes.SPLASH_SCREEN) { inclusive = true }
                        }
                    }
                )
            }
            // --- Chat Screen Route ---
            composable(Routes.CHAT_SCREEN) {
                ChatScreen(
                    onMapDataReceived = { mapData ->
                        navController.navigate(Routes.MAP_SCREEN)
                    }
                )
            }
        }
    }
}
```

## app\src\main\java\com\SemiColon\urbanplanner\navigation\SplashScreen.kt
`$lang
package com.SemiColon.urbanplanner.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Map
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.SemiColon.urbanplanner.SupabaseClient
import io.github.jan.supabase.gotrue.auth
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    onNavigateToDashboard: () -> Unit,
    onNavigateToLogin: () -> Unit
) {
    // 1. Show an aesthetic loading screen while checking auth state
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(120.dp)
                .background(
                    color = MaterialTheme.colorScheme.primary.copy(alpha = 0.1f),
                    shape = androidx.compose.foundation.shape.CircleShape
                ),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.Map,
                contentDescription = "App Logo",
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(64.dp)
            )
        }
        
        Spacer(modifier = Modifier.height(24.dp))
        
        Text(
            text = "UrbanPlanner",
            style = MaterialTheme.typography.displaySmall,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground
        )
        
        Spacer(modifier = Modifier.height(32.dp))
        
        CircularProgressIndicator(
            color = MaterialTheme.colorScheme.primary
        )
    }

    // 2. The Logic Check
    LaunchedEffect(Unit) {
        // Brief delay so the user can enjoy the splash screen aesthetic
        delay(1200)

        // Ask Supabase: "Do we have a saved user session?"
        val session = SupabaseClient.client.auth.currentSessionOrNull()

        if (session != null) {
            // YES -> User is registered. Go to Dashboard.
            onNavigateToDashboard()
        } else {
            // NO -> No user found. Go to Login.
            onNavigateToLogin()
        }
    }
}
```

## app\src\main\java\com\SemiColon\urbanplanner\network\ApiClient.kt
`$lang
package com.SemiColon.urbanplanner.network

import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.plugins.auth.*
import io.ktor.client.plugins.auth.providers.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.defaultRequest
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

object ApiClient {
    

    var BASE_URL = "http://192.168.1.7:8000"

    val client = HttpClient(Android) {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
                isLenient = true
            })
        }
        
        install(Auth) {
            bearer {
                loadTokens {
                    val token = TokenManager.getAccessToken()
                    if (token != null) {
                        BearerTokens(token, "")
                    } else {
                        null
                    }
                }
                sendWithoutRequest { request ->
                    // Do not send auth headers for auth routes if they exist
                    !request.url.encodedPath.contains("/auth/")
                }
            }
        }

        defaultRequest {
            contentType(ContentType.Application.Json)
        }
    }
}
```

## app\src\main\java\com\SemiColon\urbanplanner\network\TokenManager.kt
`$lang
package com.SemiColon.urbanplanner.network

import com.SemiColon.urbanplanner.SupabaseClient
import io.github.jan.supabase.gotrue.Auth

object TokenManager {
    fun getAccessToken(): String? {
        return try {
            val auth = SupabaseClient.client.pluginManager.getPlugin(Auth)
            auth.currentAccessTokenOrNull()
        } catch (e: Exception) {
            null
        }
    }
}
```

## app\src\main\java\com\SemiColon\urbanplanner\network\ProfileRepository.kt
`$lang
package com.SemiColon.urbanplanner.network

import android.util.Log
import com.SemiColon.urbanplanner.SupabaseClient
import com.SemiColon.urbanplanner.network.models.ProfileResponse
import io.github.jan.supabase.gotrue.auth
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.serialization.json.jsonPrimitive

class ProfileRepository {

    suspend fun getMyProfile(): ProfileResponse? {
        return try {
            val response = ApiClient.client.get("${ApiClient.BASE_URL}/api/v1/profiles/me")
            if (response.status.value in 200..299) {
                response.body<ProfileResponse>()
            } else {
                Log.w("ProfileRepo", "Backend returned status ${response.status}, falling back to Supabase session")
                getFallbackProfileFromSupabase()
            }
        } catch (e: Exception) {
            Log.w("ProfileRepo", "Failed to reach backend /api/v1/profiles/me: ${e.message}. Using Supabase session.")
            getFallbackProfileFromSupabase()
        }
    }

    private fun getFallbackProfileFromSupabase(): ProfileResponse? {
        return try {
            val user = SupabaseClient.client.auth.currentUserOrNull() ?: return null
            val metadata = user.userMetadata
            val fullName = metadata?.get("full_name")?.jsonPrimitive?.content 
                ?: metadata?.get("name")?.jsonPrimitive?.content 
                ?: user.email?.substringBefore("@")?.replaceFirstChar { it.uppercase() }
                ?: "Urban Planner User"
            val orgName = metadata?.get("organization_name")?.jsonPrimitive?.content ?: "Independent"
            val role = metadata?.get("role")?.jsonPrimitive?.content ?: "b2c"

            ProfileResponse(
                id = user.id,
                fullName = fullName,
                organizationName = orgName,
                role = role,
                status = "active"
            )
        } catch (e: Exception) {
            Log.e("ProfileRepo", "Error creating fallback profile: ${e.message}")
            null
        }
    }
}
```

## app\src\main\java\com\SemiColon\urbanplanner\network\models\AgentModels.kt
`$lang
package com.SemiColon.urbanplanner.network.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject

@Serializable
data class ChatRequest(
    val query: String,
    @SerialName("session_id") val sessionId: String? = null,
    @SerialName("model_name") val modelName: String = "gemini-2.0-flash"
)

@Serializable
data class ChatResponse(
    val response: String,
    @SerialName("session_id") val sessionId: String,
    @SerialName("map_data") val mapData: JsonObject? = null
)
```

## app\src\main\java\com\SemiColon\urbanplanner\network\models\AnalysisModels.kt
`$lang
package com.SemiColon.urbanplanner.network.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject

@Serializable
data class AmenityItem(
    val name: String? = "",
    val lat: Double? = 0.0,
    val lon: Double? = 0.0,
    val distance: Double? = 0.0,
    val category: String? = "unknown",
    val type: String? = "unknown"
)

@Serializable
data class AnalysisRequest(
    val latitude: Double,
    val longitude: Double,
    val radius: Int = 1000,
    val preferences: Map<String, Int>
)

@Serializable
data class AnalysisResponse(
    @SerialName("overall_score") val overallScore: Int? = 0,
    @SerialName("radar_chart_data") val radarChartData: Map<String, Int>? = emptyMap(),
    @SerialName("amenities_found") val amenitiesFound: List<AmenityItem>? = emptyList()
)

@Serializable
data class SolarRequest(
    val latitude: Double,
    val longitude: Double,
    @SerialName("radius_km") val radiusKm: Double? = 0.5,
    @SerialName("step_size_km") val stepSizeKm: Double? = 0.1,
    @SerialName("system_size_kw") val systemSizeKw: Double? = 6.2
)

@Serializable
data class SolarResponse(
    val latitude: Double,
    val longitude: Double,
    val analysis: JsonObject,
    @SerialName("heatmap_grid") val heatmapGrid: List<JsonObject>
)

@Serializable
data class Persona(
    val id: String,
    val name: String,
    val description: String,
    val preferences: Map<String, Int>
)
```

## app\src\main\java\com\SemiColon\urbanplanner\network\models\AuthModels.kt
`$lang
package com.SemiColon.urbanplanner.network.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject

@Serializable
data class LoginRequest(
    val email: String,
    val password: String
)

@Serializable
data class SignupRequest(
    val email: String,
    val password: String,
    @SerialName("full_name") val fullName: String,
    val role: String = "b2c",
    @SerialName("organization_name") val organizationName: String? = null
)

@Serializable
data class RefreshRequest(
    @SerialName("refresh_token") val refreshToken: String
)

@Serializable
data class AuthResponse(
    @SerialName("access_token") val accessToken: String,
    @SerialName("refresh_token") val refreshToken: String,
    val user: JsonObject
)

@Serializable
data class ProfileResponse(
    val id: String,
    @SerialName("full_name") val fullName: String?,
    @SerialName("organization_name") val organizationName: String?,
    val role: String,
    val status: String
)

@Serializable
data class ValidationError(
    val loc: List<String>,
    val msg: String,
    val type: String
)

@Serializable
data class HTTPValidationError(
    val detail: List<ValidationError>
)
```

## app\src\main\java\com\SemiColon\urbanplanner\settings\SettingsScreen.kt
`$lang
package com.SemiColon.urbanplanner.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.ColorLens
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PrivacyTip
import androidx.compose.material.icons.filled.Security
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.SemiColon.urbanplanner.SupabaseClient
import com.SemiColon.urbanplanner.navigation.Routes
import com.SemiColon.urbanplanner.ui.theme.AppTheme
import com.SemiColon.urbanplanner.utils.PreferencesManager
import io.github.jan.supabase.gotrue.auth
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    navController: NavHostController,
    preferencesManager: PreferencesManager,
    profileViewModel: ProfileViewModel = viewModel()
) {
    val currentTheme by preferencesManager.appTheme.collectAsState()
    val profile by profileViewModel.profile.collectAsState()
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Settings", fontWeight = FontWeight.Bold) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background,
                    titleContentColor = MaterialTheme.colorScheme.onBackground
                )
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp),
            contentPadding = PaddingValues(bottom = 24.dp)
        ) {
            item {
                // Profile Header (Clickable to open profile)
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(20.dp))
                        .clickable { navController.navigate(Routes.PROFILE_SCREEN) }
                        .padding(vertical = 12.dp, horizontal = 4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(72.dp)
                            .clip(CircleShape)
                            .background(MaterialTheme.colorScheme.primaryContainer),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = (profile?.fullName?.take(1) ?: "U").uppercase(),
                            style = MaterialTheme.typography.headlineMedium,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                    }
                    Spacer(modifier = Modifier.width(16.dp))
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = profile?.fullName ?: "Urban Planner User",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        Text(
                            text = profileViewModel.userEmail,
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                    Icon(
                        imageVector = Icons.Default.ChevronRight,
                        contentDescription = "View Profile",
                        tint = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            item {
                SettingsSection(title = "Account") {
                    SettingsItem(
                        icon = Icons.Default.AccountCircle,
                        title = "Profile",
                        subtitle = "View and edit your personal information",
                        onClick = { navController.navigate(Routes.PROFILE_SCREEN) }
                    )
                    SettingsItem(
                        icon = Icons.Default.Security,
                        title = "Security",
                        subtitle = "Password and authentication details",
                        onClick = { navController.navigate(Routes.PROFILE_SCREEN) }
                    )
                }
            }

            item {
                SettingsSection(title = "Appearance") {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Icon(
                                imageVector = Icons.Default.ColorLens,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.primary,
                                modifier = Modifier.size(28.dp)
                            )
                            Spacer(modifier = Modifier.width(16.dp))
                            Column {
                                Text(
                                    text = "App Theme",
                                    style = MaterialTheme.typography.titleMedium,
                                    fontWeight = FontWeight.SemiBold,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                                Spacer(modifier = Modifier.height(2.dp))
                                Text(
                                    text = "Choose your preferred color palette",
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                        }
                        
                        Spacer(modifier = Modifier.height(16.dp))

                        // Theme selection chips horizontally scrollable
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .horizontalScroll(rememberScrollState()),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            AppTheme.values().forEach { theme ->
                                FilterChip(
                                    selected = currentTheme == theme,
                                    onClick = { preferencesManager.setAppTheme(theme) },
                                    label = { Text(theme.name.lowercase().replaceFirstChar { it.uppercase() }) },
                                    colors = FilterChipDefaults.filterChipColors(
                                        selectedContainerColor = MaterialTheme.colorScheme.primary,
                                        selectedLabelColor = MaterialTheme.colorScheme.onPrimary
                                    )
                                )
                            }
                        }
                    }
                }
            }

            item {
                SettingsSection(title = "Preferences") {
                    SettingsItem(
                        icon = Icons.Default.Notifications,
                        title = "Notifications",
                        subtitle = "Manage alerts and updates",
                        onClick = { }
                    )
                }
            }

            item {
                SettingsSection(title = "About") {
                    SettingsItem(
                        icon = Icons.Default.Info,
                        title = "App Version",
                        subtitle = "UrbanPlanner v1.0.0",
                        onClick = { }
                    )
                    SettingsItem(
                        icon = Icons.Default.PrivacyTip,
                        title = "Privacy Policy",
                        subtitle = "Read our terms and conditions",
                        onClick = { }
                    )
                }
            }

            item {
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = {
                        coroutineScope.launch {
                            try {
                                SupabaseClient.client.auth.signOut()
                            } catch (e: Exception) {
                                // Ignore errors on logout
                            }
                            navController.navigate(Routes.LOGIN_SCREEN) {
                                popUpTo(0) { inclusive = true }
                            }
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.errorContainer,
                        contentColor = MaterialTheme.colorScheme.onErrorContainer
                    ),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.Logout,
                        contentDescription = "Logout",
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(
                        text = "Log Out", 
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.titleMedium
                    )
                }
            }
        }
    }
}

@Composable
fun SettingsSection(
    title: String,
    content: @Composable () -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleSmall,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(bottom = 8.dp, start = 16.dp)
        )
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)),
            elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
        ) {
            Column(modifier = Modifier.padding(vertical = 8.dp)) {
                content()
            }
        }
    }
}

@Composable
fun SettingsItem(
    icon: ImageVector,
    title: String,
    subtitle: String,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(horizontal = 16.dp, vertical = 14.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier.size(28.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onSurface
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = subtitle,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
        Icon(
            imageVector = Icons.Default.ChevronRight,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.size(24.dp)
        )
    }
}
```

## app\src\main\java\com\SemiColon\urbanplanner\settings\ProfileViewModel.kt
`$lang
package com.SemiColon.urbanplanner.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.SemiColon.urbanplanner.SupabaseClient
import com.SemiColon.urbanplanner.network.ProfileRepository
import com.SemiColon.urbanplanner.network.models.ProfileResponse
import io.github.jan.supabase.gotrue.auth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ProfileViewModel(
    private val repository: ProfileRepository = ProfileRepository()
) : ViewModel() {

    private val _profile = MutableStateFlow<ProfileResponse?>(null)
    val profile: StateFlow<ProfileResponse?> = _profile.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage.asStateFlow()

    val userEmail: String
        get() = SupabaseClient.client.auth.currentUserOrNull()?.email ?: "user@example.com"

    init {
        loadProfile()
    }

    fun loadProfile() {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null
            try {
                val result = withContext(Dispatchers.IO) {
                    repository.getMyProfile()
                }
                _profile.value = result
            } catch (e: Exception) {
                _errorMessage.value = e.message
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun signOut(onSuccess: () -> Unit) {
        viewModelScope.launch {
            try {
                withContext(Dispatchers.IO) {
                    SupabaseClient.client.auth.signOut()
                }
            } catch (e: Exception) {
                // Ignore logout exceptions
            }
            onSuccess()
        }
    }
}
```

## app\src\main\java\com\SemiColon\urbanplanner\settings\ProfileScreen.kt
`$lang
package com.SemiColon.urbanplanner.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.SemiColon.urbanplanner.navigation.Routes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    navController: NavHostController,
    viewModel: ProfileViewModel = viewModel()
) {
    val profile by viewModel.profile.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    var showLogoutDialog by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("My Profile", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { viewModel.loadProfile() }) {
                        Icon(
                            imageVector = Icons.Default.Refresh,
                            contentDescription = "Refresh Profile"
                        )
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background,
                    titleContentColor = MaterialTheme.colorScheme.onBackground
                )
            )
        }
    ) { paddingValues ->
        if (isLoading && profile == null) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(color = MaterialTheme.colorScheme.primary)
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background)
                    .padding(paddingValues)
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(20.dp),
                contentPadding = PaddingValues(vertical = 16.dp)
            ) {
                // 1. Profile Hero Card
                item {
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(24.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.7f)
                        ),
                        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(24.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(88.dp)
                                    .clip(CircleShape)
                                    .background(MaterialTheme.colorScheme.primary),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = (profile?.fullName?.take(1) ?: "U").uppercase(),
                                    style = MaterialTheme.typography.headlineLarge,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.onPrimary
                                )
                            }
                            Spacer(modifier = Modifier.height(16.dp))
                            Text(
                                text = profile?.fullName ?: "Urban Planner User",
                                style = MaterialTheme.typography.titleLarge,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onPrimaryContainer
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = viewModel.userEmail,
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.8f)
                            )
                            Spacer(modifier = Modifier.height(12.dp))
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(8.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                SuggestionChip(
                                    onClick = { },
                                    label = { 
                                        Text(
                                            (profile?.role ?: "b2c").uppercase(),
                                            fontWeight = FontWeight.SemiBold
                                        ) 
                                    },
                                    colors = SuggestionChipDefaults.suggestionChipColors(
                                        containerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.8f)
                                    )
                                )
                                SuggestionChip(
                                    onClick = { },
                                    label = { 
                                        Text(
                                            (profile?.status ?: "active").replaceFirstChar { it.uppercase() },
                                            fontWeight = FontWeight.SemiBold,
                                            color = Color(0xFF10B981)
                                        ) 
                                    },
                                    icon = {
                                        Box(
                                            modifier = Modifier
                                                .size(8.dp)
                                                .clip(CircleShape)
                                                .background(Color(0xFF10B981))
                                        )
                                    },
                                    colors = SuggestionChipDefaults.suggestionChipColors(
                                        containerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.8f)
                                    )
                                )
                            }
                        }
                    }
                }

                // 2. Personal Information Section
                item {
                    ProfileSection(title = "Personal Information") {
                        ProfileInfoItem(
                            icon = Icons.Default.Person,
                            label = "Full Name",
                            value = profile?.fullName ?: "Not specified"
                        )
                        HorizontalDivider(
                            modifier = Modifier.padding(horizontal = 16.dp),
                            color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.4f)
                        )
                        ProfileInfoItem(
                            icon = Icons.Default.Email,
                            label = "Email Address",
                            value = viewModel.userEmail
                        )
                        HorizontalDivider(
                            modifier = Modifier.padding(horizontal = 16.dp),
                            color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.4f)
                        )
                        ProfileInfoItem(
                            icon = Icons.Default.Fingerprint,
                            label = "User ID",
                            value = profile?.id?.take(18) + "..."
                        )
                    }
                }

                // 3. Organization & Role Section
                item {
                    ProfileSection(title = "Organization & Role") {
                        ProfileInfoItem(
                            icon = Icons.Default.Business,
                            label = "Organization",
                            value = profile?.organizationName ?: "Independent"
                        )
                        HorizontalDivider(
                            modifier = Modifier.padding(horizontal = 16.dp),
                            color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.4f)
                        )
                        ProfileInfoItem(
                            icon = Icons.Default.Badge,
                            label = "Assigned Role",
                            value = when (profile?.role?.lowercase()) {
                                "b2b", "urban_planner" -> "Professional Planner"
                                "enterprise" -> "Enterprise Account"
                                else -> "Standard User (B2C)"
                            }
                        )
                    }
                }

                // 4. Logout Action Button
                item {
                    Spacer(modifier = Modifier.height(8.dp))
                    Button(
                        onClick = { showLogoutDialog = true },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.errorContainer,
                            contentColor = MaterialTheme.colorScheme.onErrorContainer
                        ),
                        shape = RoundedCornerShape(16.dp)
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.Logout,
                            contentDescription = "Log Out",
                            modifier = Modifier.size(24.dp)
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        Text(
                            text = "Log Out",
                            fontWeight = FontWeight.Bold,
                            style = MaterialTheme.typography.titleMedium
                        )
                    }
                }
            }
        }
    }

    if (showLogoutDialog) {
        AlertDialog(
            onDismissRequest = { showLogoutDialog = false },
            title = { Text("Log Out") },
            text = { Text("Are you sure you want to log out of your account?") },
            confirmButton = {
                TextButton(
                    onClick = {
                        showLogoutDialog = false
                        viewModel.signOut(onSuccess = {
                            navController.navigate(Routes.LOGIN_SCREEN) {
                                popUpTo(0) { inclusive = true }
                            }
                        })
                    }
                ) {
                    Text("Log Out", color = MaterialTheme.colorScheme.error, fontWeight = FontWeight.Bold)
                }
            },
            dismissButton = {
                TextButton(onClick = { showLogoutDialog = false }) {
                    Text("Cancel")
                }
            }
        )
    }
}

@Composable
fun ProfileSection(
    title: String,
    content: @Composable () -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleSmall,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(bottom = 8.dp, start = 8.dp)
        )
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)
            ),
            elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
        ) {
            Column(modifier = Modifier.padding(vertical = 4.dp)) {
                content()
            }
        }
    }
}

@Composable
fun ProfileInfoItem(
    icon: ImageVector,
    label: String,
    value: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 14.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.1f)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = label,
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(22.dp)
            )
        }
        Spacer(modifier = Modifier.width(16.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = label,
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = value,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}
```

## app\src\main\java\com\SemiColon\urbanplanner\ui\theme\Color.kt
`$lang
package com.SemiColon.urbanplanner.ui.theme
import androidx.compose.ui.graphics.Color
// ============================================
// URBAN PLANNER APP COLOR SYSTEM
// ============================================
// === Core Brand Colors ===
val UrbanPrimary = Color(0xFF3B82F6) // Electric Blue - main actions
val UrbanPrimaryVariant = Color(0xFF2563EB) // Darker blue for emphasis
val UrbanSecondary = Color(0xFF10B981) // Emerald - secondary actions
val UrbanSecondaryVariant = Color(0xFF059669)
// === Semantic Colors - Urban Planning States ===
val CriticalRed = Color(0xFFEF4444) // Critical infrastructure issues
val CriticalRedDark = Color(0xFFDC2626)
val ConstructionOrange = Color(0xFFF59E0B) // Construction zones, maintenance
val ConstructionOrangeDark = Color(0xFFD97706)
val ParkGreen = Color(0xFF22C55E) // Parks, eco-zones
val ParkGreenDark = Color(0xFF16A34A)
val InfoBlue = Color(0xFF0EA5E9) // Information, updates, water bodies
val InfoBlueDark = Color(0xFF0284C7)
// === Population / Traffic Density Colors (for heatmaps) ===
val DensityLow = Color(0xFF93C5FD) // Light blue
val DensityMedium = Color(0xFF3B82F6) // Medium blue
val DensityHigh = Color(0xFF1D4ED8) // Dark blue
val DensitySevere = Color(0xFF7C3AED) // Purple
val DensityCritical = Color(0xFFDC2626) // Red
// === Dark Theme Base Colors ===
val DarkBackground = Color(0xFF0F172A) // Slate 900 - main background
val DarkSurface = Color(0xFF1E293B) // Slate 800 - cards/panels
val DarkSurfaceVariant = Color(0xFF334155) // Slate 700 - elevated surfaces
val DarkBorder = Color(0xFF475569) // Slate 600 - borders
// === Text Colors (Dark Theme) ===
val TextPrimary = Color(0xFFF8FAFC) // Slate 50 - main text
val TextSecondary = Color(0xFF94A3B8) // Slate 400 - secondary text
val TextMuted = Color(0xFF64748B) // Slate 500 - muted/disabled
val TextOnPrimary = Color(0xFFFFFFFF) // White on primary buttons
// === Light Theme Base Colors ===
val LightBackground = Color(0xFFF8FAFC) // Slate 50
val LightSurface = Color(0xFFFFFFFF) // Pure white
val LightSurfaceVariant = Color(0xFFF1F5F9) // Slate 100
val LightBorder = Color(0xFFE2E8F0) // Slate 200
// === Text Colors (Light Theme) ===
val LightTextPrimary = Color(0xFF0F172A) // Slate 900
val LightTextSecondary = Color(0xFF475569) // Slate 600
val LightTextMuted = Color(0xFF94A3B8) // Slate 400
// === Zoning Colors ===
val ResidentialZone = Color(0xFFD97706) // Amber
val CommercialZone = Color(0xFF0284C7) // Sky blue
val IndustrialZone = Color(0xFFDC2626) // Red
val MixedUseZone = Color(0xFF7C3AED) // Purple
// === Status Badge Colors ===
val StatusActive = Color(0xFF3B82F6) // Active project
val StatusWarning = Color(0xFFF59E0B) // Warning/Delay
val StatusPlanning = Color(0xFF7C3AED) // Under planning
val StatusCompleted = Color(0xFF22C55E) // Completed
// ============================================
// THEME COLOR SCHEMES
// ============================================
// Ocean Theme
val OceanPrimary = Color(0xFF0891B2)
val OceanSecondary = Color(0xFF06B6D4)
val OceanBackground = Color(0xFF0C1929)
val OceanSurface = Color(0xFF132338)
// Forest Theme
val ForestPrimary = Color(0xFF059669)
val ForestSecondary = Color(0xFF10B981)
val ForestBackground = Color(0xFF0A1F0F)
val ForestSurface = Color(0xFF14321A)
// Sunset Theme
val SunsetPrimary = Color(0xFFF97316)
val SunsetSecondary = Color(0xFFFB923C)
val SunsetBackground = Color(0xFF1C1410)
val SunsetSurface = Color(0xFF2E2118)
// Midnight Theme
val MidnightPrimary = Color(0xFF8B5CF6)
val MidnightSecondary = Color(0xFFA78BFA)
val MidnightBackground = Color(0xFF0A0A14)
val MidnightSurface = Color(0xFF14141F)
// Industrial Theme (High Contrast)
val IndustrialPrimary = Color(0xFFF59E0B)
val IndustrialSecondary = Color(0xFFD97706)
val IndustrialBackground = Color(0xFF1A1A1A)
val IndustrialSurface = Color(0xFF2D2D2D)
```

## app\src\main\java\com\SemiColon\urbanplanner\ui\theme\Theme.kt
`$lang
package com.SemiColon.urbanplanner.ui.theme
import android.app.Activity
import android.os.Build
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
enum class AppTheme {
    DEFAULT,
    OCEAN,
    FOREST,
    SUNSET,
    MIDNIGHT,
    INDUSTRIAL
}
private val DefaultDarkColorScheme = darkColorScheme(
    primary = UrbanPrimary,
    onPrimary = TextOnPrimary,
    primaryContainer = UrbanPrimaryVariant,
    secondary = UrbanSecondary,
    onSecondary = TextOnPrimary,
    secondaryContainer = UrbanSecondaryVariant,
    tertiary = InfoBlue,
    background = DarkBackground,
    onBackground = TextPrimary,
    surface = DarkSurface,
    onSurface = TextPrimary,
    surfaceVariant = DarkSurfaceVariant,
    onSurfaceVariant = TextSecondary,
    error = CriticalRed,
    onError = TextOnPrimary,
    outline = DarkBorder
)
private val OceanDarkColorScheme = darkColorScheme(primary = OceanPrimary, onPrimary = TextOnPrimary, primaryContainer = Color(0xFF065266), secondary = OceanSecondary, background = OceanBackground, onBackground = TextPrimary, surface = OceanSurface, onSurface = TextPrimary, error = CriticalRed, onError = TextOnPrimary)
private val ForestDarkColorScheme = darkColorScheme(primary = ForestPrimary, onPrimary = TextOnPrimary, primaryContainer = Color(0xFF047857), secondary = ForestSecondary, background = ForestBackground, onBackground = TextPrimary, surface = ForestSurface, onSurface = TextPrimary, error = CriticalRed, onError = TextOnPrimary)
private val SunsetDarkColorScheme = darkColorScheme(primary = SunsetPrimary, onPrimary = TextOnPrimary, primaryContainer = Color(0xFFEA580C), secondary = SunsetSecondary, background = SunsetBackground, onBackground = TextPrimary, surface = SunsetSurface, onSurface = TextPrimary, error = CriticalRed, onError = TextOnPrimary)
private val MidnightDarkColorScheme = darkColorScheme(primary = MidnightPrimary, onPrimary = TextOnPrimary, primaryContainer = Color(0xFF7C3AED), secondary = MidnightSecondary, background = MidnightBackground, onBackground = TextPrimary, surface = MidnightSurface, onSurface = TextPrimary, error = CriticalRed, onError = TextOnPrimary)
private val IndustrialDarkColorScheme = darkColorScheme(primary = IndustrialPrimary, onPrimary = TextOnPrimary, primaryContainer = ConstructionOrangeDark, secondary = IndustrialSecondary, background = IndustrialBackground, onBackground = TextPrimary, surface = IndustrialSurface, onSurface = TextPrimary, error = CriticalRed, onError = TextOnPrimary)
private val DefaultLightColorScheme = lightColorScheme(
    primary = UrbanPrimary,
    onPrimary = TextOnPrimary,
    primaryContainer = Color(0xFFDBEAFE),
    secondary = UrbanSecondary,
    onSecondary = TextOnPrimary,
    secondaryContainer = Color(0xFFD1FAE5),
    background = LightBackground,
    onBackground = LightTextPrimary,
    surface = LightSurface,
    onSurface = LightTextPrimary,
    surfaceVariant = LightSurfaceVariant,
    onSurfaceVariant = LightTextSecondary,
    error = CriticalRed,
    onError = TextOnPrimary,
    outline = LightBorder
)
@Composable
fun UrbanPlannerTheme(
    appTheme: AppTheme = AppTheme.DEFAULT,
    darkTheme: Boolean = true,
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> when (appTheme) {
            AppTheme.DEFAULT -> DefaultDarkColorScheme
            AppTheme.OCEAN -> OceanDarkColorScheme
            AppTheme.FOREST -> ForestDarkColorScheme
            AppTheme.SUNSET -> SunsetDarkColorScheme
            AppTheme.MIDNIGHT -> MidnightDarkColorScheme
            AppTheme.INDUSTRIAL -> IndustrialDarkColorScheme
        }
        else -> DefaultLightColorScheme
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
        }
    }
    MaterialTheme(colorScheme = colorScheme, typography = Typography, content = content)
}
object UrbanColors {
    val critical @Composable get() = CriticalRed
    val criticalDark @Composable get() = CriticalRedDark
    val construction @Composable get() = ConstructionOrange
    val constructionDark @Composable get() = ConstructionOrangeDark
    val park @Composable get() = ParkGreen
    val parkDark @Composable get() = ParkGreenDark
    val info @Composable get() = InfoBlue
    val infoDark @Composable get() = InfoBlueDark
    val densityLow @Composable get() = DensityLow
    val densityMedium @Composable get() = DensityMedium
    val densityHigh @Composable get() = DensityHigh
    val densitySevere @Composable get() = DensitySevere
    val densityCritical @Composable get() = DensityCritical
    val residential @Composable get() = ResidentialZone
    val commercial @Composable get() = CommercialZone
    val industrial @Composable get() = IndustrialZone
    val mixedUse @Composable get() = MixedUseZone
    val statusActive @Composable get() = StatusActive
    val statusWarning @Composable get() = StatusWarning
    val statusPlanning @Composable get() = StatusPlanning
    val statusCompleted @Composable get() = StatusCompleted
}
```

## app\src\main\java\com\SemiColon\urbanplanner\ui\theme\ThemeManager.kt
`$lang
package com.SemiColon.urbanplanner.ui.theme

import android.content.Context
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

enum class ThemeMode {
    SYSTEM, LIGHT, DARK
}

class ThemeManager(context: Context) {
    private val prefs = context.getSharedPreferences("urban_planner_prefs", Context.MODE_PRIVATE)

    private val _themeMode = MutableStateFlow(
        ThemeMode.valueOf(prefs.getString("theme_mode", ThemeMode.SYSTEM.name) ?: ThemeMode.SYSTEM.name)
    )
    val themeMode: StateFlow<ThemeMode> = _themeMode.asStateFlow()

    fun setThemeMode(mode: ThemeMode) {
        prefs.edit().putString("theme_mode", mode.name).apply()
        _themeMode.value = mode
    }

    companion object {
        @Volatile
        private var INSTANCE: ThemeManager? = null

        fun getInstance(context: Context): ThemeManager {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: ThemeManager(context.applicationContext).also { INSTANCE = it }
            }
        }
    }
}
```

## app\src\main\java\com\SemiColon\urbanplanner\ui\theme\Type.kt
`$lang
package com.SemiColon.urbanplanner.ui.theme
import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
// ============================================
// URBAN PLANNER TYPOGRAPHY SYSTEM
// ============================================
val UrbanFont = FontFamily.Default
val Typography =
        Typography(
                displayLarge = TextStyle(fontFamily = UrbanFont, fontWeight = FontWeight.Bold, fontSize = 57.sp, lineHeight = 64.sp, letterSpacing = (-0.25).sp),
                displayMedium = TextStyle(fontFamily = UrbanFont, fontWeight = FontWeight.Bold, fontSize = 45.sp, lineHeight = 52.sp, letterSpacing = 0.sp),
                displaySmall = TextStyle(fontFamily = UrbanFont, fontWeight = FontWeight.Bold, fontSize = 36.sp, lineHeight = 44.sp, letterSpacing = 0.sp),
                headlineLarge = TextStyle(fontFamily = UrbanFont, fontWeight = FontWeight.SemiBold, fontSize = 32.sp, lineHeight = 40.sp, letterSpacing = 0.sp),
                headlineMedium = TextStyle(fontFamily = UrbanFont, fontWeight = FontWeight.SemiBold, fontSize = 28.sp, lineHeight = 36.sp, letterSpacing = 0.sp),
                headlineSmall = TextStyle(fontFamily = UrbanFont, fontWeight = FontWeight.SemiBold, fontSize = 24.sp, lineHeight = 32.sp, letterSpacing = 0.sp),
                titleLarge = TextStyle(fontFamily = UrbanFont, fontWeight = FontWeight.SemiBold, fontSize = 22.sp, lineHeight = 28.sp, letterSpacing = 0.sp),
                titleMedium = TextStyle(fontFamily = UrbanFont, fontWeight = FontWeight.Medium, fontSize = 16.sp, lineHeight = 24.sp, letterSpacing = 0.15.sp),
                titleSmall = TextStyle(fontFamily = UrbanFont, fontWeight = FontWeight.Medium, fontSize = 14.sp, lineHeight = 20.sp, letterSpacing = 0.1.sp),
                bodyLarge = TextStyle(fontFamily = UrbanFont, fontWeight = FontWeight.Normal, fontSize = 16.sp, lineHeight = 24.sp, letterSpacing = 0.5.sp),
                bodyMedium = TextStyle(fontFamily = UrbanFont, fontWeight = FontWeight.Normal, fontSize = 14.sp, lineHeight = 20.sp, letterSpacing = 0.25.sp),
                bodySmall = TextStyle(fontFamily = UrbanFont, fontWeight = FontWeight.Normal, fontSize = 12.sp, lineHeight = 16.sp, letterSpacing = 0.4.sp),
                labelLarge = TextStyle(fontFamily = UrbanFont, fontWeight = FontWeight.Medium, fontSize = 14.sp, lineHeight = 20.sp, letterSpacing = 0.1.sp),
                labelMedium = TextStyle(fontFamily = UrbanFont, fontWeight = FontWeight.Medium, fontSize = 12.sp, lineHeight = 16.sp, letterSpacing = 0.5.sp),
                labelSmall = TextStyle(fontFamily = UrbanFont, fontWeight = FontWeight.Medium, fontSize = 11.sp, lineHeight = 16.sp, letterSpacing = 0.5.sp)
        )
object UrbanTextStyles {
    val criticalAlert = TextStyle(fontFamily = UrbanFont, fontWeight = FontWeight.ExtraBold, fontSize = 20.sp, lineHeight = 28.sp, letterSpacing = 1.sp)
    val statusBadge = TextStyle(fontFamily = UrbanFont, fontWeight = FontWeight.Bold, fontSize = 10.sp, lineHeight = 12.sp, letterSpacing = 0.5.sp)
    val metricValue = TextStyle(fontFamily = UrbanFont, fontWeight = FontWeight.Bold, fontSize = 40.sp, lineHeight = 48.sp, letterSpacing = (-1).sp)
    val metricLabel = TextStyle(fontFamily = UrbanFont, fontWeight = FontWeight.Medium, fontSize = 12.sp, lineHeight = 16.sp, letterSpacing = 0.5.sp)
    val timestamp = TextStyle(fontFamily = UrbanFont, fontWeight = FontWeight.Normal, fontSize = 11.sp, lineHeight = 14.sp, letterSpacing = 0.25.sp)
    val coordinates = TextStyle(fontFamily = FontFamily.Monospace, fontWeight = FontWeight.Normal, fontSize = 13.sp, lineHeight = 18.sp, letterSpacing = 0.sp)
}
```

## app\src\main\java\com\SemiColon\urbanplanner\utils\PreferencesManager.kt
`$lang
package com.SemiColon.urbanplanner.utils

import android.content.Context
import com.SemiColon.urbanplanner.ui.theme.AppTheme
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class PreferencesManager(context: Context) {
    private val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    private val _appTheme = MutableStateFlow(readThemeFromPrefs())
    val appTheme: StateFlow<AppTheme> = _appTheme.asStateFlow()

    fun setAppTheme(theme: AppTheme) {
        prefs.edit().putString(KEY_APP_THEME, theme.name).apply()
        _appTheme.value = theme
    }

    private fun readThemeFromPrefs(): AppTheme {
        val stored = prefs.getString(KEY_APP_THEME, AppTheme.DEFAULT.name)
        return runCatching { AppTheme.valueOf(stored ?: AppTheme.DEFAULT.name) }
            .getOrDefault(AppTheme.DEFAULT)
    }

    private companion object {
        const val PREFS_NAME = "urban_planner_prefs"
        const val KEY_APP_THEME = "app_theme"
    }
}
```

## app\src\main\res\drawable\ic_launcher_background.xml
`$lang
<?xml version="1.0" encoding="utf-8"?>
<vector xmlns:android="http://schemas.android.com/apk/res/android"
    android:width="108dp"
    android:height="108dp"
    android:viewportWidth="108"
    android:viewportHeight="108">
    <path
        android:fillColor="#3DDC84"
        android:pathData="M0,0h108v108h-108z" />
    <path
        android:fillColor="#00000000"
        android:pathData="M9,0L9,108"
        android:strokeWidth="0.8"
        android:strokeColor="#33FFFFFF" />
    <path
        android:fillColor="#00000000"
        android:pathData="M19,0L19,108"
        android:strokeWidth="0.8"
        android:strokeColor="#33FFFFFF" />
    <path
        android:fillColor="#00000000"
        android:pathData="M29,0L29,108"
        android:strokeWidth="0.8"
        android:strokeColor="#33FFFFFF" />
    <path
        android:fillColor="#00000000"
        android:pathData="M39,0L39,108"
        android:strokeWidth="0.8"
        android:strokeColor="#33FFFFFF" />
    <path
        android:fillColor="#00000000"
        android:pathData="M49,0L49,108"
        android:strokeWidth="0.8"
        android:strokeColor="#33FFFFFF" />
    <path
        android:fillColor="#00000000"
        android:pathData="M59,0L59,108"
        android:strokeWidth="0.8"
        android:strokeColor="#33FFFFFF" />
    <path
        android:fillColor="#00000000"
        android:pathData="M69,0L69,108"
        android:strokeWidth="0.8"
        android:strokeColor="#33FFFFFF" />
    <path
        android:fillColor="#00000000"
        android:pathData="M79,0L79,108"
        android:strokeWidth="0.8"
        android:strokeColor="#33FFFFFF" />
    <path
        android:fillColor="#00000000"
        android:pathData="M89,0L89,108"
        android:strokeWidth="0.8"
        android:strokeColor="#33FFFFFF" />
    <path
        android:fillColor="#00000000"
        android:pathData="M99,0L99,108"
        android:strokeWidth="0.8"
        android:strokeColor="#33FFFFFF" />
    <path
        android:fillColor="#00000000"
        android:pathData="M0,9L108,9"
        android:strokeWidth="0.8"
        android:strokeColor="#33FFFFFF" />
    <path
        android:fillColor="#00000000"
        android:pathData="M0,19L108,19"
        android:strokeWidth="0.8"
        android:strokeColor="#33FFFFFF" />
    <path
        android:fillColor="#00000000"
        android:pathData="M0,29L108,29"
        android:strokeWidth="0.8"
        android:strokeColor="#33FFFFFF" />
    <path
        android:fillColor="#00000000"
        android:pathData="M0,39L108,39"
        android:strokeWidth="0.8"
        android:strokeColor="#33FFFFFF" />
    <path
        android:fillColor="#00000000"
        android:pathData="M0,49L108,49"
        android:strokeWidth="0.8"
        android:strokeColor="#33FFFFFF" />
    <path
        android:fillColor="#00000000"
        android:pathData="M0,59L108,59"
        android:strokeWidth="0.8"
        android:strokeColor="#33FFFFFF" />
    <path
        android:fillColor="#00000000"
        android:pathData="M0,69L108,69"
        android:strokeWidth="0.8"
        android:strokeColor="#33FFFFFF" />
    <path
        android:fillColor="#00000000"
        android:pathData="M0,79L108,79"
        android:strokeWidth="0.8"
        android:strokeColor="#33FFFFFF" />
    <path
        android:fillColor="#00000000"
        android:pathData="M0,89L108,89"
        android:strokeWidth="0.8"
        android:strokeColor="#33FFFFFF" />
    <path
        android:fillColor="#00000000"
        android:pathData="M0,99L108,99"
        android:strokeWidth="0.8"
        android:strokeColor="#33FFFFFF" />
    <path
        android:fillColor="#00000000"
        android:pathData="M19,29L89,29"
        android:strokeWidth="0.8"
        android:strokeColor="#33FFFFFF" />
    <path
        android:fillColor="#00000000"
        android:pathData="M19,39L89,39"
        android:strokeWidth="0.8"
        android:strokeColor="#33FFFFFF" />
    <path
        android:fillColor="#00000000"
        android:pathData="M19,49L89,49"
        android:strokeWidth="0.8"
        android:strokeColor="#33FFFFFF" />
    <path
        android:fillColor="#00000000"
        android:pathData="M19,59L89,59"
        android:strokeWidth="0.8"
        android:strokeColor="#33FFFFFF" />
    <path
        android:fillColor="#00000000"
        android:pathData="M19,69L89,69"
        android:strokeWidth="0.8"
        android:strokeColor="#33FFFFFF" />
    <path
        android:fillColor="#00000000"
        android:pathData="M19,79L89,79"
        android:strokeWidth="0.8"
        android:strokeColor="#33FFFFFF" />
    <path
        android:fillColor="#00000000"
        android:pathData="M29,19L29,89"
        android:strokeWidth="0.8"
        android:strokeColor="#33FFFFFF" />
    <path
        android:fillColor="#00000000"
        android:pathData="M39,19L39,89"
        android:strokeWidth="0.8"
        android:strokeColor="#33FFFFFF" />
    <path
        android:fillColor="#00000000"
        android:pathData="M49,19L49,89"
        android:strokeWidth="0.8"
        android:strokeColor="#33FFFFFF" />
    <path
        android:fillColor="#00000000"
        android:pathData="M59,19L59,89"
        android:strokeWidth="0.8"
        android:strokeColor="#33FFFFFF" />
    <path
        android:fillColor="#00000000"
        android:pathData="M69,19L69,89"
        android:strokeWidth="0.8"
        android:strokeColor="#33FFFFFF" />
    <path
        android:fillColor="#00000000"
        android:pathData="M79,19L79,89"
        android:strokeWidth="0.8"
        android:strokeColor="#33FFFFFF" />
</vector>
```

## app\src\main\res\drawable\ic_launcher_foreground.xml
`$lang
<vector xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:aapt="http://schemas.android.com/aapt"
    android:width="108dp"
    android:height="108dp"
    android:viewportWidth="108"
    android:viewportHeight="108">
    <path android:pathData="M31,63.928c0,0 6.4,-11 12.1,-13.1c7.2,-2.6 26,-1.4 26,-1.4l38.1,38.1L107,108.928l-32,-1L31,63.928z">
        <aapt:attr name="android:fillColor">
            <gradient
                android:endX="85.84757"
                android:endY="92.4963"
                android:startX="42.9492"
                android:startY="49.59793"
                android:type="linear">
                <item
                    android:color="#44000000"
                    android:offset="0.0" />
                <item
                    android:color="#00000000"
                    android:offset="1.0" />
            </gradient>
        </aapt:attr>
    </path>
    <path
        android:fillColor="#FFFFFF"
        android:fillType="nonZero"
        android:pathData="M65.3,45.828l3.8,-6.6c0.2,-0.4 0.1,-0.9 -0.3,-1.1c-0.4,-0.2 -0.9,-0.1 -1.1,0.3l-3.9,6.7c-6.3,-2.8 -13.4,-2.8 -19.7,0l-3.9,-6.7c-0.2,-0.4 -0.7,-0.5 -1.1,-0.3C38.8,38.328 38.7,38.828 38.9,39.228l3.8,6.6C36.2,49.428 31.7,56.028 31,63.928h46C76.3,56.028 71.8,49.428 65.3,45.828zM43.4,57.328c-0.8,0 -1.5,-0.5 -1.8,-1.2c-0.3,-0.7 -0.1,-1.5 0.4,-2.1c0.5,-0.5 1.4,-0.7 2.1,-0.4c0.7,0.3 1.2,1 1.2,1.8C45.3,56.528 44.5,57.328 43.4,57.328L43.4,57.328zM64.6,57.328c-0.8,0 -1.5,-0.5 -1.8,-1.2s-0.1,-1.5 0.4,-2.1c0.5,-0.5 1.4,-0.7 2.1,-0.4c0.7,0.3 1.2,1 1.2,1.8C66.5,56.528 65.6,57.328 64.6,57.328L64.6,57.328z"
        android:strokeWidth="1"
        android:strokeColor="#00000000" />
</vector>
```

## app\src\main\res\mipmap-anydpi-v26\ic_launcher.xml
`$lang
<?xml version="1.0" encoding="utf-8"?>
<adaptive-icon xmlns:android="http://schemas.android.com/apk/res/android">
    <background android:drawable="@drawable/ic_launcher_background" />
    <foreground android:drawable="@drawable/ic_launcher_foreground" />
    <monochrome android:drawable="@drawable/ic_launcher_foreground" />
</adaptive-icon>
```

## app\src\main\res\mipmap-anydpi-v26\ic_launcher_round.xml
`$lang
<?xml version="1.0" encoding="utf-8"?>
<adaptive-icon xmlns:android="http://schemas.android.com/apk/res/android">
    <background android:drawable="@drawable/ic_launcher_background" />
    <foreground android:drawable="@drawable/ic_launcher_foreground" />
    <monochrome android:drawable="@drawable/ic_launcher_foreground" />
</adaptive-icon>
```

## app\src\main\res\values\colors.xml
`$lang
<?xml version="1.0" encoding="utf-8"?>
<resources>
    <color name="purple_200">#FFBB86FC</color>
    <color name="purple_500">#FF6200EE</color>
    <color name="purple_700">#FF3700B3</color>
    <color name="teal_200">#FF03DAC5</color>
    <color name="teal_700">#FF018786</color>
    <color name="black">#FF000000</color>
    <color name="white">#FFFFFFFF</color>
</resources>
```

## app\src\main\res\values\strings.xml
`$lang
<resources>
    <string name="app_name">UrbanPlanner</string>
</resources>
```

## app\src\main\res\values\themes.xml
`$lang
<?xml version="1.0" encoding="utf-8"?>
<resources>

    <style name="Theme.UrbanPlanner" parent="android:Theme.Material.Light.NoActionBar" />
</resources>
```

## app\src\main\res\xml\backup_rules.xml
`$lang
<?xml version="1.0" encoding="utf-8"?><!--
   Sample backup rules file; uncomment and customize as necessary.
   See https://developer.android.com/guide/topics/data/autobackup
   for details.
   Note: This file is ignored for devices older than API 31
   See https://developer.android.com/about/versions/12/backup-restore
-->
<full-backup-content>
    <!--
   <include domain="sharedpref" path="."/>
   <exclude domain="sharedpref" path="device.xml"/>
-->
</full-backup-content>
```

## app\src\main\res\xml\data_extraction_rules.xml
`$lang
<?xml version="1.0" encoding="utf-8"?><!--
   Sample data extraction rules file; uncomment and customize as necessary.
   See https://developer.android.com/about/versions/12/backup-restore#xml-changes
   for details.
-->
<data-extraction-rules>
    <cloud-backup>
        <!-- TODO: Use <include> and <exclude> to control what is backed up.
        <include .../>
        <exclude .../>
        -->
    </cloud-backup>
    <!--
    <device-transfer>
        <include .../>
        <exclude .../>
    </device-transfer>
    -->
</data-extraction-rules>
```

## app\src\test\java\com\SemiColon\urbanplanner\ExampleUnitTest.kt
`$lang
package com.SemiColon.urbanplanner

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }
}
```

## app\src\androidTest\java\com\SemiColon\urbanplanner\ExampleInstrumentedTest.kt
`$lang
package com.SemiColon.urbanplanner

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.SemiColon.urbanplanner", appContext.packageName)
    }
}
```

