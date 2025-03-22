package kr.co.cyberline.cmm.web.util;

import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;
import kr.co.cyberline.cmm.util.lang.CylStringUtil;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.map.ListOrderedMap;
import org.apache.commons.lang3.ClassUtils;
import org.springframework.aop.support.AopUtils;
import org.springframework.util.ReflectionUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 공통 리플렉션 유틸
 *
 * @author 공통컴포넌트 개발 송치석
 * @since 2015.04.30
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일       수정자           수정내용
 *  -------       --------    ---------------------------
 *   2015. 4. 30  송치석          최초 생성 (ClassUtils / AopUtils / ReflectionUtil  이런데 찾아보고 없을때 추가할것!)
 * </pre>
 */
public abstract class CylReflectionUtil extends ReflectionUtils {

    /**
     * 프록시 체크
     * @param object
     * @return
     */
    public static boolean isCglibProxy(Object object) {

        return AopUtils.isCglibProxy(object);
    }

    /**
     * generic class에 어떤 클래스가 매핑되었는지 리턴한다. ex) BookDao extends
     * GenericHibernateDao<Board,Integer> ==> genericClass(BookDao.class,1) : Integer 가 리턴됨.
     * 익명 객체는 안되고, 컴파일 시점에서 제너릭이 결정된 클래스만 해당된다.
     * 제너릭한 수퍼타입이 나올때 까지 올라간다.
     * @param clazz
     * @param index
     * @param <T>
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> Class<T> genericClass(Class<?> clazz, int index) {
        Type type = clazz.getGenericSuperclass();
        if(type instanceof ParameterizedType){
            ParameterizedType genericSuperclass = (ParameterizedType) type;
            return  (Class<T>) genericSuperclass.getActualTypeArguments()[index];
        }
        Class<?> superClass = clazz.getSuperclass();
        if(superClass==null) throw new IllegalArgumentException("ParameterizedType이 존재하지 않습니다.");
        return genericClass(superClass, index);
    }

    @SuppressWarnings("unchecked")
    public static <T> Class<T> genericClass(Field field){
        ParameterizedType pt = (ParameterizedType)field.getGenericType();
        return (Class<T>)pt.getActualTypeArguments()[0];
    }

    /**
     * Setter Method의 Collection제너릭 타입 이름을 추출한다. Class에서 제너릭이 붙은 method는 오직
     * ex) public void setBooks(List<Book> books) {  => Book.class가 리턴
     * @param method
     * @param <T>
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> Class<T> genericClass(Method method){
        ParameterizedType pt = (ParameterizedType)method.getGenericParameterTypes()[0];
        return (Class<T>)pt.getActualTypeArguments()[0];
    }


    /**
     * name에 해당하는 Enum의 인스턴스를 리턴한다. 커스텀 타입은 사용불가.
     * @param clazz
     * @param name
     * @param <T>
     * @return
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public static <T extends Enum> T getEnumInstance(Class<T> clazz, String name) {
        if (!clazz.isEnum()) throw new IllegalArgumentException(clazz + " is not Enum");
        return (T) Enum.valueOf((Class<Enum>) clazz, name);
    }

    public static <T extends Enum<?>> T[] getEnums(Class<T> clazz) {
        return clazz.getEnumConstants();
    }

    /**
     * 예외 래핑. ClassUtils.getClass(className); 와 동일
     * @param fullName
     * @param <T>
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> Class<T> forName(String fullName) {
        Class<T> en = null;
        try {
            en = (Class<T>) Class.forName(fullName);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return en;
    }

    /**
     * 예외 래핑
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T newInstance(Class<T> clazz) {
        try {
            return clazz.newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 모든 상속구조의 필드를 다 조사하며, static인것은 제외한다.
     * 확실하지 않지만, 천만건 이상 돌리면, 매번 호출하는거보나 약간 빠르다. 천전 이하에서는 매번 호출이 더 빠름.
     * @param clazz
     * @return
     */
    @SuppressWarnings("unchecked")
    public static List<Field> getAllDeclaredFields(Class<?> clazz){
        List<Field> fields = new ArrayList<Field>();
        List<Class<?>> classes = ClassUtils.getAllSuperclasses(clazz);
        CollectionUtils.addAll(fields, clazz.getDeclaredFields());
        for(Class<?> each : classes){
            if(each== Object.class) continue;
            CollectionUtils.addAll(fields, each.getDeclaredFields());
        }
        Iterator<Field> i = fields.iterator();
        while(i.hasNext()){
            Field field = i.next();
            if(Modifier.isStatic(field.getModifiers())) i.remove();
            else field.setAccessible(true);
        }
        return fields;
    }

    /**
     * 임시 캐싱한다. 히트 오류 무시한다.
     * 캐시 사용전 700ms -> 사용후 27ms.
     * jvm 6 기준 리플렉션 안쓰면 4ms
     */
    private static final Map<Class<?>, Map<String, Field>> REFLECTION_CACHE_MAP = new ConcurrentHashMap<Class<?>, Map<String, Field>>();

