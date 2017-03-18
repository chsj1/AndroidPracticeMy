package test.hjd.com.contentprovider2;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.Nullable;

/**
 * Created by huangjundong on 2017/3/1.
 */

public class PeronProvider extends ContentProvider {
    PersonDao personDao;

    /*
    NO_MATCH:不存在匹配的uri的时候返回的code
     */
    UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);

    {
        //添加uri的匹配规则
        matcher.addURI("test.hjd.com.contentprovider2", "persons", 1);
        matcher.addURI("test.hjd.com.contentprovider2", "users", 2);
        //#匹配任意数字
        matcher.addURI("test.hjd.com.contentprovider2", "persons/#", 3);
        //*匹配任意字符
        matcher.addURI("test.hjd.com.contentprovider2", "persons/*", 4);
    }

    /*
    uri:指定了你要操作的对象.
    values:需要插入的数据.实际上是对HashMap的一次封装
     */
    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        if (matcher.match(uri) == 1) {
            System.out.println("------->你即将操作persons表");
        /*
        第二个参数为null表示不通知具体的contentObserver.
         */
            getContext().getContentResolver().notifyChange(uri, null);

            personDao.insert(new Person((String) values.get("name"), (String) values.get("salary")));
        } else if (matcher.match(uri) == 2) {
            System.out.println("------->你即将操作users表");
        } else if (matcher.match(uri) == UriMatcher.NO_MATCH) {
            System.out.println("------->不匹配任何一张表");
        } else if (matcher.match(uri) == 3) {
            System.out.println("------->匹配了persons后面接数字的情况");
        } else if (matcher.match(uri) == 4) {
            System.out.println("------->匹配了persons后面接任意字符的情况");
        }

        return null;
    }

    /*
    uri:指定了你要操作的对象.(具体是哪一张表)
    selection:操作的条件(id = ?)
    selectionArgs:条件的具体参数(就是上面?的具体的内容)
     */
    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        personDao.delete(Integer.parseInt(selectionArgs[0]));
        return 0;
    }


    /*
    uri:指定了你要操作的对象.
    values:需要插入的数据.实际上是对HashMap的一次封装
    selection:操作的条件(id = ?)
    selectionArgs:条件的具体参数(就是上面?的具体的内容)
     */
    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        personDao.update(new Person(Integer.parseInt(selectionArgs[0]), (String) values.get("name"), (String) values.get("salary")));
        return 0;
    }


    /*
    uri:指定了你要操作的对象.
    projection:选择那一列
    selection:操作的条件(id = ?)
    selectionArgs:条件的具体参数(就是上面?的具体的内容)
    sortOrder:指定根据那一列来排序
     */
    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        personDao.query();
        return null;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Override
    public boolean onCreate() {
        personDao = new PersonDao(new MyOpenHelper(getContext()));
        return true;
    }
}
