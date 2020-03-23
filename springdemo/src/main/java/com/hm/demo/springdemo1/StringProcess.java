package com.hm.demo.springdemo1;

/**
 * 功能：字符串进行特定的处理 自己并不提供字符串（材料）
 * 
 * IOAPI读取==》 文件==》字符串
 * 
 * @author Administrator
 * 
 *         内部代码结构： OOP（）：设计原则【六】 开闭原则 【开：面向功能扩展 闭：不要修改代码（（核心代码）】
 *1、单一  
 */
public class StringProcess {

	//
	private IGetString getString;

	public StringProcess(IGetString getString) {
       //依赖的对象的创建由内部转移到外部[IOC:控制反转]
	   this.getString=getString;
	} 

	public String processString() {
		// 处理逻辑
		return "处理" + this.getString.getString();
	}
}
