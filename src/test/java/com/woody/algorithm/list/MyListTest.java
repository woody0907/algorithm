package com.woody.algorithm.list;

import junit.framework.Assert;
import junit.framework.Test;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Rule;

import java.util.Iterator;

import static  junit.framework.Assert.*;

/**
 * Created with IntelliJ IDEA.
 * User: woody
 * Date: 15-8-18
 * Time: 下午3:25
 * To change this template use File | Settings | File Templates.
 */
public class MyListTest extends TestCase {

    MyArrayList<String> list;
    @Rule
    public org.junit.rules.ExpectedException exception = org.junit.rules.ExpectedException.none();

    @Before
    public void setUp(){
        list = new MyArrayList<String>();
    }


    /**
     * the MyArrayList will maintain the underlying array,
     * the array capacity,and the current number of items
     * stored the the MyArrayList
     * two version of add
     * isEmpty
     */
    @org.junit.Test
    public void test_add_capacity_size_remove_isEmpty(){
        list.add(0,"0");
        assertEquals("0",list.get(0));
        list.add(1,"1");
        list.add("3");
        assertEquals("3",list.get(2));
        String res = list.remove(0);
        assertEquals("0",res);
        assertFalse(list.isEmpty());
        list.remove(0);
        list.remove(0);
        assertTrue(list.isEmpty());
        assertEquals(list.size(), 0);
    }

    /**
     * the MyArray will provide a mechanism to change the
     * capacity of the underlying array.
     * the capacity is changed by obtaining a new array,
     * copying the old array into the new array, and allowing
     * the Virtual Machine to reclaim the old array
     *
     * implement set \ get
     */
    @org.junit.Test
    public void test_capacity_get_set(){
        try {
            for (int i = 0; i < 100; i++) {
                list.add(i,i+"");
            }
            assertEquals(100,list.size());
            assertEquals("99",list.get(99));
            assertEquals("99", list.set(99, "99set"));
            assertEquals("99set", list.get(99));
//            list.get(100);
        } catch (ArrayIndexOutOfBoundsException e) {
            fail("array out");
        }
    }

    /**
     * implements the Iterator interface,store the
     * index of the next item in the iteration
     * sequence and provide next, hasNext, remove
     */
      @org.junit.Test
    public void test_iterable_next_hasNext(){
          assertFalse(list.iterator().hasNext());
          list.add("0");
          list.add("1");
          list.add("2");
          list.add("3");
          Iterator iterator = list.iterator();
          assertEquals("0", iterator.next());
          assertEquals("1", iterator.next());
          assertEquals("2", iterator.next());
          assertEquals("3", iterator.next());

     }


}
