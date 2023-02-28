package com.example.javabootcampfinalproject.DTO;

import com.example.javabootcampfinalproject.Utility.Enum.Action;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ActionDTO {
    @NotNull
    private Action action;
}
