package com.system.software.solutions.sts.controller;
 
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.system.software.solutions.sts.model.CustomEntity;
import com.system.software.solutions.sts.model.Main;
import com.system.software.solutions.sts.service.MainService;
 
@RestController
@RequestMapping("/json")
public class MainRestController {
  
 
    @Autowired
  private MainService mainService;
     
    //-------------------Retrieve All Users--------------------------------------------------------
   
    @RequestMapping(value = "/grid", method = RequestMethod.GET)
    public ResponseEntity<CustomEntity<Main>> listAllUsers(HttpServletRequest request,HttpServletResponse response) {
    	
    	CustomEntity<Main> response_=	mainService.getEntity(request,response);
        
        return new ResponseEntity<CustomEntity<Main>> (response_, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseEntity<Main> listAllUsers(HttpServletRequest request) {
    	long id= Long.parseLong(request.getParameter("id")); 
    	
      	String box =request.getParameter("box");
    	 
      	Main main=	mainService.findById(id);
      	
      	if (main==null) {
             System.out.println("User with id " + id + " not found");
             return new ResponseEntity<Main>(HttpStatus.NOT_FOUND);
         }
   
         main.setBox(box);
           
      	mainService.update(main); 
         return new ResponseEntity<Main>(main, HttpStatus.OK);
    }
    
  
   
}