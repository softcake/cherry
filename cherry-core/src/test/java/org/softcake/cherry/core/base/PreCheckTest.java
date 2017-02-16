/*
 * Copyright 2017 softcake.org.
 *
 * Licensed under the Apache License, Version 2.0 (the "License").
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.softcake.cherry.core.base;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import org.softcake.lemon.core.tester.PrivateConstructorTester;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * PreCheckTest Class.
 *
 * @author softcake.org
 */
public class PreCheckTest {

    private static final String PARAMETER = "parameter";

    private static final String THIS_IS_THE_ERROR_MESSAGE = "This is the error message!";

    private static final String PARAMETER_NAME = "parameterName";

    private static final String THE_VALUE_OF_S_IS_F = "the value of %s is %d";

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void preCheck_constructorMustBePrivateAndThrow_assert() {

        PrivateConstructorTester.forClass(PreCheck.class).expectedExceptionType(
                IllegalStateException.class,
                "No instances!");
    }

    @Test
    public void notNull_parameterIsSameInstance_assert() {

        String para = PARAMETER;
        String result = PreCheck.notNull(para);
        assertSame(result, para);
    }

    @Test
    public void notNull_nullAsParameter_throw() {

        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("must not be null!");

        PreCheck.notNull(null);
    }

    @Test
    public void notNull_parameterIsSameInstanceWithErrorMessage_assert() {

        String para = PARAMETER;
        String result = PreCheck.notNull(para, THIS_IS_THE_ERROR_MESSAGE);
        assertSame(result, para);
    }

    @Test
    public void notNull_nullAsParameterWithErrorMessage_throw() {

        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage(THIS_IS_THE_ERROR_MESSAGE);

        PreCheck.notNull(null, THIS_IS_THE_ERROR_MESSAGE);

    }

    @Test
    public void notNull_parameterWithErrorMessageArgsIsSameInstance_assert() {

        String message = THE_VALUE_OF_S_IS_F;
        String para = PARAMETER;

        String result = PreCheck.notNull(para, message, PARAMETER, 1f);

        assertSame(result, para);
    }

    @Test
    public void notNull_nullAsParameterWithErrorMessageArgs_throw() {

        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("the value of " + PARAMETER + " is 1");
        String message = THE_VALUE_OF_S_IS_F;
        PreCheck.notNull(null, message, PARAMETER, 1);
        fail("Expected an IllegalArgumentException to be thrown");

    }

    @Test
    public void notNullOrEmpty_parameterIsSameInstance_assert() {

        String para = PARAMETER;
        String result = PreCheck.notNullOrEmpty(para);
        assertSame(result, para);
    }

    @Test
    public void notNullOrEmpty_nullAsParameter_throw() {

        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("must not be null or empty!");

        PreCheck.notNullOrEmpty(null);

    }

    @Test
    public void notNullOrEmpty_emptyParameter_throw() {

        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("must not be null or empty!");
        String para = "";
        PreCheck.notNullOrEmpty(para);

    }

    @Test
    public void notNullOrEmpty_parameterWithErrorMessageIsSameInstance_assert() {

        String message = THIS_IS_THE_ERROR_MESSAGE;
        String para = PARAMETER;
        String result = PreCheck.notNullOrEmpty(para, message);
        assertSame(result, para);
    }

    @Test
    public void notNullOrEmpty_nullAsParameterWithErrorMessage_throw() {

        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage(THIS_IS_THE_ERROR_MESSAGE);

        PreCheck.notNullOrEmpty(null, THIS_IS_THE_ERROR_MESSAGE);

    }

    @Test
    public void notNullOrEmpty_parameterWithErrorMessageArgsIsSameInstance_assert() {

        String message = THE_VALUE_OF_S_IS_F;
        String para = PARAMETER;
        String result = PreCheck.notNullOrEmpty(para, message, PARAMETER, 1f);
        assertSame(result, para);
    }

