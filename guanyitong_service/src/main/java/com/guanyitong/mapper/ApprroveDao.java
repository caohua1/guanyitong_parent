package com.guanyitong.mapper;

import com.guanyitong.model.Apprrove;

import java.util.List;

/*
认证项DAO
 */
public interface ApprroveDao {
    /**
     * 分页查询认证项信息
     */
    public List<Apprrove> selectApprrove();



}
