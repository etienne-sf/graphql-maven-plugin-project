package ${pluginConfiguration.packageName};

import com.graphql_java_generator.CustomScalarRegistry;
import com.graphql_java_generator.CustomScalarRegistryImpl;

public class CustomScalarRegistryInitializer {

	/**
	 * Initialization of the {@link CustomScalarRegistry} with all known custom scalars, that is with all custom scalars
	 * defined in the project pom
	 */
	public void initCustomScalarRegistry() {
		CustomScalarRegistry customScalarRegistry = new CustomScalarRegistryImpl();

#foreach ($customScalar in $customScalars)
#if (${customScalar.graphQLScalarTypeClass})
		CustomScalarRegistryImpl.customScalarRegistry.registerGraphQLScalarType(new ${customScalar.graphQLScalarTypeClass}());
#elseif (${customScalar.graphQLScalarTypeStaticField})
		CustomScalarRegistryImpl.customScalarRegistry.registerGraphQLScalarType(${customScalar.graphQLScalarTypeStaticField});
#elseif (${customScalar.graphQLScalarTypeGetter})
		CustomScalarRegistryImpl.customScalarRegistry.registerGraphQLScalarType(${customScalar.graphQLScalarTypeGetter}());
#else
		.scalar(): ${customScalar.name} : you must define one of graphQLScalarTypeClass, graphQLScalarTypeStaticField or graphQLScalarTypeGetter (in the POM parameters for CustomScalars)
#end
#end

		CustomScalarRegistryImpl.customScalarRegistry = customScalarRegistry;
	}

}
