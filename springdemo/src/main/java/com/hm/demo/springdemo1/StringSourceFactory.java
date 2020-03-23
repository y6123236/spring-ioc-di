package com.hm.demo.springdemo1;

public class StringSourceFactory {

	public static IGetString create(int no){
		IGetString getString=null;
		 switch (no) {
			case 1:
				getString=new DBFromString();
				break;
			case 2:
				getString=new XmlFromString();
				break;
			case 3:
				getString=new FileFromString();
				break;
			case 4:
				getString=new NetFromString();
				break;
			default:
				break;
			}
		
		return getString;
	}
	
}
