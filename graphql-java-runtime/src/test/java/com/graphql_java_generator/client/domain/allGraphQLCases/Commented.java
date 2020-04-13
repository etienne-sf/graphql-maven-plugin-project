/** Generated by the default template from graphql-java-generator */
package com.graphql_java_generator.client.domain.allGraphQLCases;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.graphql_java_generator.GraphQLField;
import com.graphql_java_generator.annotation.GraphQLInputParameters;
import com.graphql_java_generator.annotation.GraphQLInterfaceType;
import com.graphql_java_generator.annotation.GraphQLScalar;
import java.util.List;

/**
 * @author generated by graphql-java-generator
 * @see <a href="https://github.com/graphql-java-generator/graphql-java-generator">https://github.com/graphql-java-generator/graphql-java-generator</a>
 */
@JsonTypeInfo(use = Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "__typename", visible = true)
@JsonSubTypes({ @Type(value = Human.class, name = "Human") })
@GraphQLInterfaceType("Commented")
public interface Commented  {

	@JsonProperty("nbComments")
	@GraphQLScalar(fieldName = "nbComments", graphQLTypeName = "Int", javaClass = Integer.class)
	public void setNbComments(Integer nbComments);

	@JsonProperty("nbComments")
	@GraphQLScalar(fieldName = "nbComments", graphQLTypeName = "Int", javaClass = Integer.class)
	public Integer getNbComments();

	@JsonDeserialize(contentAs = String.class)
	@JsonProperty("comments")
	@GraphQLScalar(fieldName = "comments", graphQLTypeName = "String", javaClass = String.class)
	public void setComments(List<String> comments);

	@JsonDeserialize(contentAs = String.class)
	@JsonProperty("comments")
	@GraphQLScalar(fieldName = "comments", graphQLTypeName = "String", javaClass = String.class)
	public List<String> getComments();

	@JsonProperty("__typename")
	@GraphQLScalar(fieldName = "__typename", graphQLTypeName = "String", javaClass = String.class)
	public void set__typename(String __typename);

	@JsonProperty("__typename")
	@GraphQLScalar(fieldName = "__typename", graphQLTypeName = "String", javaClass = String.class)
	public String get__typename();
}
