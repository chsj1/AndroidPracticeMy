package test.hjd.com.storage;

import android.content.SharedPreferences;
import android.content.res.XmlResourceParser;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Xml;
import android.view.View;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }


    public void save(View view) {
        /*
        MODE_PRIVATE:-rw- rw- ---
        MODE_WORLD_READABLE:-rw- rw- r--
        MODE_WORLD_WRITABLE:-rw- rw- -w-
         */
        SharedPreferences sp = getSharedPreferences("share", MODE_WORLD_WRITEABLE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("address", "shanghai");
        editor.commit();

    }


    public void get(View view) {
        SharedPreferences sp = getSharedPreferences("share", MODE_APPEND);
        int salary = sp.getInt("salary", 0);
        System.out.println("------>salary: " + salary);
    }

    public void gerenateXml(View view) {

        System.out.println("-------->生成xml");

        List<Person> persons = new ArrayList<>();
        for (int i = 0; i < 2; ++i) {
            Person p = new Person(i, "hjd" + i, (25000 + i));

            persons.add(p);
        }

        XmlSerializer xs = Xml.newSerializer();
        try {
            xs.setOutput(getApplicationContext().openFileOutput("persons.xml", MODE_PRIVATE), "utf-8");
//            xs.setOutput(new FileOutputStream(new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/persons.xml")), "utf-8");

            xs.startDocument("utf-8", true);
            xs.startTag(null, "persons");

            for (Person person : persons) {
                xs.startTag(null, "person");

                xs.attribute(null, "id", person.id + "");

                xs.startTag(null, "name");
                xs.text(person.name);
                xs.endTag(null, "name");

                xs.startTag(null, "salary");
                xs.text(person.salary + "");
                xs.endTag(null, "salary");

                xs.endTag(null, "person");
            }
            xs.endTag(null, "persons");
            xs.endDocument();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void parseXml(View view) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("-------->解析xml");
                List<Person> persons = null;

                XmlPullParser xpp = Xml.newPullParser();
                try {
                    xpp.setInput(getApplicationContext().openFileInput("persons.xml"), "utf-8");
//                    xpp.setInput(new FileInputStream(new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/persons.xml")), "utf-8");

                    int type = xpp.getEventType();

                    //这里要放在外面
                    Person p = null;
                    while (type != XmlPullParser.END_DOCUMENT) {
                        String currentName = xpp.getName();

                        switch (type) {
                            case XmlPullParser.START_TAG:
                                if (currentName.equals("persons")) {
                                    persons = new ArrayList<>();
                                } else if (currentName.equals("person")) {
                                    p = new Person();
                                    p.id = Integer.parseInt(xpp.getAttributeValue(null, "id"));
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

                        /*
                        这里不要写成xpp.next()。
                        否则会一直GC
                         */
                        type = xpp.next();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }


                System.out.println("------->解析到的数据是: " + persons);
            }
        }).start();
    }


}
