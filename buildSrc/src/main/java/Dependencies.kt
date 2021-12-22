import org.gradle.api.JavaVersion

object Versions {
    //koin
    const val koin = "3.1.2"
    //room
    const val room = "2.4.0"
    //gson
    const val gson ="2.8.8"
    //retrofit
    const val retrofit = "2.9.0"
    const val retrofit_rx = "1.0.0"
    //okHttp
    const val interceptor = "4.9.0"
    //coil
    const val coil = "0.11.0"
    //coroutines
    const val coroutines = "1.6.0-RC2"
    //viewModel
    const val viewModel = "2.4.0"
    //fragment-ktx
    const val fragment_ktx = "1.4.0"
    //design
    const val design = "1.4.0"
    const val constraint = "2.1.2"
    //Test
    const val jUnit = "4.13.2"
    const val runner = "1.1.3"
    const val espressoCore = "3.4.0"

}

object Modules {
    const val model = ":model"
    const val repository = ":repository"
    const val utils = ":utils"
    const val app = ":app"

    //feature
    const val historyScreen =":historyScreen"

}
object Config {
    const val application_id = "com.example.idictionary"
    const val compile_sdk = 31
    const val min_sdk = 21
    const val target_sdk = 31
    val java_version = JavaVersion.VERSION_1_8
}

object Room {
    const val runtime = "androidx.room:room-runtime:${Versions.room}"
    const val compiler = "androidx.room:room-compiler:${Versions.room}"
    const val room_ktx = "androidx.room:room-ktx:${Versions.room}"
}

object Gson {
    const val gson = "com.google.code.gson:gson:${Versions.gson}"
}

object Retrofit {
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val converter_gson = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
    const val rxjava_adapter = "com.jakewharton.retrofit:retrofit2-rxjava2-adapter:${Versions.retrofit_rx}"
}

object OkHttp {
    const val interceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.interceptor}"
}

object Koin {
    const val core = "io.insert-koin:koin-core:${Versions.koin}"
    const val koin_android = "io.insert-koin:koin-android:${Versions.koin}"
    const val compat = "io.insert-koin:koin-android-compat:${Versions.koin}"
}

object Coil {
    const val coil = "io.coil-kt:coil:${Versions.coil}"
}

object Coroutines {
    const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
}

object ViewModel {
    const val livedata_ktx = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.viewModel}"
    const val viewmodel_ktx = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.viewModel}"
}
object FragmentKtx {
    const val ktx = "androidx.fragment:fragment-ktx:${Versions.fragment_ktx}"
}
object Design {
    const val appcompat = "androidx.appcompat:appcompat:${Versions.design}"
    const val material = "com.google.android.material:material:${Versions.design}"
    const val constraint_layout = "androidx.constraintlayout:constraintlayout:${Versions.constraint}"
}
object TestImpl {
    const val junit = "junit:junit:${Versions.jUnit}"
    const val ext_junit = "androidx.test.ext:junit:${Versions.runner}"
    const val espresso = "androidx.test.espresso:espresso-core:${Versions.espressoCore}"
}