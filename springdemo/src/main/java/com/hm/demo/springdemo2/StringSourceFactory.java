package com.hm.demo.springdemo2;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * 1、解析XML文档 2、bean元素【对应一个对象说明】 根据用户需要（提供bean名称）创建 3、检查对象是否依赖于其它对象 4、再创建 进行装配
 * xml
 * 反射
 * 递归
 * @author Administrator
 *
 */
public class StringSourceFactory {

	// 缓存器[预先XML 解析信息保存]
	private Map<String, Element> beanCaches;

	public StringSourceFactory() {
		this.beanCaches = new HashMap<String, Element>();
		// 解析XML
		try {
			this.parseXml();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 生升工厂生产对象的能力 1、任一种类型对象[告诉我你需要什么样的对象：依赖配置文件] 2、检查创建对象是否会依赖于其它对象
	 * 3、如果有依赖关系，继续创建依赖的对象，并进行装配
	 * 
	 * 反射机制
	 * 
	 * @return
	 */
	public Object getBean(String beanName) {
		if (beanName == null) {
			return null;
		}
		Element beanEle = this.beanCaches.get(beanName);

		// class 创建对象
		Object targetObj = this.getBean(beanEle);

		// 检查是否依赖其它对象 ioc di spring
		Element propertyEle = this.isHasProperty(beanEle);

		if (propertyEle != null) {
			// 存在依赖,

			// 依赖名字
			String diBeanName = propertyEle.attributeValue("ref");
			// 递归
			Object diObject = this.getBean(diBeanName);

			// 属性装配：目标对象的set属性方法
			String setFieldName = propertyEle.attributeValue("name");

			String setMethodName = "set" + setFieldName.substring(0, 1).toUpperCase() + setFieldName.substring(1);

			try {

				Method setMethod = targetObj.getClass().getMethod(setMethodName, diObject.getClass().getInterfaces());
				setMethod.invoke(targetObj, new Object[] { diObject });
				
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		}
		return targetObj;
	}

	/**
	 * 检查 当前bean元素下面是否有property元素
	 * 
	 * @param beanEle
	 * @return
	 */
	private Element isHasProperty(Element beanEle) {
		Element propertyEle = beanEle.element("property");
		return propertyEle;
	}

	private Object getBean(Element beanEle) {
		String className = beanEle.attributeValue("class");

		try {
			Class clz = Class.forName(className);
			Object obj = clz.newInstance();
			return obj;
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

	private void parseXml() throws DocumentException {
		// 解析XML
		SAXReader saxReader = new SAXReader();
		// classes目录找
		URL url = StringSourceFactory.class.getResource("/config.xml");
		// File file=new File("config.xml");
		Document doc = saxReader.read(url);
		// 根元素
		Element rootEle = doc.getRootElement();
		// 寻找到所有Bean元素
		List<Element> beanEles = rootEle.elements("bean");
		String key = null;
		for (Element bean : beanEles) {
			key = bean.attributeValue("id");
			this.beanCaches.put(key, bean);
		}
	}
}
