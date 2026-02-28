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

import com.github.roroche.eoconfig.matchers.HasConfiguration;
import com.github.roroche.eoconfig.matchers.HasProperty;
import com.github.roroche.eoconfig.matchers.IsEmptyProperties;
import java.util.Properties;
import org.cactoos.map.MapEntry;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;

/**
 * Test case for {@link OverlayConfiguration}.
 *
 * @since 0.0.1
 */
final class OverlayConfigurationTest {
    @Test
    void isOk() {
        MatcherAssert.assertThat(
            "A Configuration overridden contains new value for key",
            new OverlayConfiguration(
                new MapConfiguration(
                    new MapEntry<>("key.test", "value.test1")
                ),
                new MapEntry<>("key.test", "value.test2")
            ),
            new HasConfiguration(
                new HasProperty("key.test", "value.test2")
            )
        );
    }

    @Test
    void isEmpty() {
        MatcherAssert.assertThat(
            "A OverlayConfiguration can be created from empty Properties",
            new OverlayConfiguration(
                new ConfigurationOf(Properties::new),
                new ConfigurationOf(Properties::new)
            ),
            new HasConfiguration(
                new IsEmptyProperties()
            )
        );
    }
}
