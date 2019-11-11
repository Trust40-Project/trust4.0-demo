package trust40.enforcer.sdq.test;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;

import trust40.enforcer.sdq.PrivacyLevel;
import trust40.enforcer.sdq.rules.*;
import trust40.k4case.AllowPermission;
import trust40.k4case.DenyPermission;

import static org.junit.jupiter.api.Assertions.*;

class TestRule {
    /*
	@Test
	@DisplayName("Tests if Exceptions are thrown with null")
	void testEmptyConstructorRule() {
		assertAll("null check",
				() -> assertThrows(IllegalArgumentException.class, () -> new AllowRule(null, null, null)),
				() -> assertThrows(IllegalArgumentException.class, () -> new AllowRule(null, "", "")),
				() -> assertThrows(IllegalArgumentException.class, () -> new AllowRule("", null, "")),
				() -> assertThrows(IllegalArgumentException.class, () -> new AllowRule("", "", null)),

				() -> assertThrows(IllegalArgumentException.class, () -> new DenyRule(null, null, null, null)),
				() -> assertThrows(IllegalArgumentException.class,
						() -> new DenyRule(null, "", "", PrivacyLevel.PUBLIC)),
				() -> assertThrows(IllegalArgumentException.class,
						() -> new DenyRule("", null, "", PrivacyLevel.PUBLIC)),
				() -> assertThrows(IllegalArgumentException.class, () -> new DenyRule("", "", "", null)),

				() -> assertThrows(IllegalArgumentException.class, () -> new ReasonedAllowRule(null, null, null, null)),
				() -> assertThrows(IllegalArgumentException.class, () -> new ReasonedAllowRule(null, "", "", "")),
				() -> assertThrows(IllegalArgumentException.class, () -> new ReasonedAllowRule("", null, "", "")),
				() -> assertThrows(IllegalArgumentException.class, () -> new ReasonedAllowRule("", "", null, ""))

		);
	}
    */

	@Nested
	@DisplayName("Tests Different Equality")
	class testEqualsRule {
		AllowRule allowRule;
		DenyRule denyRule;

		@BeforeEach
		void initial() {
			allowRule = new AllowRule(new DataObject("test123"), new Operation("test234"), new DataObject("test345"));
			denyRule = new DenyRule(new DataObject("test123"), new Operation("test234"),new DataObject("test345"), PrivacyLevel.HIGHLY_SENSITIVE);
		}

		@Test
		void testEqualsWithDenyAndAllowRule() {
			assertFalse(allowRule.equals(denyRule));
		}

		@Test
		void testEqualsAllowRule() {
			assertTrue(allowRule
					.equals(new AllowRule(allowRule.getSubject(), allowRule.getOperation(), allowRule.getObject())));
		}

		@Test
		void testEqualsDenyRule() {
			assertTrue(denyRule.equals(new DenyRule(denyRule.getSubject(), denyRule.getOperation(), denyRule.getObject(),
					denyRule.getPrivacyLevel())));
		}

		@Test
		void testSameRule() {
			assertTrue(allowRule.equalRule(denyRule));
		}
	}
	@Test
    @DisplayName("Tests ScalaConversion Allow Rule")
    void scalaConversionTestAllowRule(){
        AllowRule allowRule = new AllowRule(new DataObject("worker", "test123"), new Operation("test234", "*"), new DataObject("worker", "test345"));
        AllowPermission permission = allowRule.getScalaPermission();
        assertAll("check equals scala with type",
                () -> assertEquals("test123", permission.subj()),
                () -> assertEquals("test234(*)",permission.verb()),
                () -> assertEquals("test345",permission.obj())
        );
    }
    @Test
    @DisplayName("Tests ScalaConversion Deny Rule")
    void scalaConversionTestDenyRule(){
        DenyRule denyRule = new DenyRule(new DataObject("worker", "test123"), new Operation("test234", "*"), new DataObject("worker", "test345"), PrivacyLevel.HIGHLY_SENSITIVE);
        DenyPermission permission = denyRule.getScalaPermission();
        assertAll("check equals scala with type",
                () -> assertEquals("test123", permission.subj()),
                () -> assertEquals("test234(*)",permission.ver()),
                () -> assertEquals("test345",permission.obj()),
                () -> assertEquals(trust40.enforcer.tcof.PrivacyLevel.HIGHLY_SENSITIVE(),permission.lvl())
        );
    }
}
