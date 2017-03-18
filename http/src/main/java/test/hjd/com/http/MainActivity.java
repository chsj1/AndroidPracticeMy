package test.hjd.com.http;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    String urlStr = "http://192.168.1.107:8080/JavaWeb1/hello.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        doGet(urlStr);
//        doPost(urlStr);

//        httpGet(urlStr);

//        httpPost(urlStr);

//        okHttpGet(urlStr);

//        okHttpPost(urlStr);

        multiDownload();
    }

    public void multiDownload() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String urlStr = "http://192.168.1.107:8080/JavaWeb1/android.zip";

                int threadCount = 3;

                try {
                    URL url = new URL(urlStr);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.connect();

                    if (conn.getResponseCode() == 200) {
                        int contentLength = conn.getContentLength();

                        RandomAccessFile file = new RandomAccessFile(getApplicationContext().getFilesDir().getPath() + "/android.zip", "rw");
                        file.setLength(contentLength);

                        int blockSize = contentLength / threadCount;
                        for (int i = 0; i < threadCount; ++i) {
                            int startIndex = i * blockSize;
                            int endIndex = (i + 1) * blockSize - 1;
                            if (endIndex == threadCount - 1) {
                                endIndex = contentLength - 1;
                            }
//                    new DownLoadThread(i, startIndex, endIndex).start();
                            new DownLoadThreadAndroid(i, startIndex, endIndex,getApplicationContext()).start();
                        }
                    }
                    conn.disconnect();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }).start();
    }

    public void okHttpPost(final String urlStr) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient okHttpClient = new OkHttpClient();
                Request request = new Request.Builder().url(urlStr).post(new FormBody.Builder().build()).build();
//                Request request = new Request.Builder().url(urlStr).post(new FormBody.Builder().add("sex","male").build()).build();
                try {
                    //以同步的方法进行请求
                    Response response = okHttpClient.newCall(request).execute();

                    //以异步的方式进行请求
//                    okHttpClient.newCall(request).enqueue(new Callback() {
//                        @Override
//                        public void onFailure(Call call, IOException e) {
//
//                        }
//
//                        @Override
//                        public void onResponse(Call call, Response response) throws IOException {
//
//                        }
//                    });

                    if (response.isSuccessful()) {
                        InputStream inputStream = response.body().byteStream();

                        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

                        String temp;
                        while ((temp = reader.readLine()) != null) {
                            System.out.println("----->okhttppost temp: "+ temp);
                        }


                        inputStream.close();
                        reader.close();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }


    public void okHttpGet(final String urlStr) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient okHttpClient = new OkHttpClient();
                Request request = new Request.Builder().url(urlStr).build();
                try {
                    Response response = okHttpClient.newCall(request).execute();
                    if (response.isSuccessful()) {
                        InputStream inputStream = response.body().byteStream();

                        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

                        String temp;
                        while ((temp = reader.readLine()) != null) {
                            System.out.println("----->okhttpget temp: "+ temp);
                        }


                        inputStream.close();
                        reader.close();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void httpPost(final String urlStr) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpPost httpPost = new HttpPost(urlStr);
                HttpResponse httpResponse = null;
                try {
                    //这个是需要传参数的情况
//                    List<NameValuePair> params = new ArrayList<NameValuePair>();
//                    params.add(new BasicNameValuePair("sex", "male"));
//                    httpPost.setEntity(new UrlEncodedFormEntity(params));

                    httpResponse = new DefaultHttpClient().execute(httpPost);

                    if (httpResponse.getStatusLine().getStatusCode() == 200) {
                        InputStream inputStream = httpResponse.getEntity().getContent();

                        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

                        String temp;
                        while ((temp = reader.readLine()) != null) {
                            System.out.println("----->httppost temp: "+ temp);
                        }


                        inputStream.close();
                        reader.close();
                    } else {

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

    public void httpGet(final String urlStr) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpGet httpGet = new HttpGet(urlStr);
                HttpResponse httpResponse = null;
                try {
                    httpResponse = new DefaultHttpClient().execute(httpGet);

                    if (httpResponse.getStatusLine().getStatusCode() == 200) {
                        InputStream inputStream = httpResponse.getEntity().getContent();

                        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

                        String temp;
                        while ((temp = reader.readLine()) != null) {
                            System.out.println("----->httpget temp: "+ temp);
                        }


                        inputStream.close();
                        reader.close();
                    } else {

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

    public void doGet(final String urlStr) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                InputStream inputStream = null;
                BufferedReader reader = null;
                try {
                    URL url = new URL(urlStr);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.connect();


                    if (conn.getResponseCode() == 200) {
                        inputStream = conn.getInputStream();
                        reader = new BufferedReader(new InputStreamReader(inputStream));

                        String temp;
                        while ((temp = reader.readLine()) != null) {
                            System.out.println("----->temp: "+ temp);
                        }


                        inputStream.close();
                        reader.close();
                    }else{
                        System.out.println("------>请求失败");
                    }



                    conn.disconnect();
                } catch (Exception e) {
                    e.printStackTrace();
                }finally {
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }


                    if (reader != null) {
                        try {
                            reader.close();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }).start();
    }


    public void doPost(final String urlStr) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                InputStream inputStream = null;
                BufferedReader reader = null;
                try {
                    URL url = new URL(urlStr);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setDoInput(true);
                    conn.setDoOutput(true);
                    conn.setRequestMethod("POST");
//                    conn.setRequestProperty();
                    conn.setUseCaches(false);

                    conn.connect();


                    if (conn.getResponseCode() == 200) {
                        inputStream = conn.getInputStream();
                        reader = new BufferedReader(new InputStreamReader(inputStream));

                        String temp;
                        while ((temp = reader.readLine()) != null) {
                            System.out.println("----->doPost temp: "+ temp);
                        }


                        inputStream.close();
                        reader.close();
                    }else{
                        System.out.println("------>doPost 请求失败");
                    }



                    conn.disconnect();
                } catch (Exception e) {
                    e.printStackTrace();
                }finally {
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }


                    if (reader != null) {
                        try {
                            reader.close();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }).start();
    }

}
