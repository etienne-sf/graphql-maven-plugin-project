/** Generated by the default template from graphql-java-generator */
package org.forum.generated;

import java.util.HashMap;
import java.util.Map;

import org.forum.generated.__Type;
import org.forum.generated.util.CustomJacksonDeserializers;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.graphql_java_generator.annotation.GraphQLInputParameters;
import com.graphql_java_generator.annotation.GraphQLNonScalar;
import com.graphql_java_generator.annotation.GraphQLObjectType;
import com.graphql_java_generator.annotation.GraphQLScalar;

import java.util.List;

/**
 *
 * @author generated by graphql-java-generator
 * @see <a href="https://github.com/graphql-java-generator/graphql-java-generator">https://github.com/graphql-java-generator/graphql-java-generator</a>
 */
@GraphQLObjectType("__Type")
@JsonInclude(Include.NON_NULL)
@SuppressWarnings("unused")
public class __Type 
{

	/**
	 * This map contains the deserialized values for the alias, as parsed from the json response from the GraphQL
	 * server. The key is the alias name, the value is the deserialiazed value (taking into account custom scalars,
	 * lists, ...)
	 */
	@com.graphql_java_generator.annotation.GraphQLIgnore
	Map<String, Object> aliasValues = new HashMap<>();

	public __Type(){
		// No action
	}

	@JsonProperty("kind")
	@GraphQLScalar(fieldName = "kind", graphQLTypeSimpleName = "__TypeKind", javaClass = org.forum.generated.__TypeKind.class)
	org.forum.generated.__TypeKind kind;


	@JsonProperty("name")
	@GraphQLScalar(fieldName = "name", graphQLTypeSimpleName = "String", javaClass = java.lang.String.class)
	java.lang.String name;


	@JsonProperty("description")
	@GraphQLScalar(fieldName = "description", graphQLTypeSimpleName = "String", javaClass = java.lang.String.class)
	java.lang.String description;


	/**
	 *  should be non-null for OBJECT and INTERFACE only, must be null for the others
	 */
	@JsonProperty("fields")
	@JsonDeserialize(using = CustomJacksonDeserializers.List__Field.class)
	@GraphQLInputParameters(names = {"includeDeprecated"}, types = {"Boolean"}, mandatories = {false}, listDepths = {0}, itemsMandatory = {false})
	@GraphQLNonScalar(fieldName = "fields", graphQLTypeSimpleName = "__Field", javaClass = org.forum.generated.__Field.class)
	List<org.forum.generated.__Field> fields;


	/**
	 *  should be non-null for OBJECT and INTERFACE only, must be null for the others
	 */
	@JsonProperty("interfaces")
	@JsonDeserialize(using = CustomJacksonDeserializers.List__Type.class)
	@GraphQLNonScalar(fieldName = "interfaces", graphQLTypeSimpleName = "__Type", javaClass = org.forum.generated.__Type.class)
	List<org.forum.generated.__Type> interfaces;


	/**
	 *  should be non-null for INTERFACE and UNION only, always null for the others
	 */
	@JsonProperty("possibleTypes")
	@JsonDeserialize(using = CustomJacksonDeserializers.List__Type.class)
	@GraphQLNonScalar(fieldName = "possibleTypes", graphQLTypeSimpleName = "__Type", javaClass = org.forum.generated.__Type.class)
	List<org.forum.generated.__Type> possibleTypes;


	/**
	 *  should be non-null for ENUM only, must be null for the others
	 */
	@JsonProperty("enumValues")
	@JsonDeserialize(using = CustomJacksonDeserializers.List__EnumValue.class)
	@GraphQLInputParameters(names = {"includeDeprecated"}, types = {"Boolean"}, mandatories = {false}, listDepths = {0}, itemsMandatory = {false})
	@GraphQLNonScalar(fieldName = "enumValues", graphQLTypeSimpleName = "__EnumValue", javaClass = org.forum.generated.__EnumValue.class)
	List<org.forum.generated.__EnumValue> enumValues;


