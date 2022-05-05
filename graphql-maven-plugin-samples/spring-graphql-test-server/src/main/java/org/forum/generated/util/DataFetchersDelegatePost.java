/** Generated by the default template from graphql-java-generator */
package org.forum.generated.util;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import org.dataloader.BatchLoaderEnvironment;
import org.dataloader.DataLoader;
import org.reactivestreams.Publisher;

import com.graphql_java_generator.util.GraphqlUtils;

import graphql.schema.DataFetchingEnvironment;
import org.dataloader.BatchLoaderEnvironment;

/**
 * @author generated by graphql-java-generator
 * @see <a href="https://github.com/graphql-java-generator/graphql-java-generator">https://github.com/graphql-java-generator/graphql-java-generator</a>
 */
@SuppressWarnings("unused")
public interface DataFetchersDelegatePost {
	
	/**
	 * This method loads the data for Post.author. 
	 * <BR/>
	 * For optimization, this method returns a CompletableFuture. This allows to use 
	 * <A HREF="https://github.com/graphql-java/java-dataloader">graphql-java java-dataloader</A> to highly optimize the
	 * number of requests to the server.<BR/>
	 * The principle is this one: The data loader collects all the data to load, avoid to load several times the same data, 
	 * and allows parallel execution of the queries, if multiple queries are to be run.<BR/>
	 * You can implements this method like this:
	 * <PRE>
	 * @Override
	 * public CompletableFuture<List<Character>> friends(DataFetchingEnvironment environment, DataLoader<Long, Member> dataLoader, Human origin) {
	 *     List<java.lang.Long> friendIds = origin.getFriendIds();
	 *     DataLoader<java.lang.Long, CharacterImpl> dataLoader = environment.getDataLoader("Character");
	 *     return dataLoader.loadMany(friendIds);
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
	 * @param origin 
	 *    The object from which the field is fetch. In other word: the aim of this data fetcher is to fetch the author attribute
	 *    of the <I>origin</I>, which is an instance of {ObjectType {name:Post, fields:{Field{name:id, type:ID!, params:[]},Field{name:date, type:Date!, params:[]},Field{name:author, type:Member, params:[]},Field{name:publiclyAvailable, type:Boolean, params:[]},Field{name:title, type:String!, params:[]},Field{name:content, type:String!, params:[]},Field{name:authorId, type:ID, params:[]},Field{name:topicId, type:ID, params:[]}}, comments ""}. It depends on your data modle, but it typically contains 
	 *    the id to use in the query.
	 * @throws NoSuchElementException 
	 *     This method may return a {@link NoSuchElementException} exception. In this case, the exception is trapped 
	 *     by the calling method, and the return is consider as null. This allows to use the {@link Optional#get()} method directly, without caring of 
	 *     whether or not there is a value. The generated code will take care of the {@link NoSuchElementException} exception. 
	 */
	public CompletableFuture<org.forum.generated.Member> author(
			DataFetchingEnvironment dataFetchingEnvironment, 
			DataLoader<java.lang.Long, org.forum.generated.Member> dataLoader, 
			org.forum.generated.Post origin);
 
	/**
	 * This method loads the data for Post.author. 
	 * <BR/>
	 * 
	 * @param dataFetchingEnvironment 
	 *     The GraphQL {@link DataFetchingEnvironment}. It gives you access to the full GraphQL context for this DataFetcher
	 * @param origin 
	 *    The object from which the field is fetch. In other word: the aim of this data fetcher is to fetch the author attribute
	 *    of the <I>origin</I>, which is an instance of {ObjectType {name:Post, fields:{Field{name:id, type:ID!, params:[]},Field{name:date, type:Date!, params:[]},Field{name:author, type:Member, params:[]},Field{name:publiclyAvailable, type:Boolean, params:[]},Field{name:title, type:String!, params:[]},Field{name:content, type:String!, params:[]},Field{name:authorId, type:ID, params:[]},Field{name:topicId, type:ID, params:[]}}, comments ""}. It depends on your data modle, but it typically contains 
	 *    the id to use in the query.
	 * @throws NoSuchElementException 
	 *     This method may return a {@link NoSuchElementException} exception. In this case, the exception is trapped 
	 *     by the calling method, and the return is consider as null. This allows to use the {@link Optional#get()} method directly, without caring of 
	 *     whether or not there is a value. The generated code will take care of the {@link NoSuchElementException} exception. 
	 */
	public org.forum.generated.Member author(DataFetchingEnvironment dataFetchingEnvironment, org.forum.generated.Post origin);


	/**
	 * This method loads a list of ${dataFetcher.field.name}, based on the list of id to be fetched. This method is used by
	 * <A HREF="https://github.com/graphql-java/java-dataloader">graphql-java java-dataloader</A> to highly optimize the
	 * number of requests to the server, when recursing down through the object associations.<BR/>
	 * You can find more information on this page:
	 * <A HREF="https://www.graphql-java.com/documentation/master/batching/">graphql-java batching</A><BR/>
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
	 *            A list of ID's id, for which the matching objects must be returned
	 * @param environment
	 *            The Data Loader environment
	 * @return A list of IDs
	 */
	default public List<org.forum.generated.Post> batchLoader(List<java.lang.Long> keys, BatchLoaderEnvironment environment) {
		List<org.forum.generated.Post> ret = unorderedReturnBatchLoader(keys, environment);
		if (ret == null)
			throw new NullPointerException("Either batchLoader or unorderedReturnBatchLoader must be overriden in DataFetchersDelegatePost implementation. And unorderedReturnBatchLoader must return a list.");
		return GraphqlUtils.graphqlUtils.orderList(keys, ret, "id");
	}

	/**
	 * This method loads a list of ${dataFetcher.field.name}, based on the list of id to be fetched. This method is used by
	 * <A HREF="https://github.com/graphql-java/java-dataloader">graphql-java java-dataloader</A> to highly optimize the
	 * number of requests to the server, when recursing down through the object associations.<BR/>
	 * You can find more information on this page:
	 * <A HREF="https://www.graphql-java.com/documentation/master/batching/">graphql-java batching</A><BR/>
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
	 *            A list of ID's id, for which the matching objects must be returned
	 * @param environment
	 *            The Data Loader environment
	 * @return
	 */
	default public List<org.forum.generated.Post> unorderedReturnBatchLoader(List<java.lang.Long> keys, BatchLoaderEnvironment environment) {
		return null;
	}

}
