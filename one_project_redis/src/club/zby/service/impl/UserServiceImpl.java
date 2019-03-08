package club.zby.service.impl;

import java.util.List;



import club.zby.dao.UserDao;
import club.zby.dao.impl.UserDaoImpl;
import club.zby.entity.User;
import club.zby.entity.Admin;
import club.zby.entity.Information;
import club.zby.entity.AddUser;

import club.zby.service.UserService;

public class UserServiceImpl implements UserService{
	UserDao userDao = new UserDaoImpl();
		
	public List<User> queryUser() {
		// TODO Auto-generated method stub
		return userDao.queryAllUser();
	}

	public List<Admin> queryAdmin(Admin admin) {
		// TODO Auto-generated method stub
		return userDao.queryAllAdmin(admin);
	}

	public List<Information> queryInformation(Information information) {
		// TODO Auto-generated method stub
		return userDao.queryAllUserInformation(information);
	}
	public void updateuser(String name, Information information) {
		userDao.updateuser(name, information);
	} 
	public List<AddUser> queryAddUser(AddUser adduser) {
		// TODO Auto-generated method stub
		return userDao.queryAddUser(adduser);
	}

	public boolean adduserd(Admin admin) {
		// TODO Auto-generated method stub

		userDao.adduserd(admin);
		return true ;
		
	}

	public List<Admin> UserLogin(Admin admin) {
		// TODO Auto-generated method stub
		return userDao.userlogin(admin);
	}


}