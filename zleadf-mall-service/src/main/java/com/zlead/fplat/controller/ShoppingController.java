package com.zlead.fplat.controller;

import com.zlead.utils.RedisUtil;
import org.apache.http.HttpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "shopping")
public class ShoppingController {


	 @RequestMapping(value = {"/", "index"}, method = RequestMethod.GET)
	    public ModelAndView index() {
	        return new ModelAndView("/views/index");
	    }

	    @RequestMapping(value = "Associated_factory1", method = RequestMethod.GET)
	    public ModelAndView Associated_factory1() {
	        return new ModelAndView("/views/Associated_factory1");
	    }

	    @RequestMapping(value = "Associated_factory2", method = RequestMethod.GET)
	    public ModelAndView Associated_factory2() {
	        return new ModelAndView("/views/Associated_factory2");
	    }

	    @RequestMapping(value = "buyCar", method = RequestMethod.GET)
	    public ModelAndView buyCar() {
	        return new ModelAndView("/views/buyCar");
	    }

	    @RequestMapping(value = "buyCar_2", method = RequestMethod.GET)
	    public ModelAndView buyCar_2() {
	        return new ModelAndView("/views/buyCar_2");
	    }

	    @RequestMapping(value = "Change_Password1", method = RequestMethod.GET)
	    public ModelAndView Change_Password1() {
	        return new ModelAndView("/views/Change_Password1");
	    }

	    @RequestMapping(value = "collection", method = RequestMethod.GET)
	    public ModelAndView collection() {
	        return new ModelAndView("/views/collection");
	    }

	    @RequestMapping(value = "details", method = RequestMethod.GET)
	    public ModelAndView details() {
	        return new ModelAndView("/views/details");
	    }

	    @RequestMapping(value = "footer", method = RequestMethod.GET)
	    public ModelAndView footer() {
	        return new ModelAndView("/views/footer");
	    }

	    @RequestMapping(value = "head", method = RequestMethod.GET)
	    public ModelAndView head() {
	        return new ModelAndView("/views/head");
	    }

	    @RequestMapping(value = "Identity_verification1", method = RequestMethod.GET)
	    public ModelAndView Identity_verification1() {
	        return new ModelAndView("/views/Identity_verification1");
	    }

	    @RequestMapping(value = "Identity_verification2", method = RequestMethod.GET)
	    public ModelAndView Identity_verification2() {
	        return new ModelAndView("/views/Identity_verification2");
	    }

	    @RequestMapping(value = "Identity_verification3", method = RequestMethod.GET)
	    public ModelAndView Identity_verification3() {
	        return new ModelAndView("/views/Identity_verification3");
	    }

	    @RequestMapping(value = "Land", method = RequestMethod.GET)
	    public ModelAndView Land() {
	        return new ModelAndView("/views/Land");
	    }

	    @RequestMapping(value = "maLand", method = RequestMethod.GET)
	    public ModelAndView maLand() {
	        return new ModelAndView("/views/maLand");
	    }

	    @RequestMapping(value = "orderList", method = RequestMethod.GET)
	    public ModelAndView orderList() {
	        return new ModelAndView("/views/orderList");
	    }

	    @RequestMapping(value = "orderSuccess", method = RequestMethod.GET)
	    public ModelAndView orderSuccess() {
	        return new ModelAndView("/views/orderSuccess");
	    }

	    @RequestMapping(value = "Personal_information", method = RequestMethod.GET)
	    public ModelAndView Personal_information() {
	        return new ModelAndView("/views/Personal_information");
	    }

	    @RequestMapping(value = "Receiving_address", method = RequestMethod.GET)
	    public ModelAndView Receiving_address() {
	        return new ModelAndView("/views/Receiving_address");
	    }

	    @RequestMapping(value = "Receiving_address2", method = RequestMethod.GET)
	    public ModelAndView Receiving_address2() {
	        return new ModelAndView("/views/Receiving_address2");
	    }

	    @RequestMapping(value = "Receiving_address3", method = RequestMethod.GET)
	    public ModelAndView Receiving_address3() {
	        return new ModelAndView("/views/Receiving_address3");
	    }

