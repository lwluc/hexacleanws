package com.hexaclean.arc.demo.exercises.two.three;

import com.hexaclean.arc.demo.exercises.BaseArchUnitExerciseTest;
import com.tngtech.archunit.lang.ArchRule;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

public class ArchitectureTestExercise2_3 extends BaseArchUnitExerciseTest {

    @Test
    @DisplayName("A usecase.out should be implemented by an adapter.out implementation")
    void usecase_out_implementation_check() throws ClassNotFoundException {
        ArchRule rule = classes()
                .that()
                .haveSimpleName(REPOSITORY_UNDER_TEST)
                .should()
                .implement(Class.forName("com.hexaclean.arc.demo.app.vehicle.usecase.out.VehicleDbQuery"));
        rule.check(classes);
    }

    @Test
    @DisplayName("A usecase.out should be located in the correct package and only depends on domain.model")
    void usecase_out_check() {
        ArchRule rule = classes()
                .that()
                .haveSimpleName(USECASE_OUT_QUERY_UNDER_TEST)
                .should()
                .resideInAnyPackage(USECASE, USECASE_OUT)
                .andShould()
                .onlyAccessClassesThat()
                .resideInAnyPackage(DOMAIN, DOMAIN_MODEL);
        rule.check(classes);
    }

    @Test
    @DisplayName("A adapter.out implementation should be located in the correct package and only depend on domain.model and usecase.out")
    void adapter_out_check() {
        ArchRule rule = classes()
                .that()
                .haveSimpleName(REPOSITORY_UNDER_TEST)
                .should()
                .resideInAnyPackage(ADAPTER, ADAPTER_OUT)
                .andShould()
                .onlyAccessClassesThat()
                .resideInAnyPackage(ADAPTER, ADAPTER_OUT, USECASE_OUT, USECASE, DOMAIN, DOMAIN_MODEL, JAVA_LANG);
        rule.check(classes);
    }

}
