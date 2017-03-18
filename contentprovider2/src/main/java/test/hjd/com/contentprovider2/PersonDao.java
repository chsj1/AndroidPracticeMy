package test.hjd.com.contentprovider2;

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

    public void insert(Person person) {
        db = openHelper.getWritableDatabase();
        db.execSQL("insert into persons(name,salary) values(?,?)", new String[]{person.name, person.salary});
        db.close();
    }


    public void delete(int id) {
        db = openHelper.getWritableDatabase();
        db.execSQL("delete from persons where _id = ?", new Integer[]{id});
        db.close();
    }

    public void update(Person person) {
        db = openHelper.getWritableDatabase();
        db.execSQL("update persons set name = ? , salary = ? where _id = ?", new Object[]{person.name, person.salary, person.id});
        db.close();
    }

    public void query() {
        db = openHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from persons", null);
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String salary = cursor.getString(2);

            System.out.println("-------->id: " + id + " , name: " + name + " , salary: " + salary);
        }
        db.close();
    }
}
