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
public class JPackage extends JavaTemplateGroup {

	JPackage(String packageName){
		
		super("package");
		
		this.updatePlaceholder("packageName", packageName);
	}
}
