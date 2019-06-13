# 泛型

### checkcast 解释
checkcast checks that the top item on the operand stack (a reference to an object or array) 
can be cast to a given type. For example, if you write in Java:

```
return ((String)obj);
```

then the Java compiler will generate something like:
```
aload_1 ; push -obj- onto the stack
checkcast java/lang/String ; check its a String
areturn ; return it
```


checkcast is actually a shortand for writing Java code like:
```
if (! (obj == null || obj instanceof <class>)) {
throw new ClassCastException();
}
// if this point is reached, then object is either null, or an instance of
// <class> or one of its superclasses.
```
