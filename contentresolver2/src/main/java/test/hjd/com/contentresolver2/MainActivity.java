package test.hjd.com.contentresolver2;

import android.content.ContentUris;
import android.content.ContentValues;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

/*
ContentProvider:共享数据.屏蔽了底层的细节
ContentResolver:操作共享数据.
ContentObserver:监听共享数据.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Uri uri = Uri.parse("content://test.hjd.com.contentprovider2");
//        Uri uri = Uri.parse("content://test.hjd.com.contentprovider2/persons");
//        ContentValues values = new ContentValues();
//        values.put("name", "jundong");
//        values.put("salary", "27000");
//        getContentResolver().insert(uri,values);
//        getContentResolver().query(uri, null, null, null, null);
//        getContentResolver().delete(uri, null, new String[]{"5"});


//        getContentResolver().update(uri, values, null, new String[]{"3"});

        /*
        notifyForDecendunts:false,精确匹配.true:监听该路径及子路径
         */
//        getContentResolver().registerContentObserver(uri,true,new MyContentObserver(new Handler()));
    }

    class MyContentObserver extends ContentObserver {
        public MyContentObserver(Handler handler) {
            super(handler);
        }

        @Override
        public void onChange(boolean selfChange) {
            super.onChange(selfChange);

            System.out.println("------->我监听到了共享数据的变化");
        }
    }

    public void insert(View view) {
        Uri uri = Uri.parse("content://test.hjd.com.contentprovider2/persons/3");
        ContentValues values = new ContentValues();
        values.put("name", "jundong");
        values.put("salary", "27000");
        getContentResolver().insert(uri,values);


        /*
        就是在uri后面拼接了一个id.
        例如下面的效果就是:
        content://test.hjd.com.contentprovider2/asdasdasda/20
         */
//        Uri uri1 = ContentUris.withAppendedId(uri, 20);
//        System.out.println(uri1.toString());

        /*
        获取一个uri后面的id
         */
//        long id = ContentUris.parseId(uri1);
//        System.out.println("------->解析到的id是: " + id);
    }
}
