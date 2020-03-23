package com.hm.demo.springdemo1;

public class DBFromString implements IGetString {

	/* (non-Javadoc)
	 * @see com.hm.demo.springdemo1.IGetString#getString()
	 */
	@Override
	public String getString(){
		return "从数据库中得到的字符串";
	}
}
