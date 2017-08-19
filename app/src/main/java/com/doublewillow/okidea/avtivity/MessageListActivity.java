package com.doublewillow.okidea.avtivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;

import com.doublewillow.lib_frame.BaseActivity;
import com.doublewillow.okidea.R;
import com.doublewillow.okidea.adapter.MyAdapter;
import com.doublewillow.okidea.module.Person;

import java.util.ArrayList;
import java.util.List;

public class MessageListActivity extends BaseActivity {

    private SwipeRefreshLayout mRefresh;
    private RecyclerView mRecyclerview;
    private List<Person> personList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_list);
        initData();
        mRecyclerview = getView(R.id.recycle_view);
        mRefresh = getView(R.id.btn_pull_refresh);
        mRecyclerview.setLayoutManager(new LinearLayoutManager(this, LinearLayout.VERTICAL, false));
        MyAdapter adapter = new MyAdapter(personList);
        mRecyclerview.setAdapter(adapter);
    }
    public static Intent createIntent(Context context) {
        return new Intent(context, MessageListActivity.class);
    }
    private void initData() {
        Person a = new Person("zhang", "123456");
        Person b = new Person("wang", "123456");
        Person c = new Person("qian", "123456");
        Person d = new Person("sun", "123456");
        Person e = new Person("li", "123456");
        Person f = new Person("asd", "123456");
        Person g = new Person("zhaxcng", "123456");
        Person h = new Person("sdfg", "123456");
        Person i = new Person("zhfgs3rtang", "123456");
        Person j = new Person("zhaertwerng", "123456");
        Person k = new Person("zhasdfgng", "123456");
        Person l = new Person("zhavbng", "123456");
        Person m = new Person("hjljl", "123456");
        Person n = new Person("zh56u45ang", "123456");
        Person o = new Person("45678", "123456");

        personList.add(a);
        personList.add(b);
        personList.add(c);
        personList.add(d);
        personList.add(e);
        personList.add(f);
        personList.add(g);
        personList.add(h);
        personList.add(i);
        personList.add(j);
        personList.add(k);
        personList.add(l);
        personList.add(m);
        personList.add(n);
        personList.add(o);
    }
}