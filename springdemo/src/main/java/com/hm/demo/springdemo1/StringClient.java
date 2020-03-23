package com.hm.demo.springdemo1;

import java.util.Scanner;

public class StringClient {

	public static void main(String[] args) {
		
		IGetString getString=null;
		
		System.out.println("请选择数据源：1、DB 2、XML 3、FILE 4、NET");
		
		Scanner input=new Scanner(System.in);
	    
		int no=input.nextInt();
	   
	    //依赖对象交由工厂创建
	    getString= StringSourceFactory.create(no);
		
	    //实例对象  DI(依赖注入) 
		StringProcess stringProcess=new StringProcess(getString);	
		
		String proStr= stringProcess.processString();
		
		System.out.println(proStr);
		
		input.close();
	}

}
