package com.example.HttpTest;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.example.AndroidProDiv.R;
import org.apache.http.conn.util.InetAddressUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.*;
import java.util.Enumeration;
import java.util.logging.Handler;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 14-6-9
 * Time: ����5:48
 * To change this template use File | Settings | File Templates.
 */
public class SocketServerActivity extends Activity {
    private final String SERVERIP = getLocalAdd();
    private final int SERVERPORT = 9999;
    private static TextView textView;

    android.os.Handler handler = new android.os.Handler();
    ServerSocket serverSocket;
    public String getLocalAdd() {
        try {
            for (Enumeration<NetworkInterface> niEnum
                         = NetworkInterface.getNetworkInterfaces();
                    niEnum.hasMoreElements();){
                NetworkInterface ni = niEnum.nextElement();
                for (Enumeration<InetAddress> ips = ni.getInetAddresses();
                        ips.hasMoreElements();){
                    InetAddress ip = ips.nextElement();
                    if (!ip.isLoopbackAddress()
                            && InetAddressUtils.isIPv4Address(ip.getHostAddress())){
                        return ip.getHostAddress();
                    }
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return null;
    }

    public class ServerThread implements Runnable {

        @Override
        public void run() {
            if (SERVERIP != null) {
                textView = (TextView) findViewById(R.id.txtsocket);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        textView.setText(textView.getText()
                                + "servet listening on IP:"
                                + SERVERIP +"\n");
                    }
                });

                //����һ��server socket��ʵ����
                try {
                    serverSocket = new ServerSocket(SERVERPORT);
                } catch (IOException e) {
                    final String error = e.getLocalizedMessage();
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            textView.setText(textView.getText()
                                    + error
                                    +"\n");
                        }
                    });
                }

                //һֱ�����ͻ��˵Ľ���
                while (true){
                    Socket client = null;
                    try {
                        client = serverSocket.accept();
                        /*Toast.makeText(getBaseContext(),
                                "�ͻ����Ѿ�����",
                                Toast.LENGTH_SHORT).show();*/
                        //�������ϵ�����ʱһ�������������ĵ���
                        // ֱ��������socket������,���Ҫ����һ�����߳���������Ϣ��UI�߳�
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                textView.setText(textView.getText()
                                        + "Connected to client."
                                        +"\n");
                            }
                        });
                    } catch (IOException e) {
                        final String error = e.getLocalizedMessage();
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                textView.setText(textView.getText()
                                        + error
                                        +"\n");
                            }
                        });
                    }

                    try {
                        BufferedReader br = new BufferedReader(
                                new InputStreamReader(client.getInputStream()));
                        OutputStream os = client.getOutputStream();
                        //�Ӵ���������ж�ȡ
                        String line = null;
                        while((line = br.readLine()) != null) {
                            final String strRec = line;

                            //���ͻ��κζ�������Ϣ��client��ע�⻹��Ҫ��Runnable
                            String s = line + "\n";
                            os.write(s.getBytes());

                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    textView.setText(textView.getText()
                                            + strRec
                                            +"\n");
                                }
                            });
                        }
                        //client����������˲�������
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                textView.setText(textView.getText()
                                        + "cliet disconnected"
                                        +"\n");
                            }
                        });
                    } catch (IOException e) {
                        final String error = e.getLocalizedMessage();
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                textView.setText(textView.getText()
                                        + error
                                        +"\n");
                            }
                        });
                    }
                }
            }
            else {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        textView.setText(textView.getText()
                                + "no internet connection on device"
                                +"\n");
                    }
                });
            }
        }
    }
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.serversocket_main);


    }

    @Override
    protected void onStart() {
        super.onStart();    //To change body of overridden methods use File | Settings | File Templates.
        new Thread(new ServerThread()).start();
    }

    @Override
    protected void onStop() {
        super.onStop();    //To change body of overridden methods use File | Settings | File Templates.
        try {
            serverSocket.close();
        } catch (IOException e) {
            final String error = e.getLocalizedMessage();
            handler.post(new Runnable() {
                @Override
                public void run() {
                    textView.setText(textView.getText()
                            + error
                            +"\n");
                }
            });
        }
    }
}