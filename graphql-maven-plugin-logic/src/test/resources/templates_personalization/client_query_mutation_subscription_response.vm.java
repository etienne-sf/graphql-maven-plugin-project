/** This template is custom **/
package ${packageUtilName};

#if (${pluginConfiguration.separateUtilityClasses})
import ${pluginConfiguration.packageName}.${object.javaName};
#end

#foreach($import in ${object.imports})
import $import;
#end

/**
 * This class is deprecated. Please use the #if(${pluginConfiguration.separateUtilityClasses})${pluginConfiguration.packageName}.#end${object.javaName} instead
 * 
 * @author etienne-sf
 */
@Deprecated
${object.annotation}
public class ${object.javaName}Response extends ${object.javaName} {

}
