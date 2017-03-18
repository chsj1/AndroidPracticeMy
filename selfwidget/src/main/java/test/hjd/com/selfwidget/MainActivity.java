package test.hjd.com.selfwidget;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button bt_youku = (Button) findViewById(R.id.youku);
        bt_youku.setOnClickListener(this);

        Button bt_lunbo = (Button) findViewById(R.id.bt_lunbo);
        bt_lunbo.setOnClickListener(this);

        Button bt_dropselect = (Button) findViewById(R.id.bt_dropselect);
        bt_dropselect.setOnClickListener(this);

        Button bt_slidemenu = (Button) findViewById(R.id.bt_slidemenu);
        bt_slidemenu.setOnClickListener(this);

        Button bt_toggleview = (Button) findViewById(R.id.bt_toggleview);
        bt_toggleview.setOnClickListener(this);

        Button bt_refreshlistview = (Button) findViewById(R.id.bt_refreshlistview);
        bt_refreshlistview.setOnClickListener(this);

        System.out.println("------->onCreate");


//        MyView myView = new MyView(this);
//        setContentView(myView);

    }

    @Override
    protected void onResume() {
        super.onResume();

        System.out.println("------>onResume");
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.youku) {
            Intent intent = new Intent(MainActivity.this, YoukuActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.bt_lunbo) {
            Intent intent = new Intent(MainActivity.this, LunboActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.bt_dropselect) {
            Intent intent = new Intent(MainActivity.this, DropSelectActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.bt_slidemenu) {
            Intent intent = new Intent(MainActivity.this, SlideMenuActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.bt_toggleview) {
            Intent intent = new Intent(MainActivity.this, ToggleViewActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.bt_refreshlistview) {
            Intent intent = new Intent(MainActivity.this, RefreshListviewActivity.class);
            startActivity(intent);
        }
    }
}
