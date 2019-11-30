package com.zlead.entity;

public class CatEntity {
    /**
     * 字段名称:  .
     * 字段定义: t_cat.id
     *
     * @ET
     */
    private Long id;

    /**
     * 字段名称: ID路径，如：1,2,3 .
     * 字段定义: t_cat.id_path
     *
     * @ET
     */
    private String idPath;

    /**
     * 字段名称: 根节点ID .
     * 字段定义: t_cat.root_id
     *
     * @ET
     */
    private Long rootId;

    /**
     * 字段名称: 父节点ID .
     * 字段定义: t_cat.parent_id
     *
     * @ET
     */
    private Long parentId;

    /**
     * 字段名称: 分类名称 .
     * 字段定义: t_cat.name
     *
     * @ET
     */
    private String name;

    /**
     * 字段名称: 排序 .
     * 字段定义: t_cat.sort_id
     *
     * @ET
     */
    private Long sortId;

    /**
     * 字段名称: 级别 .
     * 字段定义: t_cat.level
     *
     * @ET
     */
    private Integer level;

    /**
     * 字段名称: 是否为叶子节点 .
     * 字段定义: t_cat.is_leaf
     *
     * @ET
     */
    private Integer isLeaf;

    /**
     * 字段名称: 分类图片 .
     * 字段定义: t_cat.img_path
     *
     * @ET
     */
    private String imgPath;

    /**
     * 字段名称: 是否显示在首页 .
     * 字段定义: t_cat.if_home
     *
     * @ET
     */
    private Boolean ifHome;

    /**
     * This method:getId
     * t_cat.id
     *
     * @return the value of t_cat.id
     *
     * @ET
     */
    public Long getId() {
        return id;
    }

    /**
     * This method:setId
     *  t_cat.id
     *
     * @param id the value for t_cat.id
     *
     * @ET
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method:getIdPath
     * t_cat.id_path
     *
     * @return the value of t_cat.id_path
     *
     * @ET
     */
    public String getIdPath() {
        return idPath;
    }

    /**
     * This method:setIdPath
     *  t_cat.id_path
     *
     * @param idPath the value for t_cat.id_path
     *
     * @ET
     */
    public void setIdPath(String idPath) {
        this.idPath = idPath == null ? null : idPath.trim();
    }

    /**
     * This method:getRootId
     * t_cat.root_id
     *
     * @return the value of t_cat.root_id
     *
     * @ET
     */
    public Long getRootId() {
        return rootId;
    }

    /**
     * This method:setRootId
     *  t_cat.root_id
     *
     * @param rootId the value for t_cat.root_id
     *
     * @ET
     */
    public void setRootId(Long rootId) {
        this.rootId = rootId;
    }

    /**
     * This method:getParentId
     * t_cat.parent_id
     *
     * @return the value of t_cat.parent_id
     *
     * @ET
     */
    public Long getParentId() {
        return parentId;
    }

    /**
     * This method:setParentId
     *  t_cat.parent_id
     *
     * @param parentId the value for t_cat.parent_id
     *
     * @ET
     */
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    /**
     * This method:getName
     * t_cat.name
     *
     * @return the value of t_cat.name
     *
     * @ET
     */
    public String getName() {
        return name;
    }

    /**
     * This method:setName
     *  t_cat.name
     *
     * @param name the value for t_cat.name
     *
     * @ET
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method:getSortId
     * t_cat.sort_id
     *
     * @return the value of t_cat.sort_id
     *
     * @ET
     */
    public Long getSortId() {
        return sortId;
    }

    /**
     * This method:setSortId
     *  t_cat.sort_id
     *
     * @param sortId the value for t_cat.sort_id
     *
     * @ET
     */
    public void setSortId(Long sortId) {
        this.sortId = sortId;
    }

    /**
     * This method:getLevel
     * t_cat.level
     *
     * @return the value of t_cat.level
     *
     * @ET
     */
    public Integer getLevel() {
        return level;
    }

    /**
     * This method:setLevel
     *  t_cat.level
     *
     * @param level the value for t_cat.level
     *
     * @ET
     */
    public void setLevel(Integer level) {
        this.level = level;
    }

    /**
     * This method:getIsLeaf
     * t_cat.is_leaf
     *
     * @return the value of t_cat.is_leaf
     *
     * @ET
     */
    public Integer getIsLeaf() {
        return isLeaf;
    }

    /**
     * This method:setIsLeaf
     *  t_cat.is_leaf
     *
     * @param isLeaf the value for t_cat.is_leaf
     *
     * @ET
     */
    public void setIsLeaf(Integer isLeaf) {
        this.isLeaf = isLeaf;
    }

    /**
     * This method:getImgPath
     * t_cat.img_path
     *
     * @return the value of t_cat.img_path
     *
     * @ET
     */
    public String getImgPath() {
        return imgPath;
    }

    /**
     * This method:setImgPath
     *  t_cat.img_path
     *
     * @param imgPath the value for t_cat.img_path
     *
     * @ET
     */
    public void setImgPath(String imgPath) {
        this.imgPath = imgPath == null ? null : imgPath.trim();
    }

    /**
     * This method:getIfHome
     * t_cat.if_home
     *
     * @return the value of t_cat.if_home
     *
     * @ET
     */
    public Boolean getIfHome() {
        return ifHome;
    }

    /**
     * This method:setIfHome
     *  t_cat.if_home
     *
     * @param ifHome the value for t_cat.if_home
     *
     * @ET
     */
    public void setIfHome(Boolean ifHome) {
        this.ifHome = ifHome;
    }
}