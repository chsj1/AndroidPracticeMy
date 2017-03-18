package test.hjd.com.selfwidget;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by huangjundong on 2017/3/18.
 */
public class RefreshListviewActivity extends Activity {


    private List<String> datas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.refreshlistview_main);

        datas = new ArrayList<>();
        for (int i = 0; i < 30; ++i) {
            datas.add("hjd is a handsome boy" + i);
        }


        final RefreshListView rlv = (RefreshListView) findViewById(R.id.rlv);
        final MyBaseAdapter baseAdapter = new MyBaseAdapter();
        rlv.setAdapter(baseAdapter);
        rlv.setOnRereshListener(new RefreshListView.OnRefreshListener() {
            @Override
            public void headerRefresh() {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(2000);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        datas.add(0, "这时下拉刷新加载的数据");
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                baseAdapter.notifyDataSetChanged();
                                rlv.onFooterRefreshFinish();
                            }
                        });

                    }
                }).start();
            }

            @Override
            public void footerRefresh() {
                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        try {
                            Thread.sleep(2000);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }


                        datas.add("这时加载出来的数据");

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                baseAdapter.notifyDataSetChanged();
                                rlv.onFooterRefreshFinish();
                            }
                        });
                    }
                }).start();
            }
        });
    }


    class MyBaseAdapter extends BaseAdapter {
        public MyBaseAdapter() {
            super();
        }

        @Override
        public int getCount() {
            return datas.size();
        }

        @Override
        public Object getItem(int position) {
            return datas.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            TextView textView = new TextView(getApplicationContext());
            textView.setText(datas.get(position));
            textView.setTextSize(18);

            return textView;
        }
    }
}

