package com.example.HttpTest;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;
import com.example.AndroidProDiv.R;
import org.apache.http.conn.util.InetAddressUtils;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 14-6-9
 * Time: 下午5:22
 * To change this template use File | Settings | File Templates.
 */
public class IpAddGet extends Activity {

    //获取本地地址的业务逻辑方法
    public String getLocalIpv4Add() {
        try {
            for (Enumeration<NetworkInterface> nis =
                         NetworkInterface.getNetworkInterfaces();
                    nis.hasMoreElements();) {
                NetworkInterface ni = nis.nextElement();
                for (Enumeration<InetAddress> ipEnum = ni.getInetAddresses();
                        ipEnum.hasMoreElements();){
                    InetAddress ip = ipEnum.nextElement();
                //check ip是够为ipv4
                    if (!ip.isLoopbackAddress()
                            && InetAddressUtils.isIPv4Address(ip.getHostAddress())) {
                       return ip.getHostAddress();
                    }
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return null;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ipadd_main);

        Toast.makeText(this,"this device's ip is" + getLocalIpv4Add(),Toast.LENGTH_SHORT).show();
    }


}