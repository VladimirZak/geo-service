package ru.netology.i18n;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.netology.entity.Country;

class LocalizationServiceImplTest {

    @Test
    void testMethod_local_with_Russia() {
        Country rus = Country.RUSSIA;
        String expected = "Добро пожаловать";

        LocalizationServiceImpl localizationService = new LocalizationServiceImpl();
        String result = localizationService.locale(rus);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(expected, result);
    }

    @Test
    void testMethod_local_with_USA() {
        Country usa = Country.USA;
        String expected = "Welcome";

        LocalizationServiceImpl localizationService = new LocalizationServiceImpl();
        String result = localizationService.locale(usa);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(expected, result);
    }
}
