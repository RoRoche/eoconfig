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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedHashMap;
import org.cactoos.Input;
import org.cactoos.io.ResourceOf;

/**
 * A utility class for creating configurations from YAML content.
 *
 * <p><b>Example:</b></p>
 * <pre>{@code
 * // Load configuration from a YAML file in the classpath
 * Configuration config = new YamlConfiguration("application.yaml");
 *
 * // Use the loaded configuration
 * Properties props = config.properties();
 * String appName = props.getProperty("app.name");
 * }</pre>
 *
 * @since 0.0.1
 */
public final class YamlConfiguration extends ConfigurationEnvelope {
    /**
     * Primary constructor.
     *
     * @param origin The configuration to decorate
     */
    public YamlConfiguration(final Configuration origin) {
        super(origin);
    }

    /**
     * Secondary constructor that loads properties from a YAML input stream.
     *
     * @param mapper The ObjectMapper to use for parsing the YAML content
     * @param input The InputStream containing the YAML content
     * @throws IOException if the input cannot be loaded or parsed
     */
    public YamlConfiguration(
        final ObjectMapper mapper,
        final InputStream input
    ) throws IOException {
        this(
            new MapConfiguration(
                new FlattenedYaml(
                    mapper.readValue(
                        input,
                        LinkedHashMap.class
                    )
                )
            )
        );
    }

    /**
     * Secondary constructor that loads properties from a YAML input stream.
     *
     * @param mapper The ObjectMapper to use for parsing the YAML content
     * @param input The Input stream containing the YAML content
     * @throws Exception if the input cannot be loaded or parsed
     */
    public YamlConfiguration(final ObjectMapper mapper, final Input input) throws Exception {
        this(mapper, input.stream());
    }

    /**
     * Secondary constructor that loads properties from a YAML file in the classpath.
     *
     * @param resource The name of the YAML file in the classpath
     * @throws Exception if the resource cannot be loaded or parsed
     */
    public YamlConfiguration(final String resource) throws Exception {
        this(
            new ObjectMapper(new YAMLFactory()),
            new ResourceOf(resource)
        );
    }
}
