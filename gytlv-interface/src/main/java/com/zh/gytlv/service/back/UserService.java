package com.zh.gytlv.service.back;

import java.util.List;

import com.zh.gytlv.entity.Permission;
import com.zh.gytlv.entity.Role;
import com.zh.gytlv.entity.User;
import com.zh.gytlv.entity.Ztree;

public interface UserService {
	public List<User> getUserByLogin(User user);

	public int regUser(String username, String password);

	public User getUserByName(String userName);

	public List<Role> getUserRoles(String userid);

	public List<Permission> getPremission(String roleid);

	public List<Ztree> getNodes(String id);

	public List<Ztree> getAllNodes();

}
