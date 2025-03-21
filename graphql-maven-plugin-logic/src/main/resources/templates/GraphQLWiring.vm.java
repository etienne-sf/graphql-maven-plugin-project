/** Generated by the default template from graphql-java-generator */
package ${packageUtilName};

/**
 * @author generated by graphql-java-generator
 * @see <a href="https://github.com/graphql-java-generator/graphql-java-generator">https://github.com/graphql-java-generator/graphql-java-generator</a>
 */
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.graphql.execution.RuntimeWiringConfigurer;
import org.springframework.stereotype.Component;

import graphql.schema.GraphQLScalarType;

/**
 * Thanks to spring-graphql, the POJO classes are auto-magically discovered and mapped. But the custom scalars still needs to be 'manually' wired.
 * This is the objective of this class.
 * <BR/><BR/>
 * Based on the https://www.graphql-java.com/tutorials/getting-started-with-spring-boot/ tutorial
 * 
 * @author etienne-sf
 */
@Component("graphQLWiring${configuration.springBeanSuffix}")
@SuppressWarnings("unused")
public class GraphQLWiring implements RuntimeWiringConfigurer {

	/** The logger for this instance */
	protected Logger logger = LoggerFactory.getLogger(GraphQLWiring.class);

	public void configure(graphql.schema.idl.RuntimeWiring.Builder builder) {
#if ($customScalars.size() == 0)
		// No configured custom scalars
#else
		builder //
				//
				// Wiring every custom scalar definitions
#foreach ($customScalar in $customScalars)
##
## Step 1: wiring the custom scalar definitions
##
			.scalar(GraphQLScalarType.newScalar(
#if (${customScalar.customScalarDefinition.graphQLScalarTypeClass})
				new ${customScalar.customScalarDefinition.graphQLScalarTypeClass}())
#elseif (${customScalar.customScalarDefinition.graphQLScalarTypeStaticField})
				${customScalar.customScalarDefinition.graphQLScalarTypeStaticField})
#elseif (${customScalar.customScalarDefinition.graphQLScalarTypeGetter})
				${customScalar.customScalarDefinition.graphQLScalarTypeGetter})
#else
			.scalar(): ${customScalar.javaName} : you must define one of graphQLScalarTypeClass, graphQLScalarTypeStaticField or graphQLScalarTypeGetter (in the POM parameters for CustomScalars)
#end
				.name("${customScalar.name}")
				.build())
#end ##foreach
			;
#end ##if
	}
}
