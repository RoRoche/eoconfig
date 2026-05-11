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
import org.cactoos.Scalar;
import org.cactoos.map.MapEnvelope;
import org.cactoos.scalar.Unchecked;

/**
 * A {@link Map} view of a {@link Scalar} that produces a {@link Map}.
 *
 * <p>Bridges a {@code Scalar<Map<K, V>>} to a {@code Map<K, V>} so that
 * callers can use a deferred map source where a {@link Map} is required,
 * without invoking {@link Scalar#value()} themselves.</p>
 *
 * @param <K> The type of map keys
 * @param <V> The type of map values
 * @since 0.0.5
 */
public final class StickyMap<K, V> extends MapEnvelope<K, V> {

    /**
     * Primary ctor.
     * @param origin The scalar producing the backing map
     */
    /*
     * @checkstyle ConstructorsCodeFreeCheck (4 lines)
     */
    public StickyMap(final Scalar<Map<K, V>> origin) {
        super(new Unchecked<>(origin).value());
    }
}
