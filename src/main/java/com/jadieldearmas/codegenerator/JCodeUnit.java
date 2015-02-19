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
 * This class represents any java code that can be legally written inside of a 
 * Java block (blocks can be written inside of blocks).
 * @author jdearmas
 *
 * @since  
 */
public abstract class JCodeUnit extends JavaTemplateGroup {

	/**
	 * @param templateName
	 */
	JCodeUnit(String templateName) {
		super(templateName);
	
	}

	//I think that it should be empty on purpose.
}
