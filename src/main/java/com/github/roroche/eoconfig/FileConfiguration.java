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

import java.io.InputStream;
import java.util.Properties;

/**
 * A utility class for creating configurations from {@link java.io.File}.
 *
 * <p><b>Example:</b></p>
 * <pre>{@code
 * // Load configuration from a properties file in the classpath
 * Configuration config = new FileConfiguration("application-test.properties");
 *
 * // Use the loaded configuration
 * Properties props = config.properties();
 * String appName = props.getProperty("app.name");
 * }</pre>
 *
 * @since 0.0.1
 */
public final class FileConfiguration extends ConfigurationEnvelope {
    /**
     * Primary constructor.
     *
     * @param origin The configuration to decorate
     */
    public FileConfiguration(final Configuration origin) {
        super(origin);
    }

    /**
     * Secondary constructor that loads properties from a file in the classpath.
     *
     * @param name The name of the properties file in the classpath
     */
    public FileConfiguration(final String name) {
        this(
            new ConfigurationOf(
                () -> {
                    final Properties props = new Properties();
                    try (InputStream stream = Thread.currentThread()
                        .getContextClassLoader().getResourceAsStream(name)) {
                        if (stream == null) {
                            throw new IllegalArgumentException(
                                String.format("Resource '%s' not found in classpath", name)
                            );
                        }
                        props.load(stream);
                    }
                    return props;
                }
            )
        );
    }
}
