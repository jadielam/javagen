// **************************************************
//
// Modification Tracking:
// $Revision:$
// $LastChangedBy:$
// $LastChangedDate:$
//
// **************************************************
package com.jadieldearmas.codegenerator;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.stringtemplate.v4.ST;

/**
 * @author jdearmas
 *
 * @since  
 */
public class JCompilationUnit extends JavaTemplateGroup {

	private final String compilationUnitName;
	
	private Set<String> imports = new HashSet<String>();
	
	/**
	 * Package of this compilation unit
	 */
	private String packageName = null;
	
		
	private JCompilationUnit(String compilationUnitName){
		
		super("compilationUnit");
		this.compilationUnitName = compilationUnitName;
	}
	
	public static JCompilationUnit createCompilationUnit(String compilationUnitName){
		return new JCompilationUnit(compilationUnitName);
	}
	
	
	/**
	 * Sets the package of the compilation unit.
	 * @param packageName
	 * @return
	 */
	public JCompilationUnit setPackage(String packageName) {
		
		this.packageName = packageName;
		return this;
	}
	
	public JCompilationUnit addImport(String importName){
		
		this.imports.add(importName);
		return this;
	}
	
	public JClass createPublicClass(){
		
		//TODO: Also, when making it more realistic to Java, check the issue of having a class
		//with a name different than the compilation unit name.
		//TODO: Check the issue of adding different classes to this.
		
		JClass claZs = new JClass(this.compilationUnitName);
		claZs.setPublic();
		this.addNewChildTemplate("class", claZs);
		return claZs;
				
	}
	
	public JCompilationUnit addComment(String comment) throws UnsupportedOperationException{
		//TODO
		throw new UnsupportedOperationException("Operation not supported yet.");
	}
	
	@Override
	public void compile(){
		
		if (null != this.packageName){
			ST packageTemplate = super.getInstanceOf("package");
			packageTemplate.add("packageName", this.packageName);
			this.template.add("package", packageTemplate.render());
		}
		
		if (null != this.imports && 0 < this.imports.size()){
			for (String inport : this.imports){
				ST importTemplate = super.getInstanceOf("inport");
				importTemplate.add("importName", inport);
				this.template.add("inport", importTemplate.render());
			}
		}
		
		super.compile();
	}
}
