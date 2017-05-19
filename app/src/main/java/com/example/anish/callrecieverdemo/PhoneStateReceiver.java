package com.example.anish.callrecieverdemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.RemoteException;
import android.telephony.TelephonyManager;
import android.widget.Toast;

import com.android.internal.telephony.ITelephony;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

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

            if (incomingNumber.equals("+919601158411")) {
                disconnectCall(context);
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

    private void disconnectCall(Context context) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        Class clazz = Class.forName(telephonyManager.getClass().getName());
        Method method = clazz.getDeclaredMethod("getITelephony");
        method.setAccessible(true);
        ITelephony telephonyService = (ITelephony) method.invoke(telephonyManager);
        try {
            telephonyService.endCall();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        Toast.makeText(context, "Hell yaa Go to Hell", Toast.LENGTH_SHORT).show();
    }


}
