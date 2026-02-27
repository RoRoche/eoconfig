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

import java.util.Properties;
import org.cactoos.Scalar;

/**
 * A utility class for creating configurations from {@link java.util.Properties}.
 *
 * @since 0.0.1
 */
public final class ConfigurationOf implements Configuration {

    /**
     * The properties to create the configuration from.
     */
    private final Properties props;

    /**
     * Primary constructor.
     *
     * @param props The properties to create the configuration from
     */
    public ConfigurationOf(final Properties props) {
        this.props = props;
    }

    /**
     * Secondary constructor that accepts a {@link Scalar} of {@link Properties}.
     *
     * @param props The scalar of properties to create the configuration from
     * @throws Exception If an error occurs while retrieving the properties from the scalar
     */
    public ConfigurationOf(final Scalar<Properties> props) throws Exception {
        this(props.value());
    }

    @Override
    public Properties properties() {
        return this.props;
    }
}
