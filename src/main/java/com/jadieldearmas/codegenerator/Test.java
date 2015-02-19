// **************************************************
//
// Modification Tracking:
// $Revision:$
// $LastChangedBy:$
// $LastChangedDate:$
//
// **************************************************

package com.jadieldearmas.codegenerator;

import java.util.ArrayList;
import java.util.List;

import org.stringtemplate.v4.*;



/**
 * @author jdearmas
 *
 * @since  
 */
public class Test {

	public static transient final List<String> test = new ArrayList<String>();
	
	public static void main(String [] args){
		
		
		JCompilationUnit compilationUnit = JCompilationUnit.createCompilationUnit("Testing");
		compilationUnit.addPackage("default");
		compilationUnit.addImport("java.util.List");
		JClass klass = compilationUnit.addClass("Testing", "public", null, null);
		
		klass.addConstructor("public", new ArrayList<Pair<String, String>>());
		JField nameField = klass.addField("String", "name", "private", false, false);
		List<Pair<String, String>> attributeValues = new ArrayList<Pair<String, String>>();
		attributeValues.add(new Pair<String, String>("name", "Jadiel"));
		nameField.addAnnotation("Entity", attributeValues);
		compilationUnit.addImport("javax.persistence.Entity");
		klass.addMethod("getName", "public", "String", false, false, false, new ArrayList<Pair<String, String>>(), new ArrayList<String>());
		
		System.out.println(compilationUnit.toString());		
		
	}
	
	
}
