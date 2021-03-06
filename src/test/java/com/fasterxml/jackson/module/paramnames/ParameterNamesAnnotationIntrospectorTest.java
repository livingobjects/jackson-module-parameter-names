package com.fasterxml.jackson.module.paramnames;

import com.fasterxml.jackson.databind.introspect.AnnotatedConstructor;
import com.fasterxml.jackson.databind.introspect.AnnotatedMethod;
import com.fasterxml.jackson.databind.introspect.AnnotatedParameter;

import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import static org.junit.Assert.assertEquals;

/**
 * @author Lovro Pandzic
 */
public class ParameterNamesAnnotationIntrospectorTest {

    private final ParameterNamesAnnotationIntrospector PN_AI = new ParameterNamesAnnotationIntrospector();

    @Test
    public void shouldFindParameterNameFromConstructorForLegalIndex() throws Exception {
        Constructor<?> ctor = ImmutableBean.class.getConstructor(String.class, Integer.class);

        AnnotatedConstructor owner = new AnnotatedConstructor(ctor, null, null);
        AnnotatedParameter annotatedParameter = new AnnotatedParameter(owner, null, null, 0);
        
        String propertyName = PN_AI.findImplicitPropertyName(annotatedParameter);

        assertEquals("name", propertyName);
    }

    @Test
    public void shouldFindParameterNameFromMethodForLegalIndex() throws NoSuchMethodException {

        Method method  = ImmutableBeanWithStaticFactory.class.getMethod("of", String.class, Integer.class);

        AnnotatedMethod owner = new AnnotatedMethod(method, null, null);
        AnnotatedParameter annotatedParameter = new AnnotatedParameter(owner, null, null, 0);

        String propertyName = PN_AI.findImplicitPropertyName(annotatedParameter);

        assertEquals("name", propertyName);
    }
}
