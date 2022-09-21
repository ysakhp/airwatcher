package ust.airwatcher.dto;

import lombok.Data;

import java.util.List;
@Data
public class HistoryDto {
    private Weather weather;
    private List<Pollution> pollution;
}
