package ru.netology.geo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import ru.netology.entity.Country;
import ru.netology.entity.Location;

import static org.junit.jupiter.api.Assertions.*;
import static ru.netology.entity.Country.RUSSIA;
import static ru.netology.entity.Country.USA;

class GeoServiceImplTest {

    @ParameterizedTest
    @ValueSource(strings = {"172.0.32.11", "172."})
    void  testVerify_byIp_RU(String moscowIP) {

    GeoServiceImpl geoService = new GeoServiceImpl();
    Location location = geoService.byIp(moscowIP);
    Country result = location.getCountry();
    assertNotNull(result);
    assertEquals(RUSSIA, result);
    }

    @ParameterizedTest
    @ValueSource(strings = {"96.44.183.149", "96."})
    void  testVerify_byIp_USA(String newYorkIP) {

        GeoServiceImpl geoService = new GeoServiceImpl();
        Location location = geoService.byIp(newYorkIP);
        Country result = location.getCountry();
        assertNotNull(result);
        assertEquals(USA, result);
    }
}

