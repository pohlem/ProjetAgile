package fr.univ_lille1.iut_info.pohlem.projetagiles4android;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpStack;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.util.Map;

public class menu_admin extends AppCompatActivity {

    RequestQueue queue = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_admin);

        test();
    }

    private void test() {

        HttpStack stack = new HurlStack() {

            @Override
            protected HttpURLConnection createConnection(URL url) throws IOException {
                Proxy proxy = new Proxy(Proxy.Type.HTTP, InetSocketAddress.createUnresolved("cache.univ-lille1.fr", 3128));
                return (HttpURLConnection) url.openConnection();
            }
        };

        if (queue==null)
            queue = Volley.newRequestQueue(this, stack);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, "http://172.18.48.180:8080/v1/userdb",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String json) {
                        System.out.print("Reponse reçu");
                        if (json!=null) {
                            ((TextView) findViewById(R.id.testAffichageClient)).setText(json);
                            System.out.print("json : "+json);
                        } else
                                System.out.print("json null");
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.print("error : "+error.toString());
            }
        });
        queue.add(stringRequest);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_menu_admin, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
