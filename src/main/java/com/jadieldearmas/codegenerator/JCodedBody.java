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
 * Model for Java body that will be used for the body of functions that will be generated
 * programmatically, instead of from a template in a file.
 * @author jdearmas
 *
 * @since  
 */
public class JCodedBody extends JBody {

	/**
	 * Creates a new Coded Body model
	 */
	JCodedBody(){
		super("body");
		
	}
	
	/**
	 * Adds new code unit model to body
	 * @return Returns the code unit model just created.
	 * @throws UnsupportedOperationException whenever the method is called, because 
	 * the functionality is not yet supported.
	 */
	public JCodeUnit addCodeUnit() throws UnsupportedOperationException {
		//TODO
		throw new UnsupportedOperationException("Operation not yet supported");
	}
}
