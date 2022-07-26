package one.digitalinnovation.cloudparking.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import one.digitalinnovation.cloudparking.controller.mapper.ParkingMapper;
import one.digitalinnovation.cloudparking.exception.InvalidParkingPropertyException;
import one.digitalinnovation.cloudparking.exception.ParkingNotFoundException;
import one.digitalinnovation.cloudparking.model.Parking;
import one.digitalinnovation.cloudparking.repository.ParkingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class ParkingService {

    private final ParkingMapper parkingMapper;
    private final ParkingRepository parkingRepository;

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<Parking> findAll() {
        return parkingRepository.findAll();
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public Parking findById(String id) {
        return parkingRepository.findById(id).orElseThrow(() -> new ParkingNotFoundException(id));
    }

    @Transactional
    public Parking create(Parking parkingCreate) {
        parkingCreate.setEntryDate(LocalDateTime.now());
        parkingRepository.save(parkingCreate);
        return parkingCreate;
    }

    @Transactional
    public void delete(String id) {
        findById(id);
        parkingRepository.deleteById(id);
    }

    @Transactional
    public Parking patch(String id, Map<String, Object> changes) {
        var parking = findById(id);
        changes.forEach((key, value) -> {
            try {
                PropertyDescriptor propertyDescriptor = new PropertyDescriptor(key, Parking.class);
                Method setter = propertyDescriptor.getWriteMethod();
                setter.invoke(parking, value);
            } catch (IntrospectionException | InvocationTargetException | IllegalAccessException e) {
                throw new InvalidParkingPropertyException(key);
            }
        });
        parkingRepository.save(parking);
        return parking;
    }

    @Transactional
    public Parking update(String id, Parking parkingToUpdate) {
        var parking = findById(id);
        parking.setModel(parkingToUpdate.getModel());
        parking.setState(parkingToUpdate.getState());
        parking.setLicense(parkingToUpdate.getLicense());
        parking.setColor(parkingToUpdate.getColor());
        parkingRepository.save(parking);
        return parking;
    }

    @Transactional
    public Parking checkout(String id) {
        var parking = findById(id);
        parking.setExitDate(LocalDateTime.now());
        parking.setBill(ParkingCheckout.getBill(parking));
        parkingRepository.save(parking);
        return parking;
    }
}
