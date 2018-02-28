package com.emerchantpay.gateway;

import com.emerchantpay.gateway.api.constants.ErrorCodes;
import com.emerchantpay.gateway.api.exceptions.ApiException;
import com.emerchantpay.gateway.api.requests.wpf.WPFCreateRequest;
import com.emerchantpay.gateway.api.requests.wpf.WPFReconcileRequest;
import com.emerchantpay.gateway.util.Country;
import com.emerchantpay.gateway.util.Currency;
import com.emerchantpay.gateway.util.StringUtils;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

public class WPFRequestsTest {

    private GenesisClient client;
    private WPFCreateRequest wpfCreate;
    private WPFReconcileRequest wpfReconcile;

    private String uidWpf;
    private String uidReconcile;

    @Before
    public void createWPF() throws MalformedURLException {
        uidWpf = new StringUtils().generateUID();

        client = mock(GenesisClient.class);
        wpfCreate = mock(WPFCreateRequest.class);
    }

    @Before
    public void createReconcile() {
        uidReconcile = new StringUtils().generateUID();

        client = mock(GenesisClient.class);
        wpfReconcile = mock(WPFReconcileRequest.class);
    }

    public void clearRequiredParams() {
        Integer errorCode = ErrorCodes.INPUT_DATA_ERROR.getCode();
        ApiException exception = new ApiException(errorCode, ErrorCodes.getErrorDescription(errorCode),
                new Throwable());

        when(wpfCreate.setBillingCountry(null)).thenThrow(exception);
        when(wpfReconcile.setUniqueId(null)).thenThrow(exception);
    }

    public void clearCustomerInfoParams() {
        Integer errorCode = ErrorCodes.INPUT_DATA_ERROR.getCode();
        ApiException exception = new ApiException(errorCode, ErrorCodes.getErrorDescription(errorCode),
                new Throwable());

        when(wpfCreate.setCustomerEmail(null)).thenThrow(exception);
        when(wpfCreate.setCustomerPhone(null)).thenThrow(exception);
    }

    public void verifyWPFExecute() {
        when(client.execute()).thenReturn(wpfCreate);
        assertEquals(client.execute(), wpfCreate);
        verify(client).execute();
        verifyNoMoreInteractions(client);
    }

    public void verifyReconcileExecute() {
        when(client.execute()).thenReturn(wpfReconcile);
        assertEquals(client.execute(), wpfReconcile);
        verify(client).execute();
        verifyNoMoreInteractions(client);
    }

