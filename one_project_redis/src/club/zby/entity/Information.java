package club.zby.entity;

public class Information {
	private  String name;
	private  String password;
	private  String email;
	private	 String idname;
	private  String data;
	private  String age;
	private  String school;
	
	public Information() {
	}
	
	
	public Information(String name,String password,String email,String idname,String data,String age,String school) {
		this.name = name;
		this.password = password;
		this.email = email;
		this.idname = idname;
		this.data = data;
		this.age = age;
		this.school = school;
	}
	
	public  String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	public  String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	public  String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public  String getIdname() {
		return idname;
	}
	public void setIdname(String idname) {
		this.idname = idname;
	}
	public  String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}

	public  String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}

	
	public void setSchool(String school) {
		this.school = school;
	}
	public  String getSchool() {
		return school;
	}
	
	
}
	
	