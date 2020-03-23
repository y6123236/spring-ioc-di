package com.hm.spring;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

//作业：课堂代码自己动手  总结

/**
 * 1、自定义工厂（升级）
 * 2、Spring 工厂
 * @author Administrator
 *
 */
public class StringClient {

	public static void main(String[] args) {

		// 工厂
		ApplicationContext ac = new ClassPathXmlApplicationContext("application.xml");

		StringProcess sp = (StringProcess) ac.getBean("stringProcess");

		System.out.println(sp.processString());
	}
}
