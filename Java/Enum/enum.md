# Enum

- 枚举用来表示一组命名常量，在Java中，我们可以向其中添加变量，方法和构造函数，甚至实现接口

## Declaration 

```java
public enum Color {
    RED, GREEN, BLUE
}

class Test{
    public static void main(String[] args) {
        System.out.println(Color.BLUE);
    }
}
```

- 每个枚举类内部是通过类实现的，并且隐式继承java.lang.Enum类
- 当枚举类加载的时候，每一个枚举常量会一个个的进行实例化，我们无法显式调用枚举类的构造函数，因为枚举类型的构造函数始终是私有的

```java
// internally above enum Color is converted to
class Color extends Enum<Color>
{
     public static final Color RED = new Color();
     public static final Color BLUE = new Color();
     public static final Color GREEN = new Color();
}
```

## Methods

1. toString()：继承自Enum类，返回枚举常量实例的名字
2. values()：返回枚举类中的所有常量实例
3. ordinal()：找到枚举常量实例在枚举类中的索引
4. valueOf()：返回枚举类中对应名字的常量实例

```java
public enum Color {
    RED, GREEN, BLUE
}

class Test{
    public static void main(String[] args) {
        for (Color c : Color.values()) {
            System.out.println(c);
            System.out.println(c.ordinal());
        }
        System.out.println(Color.valueOf("RED"));
    }
}
```

## Variables

- 枚举类中可以加上一些成员变量

```java
// Java program to demonstrate how values can 
// be assigned to enums. 
enum TrafficSignal 
{ 
    // This will call enum constructor with one 
    // String argument 
    RED("STOP"), GREEN("GO"), ORANGE("SLOW DOWN"); 
  
    // declaring private variable for getting values 
    private String action; 
  
    // getter method 
    public String getAction() 
    { 
        return this.action; 
    } 
  
    // enum constructor - cannot be public or protected 
    private TrafficSignal(String action) 
    { 
        this.action = action; 
    } 
} 
  
// Driver code 
public class EnumConstructorExample 
{ 
    public static void main(String args[]) 
    { 
        // let's print name of each enum and there action 
        // - Enum values() examples 
        TrafficSignal[] signals = TrafficSignal.values(); 
  
        for (TrafficSignal signal : signals) 
        { 
            // use getter method to get the value 
            System.out.println("name : " + signal.name() + 
                        " action: " + signal.getAction() ); 
        } 
    } 
} 
```