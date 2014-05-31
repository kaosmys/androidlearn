package com.example.IntentFilter;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.example.AndroidProDiv.R;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 14-5-31
 * Time: ÏÂÎç9:45
 * To change this template use File | Settings | File Templates.
 */
public class MyBrowserActivity extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.browser);

        Uri uri = getIntent().getData();
        WebView webView = (WebView) findViewById(R.id.webview);
        webView.setWebViewClient(new Callback());

        if (uri == null) {
            webView.loadUrl(uri.toString());
        } else {
            webView.loadUrl("http://www.google.com");
        }
    }

    private class Callback extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
             // while return false means the current WebView handles the url.
            return false;
        }
    }
}