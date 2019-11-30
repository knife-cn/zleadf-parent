package com.zlead.dao;

import com.zlead.entity.ProdCatEntity;

import java.util.List;

public interface ProdCatDao {
    void insert(ProdCatEntity prodCatEntity);

    List<ProdCatEntity> selectAllByProdId(Long prodId);
}
