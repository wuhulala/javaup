背景

NullPointerException是java开发中的常见的异常.
```
Person person = people.find("John Smith");
if (person != null) {
 person.doSomething();
}
```
遗憾的是，在绝大多数Java代码里，我们常常忘记了判断空引用. 
如何优雅的解决这个问题. 
Optional是对可以为空的对象进行的封装, 并不会减少代码量，甚至比原来的代码还多。但好处在于，你绝对不会忘记判空，因为这里我们得到的不是Person类的对象，而是Optional。

Optional的使用
创建
如何创建一个Optional对象，基本的规则很简单：

如果我们知道自己要封装的对象是一个空对象，可以用 
Optional.absent();

如果封装的对象是一个非空对象，则可以用 
Optional.of(obj);

如果不知道对象是否为空，就这样创建创建 
Optional.fromNullable(obj);

判断
有时候，当一个对象为null的时候，我们并不是简单的忽略，而是给出一个缺省值，比如找不到这个人，任务就交给经理来做。使用Optional可以很容易地做到这一点，以上面的代码为例：

```
Optional person = people.find(“John Smith”); 
person.or(manager).doSomething()
```
说白了，Optinal是给了我们一个更有意义的“空”。