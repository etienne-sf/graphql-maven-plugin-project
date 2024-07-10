#################################################################################################################
## Import of common.vm  (commons Velocity macro and definitions)
#################################################################################################################
#parse ("templates/common.vm")
##
##
/** Generated by the default template from graphql-java-generator */
package ${packageUtilName};

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import org.dataloader.BatchLoaderEnvironment;
import org.dataloader.DataLoader;
import org.reactivestreams.Publisher;

import com.graphql_java_generator.annotation.GraphQLDirective;
import com.graphql_java_generator.util.GraphqlUtils;

import graphql.schema.DataFetchingEnvironment;
#if($configuration.generateBatchLoaderEnvironment)
import org.dataloader.BatchLoaderEnvironment;
#end

/**
#if ($dataFetcherDelegate.type.description)
 * Description for the ${dataFetcherDelegate.type.name} type: <br/>
#foreach ($line in $dataFetcherDelegate.type.description.lines)
 * ${line}
#end
 * <br/>
#end
 * This interface contains the fata fetchers that are delegated in the bean that the implementation has to provide, when
 * fetching fields for the ${dataFetchersDelegate.type.name} GraphQL type, as defined in the provided GraphQL schema. Please read the
 * <a href="https://github.com/graphql-java-generator/graphql-maven-plugin-project/wiki/server"> wiki server page</a>
 * for more information on this.
 *
 * @author generated by graphql-java-generator
 * @see <a href="https://github.com/graphql-java-generator/graphql-java-generator">https://github.com/graphql-java-generator/graphql-java-generator</a>
 */
