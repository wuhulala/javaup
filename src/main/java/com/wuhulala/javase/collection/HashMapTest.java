package com.wuhulala.javase.collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * author： wuhulala
 * date： 2017/6/25
 * version: 1.0
 * description: 作甚的
 */

public class HashMapTest {
    private static final Logger logger = LoggerFactory.getLogger(HashMapTest.class);

    public static void main(String[] args) {

        Map<String, String> nameAddress = new HashMap<>();

        nameAddress.put("xiaoming", "杭州市滨江区");
        nameAddress.put("xiaohong", "杭州市江干区");
        nameAddress.put("xiaolang", "杭州市滨江区");
        nameAddress.put("xiaogou", "杭州市滨江区");
        nameAddress.put("daming", "杭州市西湖区");

        //1.8 如果key值不存在，写入value，否则直接返回value
        nameAddress.putIfAbsent("daming", "杭州市下沙区");
        print(nameAddress, "daming"); //(key，value) ---> (daming,杭州市西湖区)

        //1.8 如果返回的值为null则删除这个映射
        //否则写入新的值
        nameAddress.compute("daming", (k, v) -> {
            return "杭州市萧山区";
        });
        print(nameAddress,"daming"); //(key，value) ---> (daming,杭州市萧山区)

        //1.8 如果这个映射的value为null才写入
        nameAddress.computeIfAbsent("daming", (k) -> {
           return "new address";
        });
        print(nameAddress,"daming"); //(key，value) ---> (daming,杭州市萧山区)

        //1.8 如果这个映射的value不为null，并且返回的新值也不为null才写入
        nameAddress.computeIfPresent("daming", (k, v) -> {
            return "new address";
        });
        print(nameAddress,"daming"); //(key，value) ---> (daming,new address)

        //O(n)
        boolean isContainedAddress = nameAddress.containsValue("new address");
        System.out.println(isContainedAddress);

        //1，8 Map接口默认方法
        nameAddress.replace("daming", "new address","test2");
        print(nameAddress,"daming"); //(key，value) ---> (daming,test2)
        nameAddress.replace("daming","test3");
        print(nameAddress,"daming"); //(key，value) ---> (daming,test3)


        nameAddress.put("daming","test4");
        print(nameAddress,"daming"); //(key，value) ---> (daming,test4)

        //1.8 遍历
        nameAddress.forEach((key, value) -> {
            System.out.println(key + "[" + value + "]");
        });

        //清空
        nameAddress.clear();
    }

    private static void print(Map map, String key) {
        logger.debug("(key，value) ---> ("+ key +"," + map.get(key) + ")");
    }
}
