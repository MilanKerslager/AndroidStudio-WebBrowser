package cz.kerslager.android.webbrowser;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.Toast;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    WebView webView;
    EditText editTextURL;
    final static String GOOGLE = "https://www.google.cz";

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
        webView.loadUrl(GOOGLE);
        editTextURL.setText(GOOGLE);
    }

    // tlačítko zpět bude sloužit k návratu na předchozí stránku
    @Override
    public void onBackPressed() {
        if (webView.canGoBack()) {
            Toast.makeText(this, "Jdu na předchozí stránku", Toast.LENGTH_SHORT).show();
            webView.goBack();
        } else {
            Toast.makeText(this, "Už jste na první stránce", Toast.LENGTH_SHORT).show();
        }
    }

    public void onKlik(View v) {
        // načtení adresy včetně ořezání mezer na začátku a na konci
        String vstup = String.valueOf(editTextURL.getText()).trim();
        editTextURL.setText(vstup);
        // doplnění chybějícího http://
        if (!vstup.startsWith("http")) {
            vstup = "http://" + vstup;
            editTextURL.setText(vstup);
        }
        // zkusíme načíst odkaz (tohle asi není moc vhodná kontrola -> regexp?)
        final URL url;
        try {
            url = new URL(vstup);
            url.toURI();
        } catch (MalformedURLException e) {
            Toast.makeText(this, "Chyba v odkazu " + vstup, Toast.LENGTH_LONG).show();
        } catch (URISyntaxException e) {
            Toast.makeText(this, "Chyba v odkazu " + vstup, Toast.LENGTH_LONG).show();
        }
        Toast.makeText(this, "Otevírám " + vstup, Toast.LENGTH_LONG).show();
        // zkusíme nahrát novou webovou stránku
        webView.loadUrl(vstup);
        // zhasneme softwarovou klávesnici
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
        // odstraníme kurzor ze vstupního políčka
        editTextURL.clearFocus();
    }
}
