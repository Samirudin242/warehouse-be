package com.fns.warehouse.service.domain.util;

import org.springframework.stereotype.Component;

@Component
public class DistanceCalculator {

    private static final double EARTH_RADIUS = 6371; // Radius of Earth in kilometers

    public double calculateDistance(double userLat, double userLng, double warehouseLat, double warehouseLng) {
        // Convert degrees to radians
        double latDistance = Math.toRadians(warehouseLat - userLat);
        double lngDistance = Math.toRadians(warehouseLng - userLng);

        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(userLat)) * Math.cos(Math.toRadians(warehouseLat))
                * Math.sin(lngDistance / 2) * Math.sin(lngDistance / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return EARTH_RADIUS * c;
    }
}

