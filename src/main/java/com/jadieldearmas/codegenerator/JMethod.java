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
public class JMethod extends JavaTemplateGroup {

	private boolean isBodyAdded = false;
	
	private final String methodName;
	private final String returnType;
	private String vissibilityModifier;
	private boolean isFinal = false;
	private boolean isStatic = false;
	private boolean isSynchronized = false;
	private List<Pair<String, String>> functionArguments = new ArrayList<Pair<String, String>>();
	private Set<String> exceptions = new HashSet<String>();
	private String textBody;
	
		
	JMethod(String methodName, String returnType){
		
		super("method");
		this.methodName = methodName;
		this.returnType = returnType;
		
	}
	
	public JMethod addArgument(String argumentType, String argumentName){
		this.functionArguments.add(new Pair<String, String>(argumentType, argumentName));
		return this;
	}
	
	public JMethod addException(String exceptionName){
		this.exceptions.add(exceptionName);
		return this;
	}
	
	public JMethod setPublic(){
		this.vissibilityModifier = "public";
		return this;
	}
	
	public JMethod setPrivate(){
		this.vissibilityModifier = "private";
		return this;
	}
	
	public JMethod setProtected(){
		this.vissibilityModifier = "protected";
		return this;
	}
	
	public JMethod setPackageProtected(){
		this.vissibilityModifier = null;
		return this;
	}
	
	public JMethod setFinal(){
		this.isFinal = true;
		return this;
	}
	
	public JMethod unsetFinal(){
		this.isFinal = false;
		return this;
	}
	
	public JMethod setStatic(){
		this.isStatic = true;
		return this;
	}
	
	public JMethod unsetStatic(){
		this.isStatic = false;
		return this;
	}
	
	public JMethod setSynchronized(){
		this.isSynchronized = true;
		return this;
	}
	
	public JMethod unsetSynchronized(){
		this.isSynchronized = false;
		return this;
	}
	
	public JMethod setTextBody(String bodyText) throws UnsupportedOperationException{
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

	
	public JComment addComment() throws UnsupportedOperationException{
		//TODO
		throw new UnsupportedOperationException("Operation not yet supported");
	}
	
	/**
	 * 
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
			
			for (String eCception : exceptions){
				throwsTemplate.add("exception", eCception);
			}
			
			this.template.add("throws", throwsTemplate.render());
		}
			
		super.compile();
	}

	/* (non-Javadoc)
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

	/* (non-Javadoc)
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
