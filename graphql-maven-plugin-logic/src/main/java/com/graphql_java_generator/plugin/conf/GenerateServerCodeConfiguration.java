/**
 * 
 */
package com.graphql_java_generator.plugin.conf;

import java.io.File;
import java.util.Map;

import org.dataloader.BatchLoaderEnvironment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.graphql_java_generator.util.GraphqlUtils;

/**
 * This class contains all parameters for the <I>generateServerCode</I> goal/task.
 * 
 * @author etienne-sf
 */
public interface GenerateServerCodeConfiguration extends GenerateCodeCommonConfiguration {

	// The String constant must be a constant expression, for use in the GraphqlMavenPlugin class.
	// So all these are String, including Boolean and Enum. Boolean are either "true" or "false"

	public final String DEFAULT_GENERATE_JPA_ANNOTATION = "false";
	public final String DEFAULT_GENERATE_BATCH_LOADER_ENVIRONMENT = "false";
	public final String DEFAULT_JAVA_TYPE_FOR_ID_TYPE = "java.util.UUID";
	public final String DEFAULT_SCAN_BASE_PACKAGES = "null";
	public final String DEFAULT_SCHEMA_PERSONALIZATION_FILE = "null"; // Can't by null, must be a valid String.

	/**
	 * (only for server mode) The <I>packaging</I> is the kind of artefact generated by the project. Typically: jar (for
	 * a standard Java application) or war (for a webapp)
	 */
	public Packaging getPackaging();

	/**
	 * <P>
	 * (only for server mode) The <I>javaTypeForIDType</I> is the java class that is used in the generated code for
	 * GraphQL fields that are of the GraphQL ID type. The default value is <I>java.util.UUID</I>. Valid values are:
	 * java.lang.String, java.lang.Long and java.util.UUID.
	 * </P>
	 * <P>
	 * This parameter is only valid for the server mode. When generating the client code, the ID is always generated as
	 * a String type, as recommended in the GraphQL doc.
	 * </P>
	 * <P>
	 * In other words: when in server mode and <I>javaTypeForIDType</I> is not set, all GraphQL ID fields are UUID
	 * attributes in java. When in server mode and <I>javaTypeForIDType</I> is set to the X type, all GraphQL ID fields
	 * are X attributes in java.
	 * </P>
	 * <P>
	 * Note: you can override this, by using the schema personalization capability. For more information, please have a
	 * look at the
	 * <A HREF="https://graphql-maven-plugin-project.graphql-java-generator.com/schema_personalization.html">Schema
	 * Personalization doc page</A>.
	 * </P>
	 * 
	 * @return
	 */
	public String getJavaTypeForIDType();

	/**
	 * <P>
	 * (only for server mode) A comma separated list of package names, <B>without</B> double quotes, that will also be
	 * parsed by Spring, to discover Spring beans, Spring repositories and JPA entities when the server starts. You
	 * should use this parameter only for packages that are not subpackage of the package defined in the _packageName_
	 * parameter and not subpackage of <I>com.graphql_java_generator</I>
	 * </P>
	 * <P>
	 * This allows for instance, to set <I>packageName</I> to <I>your.app.package.graphql</I>, and to define your Spring
	 * beans, like the
	 * <A HREF="https://graphql-maven-plugin-project.graphql-java-generator.com/server.html">DataFetcherDelegates</A> or
	 * your Spring data repositories in any other folder, by setting for instance scanBasePackages to
	 * <I>your.app.package.impl, your.app.package.graphql</I>, or just <I>your.app.package</I>
	 * </P>
	 */
	public String getScanBasePackages();

	/**
	 * (only for server mode) Transform the list of package names returned by {@link #getScanBasePackages()} by a list
	 * of package names, surrounded by double quotes, as it can be used in the Spring scanBasePackages property of the
	 * {@link SpringBootApplication} Spring annotation.
	 * 
	 * @return A string that can be added to the scanBasePackages property of {@link SpringBootApplication}, that is: an
	 *         empty String, or a list of quoted package names starting with a comma (e.g.: ", \"my.package\",
	 *         \"my.other.package\"")
	 */
	default public String getQuotedScanBasePackages() {
		return GraphqlUtils.graphqlUtils.getQuotedScanBasePackages(getScanBasePackages());
	}

