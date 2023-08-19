##
## Velocity template for the executor of a Subscription type (client side). 
##
## The generated class contains:
## - All the utility classes that allow to prepare and execute the subscription
##
##
## This template has these inputs:
## packageUtilName 			The package where this class must be generated
## configuration		The plugin's configuration
## object					The subscription type, for which this executor is being generated
##
#################################################################################################################
## Import of common.vm  (commons Velocity macro and definitions)
#################################################################################################################
#parse ("templates/common.vm")
##
##
/** Generated by the default template from graphql-java-generator */
package ${packageUtilName};
##
## The inputParams macro lists the input parameters for a field
#macro(inputParams)
#foreach ($inputParameter in $field.inputParameters)
#appliedDirectives(${inputParameter.appliedDirectives}, "			")
			${inputParameter.javaTypeFullClassname} ${inputParameter.javaName},
#end
#end
##
## The inputValues macro lists the input values for the parameters for a field
#macro(inputValues)#foreach ($inputParameter in $field.inputParameters), ${inputParameter.javaName}#end#end
##
##
##
#if($configuration.generateDeprecatedRequestResponse)
#set ($executionResponse = "${object.name}Response")
#else
#set ($executionResponse = "${configuration.packageName}.${object.classSimpleName}")
#end

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.graphql.client.GraphQlClient;
import org.springframework.stereotype.Component;
import com.graphql_java_generator.annotation.GraphQLDirective;
import com.graphql_java_generator.annotation.GraphQLNonScalar;
import com.graphql_java_generator.annotation.GraphQLScalar;
import com.graphql_java_generator.exception.GraphQLRequestExecutionException;
import com.graphql_java_generator.exception.GraphQLRequestPreparationException;
import com.graphql_java_generator.util.GraphqlUtils;
import com.graphql_java_generator.client.GraphqlClientUtils;
import com.graphql_java_generator.client.GraphQLObjectMapper;
import com.graphql_java_generator.client.GraphQLSubscriptionExecutor;
import com.graphql_java_generator.client.SubscriptionCallback;
import com.graphql_java_generator.client.SubscriptionClient;
import com.graphql_java_generator.client.request.InputParameter;
import com.graphql_java_generator.client.request.InputParameter.InputParameterType;
import com.graphql_java_generator.client.request.ObjectResponse;

#foreach($import in ${object.importsForUtilityClasses})
import $import;
#end

/**
#if ($object.description)
#foreach ($line in $object.description.lines)
 * ${line}
#end
#end
 * 
 * This class contains the methods that allows the execution of the subscriptions that are defined in the ${object.name} of the GraphQL schema.<BR/>
 * These methods allows:
 * <UL>
 * <LI>Preparation of partial subscription requests</LI>
 * <LI>Execution of partial prepared subscription requests</LI>
 * <LI>Execution of partial direct subscription requests</LI>
 * </UL>
 * You'll find all the documentation on the <A HREF="https://graphql-maven-plugin-project.graphql-java-generator.com/client_subscription.html">subscription client page doc</A>.
 * 
 * @author generated by graphql-java-generator
 * @see <a href="https://github.com/graphql-java-generator/graphql-java-generator">https://github.com/graphql-java-generator/graphql-java-generator</a>
 */
@Component
@SuppressWarnings("unused")
public class ${object.name}Executor${springBeanSuffix}  implements GraphQLSubscriptionExecutor {

	/** Logger for this class */
	private static Logger logger = LoggerFactory.getLogger(${object.name}Executor${springBeanSuffix}.class);

	@Autowired
	@Qualifier("webSocketGraphQlClient${springBeanSuffix}")
	GraphQlClient graphQlClient;

	GraphqlUtils graphqlUtils = GraphqlUtils.graphqlUtils; // must be set that way, to be used in the constructor
	
	@Autowired
	GraphqlClientUtils graphqlClientUtils;

	public ${object.name}Executor${springBeanSuffix}() {
## The @..@ is the placeholder for the maven resource filtering
		if (!"@project.version@".equals(this.graphqlUtils.getRuntimeVersion())) { //$NON-NLS-1$
			throw new RuntimeException("The GraphQL runtime version doesn't match the GraphQL plugin version. The runtime's version is '" //$NON-NLS-1$
					+ this.graphqlUtils.getRuntimeVersion() 
					+ "' whereas the GraphQL plugin version is '@project.version@'"); //$NON-NLS-1$
		}
		CustomScalarRegistryInitializer.initCustomScalarRegistry();
		DirectiveRegistryInitializer.initDirectiveRegistry();
	}

