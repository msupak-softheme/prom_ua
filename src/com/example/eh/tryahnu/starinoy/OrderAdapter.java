package com.example.eh.tryahnu.starinoy;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class OrderAdapter extends BaseAdapter {
	
	private List<ModelOrder> list;
	private LayoutInflater li;
	
	public OrderAdapter(Context ctx){
		li = LayoutInflater.from(ctx);
	}
	
	public void setList(List<ModelOrder> list){
		this.list = list;
	}
	
	public ModelOrder getOrderById(int i){
		return list.get(i);
	}

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Object getItem(int arg0) {
		return list.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		return 0;
	}

	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2) {
		ViewHolder holder;
		if (arg1==null) {
			arg1 = li.inflate(R.layout.order_l, null);
			holder = new ViewHolder();
			holder.tv1 = (TextView)arg1.findViewById(R.id.textView1);
			holder.tv2 = (TextView)arg1.findViewById(R.id.textView2);
			holder.tv3 = (TextView)arg1.findViewById(R.id.textView3);
			arg1.setTag(holder);
		} else {
            holder = (ViewHolder) arg1.getTag();
        }
		ModelOrder order = list.get(arg0);
		holder.tv1.setText("¹"+order.getId()+" "+order.getName());
		holder.tv2.setText(order.getPrice()+" "+order.getPayercomment());
		holder.tv3.setText(order.getDate());
		return arg1;
	}
	
	static class ViewHolder {
    	TextView tv1;
    	TextView tv2;
    	TextView tv3;
    }

}
