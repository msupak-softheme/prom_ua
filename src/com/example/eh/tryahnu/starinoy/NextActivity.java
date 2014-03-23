package com.example.eh.tryahnu.starinoy;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

public class NextActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        List<View> pages = new ArrayList<View>();
        List<ModelOrder> orders = SQLHelper.getInstance(NextActivity.this).getOrdersList(null);
        String orderId = getIntent().getStringExtra("o_id");
        int focusPageId = -1;
        ModelOrder order;
        TextView tv;
        for(int i = 0; i < orders.size(); i++){
        	order = orders.get(i);
        	if(orderId.equals(order.getId())){
        		focusPageId = i;
        	}
        	tv = new TextView(this);
        	tv.setText(order.getName()+" \n"+
        			order.getPhone()+" \n"+
        			order.getDate()+" \n"+
        			order.getEmail());
        	pages.add(tv);
        }
        PageAdapter pa = new PageAdapter(pages);
        ViewPager viewPager = new ViewPager(this);
        viewPager.setAdapter(pa);
        viewPager.setCurrentItem(focusPageId != -1 ? focusPageId : 1);     
        setContentView(viewPager);
	}
}
