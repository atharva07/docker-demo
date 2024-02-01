package com.atharvahiwase07.utils;

import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.atharvahiwase07.tests.vendorPortal.model.VendorPortalTestData;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil {
    private static final Logger log = LoggerFactory.getLogger(JsonUtil.class);
    private static final ObjectMapper mapper = new ObjectMapper();

    public static VendorPortalTestData getTestData(String path) {
        try(InputStream stream = ResourceLoader.getResource(path)) {
            return mapper.readValue(stream, VendorPortalTestData.class);
        } catch (Exception e) {
            log.error("urable to read test data {}", path, e);
        }
        return null;

    }
}
