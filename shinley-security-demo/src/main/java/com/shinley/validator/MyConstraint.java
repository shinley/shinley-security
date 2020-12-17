package com.shinley.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = MyConstraintValidator.class)   // 由这个类执行校验逻辑
public @interface MyConstraint {

    String message() default "";

    Class<?>[] groups() default {};

    Class<?  extends Payload>[] payload() default {};

}
