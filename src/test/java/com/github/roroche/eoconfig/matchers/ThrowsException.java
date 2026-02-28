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
package com.github.roroche.eoconfig.matchers;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.text.IsEqualIgnoringCase;

/**
 * A Hamcrest matcher that checks if a {@link Runnable}
 * throws an exception of a specific type when executed.
 *
 * <p><b>Example:</b></p>
 * <pre>{@code
 * // Assert that a Runnable throws IllegalArgumentException
 * MatcherAssert.assertThat(
 *     () -> { throw new IllegalArgumentException("Invalid argument"); },
 *     new ThrowsException(IllegalArgumentException.class, "Invalid argument")
 * );
 *
 * // Assert that a Runnable does not throw NullPointerException
 * MatcherAssert.assertThat(
 *     () -> { throw new IllegalArgumentException("Invalid argument"); },
 *     new IsNot<>(new ThrowsException(NullPointerException.class, "Null pointer"))
 * );
 * }</pre>
 * @since 0.0.1
 */
public final class ThrowsException extends TypeSafeMatcher<Runnable> {

    /**
     * The expected type of the exception to be thrown.
     */
    private final Class<? extends Throwable> expected;

    /**
     * The expected message of the exception to be thrown.
     */
    private final Matcher<String> message;

    /**
     * Primary constructor.
     *
     * @param expected The expected type of the exception to be thrown
     * @param message The expected message of the exception to be thrown
     */
    public ThrowsException(
        final Class<? extends Throwable> expected,
        final Matcher<String> message
    ) {
        this.expected = expected;
        this.message = message;
    }

    /**
     * Secondary constructor that uses a case-insensitive string matcher for the message.
     *
     * @param expected The expected type of the exception to be thrown
     * @param message The expected message of the exception to be thrown
     */
    public ThrowsException(
        final Class<? extends Throwable> expected,
        final String message
    ) {
        this(expected, new IsEqualIgnoringCase(message));
    }

    /*
     * @checkstyle IllegalCatchCheck (13 lines)
     */
    @SuppressWarnings("PMD.AvoidCatchingGenericException")
    @Override
    public boolean matchesSafely(final Runnable runnable) {
        boolean matches = false;
        try {
            runnable.run();
        } catch (final Exception exception) {
            matches = this.expected.isInstance(exception)
                &&
                this.message.matches(exception.getMessage());
        }
        return matches;
    }

    @Override
    public void describeTo(final Description description) {
        description.appendText("throws exception ")
            .appendValue(this.expected)
            .appendText(" with message ")
            .appendValue(this.message);
    }
}
