package com.hm.demo.springdemo;

public class StringClient {

	public static void main(String[] args) {
		
		//实例对象
		StringProcess stringProcess=new StringProcess();
		
		//String proStr= stringProcess.processString();
		
		String proStr= stringProcess.processString_();
		System.out.println(proStr);
	}

}
