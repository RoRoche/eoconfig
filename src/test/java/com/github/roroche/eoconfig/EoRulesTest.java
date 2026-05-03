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

import com.github.roroche.eorules.ClassesAreAbstractOrFinalRule;
import com.github.roroche.eorules.ClassesShouldHaveNoStaticMethodsRule;
import com.github.roroche.eorules.ClassesShouldNotHaveGettersOrSettersRule;
import com.github.roroche.eorules.ClassesShouldNotHavePrivateMethodsRule;
import com.github.roroche.eorules.FieldsShouldBeFinalRule;
import com.github.roroche.eorules.PublicMethodsDeclaredInInterfacesRule;
import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

/**
 * Architecture tests to check for Elegant Objects principles.
 * @since 0.0.5
 */
@SuppressWarnings({
    "allpublic",
    "JTCOP.RuleEveryTestHasProductionClass",
    "JTCOP.RuleAssertionMessage"
})
final class EoRulesTest {

    /**
     * Classes to check.
     * @checkstyle ProhibitFieldsInTestClassesCheck (4 lines)
     */
    private final JavaClasses classes = new ClassFileImporter()
        .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
        .importPackages("com.github.roroche.eoconfig");

    @Test
    void checksClassesAreAbstractOrFinal() {
        new ClassesAreAbstractOrFinalRule().check(this.classes);
    }

    @Test
    void checksThereAreNoStaticMethods() {
        new ClassesShouldHaveNoStaticMethodsRule().check(this.classes);
    }

    @Test
    void checksClassesDoNotHaveGettersOrSetters() {
        new ClassesShouldNotHaveGettersOrSettersRule().check(this.classes);
    }

    @Test
    void checksClassesDoNotHavePrivateMethods() {
        new ClassesShouldNotHavePrivateMethodsRule().check(this.classes);
    }

    @Test
    void checksFieldsAreFinal() {
        new FieldsShouldBeFinalRule().check(this.classes);
    }

    @Test
    void checksPublicMethodsAreDeclaredInInterfaces() {
        new PublicMethodsDeclaredInInterfacesRule().check(this.classes);
    }
}
