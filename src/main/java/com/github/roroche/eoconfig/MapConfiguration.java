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
 * A utility class for creating configurations from {@link java.util.Map}.
 *
 * <p><b>Example:</b></p>
 * <pre>{@code
 * // Load configuration from map entries
 * Configuration config = new MapConfiguration(
 *     new MapEntry<>("key.test", "value.test"),
 *     new MapEntry<>("app.name", "MyApp")
 * );
 *
 * // Or from a map
 * Map<String, String> configMap = new HashMap<>();
 * configMap.put("db.host", "localhost");
 * configMap.put("db.port", "5432");
 * Configuration config = new MapConfiguration(configMap);
 *
 * Properties props = config.properties();
 * }</pre>
 *
 * @since 0.0.1
 */
public final class MapConfiguration extends ConfigurationEnvelope {
    /**
     * Primary constructor.
     *
     * @param origin The configuration to decorate
     */
    public MapConfiguration(final Configuration origin) {
        super(origin);
    }

    /**
     * Secondary ctor.
     *
     * @param props The properties to load.
     */
    public MapConfiguration(final Properties props) {
        this(new ConfigurationOf(props));
    }

    /**
     * Secondary ctor.
     *
     * @param map The map of string keys and values to load.
     */
    public MapConfiguration(final Map<String, String> map) {
        this(
            map.entrySet()
                .stream()
                .collect(
                    Properties::new,
                    (final Properties props, final Map.Entry<String, String> entry) ->
                        props.setProperty(
                            entry.getKey(),
                            entry.getValue()
                        ),
                    Properties::putAll
                )
        );
    }

    /**
     * Secondary ctor.
     *
     * @param entries The entries of the map of string keys and values to load.
     */
    @SafeVarargs
    public MapConfiguration(final Map.Entry<String, String>... entries) {
        this(new MapOf<>(entries));
    }
}
