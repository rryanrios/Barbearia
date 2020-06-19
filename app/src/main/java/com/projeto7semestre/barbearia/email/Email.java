package com.projeto7semestre.barbearia.email;

import java.io.Serializable;

public class Email {
    public static String email = "pessoa@teste.com.br";
    public static String Nome;
    public static int indiceEmail;

    public static boolean validarEmail() {
        //Verifica se o email possui o @.
        indiceEmail = email.indexOf('@');
        if (indiceEmail > 0)
            return (true);
        else
            return (false);
    }
}
