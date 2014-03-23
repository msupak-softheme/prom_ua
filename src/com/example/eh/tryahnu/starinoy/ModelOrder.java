package com.example.eh.tryahnu.starinoy;

import java.util.ArrayList;
import java.util.List;

public class ModelOrder {
	
	private String id;
	private String state;
	private String name;
	private String company;
	private String phone;
	private String email;
	private String address;
	private String index;
	private String paymentType;
	private String deliveryType;
	private String deliverycost;
	private String payercomment;
	private String salescomment;
	private String price;
	private String date;
	private List<ModelItem> itemList;
	
	public ModelOrder(){
		itemList = new ArrayList<ModelItem>();
	}
	
	public void addItem(ModelItem item){
		itemList.add(item);
	}
	
	public List<ModelItem> getItemList(){
		return itemList;
	}
	
	public void setItemList(List<ModelItem> itemList){
		this.itemList = itemList;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	public void setState(String state) {
		this.state = state;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public void setIndex(String index) {
		this.index = index;
	}
	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}
	public void setDeliveryType(String deliveryType) {
		this.deliveryType = deliveryType;
	}
	public void setDeliverycost(String deliverycost) {
		this.deliverycost = deliverycost;
	}
	public void setPayercomment(String payercomment) {
		this.payercomment = payercomment;
	}
	public void setSalescomment(String salescomment) {
		this.salescomment = salescomment;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getId() {
		return id;
	}
	public String getState() {
		return state;
	}
	public String getName() {
		return name;
	}
	public String getCompany() {
		return company;
	}
	public String getPhone() {
		return phone;
	}
	public String getEmail() {
		return email;
	}
	public String getAddress() {
		return address;
	}
	public String getIndex() {
		return index;
	}
	public String getPaymentType() {
		return paymentType;
	}
	public String getDeliveryType() {
		return deliveryType;
	}
	public String getDeliverycost() {
		return deliverycost;
	}
	public String getPayercomment() {
		return payercomment;
	}
	public String getSalescomment() {
		return salescomment;
	}
	public String getPrice() {
		return price;
	}

	public void setDate(String date) {
		this.date = date;
	}
	
	public String getDate(){
		return date;
	}
	
}
