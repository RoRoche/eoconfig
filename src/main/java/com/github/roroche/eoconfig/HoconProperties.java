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
import com.typesafe.config.ConfigValue;
import java.util.Map;
import java.util.Properties;
import org.cactoos.Scalar;
import org.cactoos.iterable.Mapped;
import org.cactoos.map.MapEntry;
import org.cactoos.map.MapOf;
import org.cactoos.scalar.ScalarEnvelope;
import org.cactoos.scalar.Unchecked;

/**
 * A {@link Scalar} that builds a {@link Properties} instance from a HOCON
 * {@link Config} (or from raw HOCON content).
 *
 * <p><b>Example:</b></p>
 * <pre>{@code
 * Properties props = new HoconProperties("app.name = MyApp").value();
 * }</pre>
 *
 * @since 0.0.5
 */
public final class HoconProperties extends ScalarEnvelope<Properties> {

    /**
     * Secondary ctor.
     * @param content The HOCON configuration string to parse
     */
    public HoconProperties(final String content) {
        this(new ParsedHocon(content));
    }

    /**
     * Primary ctor.
     * @param config The scalar producing the HOCON configuration to convert
     */
    /*
     * @checkstyle ConstructorsCodeFreeCheck (16 lines)
     */
    public HoconProperties(final Scalar<Config> config) {
        super(
            new PropertiesOf(
                new MapOf<>(
                    new Mapped<>(
                        (final Map.Entry<String, ConfigValue> entry) -> new MapEntry<>(
                            entry.getKey(),
                            String.valueOf(entry.getValue().unwrapped())
                        ),
                        new Unchecked<>(config).value().entrySet()
                    )
                )
            )
        );
    }
}
