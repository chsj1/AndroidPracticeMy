package test.hjd.com.storage2;

import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Xml;
import android.view.View;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    PersonDao personDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //创建数据库
        MyOpenHelper openHelper = new MyOpenHelper(this);

        personDao = new PersonDao(openHelper);

        /*
        openHelper.getReadableDatabase():尝试以可读可写的形式去打开一个数据,如果如果打开失败，
        则尝试以只读方式去操作一个数据库

        openHelper.getWritableDatabase():尝试以可读可写的形式去打开一个数据,如果如果打开失败.
        直接报错返回
         */
//        openHelper.getReadableDatabase();
//        openHelper.getWritableDatabase();

    }

    public void insert(View view) {
        personDao.insert(new Person(0,"hjd100",31000));
    }

    public void delete(View view) {
        personDao.delete(2);
    }

    public void update(View view) {
        personDao.update(new Person(1,"hjd8",38000));
    }

    public void query(View view) {
        personDao.query();
    }

    public void trans(View view) {
        MyOpenHelper openHelper = new MyOpenHelper(this);
        SQLiteDatabase db = openHelper.getWritableDatabase();

        //sqlite中的数据库的事务
        db.beginTransaction();
        try{
            db.execSQL("update persons set salary = ? where _id = ?", new String[]{"30000", "1"});
            int a = 1/0;
            db.execSQL("update persons set salary = ? where _id = ?", new String[]{"30000", "2"});

            db.setTransactionSuccessful();
        }finally {
            db.endTransaction();
        }

        db.close();
    }
    public void parseXml(View view) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                XmlPullParser xpp = Xml.newPullParser();
                List<Person> persons = null;
                try {
                    xpp.setInput(getApplicationContext().openFileInput("persons.xml"), "utf-8");


                    int type = xpp.getEventType();
                    Person p = null;
                    while (type != XmlPullParser.END_DOCUMENT) {
                        String currentName = xpp.getName();

                        switch (type) {
                            case XmlPullParser.START_TAG:
                                if (currentName.equals("persons")) {
                                    persons = new ArrayList<>();
                                } else if (currentName.equals("person")) {
                                    p = new Person();
                                    p.id = Integer.parseInt(xpp.getAttributeValue(null,"id"));
                                } else if (currentName.equals("name")) {
                                    p.name = xpp.nextText();
                                } else if (currentName.equals("salary")) {
                                    p.salary = Integer.parseInt(xpp.nextText());
                                }
                                break;
                            case XmlPullParser.END_TAG:
                                if (currentName.equals("person")) {
                                    persons.add(p);
                                }
                                break;
                        }

                        type = xpp.next();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

                System.out.println("----->解析出来的xml数据: " + persons);
            }
        }).start();
    }

    public void generateXml(View view) {
        List<Person> persons = new ArrayList<>();
        for(int i = 0 ; i < 10 ; ++i) {
            Person p = new Person(i, "hjd" + i, 25000 + i);
            persons.add(p);
        }


        XmlSerializer xs = Xml.newSerializer();
        try {
            xs.setOutput(getApplicationContext().openFileOutput("persons.xml", MODE_PRIVATE),"utf-8");

            xs.startDocument("utf-8", true);

            xs.startTag(null, "persons");

            for (Person p : persons) {
                xs.startTag(null, "person");
                xs.attribute(null, "id", p.id + "");

                xs.startTag(null, "name");
                xs.text(p.name);
                xs.endTag(null, "name");

                xs.startTag(null, "salary");
                xs.text(p.salary + "");
                xs.endTag(null, "salary");

                xs.endTag(null, "person");
            }

            xs.endTag(null, "persons");
            xs.endDocument();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void readFromFile(View view) {
        BufferedReader reader = null;
        try {
//            reader = new BufferedReader(new FileReader(new File("/data/data/test.hjd.com.storage2/save.txt")));

//            reader = new BufferedReader(new FileReader(new File(getApplicationContext().getFilesDir().getAbsolutePath() + "/save1.txt")));

            String temp;
            while ((temp = reader.readLine()) != null) {
                System.out.println("------->temp: " + temp);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveFile(View view) {
        PrintWriter writer = null;
        try {
            //指定私有目录下要操作的文件的3种方式
            //通过硬编码指定私有目录下要存储到的路径
//            writer = new PrintWriter(new File("/data/data/test.hjd.com.storage2/save.txt"));
            //通过context.getFilesDir()来指定私有目录下要存储到的路径
//            writer = new PrintWriter(new File(getApplicationContext().getFilesDir().getAbsolutePath() + "/save1.txt"));
            //context.openFileOutput()来制定私有目录下要操作的文件
//            writer = new PrintWriter(getApplicationContext().openFileOutput("save2.txt",MODE_PRIVATE));


            //操作sd卡里面的数据
            writer = new PrintWriter(new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/save3.txt"));

            writer.println("hello world. hahahha ");
            writer.println("hello world3333. hahahha ");
            writer.println("hello world333. hahahha ");
            writer.println("hello world3333. hahahha ");
            writer.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }finally {
            if (writer != null) {
                writer.close();
            }
        }

    }

    /*
    sharedpreferences文件存在与/data/data/packagename/shared_prefs目录下
    MODE_PRIVATE:除了用户和用户所属组，其他人都没有操作权限
    MODE_WORLD_READABLE:所有人都可读
    MODE_WORLD_WRITEABLE:所有人都可写
     */
    public void savesp(View view) {
        /*
        通过getSharedPreferences(name,mode):生成xml文件是application级别的,名字是自定义的
         */
//        SharedPreferences sp = getSharedPreferences("share1", MODE_WORLD_READABLE);

        /*
        getPreferences(mode):生成的xml文件是activity级别的.名字是xxxActivity.xml
         */
//        SharedPreferences sp = getPreferences(MODE_PRIVATE);

        /*
        PreferenceManager.getDefaultSharedPreferences(mode):
        它是application级别的,名字是以packageName_preferences.xml来命名
         */

        /*
        所谓的application级别,指的是所有的activity都能使用该xml文件里面的数据.
        activity级别,就只有该activity能使用该xml文件里面的数据
         */
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        SharedPreferences.Editor editor = sp.edit();
        editor.putString("name", "huangjundong hahah ");
        editor.commit();
    }

    public void getsp(View view) {
        /*
        getSharedPreferences("share", MODE_PRIVATE):文件的名字,打开方式.
        sp.getString("name", null): 获取文件中key为name的value,默认值是null
         */
//        SharedPreferences sp = getSharedPreferences("share1", MODE_PRIVATE);

//        SharedPreferences sp = getPreferences(MODE_PRIVATE);;

        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        String name = sp.getString("name", null);
        System.out.println("----->name: " + name);
    }

}
