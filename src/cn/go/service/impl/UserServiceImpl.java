package cn.go.service.impl;

import org.springframework.stereotype.Service;

import cn.go.model.User;
import cn.go.service.UserService;

@Service("userService")
public class UserServiceImpl extends BaseServiceImpl<User> implements
		UserService {

}
