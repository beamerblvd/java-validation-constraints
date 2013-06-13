/*
 * Copyright 2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.nicholaswilliams.java.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * Constraint annotation that ensures that the {@link CharSequence} target is a valid URI, and optionally that the
 * {@code CharSequence} or {@link java.net.URI} target has a restricted set of components.<br>
 * <br>
 * {@code null} values are considered valid.
 *
 * @author Nicholas Williams
 * @since 1.0.0
 */
@SuppressWarnings("unused")
@Target({ ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR,
		  ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = {  }) //TODO: implement validator
public @interface Uri
{
	/**
	 * Indicates which schemes the URI is allowed to have. By default, all schemes are allowed and the URI is
	 * allowed to have no scheme. A {@code null} value indicates that the URI may not have a scheme.
	 *
	 * @return the allowed schemes.
	 */
	String[] schemes() default { };

	/**
	 * Indicates which scheme-specific parts the URI is allowed to have. By default, all SSPs are allowed and the URI
	 * is allowed to have no SSP. A {@code null} value indicates that the URI may not have a scheme or SSP.
	 *
	 * @return the allowed host names.
	 */
	String[] ssp() default { };

	/**
	 * Indicates which ports the URI is allowed to have. By default, all ports are allowed and the URI is allowed to
	 * have no port. A {@code null} value indicates that the URI may not have a part.
	 *
	 * @return the allowed ports.
	 */
	int[] port() default { };

	/**
	 * Indicates whether the URI must have a path part.
	 *
	 * @return {@code true} if the URI must have a path part, {@code false} otherwise.
	 */
	boolean requiresPath() default false;

	/**
	 * Indicates whether the URI must have a query part.
	 *
	 * @return {@code true} if the URI must have a query part, {@code false} otherwise.
	 */
	boolean requiresQuery() default false;

	/**
	 * Indicates whether the URI must have a fragment.
	 *
	 * @return {@code true} if the URI must have a fragment, {@code false} otherwise.
	 */
	boolean requiresFragment() default false;

	/**
	 * Indicates whether the URI must have a user-info part.
	 *
	 * @return {@code true} if the URI must have a user-info part, {@code false} otherwise.
	 */
	boolean requiresUserInfo() default false;

	String message() default "{net.nicholaswilliams.java.validation.Uri.message}";

	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };

	/**
	 * Used for specifying multiple constraints of the same type.
	 */
	@Target({ ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR,
			  ElementType.PARAMETER })
	@Retention(RetentionPolicy.RUNTIME)
	@Documented
	static @interface List
	{
		Uri[] value();
	}
}
