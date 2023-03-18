package br.com.vamboraprogramar.liveunittest.validator;

import br.com.vamboraprogramar.liveunittest.entity.User;
import br.com.vamboraprogramar.liveunittest.exceptions.InvalidFieldException;
import br.com.vamboraprogramar.liveunittest.exceptions.RequiredFieldException;
import br.com.vamboraprogramar.liveunittest.exceptions.UserNullException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class UserValidatorTest {

    private UserValidator userValidator;

    @BeforeEach
    public void setup(){
        userValidator =  new UserValidator();
    }

    @Test
    public void deveLancarExcecaoQuandoUsuarioForNulo(){
        Assertions.assertThrows(UserNullException.class,
                ()-> userValidator.validate(null));
    }

    @Test
    public void deveLancarExcecaoQuandoUsuarioContiverNomeNulo(){
        User user = User.builder().build();

        RequiredFieldException thrown = Assertions.assertThrows(RequiredFieldException.class,
                ()-> userValidator.validate(user));

        Assertions.assertEquals("Nome não pode ser nulo!", thrown.getMessage());
    }

    @Test
    public void deveLancarExcecaoQuandoUsuarioContiverNascimentoNulo(){
        User user = User.builder()
                .givenName("Jose")
                .build();

        RequiredFieldException thrown = Assertions.assertThrows(RequiredFieldException.class,
                ()-> userValidator.validate(user));

        Assertions.assertEquals("Data de nascimento não pode ser nulo!", thrown.getMessage());
    }

    @Test
    public void deveLancarExecaoQuandoNomeContiverMaisQue30Letras(){
        User user = User.builder()
                .givenName("0123456789012345678901234567890123456789")
                .surname("Ferreira")
                .birthDate(LocalDate.of(1982, 5, 21))
                .build();

        InvalidFieldException thrown = Assertions.assertThrows(InvalidFieldException.class,
                ()-> userValidator.validate(user));

        Assertions.assertEquals("O nome deve ter no máximo 30 caracteres", thrown.getMessage());
    }

    @Test
    public void deveLancarExecaoQuandoNomeForBranco(){
        User user = User.builder()
                .givenName("")
                .surname("")
                .birthDate(LocalDate.of(1982, 5, 21))
                .gender("M")
                .build();

        InvalidFieldException thrown = Assertions.assertThrows(InvalidFieldException.class,
                ()-> userValidator.validate(user));

        Assertions.assertEquals("O nome deve ter pelo menos 1 caracter", thrown.getMessage());

    }

    @Test
    public void naoDeveLancarExcecaoQuandoUsuarioOK(){
        User user = User.builder()
                .givenName("Endrigo")
                .surname("Ferreira")
                .birthDate(LocalDate.of(1982, 5, 21))
                .gender("M")
                .build();

        Assertions.assertDoesNotThrow(()-> userValidator.validate(user));
    }
}
