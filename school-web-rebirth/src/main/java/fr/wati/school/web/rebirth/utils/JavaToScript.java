package fr.wati.school.web.rebirth.utils;

import java.io.PrintStream;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

/**
 * User: yfuksenk Date: Mar 4, 2004 Time: 3:35:14 PM
 */
public class JavaToScript {

	public static void main(String[] args) {
		AjaxAutoLoadElement[] autoLoadElements=new AjaxAutoLoadElement[]{new AjaxAutoLoadElement("topbar", "/login")};
		//System.out.println(getJavaScriptObject("elementsArray", autoLoadElements));
		System.out.println(getFirstPartJavaScriptForAjaxAutoLoadElements(autoLoadElements));
		
	}

	public static String getDivPartJavaScriptForAjaxAutoLoadElements(AjaxAutoLoadElement ajaxAutoLoadElement){
		StringBuffer stringBuffer=new StringBuffer();
		StringBuffer preffix=new StringBuffer();
		preffix.append("<div id=\""+ajaxAutoLoadElement.divJQuerySelector+"\"></div>");
		preffix.append("\n");
		stringBuffer.append(preffix.toString());
		stringBuffer.append("\n");
		return stringBuffer.toString(); 
	}
	
	public static String getFirstPartJavaScriptForAjaxAutoLoadElements(AjaxAutoLoadElement[] ajaxAutoLoadElements){
		StringBuffer stringBuffer=new StringBuffer();
		
		
		StringBuffer preffix=new StringBuffer();
		for (int i = 0; i < ajaxAutoLoadElements.length; i++) {
			preffix.append("<div id=\""+ajaxAutoLoadElements[i].divJQuerySelector+"\"></div>");
			preffix.append("\n");
		}
		stringBuffer.append(preffix.toString());
		stringBuffer.append("\n");
		stringBuffer.append("<script>");
		stringBuffer.append(getJavaScriptObject("elementsArray", ajaxAutoLoadElements));
	
		return stringBuffer.toString();
	}
	
	public static String getSecondPartJavaScriptForAjaxAutoLoadElements(AjaxAutoLoadElement[] ajaxAutoLoadElements){
		StringBuffer stringBuffer=new StringBuffer();
		stringBuffer.append("\n");
		stringBuffer.append("<script>");
		stringBuffer.append(getJavaScriptObject("elementsArray", ajaxAutoLoadElements));
	
		return stringBuffer.toString();
	}
	
	public static String getCommonPartJavaScriptForAjaxAutoLoadElements(){
		StringBuffer suffix=new StringBuffer()
		.append("window.onload= function(){")
		.append("for(var i =0;  i<elementsArray.length; i++){")
		.append("doAjaxPost(elementsArray[i]['divJQuerySelector'], elementsArray[i]['url']);")
		.append(" } };");
		suffix.append(suffix.toString());
		suffix.append("</script>");
		return suffix.toString();
	}
	public static final String getJavaScriptObject(String jsName, Object obj) {
		if (obj == null)
			return jsName + " = null;\n";
		String result = null;
		String declClass = null;
		Boolean definesToString = new Boolean(false);
		try {
			Method method = obj.getClass().getDeclaredMethod("toString", null);
			declClass = method.getDeclaringClass().getName();
			// System.out.println("Found toString " + aClass.getName());
			definesToString = new Boolean(method != null
					&& Modifier.isPublic(method.getModifiers()));
		} catch (NoSuchMethodException e) {
		}
		if (obj instanceof Collection) {
			Collection cl = (Collection) obj;
			StringBuffer sb = new StringBuffer("\n" + jsName
					+ " = new Array();");
			int j = 0;
			for (Iterator iterator = cl.iterator(); iterator.hasNext();) {
				Object o = (Object) iterator.next();
				sb.append(getJavaScriptObject("\n" + jsName + "[" + j + "]", o));
				j++;
			}
			result = sb.toString();
		} else if (obj.getClass().isArray()) {
			StringBuffer sb = new StringBuffer("\n" + jsName
					+ " = new Array();");
			int length = Array.getLength(obj);
			for (int i = 0; i < length; i++) {
				sb.append(getJavaScriptObject("\n" + jsName + "[" + i + "]",
						Array.get(obj, i)));
			}
			result = sb.toString();
		} else if (obj.getClass().getName().equals("java.util.Date")) {
			result = "\n" + jsName + "= new Date(" + ((Date) obj).getTime()
					+ ");\n";
		} else if (obj instanceof Number) {
			result = "\n" + jsName + "= " + ((Number) obj).doubleValue()
					+ ";\n";
		} else if (obj instanceof Boolean) {
			result = "\n" + jsName + "= " + ((Boolean) obj).booleanValue()
					+ ";\n";
		} else if (definesToString.booleanValue()) {
			result = "\n"
					+ jsName
					+ "='"
					+ obj.toString().replaceAll("\'", "\\\'")
							.replaceAll("\n", "\\n") + "';\n";
		} else {
			StringBuffer sb = getJsObject(jsName, obj);
			result = sb.toString();
		}
		return result;
	}

	private static StringBuffer getJsObject(String jsName, Object obj) {
		Class aClass = obj.getClass();
		StringBuffer sb = new StringBuffer("\n" + jsName + " = new Array();");
		Field[] fields = aClass.getFields();
		for (int f = 0; f < fields.length; f++) {
			Field field = fields[f];
			if (!Modifier.isPublic(field.getModifiers())
					|| field.getDeclaringClass().getName()
							.equals("java.lang.Object"))
				continue;
			try {
				String jScript = getJavaScriptObject(
						jsName + "['" + field.getName() + "']", field.get(obj));
				sb.append(jScript);
			} catch (IllegalAccessException e) {
			}
		}
		Method[] methods = aClass.getMethods();
		for (int m = 0; m < methods.length; m++) {
			Method method = methods[m];
			if (!Modifier.isPublic(method.getModifiers())
					|| method.getReturnType().equals("void")
					|| method.getParameterTypes().length > 0
					|| method.getDeclaringClass().getName()
							.equals("java.lang.Object"))
				continue;
			String jScript = null;
			try {
				Object methodCallResult = method.invoke(obj, null);
				jScript = getJavaScriptObject(jsName + "['" + method.getName()
						+ "']", methodCallResult);
				sb.append(jScript);
			} catch (IllegalAccessException e) {
			} catch (InvocationTargetException e) {
			}
		}
		return sb;
	}
}
