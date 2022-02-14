package com.elchaninov.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkRequest
import android.os.Build
import androidx.lifecycle.LiveData

class OnlineLiveData(context: Context) : LiveData<Boolean>() {

    // Массив из доступных сетей
    private val availableNetworks = mutableSetOf<Network>()

    private val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    // Создаём запрос
    private val request: NetworkRequest = NetworkRequest.Builder().build()

    // Создаём колбэк, который уведомляет нас о появлении или исчезновении связи с сетью
    private val callback = object : ConnectivityManager.NetworkCallback() {

        // Если соединение отсутствует при запуске приложения
        override fun onUnavailable() {
            update(availableNetworks.isNotEmpty())
        }

        // Если соединение потеряно, убираем его из массива и уведомляем подписчиков о наличии связи
        override fun onLost(network: Network) {
            availableNetworks.remove(network)
            update(availableNetworks.isNotEmpty())
        }

        // Если соединение восстановлено, добавляем его в массив и уведомляем подписчиков о наличии сети
        override fun onAvailable(network: Network) {
            availableNetworks.add(network)
            update(availableNetworks.isNotEmpty())
        }
    }

    init {
        // Проверка соединения при запуске приложения
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            connectivityManager.requestNetwork(request, callback, 1000)
        }
    }

    // Регистрируем колбэк, если компонент, подписанный на LiveData, активен
    override fun onActive() {
        connectivityManager.registerNetworkCallback(request, callback)
    }

    // Убираем колбэк, если компонент, подписанный на LiveData, неактивен
    override fun onInactive() {
        connectivityManager.unregisterNetworkCallback(callback)
    }

    // Уведомляем подписчиков о наличии/отсутствии связи с сетью
    private fun update(online: Boolean) {
        if (online != value) {
            postValue(online)
        }
    }
}