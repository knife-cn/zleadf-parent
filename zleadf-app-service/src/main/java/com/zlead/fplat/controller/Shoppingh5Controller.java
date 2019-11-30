package com.zlead.fplat.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "h5")
public class Shoppingh5Controller {

    @RequestMapping(value = "{path}", method = RequestMethod.GET)
    public ModelAndView buyCar(@PathVariable("path") String path) {
        return new ModelAndView("/views/h5/" + path);
    }
}
