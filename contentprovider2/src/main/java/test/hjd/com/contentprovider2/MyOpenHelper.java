package test.hjd.com.contentprovider2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by huangjundong on 2017/2/28.
 */

public class MyOpenHelper extends SQLiteOpenHelper {
    public MyOpenHelper(Context context) {
        super(context, "persons.db", null, 1);
    }


    /*
    创建数据库的时候调用
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table persons( _id integer primary key autoincrement , name varchar(20) ,salary varchar(10) )");
    }

    /*
    通常用于执行update语句
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
