package com.example.UItest;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.example.AndroidProDiv.R;

/**����webview����ȡ�����������ص�����
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 14-6-1
 * Time: ����2:42
 * To change this template use File | Settings | File Templates.
 */
public class WebviewActivity extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webview);

        WebView webView = (WebView) findViewById(R.id.webview01);
        webView.setWebViewClient(new Callback());
        WebSettings webSettings = webView.getSettings();
        webSettings.setBuiltInZoomControls(true);

        webView.loadUrl(String.valueOf(Uri.parse("http://www.baidu.com")));

    }
    private class Callback extends WebViewClient {
        //������ֹ��Ӧ�õ���ϵͳ����Ӧ��������url��ַ
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            return false;    //To change body of overridden methods use File | Settings | File Templates.
        }
    }
}