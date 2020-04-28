package com.budgetyoufool.model.DTO;

import lombok.Data;

import java.time.LocalDate;

@Data
public class TimeRangeDTO {

    LocalDate start;
    LocalDate end;

}
