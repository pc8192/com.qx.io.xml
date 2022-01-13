package com.s8.io.xml.tests.example02;

import com.qx.io.xml.annotations.XML_SetElement;
import com.qx.io.xml.annotations.XML_Type;

@XML_Type(name = "Type01Stage")
public class Type01Stage extends MyStage {
	
	public MyStageDesign design;
	
	@XML_SetElement(tag = "design")
	public void setDesign(Type01StageDesign design) {
		this.design = design;
	}
	

	@XML_Type(name = "Type01StageDesign")
	public static class Type01StageDesign extends MyStageDesign {
		
	}	
	
}
