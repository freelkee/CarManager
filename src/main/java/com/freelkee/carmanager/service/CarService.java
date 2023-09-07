package com.freelkee.carmanager.service;

import com.freelkee.carmanager.entity.Owner;
import com.freelkee.carmanager.entity.Seller;
import com.freelkee.carmanager.repository.CarRepository;
import com.freelkee.carmanager.response.CarResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CarService {

    private final CarRepository carRepository;

    public CarService(final CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public List<CarResponse> getCars() {
        return carRepository.findAll().stream()
            .map(CarResponse::of)
            .collect(Collectors.toList());
    }

    public Set<Owner> getOwners(final Long carId) {
        return carRepository.getReferenceById(carId).getOwners();
    }

    public Set<Seller> getSellers(final Long carId) {
        return carRepository.getReferenceById(carId).getSellers();
    }

    public List<CarResponse> getCarsBetweenYears(int year1, int year2){
        return carRepository.getAllByYearBetweenOrderByYearAsc(year1, year2).stream()
            .map(CarResponse::of).collect(Collectors.toList());
    }

    public List<CarResponse> getCarsBetweenPrice(int price1, int price2){
        return carRepository.getAllByPriceBetweenOrderByPriceAsc(price1, price2).stream()
            .map(CarResponse::of).collect(Collectors.toList());
    }
}
