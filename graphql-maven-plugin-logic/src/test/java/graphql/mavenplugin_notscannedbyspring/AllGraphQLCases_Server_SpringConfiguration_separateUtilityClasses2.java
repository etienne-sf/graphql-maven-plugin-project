/**
 * 
 */
package graphql.mavenplugin_notscannedbyspring;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

import com.graphql_java_generator.plugin.conf.CustomScalarDefinition;
import com.graphql_java_generator.plugin.conf.GenerateServerCodeConfiguration;
import com.graphql_java_generator.plugin.conf.PluginMode;
import com.graphql_java_generator.plugin.conf.QueryMutationExecutionProtocol;
import com.graphql_java_generator.plugin.test.helper.GraphQLConfigurationTestHelper;

/**
 * The Spring configuration used for JUnit tests
 * 
 * @author etienne-sf
 */
@Configuration
@ComponentScan(basePackages = "com.graphql_java_generator", excludeFilters = {
		@Filter(type = FilterType.REGEX, pattern = ".*\\.GenerateRelaySchema.*"),
		@Filter(type = FilterType.REGEX, pattern = ".*\\.GenerateGraphQLSchema.*"),
		@Filter(type = FilterType.REGEX, pattern = "com.graphql_java_generator.client.graphqlrepository.*") })
public class AllGraphQLCases_Server_SpringConfiguration_separateUtilityClasses2 extends AbstractSpringConfiguration {

	static List<CustomScalarDefinition> customScalars;
	static {
		customScalars = new ArrayList<>();
		customScalars.add(new CustomScalarDefinition("Base64String", "byte[]", null,
				"com.graphql_java_generator.customscalars.GraphQLScalarTypeBase64String.GraphQLBase64String", null));
		customScalars.add(new CustomScalarDefinition("CustomId", "com.generated.graphql.samples.customscalar.CustomId",
				null, "com.generated.graphql.samples.customscalar.GraphQLScalarTypeCustomId.CustomIdScalarType", null));
		customScalars.add(new CustomScalarDefinition("MyCustomScalarForADate", "java.util.Date", null,
				"com.graphql_java_generator.customscalars.GraphQLScalarTypeDate.Date", null));
		customScalars.add(new CustomScalarDefinition("MyCustomScalarForADateTime", "java.time.OffsetDateTime", null,
				"graphql.scalars.ExtendedScalars.DateTime", null));
		customScalars.add(new CustomScalarDefinition("Long", "java.lang.Long", null,
				"graphql.scalars.ExtendedScalars.GraphQLLong", null));
		customScalars.add(new CustomScalarDefinition("else", "java.lang.String", null,
				"com.graphql_java_generator.customscalars.GraphQLScalarTypeString.String", null));
		customScalars.add(new CustomScalarDefinition("JSON", "com.fasterxml.jackson.databind.node.ObjectNode", null,
				"graphql.scalars.ExtendedScalars.Json", null));
		customScalars.add(new CustomScalarDefinition("MyBoolean", "java.lang.Boolean", null,
				"com.generated.graphql.samples.customscalar.GraphQLScalarTypeMyBoolean.MyBooleanScalarType", null));
		customScalars.add(new CustomScalarDefinition("NonNegativeInt", "java.lang.Integer", null,
				"graphql.scalars.ExtendedScalars.NonNegativeInt", null));
		customScalars.add(new CustomScalarDefinition("Object", "java.lang.Object", null,
				"graphql.scalars.ExtendedScalars.Object", null));
	}

	@Override
	protected void addSpecificConfigurationParameterValue(GraphQLConfigurationTestHelper configuration) {
		// The allGraphQLCases GraphQL schema is located in the allGraphQLCases client sample
		configuration.schemaFileFolder = new File(this.mavenTestHelper.getModulePathFile(),
				"../graphql-maven-plugin-samples/graphql-maven-plugin-samples-allGraphQLCases-client/src/graphqls/allGraphQLCases");
		configuration.customScalars = customScalars;

		// Parameters that control the server generation
		configuration.addRelayConnections = false; // same as in
													// AllGraphQLCases_Server_SpringConfiguration_separateUtilityClasses
		configuration.generateBatchLoaderEnvironment = true;// false testé dans separateUtilityClasses_Test
		configuration.generateDataFetcherForEveryFieldsWithArguments = true;// false testé dans
																			// separateUtilityClasses_Test
		configuration.generateDataLoaderForLists = true;// false testé dans separateUtilityClasses_Test
		configuration.generateJPAAnnotation = true;// false testé dans separateUtilityClasses_Test
		configuration.generateUtilityClasses = true;
		configuration.ignoredSpringMappings = "";
		configuration.javaTypeForIDType = GenerateServerCodeConfiguration.DEFAULT_JAVA_TYPE_FOR_ID_TYPE;
		configuration.queryMutationExecutionProtocol = QueryMutationExecutionProtocol.http;
		configuration.separateUtilityClasses = false;

		// Other parameters
		configuration.mode = PluginMode.server;
		configuration.schemaFilePattern = "allGraphQLCases*.graphqls";
		configuration.schemaPersonalizationFile = new File(this.mavenTestHelper.getModulePathFile(),
				"src/test/resources/schema_personalization/schema_personalization_for_code_generation.json");
		configuration.separateUtilityClasses = true;
	}
}
