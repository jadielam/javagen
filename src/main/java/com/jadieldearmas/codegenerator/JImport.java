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
		
		super();
		this.template = super.getInstanceOf("inport");
		this.template.add("importName", importName);
	}
}
