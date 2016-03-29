package fr.univ_lille1.iut_info.pohlem.projetagiles4android;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class monCompte extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mon_compte);
        Bundle bundle = getIntent().getExtras();
        if (bundle.getString("login") == null) {
            Intent intentMain = new Intent(monCompte.this, connexion.class);
            monCompte.this.startActivity(intentMain);
        } else {
            ((TextView) (findViewById(R.id.login_text))).setText(bundle.getString("login"));
            load(bundle.getString("login"));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_mon_compte, menu);
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
    public void enregistrer(View view){
        AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(this);
        dlgAlert.setMessage("Enregistrement ValidÃ© !");
        dlgAlert.setPositiveButton("Ok",null);
        dlgAlert.setCancelable(true);
        dlgAlert.create().show();
    }

    private void load(String login) {
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, "http://51.254.167.75/v1/userdb/"+login,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String json) {

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(monCompte.this, "ERROR: " + error, Toast.LENGTH_SHORT).show();
            }
        });
        queue.add(stringRequest);
    }

}
