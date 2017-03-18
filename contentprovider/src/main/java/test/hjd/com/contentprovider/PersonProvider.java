package test.hjd.com.contentprovider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.Nullable;

/**
 * Created by huangjundong on 2017/2/28.
 */

public class PersonProvider extends ContentProvider {
    PersonDao personDao;


    UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
//        matcher.addURI();
    }


    /*
    uri:指定要删除那一个库里面的哪一张表
    values:要插入的数据
     */
    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        //第二个参数为null表示不具体通知某一个observer
        getContext().getContentResolver().notifyChange(uri,null);
        personDao.insertData((String) values.get("name"), (String) values.get("salary"));

        return null;
    }

    /*
    uri:指定要删除那一个库里面的哪一张表
    selection:where语句
    selectionArgs:where语句中占位符的数据
     */
    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        personDao.deleteData(Integer.parseInt(selectionArgs[0]));
        return 0;
    }

    /*
    uri:指定要更新那一个库里面的哪一张表
    values:需要插入的数据
    selection:where语句
    selectionArgs:where语句中占位符的数据
     */
    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        //这里需要注意,不要强转
        int id = Integer.parseInt((String) values.get("id"));
        String name = (String) values.get("name");
        String salary = (String) values.get("salary");

        System.out.println("------>update id: " + id + " ,name: " + name + " ,salary: " + salary);

        personDao.updateData(id, name, salary);
        return 0;
    }

    /*
    uri:指定是哪一个数据库的哪一张表
    projection[]:需要查询那几列
    selection:where 语句
    selectionArgs:where 语句中的占位符的数据
    sortOrder:排序规则
     */
    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        personDao.queryData();
        return null;
    }

    @Override
    public boolean onCreate() {
        MyOpenHelper helper = new MyOpenHelper(getContext());
        personDao = new PersonDao(helper);

        return true;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }
}
