package br.com.vamboraprogramar.liveunittest.validator;

import br.com.vamboraprogramar.liveunittest.entity.User;
import br.com.vamboraprogramar.liveunittest.exceptions.InvalidFieldException;
import br.com.vamboraprogramar.liveunittest.exceptions.RequiredFieldException;
import br.com.vamboraprogramar.liveunittest.exceptions.UserNullException;

public class UserValidator {

    public void validate(User user){
        validateObjectNotNull(user);
        validateRequiredFields(user);
        validateSize(user);
        validateFormat(user);
    }

    public void validateObjectNotNull(User user){
        if(user == null){
            throw new UserNullException("Usuário não pode ser nulo!");
        }
    }

    private void validateRequiredFields(User user){
        if(user.getGivenName() == null){
            throw new RequiredFieldException("Nome não pode ser nulo!");
        }

        if(user.getBirthDate() == null){
            throw new RequiredFieldException("Data de nascimento não pode ser nulo!");
        }
    }

    private void validateSize(User user){
        if(user.getGivenName().length() <= 0){
            throw new InvalidFieldException("O nome deve ter pelo menos 1 caracter");
        }

        if(user.getSurname().length() <= 0){
            throw new InvalidFieldException("O sobrenome deve ter pelo menos 1 caracter");
        }

        if(user.getGivenName().length() >= 30){
            throw new InvalidFieldException("O nome deve ter no máximo 30 caracteres");
        }

        if(user.getSurname().length() > 30){
            throw new InvalidFieldException("O sobrenome deve ter no máximo 30 caracteres");
        }

        if(user.getGender().length() > 1){  //ponto de erro
            throw new InvalidFieldException("O genero deve ter no máximo 1 caracteres");
        }
    }

    private void validateFormat(User user){
        if(!(user.getGivenName().charAt(0) >= 65
                && user.getGivenName().charAt(0) <= 90)){  //ponto de erro
            throw new InvalidFieldException("O nome deve ter iniciar com letra maiscula");
        }

        if(!user.getGender().equals("M")
                && !user.getGender().equals("F")){ //ponto de falha
            throw new InvalidFieldException("O genero deve ser M para masculino ou F para Feminino");
        }
    }
}
