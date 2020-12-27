package com.udacity.jwdnd.course1.cloudstorage.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/error")
public class ErrorController implements org.springframework.boot.web.servlet.error.ErrorController {

    @GetMapping()
    public ModelAndView renderErrorPage(HttpServletRequest httpRequest) {

        ModelAndView errorPage = new ModelAndView("error");
        String errorCode = "";
        String errorMsg = "";
        int httpErrorCode = getErrorCode(httpRequest);

        switch (httpErrorCode) {
            case 400: {
                errorCode = "400";
                errorMsg = "Http Error, Bad Request";
                break;
            }
            case 401: {
                errorCode = "401";
                errorMsg = "Http Error, Unauthorized";
                break;
            }
            case 404: {
                errorCode = "404";
                errorMsg = "Http Error, Resource not found";
                break;
            }
            case 500: {
                errorCode = "500";
                errorMsg = "Http Error, Internal Server Error";
                break;
            }
        }
        errorPage.addObject("errorCode", errorCode);
        errorPage.addObject("errorMsg", errorMsg);
        return errorPage;
    }

    private int getErrorCode(HttpServletRequest httpRequest) {
        return (Integer) httpRequest
                .getAttribute("javax.servlet.error.status_code");
    }

    @Override
    public String getErrorPath() {
        return "error";
    }
}
