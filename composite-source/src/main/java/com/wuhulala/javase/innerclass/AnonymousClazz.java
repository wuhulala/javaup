package com.wuhulala.javase.innerclass;

/**
 * @author xueaohui
 * @version 1.0
 * @date 2017/5/3
 * @link https://github.com/wuhulala
 */
class Point{
    private int x;
    private int y;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
public class AnonymousClazz {
    public  void test() {
        int a  = 1;
        int b  = 2;
        final Point c = new Point(1, 2);
        OneInterface oneInterface = new OneInterface() {
            @Override
            public Point add(int a, int b) {
                return c;
            }
        };
        System.out.println(oneInterface.add(a, b));

    }
}

//jd-gui反编译
//
// 把c传了进去，如果修改了这个c值，那么又是数据不安全了。如果这个是一个基本类型,会在编译时期直接放入代码中,如果final int c = 3，那么第9行直接return 3;
//public class AnonymousClazz
//{
//1 public void test()
//2        {
//3        int a = 1;
//4        int b = 2;
//5        Point c = new Point(1, 2);
//6        OneInterface oneInterface = new OneInterface(c) //重点可以看这里
//7        {
//8 public Point add(int a, int b) {
//9        return this.val$c;
//10        }
//11        };
//12        System.out.println(oneInterface.add(a, b));
//13        }
//14        }
