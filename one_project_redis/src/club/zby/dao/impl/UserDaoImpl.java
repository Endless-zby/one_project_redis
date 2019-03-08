package club.zby.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



import club.zby.entity.AddUser;
import club.zby.entity.Admin;
import club.zby.entity.Information;
import club.zby.entity.User;
import club.zby.dao.UserDao;

import club.zby.util.DBUtil;

public class UserDaoImpl implements UserDao{
	
	public List<User> queryAllUser() {
		List<User> users = new ArrayList<User>();		
		try {			
			ResultSet rs = DBUtil.showAdmin("select * from zby1",null);
			while(rs.next()){

				User user = new User(rs.getString("name"), rs.getString("password"),rs.getString("email"));
				users.add(user);	
			}
	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return users;
	}
	
	public List<Information> queryAllUserInformation(Information information) {
		List<Information> informations = new ArrayList<Information>();		
			
		try {			
			Object[] params = {information.getName()};
			System.out.println("!!!!!!!!"+information.getName());
			ResultSet rs = DBUtil.executeQuery("select * from zby1 where name = ?",params);
			while(rs.next()){

				Information user = new Information(rs.getString("name"), rs.getString("password"),rs.getString("email"),rs.getString("idname"),rs.getString("data"),rs.getString("age"),rs.getString("school"));
				System.out.println(rs.getString("name")+";"+rs.getString("password")+";"+rs.getString("email")+";"+rs.getString("idname")+";"+rs.getString("data")+";"+rs.getString("age")+";"+rs.getString("school"));
				informations.add(user);	
			}
	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return informations;
	}
	
	public List<Admin> queryAllAdmin(Admin admin) {
		List<Admin> users = new ArrayList<Admin>();
		// TODO Auto-generated method stub
		try {
			Object[] params = {admin.getName(),admin.getPassword()};
			ResultSet rs = DBUtil.executeQuery("select * from admin where name = ? and password = ?",params);
			while(rs.next()){
				Admin usersd = new Admin(rs.getString("name"), rs.getString("password"));
				users.add(usersd);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return users;
	}

	public void updateuser(String name,Information information) {
		try {
			Object[] params = {information.getSchool(),information.getAge(),information.getData(),information.getIdname(),information.getEmail(),information.getPassword(),name};
			DBUtil.executeUpdate("update zby1 set school = ?,age = ?,data = ?,idname = ?,email = ?,password = ? where name =?",params);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}

	public List<AddUser> queryAddUser(AddUser addUser) {
		// TODO Auto-generated method stub
		List<AddUser> addUsers = new ArrayList<AddUser>();
		// TODO Auto-generated method stub
		try {
			Object[] params = {addUser.getName()};
			ResultSet rs = DBUtil.executeQuery("select * from zby1 where name = ?",params);
			while(rs.next()){
				AddUser add = new AddUser(rs.getString("name"));
				addUsers.add(add);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return addUsers;
	}

	public void adduserd(Admin admin) {
		// TODO Auto-generated method stub
		try {
			Object[] params = {admin.getName(),	admin.getPassword()};
			DBUtil.executeUpdate("insert into zby1(name,password) values(?,?)",params);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<Admin> userlogin(Admin admin) {
		// TODO Auto-generated method stub
		List<Admin> logins = new ArrayList<Admin>();
		// TODO Auto-generated method stub
		try {
			Object[] params = {admin.getName(),admin.getPassword()};
			ResultSet rs = DBUtil.executeQuery("select * from zby1 where name = ? and password = ?",params);
			while(rs.next()){
				Admin login = new Admin(rs.getString("name"), rs.getString("password"));
				logins.add(login);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return logins;
	
	}
	
	
	
	
}

