package com.qx.io.xml.parser;

import java.io.IOException;

import com.qx.io.xml.handler.XML_Lexicon;

/**
 * 
 * @author pc
 *
 */
public class XML_Parser {

	public boolean isVerbose;
	
	
	private XML_StreamReader reader;

	private RootParsedElement rootScope;
	public ParsedScope scope;

	public XML_Parser(XML_Lexicon context, XML_StreamReader reader, boolean isVerbose) {
		super();
		this.reader = reader;
		this.isVerbose = isVerbose;
		rootScope = new RootParsedElement(context);
	}

	/**
	 * 
	 * @return
	 * @throws XML_ParsingException 
	 * @throws IOException 
	 * @throws Exception
	 */
	public Object parse() throws XML_ParsingException, IOException {
		scope = rootScope;
		while(scope!=null){
			scope.parse(this, reader);
		}
		return rootScope.getRootObject();
	}


}
