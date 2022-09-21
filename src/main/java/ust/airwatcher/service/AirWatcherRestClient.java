package ust.airwatcher.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ust.airwatcher.config.ConfigProperties;
import ust.airwatcher.dto.*;
import ust.airwatcher.exception.RestClientAccessException;
import ust.airwatcher.util.AirPollutionConstants;

@Service
public class AirWatcherRestClient {

    @Value("${air.pollution.apikey}")
    private String key;

    @Autowired
    ConfigProperties configProperties;

    @Autowired
    ExternalAPIService externalAPIService;

    public AirResponseDto<CountryDto> getCountries() {

        return externalAPIService.getWebClient(configProperties.getBaseUrl()).get()
                .uri(
                        uriBuilder -> uriBuilder.path(AirPollutionConstants.GET_COUNTRIES_URI)
                                .queryParam(AirPollutionConstants.STRING_KEY, configProperties.getApiKey())
                                .build())
                .retrieve()
                .bodyToMono(AirResponseDto.class)
                .block();

    }

    public AirResponseDto<StateDto> getStatesByCountry(String country) {

        try {
            return externalAPIService.getWebClient(configProperties.getBaseUrl()).get()
                    .uri(
                            uriBuilder -> uriBuilder.path(AirPollutionConstants.GET_STATES_URI)
                                    .queryParam(AirPollutionConstants.STRING_COUNTRY, country)
                                    .queryParam(AirPollutionConstants.STRING_KEY, configProperties.getApiKey())
                                    .build())
                    .retrieve()
                    .bodyToMono(AirResponseDto.class)
                    .block();


        } catch (Exception e) {
            throw new RestClientAccessException();
        }

    }

    public AirResponseDto<CityDto> getCities(String state, String country) {
        try {
            return externalAPIService.getWebClient(configProperties.getBaseUrl()).get()
                    .uri(
                            uriBuilder -> uriBuilder.path(AirPollutionConstants.GET_CITIES_URI)
                                    .queryParam(AirPollutionConstants.STRING_STATE, state)
                                    .queryParam(AirPollutionConstants.STRING_COUNTRY, country)
                                    .queryParam(AirPollutionConstants.STRING_KEY, configProperties.getApiKey())
                                    .build())
                    .retrieve()
                    .bodyToMono(AirResponseDto.class)
                    .block();

        } catch (Exception e) {
            throw new RestClientAccessException();
        }
    }

    public AirResponseDto<AirQualityDto> getAirQuality(String city, String state, String country) {
        try {
            return externalAPIService.getWebClient(configProperties.getBaseUrl()).get()
                    .uri(
                            uriBuilder -> uriBuilder.path(AirPollutionConstants.GET_CITY_AIR_QUALITY_URI)
                                    .queryParam(AirPollutionConstants.STRING_CITY, city)
                                    .queryParam(AirPollutionConstants.STRING_STATE, state)
                                    .queryParam(AirPollutionConstants.STRING_COUNTRY, country)
                                    .queryParam(AirPollutionConstants.STRING_KEY, configProperties.getApiKey())
                                    .build())
                    .retrieve()
                    .bodyToMono(AirResponseDto.class)
                    .block();

        } catch (Exception e) {
            throw new RestClientAccessException();
        }

    }
}
