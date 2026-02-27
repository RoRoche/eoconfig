/*
 * MIT License
 *
 * Copyright (c) 2026 Romain Rochegude
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.github.roroche.eoconfig.matchers;

import java.util.Properties;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.text.IsEqualIgnoringCase;

/**
 * A Hamcrest matcher that checks if a {@link Properties} object
 * has a property with a specific key and value.
 *
 * <p><b>Example:</b></p>
 * <pre>{@code
 * // Assert that properties contain specific key-value pairs
 * Properties props = new Properties();
 * props.setProperty("db.host", "localhost");
 * props.setProperty("db.port", "5432");
 *
 * MatcherAssert.assertThat(
 *     props,
 *     new HasProperty("db.host", "localhost")
 * );
 *
 * // Use with Configuration matcher
 * Configuration config = new MapConfiguration(
 *     new MapEntry<>("app.name", "TestApp"),
 *     new MapEntry<>("app.version", "2.0.0")
 * );
 *
 * MatcherAssert.assertThat(
 *     config,
 *     new HasConfiguration(
 *         new AllOf<>(
 *             new HasProperty("app.name", "TestApp"),
 *             new HasProperty("app.version", "2.0.0")
 *         )
 *     )
 * );
 * }</pre>
 * @since 0.0.1
 */
public final class HasProperty extends TypeSafeMatcher<Properties> {

    /**
     * The key of the property to check.
     */
    private final String key;

    /**
     * The matcher for the value of the property.
     */
    private final Matcher<String> expected;

    /**
     * Constructs a HasProperty matcher with the given key and expected value matcher.
     *
     * @param key      The key of the property to check
     * @param expected The matcher for the value of the property
     */
    public HasProperty(final String key, final Matcher<String> expected) {
        this.key = key;
        this.expected = expected;
    }

    /**
     * Constructs a HasProperty matcher with the given key and expected value.
     *
     * @param key      The key of the property to check
     * @param expected The expected value of the property
     */
    public HasProperty(final String key, final String expected) {
        this(key, new IsEqualIgnoringCase(expected));
    }

    @Override
    public boolean matchesSafely(final Properties properties) {
        return this.expected.matches(properties.getProperty(this.key));
    }

    @Override
    public void describeTo(final Description description) {
        description.appendText("a Properties matching ");
        this.expected.describeTo(description);
        description.appendText(" for key ").appendValue(this.key);
    }

    @Override
    public void describeMismatchSafely(
        final Properties properties,
        final Description description
    ) {
        description.appendText("was ").appendValue(properties.getProperty(this.key));
    }
}
