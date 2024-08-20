//simple reflection wrapper util class, wirtten by w00d7

package com.w00d7;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class ReflectionUtil
{

    /**
     * Returns the value of the field from the given instance/object
     * 
     * @param object the instance from which to retrieve the field
     * @param fieldName the name of the field
     * @return the value of the field
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */

    public static Object getField(Object object, String fieldName) 
        throws NoSuchFieldException, IllegalAccessException {
            Field field = object.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            return field.get(object);
    }

    /**
     * Sets the value of the field of a given instance/object
     * 
     * @param object the object to which the field will be modified
     * @param fieldName the name of the field
     * @param value the value to apply
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */

    public static void setField(Object object, String fieldName, Object value) 
        throws NoSuchFieldException, IllegalAccessException {
            Field field = object.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(object, value);
    }

    /**
     * Invokes a method on the given object.
     * 
     * @param object the object on which the method will be invoked
     * @param methodName the name of the method 
     * @param parameterTypes the types of the parameters
     * @param args the arguments to pass to the method
     * @return the result of the invocation
     * @throws NoSuchMethodException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */

    public static Object invokeMethod(Object object, String methodName, Class<?>[] parameterTypes, Object... args) 
        throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
            Method method = object.getClass().getDeclaredMethod(methodName, parameterTypes);
            method.setAccessible(true);
            return method.invoke(object, args);
    }

     /**
     * Gets the value of a static field from the given class.
     *
     * @param class_    the class from which to get the static field
     * @param fieldName the name of the static field
     * @return the value of the static field
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    public static Object getStaticField(Class<?> class_, String fieldName)
            throws NoSuchFieldException, IllegalAccessException {
        Field field = class_.getDeclaredField(fieldName);
        field.setAccessible(true);
        return field.get(null);
    }

    /**
     * Sets the value of a static field in the given class.
     *
     * @param class_    the class in which to set the static field
     * @param fieldName the name of the static field
     * @param value     the value to set
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    public static void setStaticField(Class<?> class_, String fieldName, Object value)
            throws NoSuchFieldException, IllegalAccessException {
        Field field = class_.getDeclaredField(fieldName);
        field.setAccessible(true);
        field.set(null, value);
    }

    /**
     * Invokes a static method on the given class.
     *
     * @param class_       the class on which to invoke the static method
     * @param methodName  the name of the static method
     * @param parameterTypes the types of the parameters
     * @param args        the arguments to pass to the method
     * @return the result of the method invocation
     * @throws NoSuchMethodException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    public static Object invokeStaticMethod(Class<?> class_, String methodName, Class<?>[] parameterTypes, Object... args)
            throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Method method = class_.getDeclaredMethod(methodName, parameterTypes);
        method.setAccessible(true);
        return method.invoke(null, args);
    }

    /**
     * Sets the value of a final field in the given object.
     *
     * @param object    the object in which to set the final field
     * @param fieldName the name of the final field
     * @param value     the value to set
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    public static void setFinalField(Object object, String fieldName, Object value)
            throws NoSuchFieldException, IllegalAccessException {
        Field field = object.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);

        // Remove the final modifier
        Field modifiersField = Field.class.getDeclaredField("modifiers");
        modifiersField.setAccessible(true);
        modifiersField.setInt(field, field.getModifiers() & ~Modifier.FINAL);

        field.set(object, value);
    }

    
}   

