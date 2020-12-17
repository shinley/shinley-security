package com.shinley.validator;

import com.shinley.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 此类不用comment注解
 */
public class MyConstraintValidator implements ConstraintValidator<MyConstraint, String> {

    @Autowired
    private HelloService helloService;


    @Override
    public void initialize(MyConstraint constraintAnnotation) {
        System.out.println("my validator init");
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        helloService.greeting("tom");
        System.out.println(value);
        return false;
    }
}
