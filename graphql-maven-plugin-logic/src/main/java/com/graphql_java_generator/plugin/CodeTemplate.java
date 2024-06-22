package com.graphql_java_generator.plugin;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * Enumeration that defines the available templates for code generation
 * 
 * @author ggomez
 *
 */
@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum CodeTemplate {

	// Common files (alphabetic order)
	ENUM(CodeTemplateScope.COMMON, "templates/enum_type.vm.java"), //
	INTERFACE(CodeTemplateScope.COMMON, "templates/interface_type.vm.java"), //
	OBJECT(CodeTemplateScope.COMMON, "templates/object_type.vm.java"), //
	UNION(CodeTemplateScope.COMMON, "templates/union_type.vm.java"),

	// Client files (alphabetic order)
	CUSTOM_SCALAR_REGISTRY_INITIALIZER(CodeTemplateScope.CLIENT,
			"templates/client_CustomScalarRegistryInitializer.vm.java"), //
	DIRECTIVE_REGISTRY_INITIALIZER(CodeTemplateScope.CLIENT, "templates/client_DirectiveRegistryInitializer.vm.java"), //
	GRAPHQL_REQUEST(CodeTemplateScope.CLIENT, "templates/client_GraphQLRequest.vm.java"), //
	GRAPHQL_REACTIVE_REQUEST(CodeTemplateScope.CLIENT, "templates/client_GraphQLReactiveRequest.vm.java"), //
	JACKSON_DESERIALIZERS(CodeTemplateScope.CLIENT, "templates/client_jackson_deserializers.vm.java"), //
	JACKSON_SERIALIZERS(CodeTemplateScope.CLIENT, "templates/client_jackson_serializers.vm.java"), //
	QUERY_MUTATION(CodeTemplateScope.CLIENT, "templates/client_query_mutation_type.vm.java"), //
	QUERY_MUTATION_EXECUTOR(CodeTemplateScope.CLIENT, "templates/client_query_mutation_executor.vm.java"), //
	QUERY_MUTATION_REACTIVE_EXECUTOR(CodeTemplateScope.CLIENT,
			"templates/client_query_mutation_reactive_executor.vm.java"), //
	QUERY_RESPONSE(CodeTemplateScope.CLIENT, "templates/client_query_mutation_subscription_response.vm.java"), //
	ROOT_RESPONSE(CodeTemplateScope.CLIENT, "templates/client_query_mutation_subscription_rootResponse.vm.java"), //
	SPRING_AUTOCONFIGURATION_DEFINITION_FILE(CodeTemplateScope.CLIENT,
			"templates/client_spring_autoconfiguration_definition.vm.properties"), //
	CLIENT_SPRING_AUTO_CONFIGURATION_CLASS(CodeTemplateScope.CLIENT,
			"templates/client_spring_auto_configuration.vm.java"), //
	SUBSCRIPTION(CodeTemplateScope.CLIENT, "templates/client_subscription_type.vm.java"), //
	SUBSCRIPTION_EXECUTOR(CodeTemplateScope.CLIENT, "templates/client_subscription_executor.vm.java"), //
	SUBSCRIPTION_REACTIVE_EXECUTOR(CodeTemplateScope.CLIENT, "templates/client_subscription_reactive_executor.vm.java"), //
	TYPE_MAPPING(CodeTemplateScope.CLIENT, "templates/client_type_mapping.vm.java"), //
	TYPE_MAPPING_CSV(CodeTemplateScope.CLIENT, "templates/client_type_mapping.vm.csv"), //

	// Server files (alphabetic order)
	DATA_FETCHER_DELEGATE(CodeTemplateScope.SERVER, "templates/server_GraphQLDataFetchersDelegate.vm.java"), //
	DATA_FETCHERS_DELEGATES_REGISTRY(CodeTemplateScope.SERVER,
			"templates/server_DataFetchersDelegatesRegistry.vm.java"), //
	ENTITY_CONTROLLER(CodeTemplateScope.SERVER, "templates/server_EntityController.vm.java"), //
	SERVER(CodeTemplateScope.SERVER, "templates/server_GraphQLServerMain.vm.java"), //
	SERVER_SPRING_AUTO_CONFIGURATION_CLASS(CodeTemplateScope.SERVER,
			"templates/server_spring_auto_configuration.vm.java"), //
	WIRING(CodeTemplateScope.COMMON, "templates/GraphQLWiring.vm.java"), //

	// Template for the GraphQL relay schema generation
	RELAY_SCHEMA(CodeTemplateScope.GENERATE_RELAY_SCHEMA, "templates/generateRelaySchema.vm.graphqls");

	/**
	 * The scope for this template
	 */
	@NonNull
	private CodeTemplateScope scope;

	/**
	 * The default value to use
	 */
	@NonNull
	private String defaultPath;

}
