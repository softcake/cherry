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

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Map;

/**
 * PreCheck Class.
 *
 * @author softcake.org
 */
public final class PreCheck {

    private PreCheck() {

        throw new IllegalStateException("No instances!");
    }

    /**
     * Ensures that an object reference passed as a parameter to the calling method is not null.
     *
     * @param <T> the type
     * @param obj an object reference
     *
     * @return the non-null reference that was validated
     *
     * @throws IllegalArgumentException if {@code obj} is null
     */
    public static <T> T notNull(final T obj) {

        if (obj == null) {

            throw new IllegalArgumentException("must not be null!");

        }

        return obj;
    }

    /**
     * Ensures that an object reference passed as a parameter to the calling method is not null.
     *
     * @param <T>          the type
     * @param obj          an object reference
     * @param errorMessage the exception message to use if the check fails
     *
     * @return the non-null reference that was validated
     *
     * @throws IllegalArgumentException if {@code obj} is null
     */
    public static <T> T notNull(final T obj, final String errorMessage) {

        if (obj == null) {

            throw new IllegalArgumentException(errorMessage);

        }

        return obj;
    }

    /**
     * Ensures that an object reference passed as a parameter to the calling method is not null.
     *
     * @param <T>              the type
     * @param obj              an object reference
     * @param errorMessage     a template for the exception message should the check fail. The
     *                         message is formed by replacing each {@code %s} placeholder in the
     *                         template with an argument. These are matched by position - the first
     *                         {@code %s} gets {@code errorMessageArgs[0]}, etc. Unmatched arguments
     *                         will be appended to the formatted message in square braces. Unmatched
     *                         placeholders will be left as-is.
     * @param errorMessageArgs the arguments to be substituted into the message template. Arguments
     *                         are converted to strings using {@link String#valueOf(Object)}.
     *
     * @return the non-null reference that was validated
     *
     * @throws IllegalArgumentException if {@code obj} is null
     */
    public static <T> T notNull(final T obj,
                                final String errorMessage,
                                final Object... errorMessageArgs) {

        if (obj == null) {

            throw new IllegalArgumentException(format(errorMessage, errorMessageArgs));

        }

        return obj;
    }

    /**
     * Ensures that an object reference passed as a parameter to the calling method is not null and
     * not empty.
     *
     * @param <T> the type
     * @param obj an object reference
     *
     * @return the non-null and not empty reference that was validated
     *
     * @throws IllegalArgumentException if {@code obj} is null or empty
     */
    public static <T> T notNullOrEmpty(final T obj) {

        if (isParamNullOrEmpty(obj)) {

            throw new IllegalArgumentException("must not be null or empty!");

        }

        return obj;
    }

    /**
     * Ensures that an object reference passed as a parameter to the calling method is not null and
     * not empty.
     *
     * @param <T>          the type
     * @param obj          an object reference
     * @param errorMessage the exception message to use if the check fails
     *
     * @return the non-null and not empty reference that was validated
     *
     * @throws IllegalArgumentException if {@code obj} is null or empty
     */
    public static <T> T notNullOrEmpty(final T obj, final String errorMessage) {

        if (isParamNullOrEmpty(obj)) {

            throw new IllegalArgumentException(errorMessage);

        }

        return obj;
    }

    /**
     * Ensures that an object reference passed as a parameter to the calling method is not null and
     * not empty.
     *
     * @param <T>              the type
     * @param obj              an object reference
     * @param errorMessage     a template for the exception message should the check fail. The
     *                         message is formed by replacing each {@code %s} placeholder in the
     *                         template with an argument. These are matched by position - the first
     *                         {@code %s} gets {@code errorMessageArgs[0]}, etc. Unmatched arguments
     *                         will be appended to the formatted message in square braces. Unmatched
     *                         placeholders will be left as-is.
     * @param errorMessageArgs the arguments to be substituted into the message template. Arguments
     *                         are converted to strings using {@link String#valueOf(Object)}.
     *
     * @return the non-null and not empty reference that was validated
     *
     * @throws IllegalArgumentException if {@code obj} is null or empty
     */
    public static <T> T notNullOrEmpty(final T obj,
                                       final String errorMessage,
                                       final Object... errorMessageArgs) {

        if (isParamNullOrEmpty(obj)) {

            throw new IllegalArgumentException(format(errorMessage, errorMessageArgs));

        }

        return obj;
    }

