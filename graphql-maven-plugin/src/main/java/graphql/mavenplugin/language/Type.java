/**
 * 
 */
package graphql.mavenplugin.language;

import java.util.List;

/**
 * All types found in the GraphQL schema(s), and discovered during the GraphQL parsing, are instance of {@link Type}.
 * 
 * @author EtienneSF
 */
public interface Type {

	public enum GraphQlType {
		OBJECT, INTERFACE, SCALAR, ENUM
	}

	/**
	 * The name of the object type
	 * 
	 * @return
	 */
	public String getName();

	/**
	 * Returns true if this class is a JPA Entity. If yes, the generated code should contain the @Entity annotation, and
	 * its field should be annotated accordingly
	 * 
	 * @return true if this type is a JPA Entity. False otherwise.
	 */
	public boolean isJPAEntity();

	/**
	 * The GraphQlType for this type
	 * 
	 * @return
	 */
	public GraphQlType getGraphQlType();

	/**
	 * The java class simple name for this type. It may be and interface or a concrete class. <BR/>
	 * 
	 * @return The java classname is usually the name of the type. But in some case, collision my occur with the Java
	 *         syntax. In this cas, this method will return a classname different from the name
	 */
	public String getClassSimpleName();

	/**
	 * The java class full name for this type. It may be and interface or a concrete class. <BR/>
	 * 
	 * @return The java classname is usually the name of the type. But in some case, collision my occur with the Java
	 *         syntax. In this cas, this method will return a classname different from the name
	 */
	public String getClassFullName();

	/**
	 * Returns the name of the concrete class for this type. If this type is an interface, then this method returns the
	 * name of the default implementation for this class. Otherwise, this method returns the same as
	 * {@link #getJavaClassSimpleName()}
	 * 
	 * @return
	 */
	public String getConcreteClassSimpleName();

	/**
	 * Returns the list of {@link Field}s for this type. Or null, if this field can't have any field, like a Scalar for
	 * instance
	 * 
	 * @return
	 */
	public List<Field> getFields();
}