    /**
     * access를 true로 설정 후 Map으로 리턴한다.
     * @param clazz
     * @return
     */
    public static Map<String, Field> getAllDeclaredFieldMap(Class<?> clazz){
        Map<String, Field> fieldMap = REFLECTION_CACHE_MAP.get(clazz);
        if(fieldMap==null){
            fieldMap = Maps.newHashMap();
            List<Field> fields = getAllDeclaredFields(clazz);
            for(Field each : fields){
                each.setAccessible(true);
                fieldMap.put(each.getName(), each);
            }
            REFLECTION_CACHE_MAP.put(clazz, fieldMap);
        }
        return fieldMap;
    }

    /**
     * access를 true로 설정 후 Map으로 리턴한다.
     * 카멜케이스를 DB에서 사용하는 언더스코어로 변경해서 리턴한다.
     * @param clazz
     * @return
     */
    public static Map<String, Field> getAllDeclaredFieldUnderscoreMap(Class<?> clazz){
        Map<String, Field> fieldMap = Maps.newHashMap();
        List<Field> fields = getAllDeclaredFields(clazz);
        for(Field each : fields){
            each.setAccessible(true);
            fieldMap.put(CylStringUtil.getUnderscore(each.getName()), each);
        }
        return fieldMap;
    }

    /**
     * 간단메소드. 특이한 케이스에만 사용하자
     * @param instance
     * @param name
     * @param <T>
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> T findFieldValue(Object instance, String name) {
        Field field = ReflectionUtils.findField(instance.getClass(), name);
        field.setAccessible(true);
        try {
            return (T) field.get(instance);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 간단메소드. 특이한 케이스에만 사용하자
     * 해당하는 이름의 필드명이 있다면 값을 입력한다. 아니면 무시한다.
     * @param instance
     * @param fieldName
     * @param value
     * @return
     */
    public static boolean setField(Object instance, String fieldName, Object value){
        Field field = ReflectionUtils.findField(instance.getClass(), fieldName);
        if(field==null) return false;
        field.setAccessible(true);
        setField(field, instance, value);
        return true;
    }


    /**
     * findField 에 없는거 추가.
     * 메소드 이름과 args의 수 만으로 메소드를 찾아낸다. 첫번째 찾으면 리턴
     * @param clazz
     * @param name
     * @param argsSize
     * @return
     */
    public static Method findField(Class<?> clazz, String name, int argsSize) {
        Method[] methods = clazz.getMethods();
        for (Method method : methods)
            if (method.getName().equals(name) && method.getParameterTypes().length == argsSize) return method;
        return null;
    }

    /**
     * findMethod 에 없는거 추가
     * 해당 어노테이션이 매핑된 모든 메소드를 가져온다
     * @param clazz
     * @param anno
     * @return
     */
    public static List<Method> findMethod(Class<?> clazz, Class<? extends Annotation> anno) {
        List<Method> result = new ArrayList<Method>();
        Method[] methods = clazz.getMethods();
        for (Method method : methods) if(method.isAnnotationPresent(anno)) result.add(method);
        return result;
    }

