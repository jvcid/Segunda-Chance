package com.unifor.segundachance.dto.response;


public class CategoryResponseDTO {

    private Integer id;
    private String name;

    public CategoryResponseDTO(){
    }

    public CategoryResponseDTO(Integer id, String name){
        this.id = id;
        this.name = name;
    }

    public Integer getId(){
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName(){
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}

