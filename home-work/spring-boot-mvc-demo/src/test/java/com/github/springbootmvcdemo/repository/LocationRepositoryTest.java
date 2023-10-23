package com.github.springbootmvcdemo.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
// import org.springframework.test.context.TestPropertySource;

import com.github.springbootmvcdemo.models.Location;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE) // Don't replace the application default DataSource
@Rollback(false) // keep data in database after test
@Nested
// @TestPropertySource(
// properties = {
// "spring.datasource.url=jdbc:mysql://42.117.7.84:30778/weatherdb"
// }
// )
public class LocationRepositoryTest {

    @Autowired
    private LocationRepository repo;

    @DisplayName("Add Location Success")
    @Test
    public void testAddLocationSuccess() {
        /*
         * Expected value
         */
        Location location = new Location();
        location.setCode("NYC_USA");
        location.setCityName("New York City");
        location.setRegionName("NY");
        location.setCountryCode("US");
        location.setCountryName("United State of America");
        location.setEnable(1);
        location.setTrashed(0);

        /*
         * Execute test
         */
        Location result = this.repo.save(location);

        /*
         * check result with expected value
         */
        Assertions.assertThat(result).isNotNull();
        Assertions.assertThat(result.getCode()).isEqualTo(location.getCode());
    }

    @DisplayName("GET Location Success")
    @Test
    @Disabled
    public void testGetCodeFound() {
        /*
         * Expected value
         */
        String code = "NYC_USA";

        /*
         * Execute test
         */
        Location result = this.repo.findByCode(code);

        /*
         * check result with expected value
         */
        Assertions.assertThat(result).isNotNull();
        Assertions.assertThat(result.getCode()).isEqualTo(code);
    }

    @DisplayName("GET Location Failure")
    @Test
    public void testGetCodeNotFound() {
        /*
         * Expected value
         */
        String code = "ABCD";

        /*
         * Execute test
         */
        Location result = this.repo.findByCode(code);

        /*
         * check result with expected value
         */
        Assertions.assertThat(result).isNull();
    }

    @DisplayName("Delete Location Success")
    @Test
    public void testUpdateTrashedByCode() {
        /*
         * Expected value
         */
        String code = "NYC_USA";

        /*
         * Execute test
         */
        this.repo.trashedByCode(code);

        /*
         * check result with expected value
         */
        Location result = this.repo.findByCode(code);

        Assertions.assertThat(result).isNull();
    }
}
