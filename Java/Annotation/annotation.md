# Annotation

## Annotation组成部分

1. interface Annotation
    > 一个Annotation与多个ElementType关联，与一个RetentionPolicy关联

2. enum ElementType

    ``` java
    public enum ElementType {
        /** Class, interface (including annotation type), or enum declaration */
        TYPE,
        /** Field declaration (includes enum constants) */
        FIELD,
        /** Method declaration */
        METHOD,
        /** Formal parameter declaration */
        PARAMETER,
        /** Constructor declaration */
        CONSTRUCTOR,
        /** Local variable declaration */
        LOCAL_VARIABLE,
        /** Annotation type declaration */
        ANNOTATION_TYPE,
        /** Package declaration */
        PACKAGE,
        /**
        * Type parameter declaration
        *
        * @since 1.8
        */
        TYPE_PARAMETER,
        /**
        * Use of a type
        *
        * @since 1.8
        */
        TYPE_USE
    }
    ```

3. enum RetentionPolicy
    ```java
    public enum RetentionPolicy {
        /**
        * Annotations are to be discarded by the compiler.
        */
        SOURCE,
        /**
        * Annotations are to be recorded in the class file by the compiler
        * but need not be retained by the VM at run time.  This is the default
        * behavior.
        */
        CLASS,
        /**
        * Annotations are to be recorded in the class file by the compiler and
        * retained by the VM at run time, so they may be read reflectively.
        *
        * @see java.lang.reflect.AnnotatedElement
        */
        RUNTIME 
    }
    ```

## Java自带的Annotation

1. @interface

2. @Documented : 类和方法的 Annotation 在缺省情况下是不出现在 javadoc 中的，标注之后会出现在 javadoc 中的

3. @Target(ElementType.xxx) : 指定 Annotation 的类型属性

4. @Retention(RetentionPolicy.xxx) : 指定 Annotation 的策略属性

5. @Deprecated

6. @Override

7. @Documented

8. @Inherited : 注解是否具有继承性，即注解父类的时候，其子类也会有该注解

9. SuppressWarnings

## exercise

```java
package com.bego.annotations;

import java.io.File;
import java.lang.annotation.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@interface Component{
    String value();
}

@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@interface ComponentScan{
    String value() default  "com.bego";
}

@Component("controller")
class Controller{
    Controller(){
        System.out.println("Controller Constructed");
    }
}

@ComponentScan
public class Application {

    private List<String> classes = new ArrayList<String>();

    private Map<String, Object> container = new HashMap<String, Object>();

    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        new Application().run();
    }

    public void run() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        if(this.getClass().isAnnotationPresent(ComponentScan.class)) {
            ComponentScan annotation = this.getClass().getAnnotation(ComponentScan.class);
            String packageName = annotation.value();
            String classPath = this.getClass().getResource("/").getPath();
            String searchPath = classPath + packageName.replace(".", File.separator);
            this.getClassesPath(new File(searchPath));
            this.refresh(classPath);
        }   else {
            System.out.println("ComponentScan not present");
        }

    }

    private void refresh(String classPath) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        for(String s : classes) {
            s = s.replace(classPath.replace("/","\\").replaceFirst("\\\\",""),"").replace("\\",".").replace(".class","");
            Class cls = Class.forName(s);
            if(cls.isAnnotationPresent(Component.class)) {
                Component annotation = (Component) cls.getAnnotation(Component.class);
                container.put(annotation.value(), cls.newInstance());
            }
        }
        for (Map.Entry<String, Object> entry : container.entrySet()) {
            System.out.println("key:" + entry.getKey() + "class:" + entry.getValue().toString());
        }
    }

    public void getClassesPath(File file){
        if(file.isDirectory()) {
            File[] files = file.listFiles();
            for(File file1 : files) {
                getClassesPath(file1);
            }
        } else {
            if(file.getName().endsWith(".class")) {
                classes.add(file.getPath());
            }
        }

    }
}
```