package info.linxiangyu.ghoauth;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class MainActivity extends Activity {


    Button mGetAcessTokenButton;
    TextView mDispalyText;
    WebView mWebview;
    String mAuthUri;

    public static final String CODE = "ff5846e002f861f48f11";

    public static final String ACESS_TOKEN = "60016ac5c755303936ad491b13adea1fab36183e";

    public static final String LIST_GIST_URL = "https://api.github.com/gists?access_token=" + ACESS_TOKEN;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);


        mAuthUri = "https://github.com/login/oauth/authorize?client_id=055d97c5194a0bb447d9&scope=gist";

        mGetAcessTokenButton = (Button)findViewById(R.id.button);
        mDispalyText = (TextView)findViewById(R.id.dispalyText);
        mWebview = (WebView) findViewById(R.id.webView);



        mGetAcessTokenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                   //getCode();
                   //getAcessToken();
                getListGist();
            }
        });



        WebSettings settings = mWebview.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setSupportZoom(true);
        settings.setBuiltInZoomControls(true);
        mWebview.requestFocusFromTouch();
        WebViewClient wvc = new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                Log.d("TAG", "Loading url: " + url);
                super.onPageStarted(view, url, favicon);
                String reUrl = view.getUrl();
                if (url.indexOf("code") != -1){
                    mDispalyText.setText(reUrl);
                }
            }
        };
        mWebview.setWebViewClient(wvc);


    }



    public void getCode(){
        mWebview.loadUrl(mAuthUri);
    }



    public void getAcessToken(){
        Log.d("TAG", "on get acess token");

        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        params.put("client_id", "055d97c5194a0bb447d9");
        params.put("client_secret", "1af3f30b8a632b140419eb00e4bfe086348d572f");
        params.put("code", CODE);
        client.post("https://github.com/login/oauth/access_token", params,  new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(String response) {
                Log.d("TAG", response);
            }


            @Override
            public void onFailure(Throwable throwable, String s) {
                Log.d("TAG", "on failture" + throwable.toString());
            }

            @Override
            public void onFinish() {
                Log.d("TAG", "on finish");
            }
        });

    }


    public void getListGist(){

        AsyncHttpClient client = new AsyncHttpClient();
        client.get(LIST_GIST_URL,  new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(String response) {
                Log.d("TAG", response);
            }
        });

    }





}
