package amal.bouaouina.mybestlocation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.loader.content.AsyncTaskLoader;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import org.json.JSONObject;

import amal.bouaouina.mybestlocation.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(LayoutInflater.from(MainActivity.this));

        setContentView(binding.getRoot());

        binding.btnFetchData.setOnClickListener(new View.OnClickListener(){
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

        @Override
        protected void onPreExecute() {
            Toast.makeText(MainActivity.this, "Start ...", Toast.LENGTH_SHORT).show();
        }

        @Override
        protected Object doInBackground(Object[] objects) {
            JSONParser parser=new JSONParser();
            JSONObject result=parser.makeRequest(Util.URL_GET_ALL_POSITIONS);
            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            Toast.makeText(MainActivity.this, "Done!", Toast.LENGTH_SHORT).show();
        }
    }
}