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

import java.util.LinkedHashMap;
import java.util.Map;
import org.cactoos.Scalar;
import org.cactoos.scalar.Folded;
import org.cactoos.scalar.ScalarEnvelope;

/**
 * A {@link Scalar} that flattens a nested YAML-like structure into a flat
 * map with dot-separated keys.
 *
 * <p>For example, given the following nested map:</p>
 * <pre>{@code
 * {
 *   "app": {
 *     "name": "MyApp",
 *     "version": "1.0.0"
 *   }
 * }
 * }</pre>
 *
 * <p>The {@code value()} call produces:</p>
 * <pre>{@code
 * {
 *   "app.name": "MyApp",
 *   "app.version": "1.0.0"
 * }
 * }</pre>
 *
 * @since 0.0.5
 */
public final class YamlFlattening extends ScalarEnvelope<Map<String, String>> {

    /**
     * Secondary ctor with empty prefix.
     * @param yaml The nested YAML structure to flatten
     */
    public YamlFlattening(final Map<String, Object> yaml) {
        this("", yaml);
    }

    /**
     * Primary ctor.
     * @param prefix The prefix to prepend to keys (use empty string for no prefix)
     * @param yaml The nested YAML structure to flatten
     */
    /*
     * @checkstyle ConstructorsCodeFreeCheck (27 lines)
     */
    @SuppressWarnings("unchecked")
    public YamlFlattening(final String prefix, final Map<String, Object> yaml) {
        super(
            new Folded<>(
                new LinkedHashMap<>(),
                (final Map<String, String> flat, final Map.Entry<String, Object> entry) -> {
                    final String key;
                    if (prefix.isEmpty()) {
                        key = entry.getKey();
                    } else {
                        key = "%s.%s".formatted(prefix, entry.getKey());
                    }
                    final Object raw = entry.getValue();
                    if (raw instanceof Map) {
                        flat.putAll(
                            new YamlFlattening(key, (Map<String, Object>) raw).value()
                        );
                    } else {
                        flat.put(key, String.valueOf(raw));
                    }
                    return flat;
                },
                yaml.entrySet()
            )
        );
    }
}
