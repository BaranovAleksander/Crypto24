package com.example.crypto24

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.crypto24.adapters.CoinInfoAdapter
import com.example.crypto24.databinding.ActivityCoinPriceListBinding
import com.example.crypto24.pojo.CoinPriceInfo

class CoinPriceListActivity : AppCompatActivity() {

    private lateinit var viewModel: CoinViewModel
    private lateinit var binding: ActivityCoinPriceListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCoinPriceListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = CoinInfoAdapter(this)
        adapter.onCoinClickListener =
            object : CoinInfoAdapter.OnCoinClickListener {
                override fun onCoinClick(coinPriseInfo: CoinPriceInfo) {
                    val intent = CoinDetailActivity.newIntent(
                        this@CoinPriceListActivity,
                        coinPriseInfo.fromSymbol
                    )
                    startActivity(intent)
                }
            }

        binding.rvCoinPriceList.adapter = adapter

        viewModel = ViewModelProvider(this)[CoinViewModel::class.java]
        viewModel.priceList.observe(this, Observer {
            adapter.coinInfoList = it
        })
    }
}


