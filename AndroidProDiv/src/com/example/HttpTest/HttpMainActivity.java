package com.example.HttpTest;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;
import com.example.AndroidProDiv.R;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 14-6-9
 * Time: 上午11:38
 * To change this template use File | Settings | File Templates.
 */
public class HttpMainActivity extends Activity {

    //通过get方法得到输入流，可以读取从服务器上的数据
    public static InputStream openHttpGetConnection(String url) throws IOException {
         HttpClient httpClient = new DefaultHttpClient();
         HttpResponse httpResponse = httpClient.execute(new HttpGet(url));
         InputStream inputStream = httpResponse.getEntity().getContent();
         return inputStream;
    }

    //进行一个下载的业务类逻辑的方法，以免阻塞UI线程
    public String DownloadText(String URL) {
        int BUFFER_SIZE = 2000;
        InputStream is = null;
        InputStreamReader isr = null;
        String txt= "";

        try {
            is  = openHttpGetConnection(URL);
            int hasread = 0;
            isr = new InputStreamReader(is);
            char[] buff = new char[BUFFER_SIZE];
            while ((hasread = isr.read(buff)) != -1) {
                String res = String.valueOf(buff,0,hasread);
                txt += res;
                buff = new char[BUFFER_SIZE];
            }
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
                if (isr == null) {
                    isr.close();
                }
            } catch (IOException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }
        return txt;
    }

    private Bitmap DownloadImage(String url) {
        Bitmap bitmap = null;
        InputStream in = null;
        try {
            in = openHttpGetConnection(url);
            bitmap = BitmapFactory.decodeStream(in);
            in.close();
        } catch (IOException e) {
            Log.d("DownloadPics",e.getLocalizedMessage());
        }
        return bitmap;
    }
    //进行一个字典定义的业务逻辑方法
    private String WordDefinition(String word) throws ParserConfigurationException {
        InputStream is = null;
        String strDefinition = "";
        try {
            is = openHttpGetConnection("http://services.aonaware.com/DictService/" +
                    "DictService.asmx/Define?word=" + word);
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        //进行一系列doc相关的类，得到doc文件
        Document doc = null;
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db;
        db = dbf.newDocumentBuilder();
        doc = db.newDocument();
        doc.getDocumentElement().normalize();

        //回溯doc中的所有节点
        NodeList definitionElements = doc.getElementsByTagName("Definition");

        //迭代所有的Definition节点
        for (int i = 0; i < definitionElements.getLength(); i++) {
            Node itemNode = definitionElements.item(i);
            if (itemNode.getNodeType() == Node.ELEMENT_NODE) {
                //转换所有的Definition节点为Element
                Element definitionElement = (Element) itemNode;

                //取得所有WordDefinition节点
                NodeList wordDefinitionElements = definitionElement
                        .getElementsByTagName("WordDefinition");
                strDefinition = "";
                //迭代所有的WordDefinition节点下的元素
                for (int j = 0; j < wordDefinitionElements.getLength(); j++) {
                    //取得所有的子节点在WordDefinition下
                     NodeList txtNodes = wordDefinitionElements.item(j).getChildNodes();
                     strDefinition += txtNodes.item(0).getNodeValue() + ".\n";

                }
            }
        }
        return strDefinition;
    }

    //定义一个封装好业务逻辑的异步加载任务，进行网络下载，避免阻塞线程
    public class DownloadTask extends AsyncTask<String,String,String> {

        //在worker thread中进行下载任务（即业务逻辑的方法），android中已经封装
        // 好实现框架，只要封装自己的业务逻辑即可。并且此类的方法需要处理的url由
        @Override
        protected String doInBackground(String... params) {
            return DownloadText(params[0]);  //To change body of implemented methods use File | Settings | File Templates.
        }

        //在doInBackground后进行UI线程的执行,如果task没有被取消，则此方法不会被执行
        @Override
        protected void onPostExecute(String s) {
            Toast.makeText(getBaseContext(),s,Toast.LENGTH_SHORT).show();
            Log.d("DownloadTestTask",s);
        }
    }

    //同样也是封装一个已经定义好的下载图片的下载任务，进行网络下载，避免阻塞线程.主
    // 要注意AsyncTask中泛型的正确定义
    public class DownloadPicTask extends AsyncTask<String,Integer,Bitmap> {

        @Override
        protected Bitmap doInBackground(String... params) {
            return DownloadImage(params[0]);
        }

        //处理完后的结果，返回的服务器端
        @Override
        protected void onPostExecute(Bitmap bitmap) {
            ImageView imageView = (ImageView) findViewById(R.id.img1);
            imageView.setImageBitmap(bitmap);
        }
    }

    //封装好解析xml文件的任务类，同时也要注意AsyncTask中的泛型定义
    public class AccessWebServiceTask extends AsyncTask<String,Void,String> {

        @Override
        protected String doInBackground(String... params) {
            try {
                return WordDefinition(params[0]);  //To change body of implemented methods use File | Settings | File Templates.
            } catch (ParserConfigurationException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
            return null;
        }
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_httptest);

       /* new DownloadTask().
                execute("http://www.webservicex.net/CurrencyConvertor.asmx/ConversionRate?FromCurrency=EUR&ToCurrency=USD");*/
        /*new DownloadPicTask().
                execute("http://www.mayoff.com/5-01cablecarDCP01934.jpg");*/

        new AccessWebServiceTask().execute("cool");
    }
}