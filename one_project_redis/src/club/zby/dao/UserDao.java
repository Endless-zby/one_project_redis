package club.zby.dao;

import java.util.List;

import club.zby.entity.AddUser;
import club.zby.entity.Admin;
import club.zby.entity.Information;
import club.zby.entity.User;




public interface UserDao {

	 List<User> queryAllUser();
	 List<Information> queryAllUserInformation(Information information);
	 List<Admin> queryAllAdmin(Admin admin); 
	 void updateuser(String name,Information information);
	 List<AddUser> queryAddUser(AddUser addUser);
	 void adduserd(Admin admin);
	 List<Admin> userlogin(Admin admin); 
	 
}