	    @RequestMapping(value = "Receiving_address4", method = RequestMethod.GET)
	    public ModelAndView Receiving_address4() {
	        return new ModelAndView("/views/Receiving_address4");
	    }

	    @RequestMapping(value = "Receiving_address5", method = RequestMethod.GET)
	    public ModelAndView Receiving_address5() {
	        return new ModelAndView("/views/Receiving_address5");
	    }

	    @RequestMapping(value = "Receiving_address6", method = RequestMethod.GET)
	    public ModelAndView Receiving_address6() {
	        return new ModelAndView("/views/Receiving_address6");
	    }

	    @RequestMapping(value = "searchPage", method = RequestMethod.GET)
	    public ModelAndView searchPage() {
	        return new ModelAndView("/views/searchPage");
	    }

	    @RequestMapping(value = "searchWu", method = RequestMethod.GET)
	    public ModelAndView searchWu() {
	        return new ModelAndView("/views/searchWu");
	    }

	    @RequestMapping(value = "speedHead", method = RequestMethod.GET)
	    public ModelAndView speedHead() {
	        return new ModelAndView("/views/speedHead");
	    }

	    @RequestMapping(value = "underLine", method = RequestMethod.GET)
	    public ModelAndView underLine() {
	        return new ModelAndView("/views/underLine");
	    }

	    @RequestMapping(value = "upload", method = RequestMethod.GET)
	    public ModelAndView upload() {
	        return new ModelAndView("/views/upload");
	    }

	    @RequestMapping(value = "User_center", method = RequestMethod.GET)
	    public ModelAndView User_center() {
	        return new ModelAndView("/views/User_center");
	    }

	    @RequestMapping(value = "User_center2", method = RequestMethod.GET)
	    public ModelAndView User_center2() {
	        return new ModelAndView("/views/User_center2");
	    }

	    @RequestMapping(value = "User_center3", method = RequestMethod.GET)
	    public ModelAndView User_center3() {
	        return new ModelAndView("/views/User_center3");
	    }

	    @RequestMapping(value = "User_center4", method = RequestMethod.GET)
	    public ModelAndView User_center4() {
	        return new ModelAndView("/views/User_center4");
	    }

	    @RequestMapping(value = "User_center5", method = RequestMethod.GET)
	    public ModelAndView User_center5() {
	        return new ModelAndView("/views/User_center5");
	    }

	    @RequestMapping(value = "User_center6", method = RequestMethod.GET)
	    public ModelAndView User_center6() {
	        return new ModelAndView("/views/User_center6");
	    }
	    @RequestMapping(value = "bottom", method = RequestMethod.GET)
	    public ModelAndView bottom() {
	        return new ModelAndView("/views/bottom");
	    }
	    @RequestMapping(value = "left", method = RequestMethod.GET)
	    public ModelAndView left() {
	        return new ModelAndView("/views/left");
	    }
	    @RequestMapping(value = "storeDetails", method = RequestMethod.GET)
	    public ModelAndView storeDetails() {
	        return new ModelAndView("/views/storeDetails");
	    }

	    @RequestMapping(value = "actDetails", method = RequestMethod.GET)
	    public ModelAndView actDetails(HttpServletRequest request) {
	        return new ModelAndView("/views/actDetails");
	    }

	    @RequestMapping(value = "activityDetails", method = RequestMethod.GET)
	    public ModelAndView activityDetails() {
	        return new ModelAndView("/views/activityDetails");
	    }
		@RequestMapping(value = "xiaoxi", method = RequestMethod.GET)
		public ModelAndView xiaoxi() {
			return new ModelAndView("/views/xiaoxi");
		}
		@RequestMapping(value = "xiaoxi2", method = RequestMethod.GET)
		public ModelAndView xiaoxi2() {
			return new ModelAndView("/views/xiaoxi2");
		}
		@RequestMapping(value = "editDetail", method = RequestMethod.GET)
		public ModelAndView editDetail() {
			return new ModelAndView("/views/editDetail");
		}

		@RequestMapping(value = "register", method = RequestMethod.GET)
		public ModelAndView register() {
		return new ModelAndView("/views/register");
	}
}
