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
import java.util.Map;
import org.cactoos.Input;
import org.cactoos.Scalar;
import org.cactoos.map.MapEnvelope;
import org.cactoos.scalar.Sticky;

/**
 * A {@link Map} built from YAML.
 * @since 0.0.5
 */
public final class YamlMap extends MapEnvelope<String, Object> {

    /**
     * Secondary ctor.
     * @param mapper The Jackson mapper to use
     * @param input The input to parse
     */
    public YamlMap(final ObjectMapper mapper, final Input input) throws Exception {
        this(
            new Sticky<>(
                new ParsedYaml(mapper, input, new MapType())
            )
        );
    }

    /**
     * Primary ctor.
     * @param origin The original {@link Map} from YAML
     */
    /*
     * @checkstyle ConstructorsCodeFreeCheck (4 lines)
     */
    public YamlMap(final Scalar<Map<String, Object>> origin) throws Exception {
        super(origin.value());
    }
}
