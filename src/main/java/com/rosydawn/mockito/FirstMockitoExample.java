package com.rosydawn.mockito;

// 静态导入会使代码更简洁
import static org.mockito.Mockito.*;

import java.util.List;

/**
 * Created by Vincent on 2017/10/16.
 */
public class FirstMockitoExample {
    public static void main(String[] args) {
        // 创建mock对象
        List mockedList = mock(List.class);

        // 使用mock对象
        mockedList.add("one");
        mockedList.clear();

        // 验证
        verify(mockedList).add("one");
        verify(mockedList).clear();
    }
}
