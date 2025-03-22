package kr.co.cyberline.cmm.web.util;

import kr.co.cyberline.cmm.util.lang.CylStringUtil;
import kr.co.cyberline.cmm.web.annotation.CylKeepCondition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URLEncoder;

public class CylBeanUtils extends BeanUtils {
	private final static Logger logger = LoggerFactory.getLogger(CylBeanUtils.class);

	public CylBeanUtils() {
		super();
	}

	/**
	 * <pre>
	 * 1. 개요 : ValueObject의 String attribute의 전체 trim
	 * 2. 처리내용 : java.lang.String Type의 attribute trim 처리 (get, set method 존재필수)
	 * </pre>
	 * 
	 * @Author : chseok82
	 * @Date : 2012. 8. 11.
	 * @Method Name : getAttributeAllTrim
	 * @param obj
	 * @return java.lang.String Type의 전체 attribute trim수행 후 Object 반환
	 */
	public static Object getAttributeAllTrim(Object obj) {
		Object resultObj = null;

		try {
			resultObj = obj;
			Field fields[] = resultObj.getClass().getDeclaredFields();

			for (Field field : fields) {
				String fieldName = field.getName();
				String fieldType = field.getType().getName();
				String getMethodName = getMethodName(fieldName, "get");
				String setMethodName = getMethodName(fieldName, "set");

				if (fieldType.equals("java.lang.String")) {
					Method setMethod = resultObj.getClass().getMethod(setMethodName, new Class[] { String.class });
					Method getMethod = resultObj.getClass().getMethod(getMethodName);

					String str = (String) getMethod.invoke(resultObj);
					if (str == null) {
						setMethod.invoke(resultObj, new Object[] { null });
					} else {
						setMethod.invoke(resultObj, new Object[] { str.trim() });
					}

				}
			}
		} catch (Exception ex) {
			logger.error(ex.toString());
		}

		return resultObj;
	}

	/**
	 * <pre>
	 * 1. 개요 : ValueObject의 String attribute의 값이 존재하지 않을경우 null으로 값 변경
	 * 2. 처리내용 : java.lang.String Type의 attribute 값이 존재하지 않을경우 null 값으로 변경 처리 (get, set method 존재필수) " "는 null 변환하지 않음.
	 * </pre>
	 * 
	 * @Author : chseok82
	 * @Date : 2012. 8. 11.
	 * @Method Name : getAttributeAllTrim
	 * @param obj
	 * @return java.lang.String Type의 전체 attribute empty("")값을 null으로 변경 후
	 *         Object 반환
	 */
	public static Object getAttributeEmptyToNull(Object obj) {
		Object resultObj = null;

		try {
			resultObj = obj;
			Field fields[] = resultObj.getClass().getDeclaredFields();

			for (Field field : fields) {
				String fieldName = field.getName();
				String fieldType = field.getType().getName();
				String getMethodName = getMethodName(fieldName, "get");
				String setMethodName = getMethodName(fieldName, "set");

				if (fieldType.equals("java.lang.String")) {
					Method setMethod = resultObj.getClass().getMethod(setMethodName, new Class[] { String.class });
					Method getMethod = resultObj.getClass().getMethod(getMethodName);

					String str = (String) getMethod.invoke(resultObj);
					if (str == null || str.equals("")) {
						setMethod.invoke(resultObj, new Object[] { null });
					} else {
						setMethod.invoke(resultObj, new Object[] { str });
					}

				}
			}
		} catch (Exception ex) {
			logger.error(ex.toString());
		}

		return resultObj;
	}

	/**
	 * <pre>
	 * 1. 개요 : ValueObject의 String attribute의 값이 null일 경우 empty("") 문자열로 변환
	 * 2. 처리내용 : java.lang.String Type의 attribute null일 경우 empty("") 값으로 변경 처리 (get, set method 존재필수)
	 * </pre>
	 * 
	 * @Author : chseok82
	 * @Date : 2012. 8. 11.
	 * @Method Name : getAttributeAllTrim
	 * @param obj
	 * @return java.lang.String Type의 전체 attribute null값을 empty("")으로 변환된 Object
	 *         반환
	 */
	public static Object getAttributeNullToEmpty(Object obj) {
		Object resultObj = null;

		try {
			resultObj = obj;
			Field fields[] = resultObj.getClass().getDeclaredFields();

			for (Field field : fields) {
				String fieldName = field.getName();
				String fieldType = field.getType().getName();
				String getMethodName = getMethodName(fieldName, "get");
				String setMethodName = getMethodName(fieldName, "set");

				if (fieldType.equals("java.lang.String")) {
					Method setMethod = resultObj.getClass().getMethod(setMethodName, new Class[] { String.class });
					Method getMethod = resultObj.getClass().getMethod(getMethodName);

					String str = (String) getMethod.invoke(resultObj);
					if (str == null) {
						setMethod.invoke(resultObj, new Object[] { "" });
					} else {
						setMethod.invoke(resultObj, new Object[] { str });
					}

				}
			}
		} catch (Exception ex) {
			logger.error(ex.toString());
		}

		return resultObj;
	}

