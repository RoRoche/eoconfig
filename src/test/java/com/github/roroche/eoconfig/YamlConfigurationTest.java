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
package com.github.roroche.eoconfig;

import com.github.roroche.eoconfig.matchers.HasConfiguration;
import com.github.roroche.eoconfig.matchers.HasProperty;
import com.github.roroche.eoconfig.matchers.IsEmptyProperties;
import com.github.roroche.eoconfig.matchers.ThrowsException;
import org.hamcrest.MatcherAssert;
import org.hamcrest.core.AllOf;
import org.hamcrest.core.IsNot;
import org.hamcrest.core.StringContains;
import org.junit.jupiter.api.Test;

/**
 * Test case for {@link YamlConfiguration}.
 *
 * @since 0.0.1
 */
final class YamlConfigurationTest {
    @Test
    void isOk() throws Exception {
        MatcherAssert.assertThat(
            "A YamlConfiguration can be created from Map.Entry",
            new YamlConfiguration("application-properties.yml"),
            new HasConfiguration(
                new AllOf<>(
                    new IsNot<>(new IsEmptyProperties()),
                    new HasProperty("server.host", "localhost"),
                    new HasProperty("server.port", "8080"),
                    new HasProperty("database.url", "jdbc:postgresql://localhost/db"),
                    new HasProperty("database.user", "admin")
                )
            )
        );
    }

    /*
     * @checkstyle IllegalCatchCheck (18 lines)
     */
    @SuppressWarnings({"PMD.AvoidCatchingGenericException", "PMD.AvoidThrowingRawExceptionTypes"})
    @Test
    void isEmpty() {
        MatcherAssert.assertThat(
            "A YamlConfiguration throws from empty Properties",
            () -> {
                try {
                    new YamlConfiguration("empty-application-properties.yml");
                } catch (final Exception exception) {
                    throw new RuntimeException(exception.getMessage(), exception);
                }
            },
            new ThrowsException(
                RuntimeException.class,
                new StringContains("No content to map due to end-of-input")
            )
        );
    }

    /*
     * @checkstyle IllegalCatchCheck (18 lines)
     */
    @SuppressWarnings({"PMD.AvoidCatchingGenericException", "PMD.AvoidThrowingRawExceptionTypes"})
    @Test
    void doesNotExist() {
        MatcherAssert.assertThat(
            "A YamlConfiguration throws exception if file not found",
            () -> {
                try {
                    new YamlConfiguration("non-existing-file.yml");
                } catch (final Exception exception) {
                    throw new RuntimeException(exception.getMessage(), exception);
                }
            },
            new ThrowsException(
                RuntimeException.class,
                new StringContains("The resource \"non-existing-file.yml\" was not found")
            )
        );
    }
}
