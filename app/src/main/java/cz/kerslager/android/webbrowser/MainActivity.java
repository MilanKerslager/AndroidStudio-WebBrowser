package cz.kerslager.android.webbrowser;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {

    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // získáme přístup k widgetu webview, který zabírá celou plochu obrazovky
        webView = (WebView) findViewById(R.id.webview);

        // připojíme renderovací engine do widgetu webview
        webView.setWebViewClient(new WebViewClient());
        webView.getSettings().setJavaScriptEnabled(true);
        // skryjeme posouvací scrollbary (ale stále budou fungovat)
        webView.setVerticalScrollBarEnabled(false);
        webView.setHorizontalScrollBarEnabled(false);
        // nahrajeme stránku
        webView.loadUrl("https://www.google.cz/");
    }
}
