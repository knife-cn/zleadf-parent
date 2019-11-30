package com.zlead.fplat.controller;

import com.zlead.fplat.service.CrmPrdListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "crmprdlist")
public class CrmPrdListController {
    @Autowired
    private CrmPrdListService crmPrdListService;

    @RequestMapping(value = "findallnamelist", method = RequestMethod.GET)
    public List<Map<String, Object>> findAllNameList() {
        return crmPrdListService.findAllNameList();
    }

}