	/**
	 * This method takes a <B>full request</B> definition, and executes the it against the GraphQL server. That is,
	 * the request contains the full GraphQL requests, including the query/mutation/subscription keyword.<BR/>
	 * It offers a logging of the call (if in debug mode), or of the call and its parameters (if in trace mode).<BR/>
	 * For instance:
	 * 
	 * <PRE>
	 * Map<String, Object> params = new HashMap<>();
	 * params.put("heroParam", heroParamValue);
	 * params.put("skip", Boolean.FALSE);
	 * 
	 * MyQueryType response = myQueryType.execWithBindValues(
	 * 		"subscription {subscribeToNewHeros {id name}}",
	 * 		params);
	 * Character c = response.getHero();
	 * </PRE>
	 * 
	 * @param queryResponseDef
	 *            The response definition of the ${object.requestType}, in the native GraphQL format (see here above). It must ommit the
	 *            query/mutation/subscription keyword, and start by the first { that follows.It may contain directives,
	 *            as explained in the GraphQL specs.
	 * @param parameters
	 *            The map of values, for the bind variables defined in the query. If there is no bind variable in the
	 *            defined Query, this argument may be null or an empty {@link Map}. The key is the parameter name, as
	 *            defined in the query (in the above sample: heroParam is an optional parameter and skip is a mandatory
	 *            one). The value is the parameter vale in its Java type (for instance a {@link Date} for the
	 *            {@link GraphQLScalarTypeDate}). The parameters which value is missing in this map will no be
	 *            transmitted toward the GraphQL server.
	 * @return
	 *            The {@link SubscriptionClient} that allows the caller to act on the subscribed subscription.
	 * @throws GraphQLRequestPreparationException
	 *             When an error occurs during the request preparation, typically when building the
	 *             {@link ObjectResponse}
	 * @throws GraphQLRequestExecutionException
	 *             When an error occurs during the request execution, typically a network error, an error from the
	 *             GraphQL server or if the server response can't be parsed
	 */
	public SubscriptionClient execWithBindValues(
			String queryResponseDef, 
			SubscriptionCallback<?> subscriptionCallback,
			Map<String, Object> parameters)
			throws GraphQLRequestExecutionException, GraphQLRequestPreparationException {
		logger.debug("Executing ${object.requestType} {} ", queryResponseDef); //$NON-NLS-1$
		ObjectResponse objectResponse = getResponseBuilder().withQueryResponseDef(queryResponseDef).build();
		return exec(objectResponse, subscriptionCallback, parameters);
	}

	/**
	 * This method takes a <B>full request</B> definition, and executes it against the GraphQL server. That is,
	 * the query contains the full string that <B><U>follows</U></B> the query/mutation/subscription keyword.<BR/>
	 * It offers a logging of the call (if in debug mode), or of the call and its parameters (if in trace mode).<BR/>
	 * For instance:
	 * 
	 * <PRE>
	 * MyQueryType response = myQueryType.execWithBindValues(
	 * 		"{hero(param:?heroParam) @include(if:true) {id name @skip(if: ?skip) appearsIn friends {id name}}}",
	 * 		"heroParam", heroParamValue, "skip", Boolean.FALSE);
	 * Character c = response.getHero();
	 * </PRE>
	 * 
	 * @param queryResponseDef
	 *            The response definition of the query, in the native GraphQL format (see here above). It must ommit the
	 *            query/mutation/subscription keyword, and start by the first { that follows.It may contain directives,
	 *            as explained in the GraphQL specs.
	 * @param paramsAndValues
	 *            This parameter contains all the name and values for the Bind Variables defined in the objectResponse
	 *            parameter, that must be sent to the server. Optional parameter may not have a value. They will be
	 *            ignored and not sent to the server. Mandatory parameter must be provided in this argument.<BR/>
	 *            This parameter contains an even number of parameters: it must be a series of name and values :
	 *            (paramName1, paramValue1, paramName2, paramValue2...)
	 * @return
	 *            The {@link SubscriptionClient} that allows the caller to act on the subscribed subscription.
	 * @throws GraphQLRequestPreparationException
	 *             When an error occurs during the request preparation, typically when building the
	 *             {@link ObjectResponse}
	 * @throws GraphQLRequestExecutionException
	 *             When an error occurs during the request execution, typically a network error, an error from the
	 *             GraphQL server or if the server response can't be parsed
	 */
	public SubscriptionClient exec(
			String queryResponseDef, 
			SubscriptionCallback<?> subscriptionCallback,
			Object... paramsAndValues)
			throws GraphQLRequestExecutionException, GraphQLRequestPreparationException {
		logger.debug("Executing ${object.requestType} {} ", queryResponseDef); //$NON-NLS-1$
		ObjectResponse objectResponse = getResponseBuilder().withQueryResponseDef(queryResponseDef).build();
		return execWithBindValues(objectResponse, subscriptionCallback, this.graphqlClientUtils.generatesBindVariableValuesMap(paramsAndValues));
	}

