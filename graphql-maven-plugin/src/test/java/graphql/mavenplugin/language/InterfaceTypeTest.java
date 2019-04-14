package graphql.mavenplugin.language;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import graphql.mavenplugin.PluginMode;
import graphql.mavenplugin.language.impl.InterfaceType;
import graphql.mavenplugin.language.impl.ObjectType;

class InterfaceTypeTest {

	String packageName = "a.package.name";
	String packageName2 = "a.package.name";

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void testGetConcreteClassSimpleName() {
		// Preparation
		ObjectType o = new ObjectType(packageName2, PluginMode.server);
		o.setName("AClassName");

		InterfaceType i = new InterfaceType(packageName, PluginMode.server);
		i.setDefaultImplementation(o);

		// Verification
		assertEquals("AClassName", i.getConcreteClassSimpleName(), "");
	}

}
