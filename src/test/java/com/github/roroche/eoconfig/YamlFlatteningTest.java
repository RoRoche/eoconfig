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

import java.util.Collections;
import org.cactoos.map.MapEntry;
import org.cactoos.map.MapOf;
import org.hamcrest.MatcherAssert;
import org.hamcrest.collection.IsMapContaining;
import org.hamcrest.collection.IsMapWithSize;
import org.hamcrest.core.AllOf;
import org.hamcrest.core.IsEqual;
import org.junit.jupiter.api.Test;

/**
 * Test case for {@link YamlFlattening}.
 * @since 0.0.5
 */
@SuppressWarnings("allpublic")
final class YamlFlatteningTest {

    @Test
    void flattensNestedMaps() throws Exception {
        MatcherAssert.assertThat(
            "A YamlFlattening produces dot-separated keys for nested maps",
            new YamlFlattening(
                new MapOf<>(
                    new MapEntry<>(
                        "app",
                        new MapOf<String, Object>(
                            new MapEntry<>("name", "MyApp"),
                            new MapEntry<>("version", "1.0.0")
                        )
                    )
                )
            ).value(),
            new AllOf<>(
                new IsMapContaining<>(new IsEqual<>("app.name"), new IsEqual<>("MyApp")),
                new IsMapContaining<>(new IsEqual<>("app.version"), new IsEqual<>("1.0.0")),
                new IsMapWithSize<>(new IsEqual<>(2))
            )
        );
    }

    @Test
    void keepsFlatKeysAsIs() throws Exception {
        MatcherAssert.assertThat(
            "A YamlFlattening stringifies leaf values and leaves keys untouched",
            new YamlFlattening(
                new MapOf<String, Object>(
                    new MapEntry<>("key", "value"),
                    new MapEntry<>("count", 42)
                )
            ).value(),
            new AllOf<>(
                new IsMapContaining<>(new IsEqual<>("key"), new IsEqual<>("value")),
                new IsMapContaining<>(new IsEqual<>("count"), new IsEqual<>("42"))
            )
        );
    }

    @Test
    void appliesPrefix() throws Exception {
        MatcherAssert.assertThat(
            "A YamlFlattening prepends a non-empty prefix",
            new YamlFlattening(
                "app",
                new MapOf<>(
                    new MapEntry<>("name", "MyApp")
                )
            ).value(),
            new IsMapContaining<>(new IsEqual<>("app.name"), new IsEqual<>("MyApp"))
        );
    }

    @Test
    void isEmptyForEmptyMap() throws Exception {
        MatcherAssert.assertThat(
            "A YamlFlattening is empty when the source map is empty",
            new YamlFlattening(Collections.emptyMap()).value(),
            new IsMapWithSize<>(new IsEqual<>(0))
        );
    }
}