	/**
	 * <P>
	 * (only for server mode) schemaPersonalizationFile is the file name where the GraphQL maven plugin will find
	 * personalization that it must apply before generating the code. This applies to the <B>server</B> mode only. See
	 * <A HREF="https://graphql-maven-plugin-project.graphql-java-generator.com/schema_personalization.html">the doc on
	 * the plugin web site</A> for more details.
	 * </P>
	 * <P>
	 * The standard file would be something like /src/main/graphql/schemaPersonalizationFile.json, which avoids to embed
	 * this compile time file within your maven artifact (as it is not in the /src/main/java nor in the
	 * /src/main/resources folders).
	 * </P>
	 * 
	 * @return
	 */
	public File getSchemaPersonalizationFile();

	/**
	 * <P>
	 * (only for server mode) Indicates if the plugin should generate add the {@link BatchLoaderEnvironment} parameter
	 * to the <I>batchLoader</I> methods, in DataFetchersDelegate. This parameter allows to get the context of the Batch
	 * Loader, including the context associated to the id, when using the id has been added by the
	 * {@link org.dataloader.DataLoader#load(Object, Object)} method.
	 * </P>
	 * <P>
	 * For instance, if you have the method below, for a field named <I>oneWithIdSubType</I> in a DataFetcherDelegate:
	 * </P>
	 * 
	 * <PRE>
	 * &#64;Override
	 * public CompletableFuture&lt;AllFieldCasesWithIdSubtype> oneWithIdSubType(
	 * 		DataFetchingEnvironment dataFetchingEnvironment, DataLoader&lt;UUID, AllFieldCasesWithIdSubtype> dataLoader,
	 * 		AllFieldCases source, Boolean uppercase) {
	 * 	return dataLoader.load(UUID.randomUUID());
	 * }
	 * </PRE>
	 * <P>
	 * then, in the <I>AllFieldCasesWithIdSubtype</I> DataFetcherDelegate, you can retrieve the uppercase this way:
	 * </P>
	 * 
	 * <PRE>
	 * &#64;Override
	 * public List&lt;AllFieldCasesWithIdSubtype> batchLoader(List&lt;UUID> keys, BatchLoaderEnvironment environment) {
	 * 	List&lt;AllFieldCasesWithIdSubtype> list = new ArrayList<>(keys.size());
	 * 	for (UUID id : keys) {
	 * 		// Let's manage the uppercase parameter, that was associated with this key
	 * 		Boolean uppercase = (Boolean) environment.getKeyContexts().get(id);
	 * 		if (uppercase != null && uppercase) {
	 * 			item.setName(item.getName().toUpperCase());
	 * 		}
	 * 
	 * 		// Do something with the id and the uppercase value
	 * 	}
	 * 	return list;
	 * }
	 * </PRE>
	 * <P>
	 * For more complex cases, you can store a {@link Map} with all the needed values, instead of just the parameter
	 * value.
	 * </P>
	 * <P>
	 * <B><I>Default value is false</I></B>
	 * </P>
	 */
	public boolean isGenerateBatchLoaderEnvironment();

	/**
	 * <P>
	 * (only for server mode) Indicates whether the plugin should generate the JPA annotations, for generated objects.
	 * </P>
	 * <P>
	 * <B><I>Default value is false</I></B>
	 * </P>
	 */
	public boolean isGenerateJPAAnnotation();

	/** Logs all the configuration parameters (only when in the debug level) */
	@Override
	public default void logConfiguration() {
		Logger logger = LoggerFactory.getLogger(getClass());
		if (logger.isDebugEnabled()) {
			logger.debug("-- start configuration --");
			logger.debug("The graphql-java-generator Plugin Configuration for the generateServerCode goal/task is -->");
			logGenerateServerCodeConfiguration();
			logger.debug("-- end configuration --");
		}
	}

	/**
	 * Logs all the configuration parameters for the <I>generateServerCode</I> task/goal (only when in the debug level)
	 */
	public default void logGenerateServerCodeConfiguration() {
		Logger logger = LoggerFactory.getLogger(getClass());
		logger.debug("  Parameters specific to the generateServerCode task/goal:");
		logger.debug("    generateBatchLoaderEnvironment: " + isGenerateBatchLoaderEnvironment());
		logger.debug("    generateJPAAnnotation: " + isGenerateJPAAnnotation());
		logger.debug("    javaTypeForIDType: " + getJavaTypeForIDType());
		logger.debug("    packaging: " + getPackaging());
		logger.debug("    scanBasePackages: " + getScanBasePackages());
		logger.debug("    schemaPersonalizationFile: " + getSchemaPersonalizationFile());
		logGenerateCodeCommonConfiguration();
	}
}
