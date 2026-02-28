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
import org.cactoos.map.MapEnvelope;
import org.cactoos.scalar.Unchecked;

/**
 * A Map that flattens a nested YAML structure into a flat map with dot-separated keys.
 *
 * <p>For example, given the following YAML structure:</p>
 * <pre>{@code
 * app:
 *   name: MyApp
 *   version: 1.0.0
 * }</pre>
 *
 * <p>The FlattenedYaml will produce the following flat map:</p>
 * <pre>{@code
 * {
 *   "app.name": "MyApp",
 *   "app.version": "1.0.0"
 * }
 * }</pre>
 *
 * @since 0.0.1
 */
public final class FlattenedYaml extends MapEnvelope<String, String> {

    /**
     * Constructs a FlattenedYaml with a given prefix.
     *
     * @param prefix The prefix to prepend to keys (use empty string for no prefix)
     * @param yaml The nested YAML structure to flatten
     */
    @SuppressWarnings("unchecked")
    public FlattenedYaml(
        final String prefix,
        final Map<String, Object> yaml
    ) {
        super(
            new Unchecked<>(
                () -> {
                    final Map<String, String> flat = new LinkedHashMap<>();
                    for (final Entry<String, Object> entry : yaml.entrySet()) {
                        final String key;
                        if (prefix.isEmpty()) {
                            key = entry.getKey();
                        } else {
                            key = "%s.%s".formatted(prefix, entry.getKey());
                        }
                        final Object value = entry.getValue();
                        if (value instanceof Map) {
                            flat.putAll(
                                new FlattenedYaml(
                                    key,
                                    (Map<String, Object>) value
                                )
                            );
                        } else {
                            flat.put(
                                key,
                                String.valueOf(value)
                            );
                        }
                    }
                    return flat;
                }
            ).value()
        );
    }

    /**
     * Constructs a FlattenedYaml with an empty prefix.
     *
     * @param yaml The nested YAML structure to flatten
     */
    public FlattenedYaml(final Map<String, Object> yaml) {
        this("", yaml);
    }
}
