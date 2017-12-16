package com.wuhulala.javase.collection;

import com.wuhulala.api.User;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 作甚的
 *
 * @author wuhulala
 * @version 1.0
 * @since 2017/12/16
 */
public class ArrayListCopyTest {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        User u1 = new User("user1", 12);
        User u2 = new User("user2", 13);
        User u3 = new User("user3", 14);
        User u4 = new User("user4", 15);
        User u5 = new User("user5", 15);
        User u6 = new User("user6", 15);
        User u7 = new User("user7", 15);
        User u8 = new User("user8", 15);


        List<User> userSrc = new ArrayList<>();
        userSrc.add(u1);
        userSrc.add(u2);
        userSrc.add(u3);
        userSrc.add(u4);

        List<User> userDest = new ArrayList<>(4);
        userDest.add(u5);
        userDest.add(u6);
        userDest.add(u7);
        userDest.add(u8);

        // 浅拷贝
        Collections.copy(userDest, userSrc);

        System.out.println(userDest.get(0) == u1);
        u1.setName("new_user1");
        System.out.println(userDest.get(0).getName());
        System.out.println(userSrc.get(0).getName());
        for (User user : userDest) {
            System.out.println(user.getName());
        }

        // 浅拷贝2
        List<User> userDest2 = new ArrayList<>(userSrc);
        System.out.println(userDest2.get(0) == userSrc.get(0));

        // 序列化方式 拷贝,这只是jdk的一种格式 还有很多种，比如json/ protobuff
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream os = new ObjectOutputStream(bos);
        os.writeObject(userSrc);
        os.flush();
        os.close();
        byte[] userSrcByte = bos.toByteArray();

        ByteArrayInputStream bis = new ByteArrayInputStream(userSrcByte);
        ObjectInputStream is = new ObjectInputStream(bis);
        List<User> userDest3 = (List<User>) is.readObject();
        System.out.println(userDest3.get(0));
        System.out.println(userDest3.get(0).getName());
        System.out.println(userDest3.get(0) == userSrc.get(0));
        bos.close();


    }
}
