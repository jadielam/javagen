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
 * @since  
 */
public abstract class JavaTemplateGroup {

	private final STGroup javaGroup;
	
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
	 * This field most be hidden and initialized by the child classes
	 */
	ST template;
	
	JavaTemplateGroup(String templateName){
		
		this.javaGroup = new STGroupFile("java.stg");
		this.template = getInstanceOf(templateName);
	}
	
	public ST getInstanceOf(String template){
		return this.javaGroup.getInstanceOf(template);
	}
	
	boolean isCompiled(){
		return this.isCompiled;
	}
	
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
	public void compile(){
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
