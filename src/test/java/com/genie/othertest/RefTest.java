package com.genie.othertest;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.Test;


public class RefTest {

	@Test
	public void paramIntegerTest() throws InstantiationException, IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException{
		try {
			Class c = Class.forName("java.lang.Integer");
			Class c2 = Class.forName("java.lang.String");
			Class[] parameterTypes = {Class.forName("java.lang.String")};
			Constructor constructor = c.getConstructor(parameterTypes);
			Object[] parameters={"1"};
			Object obj = constructor.newInstance(parameters);
			System.out.println(c.toString());
			System.out.println(obj.toString());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void paramTest() throws ClassNotFoundException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, InstantiationException{
		Class c = Class.forName("com.genie.othertest.RefTestClass");
		
		Class[] paramObjs = {getClass("java.lang.String"),getClass("int"),getClass("java.lang.Integer")};
		
		Method theMethod = c.getDeclaredMethod("testParam", paramObjs);
		
		String param1 = "StringValue";
		String param2 = "2";
		String param3 = "3";
		
		Object[] invokeParamObjs = {param1,Integer.parseInt(param2),Integer.valueOf(param3)};
		
		theMethod.invoke(c.newInstance(), invokeParamObjs);
		
	}

	public Class getClass(String className){
		Class clazz = null;
		if(className.equals("int")){//需要穷举基础数据类型
			clazz=int.class;
		}else{
			try {
				clazz=Class.forName(className);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return clazz;
	}
}
