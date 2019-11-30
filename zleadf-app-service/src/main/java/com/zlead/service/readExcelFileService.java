package com.zlead.service;

import org.springframework.web.multipart.MultipartFile;

public interface readExcelFileService {
    String readAgentFile(MultipartFile file);
    
    String readFacFile(MultipartFile file);

    String importAgentFile(MultipartFile file);
    
    String readProdFile(MultipartFile file);

  //  String readFactoryExcelFile(MultipartFile file);
}
