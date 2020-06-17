package com.example.mvvm.opengl2_0.comm;

/**
 * author : 90589
 * date   : 2020/6/16
 * desc   : 描述
 */
public interface OP<T> {
    /**
     * 添加
     * @param ts 若干对象
     */
    void add(T... ts);

    /**
     * 根据id移除元素
     * @param id 索引
     */
    void remove(int id);
}
