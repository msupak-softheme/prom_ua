package com.example.eh.tryahnu.starinoy;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	private LoadOrderAsyncTask task;
	private ListView lv;
	private OrderAdapter adapter;
	private EditText etSearch;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Button btnLoad = (Button)findViewById(R.id.button1);
		btnLoad.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(!Utils.isOnline(MainActivity.this)){
					Toast.makeText(MainActivity.this, R.string.net_error, Toast.LENGTH_SHORT).show();
					return;
				}
				task = new LoadOrderAsyncTask();
				task.execute();
			}
		});
		
		Button btnSearch = (Button)findViewById(R.id.button2);
		btnSearch.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				new SearchOrderAsyncTask().execute(etSearch.getText().toString());
			}
		});

		lv = (ListView)findViewById(R.id.listView1);
		etSearch = (EditText)findViewById(R.id.editText1);
	}

	private class LoadOrderAsyncTask extends AsyncTask<Void, Void, List<ModelOrder>>{

		@Override
		protected List<ModelOrder> doInBackground(Void... params) {
			String restResult = new RestProvider().getItemXml();
			if(restResult == null)
				return null;
			XMLParser parser = new XMLParser();
			if(!parser.setParserData(restResult))
				return null;
			List<ModelOrder> list = parser.getList();
			if(list.isEmpty())
				return null;
			SQLHelper.getInstance(MainActivity.this).clearTables();
			SQLHelper.getInstance(MainActivity.this).addOrder(list, MainActivity.this);
			return list;
		}
		
		protected void onPostExecute(java.util.List<ModelOrder> result) {
			if(result != null)
				setListAdapter(result);
		}
		
	};
	
	private class SearchOrderAsyncTask extends AsyncTask<String, Void, List<ModelOrder>>{

		@Override
		protected List<ModelOrder> doInBackground(String... params) {
			return SQLHelper.getInstance(MainActivity.this).getOrdersList(params[0].equals("")?null:params[0]);
		}
		
		protected void onPostExecute(java.util.List<ModelOrder> result) {
			setListAdapter(result);
		}
		
	};
	

	private void setListAdapter(List<ModelOrder> result){
		if(adapter == null){
			adapter = new OrderAdapter(this);
			adapter.setList(result);
			lv.setAdapter(adapter);
			lv.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1,
						int arg2, long arg3) {
					ModelOrder mo = adapter.getOrderById(arg2);
					Intent i = new Intent(MainActivity.this, NextActivity.class);
					i.putExtra("o_id", mo.getId());
					startActivity(i);
				}
			});
		}else{
			adapter.setList(result);
			adapter.notifyDataSetChanged();
		}
	}
	
	
}
