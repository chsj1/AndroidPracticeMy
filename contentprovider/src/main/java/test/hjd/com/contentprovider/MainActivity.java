package test.hjd.com.contentprovider;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    MyOpenHelper openHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //创建数据源(创建数据库,建表)
        openHelper = new MyOpenHelper(this);

        //直接直接操作数据库
        PersonDao personDao = new PersonDao(openHelper);
        personDao.insertData("jundong", "28000");

//        insertData("hjd", "26000");
//        insertData("jdh", "27000");

//        deleteData(2);

//        updateData(3, "jdhh", "30000");
//        queryData();
    }


}
