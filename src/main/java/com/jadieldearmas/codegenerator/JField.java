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

	JField(String type, String name, String vissibilityModifier,
			boolean isFinal, boolean isStatic){
		
		super();
		this.template = super.getInstanceOf("field");
		this.template.add("type", type);
		this.template.add("name", name);
		this.template.add("vissibilityModifier", vissibilityModifier);
		if (isFinal) this.template.add("isFinal", "final");
		if (isStatic) this.template.add("isStatic", "static");
		
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
