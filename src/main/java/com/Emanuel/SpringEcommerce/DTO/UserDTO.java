package com.Emanuel.SpringEcommerce.DTO;


import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

//Classe que cria o DTO dos usuários
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    @NotBlank(message = "Nome é obrigatório")
    private String nome;
    @NotBlank(message = "CPF é obrigatório")
    private String cpf;
    @NotBlank(message = "O Endereço é obrigatório")
    private String endereco;
    private String email;
    private String telefone;
    private LocalDateTime dataCadastro;



}
