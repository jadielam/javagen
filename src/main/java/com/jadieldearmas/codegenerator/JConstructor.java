// **************************************************
//
// Modification Tracking:
// $Revision:$
// $LastChangedBy:$
// $LastChangedDate:$
//
// **************************************************
package com.jadieldearmas.codegenerator;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.stringtemplate.v4.ST;



/**
 * @author jdearmas
 *
 * @since  
 */
public class JConstructor extends JavaTemplateGroup {
	
	private boolean isBodyAdded = false;
	
	private final String constructorName;
	
	private String vissibilityModifier;
	
	private String textBody;
	
	private List<Pair<String, String>> functionArguments = new ArrayList<Pair<String, String>>();
	
	
	/**
	 * 
	 * @param className
	 * @param vissibilityModifier
	 * @param someAttributeValues
	 */
	JConstructor(String className){
		
		super("constructor");
		this.constructorName = className;
	}
	
	public JConstructor setPublic(){
		this.vissibilityModifier = "public";
		return this;
	}
	
	public JConstructor setPrivate(){
		this.vissibilityModifier = "private";
		return this;
	}
	
	public JConstructor setProtected(){
		this.vissibilityModifier = "protected";
		return this;
	}
	
	public JConstructor setPackageProtected(){
		this.vissibilityModifier = null;
		return this;
	}
	
	public JConstructor addArgument(String argumentType, String argumentName){
		this.functionArguments.add(new Pair<String, String>(argumentType, argumentName));
		return this;
	}
	
	/**
	 * The body of the Constructor can be added only once.
	 * @param body of the constructor
	 * @throws UnsupportedOperationException thrown whenever the body
	 * of this Constructor has already been added.
	 */
	public JConstructor setTextBody(String bodyText) throws UnsupportedOperationException{
		
		//TODO: Figure out a way in which I don't throw this exception.
		if (!this.isBodyAdded){
			this.textBody = bodyText;
			return this;
		}
		else{
			throw new UnsupportedOperationException("The body of the constructor has already been added");
		}
	}
	
	public JCodedBody addCodedBody() throws UnsupportedOperationException{
		if (!this.isBodyAdded){
			JCodedBody body = new JCodedBody();
			
			this.addNewChildTemplate("body", body);
			this.isBodyAdded = true;
			this.textBody = null;
			return body;
		}
		else{
			throw new UnsupportedOperationException("The body of the constructor has been added already.");
		}
	}
	
	public JAnnotation addAnnotation(String name){
		
		JAnnotation annotation = new JAnnotation(name);
		this.addNewChildTemplate("annotation", annotation);
		return annotation;
	}

	
	public JComment addComment(String comment) throws UnsupportedOperationException{
		//TODO
		throw new UnsupportedOperationException("Operation not yet supported");
	}
	
	public JComment addJavadocComment() throws UnsupportedOperationException {
		//TODO
		throw new UnsupportedOperationException("Operation not yet supported");
	}
	
	/**
	 * 
	 */
	@Override
	public void compile(){
		this.template.add("className", this.constructorName);
		this.template.add("vissibilityModifier", this.vissibilityModifier);
		if (null != this.textBody){
			this.template.add("body", this.textBody);
		}
		
		if (null != this.functionArguments && 0 < this.functionArguments.size()){
			for (Pair<String, String> argument : this.functionArguments){
				ST functionArgumentTemplate = super.getInstanceOf("functionArgument");
				String argumentType = argument.getFirst();
				String argumentName = argument.getSecond();
				functionArgumentTemplate.add("argumentType", argumentType);
				functionArgumentTemplate.add("argumentName", argumentName);
				this.template.add("functionArgument", functionArgumentTemplate.render());
			}
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
		int result = super.hashCode();
		result = prime
				* result
				+ ((this.functionArguments == null) ? 0 : this.functionArguments
						.hashCode());
		result = prime
				* result
				+ ((this.constructorName == null) ? 0 : this.constructorName
						.hashCode());
		return result;
	}

	/** 
	 * Equals based on fields constructorName and attributePairs.
	 * (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!super.equals(obj)) {
			return false;
		}
		if (!(obj instanceof JConstructor)) {
			return false;
		}
		JConstructor other = (JConstructor) obj;
		if (this.functionArguments == null) {
			if (other.functionArguments != null) {
				return false;
			}
		} else if (!this.functionArguments.equals(other.functionArguments)) {
			return false;
		}
		if (this.constructorName == null) {
			if (other.constructorName != null) {
				return false;
			}
		} else if (!this.constructorName.equals(other.constructorName)) {
			return false;
		}
		return true;
	}
	
	
}
