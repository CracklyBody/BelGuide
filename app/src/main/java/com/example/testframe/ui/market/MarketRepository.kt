package com.example.testframe.ui.market

import com.example.testframe.ui.market_list

class MarketRepository {
    fun getMarketItems(onDataReadyCallback: OnDataReadyCallback){
        // Тут можно организовать процесс загрузки данных из бд или запросом с сервера

        onDataReadyCallback.onDataReady(market_list)
    }
}

interface OnDataReadyCallback{
    fun onDataReady(data: List<ItemMarket>)
}