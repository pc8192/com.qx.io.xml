package com.s8.io.xml.tests.example01;


import com.qx.io.xml.annotations.XML_GetAttribute;
import com.qx.io.xml.annotations.XML_GetElement;
import com.qx.io.xml.annotations.XML_SetAttribute;
import com.qx.io.xml.annotations.XML_SetElement;
import com.qx.io.xml.annotations.XML_Type;

@XML_Type(name="user", sub={})
public class User extends TestClass3 {

	private String name = "none";
	
	private String password;

	private String note;
	
	public String getName() {
		return name;
	}

	@XML_SetAttribute(name="name")
	public void setName(String name) {
		this.name = name;
	}


	@XML_SetAttribute(name="password")
	public void setPassword(String password) {
		this.password = password;
	}
	
	@XML_GetAttribute(tag="password")
	public String getPassword() {
		return password;
	}
	
	@XML_SetElement(tag="note")
	public void setNote(String note){
		this.note = note;
	}
	
	@XML_GetElement(tag="note")
	public String getNote(){
		return note;
	}
}
