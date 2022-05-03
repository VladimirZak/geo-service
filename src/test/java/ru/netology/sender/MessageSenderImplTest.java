package ru.netology.sender;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.geo.GeoServiceImpl;
import ru.netology.i18n.LocalizationService;
import ru.netology.i18n.LocalizationServiceImpl;

import java.util.HashMap;
import java.util.Map;

public class MessageSenderImplTest {

    @ParameterizedTest
    @ValueSource(strings = {"172.0.32.11", "172."})
    public void testVerify_method_send_byRussian(String ipRU) {

        GeoService geoService = Mockito.mock(GeoServiceImpl.class);
        Location location = Mockito.mock(Location.class);
        Mockito.when(location.getCountry()).thenReturn(Country.RUSSIA);
        Mockito.when(geoService.byIp(ipRU)).thenReturn(location);
        LocalizationService localizationService = Mockito.mock(LocalizationServiceImpl.class);
        Mockito.when(localizationService.locale(location.getCountry())).thenReturn("Добро пожаловать");

        MessageSender messageSender = new MessageSenderImpl(geoService, localizationService);
        Map<String, String> headers = new HashMap<String, String>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, ipRU);

        String result = messageSender.send(headers);
        String expected = "Добро пожаловать";
        Assertions.assertNotNull(result);
        Assertions.assertEquals(expected, result);
    }

    @ParameterizedTest
    @ValueSource(strings = {"96.44.183.149", "96."})
    void testVerify_method_send_byUSA(String ipUSA) {

        GeoService geoService = Mockito.mock(GeoServiceImpl.class);
        Location location = Mockito.mock(Location.class);
        Mockito.when(location.getCountry()).thenReturn(Country.USA);
        Mockito.when(geoService.byIp(ipUSA)).thenReturn(location);
        LocalizationService localizationService = Mockito.mock(LocalizationServiceImpl.class);
        Mockito.when(localizationService.locale(location.getCountry())).thenReturn("Welcome");

        MessageSender messageSender = new MessageSenderImpl(geoService, localizationService);
        Map<String, String> headers = new HashMap<String, String>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, ipUSA);

        String result = messageSender.send(headers);
        String expected = "Welcome";
        Assertions.assertNotNull(result);
        Assertions.assertEquals(expected, result);
    }
}
