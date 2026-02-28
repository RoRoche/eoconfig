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

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import com.typesafe.config.ConfigValue;
import java.util.Map;
import java.util.Properties;

/**
 * A utility class for creating configurations from HOCON content.
 *
 * <p><b>Example:</b></p>
 * <pre>{@code
 * // Load configuration from a HOCON string
 * Configuration config = new HoconConfiguration("app.name = MyApp\napp.version = 1.0");
 *
 * // Or load from a HOCON file
 * Config hoconConfig = ConfigFactory.parseResources("config/application.conf").resolve();
 * Configuration config = new HoconConfiguration(hoconConfig);
 *
 * Properties props = config.properties();
 * String appName = props.getProperty("app.name");
 * }</pre>
 *
 * @since 0.0.1
 */
public final class HoconConfiguration extends ConfigurationEnvelope {
    /**
     * Primary constructor.
     *
     * @param origin The configuration to decorate
     */
    public HoconConfiguration(final Configuration origin) {
        super(origin);
    }

    /**
     * Secondary ctor.
     *
     * @param props The properties to load.
     */
    public HoconConfiguration(final Properties props) {
        this(new ConfigurationOf(props));
    }

    /**
     * Secondary ctor.
     *
     * @param config The HOCON configuration to load.
     */
    public HoconConfiguration(final Config config) {
        this(
            config.entrySet()
                .stream()
                .collect(
                    Properties::new,
                    (final Properties props, final Map.Entry<String, ConfigValue> entry) ->
                        props.setProperty(
                            entry.getKey(),
                            String.valueOf(entry.getValue().unwrapped())
                        ),
                    Properties::putAll
                )
        );
    }

    /**
     * Secondary ctor.
     *
     * @param content The HOCON configuration string to load.
     */
    public HoconConfiguration(final String content) {
        this(ConfigFactory.parseString(content).resolve());
    }
}
