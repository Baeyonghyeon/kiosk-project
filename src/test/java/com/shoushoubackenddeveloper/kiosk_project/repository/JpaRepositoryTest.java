package com.shoushoubackenddeveloper.kiosk_project.repository;

import com.shoushoubackenddeveloper.kiosk_project.domain.Coffee;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("JPA 연결 테스트")
@ActiveProfiles("test")
@DataJpaTest
class JpaRepositoryTest {

    private final CoffeeRepository coffeeRepository;

    public JpaRepositoryTest(
            @Autowired CoffeeRepository coffeeRepository) {
        this.coffeeRepository = coffeeRepository;
    }

    @DisplayName("Coffee insert 테스트")
    @Test
    void givenTestData_whenInserting_thenWorksFine() {
        // Given
        long previousCount = coffeeRepository.count();
        Coffee coffee = Coffee.of("h_103", "카푸치노", "Cappuccino", 4700, "판매중", true);

        // When
        coffeeRepository.save(coffee);

        // Then
        assertThat(coffeeRepository.count())
                .isEqualTo(previousCount + 1);
    }
}
