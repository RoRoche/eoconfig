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

import java.util.List;
import java.util.Properties;
import org.cactoos.list.ListOf;

/**
 * A utility class for creating configurations from environment variables.
 *
 * <p><b>Example:</b></p>
 * <pre>{@code
 * // Load configuration from a properties file in the classpath
 * Configuration config = new EnvironmentConfiguration("APP_NAME", "APP_VERSION");
 *
 * // Use the loaded configuration
 * Properties props = config.properties();
 * String appName = props.getProperty("APP_NAME");
 * }</pre>
 * @since 0.0.1
 */
public final class EnvironmentConfiguration extends ConfigurationEnvelope {
    /**
     * Creates a new configuration envelope.
     *
     * @param origin The configuration to decorate
     */
    public EnvironmentConfiguration(final Configuration origin) {
        super(origin);
    }

    /**
     * Secondary ctor.
     *
     * @param keys The list of keys to filter the environment variables.
     */
    public EnvironmentConfiguration(final List<String> keys) {
        this(
            () -> keys.stream()
                .filter(System.getenv()::containsKey)
                .collect(
                    Properties::new,
                    (final Properties props, final String key) ->
                        props.setProperty(key, System.getenv().get(key)),
                    Properties::putAll
                )
        );
    }

    /**
     * Secondary ctor.
     *
     * @param keys The keys to filter the environment variables.
     */
    public EnvironmentConfiguration(final String... keys) {
        this(new ListOf<>(keys));
    }
}
