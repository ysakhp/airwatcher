package ust.airwatcher.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ust.airwatcher.dto.AirResponseDto;
import ust.airwatcher.service.AirWatcherRestClient;

@RestController
@RequestMapping("/api/v1/airwatcher")
public class AirWatcherController {

    @Autowired
    AirWatcherRestClient airWatcherRestClient;

    @GetMapping("/countries")
    public ResponseEntity<AirResponseDto> getCountries() {
        return ResponseEntity.ok(airWatcherRestClient.getCountries());
    }

    @GetMapping("/states/{country}")
    public ResponseEntity<AirResponseDto> getStates(@PathVariable("country") String country) {
        return ResponseEntity.ok(airWatcherRestClient.getStatesByCountry(country));
    }

    @GetMapping("/cities/{state}/{country}")
    public ResponseEntity<AirResponseDto> getCities(@PathVariable("state") String state, @PathVariable("country") String country) {
        return ResponseEntity.ok(airWatcherRestClient.getCities(state, country));
    }

    @GetMapping("/airquality/{city}/{state}/{country}")
    public ResponseEntity<AirResponseDto> getAirQuality(@PathVariable("city") String city, @PathVariable("state") String state,
                                                        @PathVariable("country") String country) {
        return ResponseEntity.ok(airWatcherRestClient.getAirQuality(city, state, country));
    }
}
