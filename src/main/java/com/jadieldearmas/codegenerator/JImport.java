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
 * @author jdearmas
 *
 * @since  
 */
public class JImport extends JavaTemplateGroup {

	JImport(String importName){
		
		super("inport");
		this.updatePlaceholder("importName", importName);
		
	}
}
