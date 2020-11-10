package com.example.testframe.ui.market

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MarketViewModel: ViewModel() {
    private var marketRepository: MarketRepository = MarketRepository()
    private var _marketItems = MutableLiveData<List<ItemMarket>>()

    val marketItems: LiveData<List<ItemMarket>> = _marketItems

    fun loadMarketItems(){
        marketRepository.getMarketItems(object: OnDataReadyCallback{
            override fun onDataReady(data: List<ItemMarket>) {
                _marketItems.value = data
            }
        })
    }
}