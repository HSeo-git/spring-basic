package com.example.springbasic.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE, ElementType.METHOD}) //annotation target
@Retention(RetentionPolicy.RUNTIME) //annotation maintain period
public @interface RunningTime {
}
