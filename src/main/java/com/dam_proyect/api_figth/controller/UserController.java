package com.dam_proyect.api_figth.controller;

import com.dam_proyect.api_figth.dto.*;
import com.dam_proyect.api_figth.model.User;
import com.dam_proyect.api_figth.service.contract.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/user")
public class UserController extends BaseController {

    private final UserService userService;

    public UserController(UserService userService) {
        super();
        this.userService = userService;
    }

    @PostMapping()
    public ResponseEntity<ResponseBaseDto<String>> register(@Valid @RequestBody RegisterRequestDto dto) {
        try {
            ResponseBaseDto<UserDto> response = userService.registerUser(dto);
            if (response.isSuccess()) {
                return new ResponseEntity<>(new ResponseBaseDto<>("Usuario registrado correctamente. Por favor, verifica tu correo electrónico.", true, null), HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>(new ResponseBaseDto<>(response.getMessage(), false, null), HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseBaseDto<>("Error al registrar el usuario: " + e.getMessage(), false, null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/activate")
    public ResponseEntity<ResponseBaseDto<String>> activateAccount(@RequestParam String token) {
        try {
            ResponseBaseDto<String> response = userService.activateAccount(token);
            if (response.isSuccess()) {
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseBaseDto<>("Error al activar la cuenta: " + e.getMessage(), false, null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<ResponseBaseDto<UserDto>> getUserById(@PathVariable String id) {
        try {
            ResponseBaseDto<UserDto> response = userService.getUserById(id);
            if (response.isSuccess()) {
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseBaseDto<>("Error al obtener el usuario: " + e.getMessage(), false, null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/name/{username}")
    public ResponseEntity<ResponseBaseDto<UserDto>> getUserByUsername(@PathVariable String username) {
        try {
            ResponseBaseDto<UserDto> response = userService.getUserByUsername(username);
            if (response.isSuccess()) {
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseBaseDto<>("Error al obtener el usuario: " + e.getMessage(), false, null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/reset-password")
    public ResponseEntity<ResponseBaseDto<Void>> resetPassword(@RequestParam String email, @RequestParam String newPassword) {
        try {
            ResponseBaseDto<Void> response = userService.resetPassword(email, newPassword);
            if (response.isSuccess()) {
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseBaseDto<>("Error al restablecer la contraseña: " + e.getMessage(), false, null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //TODO: Dar una vuelta a la logica para la nueva contraseña, ahora no tiene sentido ya que la contraseña nueva se pasa como body
//    @GetMapping("new-password")


    @PatchMapping("{id}/password")
    public ResponseEntity<ResponseBaseDto<Void>> changePassword(@PathVariable String id, @Valid @RequestBody UserChagePasswordRequestDto dto) {
        try {
            ResponseBaseDto<Void> response = userService.changePassword(id, dto);
            if (response.isSuccess()) {
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseBaseDto<>("Error al cambiar la contraseña: " + e.getMessage(), false, null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/upload-image")
    public ResponseEntity<ResponseBaseDto<String>> uploadUserImage(@RequestParam("image") MultipartFile image) {
        try {
            ResponseBaseDto<String> response = userService.uploadUserImage(image);
            if (response.isSuccess()) {
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }
        } catch (IOException e) {
            return new ResponseEntity<>(new ResponseBaseDto<>("Error al subir la imagen: " + e.getMessage(), false, null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/me")
    public ResponseEntity<ResponseBaseDto<UserDto>> getCurrentUser() {
        try {
            String userId = getUsuarioIdFromAuth();

            if (userId == null) {
                return ResponseEntity
                        .status(HttpStatus.UNAUTHORIZED)
                        .body(new ResponseBaseDto<>("Usuario no autenticado", false, null));
            }

            return ResponseEntity.ok(userService.getUserById(userId));

        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseBaseDto<>("Error al obtener el usuario actual: " + e.getMessage(), false, null));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseBaseDto<UserDto>> updateUser(@PathVariable String id, @Valid @RequestBody UserUpdateRequestDto dto) {
        try {
            ResponseBaseDto<UserDto> response = userService.updateUser(id, dto);
            if (response.isSuccess()) {
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseBaseDto<>("Error al actualizar el usuario: " + e.getMessage(), false, null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseBaseDto<Void>> deleteUser(@PathVariable String id) {
        try {
            ResponseBaseDto<Void> response = userService.deleteUser(id);
            if (response.isSuccess()) {
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseBaseDto<>("Error al eliminar el usuario: " + e.getMessage(), false, null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
