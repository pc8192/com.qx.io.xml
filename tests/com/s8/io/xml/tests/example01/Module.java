package com.s8.io.xml.tests.example01;

import com.qx.io.xml.annotations.XML_GetAttribute;
import com.qx.io.xml.annotations.XML_SetAttribute;
import com.qx.io.xml.annotations.XML_Type;


public @XML_Type(name="module", sub={}) class Module {

	public int hiddenValue;
	
	private double b = 5.0;
	
	public @XML_SetAttribute(name="b2") void setFactor2(double b){
		this.b = b;
	}
	
	public @XML_GetAttribute(tag="b2") double getFactor2(){
		return b;
	}
}
