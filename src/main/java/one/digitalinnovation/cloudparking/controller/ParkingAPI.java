package one.digitalinnovation.cloudparking.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import one.digitalinnovation.cloudparking.controller.dto.ParkingCreateDTO;
import one.digitalinnovation.cloudparking.controller.dto.ParkingDTO;
import one.digitalinnovation.cloudparking.controller.dto.ParkingUpdateDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Api(value = "parkings", tags = "Parking API")
public interface ParkingAPI {
    @GetMapping(value = "/parkings")
    @ApiOperation("Find all parkings")
    ResponseEntity<List<ParkingDTO>> findAll();

    @GetMapping(value = "/parkings/{id}")
    @ApiOperation("Retrive a parking by ID")
    ResponseEntity<ParkingDTO> findById(@PathVariable String id);

    @PostMapping(value = "/parkings")
    @ApiOperation("Create a parking")
    ResponseEntity<ParkingDTO> create(@RequestBody ParkingCreateDTO dto);

    @DeleteMapping(value = "/parkings/{id}")
    @ApiOperation("Delete a parking by ID")
    ResponseEntity<Void> delete(@PathVariable String id);

    @PatchMapping(value = "/parkings/{id}")
    @ApiOperation("Partial update a parking by ID")
    ResponseEntity<ParkingDTO> patch(@PathVariable String id, @RequestBody Map<String, Object> fields);

    @PutMapping(value = "/parkings/{id}")
    @ApiOperation("Update a parking by ID")
    ResponseEntity<ParkingDTO> update(@PathVariable String id, @RequestBody ParkingUpdateDTO dto);

    @PostMapping(value = "/parkings/{id}")
    @ApiOperation("Register a parking checkout by ID")
    ResponseEntity<ParkingDTO> checkout(@PathVariable String id);
}
