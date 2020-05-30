package com.example.team.service;

public interface RedisService<T> {
    /**
     * set存数据
     *
     * @param key
     * @param value
     * @return
     */
    void set(String key, String value);

    /**
     * get获取数据
     *
     * @param key
     * @return
     */
    String get(String key);

    /**
     * 设置有效天数
     *
     * @param key
     * @param expire
     * @return
     */
    void setExpire(String key, long expire);

    void delete(String key);

}
