package test.hjd.com.contentprovider;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by huangjundong on 2017/2/28.
 */

public class PersonDao {

    MyOpenHelper openHelper;
    SQLiteDatabase db;

    public PersonDao(MyOpenHelper openHelper) {
        this.openHelper = openHelper;
    }

    /*
       本app直接操作数据库
        */
    public void insertData(String name, String salary) {
        db = openHelper.getWritableDatabase();
        db.execSQL("insert into persons(name,salary) values(?,?)", new String[]{name, salary});
        db.close();
    }


    public void deleteData(int id) {
        db = openHelper.getWritableDatabase();
        db.execSQL("delete from persons where _id = ?", new String[]{id + ""});
        db.close();
    }


    public void updateData(int id, String name, String salary) {
        System.out.println("------>传过来的id是: " + id + ",name: " + name + ",salary: " + salary);

        db = openHelper.getWritableDatabase();
        db.execSQL("update persons set name = ? , salary = ? where _id = ?", new String[]{name, salary, id + ""});
        db.close();
    }


    public void queryData() {
        db = openHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from persons", null);
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String salary = cursor.getString(2);

            System.out.println("------>id: " + id + " ,name: " + name + ",salary: " + salary);

        }
        db.close();
    }


}
