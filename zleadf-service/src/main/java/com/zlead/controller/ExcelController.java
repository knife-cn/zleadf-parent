package com.zlead.controller;

import com.zlead.service.readExcelFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/zlead/excel")
public class ExcelController {

    @Autowired
    private readExcelFileService readExcelFileService;


    //导入excel
    @RequestMapping(value = "/impagent", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> impagent(@RequestParam(value = "file", required = false) MultipartFile file, HttpServletRequest request, HttpServletResponse response) {

        String originalFilename = file.getOriginalFilename();
        Map<String, Object> map = new HashMap<String, Object>();
        System.out.println(file);
        readExcelFileService.importAgentFile(file);
        return map;
    }

    @RequestMapping(value = "/importFactory", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> importFactoryExcel(@RequestParam(value = "file", required = false) MultipartFile file, HttpServletRequest request, HttpServletResponse response) {

        String originalFilename = file.getOriginalFilename();
        Map<String, Object> map = new HashMap<String, Object>();
        System.out.println(file);
        readExcelFileService.readFacFile(file);
        return map;
    }


}