    @Test
    public void testWPF() throws MalformedURLException {

        // Create WPF
        when(wpfCreate.setTransactionId(isA(String.class))).thenReturn(wpfCreate);
        when(wpfCreate.setUsage(isA(String.class))).thenReturn(wpfCreate);
        when(wpfCreate.setDescription(isA(String.class))).thenReturn(wpfCreate);
        when(wpfCreate.setAmount(isA(BigDecimal.class))).thenReturn(wpfCreate);
        when(wpfCreate.setCurrency(isA(String.class))).thenReturn(wpfCreate);
        when(wpfCreate.setCustomerEmail(isA(String.class))).thenReturn(wpfCreate);
        when(wpfCreate.setCustomerPhone(isA(String.class))).thenReturn(wpfCreate);
        when(wpfCreate.setNotificationUrl(isA(URL.class))).thenReturn(wpfCreate);
        when(wpfCreate.setReturnSuccessUrl(isA(URL.class))).thenReturn(wpfCreate);
        when(wpfCreate.setReturnFailureUrl(isA(URL.class))).thenReturn(wpfCreate);
        when(wpfCreate.setReturnCancelUrl(isA(URL.class))).thenReturn(wpfCreate);
        when(wpfCreate.setBillingPrimaryAddress(isA(String.class))).thenReturn(wpfCreate);
        when(wpfCreate.setBillingSecondaryAddress(isA(String.class))).thenReturn(wpfCreate);
        when(wpfCreate.setBillingFirstname(isA(String.class))).thenReturn(wpfCreate);
        when(wpfCreate.setBillingLastname(isA(String.class))).thenReturn(wpfCreate);
        when(wpfCreate.setBillingCity(isA(String.class))).thenReturn(wpfCreate);
        when(wpfCreate.setBillingCountry(isA(String.class))).thenReturn(wpfCreate);
        when(wpfCreate.setBillingZipCode(isA(String.class))).thenReturn(wpfCreate);
        when(wpfCreate.setBillingState(isA(String.class))).thenReturn(wpfCreate);
        when(wpfCreate.setLifetime(isA(Integer.class))).thenReturn(wpfCreate);

        assertEquals(wpfCreate.setTransactionId(uidWpf).setUsage("TICKETS"), wpfCreate);
        assertEquals(wpfCreate.setDescription("TEST Description"), wpfCreate);
        assertEquals(wpfCreate.setCurrency(Currency.USD.getCurrency())
                .setAmount(new BigDecimal("2.00")), wpfCreate);
        assertEquals(wpfCreate.setCustomerEmail("john@example.com").setCustomerPhone("+55555555"), wpfCreate);
        assertEquals(wpfCreate.setReturnSuccessUrl(new URL("https://www.example.com/success"))
                .setReturnFailureUrl(new URL("https://www.example.com/failure")), wpfCreate);
        assertEquals(wpfCreate.setReturnCancelUrl(new URL("https://example.com/return_cancel"))
                .setNotificationUrl(new URL("https://example.com/notification")), wpfCreate);
        assertEquals(wpfCreate.setBillingPrimaryAddress("Berlin")
                .setBillingSecondaryAddress("Berlin")
                .setBillingFirstname("Plamen")
                .setBillingLastname("Petrov").setBillingCity("Berlin")
                .setBillingCountry(Country.Germany.getCode())
                .setBillingZipCode("M4B1B3")
                .setBillingState("BE"), wpfCreate);
        assertEquals(wpfCreate.setLifetime(60), wpfCreate);

        verify(wpfCreate).setTransactionId(uidWpf);
        verify(wpfCreate).setUsage("TICKETS");
        verify(wpfCreate).setDescription("TEST Description");
        verify(wpfCreate).setCurrency(Currency.USD.getCurrency());
        verify(wpfCreate).setAmount(new BigDecimal("2.00"));
        verify(wpfCreate).setCustomerEmail("john@example.com");
        verify(wpfCreate).setCustomerPhone("+55555555");
        verify(wpfCreate).setReturnSuccessUrl(new URL("https://www.example.com/success"));
        verify(wpfCreate).setReturnFailureUrl(new URL("https://www.example.com/failure"));
        verify(wpfCreate).setReturnCancelUrl(new URL("https://example.com/return_cancel"));
        verify(wpfCreate).setNotificationUrl(new URL("https://example.com/notification"));
        verify(wpfCreate).setBillingPrimaryAddress("Berlin");
        verify(wpfCreate).setBillingSecondaryAddress("Berlin");
        verify(wpfCreate).setBillingFirstname("Plamen");
        verify(wpfCreate).setBillingLastname("Petrov");
        verify(wpfCreate).setBillingCity("Berlin");
        verify(wpfCreate).setBillingCountry(Country.Germany.getCode());
        verify(wpfCreate).setBillingZipCode("M4B1B3");
        verify(wpfCreate).setBillingState("BE");
        verify(wpfCreate).setLifetime(60);
        verifyNoMoreInteractions(wpfCreate);

        verifyWPFExecute();
    }

    @Test
    public  void testReconcile() {
        when(wpfReconcile.setUniqueId(isA(String.class))).thenReturn(wpfReconcile);

        assertEquals(wpfReconcile.setUniqueId(uidReconcile), wpfReconcile);

        verify(wpfReconcile).setUniqueId(uidReconcile);
    }

    @Test(expected = ApiException.class)
    public void testWPFWithMissingRequiredParams() {
        clearRequiredParams();
        assertNull(wpfCreate.setBillingCountry(null));
        verify(wpfCreate).setBillingCountry(null);
        verifyNoMoreInteractions(wpfCreate);

        verifyWPFExecute();
    }

    @Test(expected = ApiException.class)
    public void testWPFWithMissingCustomerInfoParams() {
        clearCustomerInfoParams();
        assertNull(wpfCreate.setCustomerEmail(null));
        verify(wpfCreate).setCustomerEmail(null);
        assertNull(wpfCreate.setCustomerPhone(null));
        verify(wpfCreate).setCustomerPhone(null);
        verifyNoMoreInteractions(wpfCreate);

        verifyWPFExecute();
    }

    @Test(expected = ApiException.class)
    public void testWPFReconcileWithMissingParams() {
        clearRequiredParams();
        assertNull(wpfReconcile.setUniqueId(null));
        verify(wpfReconcile).setUniqueId(null);
        verifyNoMoreInteractions(wpfReconcile);

        verifyReconcileExecute();
    }
}