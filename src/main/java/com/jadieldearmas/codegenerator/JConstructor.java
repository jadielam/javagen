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
import java.util.List;

import org.stringtemplate.v4.ST;



/**
 * Java model for a class constructor
 * @author jdearmas
 *
 * @since  
 */
public class JConstructor extends JavaTemplateGroup {
	
	/**
	 * True if the body of the constructor is already added.
	 * False if not. TODO: Should be done away with.
	 */
	private boolean isBodyAdded = false;
	
	/**
	 * The name of the constructor
	 */
	private final String constructorName;
	
	/**
	 * The vissibility modifier of the constructor.
	 * It can take the values: "public", "private" or <code>null</code>
	 */
	private String vissibilityModifier;
	
	/**
	 * The text body of the constructor.
	 * TODO: Do away with it, and simply add a Body template to this thing.
	 */
	private String textBody;
	
	/**
	 * The function arguments of the constructor
	 */
	private List<Pair<String, String>> functionArguments = new ArrayList<Pair<String, String>>();
	
	
	/**
	 * The constructor of the constructor model
	 * @param className The name of the class to which this constructor belongs.
	 */
	JConstructor(String className){
		
		super("constructor");
		this.constructorName = className;
	}
	
	/**
	 * Sets the constructor to be public.
	 * @return Returns <code>this</code> constructor model.
	 */
	public JConstructor setPublic(){
		this.vissibilityModifier = "public";
		return this;
	}
	
	/**
	 * Sets the constructor to be private
	 * @return Returns <code>this</code> constructor model.
	 */
	public JConstructor setPrivate(){
		this.vissibilityModifier = "private";
		return this;
	}
	
	/**
	 * Sets the constructor to be protected.
	 * @return Returns <code>this</code> constructor model.
	 */
	public JConstructor setProtected(){
		this.vissibilityModifier = "protected";
		return this;
	}
	
	/**
	 * Sets the constructor to be package-protected.
	 * @return Returns <code>this</code> constructor model.
	 */
	public JConstructor setPackageProtected(){
		this.vissibilityModifier = null;
		return this;
	}
	
	/**
	 * Adds an argument to the constructor.
	 * @param argumentType The Java type of the argument
	 * @param argumentName The name/identifier of the argument
	 * @return Returns <code>this</code> constructor model.
	 */
	public JConstructor addArgument(String argumentType, String argumentName){
		this.functionArguments.add(new Pair<String, String>(argumentType, argumentName));
		return this;
	}
	
	/**
	 * This method adds a body to the constructor as text.
	 * TODO: Remove the throwing of exception and just replace the previous body of the
	 * constructor. I don't know. This one looks complex.
	 * 
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
	
	/**
	 * Creates a new coded body model.
	 * @return Returns the coded body model just created
	 * @throws UnsupportedOperationException if a body already exists.
	 */
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
	
	/**
	 * Adds a new annotation to the constructor.
	 * @param name The name of the annotation
	 * @return Returns the annotation that was created. 
	 */
	public JAnnotation addAnnotation(String name){
		
		JAnnotation annotation = new JAnnotation(name);
		this.addNewChildTemplate("annotation", annotation);
		return annotation;
	}

	/**
	 * Adds a new comment to the constructor
	 * @param comment The string of the comment.
	 * @return The comment that was created
	 * @throws UnsupportedOperationException whenever the method is called, because
	 * this operation is not yet supported.
	 */
	public JComment addComment(String comment) throws UnsupportedOperationException{
		//TODO
		throw new UnsupportedOperationException("Operation not yet supported");
	}
	
	/**
	 * Adds a javadoc style comment to the constructor
	 * @return Returns the comment that was created
	 * @throws UnsupportedOperationException whenever the method is called, because
	 * this operation is not yet supported.
	 */
	public JComment addJavadocComment() throws UnsupportedOperationException {
		//TODO
		throw new UnsupportedOperationException("Operation not yet supported");
	}
	
	/**
	 * @see JavaTemplateGroup#compile()
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
	 * 
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
