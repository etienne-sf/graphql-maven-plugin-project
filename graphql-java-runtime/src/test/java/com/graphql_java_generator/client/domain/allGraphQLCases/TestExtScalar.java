/** Generated by the default template from graphql-java-generator */
package com.graphql_java_generator.client.domain.allGraphQLCases;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.graphql_java_generator.GraphQLField;
import com.graphql_java_generator.annotation.GraphQLInputParameters;
import com.graphql_java_generator.annotation.GraphQLObjectType;
import com.graphql_java_generator.annotation.GraphQLScalar;

/**
 * @author generated by graphql-java-generator
 * @see <a href="https://github.com/graphql-java-generator/graphql-java-generator">https://github.com/graphql-java-generator/graphql-java-generator</a>
 */
@GraphQLObjectType("TestExtScalar")
public class TestExtScalar  {

	public TestExtScalar(){
		// No action
	}

	@JsonDeserialize(using = CustomScalarDeserializerNonNegativeInt.class)
	@JsonProperty("bad")
	@GraphQLScalar(fieldName = "bad", graphQLTypeName = "NonNegativeInt", javaClass = Integer.class)
	Integer bad;


	@JsonProperty("__typename")
	@GraphQLScalar(fieldName = "__typename", graphQLTypeName = "String", javaClass = String.class)
	String __typename;



	public void setBad(Integer bad) {
		this.bad = bad;
	}

	public Integer getBad() {
		return bad;
	}

	public void set__typename(String __typename) {
		this.__typename = __typename;
	}

	public String get__typename() {
		return __typename;
	}

    public String toString() {
        return "TestExtScalar {"
				+ "bad: " + bad
				+ ", "
				+ "__typename: " + __typename
        		+ "}";
    }

    /**
	 * Enum of field names
	 */
	 public static enum Field implements GraphQLField {
		Bad("bad"),
		__typename("__typename");

		private String fieldName;

		Field(String fieldName) {
			this.fieldName = fieldName;
		}

		public String getFieldName() {
			return fieldName;
		}

		public Class<?> getGraphQLType() {
			return this.getClass().getDeclaringClass();
		}

	}

	public static Builder builder() {
			return new Builder();
		}



	/**
	 * Builder
	 */
	public static class Builder {
		private Integer bad;


		public Builder withBad(Integer bad) {
			this.bad = bad;
			return this;
		}

		public TestExtScalar build() {
			TestExtScalar _object = new TestExtScalar();
			_object.setBad(bad);
			_object.set__typename("TestExtScalar");
			return _object;
		}
	}
}
