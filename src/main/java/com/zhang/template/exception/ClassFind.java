package com.zhang.template.exception;

public class ClassFind {

    public Class classFind(ClassFindInterface classFindInterface, String className) {
        Class<?> clazz = null;
        try {
            clazz = classFindInterface.classNameToClass(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return clazz;
    }

    public static void main(String[] args) {
        ClassFind classFind = new ClassFind();
        Class<?> classNew = classFind.classFind(o-> Class.forName(o), "com.zhang.template.entity.Menu");
        System.out.println(classNew.getName());
    }
}
