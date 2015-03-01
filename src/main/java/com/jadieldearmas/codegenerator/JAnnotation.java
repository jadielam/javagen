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
 * @author jdearmas
 *
 * Class that represents a Java annotation
 * @since 0.1
 */
public class JAnnotation extends JavaTemplateGroup {

	/**
	 * The name of the annotation
	 */
	private final String name;
	
	/**
	 * The set of attributeValue pairs.
	 * TODO: See what are the rules for annotation pairs in Java.
	 */
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
	
	/**
	 * Adds an attribute name and an attribute value to the annotation.
	 * When annotation is empty, it looks like this:
	 * <code>@name</code>
	 * 
	 * When annotation has attributes, it looks like this:
	 * <code>@name(attributeName="attributeValue")</code>
	 * 
	 * When more than one attribute is added, they are separated by commas:
	 * <code>@name(attributeName1="attributeValue1", attributeName2="attributeValue2")</code>
	 * 
	 * @param attributeName the name of the attribute
	 * @param attributeValue the value of that attribute.
	 * @return this annotation
	 */
	public JAnnotation addAttribute(String attributeName, String attributeValue){
		this.attributeValuePairs.add(new Pair<String, String>(attributeName, attributeValue));
		return this;
	}

	/**
	 * @see JavaTemplateGroup#compile()
	 */
	@Override
	public void compile(){
				
		for (Pair<String, String> attributeValue : this.attributeValuePairs){
			ST attributeValueTemplate = super.getInstanceOf("attributeValue");
			String attribute = attributeValue.getFirst();
			String value = attributeValue.getSecond();
			attributeValueTemplate.add("attribute", attribute);
			attributeValueTemplate.add("value", value);
			this.template.add("attributeValue", attributeValueTemplate.render());
		}
		
		super.compile();
	}

	/** 
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
	 * multiple times, regardless of the number of parameters.
	 * TODO: Java 7 might not allow repeated annotations, but Java 8 does.
	 * @see JavaTemplateGroup#compile()
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
