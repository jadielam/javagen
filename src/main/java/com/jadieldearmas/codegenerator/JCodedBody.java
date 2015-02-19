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
 * To be used for the body of functions that will be generated
 * programmatically, instead of from a template in a file.
 * @author jdearmas
 *
 * @since  
 */
public class JCodedBody extends JBody {

	JCodedBody(){
		super("body");
		
	}
	
	public JCodeUnit addCodeUnit() throws UnsupportedOperationException {
		//TODO
		throw new UnsupportedOperationException("Operation not yet supported");
	}
}
