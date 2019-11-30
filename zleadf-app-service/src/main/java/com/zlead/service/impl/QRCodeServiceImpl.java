package com.zlead.service.impl;

import com.zlead.dao.ShopDao;
import com.zlead.entity.MemberEntity;
import com.zlead.entity.ShopEntity;
import com.zlead.service.QRCodeService;
import com.zlead.utils.TwoDimensionCode;
import com.zlead.util.ProjectProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

/**
 * 生成二维码
 */
@Transactional
@Service
public class QRCodeServiceImpl implements QRCodeService {
    @Autowired
    private ShopDao shopDao;
    /**
     * 生成企业商城的二维码
     * @param request
     * @return
     */
    public String getTwoBarCode(HttpServletRequest request, MemberEntity memberEntity)
    {
        OutputStream out;
        String imgPath = null;
        try
        {
            //查询店铺信息
            ShopEntity shopEntity = shopDao.findByMemeberId(memberEntity.getMemberId());
            if(shopEntity!=null){
                String fileName = shopEntity.getId()+ ".png";
                String path2 = "upload";
                String srcPath = request.getSession().getServletContext().getRealPath(
                        File.separator + path2);
                imgPath = srcPath + "\\" + fileName;
                out = new FileOutputStream(srcPath + File.separator + fileName);
                BufferedImage image = new BufferedImage(148, 80, BufferedImage.TYPE_INT_RGB);
                ImageIO.write(image, "png", out);
                String encoderContent = ProjectProperties.companyAppAddress+shopEntity.getId();
                TwoDimensionCode handler = new TwoDimensionCode();
                handler.encoderQRCode(encoderContent, imgPath, "png");
                imgPath = path2 + File.separator + fileName;
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return imgPath;
    }
}
