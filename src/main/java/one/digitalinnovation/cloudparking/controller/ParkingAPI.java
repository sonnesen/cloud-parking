package one.digitalinnovation.cloudparking.controller;

import io.swagger.annotations.ApiOperation;
import one.digitalinnovation.cloudparking.controller.dto.ParkingCreateDTO;
import one.digitalinnovation.cloudparking.controller.dto.ParkingDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface ParkingAPI {
    @GetMapping
    @ApiOperation("Find all parkings")
    ResponseEntity<List<ParkingDTO>> findAll();

    @GetMapping(value = "/{id}")
    @ApiOperation("Retrive a parking by ID")
    ResponseEntity<ParkingDTO> findById(@PathVariable String id);

    @PostMapping
    @ApiOperation("Create a parking")
    ResponseEntity<ParkingDTO> create(@RequestBody ParkingCreateDTO dto);
}
