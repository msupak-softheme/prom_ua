package com.example.eh.tryahnu.starinoy;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class SQLHelper {
	
	private TableItem tItem;
	private TableOrder tOrder;
	
	static private SQLHelper mSQLHelper;
    
    private SQLHelper(Context ctx){
    	tItem = new TableItem(ctx);
    	tOrder = new TableOrder(ctx);
    }

    public static synchronized SQLHelper getInstance(Context ctx){
		if(mSQLHelper == null){
			mSQLHelper = new SQLHelper(ctx);
		}
		return mSQLHelper;
	}
    
    synchronized public void addOrder(List<ModelOrder> list, Context ctx){
		try {
			SQLiteDatabase mDb1 = tOrder.getWritableDatabase();
			mDb1.beginTransaction();
			for (ModelOrder order : list) {
				mDb1.insert(TableOrder.TABLE_NAME, null,
						createContentValuesOrder(order));
				addItems(order.getItemList(), order.getId());
			}
			mDb1.setTransactionSuccessful();
			mDb1.endTransaction();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private boolean addItems(List<ModelItem> itemList, String orderId) {
		try{
			SQLiteDatabase mDb1 = tItem.getWritableDatabase();
			
			mDb1.beginTransaction();
			for(ModelItem item : itemList){
				mDb1.insert(TableItem.TABLE_NAME, null, 
						createContentValuesItem(item, orderId));
			}
			mDb1.setTransactionSuccessful();
			mDb1.endTransaction();
			return true;
		}catch (Exception e) {
	        e.printStackTrace();
	    }
		return false;
	}

	private ContentValues createContentValuesItem(ModelItem item, String orderId) {
		ContentValues content = new ContentValues();
		content.put(TableItem.KEY_ID, item.getId());
		content.put(TableItem.KEY_ORDER_ID, orderId);
		content.put(TableItem.KEY_NAME, item.getName());
		content.put(TableItem.KEY_Q, item.getQuantity());
		content.put(TableItem.KEY_CUR, item.getCurrency());
		content.put(TableItem.KEY_IMAGE, item.getImage());
		content.put(TableItem.KEY_URL, item.getUrl());
		content.put(TableItem.KEY_PRICE, item.getPrice());
		return content;
	}

	private ContentValues createContentValuesOrder(ModelOrder order) {
		ContentValues content = new ContentValues();
		content.put(TableOrder.KEY_ID, order.getId());
		content.put(TableOrder.KEY_STATE, order.getState());
		content.put(TableOrder.KEY_NAME, order.getName());
		content.put(TableOrder.KEY_COMPANY, order.getCompany());
		content.put(TableOrder.KEY_PHONE, order.getPhone());
		content.put(TableOrder.KEY_EMAIL, order.getEmail());
		content.put(TableOrder.KEY_INDEX, order.getIndex());
		content.put(TableOrder.KEY_PAYMENT_TYPE, order.getPaymentType());
		content.put(TableOrder.KEY_DTYPE, order.getDeliveryType());
		content.put(TableOrder.KEY_DCOST, order.getDeliverycost());
		content.put(TableOrder.KEY_PCOMMENT, order.getPayercomment());
		content.put(TableOrder.KEY_SCOMMENT, order.getSalescomment());
		content.put(TableOrder.KEY_DATE, order.getPrice());
		content.put(TableOrder.KEY_PRICE, order.getPrice());
		return content;
	}
	
	
	synchronized public List<ModelOrder> getOrdersList(String filter){
		List<ModelOrder> list = new ArrayList<ModelOrder>();
		SQLiteDatabase mDb = tOrder.getReadableDatabase();
		try{
			Cursor cursor = mDb.query(TableOrder.TABLE_NAME, 
					new String[] {
					TableOrder.KEY_ID, TableOrder.KEY_STATE, TableOrder.KEY_NAME,
					TableOrder.KEY_COMPANY, TableOrder.KEY_PHONE,
					TableOrder.KEY_EMAIL, TableOrder.KEY_INDEX,
					TableOrder.KEY_PAYMENT_TYPE, TableOrder.KEY_DTYPE,
					TableOrder.KEY_DCOST, TableOrder.KEY_PCOMMENT, 
					TableOrder.KEY_SCOMMENT, TableOrder.KEY_PRICE, TableOrder.KEY_DATE}, buildSearchField(filter), 
					null, null, null, null);
			if (cursor != null && cursor.getCount() != 0){
				cursor.moveToFirst();
				ModelOrder order;
				do{
					order = new ModelOrder();
					order.setId(cursor.getString(cursor.getColumnIndex(TableOrder.KEY_ID)));
					order.setState(cursor.getString(cursor.getColumnIndex(TableOrder.KEY_STATE)));
					order.setName(cursor.getString(cursor.getColumnIndex(TableOrder.KEY_NAME)));
					order.setCompany(cursor.getString(cursor.getColumnIndex(TableOrder.KEY_COMPANY)));
					order.setPhone(cursor.getString(cursor.getColumnIndex(TableOrder.KEY_PHONE)));
					order.setEmail(cursor.getString(cursor.getColumnIndex(TableOrder.KEY_EMAIL)));
					order.setIndex(cursor.getString(cursor.getColumnIndex(TableOrder.KEY_INDEX)));
					order.setPaymentType(cursor.getString(cursor.getColumnIndex(TableOrder.KEY_PAYMENT_TYPE)));
					order.setDeliveryType(cursor.getString(cursor.getColumnIndex(TableOrder.KEY_DTYPE)));
					order.setDeliverycost(cursor.getString(cursor.getColumnIndex(TableOrder.KEY_DCOST)));
					order.setPayercomment(cursor.getString(cursor.getColumnIndex(TableOrder.KEY_PCOMMENT)));
					order.setSalescomment(cursor.getString(cursor.getColumnIndex(TableOrder.KEY_SCOMMENT)));
					order.setPrice(cursor.getString(cursor.getColumnIndex(TableOrder.KEY_PRICE)));
					order.setDate(cursor.getString(cursor.getColumnIndex(TableOrder.KEY_DATE)));
					order.setItemList(getItemList(order.getId()));
					
					list.add(order);
				}while(cursor.moveToNext());
			}
		}finally{
			if(mDb != null){
				mDb.close();
			}
		}
		return list;
	}
	
	
	
	private List<ModelItem> getItemList(String orderId){
		List<ModelItem> itemList = new ArrayList<ModelItem>();
		SQLiteDatabase mDb = tItem.getReadableDatabase();
		try{
			Cursor cursor = mDb.query(TableItem.TABLE_NAME, 
					new String[] {
					TableItem.KEY_ID, TableItem.KEY_ORDER_ID, TableItem.KEY_NAME,
					TableItem.KEY_Q, TableItem.KEY_CUR,TableItem.KEY_IMAGE, 
					TableItem.KEY_URL, TableItem.KEY_PRICE }, TableItem.KEY_ORDER_ID + " = ?", 
					new String[]{orderId}, null, null, null);
			if (cursor != null && cursor.getCount() != 0){
				cursor.moveToFirst();
				ModelItem item;
				do{
					item = new ModelItem();
					item.setId(cursor.getString(cursor.getColumnIndex(TableItem.KEY_ID)));
					item.setName(cursor.getString(cursor.getColumnIndex(TableItem.KEY_NAME)));
					item.setQuantity(cursor.getString(cursor.getColumnIndex(TableItem.KEY_Q)));
					item.setCurrency(cursor.getString(cursor.getColumnIndex(TableItem.KEY_CUR)));
					item.setImage(cursor.getString(cursor.getColumnIndex(TableItem.KEY_IMAGE)));
					item.setUrl(cursor.getString(cursor.getColumnIndex(TableItem.KEY_URL)));
					itemList.add(item);
				}while(cursor.moveToNext());
			}
		}finally{
			if(mDb != null){
				mDb.close();
			}
		}
		return itemList;
	}
	
	private String buildSearchField(String filterStr){
		if(filterStr == null) return null;
		return TableOrder.KEY_ID + " like '%" + filterStr + "%' or "
				+ TableOrder.KEY_NAME + " like '%" + filterStr + "%' or "
				+ TableOrder.KEY_PHONE + " like '%" + filterStr + "%'";
	}
	
    synchronized public void clearTables(){
    	SQLiteDatabase mDb = tItem.getWritableDatabase();
    	tItem.onUpgrade(mDb, 1, 1);
    	mDb.close();
    	mDb = tOrder.getWritableDatabase();
    	tOrder.onUpgrade(mDb, 1, 1);
    	mDb.close();
    }
}
