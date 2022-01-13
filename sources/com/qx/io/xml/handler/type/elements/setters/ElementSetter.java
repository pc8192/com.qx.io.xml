package com.qx.io.xml.handler.type.elements.setters;

import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.Method;

import com.qx.io.xml.handler.XML_LexiconBuilder;
import com.qx.io.xml.handler.type.TypeBuilder;
import com.qx.io.xml.handler.type.TypeHandler;
import com.qx.io.xml.handler.type.XML_TypeCompilationException;
import com.qx.io.xml.parser.ObjectParsedScope;
import com.qx.io.xml.parser.ParsedScope;
import com.qx.io.xml.parser.XML_ParsingException;
import com.qx.io.xml.parser.XML_StreamReader;


public abstract class ElementSetter {

	public static abstract class Prototype {

		public abstract boolean matches(Class<?> fieldType);

		public abstract ElementSetter.Builder create(Method method);

	}

	public static abstract class Builder {



		protected String tag;

		protected boolean isBuilt0 = false;

		protected boolean isBuilt1 = false;



		/**
		 * 
		 * @param method
		 * @param tag
		 */
		public Builder(String tag) {
			super();
			this.tag = tag;
		}



		/**
		 * explore subTypes
		 * 
		 * @param contextBuilder
		 * @throws XML_TypeCompilationException
		 */
		public abstract void explore(XML_LexiconBuilder contextBuilder) throws XML_TypeCompilationException;


		/**
		 * 
		 * @param context
		 * 
		 * @return false if the initialization has been successful, true if build need to re-launched.
		 * 
		 * @throws XML_TypeCompilationException 
		 */
		public abstract boolean build0(XML_LexiconBuilder contextBuilder, TypeBuilder builder, boolean isVerbose) 
				throws XML_TypeCompilationException;


		public abstract boolean build1(XML_LexiconBuilder contextBuilder, TypeBuilder builder, boolean isVerbose) 
				throws XML_TypeCompilationException;


	}




	/**
	 *  the XML tag for mapping purposes
	 */
	private final String tag;


	private final boolean isFieldTag;


	public ElementSetter(String tag, boolean isFieldTag) {
		super();
		this.tag = tag;
		this.isFieldTag = isFieldTag;
	}

	public String getTag() {
		return tag;
	}


	/**
	 * Tells whether tag is the name of field or the name of a type (implying a specific field).
	 * @return true if tag is a field name, false otherwise.
	 */
	public boolean isFieldTag() {
		return isFieldTag;
	}



	public abstract ParsedScope createParsedElement(ObjectParsedScope parent, XML_StreamReader.Point point) throws XML_ParsingException;



	public final static ElementSetter.Prototype[] PROTOTYPES = new ElementSetter.Prototype[] {
			BooleanElementSetter.PROTOTYPE,
			ShortElementSetter.PROTOTYPE,
			IntegerElementSetter.PROTOTYPE,
			LongElementSetter.PROTOTYPE,
			FloatElementSetter.PROTOTYPE,
			DoubleElementSetter.PROTOTYPE,
			StringElementSetter.PROTOTYPE,
			ObjectElementSetter.PROTOTYPE
	};

	/**
	 * <p>We assume that all dependencies has been resolved at this point</p>
	 * 
	 * @param context
	 * @param method
	 * @param factory
	 * @return
	 * @throws XML_TypeCompilationException
	 */
	public static ElementSetter.Builder create(Method method) 
			throws XML_TypeCompilationException {

		Class<?>[] parameters = method.getParameterTypes();
		if(parameters.length!=1){
			throw new XML_TypeCompilationException("Illegal number of parameters for a setter");
		}

		Class<?> fieldType = parameters[0];

		for(ElementSetter.Prototype prototype : PROTOTYPES) {
			if(prototype.matches(fieldType)) {
				return prototype.create(method);
			}
		}

		throw new XML_TypeCompilationException("Failed to match setter for: "+method);
	}


	public abstract Method getMethod();

	public abstract void xsd_write(Writer writer) throws IOException;

	public abstract void DTD_writeHeader(Writer writer) throws IOException;

	public abstract void DTD_writeFieldDefinition(TypeHandler typeHandler, Writer writer) throws IOException;


}
