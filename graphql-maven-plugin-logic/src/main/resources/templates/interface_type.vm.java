#################################################################################################################
## Import of common.vm  (commons Velocity macro and definitions)
#################################################################################################################
#parse ("templates/common.vm")
##
##
/** Generated by the default template from graphql-java-generator */
package ${configuration.packageName};

#foreach($import in ${object.imports})
import $import;
#end

import com.graphql_java_generator.annotation.GraphQLDirective;

/**
#if ($object.description)
#foreach ($line in $object.description.lines)
 * ${line}
#end
#end
 * 
 * @author generated by graphql-java-generator
 * @see <a href="https://github.com/graphql-java-generator/graphql-java-generator">https://github.com/graphql-java-generator/graphql-java-generator</a>
 */
${object.annotation}
#appliedDirectives(${object.appliedDirectives}, "")
@SuppressWarnings("unused")
public interface ${object.javaName}
#if($object.implementz.size()>0)
	extends #foreach($impl in $object.implementedTypes)$impl.javaName#if($foreach.hasNext), #end#end
	#foreach ($interface in $object.additionalInterfaces)
	, $interface
#end
#elseif ($object.additionalInterfaces.size() > 0)
	extends #foreach($interface in $object.additionalInterfaces)$interface#if($foreach.hasNext), #end#end 
#end
{
#foreach ($field in $object.fields)
## 
## 
## 
## If the field's name is either class or Class, then a getClass attribute would be generated, which generates a compile time error, as the getClass() method
## may not be overridden. So the generated getClass method is suffixed by "_" to avoid this name conflict.
#if ($field.name.equals("class") || $field.name.equals("Class"))
#set($fieldPrefixForGetterAndSetter="_")
#else
#set($fieldPrefixForGetterAndSetter="")
#end
##
## 
## 
##

#if ($field.comments.size() > 0)
	/**
#end	
#foreach ($comment in $field.comments)
	 * $comment
#end
#if ($field.comments.size() > 0)
	 */
#end
	${field.annotation}
#appliedDirectives(${field.appliedDirectives}, "	")
	public void set$fieldPrefixForGetterAndSetter${field.pascalCaseName}(${field.javaTypeFullClassname} ${field.javaName});

#if ($field.comments.size() > 0)
	/**
#end	
#foreach ($comment in $field.comments)
	 * $comment
#end
#if ($field.comments.size() > 0)
	 */
#end
	${field.annotation}
#appliedDirectives(${field.appliedDirectives}, "	")
	public ${field.javaTypeFullClassname} get$fieldPrefixForGetterAndSetter${field.pascalCaseName}();
#end
##
## When in client mode, we add the capability to receive unknown JSON attributes, which includes returned values for GraphQL aliases
##
#if(${configuration.mode}=="client")

	/**
	 * This method is called during the json deserialization process, by the {@link GraphQLObjectMapper}, each time an
	 * alias value is read from the json.
	 * 
	 * @param aliasName
	 * @param aliasDeserializedValue
	 */
	public void setAliasValue(String aliasName, Object aliasDeserializedValue);

	/**
	 * Retrieves the value for the given alias, as it has been received for this object in the GraphQL response. <BR/>
	 * This method <B>should not be used for Custom Scalars</B>, as the parser doesn't know if this alias is a custom
	 * scalar, and which custom scalar to use at deserialization time. In most case, a value will then be provided by
	 * this method with a basis json deserialization, but this value won't be the proper custom scalar value.
	 * 
	 * @param alias
	 * @return
	 */
	public Object getAliasValue(String alias);

#end
}
