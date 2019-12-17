package com.jia.googleadm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.android.gms.ads.*
import com.google.android.gms.ads.reward.RewardItem
import com.google.android.gms.ads.reward.RewardedVideoAd
import com.google.android.gms.ads.reward.RewardedVideoAdListener
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), RewardedVideoAdListener {
    private lateinit var mRewardeVideoAd: RewardedVideoAd
    private lateinit var mIntersititalAd: InterstitialAd

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        MobileAds.initialize(this){}
        //BANNER
        var adRequest = AdRequest.Builder().build()
        adView.loadAd(adRequest)
        adView.adListener = object :AdListener(){
            override fun onAdLoaded() {
                Log.e("adListener","onAdLoaded")
            }

            override fun onAdClicked() {
                Log.e("adListener","onAdClicked")
            }

            override fun onAdOpened() {
                Log.e("adListener","onAdOpened")
            }
            override fun onAdLeftApplication() {
                Log.e("adListener","onAdLeftApplication")
            }
            override fun onAdImpression() {
                Log.e("adListener","onAdImpression")
            }
            override fun onAdFailedToLoad(p0: Int) {
                Log.e("adListener", "onAdFailedToLoad:$p0")
            }
            override fun onAdClosed() {
                Log.e("adListener","onAdClosed")
            }
        }
        //插页式
        mIntersititalAd = InterstitialAd(this)
        mIntersititalAd.adUnitId = "ca-app-pub-3940256099942544/1033173712"
        adChaView.setOnClickListener {
            mIntersititalAd.loadAd(AdRequest.Builder().build())
            if(mIntersititalAd.isLoaded){
                mIntersititalAd.show()
            }else{
                Toast.makeText(this,"插页式初始化失败",Toast.LENGTH_LONG).show()
            }
        }

        //激励
        mRewardeVideoAd = MobileAds.getRewardedVideoAdInstance(this)
        mRewardeVideoAd.rewardedVideoAdListener = this
        mRewardeVideoAd.loadAd("ca-app-pub-3940256099942544/5224354917",AdRequest.Builder().build())
        jiliView.setOnClickListener {
            if(mRewardeVideoAd.isLoaded){
                mRewardeVideoAd.show()
            }
        }
    }

    override fun onPause() {
        super.onPause()
        mRewardeVideoAd.pause(this)
    }

    override fun onResume() {
        super.onResume()
        mRewardeVideoAd.resume(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        mRewardeVideoAd.destroy(this)
    }

    override fun onRewardedVideoAdClosed() {
        Log.e("jiliView","onRewardedVideoAdClosed")
        mRewardeVideoAd.loadAd("ca-app-pub-3940256099942544/5224354917",AdRequest.Builder().build())
    }

    override fun onRewardedVideoAdLeftApplication() {
        Log.e("jiliView","onRewardedVideoAdLeftApplication")
    }

    override fun onRewardedVideoAdLoaded() {
        Log.e("jiliView","onRewardedVideoAdLoaded")
    }

    override fun onRewardedVideoAdOpened() {
        Log.e("jiliView","onRewardedVideoAdOpened")
    }

    override fun onRewardedVideoCompleted() {
        Log.e("jiliView","onRewardedVideoCompleted")
    }

    override fun onRewarded(p0: RewardItem?) {
        Log.e("jiliView","onRewarded  currency "+p0?.type+" amount "+p0?.amount)
    }

    override fun onRewardedVideoStarted() {
        Log.e("jiliView","onRewardedVideoStarted")
    }

    override fun onRewardedVideoAdFailedToLoad(p0: Int) {
        Log.e("jiliView","onRewardedVideoAdFailedToLoad")
    }


}
