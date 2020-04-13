/** Generated by the default template from graphql-java-generator */
package com.graphql_java_generator.client.domain.allGraphQLCases;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.graphql_java_generator.GraphQLField;
import com.graphql_java_generator.annotation.GraphQLInputParameters;
import com.graphql_java_generator.annotation.GraphQLInputType;
import com.graphql_java_generator.annotation.GraphQLScalar;
import java.util.Date;

/**
 * @author generated by graphql-java-generator
 * @see <a href="https://github.com/graphql-java-generator/graphql-java-generator">https://github.com/graphql-java-generator/graphql-java-generator</a>
 */
@GraphQLInputType("FieldParameterInput")
public class FieldParameterInput  {

	public FieldParameterInput(){
		// No action
	}

	@JsonProperty("uppercase")
	@GraphQLScalar(fieldName = "uppercase", graphQLTypeName = "Boolean", javaClass = Boolean.class)
	Boolean uppercase;


	@JsonDeserialize(using = CustomScalarDeserializerDate.class)
	@JsonProperty("date")
	@GraphQLScalar(fieldName = "date", graphQLTypeName = "Date", javaClass = Date.class)
	Date date;



	public void setUppercase(Boolean uppercase) {
		this.uppercase = uppercase;
	}

	public Boolean getUppercase() {
		return uppercase;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Date getDate() {
		return date;
	}

    public String toString() {
        return "FieldParameterInput {"
				+ "uppercase: " + uppercase
				+ ", "
				+ "date: " + date
        		+ "}";
    }

    /**
	 * Enum of field names
	 */
	 public static enum Field implements GraphQLField {
		Uppercase("uppercase"),
		Date("date");

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
		private Boolean uppercase;
		private Date date;


		public Builder withUppercase(Boolean uppercase) {
			this.uppercase = uppercase;
			return this;
		}
		public Builder withDate(Date date) {
			this.date = date;
			return this;
		}

		public FieldParameterInput build() {
			FieldParameterInput _object = new FieldParameterInput();
			_object.setUppercase(uppercase);
			_object.setDate(date);
			return _object;
		}
	}
}
