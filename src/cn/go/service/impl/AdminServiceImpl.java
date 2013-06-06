package cn.go.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import cn.go.model.Admin;
import cn.go.service.AdminService;
import cn.go.util.SqlTool;

@Service("adminService")
public class AdminServiceImpl extends BaseServiceImpl<Admin> implements
		AdminService {

	@Override
	public Admin login(String uname, String passwd) {
		String sql = "select * from admin where uname=? and passwd=? limit 0,1";
		Object[] args = new Object[] { uname, passwd };
		SqlTool.showSqlAndArgs("login", sql, args);
		List<Admin> data = list(sql, null, args);
		if (data.size() == 0)
			return null;
		return data.get(0);
	}

}
