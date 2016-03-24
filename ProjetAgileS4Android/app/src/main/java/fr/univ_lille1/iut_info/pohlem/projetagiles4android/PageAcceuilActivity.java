package fr.univ_lille1.iut_info.pohlem.projetagiles4android;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.android.volley.*;
import com.android.volley.toolbox.*;

public class PageAcceuilActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_acceuil);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_page_acceuil, menu);
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
    public void onConnect(View view){
        System.out.println("ok");
        TextView tvNom = (TextView) findViewById(R.id.nomCompte);
        TextView tvMdp = (TextView) findViewById(R.id.mdp);
        if(tvNom.getText()=="admin"&&tvMdp.getText()=="admin") {
            Intent intentMain = new Intent(PageAcceuilActivity.this ,
                    menu_admin.class);
            PageAcceuilActivity.this.startActivity(intentMain);
        }
        if(tvNom.getText().toString().equals("pohlem")&&tvMdp.getText().toString().equals("maxime")) {

            Intent intentMain = new Intent(PageAcceuilActivity.this ,
                    menu.class);
            PageAcceuilActivity.this.startActivity(intentMain);
        }
    }
    public void onInscrire(View view){
        Intent intentMain = new Intent(PageAcceuilActivity.this ,
                inscription.class);
        PageAcceuilActivity.this.startActivity(intentMain);
    }
}
