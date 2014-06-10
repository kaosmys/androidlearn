package com.example.MakePhoneCalls;

import android.app.Activity;
import android.os.Bundle;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import com.example.AndroidProDiv.R;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 14-6-7
 * Time: ÏÂÎç2:42
 * To change this template use File | Settings | File Templates.
 */
public class StateMorningtorActivity extends Activity {
    TelephonyManager manager;
    PhoneReceiver phoneReceiver;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.statemorningtormain);

        phoneReceiver = new PhoneReceiver(this);
        manager = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
    }

    @Override
    protected void onResume() {
        super.onResume();    //To change body of overridden methods use File | Settings | File Templates.
        manager.listen(phoneReceiver, PhoneStateListener.LISTEN_CALL_STATE);
    }

    @Override
    protected void onPause() {
        super.onPause();    //To change body of overridden methods use File | Settings | File Templates.
        manager.listen(phoneReceiver,PhoneStateListener.LISTEN_NONE);
    }
}