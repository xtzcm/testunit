package com.example.testunit.demo.sort;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.text.Collator;
import java.util.*;

/**
 * @Despriction: 根据list中对象的某个字段的顺序对list进行排序
 * @Author: wangcheng
 * @Date: 2018/5/3 21:36
 */
public class TestSortObjList {

    public static List<ObjForSort> objList;

    @Before
    public void getObjList(){
        ObjForSort obj5 = new ObjForSort("王五",15);
        ObjForSort obj3 = new ObjForSort("张三",13);
        ObjForSort obj7 = new ObjForSort("田七",17);
        ObjForSort obj4 = new ObjForSort("李四", 14);
        ObjForSort obj6 = new ObjForSort("赵六", 16);
        objList = Arrays.asList(obj5, obj3, obj7, obj4, obj6);
    }

    @After
    public void soutResult(){
        for (ObjForSort obj : objList) {
            System.out.println(obj.getName() + obj.getAge());
        }
    }

    /**
     * 根据int字段进行排序
     */
    @Test
    public void testSortByInt() {
        //方法1:常规方式 TODO 排序失败
        Comparator<ObjForSort> comparator = new Comparator<ObjForSort>() {
            @Override
            public int compare(ObjForSort o1, ObjForSort o2) {
                return o1.getAge() > o2.getAge() ? 0 : 1;
            }
        };
        //方法2:lambda表达式
//        Comparator<ObjForSort> comparator = (o1, o2) -> o1.getAge() > o2.getAge() ? 1 : 0;
//        Collections.sort(objList, comparator);
    }

    /**
     * 根据String字段排序
     */
    @Test
    public void testSortByString(){

        //根据姓名排序（String）

//        Collections.sort(objList, new Comparator<ObjForSort>() {
//            @Override
//            public int compare(ObjForSort o1, ObjForSort o2) {
//                Collator collator = Collator.getInstance(Locale.CHINA);
//                return collator.compare(o1.getName(), o2.getName());
//            }
//        });

//         Collections.sort(objList, (o1, o2) -> {
//            Collator collator = Collator.getInstance(Locale.CHINA);
//            return collator.compare(o1.getName(), o2.getName());
//        });

        //TODO 排序失败
//        objList.sort((p1, p2) -> p1.getName().compareTo(p2.getName()));

    }
}

class ObjForSort{
    private String name;
    private int age;

    public ObjForSort(String name, int age) {
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
}
