package com.hm.demo.springdemo2;

import java.util.Scanner;

//作业：课堂代码自己动手  总结

public class StringClient {

	public static void main(String[] args) {
			   
		//IOC工厂 依赖注入工厂【】
		StringSourceFactory stringSourceFactory=new StringSourceFactory();
		//目标对象
		StringProcess stringProcess=(StringProcess)stringSourceFactory.getBean("stringProcess");	
		
		String proStr= stringProcess.processString();
		
		System.out.println(proStr);
	}
}
