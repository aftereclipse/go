package cn.go.service;

import cn.go.model.Admin;

public interface AdminService extends BaseService<Admin> {
	public Admin login(String uname,String passwd);
}
