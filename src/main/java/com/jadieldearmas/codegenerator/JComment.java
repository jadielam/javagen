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
 * This class is the Java model for a java comment. It is abstract because
 * it will support two types of comments:
 * -javadoc comment
 * -regular comment
 * 
 * Every class that extends this class needs to call the constructor defined here.
 * @author jdearmas
 *
 * @since 1.0
 */
public abstract class JComment extends JavaTemplateGroup {

	/**
	 * Constructor that will be called by the classes
	 * extending this class.
	 * @param templateName
	 */
	JComment(String templateName) {
		super(templateName);
		
	}

	//Keep it like this.
}
