package com.atharvahiwase07.tests.flightreservation.model;

public record FlightReservationTestData(String firstname, String lastname,
                                        String email, String password,
                                        String street, String city,
                                        String zip, int passengerCount,
                                        int expectedPrice) {
    
}
