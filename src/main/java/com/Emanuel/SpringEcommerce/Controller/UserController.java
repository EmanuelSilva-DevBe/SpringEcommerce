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

    //Controller Post que faz a incialização de três usuários.
    @PostConstruct
    public void IntiateList(){
        UserDTO userDTO = new UserDTO();

        userDTO.setNome("Emanuel");
        userDTO.setCpf("08912546333");
        userDTO.setTelefone("85998481269");
        userDTO.setEmail("Emanuel43333@gmail.com");
        userDTO.setEndereco("Rua doutor otávio lobo");
        userDTO.setDataCadastro(LocalDateTime.now());

        UserDTO userDTO2 = new UserDTO();

        userDTO2.setNome("Kaylane Eduarda");
        userDTO2.setCpf("123456789101");
        userDTO2.setTelefone("85997601227");
        userDTO2.setEmail("kaylane04@gmail.com");
        userDTO2.setEndereco("Rua itapoã");
        userDTO2.setDataCadastro(LocalDateTime.now());

        users.add(userDTO);
        users.add(userDTO2);
    }

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
