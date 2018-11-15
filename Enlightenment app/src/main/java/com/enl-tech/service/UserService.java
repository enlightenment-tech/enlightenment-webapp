package com.enl-tech.service;

import com.enl-tech.model.User;

public interface UserService {
	//public User findUserByEmail(String email);
	//public void saveUser(User user);

	User login(email, password);
}
