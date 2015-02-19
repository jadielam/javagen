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
 * This class is used to load a body template from a file
 * instead of having to write it programmatically.
 * @author jdearmas
 *
 * @since  
 */
public class JTemplatedBody extends JBody {

	JTemplatedBody(String pathToTemplate, 
			List<Pair<String, String>> placeholderValues){
		
		super();
		STGroupFile fileTemplate = new STGroupFile(pathToTemplate);
		this.template = fileTemplate.getInstanceOf("templatedBody");
		
		for (Pair<String, String> toReplace : placeholderValues){
			String placeholder = toReplace.getFirst();
			String value = toReplace.getSecond();
			this.template.add(placeholder, value);
		}
	}
	
}
