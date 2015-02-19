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
	Map<String, List<JavaTemplateGroup>> childTemplates = new HashMap<String, List<JavaTemplateGroup>>();
	
	/**
	 * This map is used for all the templates that can only hold one value and cannot be repeated.
	 * For example, the method template will contain a placeholder called vissibilityModifier.
	 * That placeholder can only hold one value, no matter how many times the user of the API
	 * adds new vissibility modifiers to a method, only the last value entered is valid.
	 */
	Map<String, String> placeholderUpdate = new HashMap<String, String>();
	
	/**
	 * This map is used for all the templates that can hold more than one value, but that
	 * the value cannot be repeated twice.  For example, the moethod template will contain 
	 * a placeholder called interface, and its value contains a list of interfaces
	 * that the class implements.  The same for function arguments and exceptions.
	 */
	Map<String, Set<String>> placeholderAdd = new HashMap<String, Set<String>>();
	
	/**
	 * This field most be hidden and initialized by the child classes
	 */
	private ST template;
	
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
	
	void updatePlaceholder(String placeholderName, String placeholderValue){
		this.placeholderUpdate.put(placeholderName, placeholderValue);
	}
	
	void addPlaceholder(String placeholderName, String placeholderValue){
		if (this.placeholderAdd.containsKey(placeholderName)){
			Set<String> values = this.placeholderAdd.get(placeholderName);
			values.add(placeholderValue);
		}
		else{
			Set<String> values = new HashSet<String>();
			values.add(placeholderValue);
			this.placeholderAdd.put(placeholderName, values);
		}
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
				List<JavaTemplateGroup> ctemplates = e.getValue();
				for (JavaTemplateGroup ctemplate : ctemplates){
					ctemplate.compile();
					this.template.add(templateName, ctemplate);
				}
			}
			
			//Adding the unique placeholders
			for (Entry<String, String> e : this.placeholderUpdate.entrySet()){
				String placeholderName = e.getKey();
				String placeholderValue = e.getValue();
				
				this.template.add(placeholderName, placeholderValue);
			}
			
			//Adding the repeated placeholders
			for (Entry<String, Set<String>> e : this.placeholderAdd.entrySet()){
				String placeholderName = e.getKey();
				Set<String> values = e.getValue();
				
				for (String value : values){
					this.template.add(placeholderName, value);
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
	
}
