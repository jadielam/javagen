// **************************************************
//
// Modification Tracking:
// $Revision:$
// $LastChangedBy:$
// $LastChangedDate:$
//
// **************************************************
package com.jadieldearmas.codegenerator;

import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroupFile;

import java.util.List;



/**
 * This class is used to set the body text from a String
 * instead of doing it programmatically.
 * 
 * Use it as a last resort.
 * 
 * @author jdearmas
 *
 * @since  
 */
public class JTextBody extends JBody {

	private final String bodyText;
	JTextBody(String text){
		
		super("body");
		this.bodyText = text;
		
	}
	
	/**
	 * 
	 */
	@Override
	public void compile(){
		this.template.add("body", this.bodyText);
	}
	
}
