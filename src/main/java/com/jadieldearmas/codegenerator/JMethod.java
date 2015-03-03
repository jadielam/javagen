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
 * Java model for method of Java class
 * 
 * @since 1.0  
 */
public class JMethod extends JavaTemplateGroup {

	/**
	 * True if the body of the method is already added. 
	 * False if not. TODO: Should be done away with.
	 */
	private boolean isBodyAdded = false;
	
	/**
	 * THe name of the method
	 */
	private final String methodName;
	
	/**
	 * The Java type of the returned object
	 */
	private final String returnType;
	
	/**
	 * The visibility modifier of the method
	 */
	private String vissibilityModifier;
	
	/**
	 * True if method is final. false otherwise
	 */
	private boolean isFinal = false;
	
	/**
	 * True if method is final. False otherwise
	 */
	private boolean isStatic = false;
	
	/**
	 * True if method is synchronized. False otherwise.
	 */
	private boolean isSynchronized = false;
	
	/**
	 * List of function arguments to be added
	 */
	private List<Pair<String, String>> functionArguments = new ArrayList<Pair<String, String>>();
	
	/**
	 * The names of the exceptions that this method throws
	 */
	private Set<String> exceptions = new HashSet<String>();
	
	/**
	 * The text of the body of the method
	 */
	private String textBody;
	
	
	/**
	 * Constructor that builds a new method model
	 * @param methodName the name of the method
	 * @param returnType the Java type of the object returned by the method
	 */
	JMethod(String methodName, String returnType){
		
		super("method");
		this.methodName = methodName;
		this.returnType = returnType;
		
	}
	
	/**
	 * Adds a new argument to the method
	 * @param argumentType the Java type of the argument
	 * @param argumentName the name of the argument
	 * @return Returns <code>this</code> JMethod
	 */
	public JMethod addArgument(String argumentType, String argumentName){
		this.functionArguments.add(new Pair<String, String>(argumentType, argumentName));
		return this;
	}
	
	/**
	 * Adds a new exception to the set of exceptions that this method throws.
	 * @param exceptionName The name of the class of the exception
	 * @return Returns <code>this</code> JMethod
	 */
	public JMethod addException(String exceptionName){
		this.exceptions.add(exceptionName);
		return this;
	}
	
	/**
	 * Sets the visibilit of the method to be public
	 * @return Returns <code>this</code> JMethod
	 */
	public JMethod setPublic(){
		this.vissibilityModifier = "public";
		return this;
	}
	
	/**
	 * Sets the visibility of the method to be private.
	 * @return Returns <code>this</code> JMethod
	 */
	public JMethod setPrivate(){
		this.vissibilityModifier = "private";
		return this;
	}
	
	/**
	 * Sets the visibility of the method to be protected
	 * @return Returns <code>this</code> JMethod
	 */
	public JMethod setProtected(){
		this.vissibilityModifier = "protected";
		return this;
	}
	
	/**
	 * Sets the visibility of the method to null, which is equivalent to package-protected.
	 * @return Returns <code>this</code> JMethod
	 */
	public JMethod setPackageProtected(){
		this.vissibilityModifier = null;
		return this;
	}
	
	/**
	 * Sets the method to final
	 * @return Returns <code>this</code> JMethod
	 */
	public JMethod setFinal(){
		this.isFinal = true;
		return this;
	}
	
	/**
	 * Removes the final modifier from the method.
	 * @return Returns <code>this</code> JMethod
	 */
	public JMethod unsetFinal(){
		this.isFinal = false;
		return this;
	}
	
	/**
	 * Sets the method to be static
	 * @return Returns <code>this</code> JMethod
	 */
	public JMethod setStatic(){
		this.isStatic = true;
		return this;
	}
	
	/**
	 * Removes the static modifier from the method
	 * @return Returns <code>this</code> JMethod
	 */
	public JMethod unsetStatic(){
		this.isStatic = false;
		return this;
	}
	
