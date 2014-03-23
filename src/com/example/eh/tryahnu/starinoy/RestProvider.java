package com.example.eh.tryahnu.starinoy;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.util.Pair;

public class RestProvider {

	private static final String REQUEST_URL = "http://my.deal.by/cabinet/export_orders/xml/31036?hash_tag=0ecc3ed1de227f51bf44ca80ae7abf6b";
	
	private Pair<Integer, String> sendGetRequest(String url){
		HttpClient client = new DefaultHttpClient();
		HttpGet getRequest = new HttpGet(url);
		try{
			HttpResponse response = client.execute(getRequest);
			return new Pair(response.getStatusLine().getStatusCode(),
					EntityUtils.toString(response.getEntity(), "UTF-8"));
				
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			client.getConnectionManager().shutdown();
		}
		return new Pair(500, null);
	}
	
	public String getItemXml(){
		Pair<Integer, String> result = sendGetRequest(REQUEST_URL);
		if(result.first == 200){
			return result.second;
		}else{
			return null;
		}
	}
}
