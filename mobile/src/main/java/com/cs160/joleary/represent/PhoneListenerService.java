package com.cs160.joleary.represent;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.wearable.MessageEvent;
import com.google.android.gms.wearable.WearableListenerService;

import java.nio.charset.StandardCharsets;

/**
 * Created by joleary and noon on 2/19/16 at very late in the night. (early in the morning?)
 */
public class PhoneListenerService extends WearableListenerService {

//   WearableListenerServices don't need an iBinder or an onStartCommand: they just need an onMessageReceieved.
private static final String DETAIL = "/detail";
    private static final String SHAKED = "/Shake";


    @Override
    public void onMessageReceived(MessageEvent messageEvent) {
        Log.d("T*&^%$#@#$%^&*", "in PhoneListenerService, got: " + messageEvent.getPath());
        if( messageEvent.getPath().equalsIgnoreCase(DETAIL) ) {

            // Value contains the String we sent over in WatchToPhoneService, "good job"
            String value = new String(messageEvent.getData(), StandardCharsets.UTF_8);

            Intent sendIntent = new Intent(getBaseContext(), DetailedActivity.class);
            sendIntent.putExtra("SHOW_PROFILE", value);
            sendIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(sendIntent);

            // Make a toast with the String
//            Context context = getApplicationContext();
//            int duration = Toast.LENGTH_SHORT;
//
//            Toast toast = Toast.makeText(context, value, duration);
//            toast.show();

            // so you may notice this crashes the phone because it's
            //''sending message to a Handler on a dead thread''... that's okay. but don't do this.
            // replace sending a toast with, like, starting a new activity or something.
            // who said skeleton code is untouchable? #breakCSconceptions
        } else if (messageEvent.getPath().equalsIgnoreCase(SHAKED) ) {
            Intent sendIntent = new Intent(getBaseContext(), CongressionalActivity.class);
            sendIntent.putExtra("ZIP_CODE", -9999);
            sendIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(sendIntent);
        } else {
            super.onMessageReceived( messageEvent );
        }

    }
}
