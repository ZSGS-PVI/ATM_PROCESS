package com.atm;

 class LoadCash  {
	
 private int loadAmount;
 int n;
	public LoadCash(int loadAmount) {
		this.loadAmount=loadAmount;
		
		
	}
	public void load() {
		if(loadAmount <100000) {
			System.out.println("Load valid Amount!!");
		}
			
			if(loadAmount%100000!=0) {
			System.out.println("Load the Amount in exact multiples of 1,00,000");
			
		}
			n=loadAmount/100000;
			
			int denomination1=1000;
			int denomination2=500;
			int denomination3=100;
			
			int quantity1=20;
			int quantity2=100;
			int quantity3=300;
			
			int amount1=denomination1 * quantity1 *n;
			int amount2= denomination2 * quantity2 *n;
			int amount3=denomination3 * quantity3 *n;
			
			int totalAmount=amount1+amount2+amount3;
			
			
			System.out.println(denomination1 + " X " + (quantity1 * n) + " = " + amount1 + " ₹");
	        System.out.println(  denomination2 + " X " + (quantity2 * n) + " = " + amount2 + " ₹");
	        System.out.println(denomination3 + " X " + (quantity3 * n) + " = " + amount3 + " ₹");
	        System.out.println("Total Amount: " + totalAmount + " ₹");
			
			
	}
	
	public int getTotalAmount() {
		 return calculateTotalAmount();
	}
	
	private int calculateTotalAmount() {
		if(loadAmount <100000) {
			System.out.println("Load valid Amount!!");
		}
			
			if(loadAmount%100000!=0) {
			System.out.println("Load the Amount in exact multiples of 1,00,000");
			
		}
			n=loadAmount/100000;
			
			int denomination1=1000;
			int denomination2=500;
			int denomination3=100;
			
			int quantity1=20;
			int quantity2=100;
			int quantity3=300;
			
			int amount1=denomination1 * quantity1 *n;
			int amount2= denomination2 * quantity2 *n;
			int amount3=denomination3 * quantity3 *n;
			
			int totalAmount=amount1+amount2+amount3;
			 return amount1 + amount2 + amount3;
			
	}
	public void setTotalAmount1(int i) {
		// TODO Auto-generated method stub
		
	}
	public int getNum1000() {
		// TODO Auto-generated method stub
		
		return 0;
	}
	public void setNum1000(int i) {
		// TODO Auto-generated method stub
		
	}
	public void setTotalAmount(int i) {
		// TODO Auto-generated method stub
		
	}
	
	

}
