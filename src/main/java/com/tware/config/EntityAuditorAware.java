package com.tware.config;

import java.util.Optional;

import com.tware.user.entity.User;
import com.tware.user.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;


@Component
public class EntityAuditorAware implements AuditorAware<Long> {
    @Autowired
    private UserService userService;
	
    @Override
    public Optional<Long> getCurrentAuditor() {
        Subject subject =null;
        try{
            subject = SecurityUtils.getSubject();
        }catch (Exception ex){

        }

        User user =null;// new User();
        if(subject!=null)
        {
        	 if(subject.isAuthenticated()) {
        		 
        		 if(subject.getPrincipals()!=null)
        		 {
        			 Object  obj= subject.getPrincipal();
        			  
        			  if(obj!=null && obj instanceof User)
        			  {

        				  user = (User)obj;
                          if(user!=null && user.getLoginUserid()!=null)
                          {
                              user.setId(new Long(user.getLoginUserid()));
                          }
        			  }
                     
                      
        		 }
               
             }
        }
        if(user!=null && user.getId()==null && userService!=null)
        {
           // user= userService.getUserByUsername(user.getUsername());

           // user.setId(107l);
        }
        if(user ==null && userService!=null)
        {
        	user= userService.getUserByUsername("admin");
        }
        else if(user ==null && userService==null)
        {
        	 return Optional.of(null);
        }
        
       if(user!=null)
       {
    	   return Optional.of(user.getId());
       }
       return Optional.of(null);
    }
}
