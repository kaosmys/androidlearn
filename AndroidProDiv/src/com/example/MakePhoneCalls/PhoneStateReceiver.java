package com.example.MakePhoneCalls;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 14-6-7
 * Time: 下午3:03
 * To change this template use File | Settings | File Templates.
 */
public class PhoneStateReceiver extends BroadcastReceiver{
    TelephonyManager manager;
    PhoneReceiver myPhoneReceiver;
    static boolean alreadyListening = false;

    @Override
    public void onReceive(Context context, Intent intent) {
        myPhoneReceiver = new PhoneReceiver(context);
        manager = (TelephonyManager) context
                .getSystemService(Context.TELEPHONY_SERVICE);

        //----不要多次增加listner------
        if (!alreadyListening) {
            manager.listen(myPhoneReceiver,
                    PhoneStateListener.LISTEN_CALL_STATE);
            alreadyListening = true;
        }

    }
}
