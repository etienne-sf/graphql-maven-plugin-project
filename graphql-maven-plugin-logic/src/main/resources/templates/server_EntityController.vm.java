##
#set ($entity=$graphqlUtils.getJavaName($dataFetchersDelegate.type.name))
##
/** Generated by the default template from graphql-java-generator */
package ${packageUtilName};

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import org.dataloader.DataLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.graphql.execution.BatchLoaderRegistry;
import org.springframework.stereotype.Controller;

import com.graphql_java_generator.server.util.GraphqlServerUtils;
import com.graphql_java_generator.util.GraphqlUtils;
import graphql.schema.DataFetchingEnvironment;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author generated by graphql-java-generator
 * @see <a href="https://github.com/graphql-java-generator/graphql-java-generator">https://github.com/graphql-java-generator/graphql-java-generator</a>
 */
@Controller
@SchemaMapping(typeName = "${dataFetchersDelegate.type.name}")
@SuppressWarnings("unused")
public class ${entity}Controller {

	@Autowired
	protected ${dataFetchersDelegate.pascalCaseName} ${dataFetchersDelegate.camelCaseName};

## The constructor is only used to declare the relevant data loader... if any (0 or 1 for each class)
#foreach ($batchLoader in $batchLoaders)
#if ($dataFetchersDelegate.type.name == $batchLoader.type.name)
	public ${entity}Controller(BatchLoaderRegistry registry) {
		// Registering the data loaders is useless if the @BatchMapping is used. But we need it here, for backward
		// compatibility with code developed against the previous plugin versions
		registry.forTypePair(${batchLoader.type.identifier.javaTypeFullClassname}.class, ${batchLoader.type.classFullName}.class).registerMappedBatchLoader((keysSet, env) -> {
			List<${batchLoader.type.identifier.javaTypeFullClassname}> keys = new ArrayList<>(keysSet.size());
			keys.addAll(keysSet);
			return Mono.fromCallable(() -> {
				Map<${batchLoader.type.identifier.javaTypeFullClassname}, ${batchLoader.type.classFullName}> map = new HashMap<>();
				// Values are returned in the same order as the keys list
				List<${batchLoader.type.classFullName}> values = this.${dataFetchersDelegate.camelCaseName}.batchLoader(keys#if($configuration.generateBatchLoaderEnvironment), env#end);
				for (int i = 0; i < keys.size(); i += 1) {
					map.put(keys.get(i), values.get(i));
				}
				return map;
			});
		});

	}
#end
#end
	
#foreach ($dataFetcher in $dataFetchersDelegate.dataFetchers)
##
## To manage enum values that are java keyword, enum values like if, else (...) are stored in enum values prefixed by _ (like _if, _else...)
## But this prevents the automatic mapping of spring-graphql to work. So enum values are returned as String
#set ($isEnum=$dataFetcher.field.type.isEnum())
#set ($isList=($dataFetcher.field.fieldTypeAST.listDepth>0))

	/**
	 * This method loads the data for ${dataFetcher.graphQLType}.${dataFetcher.field.name}. <BR/>
	 * For optimization, this method returns a CompletableFuture. This allows to use
	 * <A HREF="https://github.com/graphql-java/java-dataloader">graphql-java java-dataloader</A> to highly optimize the
	 * number of requests to the server.<BR/>
	 * The principle is this one: The data loader collects all the data to load, avoid to load several times the same
	 * data, and allows parallel execution of the queries, if multiple queries are to be run.<BR/>
	 * You can implements this method like the sample below:
	 * 
	 * <PRE>
	 * &#64;Override
	 * public CompletableFuture<List<Character>> friends(DataFetchingEnvironment environment,
	 * 		DataLoader<Long, Member> dataLoader, Human origin) {
	 * 	List<java.lang.Long> friendIds = origin.getFriendIds();
	 * 	DataLoader<java.lang.Long, CharacterImpl> dataLoader = environment.getDataLoader("Character");
	 * 	return dataLoader.loadMany(friendIds);
	 * }
	 * </PRE>
	 * 
	 * <BR/>
#if($isEnum)
 	 * To manage enum values that are java keyword, enum values like if, else (...) are stored in enum values prefixed by _ (like _if, _else...)
	 * But this prevents the automatic mapping of spring-graphql to work. So enum values are returned as String
#end
	 * 
	 * @param dataFetchingEnvironment
	 *            The GraphQL {@link DataFetchingEnvironment}. It gives you access to the full GraphQL context for this
	 *            DataFetcher
	 * @param dataLoader
	 *            The {@link DataLoader} allows to load several data in one query. It allows to solve the (n+1) queries
	 *            issues, and greatly optimizes the response time.<BR/>
	 *            You'll find more informations here: <A HREF=
	 *            "https://github.com/graphql-java/java-dataloader">https://github.com/graphql-java/java-dataloader</A>
	 * @param origin
	 *            The object from which the field is fetch. In other word: the aim of this data fetcher is to fetch the
	 *            author attribute of the <I>origin</I>, which is an instance of {ObjectType {name:Post,
	 *            fields:{Field{name:id, type:ID!, params:[]},Field{name:date, type:Date!, params:[]},Field{name:author,
	 *            type:Member, params:[]},Field{name:publiclyAvailable, type:Boolean, params:[]},Field{name:title,
	 *            type:String!, params:[]},Field{name:content, type:String!, params:[]},Field{name:authorId, type:ID,
	 *            params:[]},Field{name:topicId, type:ID, params:[]}}, comments ""}. It depends on your data modle, but
	 *            it typically contains the id to use in the query.
	 * @throws NoSuchElementException
	 *             This method may return a {@link NoSuchElementException} exception. In this case, the exception is
	 *             trapped by the calling method, and the return is consider as null. This allows to use the
	 *             {@link Optional#get()} method directly, without caring of whether or not there is a value. The
	 *             generated code will take care of the {@link NoSuchElementException} exception.
	 */
	@SchemaMapping(field = "${dataFetcher.field.name}") // This annotation is used to maintain compatibility with earlier version of the
									// plugin. Code that uses Spring Boot annotations should use remove this method
									// and use the @BatchMapping annotation instead
## The line above is probably useless. But it's a complex one, and we won"t remove it until beeing sure it's useless
##set($return="#if(${dataFetcher.completableFuture})CompletableFuture<#end#if(${dataFetchersDelegate.type.requestType}==\"subscription\")Flux<#if($dataFetcher.field.fieldTypeAST.mandatory==false)Optional<#end#end#if($isEnum)#if($isList)List<#{end}String#if($isList)>#end#else${dataFetcher.field.javaTypeFullClassname}#end#if(${dataFetchersDelegate.type.requestType}==\"subscription\")#if($dataFetcher.field.fieldTypeAST.mandatory==false)>#end>#end#if(${dataFetcher.completableFuture})>#end")
##
##
#macro(argumentType $argument)
#if($argument.type.isEnum())
${velocityUtils.repeat("List<",$argument.fieldTypeAST.listDepth)}String${velocityUtils.repeat(">",$argument.fieldTypeAST.listDepth)}##
#else
$argument.javaTypeFullClassname##
#end
#end
##
########################
## If at least one parameter is a list of enums, there would be a cast warning. Let's prevent that.
#set($suppressWarning=false)
#foreach($argument in $dataFetcher.field.inputParameters)
#if($argument.type.isEnum() && $argument.fieldTypeAST.listDepth>0)
#set($suppressWarning=true)
#end
#end
#if($suppressWarning)
@SuppressWarnings("unchecked")
#end
########################
	public Object ${dataFetcher.field.javaName}(DataFetchingEnvironment dataFetchingEnvironment##
#if(${dataFetcher.completableFuture})			, DataLoader<${dataFetcher.field.type.identifier.javaTypeFullClassname}, ${dataFetcher.field.type.classFullName}> dataLoader#end
#if($dataFetcher.graphQLOriginType)			, ${dataFetcher.graphQLOriginType.classFullName} origin#end#foreach($argument in $dataFetcher.field.inputParameters), 
			@Argument("${argument.name}") #argumentType($argument) ${argument.javaName}#end) {
		return #if($isEnum)GraphqlServerUtils.graphqlServerUtils.enumValueToString(#end this.${dataFetchersDelegate.camelCaseName}.${dataFetcher.field.javaName}(dataFetchingEnvironment#if(${dataFetcher.completableFuture}), dataLoader#end#if($dataFetcher.graphQLOriginType), origin#end #foreach($argument in $dataFetcher.field.inputParameters), #if($argument.type.isEnum())(${argument.javaTypeFullClassname})GraphqlUtils.graphqlUtils.stringToEnumValue(${argument.javaName}, ${argument.type.classFullName}.class)#else${argument.javaName}#end#end)#if($isEnum))#end;
	}

#end
}
