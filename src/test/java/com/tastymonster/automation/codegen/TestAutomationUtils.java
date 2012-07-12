package com.tastymonster.automation.codegen;

import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class TestAutomationUtils {

	public void testNormalizeField() {
		Assert.assertEquals( AutomationUtils.normalizeFieldName( "aaa" ), "aaa", "If there are no offending characters, there should be no change" );
		Assert.assertEquals( AutomationUtils.normalizeFieldName( "_aaa_" ), "aaa", "Even underscores should be removed" );
		Assert.assertEquals( AutomationUtils.normalizeFieldName( "a.b.c" ), "abc", "All non-word characters should be removed" );
		Assert.assertEquals( AutomationUtils.normalizeFieldName( "a-bcdefGhIjKlMnop==++TestThis" ), "abcdefGhIjKlMnopTestThis", "All non-word characters should be removed and case preserved" );
		Assert.assertEquals( AutomationUtils.normalizeFieldName( "A-bcdefGhIjKlMnop==++TestThis" ), "abcdefGhIjKlMnopTestThis", "All non-word characters should be removed and case preserved (except for first letter-should be lower)" );
		Assert.assertEquals( AutomationUtils.normalizeFieldName( "ABCD" ), "aBCD", "ABCD should come back as aBCD" );
		Assert.assertEquals( AutomationUtils.normalizeFieldName( ".-=!!@#$%~~``" ), "", "A string entirely composed of non-word characters should be returned as an empty string" );
		Assert.assertEquals( AutomationUtils.normalizeFieldName( "" ), "", "Empty string should return empty string" );
		Assert.assertEquals( AutomationUtils.normalizeFieldName( null ), "", "Null string should return empty string" );
		Assert.assertEquals( AutomationUtils.normalizeFieldName( "private" ), "privateElement", "Reserved keywords should have 'Element' appended" );
	}
}