	/**
	 * This method takes a <B>full request</B> definition, and executes it against the GraphQL server. That is,
	 * the query contains the full string that <B><U>follows</U></B> the query/mutation/subscription keyword.<BR/>
	 * It offers a logging of the call (if in debug mode), or of the call and its parameters (if in trace mode).<BR/>
	 * For instance:
	 * 
	 * <PRE>
	 * ObjectResponse response;
	 * 
	 * public void setup() {
	 * 	// Preparation of the query
	 * 	objectResponse = myQueryType.getResponseBuilder()
	 * 			.withQueryResponseDef("{hero(param:?heroParam) @include(if:true) {id name @skip(if: ?skip) appearsIn friends {id name}}}").build();
	 * }
	 * 
	 * public void doTheJob() {
	 * ..
	 * Map<String, Object> params = new HashMap<>();
	 * params.put("heroParam", heroParamValue);
	 * params.put("skip", Boolean.FALSE);
	 * // This will set the value sinceValue to the sinceParam field parameter
	 * MyQueryType response = queryType.execWithBindValues(objectResponse, params);
	 * Character c = response.getHero();
	 * ...
	 * }
	 * </PRE>
	 * 
	 * @param objectResponse
	 *            The definition of the response format, that describes what the GraphQL server is expected to return
	 * @param parameters
	 *            The list of values, for the bind variables defined in the query. If there is no bind variable in the
	 *            defined Query, this argument may be null or an empty {@link Map}
	 * @return
	 *            The {@link SubscriptionClient} that allows the caller to act on the subscribed subscription.
	 * @throws GraphQLRequestExecutionException
	 *             When an error occurs during the request execution, typically a network error, an error from the
	 *             GraphQL server or if the server response can't be parsed
	 */
	@SuppressWarnings({ "unchecked", "static-method" })
	public SubscriptionClient execWithBindValues(
			ObjectResponse objectResponse, 
			SubscriptionCallback<?> subscriptionCallback,
			Map<String, Object> parameters)
			throws GraphQLRequestExecutionException {
		if (logger.isTraceEnabled()) {
			if (parameters == null) {
				logger.trace("Executing ${object.requestType} without parameters"); //$NON-NLS-1$
			} else {
				StringBuilder sb = new StringBuilder("Executing root ${object.requestType} with parameters: "); //$NON-NLS-1$
				boolean addComma = false;
				for (String key : parameters.keySet()) {
					sb.append(key).append(":").append(parameters.get(key)); //$NON-NLS-1$
					if (addComma)
						sb.append(", "); //$NON-NLS-1$
					addComma = true;
				}
				logger.trace(sb.toString());
			}
		} else if (logger.isDebugEnabled()) {
			logger.debug("Executing ${object.requestType} '${object.name}'"); //$NON-NLS-1$
		}

		// Given values for the BindVariables
		Map<String, Object> parametersLocal = (parameters != null) ? parameters : new HashMap<>();

		// The subscription may only subscribe to one subscription at a time, even for a full request.
		// Let's check that, and find the type returned by this subscription (that is: the type of the notifications
		// that will be received)
		//
		// The subscription must query only one subscription
		if (objectResponse.getQuery() != null || objectResponse.getMutation() != null) {
			throw new GraphQLRequestExecutionException(
					"This method may only be called for subscription, but the given GraphQL request is a " //$NON-NLS-1$
							+ (objectResponse.getQuery() != null ? "query" : "mutation")); //$NON-NLS-1$ //$NON-NLS-2$
		}
		if (objectResponse.getSubscription() == null) {
			throw new GraphQLRequestExecutionException(
					"This method may only be called for subscription, but the given GraphQL request has no mutation field"); //$NON-NLS-1$
		}
		if (objectResponse.getSubscription().getFields().size() != 1) {
			throw new GraphQLRequestExecutionException(
					"Full Request for subscription may only be called for one subscription, but the given GraphQL request has " //$NON-NLS-1$
							+ objectResponse.getSubscription().getFields().size() + " subscription fields"); //$NON-NLS-1$
		}
		
		// It's probably possible to do much better than this switch!  
		// If someone has a better idea to call this parameterized method, please come in.
		switch (objectResponse.getSubscription().getFields().get(0).getName()) {
#foreach ($field in $object.fields)
#if ($field.name != "__typename")
		case "${field.name}": //$NON-NLS-1$
			return objectResponse.exec(parametersLocal,
					 (SubscriptionCallback<#if($field.fieldTypeAST.listDepth>0)List#else${field.javaTypeFullClassname}#end>) subscriptionCallback, 
					 ${executionResponse}.class, 
					 #if($field.fieldTypeAST.listDepth>0)List#else${field.javaTypeFullClassname}#end.class);
#end
#end
		default:
			throw new GraphQLRequestExecutionException("Unexpected field name: " + objectResponse.getSubscription().getFields().get(0).getName()); //$NON-NLS-1$
		}
	}

	/**
	 * This method takes a <B>full request</B> definition, and executes it against the GraphQL server. That is,
	 * the query contains the full string that <B><U>follows</U></B> the query/mutation/subscription keyword.<BR/>
	 * It offers a logging of the call (if in debug mode), or of the call and its parameters (if in trace mode).<BR/>
	 * For instance:
	 * 
	 * <PRE>
	 * ObjectResponse response;
	 * 
	 * public void setup() {
	 * 	// Preparation of the query
	 * 	 objectResponse = myQueryType.getResponseBuilder()
	 * 			.withQueryResponseDef("{hero(param:?heroParam) @include(if:true) {id name @skip(if: ?skip) appearsIn friends {id name}}}").build();
	 * }
	 * 
	 * public void doTheJob() {
	 * ..
	 * // This will set the value sinceValue to the sinceParam field parameter
	 * MyQueryType response = queryType.exec(objectResponse, "heroParam", heroParamValue, "skip", Boolean.FALSE);
	 * Character c = response.getHero();
	 * ...
	 * }
	 * </PRE>
	 * 
	 * @param objectResponse
	 *            The definition of the response format, that describes what the GraphQL server is expected to return
	 * @param paramsAndValues
	 *            This parameter contains all the name and values for the Bind Variables defined in the objectResponse
	 *            parameter, that must be sent to the server. Optional parameter may not have a value. They will be
	 *            ignored and not sent to the server. Mandatory parameter must be provided in this argument.<BR/>
	 *            This parameter contains an even number of parameters: it must be a series of name and values :
	 *            (paramName1, paramValue1, paramName2, paramValue2...)
	 * @return
	 *            The {@link SubscriptionClient} that allows the caller to act on the subscribed subscription.
	 * @throws GraphQLRequestExecutionException
	 *             When an error occurs during the request execution, typically a network error, an error from the
	 *             GraphQL server or if the server response can't be parsed
	 */
	public SubscriptionClient exec(
			ObjectResponse objectResponse, 
			SubscriptionCallback<?> subscriptionCallback,
			Object... paramsAndValues)
			throws GraphQLRequestExecutionException {
		return execWithBindValues(objectResponse, subscriptionCallback, this.graphqlClientUtils.generatesBindVariableValuesMap(paramsAndValues));
	}

	/**
	 * Get the {@link com.graphql_java_generator.client.request.Builder} for a <B>full request</B>, as expected by the exec 
	 * and execWithBindValues methods.
	 * 
	 * @return
	 * @throws GraphQLRequestPreparationException
	 */
	public com.graphql_java_generator.client.request.Builder getResponseBuilder() throws GraphQLRequestPreparationException {
		return new com.graphql_java_generator.client.request.Builder(this.graphQlClient, GraphQLRequest${springBeanSuffix}.class);
	}

	/**
	 * Get the {@link GraphQLRequest${springBeanSuffix}} for <B>full request</B>. For instance:
	 * <PRE>
	 * GraphQLRequest${springBeanSuffix} request = new GraphQLRequest${springBeanSuffix}(fullRequest);
	 * </PRE>
	 * 
	 * @param fullRequest The full GraphQL request, as specified in the GraphQL specification
	 * @return
	 * @throws GraphQLRequestPreparationException
	 */
	@SuppressWarnings("static-method")
	public GraphQLRequest${springBeanSuffix} getGraphQLRequest(String fullRequest) throws GraphQLRequestPreparationException {
		return new GraphQLRequest${springBeanSuffix}(fullRequest);
	}

#foreach ($field in $object.fields)
#if ($field.name != "__typename")
#foreach ($comment in $field.comments)
	// $comment
#end
	/**
#foreach ($line in $field.description.lines)
	 * $line
#end
	 *
	 * This method registers a subscription, by executing a direct partial request against the GraphQL server. This
	 * subscription is one of the fields defined in the GraphQL subscription object. The queryResponseDef contains the
	 * part of the subscription that <B><U>is after</U></B> the subscription name (see the sample below), for instance
	 * "{id name}" if you want these two fields to be sent in the notifications you'll receive for this
	 * subscription.<BR/>
	 * You must also provide a callback instance of the {@link SubscriptionCallback}, and the parameter for the
	 * subscription as parameter for this method. For instance, if the subscription subscribeToNewPost has one parameter
	 * <I>boardName</I> (as defined in the GraphQL schema):
	 * 
	 * <PRE>
	 * SubscriptionClient client;
	 * 
	 * void setup() {
	 * 	subscriptionType = new SubscriptionType("http://localhost:8180/graphql/subscription");
	 * }
	 * 
	 * void exec() {
	 * 	Map<String, Object> params = new HashMap<>();
	 * 	params.put("anOptionalParam", "a param value");
	 * 	// PostSubscriptionCallback implement SubscriptionCallback<Post>, as Post is the returned type for the
	 * 	// subscribeToNewPost subscription. Its onMessage(T) method will be called for each notification of this
	 * 	// subscription.
	 * 	client = subscriptionType.subscribeToNewPost(
	 * 			"{id date author publiclyAvailable title(param: ?anOptionalParam) content}",
	 * 			new PostSubscriptionCallback(), 
	 *          "Board name 1", // The parameter(s) of the subscription if any, are directly sent as parameter for this method
	 *          params // The bind variable you defined in your query are in this map.  
	 *          );
	 * }
	 * 
	 * void freeResources() {
	 * 	client.unsubscribe();
	 * }
	 * </PRE>
#if($graphqlUtils.isJavaReservedWords(${field.name}))
	 * This method name is prefixed by ${underscore}, as ${field.name} is a java reserved keyword. 
#end
	 * 
	 * @param queryResponseDef
	 *            The response definition of the subscription, in the native GraphQL format (see here above)
	 * @param subscriptionCallback
	 *            An instance of SubscriptionCallback<${field.type.classSimpleName}>. Its {@link SubscriptionCallback#onMessage(Object)} 
	 *            will be called for each notification received from this subscription.
#foreach ($inputParameter in $field.inputParameters)
#if (${inputParameter.description} && ${inputParameter.description.getContent()} != "")
	* @param ${inputParameter.name} ${inputParameter.description.getContent()}
#else
	* @param ${inputParameter.name} Parameter for the ${field.name} field of ${object.name}, as defined in the GraphQL schema
#end
#end
	 * @param parameters
	 *            The list of values, for the bind variables defined in the subscription. If there is no bind variable in the
	 *            defined subscription, this argument may be null or an empty {@link Map}
	 * @throws GraphQLRequestPreparationException
	 *             When an error occurs during the request preparation, typically when building the
	 *             {@link ObjectResponse}
	 * @throws GraphQLRequestExecutionException
	 *             When an error occurs during the request execution, typically a network error, an error from the
	 *             GraphQL server or if the server response can't be parsed
	 */
#appliedDirectives(${field.appliedDirectives}, "	")
	public SubscriptionClient ${field.javaName}WithBindValues(
			String queryResponseDef, 
			SubscriptionCallback<${field.javaTypeFullClassname}> subscriptionCallback,
#inputParams()
			Map<String, Object> parameters)
			throws GraphQLRequestExecutionException, GraphQLRequestPreparationException {
		ObjectResponse objectResponse = get${field.pascalCaseName}ResponseBuilder().withQueryResponseDef(queryResponseDef).build();
		return ${field.javaName}(objectResponse, subscriptionCallback#inputValues(), parameters);
	}

#foreach ($comment in $field.comments)
	// $comment
#end
	/**
#foreach ($line in $field.description.lines)
	 * $line
#end
	 *
	 * This method registers a subscription, by executing a direct partial request against the GraphQL server. This
	 * subscription is one of the fields defined in the GraphQL subscription object. The queryResponseDef contains the
	 * part of the subscription that <B><U>is after</U></B> the subscription name (see the sample below), for instance
	 * "{id name}" if you want these two fields to be sent in the notifications you'll receive for this
	 * subscription.<BR/>
	 * You must also provide a callback instance of the {@link SubscriptionCallback}, and the parameter for the
	 * subscription as parameter for this method. For instance, if the subscription subscribeToNewPost has one parameter
	 * <I>boardName</I> (as defined in the GraphQL schema):
	 * 
	 * <PRE>
	 * SubscriptionClient client;
	 * 
	 * void setup() {
	 * 	subscriptionType = new SubscriptionType("http://localhost:8180/graphql/subscription");
	 * }
	 * 
	 * void exec() {
	 * 	// PostSubscriptionCallback implement SubscriptionCallback<Post>, as Post is the returned type for the
	 * 	// subscribeToNewPost subscription. Its onMessage(T) method will be called for each notification of this
	 * 	// subscription.
	 * 	client = subscriptionType.subscribeToNewPost(
	 * 			"{id date author publiclyAvailable title(param: ?anOptionalParam) content}",
	 * 			new PostSubscriptionCallback(), 
	 *          "Board name 1", // The parameter(s) of the subscription if any, are directly sent as parameter for this method
	 *          "anOptionalParam", "a param value" // The bind variables that you've defined in your query are given as a listof couple of (name, value)  
	 *          );
	 * }
	 * 
	 * void freeResources() {
	 * 	client.unsubscribe();
	 * }
	 * </PRE>
#if($graphqlUtils.isJavaReservedWords(${field.name}))
	 * This method name is prefixed by ${underscore}, as ${field.name} is a java reserved keyword. 
#end
	 * 
	 * @param queryResponseDef
	 *            The response definition of the subscription, in the native GraphQL format (see here above)
	 * @param subscriptionCallback
	 *            An instance of SubscriptionCallback<${field.type.classSimpleName}>. Its {@link SubscriptionCallback#onMessage(Object)} 
	 *            will be called for each notification received from this subscription.
#foreach ($inputParameter in $field.inputParameters)
#if (${inputParameter.description} && ${inputParameter.description.getContent()} != "")
	* @param ${inputParameter.name} ${inputParameter.description.getContent()}
#else
	* @param ${inputParameter.name} Parameter for the ${field.name} field of ${object.name}, as defined in the GraphQL schema
#end
#end
	 * @param parameters
	 *            The list of values, for the bind variables defined in the subscription. If there is no bind variable in the
	 *            defined subscription, this argument may be null or an empty {@link Map}
	 * @throws GraphQLRequestPreparationException
	 *             When an error occurs during the request preparation, typically when building the
	 *             {@link ObjectResponse}
	 * @throws GraphQLRequestExecutionException
	 *             When an error occurs during the request execution, typically a network error, an error from the
	 *             GraphQL server or if the server response can't be parsed
	 */
#appliedDirectives(${field.appliedDirectives}, "	")
	public SubscriptionClient ${field.javaName}(String queryResponseDef, 
			SubscriptionCallback<${field.javaTypeFullClassname}> subscriptionCallback,
#inputParams()
			Object... paramsAndValues)
			throws GraphQLRequestExecutionException, GraphQLRequestPreparationException {
		logger.debug("Executing subscription '${field.name}'. queryResponseDef is '{}'", queryResponseDef); //$NON-NLS-1$
		ObjectResponse objectResponse = get${field.pascalCaseName}ResponseBuilder().withQueryResponseDef(queryResponseDef).build();
		return ${field.javaName}WithBindValues(objectResponse, subscriptionCallback#inputValues(), this.graphqlClientUtils.generatesBindVariableValuesMap(paramsAndValues));
	}

#foreach ($comment in $field.comments)
	// $comment
#end
	/**
#foreach ($line in $field.description.lines)
	 * $line
#end
	 *
	 * This method registers a subscription, by executing a direct partial request against the GraphQL server. This
	 * subscription is one of the fields defined in the GraphQL subscription object. The queryResponseDef contains the
	 * part of the subscription that <B><U>is after</U></B> the subscription name (see the sample below), for instance
	 * "{id name}" if you want these two fields to be sent in the notifications you'll receive for this
	 * subscription.<BR/>
	 * You must also provide a callback instance of the {@link SubscriptionCallback}, and the parameter for the
	 * subscription as parameter for this method. For instance, if the subscription subscribeToNewPost has one parameter
	 * <I>boardName</I> (as defined in the GraphQL schema):
	 * 
	 * <PRE>
	 * SubscriptionClient client;
	 * GraphQLRequest${springBeanSuffix} subscriptionRequest;
	 * 
	 * void setup() {
	 * 	subscriptionType = new SubscriptionType("http://localhost:8180/graphql/subscription");
	 *  subscriptionRequest = subscriptionType
	 *			.getSubscribeToNewPostGraphQLRequest("{id date author publiclyAvailable title(param: ?anOptionalParam) content}");
	 * }
	 * 
	 * void exec() {
	 * 	Map<String, Object> params = new HashMap<>();
	 * 	params.put("anOptionalParam", "a param value");
	 * 	// PostSubscriptionCallback implement SubscriptionCallback<Post>, as Post is the returned type for the
	 * 	// subscribeToNewPost subscription. Its onMessage(T) method will be called for each notification of this
	 * 	// subscription.
	 * 	client = subscriptionType.subscribeToNewPost(
	 * 			subscriptionRequest,
	 * 			new PostSubscriptionCallback(), 
	 *          "Board name 1", // The parameter(s) of the subscription if any, are directly sent as parameter for this method
	 *          params // The bind variable you defined in your query are in this map.  
	 *          );
	 * }
	 * 
	 * void freeResources() {
	 * 	client.unsubscribe();
	 * }
	 * </PRE>
#if($graphqlUtils.isJavaReservedWords(${field.name}))
	 * This method name is prefixed by ${underscore}, as ${field.name} is a java reserved keyword. 
#end
	 * 
	 * @param objectResponse
	 *            The definition of the response format, that describes what the GraphQL server is expected to return
	 * @param subscriptionCallback
	 *            An instance of SubscriptionCallback<${field.type.classSimpleName}>. Its {@link SubscriptionCallback#onMessage(Object)} 
	 *            will be called for each notification received from this subscription.
#foreach ($inputParameter in $field.inputParameters)
#if (${inputParameter.description} && ${inputParameter.description.getContent()} != "")
	* @param ${inputParameter.name} ${inputParameter.description.getContent()}
#else
	* @param ${inputParameter.name} Parameter for the ${field.name} field of ${object.name}, as defined in the GraphQL schema
#end
#end
	 * @param parameters
	 *            The list of values, for the bind variables defined in the subscription. If there is no bind variable in the
	 *            defined Query, this argument may be null or an empty {@link Map}
	 * @throws GraphQLRequestExecutionException
	 *             When an error occurs during the request execution, typically a network error, an error from the
	 *             GraphQL server or if the server response can't be parsed
	 */
#appliedDirectives(${field.appliedDirectives}, "	")
#if($field.fieldTypeAST.listDepth>0)
	@SuppressWarnings({"unchecked", "rawtypes", "static-method"})
#else 
	@SuppressWarnings("static-method")
#end
	public SubscriptionClient ${field.javaName}WithBindValues(ObjectResponse objectResponse,
			SubscriptionCallback<${field.javaTypeFullClassname}> subscriptionCallback,
#inputParams() 
			Map<String, Object> parameters)
			throws GraphQLRequestExecutionException  {
		if (logger.isTraceEnabled()) {
			logger.trace("Executing ${object.requestType} '${field.name}' with parameters: #foreach ($inputParameter in $field.inputParameters){}#if($foreach.hasNext),#end #end"#foreach ($inputParameter in $field.inputParameters), ${inputParameter.javaName}#end); //$NON-NLS-1$
		} else if (logger.isDebugEnabled()) {
			logger.debug("Executing ${object.requestType} '${field.name}'"); //$NON-NLS-1$
		}
	
		// Given values for the BindVariables
		Map<String, Object> parametersLocal = (parameters != null) ? parameters : new HashMap<>();
#foreach ($inputParameter in $field.inputParameters)
		parametersLocal.put("${object.camelCaseName}${field.pascalCaseName}${inputParameter.pascalCaseName}", ${inputParameter.javaName}); //$NON-NLS-1$
#end

#if($field.fieldTypeAST.listDepth>0)
		// This ugly double casting is necessary to make the code compile. If anyone has a better idea... please raise an issue
#end 
		return objectResponse.exec(parametersLocal,#if($field.fieldTypeAST.listDepth>0) (SubscriptionCallback<List>) (Object)#end subscriptionCallback, ${object.classFullName}.class, #if($field.fieldTypeAST.listDepth>0)List#else${field.javaTypeFullClassname}#end.class);
	}

#foreach ($comment in $field.comments)
	// $comment
#end
	/**
#foreach ($line in $field.description.lines)
	 * $line
#end
	 *
	 * This method registers a subscription, by executing a direct partial request against the GraphQL server. This
	 * subscription is one of the fields defined in the GraphQL subscription object. The queryResponseDef contains the
	 * part of the subscription that <B><U>is after</U></B> the subscription name (see the sample below), for instance
	 * "{id name}" if you want these two fields to be sent in the notifications you'll receive for this
	 * subscription.<BR/>
	 * You must also provide a callback instance of the {@link SubscriptionCallback}, and the parameter for the
	 * subscription as parameter for this method. For instance, if the subscription subscribeToNewPost has one parameter
	 * <I>boardName</I> (as defined in the GraphQL schema):
	 * 
	 * <PRE>
	 * SubscriptionClient client;
	 * GraphQLRequest${springBeanSuffix} subscriptionRequest;
	 * 
	 * void setup() {
	 * 	subscriptionType = new SubscriptionType("http://localhost:8180/graphql/subscription");
	 *  subscriptionRequest = subscriptionType
	 *			.getSubscribeToNewPostGraphQLRequest("{id date author publiclyAvailable title(param: ?anOptionalParam) content}");
	 * }
	 * 
	 * void exec() {
	 * 	Map<String, Object> params = new HashMap<>();
	 * 	params.put("anOptionalParam", "a param value");
	 * 	// PostSubscriptionCallback implement SubscriptionCallback<Post>, as Post is the returned type for the
	 * 	// subscribeToNewPost subscription. Its onMessage(T) method will be called for each notification of this
	 * 	// subscription.
	 * 	client = subscriptionType.subscribeToNewPost(
	 * 			subscriptionRequest,
	 * 			new PostSubscriptionCallback(), 
	 *          "Board name 1", // The parameter(s) of the subscription if any, are directly sent as parameter for this method
	 *          "anOptionalParam", "a param value" // The bind variables that you've defined in your query are given as a listof couple of (name, value)  
	 *          );
	 * }
	 * 
	 * void freeResources() {
	 * 	client.unsubscribe();
	 * }
	 * </PRE>
#if($graphqlUtils.isJavaReservedWords(${field.name}))
	 * This method name is prefixed by ${underscore}, as ${field.name} is a java reserved keyword. 
#end
	 * 
	 * @param objectResponse
	 *            The definition of the response format, that describes what the GraphQL server is expected to return
	 * @param subscriptionCallback
	 *            An instance of SubscriptionCallback<${field.type.classSimpleName}>. Its {@link SubscriptionCallback#onMessage(Object)} 
	 *            will be called for each notification received from this subscription.
#foreach ($inputParameter in $field.inputParameters)
#if (${inputParameter.description} && ${inputParameter.description.getContent()} != "")
	* @param ${inputParameter.name} ${inputParameter.description.getContent()}
#else
	* @param ${inputParameter.name} Parameter for the ${field.name} field of ${object.name}, as defined in the GraphQL schema
#end
#end
	 * @param paramsAndValues
	 *            This parameter contains all the name and values for the Bind Variables defined in the objectResponse
	 *            parameter, that must be sent to the server. Optional parameter may not have a value. They will be
	 *            ignored and not sent to the server. Mandatory parameter must be provided in this argument.<BR/>
	 *            This parameter contains an even number of parameters: it must be a series of name and values :
	 *            (paramName1, paramValue1, paramName2, paramValue2...)
	 * @throws GraphQLRequestExecutionException
	 *             When an error occurs during the request execution, typically a network error, an error from the
	 *             GraphQL server or if the server response can't be parsed
	 */
#appliedDirectives(${field.appliedDirectives}, "	")
#if($field.fieldTypeAST.listDepth>0)
	@SuppressWarnings({ "unchecked", "rawtypes" })
#end 
	public SubscriptionClient ${field.javaName}(ObjectResponse objectResponse,
			SubscriptionCallback<${field.javaTypeFullClassname}> subscriptionCallback,
#inputParams() 
			Object... paramsAndValues)
			throws GraphQLRequestExecutionException  {
		if (logger.isTraceEnabled()) {
			StringBuilder sb = new StringBuilder();
			sb.append("Executing subscription '${field.name}' with bind variables: "); //$NON-NLS-1$
			boolean addComma = false;
			for (Object o : paramsAndValues) {
				if (o != null) {
					sb.append(o.toString());
					if (addComma)
						sb.append(", "); //$NON-NLS-1$
					addComma = true;
				}
			}
			logger.trace(sb.toString());
		} else if (logger.isDebugEnabled()) {
			logger.debug("Executing subscription '${field.name}' (with bind variables)"); //$NON-NLS-1$
		}

		Map<String, Object> parametersLocal = this.graphqlClientUtils.generatesBindVariableValuesMap(paramsAndValues);
#foreach ($inputParameter in $field.inputParameters)
		parametersLocal.put("${object.camelCaseName}${field.pascalCaseName}${inputParameter.pascalCaseName}", ${inputParameter.javaName}); //$NON-NLS-1$
#end
		
		return objectResponse.exec(parametersLocal, #if($field.fieldTypeAST.listDepth>0) (SubscriptionCallback<List>) (Object)#end subscriptionCallback, ${object.classFullName}.class, #if($field.fieldTypeAST.listDepth>0)List#else${field.javaTypeFullClassname}#end.class);
	}

#foreach ($comment in $field.comments)
	// $comment
#end
	/**
#foreach ($line in $field.description.lines)
	 * $line
#end
	 *
	 * Get the {@link com.graphql_java_generator.client.request.Builder} for the ${field.type.classSimpleName}, as expected by the ${field.name} subscription.
	 * 
	 * @return
	 * @throws GraphQLRequestPreparationException
	 */
	public com.graphql_java_generator.client.request.Builder get${field.pascalCaseName}ResponseBuilder() throws GraphQLRequestPreparationException {
		return new com.graphql_java_generator.client.request.Builder(this.graphQlClient, GraphQLRequest${springBeanSuffix}.class, "${field.name}", RequestType.${object.requestType} //$NON-NLS-1$
#foreach ($inputParameter in $field.inputParameters)
			, InputParameter.newBindParameter("$springBeanSuffix", "${inputParameter.name}","${object.camelCaseName}${field.pascalCaseName}${inputParameter.pascalCaseName}",#if(${inputParameter.fieldTypeAST.mandatory}) InputParameterType.MANDATORY#else InputParameterType.OPTIONAL#end, "${inputParameter.graphQLTypeSimpleName}", ${inputParameter.fieldTypeAST.mandatory}, ${inputParameter.fieldTypeAST.listDepth}, ${inputParameter.fieldTypeAST.itemMandatory}) //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ 
#end
			);
	}

#foreach ($comment in $field.comments)
	// $comment
#end
	/**
#foreach ($line in $field.description.lines)
	 * $line
#end
	 * Get the {@link GraphQLRequest${springBeanSuffix}} for the ${field.name} $type, created with the given Partial request.
	 * 
	 * @param partialRequest
	 * 				The Partial GraphQL request, as explained in the 
	 * 				<A HREF="https://graphql-maven-plugin-project.graphql-java-generator.com/client.html">plugin client documentation</A> 
	 * @return
	 * @throws GraphQLRequestPreparationException
	 */
	public GraphQLRequest${springBeanSuffix} get${field.pascalCaseName}GraphQLRequest(String partialRequest) throws GraphQLRequestPreparationException {
		return new GraphQLRequest${springBeanSuffix}(this.graphQlClient,partialRequest, RequestType.${object.requestType}, "${field.name}" //$NON-NLS-1$
#foreach ($inputParameter in $field.inputParameters)
		, InputParameter.newBindParameter(
				"$springBeanSuffix", //$NON-NLS-1$
				"${inputParameter.name}", //$NON-NLS-1$
				"${object.camelCaseName}${field.pascalCaseName}${inputParameter.pascalCaseName}", //$NON-NLS-1$
				#if(${inputParameter.fieldTypeAST.mandatory}) InputParameterType.MANDATORY#else InputParameterType.OPTIONAL#end, 
				"${inputParameter.graphQLTypeSimpleName}", //$NON-NLS-1$
				${inputParameter.fieldTypeAST.mandatory}, 
				${inputParameter.fieldTypeAST.listDepth}, 
				${inputParameter.fieldTypeAST.itemMandatory})
#end
		);
	}
	
#end
#end
}
