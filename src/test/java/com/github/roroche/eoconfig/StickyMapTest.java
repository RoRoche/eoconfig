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
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

/**
 * Test case for {@link StickyMap}.
 * @since 0.0.5
 */
@SuppressWarnings("allpublic")
final class StickyMapTest {

    @Test
    void exposesScalarBackedMap() {
        MatcherAssert.assertThat(
            "A StickyMap exposes the entries of the scalar-produced map",
            new StickyMap<String, String>(
                () -> new MapOf<String, String>(
                    new MapEntry<>("a", "1"),
                    new MapEntry<>("b", "2")
                )
            ),
            Matchers.allOf(
                Matchers.hasEntry("a", "1"),
                Matchers.hasEntry("b", "2"),
                Matchers.aMapWithSize(2)
            )
        );
    }

    @Test
    void isEmptyWhenScalarReturnsEmptyMap() {
        MatcherAssert.assertThat(
            "A StickyMap is empty when the scalar returns an empty map",
            new StickyMap<>(Collections::emptyMap),
            Matchers.anEmptyMap()
        );
    }
}
