package com.rosydawn.mockito;

import static org.mockito.Mockito.*;

import java.util.LinkedList;

/**
 * Created by Vincent on 2017/10/16.
 */
public class UsingStubWithMockito {
    public static void main(String[] args) {
        // 你可以mock一个具体的类，而不仅只是接口
        LinkedList mockedList = mock(LinkedList.class);

        // 测试桩
        when(mockedList.get(0)).thenReturn("first");
        when(mockedList.get(1)).thenThrow(new RuntimeException());

        // 输出“first”
        System.out.println(mockedList.get(0));

        // 抛出异常
        System.out.println(mockedList.get(1));

        // 因为get(999) 没有保留存根，因此输出null
        System.out.println(mockedList.get(999));

        //Although it is possible to verify a stubbed invocation, usually it's just redundant
        //If your code cares what get(0) returns then something else breaks (often before even verify() gets executed).
        //If your code doesn't care what get(0) returns then it should not be stubbed. Not convinced? See here.
        // 验证get(0)被调用的次数
        verify(mockedList).get(0);
    }
}
