package com.example.eh.tryahnu.starinoy;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class TableOrder extends SQLiteOpenHelper{
	
	private static final int DB_VERSION = 1;
	private static final String DB_NAME = "ets1";

	public static final String TABLE_NAME = "t_order";
	public static final String KEY__ID = "_id";
	
	public static final String KEY_ID = "id";
	public static final String KEY_STATE = "state";
	public static final String KEY_NAME = "name";
	public static final String KEY_COMPANY = "company";
	public static final String KEY_PHONE = "phone";
	public static final String KEY_EMAIL = "email";
	public static final String KEY_INDEX = "index1";
	public static final String KEY_PAYMENT_TYPE = "paymentType";
	public static final String KEY_DTYPE = "deliveryType";
	public static final String KEY_DCOST = "deliverycost";
	public static final String KEY_PCOMMENT = "payercomment";
	public static final String KEY_SCOMMENT = "salescomment";
	public static final String KEY_PRICE = "price";
	public static final String KEY_DATE = "date";

	
	
	private static final String DROP_TABLE = "drop table if exists %s";
	private static final String CREATE_TABLE = "create table " + TABLE_NAME
			+ " ( " + KEY__ID + " integer primary key autoincrement, " 
			+ KEY_ID + " TEXT, "
			+ KEY_STATE + " TEXT, "
			+ KEY_NAME + " TEXT, "
			+ KEY_COMPANY + " TEXT, "
			+ KEY_PHONE + " TEXT, "
			+ KEY_EMAIL + " TEXT, "
			+ KEY_INDEX + " TEXT, "
			+ KEY_PAYMENT_TYPE + " TEXT, "
			+ KEY_DTYPE + " TEXT, "
			+ KEY_DCOST + " TEXT, "
			+ KEY_PCOMMENT + " TEXT, "
			+ KEY_DATE + " TEXT, "
			+ KEY_SCOMMENT + " TEXT, "
			+ KEY_PRICE + " TEXT)";

	public TableOrder(Context context) {
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
