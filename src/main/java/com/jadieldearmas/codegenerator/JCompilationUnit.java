// **************************************************
//
// Modification Tracking:
// $Revision:$
// $LastChangedBy:$
// $LastChangedDate:$
//
// **************************************************
package com.jadieldearmas.codegenerator;

import java.util.List;

/**
 * @author jdearmas
 *
 * @since  
 */
public class JCompilationUnit extends JavaTemplateGroup {

	private final String compilationUnitName;
	private boolean isPackageAdded = false;
	private boolean isClassAdded = false;
	
	JCompilationUnit(String compilationUnitName){
		
		super("compilationUnit");
		this.compilationUnitName = compilationUnitName;
			
	}
	
	public static JCompilationUnit createCompilationUnit(String compilationUnitName){
		return new JCompilationUnit(compilationUnitName);
	}
	
	public JPackage addPackage(String packageName) throws UnsupportedOperationException {
		if (!isPackageAdded){
			
			JPackage paKkage = new JPackage(packageName);
			
			this.addNewChildTemplate("package", paKkage);
			this.isPackageAdded = true;
			
			return paKkage;
			
		}
		else{
			throw new UnsupportedOperationException("The package has already been added to the compilation unit.");
		}
		
	}
	
	public JImport addImport(String importName){
		
		JImport iNport = new JImport(importName);
		
		this.addNewChildTemplate("inport", iNport);
		
		return iNport;
	}
	
	public JClass addClass(String className, String vissibilityModifier,
			String extendedClass, List<String> interfaces){
		//TODO: Fix this to make it more realistic to Java.
		//TODO: AKA: Make differentiation between public classes and other classes in the class
		//topology that I have designed.
		//TODO: Also, when making it more realistic to Java, check the issue of having a class
		//with a name different than the compilation unit name.
		if (!isClassAdded){
			
			JClass claZs = new JClass(className, vissibilityModifier, extendedClass,
					interfaces);
			
			this.addNewChildTemplate("class", claZs);
			this.isClassAdded = true;
			
			return claZs;
		}
		else{
			throw new UnsupportedOperationException("The class has already been added to the compilation unit.");
		}
		
	}
	
	public void addComment(JComment comment) throws UnsupportedOperationException{
		//TODO;
		throw new UnsupportedOperationException("Operation not supported yet.");
	}
}
