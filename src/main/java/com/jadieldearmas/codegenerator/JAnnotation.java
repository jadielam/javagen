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

import org.stringtemplate.v4.ST;



/**
 * @author jdearmas
 *
 * @since 0.1
 */
public class JAnnotation extends JavaTemplateGroup {

	/**
	 * Constructor for annotation
	 * @param name The annotation name
	 * @param someAttributeValues the parameters and the values of them.
	 */
	JAnnotation(String name, List<Pair<String, String>> someAttributeValues){
		
		super();
		this.template = super.getInstanceOf("annotation");
		this.template.add("name", name);
		
		if (null != someAttributeValues && 0 < someAttributeValues.size()){
			for (Pair<String, String> attributeValue : someAttributeValues){
				ST attributeValueTemplate = super.getInstanceOf("attributeValue");
				String attribute = attributeValue.getFirst();
				String value = attributeValue.getSecond();
				attributeValueTemplate.add("attribute", attribute);
				attributeValueTemplate.add("value", value);
				this.template.add("attributeValue", attributeValueTemplate.render());
			}
		}
		
		
		
	}

	
	
}
