package test.hjd.com.storage2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by huangjundong on 2017/3/3.
 */

public class MyOpenHelper extends SQLiteOpenHelper {

    public MyOpenHelper(Context context) {
        /*
        上下文对象.
        数据库的名字.
        facetory工厂.
        数据库的版本.只升不降.
         */
        super(context, "persons.db", null, 2);
    }


    /*
    第一次创建数据库的时候调用。常用语进行建表操作
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        System.out.println("------->onCreate db");
        db.execSQL("create table persons(_id integer primary key autoincrement , name varchar(50) , salary varchar(10) )");
    }

    /*
    升版本的时候调动.常用于对标的结构进行修改
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        System.out.println("-------->onUpgrade db");
    }
}
