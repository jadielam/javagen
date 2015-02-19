// **************************************************
//
// Modification Tracking:
// $Revision:$
// $LastChangedBy:$
// $LastChangedDate:$
//
// **************************************************
package com.jadieldearmas.codegenerator;

import java.util.List;
import java.util.Map;

import org.stringtemplate.v4.ST;


/**
 * @author jdearmas
 *
 * @since 0.1
 */
public class JClass extends JavaTemplateGroup {

	private final String className;
	/**
	 * 
	 * 
	 * 
	 * @param className the name of the class
	 * @param vissibilityModifier the visibility of the class
	 * @param interfaces the class implements
	 * @param extendedClass class the class extends
	 */
	JClass(String className, String vissibilityModifier, 
			String extendedClass, List<String> interfaces){
		
		super();
		this.className = className;
		this.template = super.getInstanceOf("class");
		this.template.add("name", className);
		this.template.add("vissibilityModifier", vissibilityModifier);
		
		if (null != extendedClass) {
			ST extendsTemplate = super.getInstanceOf("extends");
			extendsTemplate.add("extendedClass", extendedClass);
			this.template.add("extends", extendsTemplate);
		}
		
		if (null != interfaces && 0 < interfaces.size()){
			ST implementsTemplate = super.getInstanceOf("inplements");
			
			for (String iMterface : interfaces){
				implementsTemplate.add("interface", iMterface);
			}
			this.template.add("inplements", implementsTemplate);
		}
	}
	
	public JClass setPublic(){
		
		return this;
	}
	
	public JClass setPrivate(){
		
		return this;
		
	}
	
	public JClass setProtected(){
	
		return this;
	}
	
	public JClass addSuperclass(String extendedClass){
		
		return this;
	}
	
	public JClass addInterface(String iMterface){
		//interesting challenge here.
		return this;
	}
	
	public JConstructor addConstructor(String vissibilityModifier, 
			List<Pair<String, String>> someFunctionArguments){
		
		JConstructor constructor = new JConstructor(this.className,
				vissibilityModifier, someFunctionArguments);
		
		this.addNewChildTemplate("constructor", constructor);
		
		return constructor;
	}
	
	public JField addField(String type, String name, String vissibilityModifier,
			boolean isFinal, boolean isStatic){
		
		JField field = new JField(type, name, vissibilityModifier,
				isFinal, isStatic);
		
		this.addNewChildTemplate("field", field);
		
		return field;
	}
	
	public JMethod addMethod(String methodName, String vissibilityModifier, String returnType,
			boolean isFinal, boolean isSynchronized, boolean isStatic,
			List<Pair<String, String>> someFunctionArguments, List<String> exceptions){
		
		JMethod method = new JMethod(methodName, vissibilityModifier, returnType,
				isFinal, isSynchronized, isStatic, someFunctionArguments, exceptions);
		
		this.addNewChildTemplate("method", method);
		
		return method;
	}
	
	public JAnnotation addAnnotation(String name, List<Pair<String, String>> attributeValues){
		
		JAnnotation annotation = new JAnnotation(name, attributeValues);
		
		this.addNewChildTemplate("annotation", annotation);
		
		return annotation;
	}
	

	public JComment addComment() throws UnsupportedOperationException{
		//TODO
		throw new UnsupportedOperationException("Operation not yet supported");
	}
	
	
}
