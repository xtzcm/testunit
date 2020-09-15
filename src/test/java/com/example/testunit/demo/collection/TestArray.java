package com.example.testunit.demo.collection;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @Despriction:
 * @Author: wangcheng
 * @Date: 2018/5/6 11:07
 */
public class TestArray {

    /**
     * 测试浅拷贝，实际元素的内存地址是相同的
     */
    @Test
    public void testShallowCopy() {
        //=====================================
        List list1 = new ArrayList();
        for (int i = 1; i < 10000; i++) {
            list1.add("a" + i);
        }
        List list2 = new ArrayList();
        for (int i = 1; i < 10000; i++) {
            list2.add("a" + (i + 10));
        }
        //=====================================
        long start = System.currentTimeMillis();
        //1. 使用List实现类的构造方法-效率1
        List<Object> destList = new ArrayList<>(list1);
        //2. 使用list.addAll()方法-效率2
//        List<Object> destList=new ArrayList<Object>();
//        destList.addAll(list1);
        //3. 使用System.arraycopy()方法-效率2
//        Object[] src = list1.toArray(new Object[0]);
//        Object[] dest = new Object[list1.size()];
//        System.arraycopy(src, 0, dest, 0, src.length);
//        List<Object> destList = Arrays.asList(dest);
        //4. 使用Collections的copy方法-效率3
//        ArrayList<Object> destList = new ArrayList<>(Arrays.asList(new Integer[list1.size()]));
//        Collections.copy(destList, list1);
        //5. 遍历循环复制-效率5
//        List<Object> destList=new ArrayList<>(list1.size());
//        for(Object p : list1){
//            destList.add(p);
//        }
        System.out.println("耗时" + (System.currentTimeMillis() - start));
        System.out.println("destList = " + destList);
    }

    /**
     * 测试深拷贝，实际元素的内存地址是不同的
     */
    @Test
    public void testDeepCopy() {
        //=====================================
        List list1 = new ArrayList();
        for (int i = 1; i < 10000; i++) {
            list1.add("a" + i);
        }
        List list2 = new ArrayList();
        for (int i = 1; i < 10000; i++) {
            list2.add("a" + (i + 10));
        }
        //=====================================
        long start = System.currentTimeMillis();
        //1. 使用序列化-184
        List<Object> destList= null;  //调用该方法
        try {
            destList = deepCopy(list1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //2. 使用clone方法-

        System.out.println("耗时" + (System.currentTimeMillis() - start));
        System.out.println("destList = " + destList);
    }

    /**
     * 测试ArrayList
     */
    @Test
    public void testArrayList(){
        //=====================================
        List list1 = new ArrayList();
        for (int i = 1; i < 4; i++) {
            list1.add("a" + i);
        }
        List list2 = new ArrayList();
        for (int i = 1; i < 4; i++) {
            list2.add("a" + (i + 2));
        }
        System.out.println("list1原始: " + list1);
        System.out.println("list2原始: " + list2);
        //=====================================
        //1. 取并集(含重复内容)
//        list1.addAll(list2);
        //2. 取无重复并集
//        list2.removeAll(list1);
//        list1.addAll(list2);
        //3. 取交集(共有部分)
//        list1.retainAll(list2);
        //4. 取差集(删除list1中与list2相同的部分)
//        list1.removeAll(list2);
        //7. 取两个list中的不同元素
        List<Object> diffList = getDiffrent(list1, list2);
        System.out.println("diffList: " + diffList);
        System.out.println("list1更新: " + list1);
    }

    /**
     * 使用序列化进行深拷贝
     * @param src
     * @param <T>
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static <T> List<T> deepCopy(List<T> src) throws IOException, ClassNotFoundException {
        ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(byteOut);
        out.writeObject(src);

        ByteArrayInputStream byteIn = new ByteArrayInputStream(byteOut.toByteArray());
        ObjectInputStream in = new ObjectInputStream(byteIn);
        @SuppressWarnings("unchecked")
        List<T> dest = (List<T>) in.readObject();
        return dest;
    }

    /**
     * 获取两个List的不同元素
     * @param list1
     * @param list2
     * @return
     */
    private static List<String> getDiffrent(List<String> list1, List<String> list2) {
        List<String> diff = new ArrayList<>();
        List<String> maxList = list1;
        List<String> minList = list2;
        if(list2.size()>list1.size()) {
            maxList = list2;
            minList = list1;
        }
        Map<String,Integer> map = new HashMap<>(maxList.size());
        for (String string : maxList) {
            map.put(string, 1);
        }
        for (String string : minList) {
            if(map.get(string) != null) {
                map.put(string, 2);
                continue;
            }
            diff.add(string);
        }
        for(Map.Entry<String, Integer> entry : map.entrySet()) {
            if(entry.getValue() == 1) {
                diff.add(entry.getKey());
            }
        }
        return diff;
    }

}
