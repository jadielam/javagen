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
 * 2. Text-set body.
 * 
 * @author jdearmas
 *
 * @since 0.1
 */
public abstract class JBody extends JavaTemplateGroup {

	/**
	 * @param templateName this is the name of the body template in the 
	 * spring template grammar files.
	 */
	JBody(String templateName) {
		super(templateName);
		
	}

	//I think that I should leave it empty intentionally.
}
