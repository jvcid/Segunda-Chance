package com.unifor.segundachance.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CategoryRequestDTO {

    @NotBlank(message = "O nome da categoria é obrigatório")
        @Size(max = 100, message = "O nome deve ter no máximo 100 caracteres")

    private String name;

    public CategoryRequestDTO(){

    }

    public String getName(){
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}



