// **************************************************
//
// Modification Tracking:
// $Revision:$
// $LastChangedBy:$
// $LastChangedDate:$
//
// **************************************************
package com.jadieldearmas.codegenerator;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.stringtemplate.v4.ST;


/**
 * @author jdearmas
 *
 * @since 0.1
 */
public class JClass extends JavaTemplateGroup {

	private final String className;
	
	/**
	 * Can take values either "public" or "private"
	 */
	private String vissibilityModifier = null;
	
	/**
	 * The class that this class extends
	 */
	private String extendedClass = null;
	
	/**
	 * The interfaces that this class implements.
	 */
	private Set<String> interfaces = new HashSet<String>();
	/**
	 * 
	 * 
	 * 
	 * @param className the name of the class
	 * @param vissibilityModifier the visibility of the class
	 * @param interfaces the class implements
	 * @param extendedClass class the class extends
	 */
	JClass(String className){
		
		super("class");
		this.className = className;
		
	}
	
	public JClass setPublic(){
		
		this.vissibilityModifier = "public";
		return this;
	}
	
	public JClass setPrivate(){
		
		this.vissibilityModifier = "private";
		return this;
	}
	
		
	public JClass setSuperclass(String extendedClass){
		
		this.extendedClass = extendedClass;
		return this;
	}
	
	public JClass addInterface(String iMterface){
		
		this.interfaces.add(iMterface);
		return this;
	}
	
	public JConstructor addPublicConstructor(){
		
		JConstructor constructor = new JConstructor(this.className);
		constructor.setPublic();
		this.addNewChildTemplate("constructor", constructor);
		
		return constructor;
	}
	
	public JField addField(String type, String name, String vissibilityModifier,
			boolean isFinal, boolean isStatic){
		
		JField field = new JField(type, name);
		this.addNewChildTemplate("field", field);
		return field;
	}
	
	public JMethod addMethod(String methodName, String returnType){
		
		JMethod method = new JMethod(methodName, returnType);
		
		this.addNewChildTemplate("method", method);
		
		return method;
	}
	
	public JAnnotation addAnnotation(String name, List<Pair<String, String>> attributeValues){
		
		JAnnotation annotation = new JAnnotation(name);
		
		this.addNewChildTemplate("annotation", annotation);
		
		return annotation;
	}
	

	public JComment addComment() throws UnsupportedOperationException{
		//TODO
		throw new UnsupportedOperationException("Operation not yet supported");
	}
	
	/**
	 * compiles the template
	 */
	@Override
	public void compile(){
		
		this.template.add("name", this.className);
		this.template.add("vissibilityModifier", this.vissibilityModifier);
		if (null != this.extendedClass) {
			ST extendsTemplate = super.getInstanceOf("extends");
			extendsTemplate.add("extendedClass", this.extendedClass);
			this.template.add("extends", extendsTemplate.render());
		}
		
		if (null != this.interfaces && 0 < this.interfaces.size()){
			ST implementsTemplate = super.getInstanceOf("inplements");
			
			for (String iMterface : this.interfaces){
				implementsTemplate.add("interface", iMterface);
			}
			this.template.add("implements", implementsTemplate.render());
		}
		
		super.compile();
	}

	/** 
	 * (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = prime * ((this.className == null) ? 0 : this.className.hashCode());
		return result;
	}

	/** 
	 * (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		
		if (!(obj instanceof JClass)) {
			return false;
		}
		JClass other = (JClass) obj;
		if (this.className == null) {
			if (other.className != null) {
				return false;
			}
		} else if (!this.className.equals(other.className)) {
			return false;
		}
		return true;
	}
	
	
	
}
