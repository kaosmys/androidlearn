package com.example.HttpTest;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import com.example.AndroidProDiv.R;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 14-6-9
 * Time: 下午7:17
 * To change this template use File | Settings | File Templates.
 */
public class SocketClientActivity extends Activity {
    static final String SERVERIP = "192.168.2.39";
    static final int SERVER_PORT = 9999;

    Handler handler = new Handler();
    static Socket socket;
    PrintWriter pw;

    private static TextView textView;
    private static EditText editText;

    public class ClientThread implements Runnable {

        @Override
        public void run() {

            try {
                InetAddress serveradd = InetAddress.getByName(SERVERIP);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        textView.setText(textView.getText()
                                + "connecting the server");
                    }
                });
                socket = new Socket(SERVERIP,SERVER_PORT);

                pw = new PrintWriter(new BufferedWriter(
                        new OutputStreamWriter(socket.getOutputStream())));
                //---得到一个inputStream去从服务器上获取数据
                BufferedReader br = new BufferedReader(
                        new InputStreamReader(socket.getInputStream()));

                //从服务器上读取数据
                String  line = null;
                while ((line = br.readLine()) != null) {
                    final String strReadFromServer = line;

                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            textView.setText(textView.getText()
                                    + strReadFromServer
                                    + "\n");
                        }
                    });

                }

                //从服务器上断开
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        textView.setText(textView.getText()
                                + "\n"
                                + "Clinet disconnected from the server");
                    }
                });
            } catch (UnknownHostException e) {
                final String error = e.getLocalizedMessage();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        textView.setText(textView.getText()
                                + error
                                + "\n");
                    }
                });
            } catch (IOException e) {
                final String error = e.getLocalizedMessage();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        textView.setText(textView.getText()
                                + error
                                + "\n");
                    }
                });
            }
            //关闭连接
            handler.post(new Runnable() {
                @Override
                public void run() {
                    textView.setText(textView.getText()
                            + "\n"
                            + "Connection closed");
                }
            });
        }
    }

    public void onClick(View view) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                pw.println(editText.getText().toString());
            }
        });
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.socketclient_main);

        textView = (TextView) findViewById(R.id.txtsocketclient);
        editText = (EditText) findViewById(R.id.editText1);
    }

    @Override
    protected void onStart() {
        super.onStart();    //To change body of overridden methods use File | Settings | File Templates.
        new Thread(new ClientThread()).start();
    }

    @Override
    protected void onStop() {
        super.onStop();    //To change body of overridden methods use File | Settings | File Templates.
        try {
            socket.shutdownInput();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
}