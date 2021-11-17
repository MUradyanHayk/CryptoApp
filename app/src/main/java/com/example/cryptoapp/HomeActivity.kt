package com.example.cryptoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.example.cryptoapp.adapter.CoinFragmentStateAdapter
import com.example.cryptoapp.repository.CoinRepository
import com.example.cryptoapp.utils.Download
import com.example.cryptoapp.viewModel.CoinViewModel
import com.google.android.material.tabs.TabLayout

class HomeActivity : AppCompatActivity() {
    private var tabLayout: TabLayout? = null
    private var viewPager: ViewPager2? = null
    private var coinFragmentStateAdapter: CoinFragmentStateAdapter? = null
    var coinViewModel: CoinViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()

        coinViewModel = ViewModelProvider(this)[CoinViewModel::class.java]
        coinViewModel?.initializeViewModel(CoinRepository())
    }



    private fun initView() {
        tabLayout = findViewById(R.id.tab_layout)
        tabLayout?.addTab(tabLayout!!.newTab().setText(getString(R.string.all_title_text)))
        tabLayout?.addTab(tabLayout!!.newTab().setText(getString(R.string.favorite_title_text)))

        viewPager = findViewById(R.id.view_pager2)
        coinFragmentStateAdapter = CoinFragmentStateAdapter(supportFragmentManager, lifecycle)
        viewPager?.adapter = coinFragmentStateAdapter


        tabLayout?.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (tab == null) {
                    return
                }
                viewPager?.currentItem = tab.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

        })

        viewPager?.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                tabLayout?.selectTab(tabLayout?.getTabAt(position))
            }
        })

    }
}