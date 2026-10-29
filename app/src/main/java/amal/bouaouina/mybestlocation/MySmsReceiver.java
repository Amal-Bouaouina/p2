package amal.bouaouina.mybestlocation;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class MySmsReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        String messageBody, phoneNumber;
        if (intent.getAction().equals("android.provider.Telephony.SMS_RECEIVED")) {
            Bundle bundle = intent.getExtras();
            if (bundle != null) {
                Object[] pdus = (Object[]) bundle.get("pdus");
                final SmsMessage[] messages = new SmsMessage[pdus.length];
                for (int i = 0; i < pdus.length; i++) {
                    messages[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
                }
                if (messages.length > -1) {
                    messageBody = messages[0].getMessageBody();
                    phoneNumber = messages[0].getDisplayOriginatingAddress();
                    Toast.makeText(context, "Message : " + messageBody + "Reçu de la part de;" + phoneNumber, Toast.LENGTH_LONG).show();
                    if(messageBody.contains(Util.REQUEST_SMS)){
                        //recuperer gps position
                        FusedLocationProviderClient mClient =
                                LocationServices.getFusedLocationProviderClient(context);
                        mClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
                            @Override
                            public void onComplete(@NonNull Task<Location> task) {
                                if (task.isSuccessful()){
                                    Location mposition=task.getResult();
                                    if(mposition!=null){
                                        double longitude= mposition.getLongitude();
                                        double latitude=mposition.getLatitude();
                                        SmsManager manager=SmsManager.getDefault();
                                        manager.sendTextMessage(
                                                phoneNumber,
                                                null,
                                                Util.RESPONSE_SMS + "#" + longitude + "#" + latitude,
                                                null,
                                                null
                                        );                                        return;
                                    }
                                }
                                Toast.makeText(context, "pas de position detecte", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                    if(messageBody.startsWith(Util.RESPONSE_SMS)){
                        String [] t=messageBody.split("#");
                        String longitude=t[1];
                        String latitude=t[2];

                        //Notif
                        NotificationCompat.Builder mynotif=new NotificationCompat.Builder(context,Util.CHANNEL_ID);
                        mynotif.setContentTitle("Position GPS recevied.");
                        mynotif.setContentText("Tap to see on Map");
                        mynotif.setSmallIcon(android.R.drawable.ic_dialog_map);
                        mynotif.setAutoCancel(true);

                        //Action
                        Intent i=new Intent(context, MapsActivity.class);
                        i.putExtra("longitude",longitude);
                        i.putExtra("latitude",latitude);
                        PendingIntent pi=PendingIntent.getActivity(context,0,i,PendingIntent.FLAG_MUTABLE);
                        mynotif.setContentIntent(pi);

                        //lauch
                        NotificationManagerCompat notificationManager=NotificationManagerCompat.from(context);

                        NotificationChannel canal=new NotificationChannel(Util.CHANNEL_ID,
                                "this is a channel for best locations app",
                                NotificationManager.IMPORTANCE_DEFAULT);
                                notificationManager.createNotificationChannel(canal);
                        notificationManager.notify(0,mynotif.build());
                    }
                }
            }
        }

    }
}