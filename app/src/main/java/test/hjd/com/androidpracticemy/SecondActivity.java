package test.hjd.com.androidpracticemy;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Created by huangjundong on 2017/2/25.
 */

public class SecondActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_2);

        System.out.println("----------------->SecondActivity: onCreate");


        System.out.println("s-------->getTaskId: " + getTaskId() );
//        Intent intent = getIntent();
//        String username = intent.getStringExtra("username");
//        System.out.println("-------->传过来的数据是: " + username);
//
//        Bundle bundle = intent.getBundleExtra("salaryBundle");
//        int salary = bundle.getInt("salary");
//        System.out.println("------>hjd的薪水是: " + salary);
//
//
//        Person person = (Person) intent.getSerializableExtra("person");
//        System.out.println("------->person.getName: " + person.getName());
//        System.out.println("------->person.getSalary: " + person.getSalary());
//
//
//        Person2 person2 = intent.getParcelableExtra("person2");
//        System.out.println("------->person.getName: " + person2.getUsername());
//        System.out.println("------->person.getSalary: " + person2.getSalary());


        //给第一个activity回传数据
//        Intent data = new Intent();
//        data.putExtra("callback", "这时传回来的数据");
//        setResult(200, data);

    }

    @Override
    protected void onStart() {
        super.onStart();

        System.out.println("----------------->SecondActivity: onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();

        System.out.println("----------------->SecondActivity: onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();

        System.out.println("----------------->SecondActivity: onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();

        System.out.println("----------------->SecondActivity: onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        System.out.println("----------------->SecondActivity: onDestroy");
    }

    public void start(View view) {
        Intent intent = new Intent(SecondActivity.this, MyActivity.class);
        startActivity(intent);
    }


    public void start2(View view) {
        Intent intent = new Intent(SecondActivity.this, SecondActivity.class);
        startActivity(intent);
    }
}
