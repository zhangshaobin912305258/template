package com.zhang.template.exception;

@FunctionalInterface
public interface ClassFindInterface {

    Class<?> classNameToClass(String className) throws ClassNotFoundException;


}
