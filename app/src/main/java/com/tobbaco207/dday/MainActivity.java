package com.tobbaco207.dday;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.webkit.WebSettings;
import android.widget.LinearLayout;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class MainActivity extends Activity {
    private WebView webView;
    private AdView adView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);

        webView = new WebView(this);
        webView.setWebViewClient(new WebViewClient());

        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);

        layout.addView(webView,
            new LinearLayout.LayoutParams(
                -1, 0, 1));

        MobileAds.initialize(this, status -> {});

        adView = new AdView(this);
        adView.setAdSize(AdSize.BANNER);

        // Google official test banner ID
        adView.setAdUnitId(
            "ca-app-pub-3349558183751975/8617928362"
        );

        layout.addView(adView,
            new LinearLayout.LayoutParams(
                -1, -2));

        setContentView(layout);

        webView.loadUrl(
            "file:///android_asset/index.html"
        );

        AdRequest adRequest =
            new AdRequest.Builder().build();
        adView.loadAd(adRequest);
    }

    @Override
    protected void onDestroy() {
        if (adView != null) {
            adView.destroy();
        }
        if (webView != null) {
            webView.destroy();
        }
        super.onDestroy();
    }
}
