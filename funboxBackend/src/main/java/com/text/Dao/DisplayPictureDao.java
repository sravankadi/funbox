package com.text.Dao;

import com.text.model.DisplayPicture;

public interface DisplayPictureDao {
	void saveDisplayPicture(DisplayPicture displayPicture);
	DisplayPicture  getDisplayPicture(String email);
	

}