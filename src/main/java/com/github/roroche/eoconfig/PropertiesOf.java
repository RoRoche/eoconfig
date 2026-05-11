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
import org.cactoos.Scalar;

/**
 * A {@link Scalar} that builds a {@link Properties} instance from a
 * {@link Map} of string keys and values.
 *
 * <p><b>Example:</b></p>
 * <pre>{@code
 * Map<String, String> source = new HashMap<>();
 * source.put("db.host", "localhost");
 * source.put("db.port", "5432");
 *
 * Properties props = new PropertiesOf(source).value();
 * }</pre>
 *
 * @since 0.0.5
 */
public final class PropertiesOf implements Scalar<Properties> {

    /**
     * The map of string keys and values to convert.
     */
    private final Map<String, String> map;

    /**
     * Primary constructor.
     * @param map The map of string keys and values to convert
     */
    public PropertiesOf(final Map<String, String> map) {
        this.map = map;
    }

    @Override
    public Properties value() {
        final Properties props = new Properties();
        for (final Map.Entry<String, String> entry : this.map.entrySet()) {
            props.setProperty(entry.getKey(), entry.getValue());
        }
        return props;
    }
}
