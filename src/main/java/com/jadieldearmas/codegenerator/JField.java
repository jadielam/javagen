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
		
		super("field");
		
		this.updatePlaceholder("type", type);
		this.updatePlaceholder("name", name);
		this.updatePlaceholder("vissibilityModifier", vissibilityModifier);
		
		if (isFinal) this.updatePlaceholder("isFinal", "final");
		if (isStatic) this.updatePlaceholder("isStatic", "static");
		
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
