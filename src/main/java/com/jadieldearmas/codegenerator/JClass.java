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
import java.util.Set;

import org.stringtemplate.v4.ST;


/**
 * @author jdearmas
 * 
 * Model that represents a Java class
 *
 * @since 1.0
 */
public class JClass extends JavaTemplateGroup {

	/**
	 * The name of the class
	 */
	private final String className;
	
	/**
	 * The visibility of the class. Can take values either "public" or "private" or null.
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
	 * Constructor that creates a Class model. It takes the name of the class as parameter.
	 * @param className the name of the class
	 */
	JClass(String className){
		
		super("class");
		this.className = className;
		
	}
	
	/**
	 * makes the class public
	 * @return
	 */
	public JClass setPublic(){
		
		this.vissibilityModifier = "public";
		return this;
	}
	
	/**
	 * Makes the class private
	 * @return
	 */
	public JClass setPrivate(){
		
		this.vissibilityModifier = "private";
		return this;
	}
	
	
	/**
	 * Sets the name of the superclass that this class extends
	 * @param extendedClass the name of the extended super class.
	 * @return Returns <code>this</code> class model
	 */
	public JClass setSuperclass(String extendedClass){
		
		this.extendedClass = extendedClass;
		return this;
	}
	
	/**
	 * Adds an interface name to the list of interfaces implemented by this class.
	 * @param iMterface the name of the interface
	 * @return Returns <code>this<code> class model
	 */
	public JClass addInterface(String iMterface){
		
		this.interfaces.add(iMterface);
		return this;
	}
	
	/**
	 * Adds a public constructor to the class
	 * @return Returns the constructor model that was created
	 */
	public JConstructor addPublicConstructor(){
		
		JConstructor constructor = new JConstructor(this.className);
		constructor.setPublic();
		this.addNewChildTemplate("constructor", constructor);
		
		return constructor;
	}
	
	/**
	 * Adds a new field to the class.
	 * @param type The Java type of the new field
	 * @param name The identifier or name of the new field
	 * @return Returns the field model that was created
	 */
	public JField addField(String type, String name){
		
		JField field = new JField(type, name);
		this.addNewChildTemplate("field", field);
		return field;
	}
	
	/**
	 * Adds a new method to the class
	 * @param methodName the name of the method 
	 * @param returnType the return type of the method
	 * @return Returns the method model that was created
	 */
	public JMethod addMethod(String methodName, String returnType){
		
		JMethod method = new JMethod(methodName, returnType);
		
		this.addNewChildTemplate("method", method);
		
		return method;
	}
	
	/**
	 * Adds a new annotation to the class
	 * @param name the name of the annotation
	 * @return Returns the annotation model that was created.
	 */
	public JAnnotation addAnnotation(String name){
		
		JAnnotation annotation = new JAnnotation(name);
		
		this.addNewChildTemplate("annotation", annotation);
		
		return annotation;
	}
	
	
	/**
	 * Adds a new comment to the class
	 * @return the comment model that was created
	 * @throws UnsupportedOperationException at any time the method is called, because
	 * the method is not yet supported.
	 */
	public JComment addComment() throws UnsupportedOperationException{
		//TODO
		throw new UnsupportedOperationException("Operation not yet supported");
	}
	
	/**
	 * Compiles the class template.
	 */
	@Override
	void compile(){
		
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
	 * Implements equality based only on the className field.
	 * 
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
