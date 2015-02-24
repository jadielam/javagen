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



/**
 * @author jdearmas
 *
 * @since  
 */
public class JField extends JavaTemplateGroup {

	private final String type;
	private final String name;
	private String vissibilityModifier;
	private boolean isStatic = false;
	private boolean isFinal = false;
	
	JField(String type, String name){
		
		super("field");
		
		this.type = type;
		this.name = name;
		
	}
	
	
	public JField setPublic(){
		this.vissibilityModifier = "public";
		return this;
	}
	
	public JField setPrivate(){
		this.vissibilityModifier = "private";
		return this;
	}
	
	public JField setProtected(){
		this.vissibilityModifier = "protected";
		return this;
	}
	
	public JField setPackageProtected(){
		this.vissibilityModifier = null;
		return this;
	}
	
	public JField setFinal(){
		this.isFinal = true;
		return this;
	}
	
	public JField unsetFinal(){
		this.isFinal = false;
		return this;
	}
	
	public JField setStatic(){
		this.isStatic = true;
		return this;
	}
	
	public JField unsetStatic(){
		this.isStatic = false;
		return this;
	}
	
	
	public JAnnotation addAnnotation(String name) {
		
		JAnnotation annotation = new JAnnotation(name);
		this.addNewChildTemplate("annotation", annotation);
		return annotation;
	}
	
	public JComment addComment(String comment) throws UnsupportedOperationException{
		//TODO
		throw new UnsupportedOperationException("Operation not yet supported");
	}
	
	/**
	 * 
	 */
	@Override
	public void compile(){
		
		this.template.add("type", this.type);
		this.template.add("name", this.name);
		this.template.add("vissibilityModifier", this.vissibilityModifier);
		if (this.isStatic){
			this.template.add("isStatic", "static");
		}
		if (this.isFinal){
			this.template.add("isFinal", "final");
		}
		
		super.compile();
	}
	
}
