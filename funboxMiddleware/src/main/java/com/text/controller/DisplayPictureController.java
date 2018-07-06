package com.text.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.text.Dao.DisplayPictureDao;
import com.text.model.DisplayPicture;
import com.text.model.ErrorClazz;

@Controller
public class DisplayPictureController {
	@Autowired
	private DisplayPictureDao displayPictureDao;
	@RequestMapping(value="/uploaddisplaypic",method=RequestMethod.POST)
        public ResponseEntity<?> uploadDisplayPicture(@RequestParam CommonsMultipartFile image,HttpSession session){
		String email=(String)session.getAttribute("email");
		if(email==null){
		ErrorClazz errorClazz=new ErrorClazz(7,"Unauthorized access..please login");
		return new ResponseEntity<ErrorClazz>(errorClazz,HttpStatus.UNAUTHORIZED);

		}
		DisplayPicture displayPicture=new DisplayPicture();
		displayPicture.setEmail(email);
		displayPicture.setImage(image.getBytes());
		displayPictureDao.saveDisplayPicture(displayPicture);
		return new ResponseEntity<DisplayPicture>(displayPicture,HttpStatus.OK);
	}
	
	@RequestMapping(value="/getimage/{email:.+}",method=RequestMethod.GET)
   public @ResponseBody byte[] getImage(@PathVariable String email,HttpSession session){
	   String auth=(String)session.getAttribute("email");
	   if(auth==null)
		   return null;//src attribute
	    DisplayPicture displayPicture=displayPictureDao.getDisplayPicture(email);
	    if(displayPicture==null)
	    	return null;
	    return displayPicture.getImage();

	   
   }
	
}