package test.hjd.com.contentprovider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by huangjundong on 2017/2/28.
 */

class MyOpenHelper extends SQLiteOpenHelper {

    public MyOpenHelper(Context context) {
        super(context, "persons.db", null, 1);
        System.out.println("------->创建了数据库");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        System.out.println("-------->开始执行建表语句");
        db.execSQL("create table persons(_id integer primary key autoincrement , name varchar(50) ,salary varchar(10) )");
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
