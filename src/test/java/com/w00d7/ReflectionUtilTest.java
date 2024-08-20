package com.w00d7;

import java.lang.reflect.InvocationTargetException;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

public class ReflectionUtilTest {

    private TestClass testClassInstance;
    private static final String FIELD_NAME = "privateField";
    private static final String FINAL_FIELD_NAME = "finalFieldTest";
    private static final String STATIC_FIELD_NAME = "staticFieldTest";
    private static final String PRIVATE_METHOD_NAME = "privateMethod";

    @Before
    public void setUp() {
        testClassInstance = new TestClass();
    }

    @Test
    public void testGetField() throws NoSuchFieldException, IllegalAccessException {
        ReflectionUtil.setField(testClassInstance, FIELD_NAME, "TestValue");
        Object fieldValue = ReflectionUtil.getField(testClassInstance, FIELD_NAME);
        assertEquals("TestValue", fieldValue);
    }

    @Test
    public void testSetField() throws NoSuchFieldException, IllegalAccessException {
        ReflectionUtil.setField(testClassInstance, FIELD_NAME, "NewValue");
        String fieldValue = (String) ReflectionUtil.getField(testClassInstance, FIELD_NAME);
        assertEquals("NewValue", fieldValue);
    }

    @Test
    public void testGetStaticField() throws NoSuchFieldException, IllegalAccessException {
        String staticFieldValue = (String) ReflectionUtil.getStaticField(TestClass.class, STATIC_FIELD_NAME);
        assertEquals("Susan", staticFieldValue);
    }

    @Test
    public void testSetStaticField() throws NoSuchFieldException, IllegalAccessException {
        ReflectionUtil.setStaticField(TestClass.class, STATIC_FIELD_NAME, "NewStaticValue");
        String staticFieldValue = (String) ReflectionUtil.getStaticField(TestClass.class, STATIC_FIELD_NAME);
        assertEquals("NewStaticValue", staticFieldValue);
    }

    @Test
    public void testInvokeMethod() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Object result = ReflectionUtil.invokeMethod(testClassInstance, PRIVATE_METHOD_NAME, new Class<?>[]{});
        assertEquals("Hello from privateMethod!", result);
    }

    @Test
    public void testSetFinalField() throws NoSuchFieldException, IllegalAccessException {
        ReflectionUtil.setFinalField(testClassInstance, FINAL_FIELD_NAME, "NewFinalValue");
        String finalFieldValue = (String) ReflectionUtil.getField(testClassInstance, FINAL_FIELD_NAME);
        assertEquals("NewFinalValue", finalFieldValue);
    }

    private static class TestClass {
        private String privateField;
        private Integer privateFieldTwo = 10;
        private int privateFieldPrimitive = 4;
        private final String finalFieldTest = "Bob";
        private static String staticFieldTest = "Susan";

        private String getPrivateField() {
            return privateField;
        }

        private String privateMethod() {
            return "Hello from privateMethod!";
        }
    }
}
