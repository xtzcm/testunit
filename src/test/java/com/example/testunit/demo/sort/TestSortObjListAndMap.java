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
public class TestSortObjListAndMap {

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
    public void testSortListByInt() {
        //常规方式
//        Collections.sort(objList, new Comparator<ObjForSort>() {
//            @Override
//            public int compare(ObjForSort o1, ObjForSort o2) {
//                return o1.getAge() - o2.getAge();
//            }
//        });
        //lambda表达式方式
        Collections.sort(objList, Comparator.comparingInt(ObjForSort::getAge));
    }

    /**
     * 根据String字段排序
     */
    @Test
    public void testSortListByString(){
        //常规方式
//        Collections.sort(objList, new Comparator<ObjForSort>() {
//            @Override
//            public int compare(ObjForSort o1, ObjForSort o2) {
//                Collator collator = Collator.getInstance(Locale.CHINA);
//                return collator.compare(o1.getName(), o2.getName());
//            }
//        });
        //lambda表达式方式
        Collections.sort(objList, (o1, o2) -> {
            Collator collator = Collator.getInstance(Locale.CHINA);
            return collator.compare(o1.getName(), o2.getName());
        });
    }

    /**
     * 根据ObjForSort对象的name进行排序
     */
    @Test
    public void testSortMapByObjForSortName(){
        //常规方式
//        Map<ObjForSort, String> objMap = new TreeMap<>(new Comparator<ObjForSort>() {
//            @Override
//            public int compare(ObjForSort o1, ObjForSort o2) {
//                int result = o1.getName().compareTo(o2.getName());
//                if (0 == result)
//                    return new Integer(o1.getAge()).compareTo(new Integer(o2.getAge()));
//                return result;
//            }
//        });
        //lambda表达式方式
        Map<ObjForSort, String> objMap = new TreeMap<>((o1, o2) -> {
            int result = o1.getName().compareTo(o2.getName());
            if (0 == result)
                return new Integer(o1.getAge()).compareTo(new Integer(o2.getAge()));
            return result;
        });
        objMap.put(new ObjForSort("zhaoliu", 16), "深圳");
        objMap.put(new ObjForSort("赵六", 16), "深圳");
        objMap.put(new ObjForSort("王五", 15), "广州");
        objMap.put(new ObjForSort("wangwu", 15), "广州");
        objMap.put(new ObjForSort("李四", 14), "上海");
        objMap.put(new ObjForSort("lisi", 14), "上海");
        objMap.put(new ObjForSort("张三", 13), "北京");
        objMap.put(new ObjForSort("zhangsan", 13), "北京");
        for (Map.Entry<ObjForSort, String> entry : objMap.entrySet()) {
            System.out.println(entry.getKey().getName() + " : " + entry.getValue());
        }
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