	/**
	 * <pre>
	 * 1. 개요 : 조회조건 및 페이지 정보를 유지하기 위해 관련 속성을 복사한다.
	 * 2. 처리내용 : KeepCondition annotation 속성 및 페이지 정보 복사
	 * </pre>
	 * 
	 * @Author : chseok82
	 * @Date : 2012. 8. 15.
	 * @Method Name : copyCondProperties
	 * @param source
	 * @param target
	 * 
	 */
	public static void copyCondProperties(Object source, Object target) {
		Field fields[] = source.getClass().getDeclaredFields();
		
		setFieldCondProperties(fields, source, target);
		setSuperClassFieldCondProperties(source.getClass().getSuperclass(), source, target);
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 상위 클래스의 조회조건 및 페이지 정보를 유지하기 위해 관련 속성을 복사한다.
	 * 2. 처리내용 : 재귀적 호출로 최상위 클래스의 속성 및 페이지 정보 복사
	 * </pre>
	 * 
	 * @Author	: chseok82
	 * @Date	: 2012. 10. 15.
	 * @Method Name : setSuperClassFieldCondProperties
	 * @param superClass
	 * @param source
	 * @param target
	 * @return
	 */
	private static boolean setSuperClassFieldCondProperties(Class superClass, Object source, Object target) {
		boolean isLoop = true;
		
		while(isLoop) {
			if (superClass != null) {
				Field fields[] = superClass.getDeclaredFields();
				setFieldCondProperties(fields, source, target);
				isLoop = setSuperClassFieldCondProperties(superClass.getSuperclass(), source, target);
			} else {
				isLoop = false;
			}
		}
		
		return isLoop;
	}

	/**
	 * <pre>
	 * 1. 개요 : 조회조건 및 페이지 정보를 유지하기 위해 관련 속성을 복사한다.
	 * 2. 처리내용 : KeepCondition annotation 속성 및 페이지 정보 복사
	 * 3. 추가내용 : 배열파라미터는 set해주는 부분이 없어서 신규로 추가함 by ms
	 * </pre>
	 * 
	 * @Author	: chseok82
	 * @Date	: 2012. 10. 15.
	 * @Method Name : setFieldCondProperties
	 * @param fields
	 * @param source
	 * @param target
	 */
	private static void setFieldCondProperties(Field[] fields, Object source, Object target) {
		
		try {
			for (Field field : fields) {
				String fieldName = field.getName();
				String fieldType = field.getType().getName();
				String getMethodName = getMethodName(fieldName, "get");
				String setMethodName = getMethodName(fieldName, "set");

				if (isKeepConditionField(field) && isExistMethod(target.getClass(), setMethodName)) {
					Method getMethod = source.getClass().getMethod(getMethodName);
					Method setMethod = null;
					
					if (fieldType.equals("java.lang.String")) {
						setMethod = target.getClass().getMethod(setMethodName, new Class[] { String.class });
						String value = (String) getMethod.invoke(source);
						setMethod.invoke(target, new Object[] { value });
					} else if (fieldType.equals("boolean")) {
						setMethod = target.getClass().getMethod(setMethodName, new Class[] { boolean.class });
						boolean value = (Boolean) getMethod.invoke(source);
						setMethod.invoke(target, new Object[] { value });
					} else if (fieldType.equals("int")) {
						setMethod = target.getClass().getMethod(setMethodName, new Class[] { int.class });
						int value = (Integer) getMethod.invoke(source);
						setMethod.invoke(target, new Object[] { value });
					} else if (fieldType.equals("[Ljava.lang.String;")) {
						setMethod = target.getClass().getMethod(setMethodName, new Class[] { String[].class });
						Object objValues = getMethod.invoke(source);
						if(objValues != null){
							String[] values = (String[]) objValues;
							setMethod.invoke(target, new Object[] { values });
						}
					}
				}
			}
		} catch (Exception ex) {
			logger.error(ex.toString());
		}
	}

	/**
	 * <pre>
	 * 1. 개요 : 조회조건 및 페이지 정보를 유지하기 위해 관련 속성을 get 방식의 파라메터 전달 문자열로 반환한다.
	 * 2. 처리내용 : KeepCondition annotation  속성 및 페이지 정보 복사
	 * </pre>
	 * 
	 * @Author	: chseok82
	 * @Date	: 2012. 10. 15.
	 * @Method Name : getConditionParameter
	 * @param obj
	 * @return
	 */
	public static String getConditionParameter(Object obj) {
		StringBuffer parametersStr = new StringBuffer();
		String resultStr = "";
		Field fields[] = obj.getClass().getDeclaredFields();
		
		parametersStr.append(getFieldCondParameter(fields, obj));
		
		if (!parametersStr.toString().equals("")) {
			parametersStr.append("&");
		}
		
		getSuperClassFieldCondParameter(obj.getClass().getSuperclass(), obj, parametersStr);
		
		if (parametersStr.toString().endsWith("&")) {
			resultStr = parametersStr.toString().substring(0, parametersStr.toString().length() - 1);
		}
		
		return resultStr.toString();
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 상위 클래스의 조회조건 및 페이지 정보를 유지하기 위해 관련 속성을 get 방식의 파라메터 전달 문자열로 반환한다.
	 * 2. 처리내용 : 재귀적 호출로 최상위 클래스의 속성 및 페이지 정보 복사 get 방식의 파라메터 전달 문자열로 반환한다.
	 * </pre>
	 * 
	 * @Author	: chseok82
	 * @Date	: 2012. 10. 15.
	 * @Method Name : getSuperClassFieldCondParameter
	 * @param superClass
	 * @param obj
	 * @param resultStr
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	private static boolean getSuperClassFieldCondParameter(Class superClass, Object obj, StringBuffer resultStr) {
		boolean isLoop = true;
		String tempCondParameter = "";
		
		while(isLoop) {
			if (superClass != null) {
				Field fields[] = superClass.getDeclaredFields();
				tempCondParameter = getFieldCondParameter(fields, obj);
				
				if (!tempCondParameter.equals("")) {
					tempCondParameter += "&";
				}
				
				resultStr.append(tempCondParameter);
				isLoop = getSuperClassFieldCondParameter(superClass.getSuperclass(), obj, resultStr);
			} else {
				isLoop = false;
			}
		}
		
		return isLoop;
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 조회조건 및 페이지 정보를 유지하기 위해 관련 속성을 get 방식의 파라메터 전달 문자열로 반환한다.
	 * 2. 처리내용 : KeepCondition annotation  속성 및 페이지 정보 복사
	 * 3. 추가내용 : 배열파라미터는 set해주는 부분이 없어서 신규로 추가함 by ms
	 * </pre>
	 * 
	 * @Author	: chseok82
	 * @Date	: 2012. 10. 15.
	 * @Method Name : getFieldCondParameter
	 * @param fields
	 * @param obj
	 * @return
	 */
	private static String getFieldCondParameter(Field fields[], Object obj) {
		StringBuilder resultStr = new StringBuilder();
		boolean isCondProperty = false;
		
		try {
			for (Field field : fields) {
				String fieldName = field.getName();
				String fieldType = field.getType().getName();
				String getMethodName = getMethodName(fieldName, "get");
				
				if (isKeepConditionField(field)) {
					isCondProperty = true;
				} else {
					isCondProperty = false;
				}
				
				if (isCondProperty) {
					
					if (!fieldType.equals("[Ljava.lang.String;")) {
						if (!resultStr.toString().equals("")) {
							resultStr.append("&");
						}
						resultStr.append(fieldName).append("=");
					}
					
					Method getMethod = obj.getClass().getMethod(getMethodName);

					if (getMethod != null) {
						if (fieldType.equals("java.lang.String")) {
							String value = (String) getMethod.invoke(obj);
							resultStr.append(URLEncoder.encode(CylStringUtil.nvl(value), "UTF-8"));
						} else if (fieldType.equals("boolean")) {
							boolean value = (Boolean) getMethod.invoke(obj);
							resultStr.append(value);
						} else if (fieldType.equals("int")) {
							int value = (Integer) getMethod.invoke(obj);
							resultStr.append(value);
						} else if (fieldType.equals("[Ljava.lang.String;")) {
							
							Object objValues = getMethod.invoke(obj);
							
							if(objValues != null){
								
								System.out.println();
								System.out.println(objValues.toString());
								String[] values = (String[]) objValues;
								
								if(values.length > 0){
									
									for(int i=0; i < values.length; i++){
										if (!resultStr.toString().equals("")) {
											resultStr.append("&");
										}
										resultStr.append(fieldName).append("=");
										resultStr.append(URLEncoder.encode(CylStringUtil.nvl(values[i]), "UTF-8"));
									}
									
								}
							}
						}
					}
				}
			}
		} catch (Exception ex) {
			logger.error(ex.toString());
		}		
		
		return resultStr.toString();
	}
	
	/**
	 * <pre>
	 * 조회조건 및 페이지 정보를 유지시키기 위한 변수인지 여부
	 * </pre>
	 * 
	 * @Author	: jong-jin, park
	 * @Date	: 2013. 02. 01.
	 * @param f
	 * @return
	 */
	private static boolean isKeepConditionField(Field f){
		String fieldName = f.getName();
		return f.getAnnotation(CylKeepCondition.class)!=null ||
			   fieldName.equals("pageIndex") || 
			   fieldName.equals("pageUnit") || 
			   fieldName.equals("pageSize")|| 
			   fieldName.equals("firstIndex") || 
			   fieldName.equals("lastIndex") || 
			   fieldName.equals("recordCountPerPage");
	}

	private static String getMethodName(String fieldName, String type) {
		String resultStr = "";
		String upperFieldName = fieldName.toUpperCase();

		resultStr = new StringBuilder().append(type).append(upperFieldName.substring(0, 1)).append(fieldName.substring(1)).toString();

		return resultStr;
	}
	
	private static boolean isExistMethod(Class cls, String methodName) {
		boolean resultFlag = false;
		
		for (Method method : cls.getMethods()) {
			if (method.getName().equals(methodName)) {
				resultFlag = true;
				break;
			}
		}
		
		return resultFlag;
	}
}

