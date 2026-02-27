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

import com.github.roroche.eoconfig.Configuration;
import java.util.Properties;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

/**
 * A matcher for {@link Configuration} objects that checks
 * if their properties match a given matcher.
 *
 * <p><b>Example:</b></p>
 * <pre>{@code
 * // Assert that a configuration contains specific properties
 * Configuration config = new MapConfiguration(
 *     new MapEntry<>("app.name", "MyApp"),
 *     new MapEntry<>("app.version", "1.0.0")
 * );
 *
 * MatcherAssert.assertThat(
 *     config,
 *     new HasConfiguration(
 *         new HasProperty("app.name", "MyApp")
 *     )
 * );
 *
 * // Or combine multiple property matchers
 * MatcherAssert.assertThat(
 *     config,
 *     new HasConfiguration(
 *         new AllOf<>(
 *             new HasProperty("app.name", "MyApp"),
 *             new HasProperty("app.version", "1.0.0")
 *         )
 *     )
 * );
 * }</pre>
 * @since 0.0.1
 */
public final class HasConfiguration extends TypeSafeMatcher<Configuration> {

    /**
     * The matcher for the properties of the configuration.
     */
    private final Matcher<Properties> properties;

    /**
     * Constructs a HasConfiguration matcher with the given properties matcher.
     *
     * @param properties The matcher for the properties of the configuration
     */
    public HasConfiguration(final Matcher<Properties> properties) {
        this.properties = properties;
    }

    @Override
    public boolean matchesSafely(final Configuration configuration) {
        return this.properties.matches(configuration.properties());
    }

    @Override
    public void describeTo(final Description description) {
        description.appendText("a Configuration matching ");
        this.properties.describeTo(description);
    }

    @Override
    public void describeMismatchSafely(
        final Configuration configuration,
        final Description description
    ) {
        description.appendText("was ").appendValue(configuration.properties());
    }
}