	/**
	 * Sets the method to be synchronized
	 * @return Returns <code>this</code> JMethod
	 */
	public JMethod setSynchronized(){
		this.isSynchronized = true;
		return this;
	}
	
	/**
	 * Removes the synchronized modifier from the method
	 * @return Returns <code>this</code> JMethod
	 */
	public JMethod unsetSynchronized(){
		this.isSynchronized = false;
		return this;
	}
	
	/**
	 * Adds a body to the method as text.
	 * TODO: Check the issues. Refer to the comments in the equivalent method
	 * in JConstructor
	 * @see JConstructor#setTextBody(String)
	 * @param bodyText The text of the body that is added
	 * @return Returns <code>this</code> JMethod
	 * @throws UnsupportedOperationException whenever the body has already being added.
	 */
	public JMethod setTextBody(String bodyText) throws UnsupportedOperationException{
		if (!this.isBodyAdded){
			this.textBody = bodyText;
			return this;
		}
		
		throw new UnsupportedOperationException("The body of the constructor has already been added");
		
	}
	
	/**
	 * Adds coded body model to the method
	 * @return Returns the coded body model just created
	 * @throws UnsupportedOperationException if the body has already being added to the method.
	 */
	public JCodedBody addCodedBody() throws UnsupportedOperationException{
		if (!this.isBodyAdded){
			JCodedBody body = new JCodedBody();
			
			this.addNewChildTemplate("body", body);
			this.isBodyAdded = true;
			this.textBody = null;
			return body;
		}
		
		throw new UnsupportedOperationException("The body of the constructor has been added already.");
	}
	
	/**
	 * Adds annotation to the function
	 * @param name The name of the annotation
	 * @return the Annotation model just created.
	 */
	public JAnnotation addAnnotation(String name){
		
		JAnnotation annotation = new JAnnotation(name);
		
		this.addNewChildTemplate("annotation", annotation);
		
		return annotation;
	}

	/**
	 * Adds comment to the function
	 * @return the comment model just created
	 * TODO: Revise why I have to return JComment.
	 * @throws UnsupportedOperationException whenever the method is called, because
	 * the method is not yet supported.
	 */
	public JComment addComment() throws UnsupportedOperationException{
		//TODO
		throw new UnsupportedOperationException("Operation not yet supported");
	}
	
	/**
	 * @see JavaTemplateGroup#compile()
	 */
	@Override
	public void compile(){
		
		this.template.add("methodName", this.methodName);
		this.template.add("returnType", this.returnType);
		if (null != this.vissibilityModifier){
			this.template.add("vissibilityModifier", this.vissibilityModifier);
		}
		if (null != this.textBody){
			this.template.add("body", this.textBody);
		}
		
		if (this.isStatic){
			this.template.add("isStatic", "static");
		}
		if (this.isFinal){
			this.template.add("isFinal", "final");
		}
		if (this.isSynchronized){
			this.template.add("isSynchronized", "synchronized");
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
		
		if (null != this.exceptions && 0 < this.exceptions.size()){
			
			ST throwsTemplate = super.getInstanceOf("throws");
			
			for (String eCception : this.exceptions){
				throwsTemplate.add("exception", eCception);
			}
			
			this.template.add("throws", throwsTemplate.render());
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
				+ ((this.functionArguments == null) ? 0
						: this.functionArguments.hashCode());
		result = prime * result
				+ ((this.methodName == null) ? 0 : this.methodName.hashCode());
		return result;
	}

	/** 
	 * Equality based on function arguments and method name.
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
		if (!(obj instanceof JMethod)) {
			return false;
		}
		JMethod other = (JMethod) obj;
		if (this.functionArguments == null) {
			if (other.functionArguments != null) {
				return false;
			}
		} else if (!this.functionArguments.equals(other.functionArguments)) {
			return false;
		}
		if (this.methodName == null) {
			if (other.methodName != null) {
				return false;
			}
		} else if (!this.methodName.equals(other.methodName)) {
			return false;
		}
		return true;
	}

}
