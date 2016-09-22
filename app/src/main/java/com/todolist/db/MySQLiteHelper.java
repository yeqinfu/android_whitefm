package com.todolist.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

import com.orhanobut.logger.Logger;
import com.whitefm.base.Utils_Constant;

/**
 * Created by yeqinfu on 9/22/16.
 */
public class MySQLiteHelper extends SQLiteOpenHelper {

    //数据库版本
    private static final int VERSION = Utils_Constant.db_version;


    public static MySQLiteHelper mySQLiteHelper;
    public static MySQLiteHelper getInstance(Context context){
        if(mySQLiteHelper == null){
            synchronized (MySQLiteHelper.class){
                if(mySQLiteHelper == null){
                    mySQLiteHelper = new MySQLiteHelper(context,"rxdb.sqlite");
                }
            }
        }
        return mySQLiteHelper;
    }

    private MySQLiteHelper(Context context, String name) {
        this(context, name, VERSION);
    }

    private MySQLiteHelper(Context context, String name, int version) {
        this(context, name, null, version);
    }

    //调用父类构造器
    private MySQLiteHelper(Context context, String name, CursorFactory factory,
                          int version) {
        super(context, name, factory, version);
        Logger.d("----------------------MySQLiteHelper--------------");

    }

    /**
     * 当数据库首次创建时执行该方法，一般将创建表等初始化操作放在该方法中执行.
     * 重写onCreate方法，调用execSQL方法创建表
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        Logger.d("----------------------oncreate--------------");
        for (DB_Enum item : DB_Enum.values()) {
            db.execSQL(item.getCreate_sql());
            Logger.i("createtable"+item.getCreate_sql());
        }
    }

    //当打开数据库时传入的版本号与当前的版本号不同时会调用该方法
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Logger.d("----------------------onUpgrade--------------");
    }

}
