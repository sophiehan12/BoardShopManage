package com.example.middleproject.dto;

import com.example.middleproject.model.MyshopEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class MyshopDTO {
    private String id;
    private String userId;
    private String title;
    private int length;
    private String type;


    public MyshopDTO(final MyshopEntity entity){
        this.id = entity.getId();
        this.userId = entity.getUserId();
        this.title = entity.getTitle();
        this.length = entity.getLength();
        this.type = entity.getType();
    }

    public static MyshopEntity toEntity(final MyshopDTO dto){
        return MyshopEntity.builder()
                .id(dto.getId())
                .userId(dto.getUserId())
                .title(dto.getTitle())
                .length(dto.getLength())
                .type(dto.getType())
                .build();
    }
}
