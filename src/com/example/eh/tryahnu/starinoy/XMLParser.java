package com.example.eh.tryahnu.starinoy;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import android.content.res.XmlResourceParser;

public class XMLParser {
	
	private XmlPullParser xmlResourceParser;

	public boolean setParserData(String str){
		try {
			XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
			factory.setNamespaceAware(true);
			xmlResourceParser = factory.newPullParser();
			xmlResourceParser.setInput(new StringReader(str));
			return true;
		} catch (XmlPullParserException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public List<ModelOrder> getList(){
		List<ModelOrder> list = new ArrayList<ModelOrder>();
		ModelOrder order = null;
		ModelItem item = null;
		try {
			int eventType = -1;
			while (eventType != XmlResourceParser.END_DOCUMENT) {
				try {
					if (eventType == XmlResourceParser.START_TAG) {

						String strNode = xmlResourceParser.getName();
						while (strNode == null) {
							eventType = xmlResourceParser.next();
							strNode = xmlResourceParser.getName();
						}
						if (strNode.equalsIgnoreCase("order")) {
							order = new ModelOrder();
							order.setId(xmlResourceParser.getAttributeValue(null, "id"));
							order.setState(xmlResourceParser.getAttributeValue(null, "state"));
							String strNode1 = "";
							eventType = xmlResourceParser.next();
							do{
								strNode1 = xmlResourceParser.getName();
								while (strNode1 == null) {
									eventType = xmlResourceParser.next();
									strNode1 = xmlResourceParser.getName();
								}
								if (eventType == XmlResourceParser.START_TAG) {
									if(xmlResourceParser.next() == XmlPullParser.TEXT)
									if(strNode1.equalsIgnoreCase("name")){
										order.setName(xmlResourceParser.getText());
									}else if(strNode1.equalsIgnoreCase("phone")){
										order.setPhone(xmlResourceParser.getText());
									}else if(strNode1.equalsIgnoreCase("email")){
										order.setEmail(xmlResourceParser.getText());
									}else if(strNode1.equalsIgnoreCase("date")){
										order.setDate(xmlResourceParser.getText());
									}else if(strNode1.equalsIgnoreCase("address")){
										order.setAddress(xmlResourceParser.getText());
									}else if(strNode1.equalsIgnoreCase("index")){
										order.setIndex(xmlResourceParser.getText());
									}else if(strNode1.equalsIgnoreCase("deliveryType")){
										order.setDeliveryType(xmlResourceParser.getText());
									}else if(strNode1.equalsIgnoreCase("priceUAH")){
										order.setPrice(xmlResourceParser.getText());
									}
									else if(strNode1.equalsIgnoreCase("item")){
										item = new ModelItem();
										item.setId(xmlResourceParser.getAttributeValue(null, "id"));
										String strNode2 = "";
										eventType = xmlResourceParser.next();
										do{
											strNode2 = xmlResourceParser.getName();
											while (strNode2 == null) {
												eventType = xmlResourceParser.next();
												strNode2 = xmlResourceParser.getName();
											}
											if (eventType == XmlResourceParser.START_TAG) {
												if(xmlResourceParser.next() == XmlPullParser.TEXT)
												if(strNode2.equalsIgnoreCase("name")){
													item.setName(xmlResourceParser.getText());
												}else if(strNode2.equalsIgnoreCase("quantity")){
													item.setQuantity(xmlResourceParser.getText());
												}else if(strNode2.equalsIgnoreCase("price")){
													item.setPrice(xmlResourceParser.getText());
												}else if(strNode2.equalsIgnoreCase("image")){
													item.setPrice(xmlResourceParser.getText());
												}else if(strNode2.equalsIgnoreCase("url")){
													item.setPrice(xmlResourceParser.getText());
												}
											}
											eventType = xmlResourceParser.next();
										} while (!strNode2.equalsIgnoreCase("item"));
										order.addItem(item);
									}
								}
								eventType = xmlResourceParser.next();
							} while (!strNode1.equalsIgnoreCase("order"));
							list.add(order);
							
						}
					}

					eventType = xmlResourceParser.next();
				} catch (XmlPullParserException xmlPullExp) {
					xmlPullExp.printStackTrace();
					return null;
				} catch (IOException ioExp) {
					ioExp.printStackTrace();
					return null;
				} catch (Exception exp) {
					exp.printStackTrace();
					return null;
				}
			}
		} catch (Exception e) {
			return null;
		}
		
		return list;
	}
}
