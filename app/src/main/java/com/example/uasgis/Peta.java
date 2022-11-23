package com.example.uasgis;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;

public class Peta extends AppCompatActivity {
    WebView webView;
    int lebar;
    int tinggi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_peta);
        webView=findViewById(R.id.webview);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus){
        super.onWindowFocusChanged(hasFocus);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        lebar=(webView.getWidth()/3);
        tinggi=(webView.getHeight()/3);
        viewWeb();
    }

    private void viewWeb() {
        webView.setWebViewClient(new WebViewClient());
        webView.setWebChromeClient(new WebChromeClient());
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("http://192.168.43.210:8080/geoserver/UAS_GIS/wms?service=WMS&version=1.1.0&request=GetMap&layers=UAS_GIS%3AGroup%20Kab%20Gianyar&bbox=115.225288391113%2C-8.65023994445801%2C115.371032714844%2C-8.31407642364502&width=443&height=500&srs=EPSG%3A404000&format=application/openlayers");
    }
}