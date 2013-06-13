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
import javax.validation.OverridesAttribute;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

/**
 * Composite constraint annotation that enforces {@link NotNull @NotNull} and {@link DecimalMin @DecimalMin}.
 *
 * @author Nicholas Williams
 * @since 1.0.0
 * @see DecimalMin
 */
@SuppressWarnings("unused")
@Target({ ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR,
		  ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = { })
@ReportAsSingleViolation
@NotNull
@DecimalMin("0")
public @interface NotNullDecimalMin
{
	/**
	 * The minimum value allowed.
	 *
	 * @return the minimum.
	 */
	@OverridesAttribute(constraint = DecimalMin.class, name = "value")
	String value();

	/**
	 * Specifies whether the minimum is inclusive or exclusive. It is inclusive by default.
	 *
	 * @return {@code true} if the target must be {@code ≥ }{@link #value}, {@code false} if the target must be
	 *         {@code > }{@link #value}
	 */
	@OverridesAttribute(constraint = DecimalMin.class, name = "inclusive")
	boolean inclusive() default true;

	String message() default "{net.nicholaswilliams.java.validation.NotNullDecimalMin.message}";

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
		NotNullDecimalMin[] value();
	}
}
