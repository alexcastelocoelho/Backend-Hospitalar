package com.example.api.utils;

public class MetodosUteis {

    //Verifica se cpf é valido
    public static boolean isCPF(String cpf) {

        if (cpf == null || cpf.length() != 11 || cpf.matches("^(\\d)\\1+$")) {
            return false;
        }

        try {
            char dig10, dig11;
            int sm, i, r, num, peso;


            sm = 0;
            peso = 10;
            for (i = 0; i < 9; i++) {
                num = Character.getNumericValue(cpf.charAt(i));
                sm += num * peso;
                peso--;
            }

            r = 11 - (sm % 11);
            dig10 = (r == 10 || r == 11) ? '0' : (char) (r + '0');


            sm = 0;
            peso = 11;
            for (i = 0; i < 10; i++) {
                num = Character.getNumericValue(cpf.charAt(i));
                sm += num * peso;
                peso--;
            }

            r = 11 - (sm % 11);
            dig11 = (r == 10 || r == 11) ? '0' : (char) (r + '0');


            return (dig10 == cpf.charAt(9)) && (dig11 == cpf.charAt(10));

        } catch (Exception e) {
            return false;
        }
    }

    //Formatação para cpf
    public static String imprimeCPF(String CPF) {
        return(CPF.substring(0, 3) + "." + CPF.substring(3, 6) + "." + CPF.substring(6, 9) + "-" + CPF.substring(9, 11));
    }



}
