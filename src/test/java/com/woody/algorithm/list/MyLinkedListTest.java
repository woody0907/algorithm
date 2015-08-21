package com.woody.algorithm.list;

import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: woody
 * Date: 15-8-19
 * Time: 下午4:59
 * To change this template use File | Settings | File Templates.
 */

public class MyLinkedListTest extends TestCase {

    MyLinkedList<String> list;
    @Before
    public void setUp(){
        list = new MyLinkedList<String>();
    }

    @org.junit.Test
    public void test_add_size_remove(){
        list.add(0,"0");
        assertEquals("0",list.get(0));
        assertEquals(1,list.size());
        String removeValue = list.remove(0);
        assertEquals("0",removeValue);
        assertEquals(0,list.size());
    }

}
