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
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.STGroupFile;

/**
 * @author jdearmas
 *
 * Parent class to all the different entities of the java grammar classes.
 * It is called JavaTemplateGroup because it contains a reference
 * to the String Template template group where all the different 
 * Java grammar classes such as JAnnotation, JBody, JComment, JMethod, etc.
 * are defined.
 * 
 * As a template class it sets the tone on how the API is used.  Each
 * class that extends this class behaves in the following way:
 * -It contains child templates that can be added during the lifetime of the class
 * -The class is in flux until the compile method is called.  Once the compile
 * method is called, any other changes to the class are ignored and do not make
 * it to the string representation of the class.
 * -The toString method prints a Java 7 compliant source representation
 * of the template.
 * @since 1.0  
 */
public abstract class JavaTemplateGroup {

	/**
	 * The template group from where all the templates are obtained
	 */
	private final STGroup javaGroup;
	
	/**
	 * Determines if the class is compiled or not.
	 */
	private boolean isCompiled = false;
	
	/**
	 * Key: is the name of the template placeholder in the java.stg file
	 * Value: is a list with all the templates that will be added to the placeholder in key.
	 * 
	 * The Key placeholder in the grammar description file must contain a separator.
	 * 
	 * For example, a class template will contain in this map a key by the value "method" and
	 * a list with all the method templates that belong to the class.
	 */
	private final Map<String, List<JavaTemplateGroup>> childTemplates = new HashMap<String, List<JavaTemplateGroup>>();
	
	/**
	 * The template of the class.
	 */
	final ST template;
	
	/**
	 * Constructor that uses the templateName to obtain the template from the template
	 * group.
	 * @param templateName the name of the template as it appears in the StringTemplate
	 * definition.
	 */
	JavaTemplateGroup(String templateName){
		
		this.javaGroup = new STGroupFile("java.stg");
		this.template = getInstanceOf(templateName);
	}
	
	/**
	 * Returns an instance of the template whose name is <code>template</code> and that
	 * appears in the template group.
	 * @param template the name of the template
	 * @return a StringTemplate
	 */
	public ST getInstanceOf(String template){
		return this.javaGroup.getInstanceOf(template);
	}
	
	/**
	 * Checks if the class is compiled or not
	 * @return true if it is compiled, false otherwise.
	 */
	boolean isCompiled(){
		return this.isCompiled;
	}
	
	/**
	 * TODO: Continue here.
	 * Adds a new child template to the child templates of the class
	 * @param templateName 
	 * @param template
	 */
	void addNewChildTemplate(String templateName, JavaTemplateGroup template){
		if (this.childTemplates.containsKey(templateName)){
			List<JavaTemplateGroup> templates = this.childTemplates.get(templateName);
			templates.add(template);
		}
		else{
			this.childTemplates.put(templateName, new ArrayList<JavaTemplateGroup>());
			addNewChildTemplate(templateName, template);
		}
	}
	
	/**
	 * To compile a template means to substitute all  its attribute references by the actual
	 * string that will go there. Since a template will have other templates inside of it
	 * as attribute references, it first needs to compile those child templates before
	 * substituting the corresponding attribute reference by the value from its template.
	 */
	void compile(){
		if (!isCompiled()){
			//Adding the templates that can be repeated
			for (Entry<String, List<JavaTemplateGroup>> e : this.childTemplates.entrySet()){
				String templateName = e.getKey();
				List<JavaTemplateGroup> ctemplates_l = e.getValue();
				Set<JavaTemplateGroup> ctemplates = new HashSet<JavaTemplateGroup>(ctemplates_l);
				for (JavaTemplateGroup ctemplate : ctemplates){
					ctemplate.compile();
					this.template.add(templateName, ctemplate);
				}
			}
			
			this.isCompiled = true;
		}
	}
	
	/**s
	 * 
	 */
	@Override
	public String toString(){
		compile();
		return this.template.render();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((this.childTemplates == null) ? 0 : this.childTemplates
						.hashCode());
		return result;
	}

	/**
	 * Only compares templates to determine equality
	 * (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof JavaTemplateGroup)) {
			return false;
		}
		JavaTemplateGroup other = (JavaTemplateGroup) obj;
		if (this.childTemplates == null) {
			if (other.childTemplates != null) {
				return false;
			}
		} else {
			
			Set<Entry<String, List<JavaTemplateGroup>>> entries = this.childTemplates.entrySet();
			
			for (Entry<String, List<JavaTemplateGroup>> e : entries){
				String templateName = e.getKey();
				
				
				if (!other.childTemplates.containsKey(templateName)){
					return false;
				}
				
				List<JavaTemplateGroup> thisTemplates = e.getValue();
				List<JavaTemplateGroup> otherTemplates = other.childTemplates.get(templateName);
				Set<JavaTemplateGroup> thisTemplatesSet = new HashSet<JavaTemplateGroup>(thisTemplates);
				Set<JavaTemplateGroup> otherTemplatesSet = new HashSet<JavaTemplateGroup>(otherTemplates);
				
				return (thisTemplatesSet.equals(otherTemplatesSet));
			}
			
			
		}
		return true;
	}
	
	
}
