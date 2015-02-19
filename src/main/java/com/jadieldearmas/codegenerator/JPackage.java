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
		
		super();
		this.template = super.getInstanceOf("package");
		this.template.add("packageName", packageName);
	}
}
