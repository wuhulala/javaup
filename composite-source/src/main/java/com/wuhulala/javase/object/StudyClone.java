package com.wuhulala.javase.object;

import java.io.*;

/**
 * java对象的深克隆与浅克隆示例<br>
 *<hr/>
 *<b>浅克隆</b>一般情况下满足条件---利用Object.clone()进行复制<br>
 *  1.对于任何的对象x，都有x.clone()!=x<br>
 *  2.对于任何一个对象x，都有x.clone().getClass() == x.getClass()<br>
 *  3.如果对于equals定义恰当，都有x.clone().equals(x)<br>
 *  4.对于所有的原来的属性引用都是之前的引用<br>
 *  5.浅拷贝需要实现Cloneable接口<br>
 *<hr>
 *<b>深克隆</b>一般情况下满足条件---利用串行化进行复制<br>
 *  1.对于任何的对象x，都有x.clone()!=x<br>
 *  2.对于任何一个对象x，都有x.clone().getClass() == x.getClass()<br>
 *  3.如果对于equals定义恰当，都有x.clone().equals(x)<br>
 *  4.对于所有的原来的属性引用都是新建的复制对象的引用，简而言之，就是把所有的对象引用都复制一遍<br>
 *  5.进行深克隆的时候属性如果是对象引用，相应的类应该实现Serializable接口<br>
 *<hr>
 *
 * <code>
 *     User user1 = new User("xiaohui",19);<br>
 *     Address address1 = new Address("杭州市");<br>
 *     user1.setAddress(address1);<br>
 *     User user2 = (User) user1.clone();<br>
 * </code>
 * ========================== 结果 ===================================<br>
 * =======================浅克隆 Start================================<br>
 *[user1 == user2]:false<br>
 *[user1.getClass() == user2.getClass()]:true<br>
 *[user1.equals(user2)]:false<br>
 *[user1.getName():user2.getName()]xiaohui:xiaohui<br>
 *[user1.getAddress()==user2.getAddress()]:true<br>
 *=======================浅克隆 End================================<br>
 *=======================深克隆 Start================================<br>
 *[user1 == user2]:false<br>
 *[user1.getClass() == user2.getClass()]:true<br>
 *[user1.equals(user2)]:false<br>
 *[user1.getName():user2.getName()]xiaohui:xiaohui<br>
 *[user1.getAddress()==user2.getAddress()]:false<br>
 *=======================深克隆 End================================<br>
 * @author xueaohui
 * @version 1.0
 * @link https://github.com/wuhulala
 */

public class StudyClone {

    public static void main(String[] args) {

        testClone();
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");

        testDeepClone();

        /*

        * */
    }

    public static void testClone(){
        System.out.println("=======================浅克隆 Start================================");
        User user1 = new User("xiaohui",19);
        Address address1 = new Address("杭州市");
        user1.setAddress(address1);
        try {
            User user2 = (User) user1.clone();
            System.out.println("[user1 == user2]:"+(user1 == user2));           //false
            System.out.println("[user1.getClass() == user2.getClass()]:"+(user1.getClass() == user2.getClass()));//true
            System.out.println("[user1.equals(user2)]:"+user1.equals(user2)); //false
            System.out.println("[user1.getName():user2.getName()]"+user1.getName()+":"+user2.getName());//xiaohui:xiaohui
            System.out.println("[user1.getAddress()==user2.getAddress()]:"+(user1.getAddress()==user2.getAddress())); //true

        } catch (CloneNotSupportedException e) {
            System.out.println("<================克隆失败===============>");
            //e.printStackTrace();
        }

        System.out.println("=======================浅克隆 End================================");
    }

    public static void testDeepClone()  {
        System.out.println("=======================深克隆 Start================================");
        User user1 = new User("xiaohui",19);
        Address address1 = new Address("杭州市");
        user1.setAddress(address1);
        try {
            User user2 = (User) user1.deepClone();
            System.out.println("[user1 == user2]:"+(user1 == user2));           //false
            System.out.println("[user1.getClass() == user2.getClass()]:"+(user1.getClass() == user2.getClass()));//true
            System.out.println("[user1.equals(user2)]:"+user1.equals(user2)); //false
            System.out.println("[user1.getName():user2.getName()]"+user1.getName()+":"+user2.getName());//xiaohui:xiaohui
            System.out.println("[user1.getAddress()==user2.getAddress()]:"+(user1.getAddress()==user2.getAddress())); //true

        } catch (IOException e) {
            System.out.println("<================克隆失败 IOException===============>");
        } catch (ClassNotFoundException e) {
            System.out.println("<================克隆失败 ClassNotFoundException===============>");
        }

        System.out.println("=======================深克隆 End================================");
    }

}

/**
 * 用户类
 * @author xueaohui
 * @version 1.0
 * @link https://github.com/wuhulala
 */
class User implements Cloneable,Serializable{
    private String name;
    private int age;
    private Address address;

    public User() {
    }

    public User(String name,int age){
        this.name = name;
        this.age = age;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();

    }

    /**
     * 深拷贝
     */
    public Object deepClone() throws IOException, ClassNotFoundException {
        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        ObjectOutputStream oo = new ObjectOutputStream(bo);
        oo.writeObject(this);
        ByteArrayInputStream bi = new ByteArrayInputStream(bo.toByteArray());
        ObjectInputStream oi = new ObjectInputStream(bi);
        return (oi.readObject());
    }
}

class Address implements Serializable{
    private String value;

    public Address() {
    }

    public Address(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}