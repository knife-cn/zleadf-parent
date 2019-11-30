package com.zlead.dao;

import com.zlead.entity.ArticleEntity;
import com.zlead.util.page.PageBounds;
import com.zlead.util.page.PageList;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-08-09 15:53:48
 */
public interface ArticleDao {

    void insert(ArticleEntity entity);

    void update(ArticleEntity entity);

    void delete(Long id);

    PageList<ArticleEntity> findPage(Map params,PageBounds rowBounds);

    ArticleEntity findById(Long id);

    List<ArticleEntity> getList(Map params);

    List<ArticleEntity> getBannerList(Map params);

    int count();

    int updateArticleLikes(@Param("id") Long id,@Param("status") Boolean status);

    int updateArticleHits(Long id);
}
