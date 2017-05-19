package com.example.anish.callrecieverdemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;
import android.widget.Toast;

/**
 * Created by anish on 19-05-2017.
 */

public class PhoneStateReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        try {
            System.out.println("Receiver start");
            Toast.makeText(context, " Receiver start ", Toast.LENGTH_SHORT).show();

            String state = intent.getStringExtra(TelephonyManager.EXTRA_STATE);
            String incomingNumber = intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER);

            if (state.equals(TelephonyManager.EXTRA_STATE_RINGING)) {
                Toast.makeText(context, "Ringing State Number is -" + incomingNumber, Toast.LENGTH_SHORT).show();
                System.out.println("No:@@" + " " + incomingNumber);

            }
            if ((state.equals(TelephonyManager.EXTRA_STATE_OFFHOOK))) {
                Toast.makeText(context, "Received State", Toast.LENGTH_SHORT).show();
            }
            if (state.equals(TelephonyManager.EXTRA_STATE_IDLE)) {
                Toast.makeText(context, "Idle State", Toast.LENGTH_SHORT).show();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