    @Test
    public void notNullOrEmpty_nullAsParameterWithErrorMessageArgs_throw() {

        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("the value of " + PARAMETER + " is 1");
        String message = THE_VALUE_OF_S_IS_F;

        PreCheck.notNullOrEmpty(null, message, PARAMETER, 1);

    }

    @Test
    public void parameterNotNull_parameterIsSameInstance_assert() {

        String para = PARAMETER;
        String parameterName = PARAMETER_NAME;
        String result = PreCheck.parameterNotNull(para, parameterName);
        assertSame(result, para);
    }

    @Test
    public void parameterNotNull_nullAsParameter_throw() {

        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("parameter '" + PARAMETER_NAME + "'" + " must not be null!");

        PreCheck.parameterNotNull(null, PARAMETER_NAME);

    }

    @Test
    public void parameterNotNullOrEmpty_parameterIsSameInstance_assert() {

        String para = PARAMETER;
        String result = PreCheck.parameterNotNullOrEmpty(para, PARAMETER_NAME);
        assertSame(result, para);
    }

    @Test
    public void parameterNotNullOrEmpty_nullAsParameter_throw() {

        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("parameter '" + PARAMETER_NAME + "'" + " must not be null or empty");

        PreCheck.parameterNotNullOrEmpty(null, PARAMETER_NAME);

    }

    @Test
    public void parameterNotNullOrEmpty_emptyParameter_throw() {

        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("parameter '" + PARAMETER_NAME + "'" + " must not be null or empty!");
        String para = "";

        PreCheck.parameterNotNullOrEmpty(para, PARAMETER_NAME);

    }

    @Test
    public void expression_trueAsParameter_notThrow() {

        try {
            PreCheck.expression(true);
        } catch (final IllegalArgumentException expected) {
            fail("Expected that not IllegalArgumentException to be thrown!");
        }
    }

    @Test
    public void expression_falseAsParameter_throw() {

        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("expression not valid!");

        PreCheck.expression(false);

    }

    @Test
    public void expression_trueAsParameterWithErrorMessage_notThrow() {

        try {
            PreCheck.expression(true, THIS_IS_THE_ERROR_MESSAGE);
        } catch (final IllegalArgumentException expected) {
            fail("Expected that not IllegalArgumentException to be thrown!");
        }
    }

    @Test
    public void expression_falseAsParameterWithErrorMessage_throw() {

        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage(THIS_IS_THE_ERROR_MESSAGE);

        PreCheck.expression(false, THIS_IS_THE_ERROR_MESSAGE);

    }

    @Test
    public void expression_trueAsParameterWithErrorMessageArgs_notThrow() {

        String message = THE_VALUE_OF_S_IS_F;
        try {
            PreCheck.expression(true, message, PARAMETER, 1f);
        } catch (final IllegalArgumentException expected) {
            fail("Expected that not IllegalArgumentException to be thrown!");
        }
    }

    @Test
    public void expression_falseAsParameterWithErrorMessageArgs_throw() {

        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("the value of " + PARAMETER + " is 1");
        String message = THE_VALUE_OF_S_IS_F;

        PreCheck.expression(false, message, PARAMETER, 1);

    }

    @Test
    public void constructorMustBePrivateAndThrowException() throws ReflectiveOperationException {

        PrivateConstructorTester.forClass(PreCheck.class).expectedExceptionType(
                IllegalStateException.class,
                "No instances!").check();
    }

    @Test
    public void isParamNullOrEmpty_nullAsParameter_assert() {

        boolean nullOrEmpty = PreCheck.isParamNullOrEmpty(null);
        assertThat(nullOrEmpty, is(true));
    }

    @Test
    public void isParamNullOrEmpty_primitiveAsParameter_throw() {

        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("parameter must be type Object");

        int parameter = 1;
        PreCheck.isParamNullOrEmpty(parameter);

    }

    @Test
    public void isParamNullOrEmpty_emptyArrayAsParameter_assert() {

        Object[] objects = new Object[0];
        boolean nullOrEmpty = PreCheck.isParamNullOrEmpty(objects);
        assertThat(nullOrEmpty, is(true));
    }