	/**
	 *  should be non-null for INPUT_OBJECT only, must be null for the others
	 */
	@JsonProperty("inputFields")
	@JsonDeserialize(using = CustomJacksonDeserializers.List__InputValue.class)
	@GraphQLNonScalar(fieldName = "inputFields", graphQLTypeSimpleName = "__InputValue", javaClass = org.forum.generated.__InputValue.class)
	List<org.forum.generated.__InputValue> inputFields;


	/**
	 *  should be non-null for NON_NULL and LIST only, must be null for the others
	 */
	@JsonProperty("ofType")
	@GraphQLNonScalar(fieldName = "ofType", graphQLTypeSimpleName = "__Type", javaClass = org.forum.generated.__Type.class)
	org.forum.generated.__Type ofType;


	@JsonProperty("__typename")
	@GraphQLScalar(fieldName = "__typename", graphQLTypeSimpleName = "String", javaClass = java.lang.String.class)
	java.lang.String __typename;



	public void setKind(org.forum.generated.__TypeKind kind) {
		this.kind = kind;
	}

	public org.forum.generated.__TypeKind getKind() {
		return kind;
	}
		

	public void setName(java.lang.String name) {
		this.name = name;
	}

	public java.lang.String getName() {
		return name;
	}
		

	public void setDescription(java.lang.String description) {
		this.description = description;
	}

	public java.lang.String getDescription() {
		return description;
	}
		

	/**
	 *  should be non-null for OBJECT and INTERFACE only, must be null for the others
	 */
	public void setFields(List<org.forum.generated.__Field> fields) {
		this.fields = fields;
	}

	/**
	 *  should be non-null for OBJECT and INTERFACE only, must be null for the others
	*/
	public List<org.forum.generated.__Field> getFields() {
		return fields;
	}
		

	/**
	 *  should be non-null for OBJECT and INTERFACE only, must be null for the others
	 */
	public void setInterfaces(List<org.forum.generated.__Type> interfaces) {
		this.interfaces = interfaces;
	}

	/**
	 *  should be non-null for OBJECT and INTERFACE only, must be null for the others
	*/
	public List<org.forum.generated.__Type> getInterfaces() {
		return interfaces;
	}
		

	/**
	 *  should be non-null for INTERFACE and UNION only, always null for the others
	 */
	public void setPossibleTypes(List<org.forum.generated.__Type> possibleTypes) {
		this.possibleTypes = possibleTypes;
	}

	/**
	 *  should be non-null for INTERFACE and UNION only, always null for the others
	*/
	public List<org.forum.generated.__Type> getPossibleTypes() {
		return possibleTypes;
	}
		

	/**
	 *  should be non-null for ENUM only, must be null for the others
	 */
	public void setEnumValues(List<org.forum.generated.__EnumValue> enumValues) {
		this.enumValues = enumValues;
	}

	/**
	 *  should be non-null for ENUM only, must be null for the others
	*/
	public List<org.forum.generated.__EnumValue> getEnumValues() {
		return enumValues;
	}
		

	/**
	 *  should be non-null for INPUT_OBJECT only, must be null for the others
	 */
	public void setInputFields(List<org.forum.generated.__InputValue> inputFields) {
		this.inputFields = inputFields;
	}

	/**
	 *  should be non-null for INPUT_OBJECT only, must be null for the others
	*/
	public List<org.forum.generated.__InputValue> getInputFields() {
		return inputFields;
	}
		

	/**
	 *  should be non-null for NON_NULL and LIST only, must be null for the others
	 */
	public void setOfType(org.forum.generated.__Type ofType) {
		this.ofType = ofType;
	}

	/**
	 *  should be non-null for NON_NULL and LIST only, must be null for the others
	*/
	public org.forum.generated.__Type getOfType() {
		return ofType;
	}
		

