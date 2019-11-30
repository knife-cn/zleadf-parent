package com.zlead.util;

import com.zlead.exception.UtilException;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;


/**
 * 反射工具类
 * @author up72
 *
 */
public class ReflectUtil {
	/**
	 * 获得类下制定注解的方法及注解信息
	 * @param c
	 * @param aType
	 * @return Map<Method,Annotation>
	 * @author wqtan
	 */
	@SuppressWarnings("unchecked")
	public Map<Method,Annotation> findMethodByAnnotation(Class c,Class aType){
		Map<Method,Annotation> result = new HashMap<Method, Annotation>();
		Method[] mets = c.getMethods();
		for(int i=0;i<mets.length;i++){
			Annotation[] as = mets[i].getAnnotations();
			if(null==as || as.length==0){
				continue;
			}
			for(int j=0;j<as.length;j++){
				if(as[j].annotationType().equals(aType)){
					result.put(mets[i], as[j]);
				}
			}
		}
		return result;
	}
	
	/**
	 * 获得类的指定类型的注解
	 * @param c
	 * @param aType
	 * @return Annotation
	 * @author wqtan
	 */
	@SuppressWarnings("unchecked")
	public Annotation getClassAnnotation(Class c,Class aType){
		Class temp = c;
		Annotation result = null;
		while(null==result && temp!=null){
			result = temp.getAnnotation(aType);
			temp = temp.getSuperclass();
		}
		return result;
	}
	
	/**
	 * 获得方法指定类型的注解
	 * @param method
	 * @param aType
	 * @return Annotation
	 * @author wqtan
	 */
	@SuppressWarnings("unchecked")
	public Annotation getMethodAnnotation(Method method,Class aType){
		Annotation result = method.getAnnotation(aType);
		return result;
	}
	
	/**
	 * 获得指定类名的类实例
	 * @param className
	 * @return
	 * @author wqtan
	 */
	@SuppressWarnings("unchecked")
	public Class loadClass(String className){
		Class result = null;
		try {
			result = Class.forName(className);
		} catch (ClassNotFoundException e) {
			throw new UtilException(e);
		}
		return result;
	}
	
	/**
	 * 获得类指定方法名的方法对象
	 * @param c 类对象
	 * @param methodName 方法名
	 * @param declared 是否包含继承
	 * @return Method
	 */
	public Method getMethod(Class c,String methodName,boolean declared){
		Method result = null;
		Method[] mets = null;
		if(declared){
			mets = c.getDeclaredMethods();
		}else{
			mets = c.getMethods();
		}
		if(null!=mets && mets.length>0){
			for(int i=0;i<mets.length;i++){
				if(mets[i].getName().equals(methodName)){
					result = mets[i];
					break;
				}
			}
		}
		return result;
	}
	
	/**
	 * 获得类指定方法名的方法对象，不包含从父级继承的方法
	 * @param c 类对象
	 * @param methodName 方法名
	 * @return Method
	 */
	public Method getMethod(Class c,String methodName){
		return this.getMethod(c, methodName, false);
	}
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
//		ReflectUtil reflectUtil = new ReflectUtil();
		
//		javax.persistence.Table  t = (Table) reflectUtil.getClassAnnotation(OpacNews.class, javax.persistence.Table.class);
//		System.out.println(t.name());
////		Class c = test.User.class;
////		Class a = PermissionAnnotation.class;
//		
//		Method method = reflectUtil.getMethod(c, "test1");
//		System.out.println(method);
//		if(null != method){
//			Annotation ann = reflectUtil.getMethodAnnotation(method, PermissionAnnotation.class);
//			System.out.println(ann);
//		}
		
	}
}