    /**
     * private으로 접근 불가능한 필드를 사용
     */
    public static class Fields{
        public final Field field;
        /** 정확한 대상 clazz를 명시해야 한다. */
        public Fields(Class<?> clazz, String fieldName){
            try {
                field = clazz.getDeclaredField(fieldName);
            } catch (NoSuchFieldException e) {
                throw new RuntimeException(e);
            }
            field.setAccessible(true);
        }
        public Object get(Object arg0){
            try {
                return field.get(arg0);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * 해당 메소드에 어노테이션이 달려있는지
     * @param bodys
     * @param clazzs
     * @return
     */
    public static boolean isAnnotationPresent(Method bodys, Class<? extends Annotation>... clazzs) {
        for (Class<? extends Annotation> each : clazzs)
            if (bodys.isAnnotationPresent(each)) return true;
        return false;
    }

    /**
     * 해당 클래스가 item들을 하나라도 구현했는지
     * body 가 최상위 타입이여야 한다. body로 캐스팅 가능하면 true
     * @param body
     * @param items
     * @return
     */
    public static boolean isAssignableFrom(Class<?> body, Class<?>... items) {
        if (body == null || items.length == 0) return false;
        for (Class<?> item : items)
            if (item.isAssignableFrom(body)) return true;
        return false;
    }


    /**
     * null포함, 단순히 이름 매칭으로 데이터의 래퍼런스만을 모두 복사한다.
     * 간단한 이력에만 사용
     * @param server
     * @param newObject
     */
    public static void shallowCopyAllByName(Object server, Object newObject){
        List<Field> list = getAllDeclaredFields(server.getClass());
        for(Field field : list){
            Field target =  findField(newObject.getClass(), field.getName());
            if(target==null) continue;
            Object value = getField(field, server);
            target.setAccessible(true);
            setField(target,newObject , value);
        }
    }

    /**
     * 복사
     * @param server
     * @param newClass
     * @param <T>
     * @return
     */
    public static <T> T shallowCopyAllByName(Object server, Class<T> newClass){
        T obj = newInstance(newClass);
        shallowCopyAllByName(server,obj);
        return obj;
    }

    /**
     * A값이 null이고 B값이 null이 아니라면 B의값을 A로 복사한다.
     * @param a
     * @param b
     * @param <T>
     */
    public static <T> void  shallowCopyNull(T a,T b){
        @SuppressWarnings("unchecked")
        Class<T> clazz =  (Class<T>) b.getClass(); //A에는 있으나 B에는 없는 필드는 복사하지 않는다.  ex) A extends B
        Field[] fields = clazz.getDeclaredFields();
        for(Field field : fields){
            int mod = field.getModifiers();
            if(Modifier.isStatic(mod)) continue;
            if(Modifier.isFinal(mod)) continue;
            field.setAccessible(true);
            try {
                Object valueOfB = field.get(b);
                if(valueOfB==null) continue;
                Object valueOfA = field.get(a);
                if(valueOfA!=null) continue;
                field.set(a, valueOfB);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * 복사. 널체크 안하고 B를 A로 복사한다.
     * @param a
     * @param b
     * @param <T>
     */
    public static <T> void  shallowCopy(T a,T b){
        @SuppressWarnings("unchecked")
        Class<T> clazz =  (Class<T>) b.getClass(); //A에는 있으나 B에는 없는 필드는 복사하지 않는다.  ex) A extends B
        Field[] fields = clazz.getDeclaredFields();
        for(Field field : fields){
            int mod = field.getModifiers();
            if(Modifier.isStatic(mod)) continue;
            if(Modifier.isFinal(mod)) continue;
            field.setAccessible(true);
            try {
                Object valueOfB = field.get(b);
                field.set(a, valueOfB);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * 지정된 B의 필드값만 A에 복사해준다.
     * 입력값을 서버값에 대입해줄때 주로 사용한다.
     *
     * @param exist
     * @param param
     * @param inputFields
     * @param <T>
     */
    public static <T> void  shallowCopyInput(T exist, T param, String... inputFields){
        if(inputFields.length==0) return;
        Map<String, Field> fields = getAllDeclaredFieldMap(param.getClass());
        for(String inputField : inputFields){
            Field field = fields.get(inputField);
            Object valueOfB = getField(field, param); //null일 경우도 대입해준다.
            setField(field, exist, valueOfB);
        }
    }

    /**
     * null값에 0을 넣어준다.
     * @param a
     * @param <T>
     */
    public static <T> void  nullValueToZero(T a){
        @SuppressWarnings("unchecked")
        Class<T> clazz =  (Class<T>) a.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for(Field field : fields){
            Class<?> type = field.getType();
            boolean isNumber = Number.class.isAssignableFrom(type);
            if(!isNumber) continue;
            field.setAccessible(true);
            try {
                Object value = field.get(a);
                if(value!=null) continue;
                if(type.isAssignableFrom(Long.class)) field.set(a, 0L);
                else if(type.isAssignableFrom(Integer.class)) field.set(a, 0);
                else if(type.isAssignableFrom(BigDecimal.class)) field.set(a, BigDecimal.ZERO);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * spring의 form태그 등에서 사용한다.
     * (itemLabel 등으로 jsp에 태그 속성을 직접 사용해도 되나, 공통모듈일 경우 통일하는게 더 좋아보인다)
     * 나중에 어노테이션 버전으로 수정해도 좋을듯
     * @param list
     * @param idField
     * @param valueField
     * @param <T>
     * @return
     */
    public static <T> Map<String, String> toMap(Collection<T> list, String idField, String valueField){
        @SuppressWarnings("unchecked")
        Map<String, String> map = new ListOrderedMap();
        if(CollectionUtils.isEmpty(list)) return map;
        Map<String, Field> meta = null;
        for(T each : list){
            if(meta==null) meta = getAllDeclaredFieldMap(each.getClass());
            Object id = getField(meta.get(idField), each);
            Object value = getField(meta.get(idField), each);
            map.put(id.toString(), value.toString());
        }
        return map;
    }

    /**
     * toString이 오버라이드 됬는지 검사.
     * @param vo
     * @return
     */
    public static boolean isOverridedToString(Object vo){
        Method toString = CylReflectionUtil.findMethod(vo.getClass(), "toString");
        return toString.getDeclaringClass() != Object.class;
    }

    /**
     * lombok으로 생성된 자료는 toString()해주고, 아니라면 아파치 형태로 toString() 해준다.
     * @param vo
     * @return
     */
    public static String toStringByLombok(Object vo){
        Preconditions.checkArgument(vo != null, "vo is required");
        if(isOverridedToString(vo)) return vo.toString();
        return CylStringUtil.toStringByReflection(vo);
    }
}
