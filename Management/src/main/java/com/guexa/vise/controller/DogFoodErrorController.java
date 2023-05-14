
package com.guexa.vise.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Guexa
 */
@Controller
public class DogFoodErrorController implements ErrorController
{
    private static final Logger LOGGER = LoggerFactory.getLogger(DogFoodErrorController.class);
    
    public String getErrorPath()
    {
        return "/error";
    }
    
    @RequestMapping("/error")
    public String handleError(HttpServletRequest request, Model model)
    {
        LOGGER.trace("Entering the method handleError...");
        LOGGER.info("Handling HTTP error...");
        
        String pageTitle = "Error";
        String errorPage = "error";
        
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        
        if(status != null)
        {
            Integer statusCode = Integer.valueOf(status.toString());
            
            if(statusCode == HttpStatus.NOT_FOUND.value())
            {
                pageTitle = "Page Not Found";
                errorPage = "error/404";
                
                LOGGER.error("Error 404...");
            }
            else if(statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value())
            {
                pageTitle = "Internal Server Error";
                errorPage = "error/500";
                
                LOGGER.error("Error 500...");
            }
            else if(statusCode == HttpStatus.FORBIDDEN.value())
            {
                pageTitle = "Forbidden Error";
                errorPage = "error/403";
                
                LOGGER.error("Error 403...");
            }
        }
        model.addAttribute("pageTitle", pageTitle);
        
        return errorPage;
    }
}
