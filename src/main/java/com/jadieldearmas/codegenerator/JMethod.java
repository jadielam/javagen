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
public class JMethod extends JavaTemplateGroup {

	private boolean isBodyAdded = false;
	
	JMethod(String methodName, String vissibilityModifier, String returnType,
			boolean isFinal, boolean isSynchronized, boolean isStatic, 
			List<Pair<String, String>> someFunctionArguments, List<String> exceptions){
		
		//TODO: Check that some items cannot be null
		super("method");
		
		this.updatePlaceholder("methodName", methodName);
		this.updatePlaceholder("vissibilityModifier", vissibilityModifier);
		this.updatePlaceholder("returnType", returnType);
		if (isFinal) this.updatePlaceholder("isFinal", "final");
		if (isSynchronized) this.updatePlaceholder("isSynchronized", "synchronized");
		if (isStatic) this.updatePlaceholder("isStatic", "static");
		
		for (Pair<String, String> argument : someFunctionArguments){
			ST functionArgumentTemplate = super.getInstanceOf("functionArgument");
			String argumentType = argument.getFirst();
			String argumentName = argument.getSecond();
			functionArgumentTemplate.add("argumentType", argumentType);
			functionArgumentTemplate.add("argumentName", argumentName);
			this.updatePlaceholder("functionArgument", functionArgumentTemplate.render());
		}
		
		
		if (null != exceptions && 0 < exceptions.size()){
			ST throwsTemplate = super.getInstanceOf("throws");
			
			for (String eCception : exceptions){
				throwsTemplate.add("exception", eCception);
			}
			this.updatePlaceholder("throws", throwsTemplate.render());
		}
	}
	
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
