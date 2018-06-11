package com.chameli.rtb;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.ArchRule;
import org.junit.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

public class ArchUnitTest {

    @Test
    public void domainShouldNotDependOnApplication() {
        JavaClasses importedClasses = new ClassFileImporter().importPackages("com.myapp");

        ArchRule rule = noClasses().that().resideInAnyPackage("..domain..").should().accessClassesThat().resideInAPackage("..application..");

        rule.check(importedClasses);
    }
}
