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
import org.cactoos.list.ListOf;
import org.cactoos.map.MapEntry;
import org.hamcrest.MatcherAssert;
import org.hamcrest.core.AllOf;
import org.hamcrest.core.IsNot;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import uk.org.webcompere.systemstubs.environment.EnvironmentVariables;
import uk.org.webcompere.systemstubs.jupiter.SystemStub;
import uk.org.webcompere.systemstubs.jupiter.SystemStubsExtension;

/**
 * Test case for {@link EnvironmentConfiguration}.
 *
 * @since 0.0.1
 */
@ExtendWith(SystemStubsExtension.class)
final class EnvironmentConfigurationTest {

    /**
     * Environment variables system stub.
     */
    @SystemStub
    private final EnvironmentVariables envvars = new EnvironmentVariables();

    @Test
    void isOk() {
        MatcherAssert.assertThat(
            "A Configuration from environment contains new value for key",
            new ConfigurationWithEnvVars(
                new EnvironmentConfiguration(
                    "ENV_VAR"
                ),
                this.envvars,
                new MapEntry<>("ENV_VAR", "env.value")
            ),
            new HasConfiguration(
                new AllOf<>(
                    new IsNot<>(new IsEmptyProperties()),
                    new HasProperty("ENV_VAR", "env.value")
                )
            )
        );
    }

    @Test
    void isEmpty() {
        MatcherAssert.assertThat(
            "A EnvironementConfiguration can be created from empty list",
            new EnvironmentConfiguration(new ListOf<>()),
            new HasConfiguration(
                new IsEmptyProperties()
            )
        );
    }
}
