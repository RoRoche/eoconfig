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

import java.util.Map;
import java.util.Properties;
import org.cactoos.map.MapOf;

/**
 * A utility class for override a configuration.
 *
 * <p><b>Example:</b></p>
 * <pre>{@code
 * // Create a base configuration and override specific values
 * Configuration baseConfig = new MapConfiguration(
 *     new MapEntry<>("key.test", "original.value"),
 *     new MapEntry<>("other.key", "other.value")
 * );
 *
 * Configuration overriddenConfig = new OverlayConfiguration(
 *     baseConfig,
 *     new MapEntry<>("key.test", "overridden.value")
 * );
 *
 * Properties props = overriddenConfig.properties();
 * String value = props.getProperty("key.test"); // Returns "overridden.value"
 * String other = props.getProperty("other.key"); // Returns "other.value"
 * }</pre>
 *
 * @since 0.0.1
 */
public final class OverlayConfiguration extends ConfigurationEnvelope {
    /**
     * Primary ctor.
     *
     * @param base The configuration to wrap.
     * @param override The configuration providing the properties
     *  to override the base configuration.
     */
    public OverlayConfiguration(final Configuration base, final Configuration override) {
        super(
            () -> {
                final Properties props = new Properties();
                props.putAll(base.properties());
                props.putAll(override.properties());
                return props;
            }
        );
    }

    /**
     * Secondary ctor.
     *
     * @param base The configuration to wrap.
     * @param map The map of string keys and values providing the properties
     *  to override the base configuration.
     */
    public OverlayConfiguration(final Configuration base, final Map<String, String> map) {
        this(base, new MapConfiguration(map));
    }

    /**
     * Secondary ctor.
     *
     * @param base The configuration to wrap.
     * @param entries The entries of the map of string keys and values
     *  providing the properties to override the base configuration.
     */
    @SafeVarargs
    public OverlayConfiguration(
        final Configuration base,
        final Map.Entry<String, String>... entries
    ) {
        this(base, new MapOf<>(entries));
    }
}
