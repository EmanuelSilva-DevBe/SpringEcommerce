package com.Emanuel.SpringEcommerce.Controller;

import com.Emanuel.SpringEcommerce.DTO.UserDTO;
import jakarta.annotation.PostConstruct;
import jakarta.validation.Valid;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    public static List<UserDTO> users = new ArrayList<UserDTO>();

    @GetMapping
    public List<UserDTO> getUsers(){
        return users;
    }

    @GetMapping("/{cpf}")
    public UserDTO getUserFilters(@PathVariable String cpf) {
        return users
                .stream()
                .filter(userDTO -> userDTO.getCpf().equals(cpf)) //Filtra o usuário com base no cpf dado na URL do site
                .findFirst()
                .orElseThrow(() -> new RuntimeException("User not found")); //Retorna uma RuntimeException caso o usuário não seja encontrado
    }

    @PostMapping("/insert")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDTO insert(@RequestBody @Valid UserDTO userDTO){
        userDTO.setDataCadastro(LocalDateTime.now());
        users.add(userDTO);
        return userDTO;
    }

    @DeleteMapping("/{cpf}")
    public boolean remove(@PathVariable String cpf){
        return users.removeIf(userDTO -> userDTO.getCpf().equals(cpf));
    }

}
