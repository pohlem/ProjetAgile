package fr.univ_lille1.iut_info.pohlem.projetagiles4android;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class inscription extends AppCompatActivity {

    boolean userExist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_inscription, menu);
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
    public void onNewUser(View view){
        final TextView tvLogin = (TextView) findViewById(R.id.login);
        final TextView tvMdp = (TextView) findViewById(R.id.mdp);
        final TextView tvName = (TextView) findViewById(R.id.name);
        //load(tvLogin.getText().toString(), tvMdp.getText().toString(), tvName.getText().toString());
        isExisting(new VolleyCallBack() {
            @Override
            public void onSuccess(boolean result) {
                if (!result)
                    load(tvLogin.getText().toString(), tvMdp.getText().toString(), tvName.getText().toString());
                else
                    Toast.makeText(inscription.this, "ERREUR : le login '" + tvLogin.getText().toString() + "' existe déjà", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public interface VolleyCallBack{
        void onSuccess(boolean result);
    }

    private void isExisting(final VolleyCallBack vcb) {
        final TextView tvLogin = (TextView) findViewById(R.id.login);
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, "http://51.254.167.75/v1/userdb/"+tvLogin.getText().toString(),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String json) {
                        vcb.onSuccess(true);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                vcb.onSuccess(false);
            }
        });
        queue.add(stringRequest);
    }

    private void load(final String login, final String passwd, final String name) {

        RequestQueue queue = Volley.newRequestQueue(this);

        JSONObject js = new JSONObject();
        try {
            js.put("name", name);
            js.put("alias", login);
            js.put("password", Base64.encodeToString(passwd.getBytes(), Base64.DEFAULT));
            js.put("adress", "0");
            js.put("id", "0");
        }catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(
                Request.Method.POST,"http://51.254.167.75/v1/userdb/", js,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Toast.makeText(inscription.this, "Compte créé", Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(inscription.this, "ERROR: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        queue.add(jsonObjReq);
    }
}