	public void set__typename(java.lang.String __typename) {
		this.__typename = __typename;
	}

	public java.lang.String get__typename() {
		return __typename;
	}
		

 
	/**
	 * This method is called during the json deserialization process, by the {@link GraphQLObjectMapper}, each time an
	 * alias value is read from the json.
	 * 
	 * @param aliasName
	 * @param aliasDeserializedValue
	 */
	public void setAliasValue(String aliasName, Object aliasDeserializedValue) {
		aliasValues.put(aliasName, aliasDeserializedValue);
	}

	/**
	 * Retrieves the value for the given alias, as it has been received for this object in the GraphQL response. <BR/>
	 * This method <B>should not be used for Custom Scalars</B>, as the parser doesn't know if this alias is a custom
	 * scalar, and which custom scalar to use at deserialization time. In most case, a value will then be provided by
	 * this method with a basis json deserialization, but this value won't be the proper custom scalar value.
	 * 
	 * @param alias
	 * @return
	 */
	public Object getAliasValue(String alias) {
		return aliasValues.get(alias);
	}

    public String toString() {
        return "__Type {"
				+ "kind: " + kind
				+ ", "
				+ "name: " + name
				+ ", "
				+ "description: " + description
				+ ", "
				+ "fields: " + fields
				+ ", "
				+ "interfaces: " + interfaces
				+ ", "
				+ "possibleTypes: " + possibleTypes
				+ ", "
				+ "enumValues: " + enumValues
				+ ", "
				+ "inputFields: " + inputFields
				+ ", "
				+ "ofType: " + ofType
				+ ", "
				+ "__typename: " + __typename
        		+ "}";
    }

	public static Builder builder() {
		return new Builder();
	}

	/**
	 * The Builder that helps building instance of this POJO. You can get an instance of this class, by calling the
	 * {@link #builder()}
	 */
	public static class Builder {
		private org.forum.generated.__TypeKind kind;
		private java.lang.String name;
		private java.lang.String description;
		private List<org.forum.generated.__Field> fields;
		private List<org.forum.generated.__Type> interfaces;
		private List<org.forum.generated.__Type> possibleTypes;
		private List<org.forum.generated.__EnumValue> enumValues;
		private List<org.forum.generated.__InputValue> inputFields;
		private org.forum.generated.__Type ofType;

		public Builder withKind(org.forum.generated.__TypeKind kind) {
			this.kind = kind;
			return this;
		}
		public Builder withName(java.lang.String name) {
			this.name = name;
			return this;
		}
		public Builder withDescription(java.lang.String description) {
			this.description = description;
			return this;
		}
		public Builder withFields(List<org.forum.generated.__Field> fields) {
			this.fields = fields;
			return this;
		}
		public Builder withInterfaces(List<org.forum.generated.__Type> interfaces) {
			this.interfaces = interfaces;
			return this;
		}
		public Builder withPossibleTypes(List<org.forum.generated.__Type> possibleTypes) {
			this.possibleTypes = possibleTypes;
			return this;
		}
		public Builder withEnumValues(List<org.forum.generated.__EnumValue> enumValues) {
			this.enumValues = enumValues;
			return this;
		}
		public Builder withInputFields(List<org.forum.generated.__InputValue> inputFields) {
			this.inputFields = inputFields;
			return this;
		}
		public Builder withOfType(org.forum.generated.__Type ofType) {
			this.ofType = ofType;
			return this;
		}

		public __Type build() {
			__Type _object = new __Type();
			_object.setKind(kind);
			_object.setName(name);
			_object.setDescription(description);
			_object.setFields(fields);
			_object.setInterfaces(interfaces);
			_object.setPossibleTypes(possibleTypes);
			_object.setEnumValues(enumValues);
			_object.setInputFields(inputFields);
			_object.setOfType(ofType);
			_object.set__typename("__Type");
			return _object;
		}
	}
}
