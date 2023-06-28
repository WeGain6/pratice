package test;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @ClassName: Test
 * @Description:
 * @Author: XuWei
 * @Date: 2022-10-29 21:26
 */
public class Demo01 {

    @Test
    public void testListRemove() {
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("1");
        list.add("2");
        list.add("3");

        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()){
            String str = iterator.next();
            if ("1".equals(str)){
                iterator.remove();
            }
        }

        System.out.println(list);
    }

}
