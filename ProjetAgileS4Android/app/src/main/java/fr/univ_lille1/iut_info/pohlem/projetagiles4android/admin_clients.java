package fr.univ_lille1.iut_info.pohlem.projetagiles4android;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

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

public class admin_clients extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_clients);
        load();
    }

    private void load() {

        HttpStack stack = new HurlStack() {

            @Override
            protected HttpURLConnection createConnection(URL url) throws IOException {
                Proxy proxy = new Proxy(Proxy.Type.HTTP, InetSocketAddress.createUnresolved("cache.univ-lille1.fr", 3128));
                return (HttpURLConnection) url.openConnection();
            }
        };
        RequestQueue queue = Volley.newRequestQueue(this, stack);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, "http://192.168.43.73:8080/v1/userdb",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String json) {
                        if (json!=null) {
                            ((TextView) findViewById(R.id.affichageClient)).setText(json);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(admin_clients.this, "ERROR: " + error, Toast.LENGTH_SHORT).show();
            }
        });
        queue.add(stringRequest);
    }
}
