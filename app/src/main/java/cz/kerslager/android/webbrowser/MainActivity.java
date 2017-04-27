package cz.kerslager.android.webbrowser;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    WebView webView;
    EditText editTextURL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // získáme přístup k widgetům v aktivitě
        editTextURL = (EditText) findViewById(R.id.editTextURL);
        webView = (WebView) findViewById(R.id.webview);

        // odstraníme app bar ve vrchní části okna
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

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
        webView.loadUrl("https://www.google.cz/");
    }

    // tlačítko zpět bude sloužit k návratu na předchozí stránku
    @Override
    public void onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack();
        }
    }

    public void onKlik(View v){
        String URL = String.valueOf(editTextURL.getText());
        Toast.makeText(this, "Otevírám: "+URL, Toast.LENGTH_LONG).show();
        webView.loadUrl(URL);
        editTextURL.clearFocus();
    }
}
