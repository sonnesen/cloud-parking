package one.digitalinnovation.cloudparking.controller;

import lombok.RequiredArgsConstructor;
import one.digitalinnovation.cloudparking.controller.dto.ParkingCreateDTO;
import one.digitalinnovation.cloudparking.controller.dto.ParkingDTO;
import one.digitalinnovation.cloudparking.controller.dto.ParkingUpdateDTO;
import one.digitalinnovation.cloudparking.controller.mapper.ParkingMapper;
import one.digitalinnovation.cloudparking.model.Parking;
import one.digitalinnovation.cloudparking.service.ParkingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class ParkingController implements ParkingAPI {

    private final ParkingService parkingService;
    private final ParkingMapper parkingMapper;

    @Override
    public ResponseEntity<List<ParkingDTO>> findAll() {
        List<Parking> parkingList = parkingService.findAll();
        var parkingDTOList = parkingMapper.toParkingDTOList(parkingList);

        return ResponseEntity.ok(parkingDTOList);
    }

    @Override
    public ResponseEntity<ParkingDTO> findById(String id) {
        var parking = parkingService.findById(id);
        var parkingDTO = parkingMapper.toParkingDTO(parking);

        return ResponseEntity.ok(parkingDTO);
    }

    @Override
    public ResponseEntity<ParkingDTO> create(ParkingCreateDTO dto) {
        var parkingCreate = parkingMapper.toParking(dto);
        var parking = parkingService.create(parkingCreate);
        var parkingDTO = parkingMapper.toParkingDTO(parking);

        return ResponseEntity.status(HttpStatus.CREATED).body(parkingDTO);
    }

    @Override
    public ResponseEntity<Void> delete(String id) {
        parkingService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<ParkingDTO> patch(String id, Map<String, Object> changes) {
        var parking = parkingService.patch(id, changes);
        var parkingDTO = parkingMapper.toParkingDTO(parking);

        return ResponseEntity.status(HttpStatus.OK).body(parkingDTO);
    }

    @Override
    public ResponseEntity<ParkingDTO> update(String id, ParkingUpdateDTO dto) {
        var parking = parkingMapper.toParking(dto);
        parking = parkingService.update(id, parking);
        var parkingDTO = parkingMapper.toParkingDTO(parking);

        return ResponseEntity.status(HttpStatus.OK).body(parkingDTO);
    }

    @Override
    public ResponseEntity<ParkingDTO> checkout(String id) {
        var parking = parkingService.checkout(id);
        var parkingDTO = parkingMapper.toParkingDTO(parking);

        return ResponseEntity.ok(parkingDTO);
    }
}
