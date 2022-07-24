package one.digitalinnovation.cloudparking.service;

import one.digitalinnovation.cloudparking.model.Parking;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ParkingService {

    private static Map<String, Parking> parkingMap = new HashMap();

    static {
        var parking1 = Parking.builder()
                .id(getUUID())
                .color("PRETO")
                .license("MSS-1111")
                .model("GOL")
                .state("SP")
                .entryDate(LocalDateTime.now())
                .build();

        var parking2 = Parking.builder()
                .id(getUUID())
                .color("PRATA")
                .license("ABC-1234")
                .model("NISSAN VERSA")
                .state("SC")
                .entryDate(LocalDateTime.now())
                .build();

        parkingMap.put(parking1.getId(), parking1);
        parkingMap.put(parking2.getId(), parking2);
    }

    private static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public List<Parking> findAll() {
        return parkingMap.values().stream().collect(Collectors.toList());
    }

    public Parking findById(String id) {
        return parkingMap.get(id);
    }

    public Parking create(Parking newParking) {
        newParking.setId(getUUID());
        newParking.setEntryDate(LocalDateTime.now());

        parkingMap.put(newParking.getId(), newParking);

        return newParking;
    }
}
