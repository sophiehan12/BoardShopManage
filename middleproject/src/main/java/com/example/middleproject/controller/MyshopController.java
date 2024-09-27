package com.example.middleproject.controller;

import com.example.middleproject.dto.MyshopDTO;
import com.example.middleproject.dto.ResponseDTO;
import com.example.middleproject.model.MyshopEntity;
import com.example.middleproject.service.MyshopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("myshop")
public class MyshopController {

    @Autowired
    private MyshopService service;


    @PostMapping
    public ResponseEntity<?> createMyshop(@AuthenticationPrincipal String userId,
                                          @RequestBody MyshopDTO dto){
        try{
            //String temporaryUserId = "temporary-user";

            MyshopEntity entity = MyshopDTO.toEntity(dto);
            entity.setId(null);
            entity.setUserId(userId);
            //entity.setUserId(dto.getUserId());
            List<MyshopEntity> entities = service.create(entity);
            List<MyshopDTO> dtos = entities.stream().map(MyshopDTO::new).collect(Collectors.toList());
            ResponseDTO<MyshopDTO> response = ResponseDTO.<MyshopDTO>builder().data(dtos).build();
            return ResponseEntity.ok().body(response);
        }catch (Exception e){
            String error = e.getMessage();
            ResponseDTO<MyshopDTO> response = ResponseDTO.<MyshopDTO>builder().error(error).build();
            return ResponseEntity.badRequest().body(response);
        }
    }

    @GetMapping
    public ResponseEntity<?> retrieveMyshopList(
            @AuthenticationPrincipal String userId){
        //String temporaryUserId = "temporary-user";

        List<MyshopEntity> entities = service.retrieve(userId);
        List<MyshopDTO> dtos = entities.stream().map(MyshopDTO::new).collect(Collectors.toList());
        ResponseDTO<MyshopDTO> response = ResponseDTO.<MyshopDTO>builder().data(dtos).build();
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/search2")
    public ResponseEntity<?> retrieveMyshopList2(){
        //String temporaryUserId = "temporary-user";

        List<MyshopEntity> entities = service.retrieve2();
        List<MyshopDTO> dtos = entities.stream().map(MyshopDTO::new).collect(Collectors.toList());
        ResponseDTO<MyshopDTO> response = ResponseDTO.<MyshopDTO>builder().data(dtos).build();
        return ResponseEntity.ok().body(response);
    }

    @PutMapping
    public ResponseEntity<?> updateMyshop(@AuthenticationPrincipal String userId,
                                            @RequestBody MyshopDTO dto) {
        //String temporaryUserId = "temporary-user";

        MyshopEntity entity = MyshopDTO.toEntity(dto);
        entity.setUserId(userId);
        List<MyshopEntity> entities = service.update(entity);
        List<MyshopDTO> dtos = entities.stream().map(MyshopDTO::new).collect(Collectors.toList());
        ResponseDTO<MyshopDTO> response = ResponseDTO.<MyshopDTO>builder().data(dtos).build();
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteMyshop(@AuthenticationPrincipal String userId,
                                          @RequestBody MyshopDTO dto) {
        try{
            //String temporaryUserId = "temporary-user";
            MyshopEntity entity  = MyshopDTO.toEntity(dto);
            entity.setUserId(userId);

            List<MyshopEntity> entities = service.delete(entity);
            List<MyshopDTO> dtos = entities.stream().map(MyshopDTO::new).collect(Collectors.toList());
            ResponseDTO<MyshopDTO> response = ResponseDTO.<MyshopDTO>builder().data(dtos).build();
            return ResponseEntity.ok().body(response);
        }catch (Exception e){
            String error = e.getMessage();
            ResponseDTO<MyshopDTO> response = ResponseDTO.<MyshopDTO>builder().error(error).build();
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PostMapping("/search")
    public ResponseEntity<?> searchMyshop(@RequestBody MyshopDTO dto){
        String mytitle = dto.getTitle();
        List<MyshopEntity> entities = service.search(mytitle);
        List<MyshopDTO> dtos = entities.stream().map(MyshopDTO::new).collect(Collectors.toList());
        ResponseDTO<MyshopDTO> response = ResponseDTO.<MyshopDTO>builder().data(dtos).build();
        return ResponseEntity.ok().body(response);
    }
}