    /**
     * Ensures that an object reference passed as a parameter to the calling method is not null.
     *
     * @param <T>           the type
     * @param obj           an object reference
     * @param parameterName the parameter name to use in exception message if the check fails.
     *                      Message: "parameter 'parameterName' must not be null!"
     *
     * @return the non-null reference that was validated
     *
     * @throws IllegalArgumentException if {@code obj} is null
     */
    public static <T> T parameterNotNull(final T obj, final String parameterName) {

        if (obj == null) {

            throw new IllegalArgumentException("parameter '"
                                               + parameterName
                                               + "'"
                                               + " must not be null!");

        }
        return obj;
    }

    /**
     * Ensures that an object reference passed as a parameter to the calling method is not null and
     * not empty
     *
     * @param <T>           the type
     * @param obj           an object reference
     * @param parameterName the parameter name to use in exception message if the check fails.
     *                      Message: "parameter 'parameterName' must not be null or empty"
     *
     * @return the non-null reference that was validated
     *
     * @throws IllegalArgumentException if {@code obj} is null or empty
     */
    public static <T> T parameterNotNullOrEmpty(final T obj, final String parameterName) {

        if (isParamNullOrEmpty(obj)) {

            throw new IllegalArgumentException("parameter '"
                                               + parameterName
                                               + "'"
                                               + " must not be null or empty");

        }

        return obj;
    }

    /**
     * Ensures the truth that an object reference passed as a parameter to the calling method is
     * null or empty
     *
     * @param <T> the type
     * @param obj an object reference (Objects, Arrays, Collections, String etc. and also Arrays of
     *            primitive types)
     *
     * @return the true if the given object is null or empty, false otherwise
     */
    public static <T> boolean isParamNullOrEmpty(final T obj) {

        boolean result;

        if (obj == null) {
            result = true;

        } else if (obj.getClass().isArray()) {
            result = Array.getLength(obj) == 0;

        } else if (obj instanceof String) {
            result = ((String) obj).isEmpty();

        } else if (obj instanceof Collection) {
            result = ((Collection) obj).isEmpty();

        } else if (obj instanceof Map) {
            result = ((Map) obj).isEmpty();

        } else if (obj instanceof CharSequence) {
            result = ((CharSequence) obj).length() == 0;

        } else {

            throw new IllegalArgumentException("parameter must be type Object");

        }

        return result;

    }

    /**
     * Ensures the truth of an expression involving one or more parameters to the calling method.
     *
     * @param expression a boolean expression
     *
     * @throws IllegalArgumentException if {@code expression} is false
     */
    public static void expression(final boolean expression) {

        if (!expression) {

            throw new IllegalArgumentException("expression not valid!");

        }
    }

    /**
     * Ensures the truth of an expression involving one or more parameters to the calling method.
     *
     * @param expression   a boolean expression
     * @param errorMessage the exception message to use if the check fails; will be converted to a
     *                     string using {@link String#valueOf(Object)}
     *
     * @throws IllegalArgumentException if {@code expression} is false
     */
    public static void expression(final boolean expression, final Object errorMessage) {

        if (!expression) {

            throw new IllegalArgumentException(getErrorMessage(errorMessage));

        }
    }

    /**
     * Ensures the truth of an expression involving one or more parameters to the calling method.
     *
     * @param expression       a boolean expression
     * @param errorMessage     a template for the exception message should the check fail. The
     *                         message is formed by replacing each {@code %s} placeholder in the
     *                         template with an argument. These are matched by position - the first
     *                         {@code %s} gets {@code errorMessageArgs[0]}, etc. Unmatched arguments
     *                         will be appended to the formatted message in square braces. Unmatched
     *                         placeholders will be left as-is.
     * @param errorMessageArgs the arguments to be substituted into the message template. Arguments
     *                         are converted to strings using {@link String#valueOf(Object)}.
     *
     * @throws IllegalArgumentException if {@code expression} is false or if the check fails and
     *                                  either {@code errorMessageTemplate} or {@code
     *                                  errorMessageArgs} is null (don't let this happen)
     */
    public static void expression(final boolean expression,
                                  final String errorMessage,
                                  final Object... errorMessageArgs) {

        if (!expression) {

            throw new IllegalArgumentException(format(errorMessage, errorMessageArgs));

        }
    }

    private static String getErrorMessage(final Object msg) {

        return msg == null || String.valueOf(msg).trim().length() == 0
               ? "error message is empty!"
               : String.valueOf(msg);
    }

    private static String format(final String msg, final Object... args) {

        return String.format(getErrorMessage(msg), args);

    }

}

