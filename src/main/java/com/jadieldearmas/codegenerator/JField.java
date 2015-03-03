// **************************************************
//
// Modification Tracking:
// $Revision:$
// $LastChangedBy:$
// $LastChangedDate:$
//
// **************************************************
package com.jadieldearmas.codegenerator;


/**
 * @author jdearmas
 * Java model for fields
 * @since 1.0
 */
public class JField extends JavaTemplateGroup {

	/**
	 * The java type of the field
	 */
	private final String type;
	
	/**
	 * The name of the field
	 */
	private final String name;
	
	/**
	 * Visibility modifier that can take values of:
	 * "private", "public" or "protected"
	 */
	private String vissibilityModifier;
	
	/**
	 * Indicates if field is static or not
	 */
	private boolean isStatic = false;
	
	/**
	 * Indicates if field is final or not
	 */
	private boolean isFinal = false;
	
	/**
	 * Constructor that creates a java field
	 * @param type the Java type of the field
	 * @param name the name of the field
	 */
	JField(String type, String name){
		
		super("field");
		
		this.type = type;
		this.name = name;
		
	}
	
	/**
	 * Sets the visibility modifier to public
	 * @return Returns <code>this</code> JField
	 */
	public JField setPublic(){
		this.vissibilityModifier = "public";
		return this;
	}
	
	/**
	 * Sets the visibility modifier to private
	 * @return Returns <code>this</code> JField
	 */
	public JField setPrivate(){
		this.vissibilityModifier = "private";
		return this;
	}
	
	/**
	 * Sets the visibility modifier to protected
	 * @return Returns <code>this</code> JField
	 */
	public JField setProtected(){
		this.vissibilityModifier = "protected";
		return this;
	}
	
	/**
	 * Sets the visibility modifier to null, which is equivalent to package-protected
	 * @return Returns <code>this</code> JField
	 */
	public JField setPackageProtected(){
		this.vissibilityModifier = null;
		return this;
	}
	
	/**
	 * Sets the field final
	 * @return Returns <code>this</code> JField 
	 */
	public JField setFinal(){
		this.isFinal = true;
		return this;
	}
	
	/**
	 * Unsets the field to be final
	 * @return Returns <code>this</code> JField
	 */
	public JField unsetFinal(){
		this.isFinal = false;
		return this;
	}
	
	/**
	 * Sets the field to be static
	 * @return Returns <code>this</code> JField
	 */
	public JField setStatic(){
		this.isStatic = true;
		return this;
	}
	
	/**
	 * Makes the field non-static
	 * @return Returns <code>this</code> JField
	 */
	public JField unsetStatic(){
		this.isStatic = false;
		return this;
	}
	
	/**
	 * Adds a new annotation to the field
	 * @param name the name of the annotation
	 * @return The annotation model that was created.
	 */
	public JAnnotation addAnnotation(String name) {
		
		JAnnotation annotation = new JAnnotation(name);
		this.addNewChildTemplate("annotation", annotation);
		return annotation;
	}
	
	/**
	 * Adds a new normal comment to the field
	 * TODO: I don't think that this class should exist or that it should
	 * be returned, since the user does not have anything to modify in a comment.
	 * @param comment THe text of the comment to add
	 * @return The new comment that was added.
	 * @throws UnsupportedOperationException whenever the method is called, because
	 * the method is not yet supported.
	 */
	public JComment addComment(String comment) throws UnsupportedOperationException{
		//TODO
		throw new UnsupportedOperationException("Operation not yet supported");
	}
	
	/**
	 * @see JavaTemplateGroup#compile()
	 */
	@Override
	public void compile(){
		
		this.template.add("type", this.type);
		this.template.add("name", this.name);
		this.template.add("vissibilityModifier", this.vissibilityModifier);
		if (this.isStatic){
			this.template.add("isStatic", "static");
		}
		if (this.isFinal){
			this.template.add("isFinal", "final");
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
	 * The equal method is based on the name, since there cannot be two fields
	 * with the same name in a Java class.
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
		if (!(obj instanceof JField)) {
			return false;
		}
		JField other = (JField) obj;
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
