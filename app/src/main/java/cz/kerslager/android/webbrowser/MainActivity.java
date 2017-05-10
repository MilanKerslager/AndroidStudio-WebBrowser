package cz.kerslager.android.webbrowser;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {

    WebView webView;
    final static String GOOGLE = "https://www.google.cz";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // odstraníme app bar ve vrchní části okna
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        // získáme přístup k webview widgetu v aktivitě
        webView = (WebView) findViewById(R.id.webview);
        // připojíme renderovací engine do widgetu webview
        webView.setWebViewClient(new WebViewClient());
        // povolíme JavaScript
        webView.getSettings().setJavaScriptEnabled(true);
        // skryjeme posouvací scrollbary (ale scrolování bude stále fungovat)
        webView.setVerticalScrollBarEnabled(false);
        webView.setHorizontalScrollBarEnabled(false);
        // povolíme zvětšování a zmenšování pomocí prstů (pinch zoom)
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setDisplayZoomControls(false);
        // nahrajeme první stránku
        webView.loadUrl(GOOGLE);
    }
}
