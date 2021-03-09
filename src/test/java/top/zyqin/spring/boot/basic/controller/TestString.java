package top.zyqin.spring.boot.basic.controller;

/**
 * @authoradmin
 * @date 2021/3/9 8:42
 * @description TestString
 */
public class TestString {
    public static void main(String[] args) {
        String name = "11.jpg";
        int index = name.lastIndexOf(".");
        System.out.println(index);
        System.out.println(name.substring(index));
    }
}
