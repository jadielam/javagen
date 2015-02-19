// **************************************************
//
// Modification Tracking:
// $Revision:$
// $LastChangedBy:$
// $LastChangedDate:$
//
// **************************************************
package com.jadieldearmas.codegenerator;

/**
 * Superclass of the two kind of bodies that we can create:
 * 1. Code-generated body.
 * 2. Template-generated body.
 * 
 * @author jdearmas
 *
 * @since  
 */
public abstract class JBody extends JavaTemplateGroup {

	/**
	 * @param templateName
	 */
	JBody(String templateName) {
		super(templateName);
		
	}

	//I think that I should leave it empty intentionally.
}