    @Test
    public void isParamNullOrEmpty_notEmptyArrayAsParameter_assert() {

        Object[] objects = new Object[1];
        boolean nullOrEmpty = PreCheck.isParamNullOrEmpty(objects);
        assertThat(nullOrEmpty, is(false));
    }

    @Test
    public void isParamNullOrEmpty_emptyStringAsParameter_assert() {

        String empty = "";
        boolean nullOrEmpty = PreCheck.isParamNullOrEmpty(empty);
        assertThat(nullOrEmpty, is(true));
    }

    @Test
    public void isParamNullOrEmpty_stringAsParameter_assert() {

        String notEmpty = "I'm not empty.";
        boolean nullOrEmpty = PreCheck.isParamNullOrEmpty(notEmpty);
        assertThat(nullOrEmpty, is(false));
    }

    @Test
    public void isParamNullOrEmpty_emptyCollectionAsParameter_assert() {

        Collection<String> collection = new HashSet<>();
        boolean nullOrEmpty = PreCheck.isParamNullOrEmpty(collection);
        assertThat(nullOrEmpty, is(true));
    }

    @Test
    public void isParamNullOrEmpty_notEmptyCollectionAsParameter_assert() {

        Collection<String> collection = new HashSet<>();
        collection.add("I'm not empty.");
        boolean nullOrEmpty = PreCheck.isParamNullOrEmpty(collection);
        assertThat(nullOrEmpty, is(false));
    }

    @Test
    public void isParamNullOrEmpty_emptyMapAsParameter_assert() {

        Map<String, String> map = new HashMap<>();
        boolean nullOrEmpty = PreCheck.isParamNullOrEmpty(map);
        assertThat(nullOrEmpty, is(true));
    }

    @Test
    public void isParamNullOrEmpty_notEmptyMapAsParameter_assert() {

        Map<String, String> map = new HashMap<>();
        map.put("key", "value");
        boolean nullOrEmpty = PreCheck.isParamNullOrEmpty(map);
        assertThat(nullOrEmpty, is(false));
    }

    @Test
    public void isParamNullOrEmpty_emptyCharSequenceAsParameter_assert() {

        StringBuffer stringBuffer = new StringBuffer();
        boolean nullOrEmpty = PreCheck.isParamNullOrEmpty(stringBuffer);
        assertThat(nullOrEmpty, is(true));
    }

    @Test
    public void isParamNullOrEmpty_notEmptyCharSequenceAsParameter_assert() {

        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("I'm not empty.");
        boolean nullOrEmpty = PreCheck.isParamNullOrEmpty(stringBuffer);
        assertThat(nullOrEmpty, is(false));
    }

    @Test
    public void getErrorMessage() throws ReflectiveOperationException {

        Method method = PreCheck.class.getDeclaredMethod("getErrorMessage", Object.class);
        method.setAccessible(true);

        String message = "I'm the error message!";
        String errorMessage = (String) method.invoke(PreCheck.class, message);

        assertThat(errorMessage, is(message));
    }

    @Test
    public void getErrorMessage_null() throws ReflectiveOperationException {

        Method method = PreCheck.class.getDeclaredMethod("getErrorMessage", Object.class);
        method.setAccessible(true);

        String errorMessage = (String) method.invoke(PreCheck.class, (Object) null);
        assertThat(errorMessage, is("error message is empty!"));

        errorMessage = (String) method.invoke(PreCheck.class, "");
        assertThat(errorMessage, is("error message is empty!"));
    }

    @Test
    public void format() throws ReflectiveOperationException {

        Method method = PreCheck.class.getDeclaredMethod("format", String.class, Object[].class);
        method.setAccessible(true);

        String errorMessage = (String) method.invoke(PreCheck.class,
                                                     "the value of %s is %d",
                                                     new Object[]{"parameter", 1});
        assertThat(errorMessage, is("the value of parameter is 1"));
    }
}
