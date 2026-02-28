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
import org.junit.jupiter.api.Test;

/**
 * Test case for {@link FileConfiguration}.
 *
 * @since 0.0.1
 */
final class FileConfigurationTest {
    @Test
    void isOk() {
        MatcherAssert.assertThat(
            "A FileConfiguration can be created from Map.Entry",
            new FileConfiguration("application-test.properties"),
            new HasConfiguration(
                new AllOf<>(
                    new IsNot<>(new IsEmptyProperties()),
                    new HasProperty("app.name", "TestApp"),
                    new HasProperty("app.version", "2.0.0")
                )
            )
        );
    }

    @Test
    void isEmpty() {
        MatcherAssert.assertThat(
            "A FileConfiguration can be created from empty Properties",
            new FileConfiguration("empty.properties"),
            new HasConfiguration(
                new IsEmptyProperties()
            )
        );
    }

    @Test
    void doesNotExist() {
        MatcherAssert.assertThat(
            "A FileConfiguration throws exception if file not found",
            () -> new FileConfiguration("non-existing-file.properties"),
            new ThrowsException(
                IllegalArgumentException.class,
                "Resource 'non-existing-file.properties' not found in classpath"
            )
        );
    }
}
