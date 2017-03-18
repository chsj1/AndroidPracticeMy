package test.hjd.com.http;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by huangjundong on 2017/3/9.
 */
public class DownLoadThread2 extends Thread {

    int threadId;
    int startIndex;
    int endIndex;

    public DownLoadThread2(int threadId, int startIndex, int endIndex) {
        this.threadId = threadId;
        this.startIndex = startIndex;
        this.endIndex = endIndex;
    }
    /*
    36864
    6048472
    12060080
     */

    @Override
    public void run() {
        String urlStr = "http://localhost:8080/JavaWeb1/android.zip";

        int lastPosition = 0;

        try {
            URL url = new URL(urlStr);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            //读取位置信息
            File positionFile = new File(threadId + ".txt");
            if (positionFile.exists()) {
                BufferedReader reader = new BufferedReader(new FileReader(positionFile));
                String position = reader.readLine();
                lastPosition = Integer.parseInt(position);

                reader.close();
            } else {
                lastPosition = startIndex;
            }

            conn.setRequestProperty("Range", "bytes:" + lastPosition + "-" + endIndex);
            System.out.println("------->threadId: " + threadId + ", startIndex: " + lastPosition + " , endIndex: " + endIndex);

            conn.connect();

            if (conn.getResponseCode() == 206) {

                RandomAccessFile file = new RandomAccessFile("android.zip", "rw");
                file.seek(lastPosition);

                InputStream inputStream = conn.getInputStream();
                byte[] buffer = new byte[1024];
                int length = -1;

                int currentPosition = lastPosition;
                while ((length = inputStream.read(buffer)) != -1) {

//                    Thread.sleep(50);

                    file.write(buffer, 0, length);

                    //保存位置信息
                    currentPosition += length;
                    RandomAccessFile positionFile1 = new RandomAccessFile(threadId + ".txt", "rwd");
                    positionFile1.writeBytes((currentPosition + ""));
                    positionFile1.close();

                    System.out.println("------->threadId: " + threadId + ", currentPosition: " + currentPosition);
                }


                //删除位置信息
                new File(threadId + ".txt").delete();

                inputStream.close();
                file.close();
            }

            conn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
