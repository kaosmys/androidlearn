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
 * Time: ����11:38
 * To change this template use File | Settings | File Templates.
 */
public class HttpMainActivity extends Activity {

    //ͨ��get�����õ������������Զ�ȡ�ӷ������ϵ�����
    public static InputStream openHttpGetConnection(String url) throws IOException {
         HttpClient httpClient = new DefaultHttpClient();
         HttpResponse httpResponse = httpClient.execute(new HttpGet(url));
         InputStream inputStream = httpResponse.getEntity().getContent();
         return inputStream;
    }

    //����һ�����ص�ҵ�����߼��ķ�������������UI�߳�
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
    //����һ���ֵ䶨���ҵ���߼�����
    private String WordDefinition(String word) throws ParserConfigurationException {
        InputStream is = null;
        String strDefinition = "";
        try {
            is = openHttpGetConnection("http://services.aonaware.com/DictService/" +
                    "DictService.asmx/Define?word=" + word);
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        //����һϵ��doc��ص��࣬�õ�doc�ļ�
        Document doc = null;
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db;
        db = dbf.newDocumentBuilder();
        doc = db.newDocument();
        doc.getDocumentElement().normalize();

        //����doc�е����нڵ�
        NodeList definitionElements = doc.getElementsByTagName("Definition");

        //�������е�Definition�ڵ�
        for (int i = 0; i < definitionElements.getLength(); i++) {
            Node itemNode = definitionElements.item(i);
            if (itemNode.getNodeType() == Node.ELEMENT_NODE) {
                //ת�����е�Definition�ڵ�ΪElement
                Element definitionElement = (Element) itemNode;

                //ȡ������WordDefinition�ڵ�
                NodeList wordDefinitionElements = definitionElement
                        .getElementsByTagName("WordDefinition");
                strDefinition = "";
                //�������е�WordDefinition�ڵ��µ�Ԫ��
                for (int j = 0; j < wordDefinitionElements.getLength(); j++) {
                    //ȡ�����е��ӽڵ���WordDefinition��
                     NodeList txtNodes = wordDefinitionElements.item(j).getChildNodes();
                     strDefinition += txtNodes.item(0).getNodeValue() + ".\n";

                }
            }
        }
        return strDefinition;
    }

    //����һ����װ��ҵ���߼����첽�������񣬽����������أ����������߳�
    public class DownloadTask extends AsyncTask<String,String,String> {

        //��worker thread�н����������񣨼�ҵ���߼��ķ�������android���Ѿ���װ
        // ��ʵ�ֿ�ܣ�ֻҪ��װ�Լ���ҵ���߼����ɡ����Ҵ���ķ�����Ҫ�����url��
        @Override
        protected String doInBackground(String... params) {
            return DownloadText(params[0]);  //To change body of implemented methods use File | Settings | File Templates.
        }

        //��doInBackground�����UI�̵߳�ִ��,���taskû�б�ȡ������˷������ᱻִ��
        @Override
        protected void onPostExecute(String s) {
            Toast.makeText(getBaseContext(),s,Toast.LENGTH_SHORT).show();
            Log.d("DownloadTestTask",s);
        }
    }

    //ͬ��Ҳ�Ƿ�װһ���Ѿ�����õ�����ͼƬ���������񣬽����������أ����������߳�.��
    // Ҫע��AsyncTask�з��͵���ȷ����
    public class DownloadPicTask extends AsyncTask<String,Integer,Bitmap> {

        @Override
        protected Bitmap doInBackground(String... params) {
            return DownloadImage(params[0]);
        }

        //�������Ľ�������صķ�������
        @Override
        protected void onPostExecute(Bitmap bitmap) {
            ImageView imageView = (ImageView) findViewById(R.id.img1);
            imageView.setImageBitmap(bitmap);
        }
    }

    //��װ�ý���xml�ļ��������࣬ͬʱҲҪע��AsyncTask�еķ��Ͷ���
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