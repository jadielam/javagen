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
 * @since 0.1
 */
public class JAnnotation extends JavaTemplateGroup {

	/**
	 * Testing
	 * Constructor for annotation
	 */
	private final String name;
	
	private Set<Pair<String, String>> attributeValuePairs;

	/**
	 * Creates a new annotation, only setting the name value.
	 * @param name1 the name of the annotation
	 */
	JAnnotation(String name1){
		
		super("annotation");
		this.template.add("name", name1);
		this.name = name1;		
		this.attributeValuePairs = new HashSet<Pair<String, String>>();
	}
	
	public JAnnotation addAttribute(String attributeName, String attributeValue){
		this.attributeValuePairs.add(new Pair<String, String>(attributeName, attributeValue));
		return this;
	}

	/**
	 * 
	 */
	@Override
	public void compile(){
				
		for (Pair<String, String> attributeValue : attributeValuePairs){
			ST attributeValueTemplate = super.getInstanceOf("attributeValue");
			String attribute = attributeValue.getFirst();
			String value = attributeValue.getSecond();
			attributeValueTemplate.add("attribute", attribute);
			attributeValueTemplate.add("value", value);
			this.template.add("attributeValue", attributeValueTemplate.render());
		}
		
		super.compile();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((this.name == null) ? 0 : this.name.hashCode());
		return result;
	}

	/** 
	 * It uses only the name of an annotation to determine equality. 
	 * The reason is that Java does not allow one same annotation repeated
	 * multiple times, regardless of the number of parameters
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!super.equals(obj)) {
			return false;
		}
		if (!(obj instanceof JAnnotation)) {
			return false;
		}
		JAnnotation other = (JAnnotation) obj;
		if (this.name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!this.name.equals(other.name)) {
			return false;
		}
		return true;
	}


	
}
