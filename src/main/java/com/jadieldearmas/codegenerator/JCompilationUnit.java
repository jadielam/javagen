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
import java.util.Set;

import org.stringtemplate.v4.ST;

/**
 * Java model class for a Java compilation unit.
 * @author jdearmas
 *
 * @since 1.0
 */
public class JCompilationUnit extends JavaTemplateGroup {

	/**
	 * THe name of the compilation unit, which will be at the end
	 * the name of the file that is created.
	 */
	private final String compilationUnitName;
	
	/**
	 * A set with all the imports that are added to the compilation unit.
	 */
	private Set<String> imports = new HashSet<String>();
	
	/**
	 * Package of this compilation unit
	 */
	private String packageName = null;
	
	/**
	 * Private constructor of a compilation unit model.	
	 * @param compilationUnitName the name of the compilation unit
	 */
	private JCompilationUnit(String compilationUnitName){
		
		super("compilationUnit");
		this.compilationUnitName = compilationUnitName;
	}
	
	/**
	 * Static factory method of a compilation unit.
	 * @param compilationUnitName
	 * @return
	 */
	public static JCompilationUnit createCompilationUnit(String compilationUnitName){
		return new JCompilationUnit(compilationUnitName);
	}
	
	
	/**
	 * Sets the package of the compilation unit.
	 * @param packageName
	 * @return Returns <code>this</code> compilation unit model
	 */
	public JCompilationUnit setPackage(String packageName) {
		
		this.packageName = packageName;
		return this;
	}
	
	/**
	 * Adds an import statement to the compilation unit
	 * @param importName THe string with the package name and class (or asterisk)
	 * that will follow the word "import" in the source code that will be created.
	 * @return Returns <code>this</code> compilation unit.
	 */
	public JCompilationUnit addImport(String importName){
		
		this.imports.add(importName);
		return this;
	}
	
	/**
	 * Creates and adds a public class to this compilation unit
	 * @return Returns the class model that was created.
	 */
	public JClass createPublicClass(){
		
		//TODO: Also, when making it more realistic to Java, check the issue of having a class
		//with a name different than the compilation unit name.
		//TODO: Check the issue of adding different classes to this.
		
		JClass claZs = new JClass(this.compilationUnitName);
		claZs.setPublic();
		this.addNewChildTemplate("class", claZs);
		return claZs;
				
	}
	
	/**
	 * Adds a comment to the compilation unit
	 * @param comment The string that it is wanted to appear in the comment
	 * @return Returns <code>this</code> compilation unit.
	 * @throws UnsupportedOperationException whenever the method is called
	 * because it is not yet supported.
	 */
	public JCompilationUnit addComment(String comment) throws UnsupportedOperationException{
		//TODO
		throw new UnsupportedOperationException("Operation not supported yet.");
	}
	
	/**
	 * Compiles the compilation unit. (Valga la redundancia)
	 * @see JavaTemplateGroup#compile()
	 */
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
