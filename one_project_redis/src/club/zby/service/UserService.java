package club.zby.service;
import java.util.List;

import club.zby.entity.Admin;
import club.zby.entity.Information;
import club.zby.entity.User;
import club.zby.entity.AddUser;
public interface UserService {
	
	List<User> queryUser();
	List<Admin> queryAdmin(Admin admin);
	List<Information> queryInformation(Information information);
	void updateuser(String name,Information information);
	List<AddUser> queryAddUser(AddUser adduser);
	boolean adduserd(Admin admin);
	List<Admin> UserLogin(Admin admin);
}
