package com.yonyou.customview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.yonyou.adapter.BaseListAdapter;
import com.yonyou.ceshi.BaseActivity;
import com.yonyou.ceshi.R;
import com.yonyou.ceshi.http.OkHttpActivity;

import java.util.ArrayList;
import java.util.List;

public class ListViewInScrollViewActivity extends BaseActivity {
    private ListView lv;
    private Button bt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_in_scroll_view);
        List<String>list = new ArrayList<>();
        for(int i=0;i<200;i++){
            list.add("第"+i+"个");
        }
        lv = (ListView)findViewById(R.id.lv);
        lv.setAdapter(new MyAdapter(this,list));
        bt = (Button)findViewById(R.id.bt);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int height = lv.getMeasuredHeight();
                Log.e("tag","height"+height);
            }
        });
    }
    private  class MyAdapter extends BaseListAdapter<String>{

        public MyAdapter(Context context, List<String> list) {
            super(context, list);
        }

        @Override
        public View getItemView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder = null;
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.item_test, null);
                viewHolder = new ViewHolder();
                viewHolder.tv = (TextView) convertView.findViewById(R.id.tv_test);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            String text = list.get(position);
            viewHolder.tv.setText(text);

            return convertView;
        }
        class ViewHolder {
            TextView tv;
        }
    }
}