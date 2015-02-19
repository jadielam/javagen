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

import org.stringtemplate.v4.ST;



/**
 * @author jdearmas
 *
 * @since  
 */
public class JConstructor extends JavaTemplateGroup {
	
	private boolean isBodyAdded = false;
	/**
	 * 
	 * @param className
	 * @param vissibilityModifier
	 * @param someAttributeValues
	 */
	JConstructor(String className, String vissibilityModifier,
			List<Pair<String, String>> someFunctionArguments){
		
		super();
		this.template = super.getInstanceOf("constructor");
		this.template.add("className", className);
		this.template.add("vissibilityModifier", vissibilityModifier);
		for (Pair<String, String> argument : someFunctionArguments){
			ST functionArgumentTemplate = super.getInstanceOf("functionArgument");
			String argumentType = argument.getFirst();
			String argumentName = argument.getSecond();
			functionArgumentTemplate.add("argumentType", argumentType);
			functionArgumentTemplate.add("argumentName", argumentName);
			this.template.add("functionArgument", functionArgumentTemplate);
		}
		
	}
	
	/**
	 * The body of the Constructor can be added only once.
	 * @param body of the constructor
	 * @throws UnsupportedOperationException thrown whenever the body
	 * of this Constructor has already been added.
	 */
	public JTemplatedBody addTemplatedBody(String pathToTemplate,
			List<Pair<String, String>> placeHolderValues) throws UnsupportedOperationException{
		if (!this.isBodyAdded){
			JTemplatedBody body = new JTemplatedBody(pathToTemplate,
					placeHolderValues);
			
			this.addNewChildTemplate("body", body);
			this.isBodyAdded = true;
			
			return body;
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
			
			return body;
		}
		else{
			throw new UnsupportedOperationException("The body of the constructor has been added already.");
		}
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
