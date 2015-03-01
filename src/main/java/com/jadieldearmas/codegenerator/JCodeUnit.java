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
 * This class is the Java model for any java expression that can be legally written inside of a 
 * Java block (blocks can be written inside of blocks).
 * 
 * The class is abstract and should be extended by all the expressions such as:
 * -if-else statements
 * -while loops
 * -declarations
 * -function calls
 * -etc. 
 * @author jdearmas
 *
 * @since  
 */
public abstract class JCodeUnit extends JavaTemplateGroup {

	/**
	 * Constructor for a new code unit.
	 * @param templateName
	 */
	JCodeUnit(String templateName) {
		super(templateName);
	
	}

	//I think that it should be empty on purpose.
}
