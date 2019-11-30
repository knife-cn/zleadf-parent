package com.zlead.service;

import com.zlead.entity.MemberEntity;
import com.zlead.entity.ProductEntity;
import com.zlead.entity.ShopEntity;
import com.zlead.util.page.PageBounds;
import com.zlead.util.page.PageList;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public interface SupplierService {
    PageList<ProductEntity> getPage(Map params, PageBounds rowBounds, MemberEntity memberEntity);
    boolean supplierProductSave(ProductEntity productEntity, MemberEntity memberEntity, HttpServletRequest request);
    ShopEntity supplierShopInfo(MemberEntity memberEntity);
}
