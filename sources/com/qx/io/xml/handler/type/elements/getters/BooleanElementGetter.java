package com.qx.io.xml.handler.type.elements.getters;

import java.lang.reflect.Method;

import com.qx.io.xml.composer.ObjectComposableScope;
import com.qx.io.xml.composer.PrimitiveComposableElement.BooleanComposableElement;

public class BooleanElementGetter extends PrimitiveElementGetter {
	
	public final static Prototype PROTOTYPE = new Prototype() {
		
		@Override
		public boolean matches(Method method) {
			Class<?> type = method.getReturnType();
			if(type==boolean.class && method.getParameterCount()==0){
				return true;
			}
			else {
				return false;
			}
		}
		
		@Override
		public ElementGetter.Builder create(Method method) {
			return new Builder(method);
		}
	};
	
	
	public static class Builder extends PrimitiveElementGetter.Builder {
		
		public Builder(Method method) {
			super(method);
		}

		@Override
		public PrimitiveElementGetter createGetter() {
			return new BooleanElementGetter(fieldTag, method);
		}
	}
	

	public BooleanElementGetter(String tag, Method method) {
		super(tag, method);
	}

	@Override
	public void createComposableElement(ObjectComposableScope scope) throws Exception {
		boolean value = (boolean) method.invoke(scope.getObject(), new Object[]{});
		scope.append(new BooleanComposableElement(fieldTag, value));
	}

}
