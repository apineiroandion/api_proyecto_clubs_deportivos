package com.dam_proyect.api_figth.controller;

import com.dam_proyect.api_figth.dto.RegisterRequestDto;
import com.dam_proyect.api_figth.dto.ResponseBaseDto;
import com.dam_proyect.api_figth.model.User;
import com.dam_proyect.api_figth.service.UserService;
import com.dam_proyect.api_figth.service.contract.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @PostMapping()
    public ResponseEntity<ResponseBaseDto<String>> register(@RequestBody RegisterRequestDto dto) {
        try{
            boolean isRegistered = userService.registerUser(dto);
            if (isRegistered) {
                ResponseBaseDto<String> response = new ResponseBaseDto<>(
                        "Registro exitoso", true, null);
                return new ResponseEntity<>(response, HttpStatus.CREATED);
            } else {
                ResponseBaseDto<String> response = new ResponseBaseDto<>(
                        "El usuario ya existe", false, null);
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            ResponseBaseDto<String> response = new ResponseBaseDto<>(
                    "Error al registrar el usuario: " + e.getMessage(), false, null);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/activate")
    public String activate(@RequestParam("token") String token) {
        return userService.activateAccount(token);
    }

    @GetMapping("{username}")
    public User getUser(@PathVariable String username) {
        return userService.getUserByUsername(username);
    }

}
