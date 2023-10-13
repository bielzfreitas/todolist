package br.com.skyline.todolist.utils;

import java.beans.PropertyDescriptor;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

public class Utils {
    
    //copiar tudo o que NÃO for nulo
    public static void copyNonNullProperties(Object source, Object target) {
        BeanUtils.copyProperties(source, target, getNullPropertyNames(source));
    }

    //a ideia é poder reutilizar para outros lugares que queria fazer update parcial
    public static String[] getNullPropertyNames(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);

        PropertyDescriptor[] pds = src.getPropertyDescriptors();

        //propriedades de valores nulos
        Set<String> emptyNames = new HashSet<>();

        //interação dos pds
        for(PropertyDescriptor pd: pds) {
            //valor da propriedade atual
            Object srcValue = src.getPropertyValue(pd.getName());
            //colocando todas as propriedades nulas dentro do "emptyNames"
            if(srcValue == null) {
                emptyNames.add(pd.getName());
            }
        }

        //criando array de strings para guardas todas as propriedades
        //convertendo o conjunto de nomes das propriedades em array de strings
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }
}


/*
 * Validação de nulo ou não nulo
 * - função/método que verifica todos os atributos dentro de um obj
 * - faz a validação dos que são nulos e os que não são nulos
 * - faz uma mescla desses atributos
 * - faz uma busca na task (banco de dados)
 * - baseado nesses obj, ele faz uma mescla os obj que está recebendo da requisição
 * 
 * "getNullPropertyNames" serve para fazer um copy (copia)
 * - do nosso obj do repositorio pro obj do body
 * 
 * Usando o "Static", não precisa instanciar a classe
 * 
 * 
 */