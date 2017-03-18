package test.hjd.com.storage2;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by huangjundong on 2017/3/3.
 */

public class PersonDao {

    MyOpenHelper openHelper;
    SQLiteDatabase db;

    public PersonDao(MyOpenHelper openHelper) {
        this.openHelper = openHelper;
    }

    public void insert(Person person) {

        db = openHelper.getWritableDatabase();
        //第一种CRUD的写法
//        db.execSQL("insert into persons(name,salary) values(?,?)", new String[]{person.name, person.salary + ""});

        //第二种CRUD的写法
        ContentValues values = new ContentValues();
        values.put("name", person.name);
        values.put("salary", person.salary);
        db.insert("persons",null,values);

        db.close();
    }

    public void delete(int id) {
        db = openHelper.getWritableDatabase();
//        db.execSQL("delete from persons where _id = ?", new String[]{id + ""});
        db.delete("persons", "_id = ?", new String[]{id + ""});
        db.close();
    }

    public void update(Person person) {
        db = openHelper.getWritableDatabase();
//        db.execSQL("update persons set name = ? , salary = ? where _id = ?", new String[]{person.name, person.salary + "", person.id + ""});

        ContentValues values = new ContentValues();
        values.put("name", person.name);
        values.put("salary", person.salary);
        db.update("persons", values, "_id = ?", new String[]{person.id + ""});
        db.close();
    }

    public void query() {
        db = openHelper.getWritableDatabase();
//        Cursor cursor = db.rawQuery("select * from persons", null);

        Cursor cursor = db.query("persons", null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            int salary = cursor.getInt(2);

            System.out.println("------>" + new Person(id,name,salary));
        }
        db.close();
    }
}
