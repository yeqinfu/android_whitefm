package com.todolist.db;

/**
 * Created by yeqinfu on 9/22/16.
 */
public enum DB_Enum {
    DB_TODO("t_todo", "create table if not exists t_todo" +
            "(id int primary key,name varchar,sex varchar)"),
    DB_TEST("t_test", "create table if not exists t_test" +
            "(id int primary key,name varchar)");

    private String table_name;
    private String create_sql;

    DB_Enum(String table_name, String create_sql) {
        this.table_name = table_name;
        this.create_sql = create_sql;
    }

    public String getTable_name() {
        return table_name;
    }

    public void setTable_name(String table_name) {
        this.table_name = table_name;
    }

    public String getCreate_sql() {
        return create_sql;
    }

    public void setCreate_sql(String create_sql) {
        this.create_sql = create_sql;
    }
}
