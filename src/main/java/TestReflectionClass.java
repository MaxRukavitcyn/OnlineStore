import UtilsDataBase.SomeFuckenClass;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TestReflectionClass<T> {

    public Map<Object, Object> getMapFieldSomeClass(Class clazz, T instance) {
        Map<Object,Object> map = new HashMap<>();
        Field[] fields = clazz.getDeclaredFields();
        Arrays.stream(fields).forEach(field -> {
            field.setAccessible(true);
            try {
                map.put(field, field.get(instance));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        return map;
    }

    public static void main(String[] args) {
        TestClass testClass = new TestClass();
        System.out.println(new TestReflectionClass().getMapFieldSomeClass(TestClass.class, testClass));
        Map<String, String> m = new HashMap<>();

        TestClass inner = new TestClass();
    }
}
