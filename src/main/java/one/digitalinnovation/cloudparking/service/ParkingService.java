package one.digitalinnovation.cloudparking.service;

import one.digitalinnovation.cloudparking.model.Parking;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ParkingService {

    private static Map<String, Parking> parkingMap = new HashMap();

    static {
        var parking1 = Parking.builder()
                .id(getUUID())
                .color("PRETO")
                .license("MSS-1111")
                .model("VW GOL")
                .state("SP")
                .build();

        var parking2 = Parking.builder()
                .id(getUUID())
                .color("PRATA")
                .license("ABC-1234")
                .model("NISSAN VERSA")
                .state("SC")
                .build();

        parkingMap.put(parking1.getId(), parking1);
        parkingMap.put(parking2.getId(), parking2);
    }

    public List<Parking> findAll() {
        return parkingMap.values().stream().collect(Collectors.toList());
    }

    private static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
