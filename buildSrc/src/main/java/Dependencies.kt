import org.gradle.api.JavaVersion

object Config {
    const val application_id = "com.elchaninov.gbprofessionaldevelopment"
    const val compile_sdk = 31
    const val min_sdk = 22
    const val target_sdk = 31
    val java_version = JavaVersion.VERSION_1_8
}

object Releases {
    const val version_code = 1
    const val version_name = "1.0"
}

object Modules {
    const val app = ":app"
    const val core = ":core"
    const val model = ":model"
    const val repository = ":repository"
    const val utils = ":utils"

    //Features
    const val favoriteScreen = ":favoriteScreen"
}

object Versions {
    //Design
    const val appcompat = "1.4.1"
    const val material = "1.5.0"
    const val swiperefreshlayout = "1.1.0"
    const val constraintlayout = "2.1.3"

    //Kotlin
    const val core = "1.6.0"
    const val stdlib = "1.5.21"
    const val livedata_ktx = "2.4.0"
    const val viewmodel_ktx = "2.4.0"

    //Retrofit
    const val retrofit = "2.9.0"
    const val converterGson = "2.9.0"
    const val interceptor = "4.9.1"

    //Koin
    const val koinCore = "3.1.2"
    const val koinAndroid = "3.1.2"
    const val koinAndroidCompat = "3.1.2"

    //Coil
    const val coil = "1.4.0"

    //Room
    const val roomKtx = "2.4.1"
    const val runtime = "2.4.1"
    const val roomCompiler = "2.4.1"

    //Test
    const val jUnit = "4.13.2"
    const val ext_junit = "1.1.3"
    const val espressoCore = "3.4.0"
}

object Design {
    const val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
    const val material = "com.google.android.material:material:${Versions.material}"
    const val constraintlayout =
        "androidx.constraintlayout:constraintlayout:${Versions.constraintlayout}"
    const val swiperefreshlayout =
        "androidx.swiperefreshlayout:swiperefreshlayout:${Versions.swiperefreshlayout}"
}

object Kotlin {
    const val core = "androidx.core:core-ktx:${Versions.core}"
    const val stdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.stdlib}"
    const val livedata_ktx = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.livedata_ktx}"
    const val viewmodel_ktx = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.viewmodel_ktx}"
}

object Retrofit {
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val converter_gson = "com.squareup.retrofit2:converter-gson:${Versions.converterGson}"
    const val logging_interceptor =
        "com.squareup.okhttp3:logging-interceptor:${Versions.interceptor}"
}

object Koin {
    const val core = "io.insert-koin:koin-core:${Versions.koinCore}"
    const val android = "io.insert-koin:koin-android:${Versions.koinAndroid}"
    const val android_compat = "io.insert-koin:koin-android-compat:${Versions.koinAndroidCompat}"
}

object Coil {
    const val coil = "io.coil-kt:coil:${Versions.coil}"
}

object Room {
    const val runtime = "androidx.room:room-runtime:${Versions.runtime}"
    const val compiler = "androidx.room:room-compiler:${Versions.roomCompiler}"
    const val room_ktx = "androidx.room:room-ktx:${Versions.roomKtx}"
}

object TestImpl {
    const val junit = "junit:junit:${Versions.jUnit}"
    const val ext_junit = "androidx.test.ext:junit:${Versions.ext_junit}"
    const val espresso = "androidx.test.espresso:espresso-core:${Versions.espressoCore}"
}
