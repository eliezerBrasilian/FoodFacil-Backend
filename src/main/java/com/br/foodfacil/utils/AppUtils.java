package com.br.foodfacil.utils;

import com.br.foodfacil.enums.Item;
import com.br.foodfacil.enums.MensagemRetorno;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Map;

@Component
public class AppUtils {
    @Value("${prod.access.token}")
    public static String PROD_ACCESS_TOKEN;

    @Value("${test.access.token}")
    public static  String TEST_ACCESS_TOKEN;

    public static final String baseUrl = "/food-facil/api/v1";

    public ResponseEntity<Object> AppCustomJson(MensagemRetorno mensagemRetorno, Item item){

        return switch (mensagemRetorno) {
            case ITEM_NAO_EXISTE ->
                    ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message", item.toString() + " nao existe"));
            case EXCLUIDO_COM_SUCESSO ->
                    ResponseEntity.ok().body(Map.of("message", item.toString() + " excluido com sucesso"));
            case EDITADO_COM_SUCESSO ->
                    ResponseEntity.ok().body(Map.of("message", item.toString() + " atualizado com sucesso no banco de dados"));
            default -> ResponseEntity.ok().body(Map.of("message", "sucesso ao adicionar"));
        };
    }

    public static String CustomMensagemExcessao(MensagemRetorno mensagemRetorno, String mensagem){
        return switch (mensagemRetorno){
            case FALHA_AO_ADICIONAR ->
                "falha ao adicionar devido a uma excessao" + mensagem;

            case FALHA_AO_EDITAR->
                "falha ao deletar devido a uma excessao" + mensagem;

            case FALHA_AO_DELETAR->
               "falha ao deletar devido a uma excessao" + mensagem;

            default -> "";
        };
    }


    public static boolean verificaExpiracao(Date expirationDate){
        // Obtém a data e hora atuais
        LocalDateTime now = LocalDateTime.now();

        // Converte a data de expiração do cupom (java.util.Date) para uma String no formato ISO 8601
        String expirationDateString = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").format(expirationDate);

        // Formata a data de expiração do cupom para LocalDateTime
        LocalDateTime expirationDateTime = LocalDateTime.parse(expirationDateString, DateTimeFormatter.ISO_DATE_TIME);

        // Verifica se a data de expiração é anterior à data atual
        boolean expired = expirationDateTime.isBefore(now);

       return expired;
    }

    public static String obtemPrimeiroNome(String nomeCompleto){
        // Dividir a string pelo espaço em branco
        String[] partes = nomeCompleto.split(" ");

        return  partes[0];
    }
}
