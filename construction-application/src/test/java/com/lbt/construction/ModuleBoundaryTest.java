package com.lbt.construction;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.ArchRule;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

class ModuleBoundaryTest {

    private final JavaClasses classes = new ClassFileImporter().importPackages("com.lbt.construction");

    @Test
    void siteModuleDoesNotDependOnOtherFeatureModules() {
        ArchRule rule = noClasses()
                .that().resideInAPackage("com.lbt.construction.site..")
                .should().dependOnClassesThat().resideInAnyPackage(
                        "com.lbt.construction.material..",
                        "com.lbt.construction.customer..",
                        "com.lbt.construction.price..",
                        "com.lbt.construction.document.."
                );
        rule.check(classes);
    }

    @Test
    void featureModulesTalkToSiteOnlyThroughItsApi() {
        ArchRule rule = noClasses()
                .that().resideInAnyPackage(
                        "com.lbt.construction.material..",
                        "com.lbt.construction.customer..",
                        "com.lbt.construction.price..",
                        "com.lbt.construction.document.."
                )
                .should().dependOnClassesThat().resideInAnyPackage(
                        "com.lbt.construction.site.domain..",
                        "com.lbt.construction.site.repository..",
                        "com.lbt.construction.site.service..",
                        "com.lbt.construction.site.web.."
                );
        rule.check(classes);
    }

    @Test
    void featureModulesDoNotDependOnEachOther() {
        assertNoDependency("com.lbt.construction.material..",
                "com.lbt.construction.customer..",
                "com.lbt.construction.price..",
                "com.lbt.construction.document..");
        assertNoDependency("com.lbt.construction.customer..",
                "com.lbt.construction.material..",
                "com.lbt.construction.price..",
                "com.lbt.construction.document..");
        assertNoDependency("com.lbt.construction.price..",
                "com.lbt.construction.material..",
                "com.lbt.construction.customer..",
                "com.lbt.construction.document..");
        assertNoDependency("com.lbt.construction.document..",
                "com.lbt.construction.material..",
                "com.lbt.construction.customer..",
                "com.lbt.construction.price..");
    }

    private void assertNoDependency(String sourcePackage, String... forbiddenPackages) {
        noClasses()
                .that().resideInAPackage(sourcePackage)
                .should().dependOnClassesThat().resideInAnyPackage(forbiddenPackages)
                .check(classes);
    }
}
