package com.kingja.cardpackage.db;

import java.util.List;

/**
 * 项目名称：物联网城市防控(警用版)
 * 类描述：TODO
 * 创建人：KingJA
 * 创建时间：2016/4/11 13:49
 * 修改备注：
 */
public interface DbDao {
    public<T> T selectFirst(Class<T> clazz, String key, String value);

    public<T> T selectFirst(Class<T> clazz, String key1, String value1, String key2, String value2);

    public<T> List<T> selectAllWhere(Class<T> clazz, String key, String value);

    public<T> List<T> selectAll(Class<T> clazz);

    public<T> List<T> selectAllAndOrder(Class<T> clazz, String order);

    public<T> List<T> selectAllWhereAndOrder(Class<T> clazz, String key, String value, String order, boolean desc);

    public<T> void saveOrUpdate(T t);

    public<T> void deleteById(Class<T> clazz, String id);

    public<T> void deleteAll(Class<T> clazz);

    public<T> List<T> selectAllWhereNotEquil(Class<T> clazz, String key, String value, String key2, String value2);


    public<T> List<T> selectAllWhereLike(Class<T> clazz, String key, String value);
    public<T> List<T> selectAllWheres(Class<T> clazz, String key, String value, String key2, String value2);


}
