package amal.bouaouina.mybestlocation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.loader.content.AsyncTaskLoader;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationBarView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import amal.bouaouina.mybestlocation.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(LayoutInflater.from(MainActivity.this));

        setContentView(binding.getRoot());

        binding.bottomNavView.setOnItemSelectedListener(
                new NavigationBarView.OnItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                        if (menuItem.getItemId() == R.id.nav_home) {
                            getSupportFragmentManager()
                                    .beginTransaction()
                                    .replace(R.id.host, new HomeFragment())
                                    .commit();

                            return true;
                        }

                        if (menuItem.getItemId() == R.id.nav_add) {
                            getSupportFragmentManager()
                                    .beginTransaction()
                                    .replace(R.id.host, new AddFragment())
                                    .commit();

                            return true;
                        }

                        return false;
                    }
                }
        );
    }}
   /* binding.btnFetchData.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Telechargement t=new Telechargement();
                t.execute();
            }
        });

        binding.btnSendSMS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String numero = binding.EdPhone.getText().toString();

                //envoie sms
                SmsManager manager = SmsManager.getDefault();//sim par defaut
                manager.sendTextMessage(
                        numero, null, Util.REQUEST_SMS, null, null
                );
            }
        });

        //demande permission
        ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.SEND_SMS, Manifest.permission.READ_SMS,
                Manifest.permission.RECEIVE_SMS,
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.POST_NOTIFICATIONS}, 1);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 1) {
            if (grantResults.length == 0) {
                finish();
            } else {
                if (grantResults[0] == PackageManager.PERMISSION_DENIED
                        || grantResults[3]==PackageManager.PERMISSION_DENIED) {
                    finish();
                }
            }
        }
    }
    class Telechargement extends AsyncTask {
        ArrayList <Position> data=new ArrayList<>();
        AlertDialog alert;
        @Override
        protected void onPreExecute() {
            AlertDialog.Builder dialog=new AlertDialog.Builder(MainActivity.this);
            dialog.setTitle("Downloading ...");
            dialog.setMessage("wait plz ..;");
            alert=dialog.create();
            alert.show();
            }

        @Override
        protected Object doInBackground(Object[] objects) {

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            JSONParser parser=new JSONParser();
            JSONObject result=parser.makeRequest(Util.URL_GET_ALL_POSITIONS);

            try {
                int success = result.getInt("success");
                if(success==0){
                    String msg=result.getString("message");
                }
                else {
                    JSONArray tableau=result.getJSONArray("positions");
                    for (int i = 0; i < tableau.length(); i++) {
                                  JSONObject ligne=tableau.getJSONObject(i);
                                  int idposition=ligne.getInt("idposition");
                        String numero=ligne.getString("numero");
                        String pseudo=ligne.getString("pseudo");
                        String longitude=ligne.getString("longitude");
                        String latitude=ligne.getString("latitude");
                        Position p=new Position(idposition,numero,pseudo,longitude,latitude);
                        data.add(p);

                    }
                }
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            alert.dismiss();
            binding.lvPosition.setAdapter(new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1,data));
        }
    }
}
*/