package com.example.MakePhoneCalls;

import android.content.Context;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.widget.Toast;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 14-6-7
 * Time: ÏÂÎç2:31
 * To change this template use File | Settings | File Templates.
 */
public class PhoneReceiver extends PhoneStateListener{
    Context context;

    public PhoneReceiver(Context context) {
        this.context = context;
    }

    @Override
    public void onCallStateChanged(int state, String incomingNumber) {
        super.onCallStateChanged(state, incomingNumber);    //To change body of overridden methods use File | Settings | File Templates.
        Toast.makeText(context,"onCallStateChanged state=" + state + "incomingNumber="
                + incomingNumber,Toast.LENGTH_SHORT).show();

        switch (state) {
            case TelephonyManager.CALL_STATE_IDLE :
                Toast.makeText(context,"idle",Toast.LENGTH_SHORT).show();
                break;
            case TelephonyManager.CALL_STATE_RINGING :
                Toast.makeText(context,"ringing",Toast.LENGTH_SHORT).show();
                break;
            case TelephonyManager.CALL_STATE_OFFHOOK:
                Toast.makeText(context,"hook",Toast.LENGTH_SHORT).show();
                break;
            default:
                Toast.makeText(context,"default state",Toast.LENGTH_SHORT).show();
                break;
        }

    }
}
