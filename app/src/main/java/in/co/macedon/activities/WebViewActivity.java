package in.co.macedon.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import in.co.macedon.R;

public class WebViewActivity extends AppCompatActivity {

    WebView webview;
    Dialog dialog;
    String str_url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        // Find the WebViewActivity by its unique ID
        android.webkit.WebView webView = findViewById(R.id.webview);

        str_url = getIntent().getStringExtra("weburl");

        // loading https://www.geeksforgeeks.org url in the WebViewActivity.
        webView.loadUrl(str_url);

        // this will enable the javascript.
        webView.getSettings().setJavaScriptEnabled(true);

        // WebViewClient allows you to handle
        // onPageFinished and override Url loading.
        webView.setWebViewClient(new WebViewClient());
    }
    @Override
    public void onBackPressed() {

        if (webview.canGoBack()){
            webview.goBack();
        }else{
            super.onBackPressed();
        }

    }
}