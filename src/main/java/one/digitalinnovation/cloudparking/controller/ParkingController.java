package one.digitalinnovation.cloudparking.controller;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import one.digitalinnovation.cloudparking.controller.dto.ParkingCreateDTO;
import one.digitalinnovation.cloudparking.controller.dto.ParkingDTO;
import one.digitalinnovation.cloudparking.controller.mapper.ParkingMapper;
import one.digitalinnovation.cloudparking.model.Parking;
import one.digitalinnovation.cloudparking.service.ParkingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/parkings")
@RequiredArgsConstructor
@Api(tags = "ParkingController")
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
    public ResponseEntity<ParkingDTO> findById(@PathVariable String id) {
        var parking = parkingService.findById(id);
        var parkingDTO = parkingMapper.toParkingDTO(parking);

        return ResponseEntity.ok(parkingDTO);
    }

    @Override
    public ResponseEntity<ParkingDTO> create(@RequestBody ParkingCreateDTO dto) {
        var parkingCreate = parkingMapper.toParking(dto);
        var parking = parkingService.create(parkingCreate);
        var parkingDTO = parkingMapper.toParkingDTO(parking);

        return ResponseEntity.status(HttpStatus.CREATED).body(parkingDTO);
    }
}
