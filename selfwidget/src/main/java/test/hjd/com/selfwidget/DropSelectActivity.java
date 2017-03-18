package test.hjd.com.selfwidget;

import android.app.Activity;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by huangjundong on 2017/3/11.
 */

public class DropSelectActivity extends Activity implements View.OnClickListener,AdapterView.OnItemClickListener{
    ListView listView;
    List<String> datas;
    private EditText et_input;
    private PopupWindow popupWindow;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drop_select_main);


        ImageButton ib_dropselect = (ImageButton) findViewById(R.id.ib_dropselect);
        ib_dropselect.setOnClickListener(this);


        datas = new ArrayList<>();
        for(int i = 0 ; i < 20 ; ++i) {
            datas.add((25000 + i) + "");
        }
        listView = new ListView(this);
        listView.setDividerHeight(0);
        listView.setAdapter(new MyBaseAdapter());
        listView.setOnItemClickListener(this);

        et_input = (EditText) findViewById(R.id.et_input);
    }

    class MyBaseAdapter extends BaseAdapter {
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
        public View getView(final int position, View convertView, ViewGroup parent) {

            View view = View.inflate(getApplicationContext(), R.layout.drop_select_item, null);

            TextView tv_content = (TextView) view.findViewById(R.id.tv_content);
            tv_content.setText(datas.get(position));

            ImageButton ib_delete = (ImageButton) view.findViewById(R.id.ib_delete);
            ib_delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    datas.remove(position);
                    notifyDataSetChanged();
                }
            });
            return view;
        }
    }
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.ib_dropselect) {
            System.out.println("---------->点击了下拉菜单");

            popupWindow = new PopupWindow(listView,et_input.getWidth(),200);
            popupWindow.setOutsideTouchable(true);
            popupWindow.setBackgroundDrawable(new BitmapDrawable());
            popupWindow.setFocusable(true);

            popupWindow.showAsDropDown(et_input);

        }
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        et_input.setText(datas.get(position));
        popupWindow.dismiss();
    }
}
