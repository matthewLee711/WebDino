package com.interns.webdino.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("home")
public class HomeEndpoint {

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView showHomeView(
            HttpServletRequest req,
            HttpServletResponse res) throws Exception{

        ModelAndView mav = new ModelAndView();
        
        String i[] = {"blops"};
        mav.addObject("message", "Welcome to WebDino!");
        mav.addObject("red", i);
        mav.setViewName("home");
        

        return mav;

    }

}
