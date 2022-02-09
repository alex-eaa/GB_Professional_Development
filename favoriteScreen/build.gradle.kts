plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-kapt")
}

dependencies {

    implementation(project(Modules.core))
    implementation(project(Modules.model))
    implementation(project(Modules.repository))
    implementation(project(Modules.descriptionScreen))
    implementation(project(Modules.utils))

    implementation(Design.appcompat)
    implementation(Design.material)

    implementation(Kotlin.core)
    implementation(Kotlin.stdlib)
    implementation(Kotlin.livedata_ktx)
    implementation(Kotlin.viewmodel_ktx)

    implementation(Koin.core)
    implementation(Koin.android)
    implementation(Koin.android_compat)

    testImplementation(TestImpl.junit)
    androidTestImplementation(TestImpl.ext_junit)
    androidTestImplementation(TestImpl.espresso)
}