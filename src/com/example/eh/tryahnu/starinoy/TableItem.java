package com.example.eh.tryahnu.starinoy;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class TableItem extends SQLiteOpenHelper{
	
	private static final int DB_VERSION = 1;
	private static final String DB_NAME = "ets";

	public static final String TABLE_NAME = "t_item";
	public static final String KEY__ID = "_id";
	
	public static final String KEY_ID = "id";
	public static final String KEY_ORDER_ID = "order_id";
	public static final String KEY_NAME = "name";
	public static final String KEY_Q = "quantity";
	public static final String KEY_CUR = "currency";
	public static final String KEY_IMAGE = "image";
	public static final String KEY_URL = "url";
	public static final String KEY_PRICE = "price";
	
	private static final String DROP_TABLE = "drop table if exists %s";
	private static final String CREATE_TABLE = "create table " + TABLE_NAME
			+ " ( " + KEY__ID + " integer primary key autoincrement, " 
			+ KEY_ID + " TEXT, "
			+ KEY_ORDER_ID + " TEXT, "
			+ KEY_NAME + " TEXT, "
			+ KEY_Q + " TEXT, "
			+ KEY_CUR + " TEXT, "
			+ KEY_IMAGE + " TEXT, "
			+ KEY_URL + " TEXT, "
			+ KEY_PRICE + " TEXT)";

	public TableItem(Context context) {
		super(context, DB_NAME, null, DB_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase sqLiteDatabase) {
		sqLiteDatabase.execSQL(CREATE_TABLE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
		sqLiteDatabase.execSQL(String.format(DROP_TABLE, TABLE_NAME));
		this.onCreate(sqLiteDatabase);
	}
}