@SuppressWarnings("unused")
public interface ${dataFetchersDelegate.pascalCaseName} {
	
#foreach ($dataFetcher in $dataFetchersDelegate.dataFetchers)
##
##
##
##
## If this dataFetcher needs a DataLoader parameter
#if ($dataFetcher.withDataLoader)
	/**
#if ($dataFetcher.field.description)
	 * Description for the ${dataFetcher.field.name} field: <br/>
#foreach ($line in $dataFetcher.field.description.lines)
	 * ${line}
#end
	 * <br/>
	 * 
#end
	 * This method loads the data for ${dataFetcher.field.owningType.name}.${dataFetcher.field.name}. It is called by 
	 * the ${dataFetcher.graphQLOriginType.name}Controller, which is <a href="https://docs.spring.io/spring-graphql/reference/controllers.html">spring-graphql 
	 * controller</a>. It may return whatever is accepted by the Spring Controller, that is:
	 * <ul>
	 * <li>A resolved value of any type</li>
	 * <li>Mono and Flux for asynchronous value(s). Supported for controller methods and for any DataFetcher as described in Reactive DataFetcher.</li>
	 * <li>Kotlin coroutine and Flow are adapted to Mono and Flux</li>
	 * <li>java.util.concurrent.Callable to have the value(s) produced asynchronously. For this to work, AnnotatedControllerConfigurer must be 
	 *     configured with an Executor</li>
	 * <li>(not directlty documented) A CompletableFuture<?>, for instance CompletableFuture<${dataFetcher.field.javaTypeFullClassname}>. This 
	 *     allows to use <A HREF="https://github.com/graphql-java/java-dataloader">graphql-java java-dataloader</A> to highly optimize the
	 *     number of requests to the server. The principle is this one: The data loader collects all the data to load, avoid to load several 
	 *     times the same data, and allows parallel execution of the queries, if multiple queries are to be run.</li>
	 * <li></li>
	 * </ul>
	 * 
	 * <BR/>
	 * You can implements this method like this:
	 * <PRE>
	 * @Override
	 * public CompletableFuture<${dataFetcher.field.javaTypeFullClassname}> ${dataFetcher.javaName}(
	 *         DataFetchingEnvironment environment, 
	 *         DataLoader<${dataFetcher.field.type.identifier.javaTypeFullClassname}, ${dataFetcher.field.type.classFullName}> dataLoader#if($dataFetcher.graphQLOriginType), 
	 *         ${dataFetcher.graphQLOriginType.classFullName} origin#end#foreach($argument in $dataFetcher.field.inputParameters), 
	 *         #appliedDirectives(${argument.appliedDirectives}, "			")
	 *         ${argument.javaTypeFullClassname} ${argument.javaName}#end
	 * ) {
	 *     List<${configuration.javaTypeForIDType}> ${dataFetcher.javaName} = origin.get${dataFetcher.pascalCaseName}();
	 *     return dataLoader.loadMany(${dataFetcher.javaName});
	 * }
	 * </PRE>
	 * <BR/>
	 * 
	 * @param dataFetchingEnvironment 
	 *     The GraphQL {@link DataFetchingEnvironment}. It gives you access to the full GraphQL context for this DataFetcher
	 * @param dataLoader
	 *            The {@link DataLoader} allows to load several data in one query. It allows to solve the (n+1) queries
	 *            issues, and greatly optimizes the response time.<BR/>
	 *            You'll find more informations here: <A HREF=
	 *            "https://github.com/graphql-java/java-dataloader">https://github.com/graphql-java/java-dataloader</A>
#if($dataFetcher.graphQLOriginType)
	 * @param origin 
	 *    The object from which the field is fetch. In other word: the aim of this data fetcher is to fetch the ${dataFetcher.name} attribute
	 *    of the <I>origin</I>, which is an instance of {$dataFetcher.graphQLOriginType}. It depends on your data modle, but it typically contains 
	 *    the id to use in the query.
#end
#foreach($argument in $dataFetcher.field.inputParameters)
	 * @param ${argument.camelCaseName} 
	 *     The input parameter sent in the query by the GraphQL consumer, as defined in the GraphQL schema.
#end
	 * @throws NoSuchElementException 
	 *     This method may return a {@link NoSuchElementException} exception. In this case, the exception is trapped 
	 *     by the calling method, and the return is consider as null. This allows to use the {@link Optional#get()} method directly, without caring of 
	 *     whether or not there is a value. The generated code will take care of the {@link NoSuchElementException} exception. 
	 */
#appliedDirectives(${dataFetcher.field.appliedDirectives}, "	")
	public Object ${dataFetcher.javaName}(
			DataFetchingEnvironment dataFetchingEnvironment, 
			DataLoader<${dataFetcher.field.type.identifier.javaTypeFullClassname}, ${dataFetcher.field.type.classFullName}> dataLoader#if($dataFetcher.graphQLOriginType), 
			${dataFetcher.graphQLOriginType.classFullName} origin#end#foreach($argument in $dataFetcher.field.inputParameters), 
#appliedDirectives(${argument.appliedDirectives}, "			")
			${argument.javaTypeFullClassname} ${argument.javaName}#end);
#end ## #if (${dataFetcher.withDataLoader})

	/**
#if ($dataFetcher.field.description)
	 * Description for the ${dataFetcher.field.name} field: <br/>
#foreach ($line in $dataFetcher.field.description.lines)
	 * ${line}
#end
	 * <br/>
	 *
#end
	 * This method loads the data for ${dataFetcher.field.owningType.name}.${dataFetcher.field.name}. It may return whatever is 
	 * accepted by the Spring Controller, that is:
	 * <ul>
	 * <li>A resolved value of any type</li>
	 * <li>Mono and Flux for asynchronous value(s). Supported for controller methods and for any DataFetcher as described in Reactive DataFetcher.</li>
	 * <li>Kotlin coroutine and Flow are adapted to Mono and Flux</li>
	 * <li>java.util.concurrent.Callable to have the value(s) produced asynchronously. For this to work, AnnotatedControllerConfigurer must be 
	 *     configured with an Executor</li>
	 * </ul>
	 * As a complement to the spring-graphql documentation, you may also return:
	 * <ul>
	 * <li>A CompletableFuture<?>, for instance CompletableFuture<${dataFetcher.field.javaTypeFullClassname}>. This 
	 *     allows to use <A HREF="https://github.com/graphql-java/java-dataloader">graphql-java java-dataloader</A> to highly optimize the
	 *     number of requests to the server. The principle is this one: The data loader collects all the data to load, avoid to load several 
	 *     times the same data, and allows parallel execution of the queries, if multiple queries are to be run.</li>
	 * <li>A Publisher (instead of a Flux), for Subscription for instance</li>
	 * </ul>
	 * For instance, your method may return:
	 * <ul>
#if ($dataFetchersDelegate.type.requestType == "subscription")
	 * <li>Flux<#if($dataFetcher.field.fieldTypeAST.mandatory==false)Optional<#end${dataFetcher.field.javaTypeFullClassname}#if($dataFetcher.field.fieldTypeAST.mandatory==false)>#end></li>
	 * <li>Publisher<#if($dataFetcher.field.fieldTypeAST.mandatory==false)Optional<#end${dataFetcher.field.javaTypeFullClassname}#if($dataFetcher.field.fieldTypeAST.mandatory==false)>#end></li>
#else
	 * <li>Mono<#if($dataFetcher.field.fieldTypeAST.mandatory==false)Optional<#end${dataFetcher.field.javaTypeFullClassname}#if($dataFetcher.field.fieldTypeAST.mandatory==false)>#end></li>
	 * <li>CompletableFuture<${dataFetcher.field.javaTypeFullClassname}></li>
	 * <li>${dataFetcher.field.javaTypeFullClassname}</li>
#end
	 * </ul> 
	 * 
	 * @param dataFetchingEnvironment 
	 *     The GraphQL {@link DataFetchingEnvironment}. It gives you access to the full GraphQL context for this DataFetcher
#if($dataFetcher.graphQLOriginType)
	 * @param origin 
	 *    The object from which the field is fetch. In other word: the aim of this data fetcher is to fetch the ${dataFetcher.name} attribute
	 *    of the <I>origin</I>, which is an instance of {$dataFetcher.graphQLOriginType}. It depends on your data modle, but it typically contains 
	 *    the id to use in the query.
#end
#foreach($argument in $dataFetcher.field.inputParameters)
	 * @param ${argument.camelCaseName} 
	 *     The input parameter sent in the query by the GraphQL consumer, as defined in the GraphQL schema.
#end
	 * @throws NoSuchElementException 
	 *     This method may return a {@link NoSuchElementException} exception. In this case, the exception is trapped 
	 *     by the calling method, and the return is consider as null. This allows to use the {@link Optional#get()} method directly, without caring of 
	 *     whether or not there is a value. The generated code will take care of the {@link NoSuchElementException} exception. 
	 */
#appliedDirectives(${dataFetcher.field.appliedDirectives}, "	")
	public Object ${dataFetcher.javaName}(
			DataFetchingEnvironment dataFetchingEnvironment#if($dataFetcher.graphQLOriginType),
			${dataFetcher.graphQLOriginType.classFullName} origin#end#foreach($argument in $dataFetcher.field.inputParameters),
#appliedDirectives(${argument.appliedDirectives}, "			")
			${argument.javaTypeFullClassname} ${argument.javaName}#end);
##
##
##
##
##
##
##
##
##
##

#end   ## #foreach ($dataFetcher in $dataFetchersDelegate.dataFetchers)
##
##
##
#foreach ($batchLoader in $dataFetchersDelegate.batchLoaders)
	/**
#if ($dataFetcher.field.description)
	 * Description for the ${dataFetcher.field.name} field: <br/>
#foreach ($line in $dataFetcher.field.description.lines)
	 * ${line}
#end
	 * <br/>
	 * 
#end
	 * This method loads a list of ${dataFetcher.field.name}, based on the list of id to be fetched. This method is used by
	 * <A HREF="https://github.com/graphql-java/java-dataloader">graphql-java java-dataloader</A> to highly optimize the
	 * number of requests to the server, when recursing down through the object associations.<BR/>
	 * You can find more information on this page:
	 * <A HREF="https://www.graphql-java.com/documentation/batching/">graphql-java batching</A><BR/>
	 * <B>Important notes:</B> 
	 * <UL>
	 * <LI>The list returned by this method must be sorted in the exact same order as the given <i>keys</i> list. If values 
	 * are missing (no value for a given key), then the returned list must contain a null value at this key's position.</LI>
	 * <LI>One of <code>batchLoader</code> or <code>unorderedReturnBatchLoader</code> must be overriden in the data fetcher 
	 * implementation. If not, then a NullPointerException will be thrown at runtime, with a proper error message.</LI>
	 * <LI>If your data storage implementation makes it complex to return values in the same order as the keys list, then it's 
	 * easier to override <code>unorderedReturnBatchLoader</code>, and let the default implementation of 
	 * <code>batchLoader</code> order the values</LI>
	 * </UL> 
	 * 
	 * @param keys
	 *            A list of ${batchLoader.type.identifier.type.name}'s id, for which the matching objects must be returned
#if($configuration.generateBatchLoaderEnvironment)
	 * @param environment
	 *            The Data Loader environment
#end
	 * @return A list of ${batchLoader.type.identifier.type.name}s
	 */
	default public List<${batchLoader.type.classFullName}> batchLoader(List<${batchLoader.type.identifier.javaTypeFullClassname}> keys#if($configuration.generateBatchLoaderEnvironment), BatchLoaderEnvironment environment#end) {
		List<${batchLoader.type.classFullName}> ret = unorderedReturnBatchLoader(keys#if($configuration.generateBatchLoaderEnvironment), environment#end);
		if (ret == null)
			throw new NullPointerException("Either batchLoader or unorderedReturnBatchLoader must be overriden in ${dataFetchersDelegate.pascalCaseName} implementation. And unorderedReturnBatchLoader must return a list."); //$NON-NLS-1$
		return GraphqlUtils.graphqlUtils.orderList(keys, ret, "${batchLoader.type.identifier.javaName}"); //$NON-NLS-1$
	}

	/**
#if ($dataFetcher.field.description)
	  * Description for the ${dataFetcher.field.name} field: <br/>
#foreach ($line in $dataFetcher.field.description.lines)
	  * ${line}
#end
	 * <br/>
	 * 
#end
	 * This method loads a list of ${dataFetcher.field.name}, based on the list of id to be fetched. This method is used by
	 * <A HREF="https://github.com/graphql-java/java-dataloader">graphql-java java-dataloader</A> to highly optimize the
	 * number of requests to the server, when recursing down through the object associations.<BR/>
	 * You can find more information on this page:
	 * <A HREF="https://www.graphql-java.com/documentation/batching/">graphql-java batching</A><BR/>
	 * <B>Important notes:</B> 
	 * <UL>
	 * <LI>The list returned may be in any order: this method is called by the default implementation of <code>batchLoader</code>, 
	 * which will sort the value return by this method, according to the given <i>keys</i> list.</LI>
	 * <LI>There may be missing values (no value for a given key): the default implementation of <code>batchLoader</code> will
	 * replace these missing values by a null value at this key's position.</LI>
	 * <LI>One of <code>batchLoader</code> or <code>unorderedReturnBatchLoader</code> must be overriden in the data fetcher 
	 * implementation. If not, then a NullPointerException will be thrown at runtime, with a proper error message.</LI>
	 * <LI>If your data storage implementation makes it complex to return values in the same order as the keys list, then it's 
	 * easier to override <code>unorderedReturnBatchLoader</code>, and let the default implementation of 
	 * <code>batchLoader</code> order the values</LI>
	 * <LI>If your data storage implementation makes it easy to return values in the same order as the keys list, then the  
	 * execution is a little quicker if you override <code>batchLoader</code>, as there would be no sort of the returned list.</LI>
	 * </UL> 
	 * 
	 * @param keys
	 *            A list of ${batchLoader.type.identifier.type.name}'s id, for which the matching objects must be returned
#if($configuration.generateBatchLoaderEnvironment)
	 * @param environment
	 *            The Data Loader environment
#end
	 * @return
	 */
	default public List<${batchLoader.type.classFullName}> unorderedReturnBatchLoader(List<${batchLoader.type.identifier.javaTypeFullClassname}> keys#if($configuration.generateBatchLoaderEnvironment), BatchLoaderEnvironment environment#end) {
		return null;
	}

#end
}
