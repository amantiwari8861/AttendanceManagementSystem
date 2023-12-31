package com.nobious.bean;

public class Admin {
	
	private int id;
	private String name;
	private Role role;
	
	public Admin() {
		super();
	}
	public Admin(int id, String name, Role role) {
		super();
		this.id = id;
		this.name = name;
		this.role = role;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	@Override
	public String toString() {
		return "Admin [id=" + id + ", name=" + name + ", role=" + role + "]";
	}
	
	
}

