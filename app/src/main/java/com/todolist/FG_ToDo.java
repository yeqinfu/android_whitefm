package com.todolist;

import android.content.ContentValues;
import android.database.Cursor;
import android.support.design.widget.Snackbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.orhanobut.logger.Logger;
import com.squareup.sqlbrite.BriteDatabase;
import com.squareup.sqlbrite.SqlBrite;
import com.todolist.db.DB_Enum;
import com.todolist.db.MySQLiteHelper;
import com.whitefm.R;
import com.whitefm.basefm.FG_BaseFM;

import java.util.ArrayList;
import java.util.UUID;

import butterknife.Bind;
import butterknife.OnClick;
import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;

/**
 * Created by yeqinfu on 9/22/16.
 */
public class FG_ToDo extends FG_BaseFM {
    @Bind(R.id.et_input)
    EditText etInput;
    @Bind(R.id.btn_insert)
    Button btnInsert;
    @Bind(R.id.lv_list)
    ListView lvList;
    ArrayList<String> data = new ArrayList<String>();

    ArrayAdapter adapter;
    SqlBrite sqlBrite = SqlBrite.create();
    @Override
    protected int getFgRes() {
        return R.layout.fg_todo;
    }



    @Override
    protected void afterViews() {
        Logger.d("----------------------afterViews--------------");
        BriteDatabase db = sqlBrite.wrapDatabaseHelper(MySQLiteHelper.getInstance(getActivity()), Schedulers.io());
        Observable<SqlBrite.Query> t_test = db.createQuery(DB_Enum.DB_TEST.getTable_name(), "SELECT * FROM " + DB_Enum.DB_TEST.getTable_name());
        t_test.subscribe(new Subscriber<SqlBrite.Query>() {
            @Override
            public void onCompleted() {
                Logger.d("----------------------onCompleted--------------");

            }

            @Override
            public void onError(Throwable e) {
                Logger.d("db 错误", e.getMessage());

            }

            @Override
            public void onNext(SqlBrite.Query query) {
                Logger.d("----------------------onNext--------------");

                Cursor cursor = query.run();
                //获取name列的索引
                int nameIndex = cursor.getColumnIndex("name");
                //获取level列的索引
                int idIndex = cursor.getColumnIndex("id");
                data.clear();
                String result = "";
                for (cursor.moveToFirst(); !(cursor.isAfterLast()); cursor.moveToNext()) {
                    result = result + cursor.getString(nameIndex) + "\t\t";
                    result = result + cursor.getInt(idIndex) + "\n";
                    Logger.d("for each"+result);
                    data.add(result);
                }
                adapter.notifyDataSetChanged();
            }
        });
        adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1,
                android.R.id.text1, data);
        lvList.setAdapter(adapter);
    }

    @OnClick(R.id.btn_insert)
    void click(View v) {
        switch (v.getId()) {
            case R.id.btn_insert:
                if (!TextUtils.isEmpty(etInput.getText())) {
                    BriteDatabase db = sqlBrite.wrapDatabaseHelper(MySQLiteHelper.getInstance(getActivity()), Schedulers.io());
                    long l=db.insert(DB_Enum.DB_TEST.getTable_name(), createUser(UUID.randomUUID().toString(), etInput.getText() + ""));
                    Snackbar.make(v, "插入结果"+l, Snackbar.LENGTH_SHORT)
                            .setAction("Action", null).show();
                    etInput.setText("");
                } else {
                    Snackbar.make(v, "输入为空", Snackbar.LENGTH_SHORT)
                            .setAction("Action", null).show();
                }
                break;
        }
    }

    private ContentValues createUser(String id, String name) {
        ContentValues values = new ContentValues();
        values.put("id", id);
        values.put("name", name);
        return values;
    }

}
