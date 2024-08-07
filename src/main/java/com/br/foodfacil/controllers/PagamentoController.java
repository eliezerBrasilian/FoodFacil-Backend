package com.br.foodfacil.controllers;

import com.br.foodfacil.dtos.MercadoPagoNotificacaoRequestDto;
import com.br.foodfacil.dtos.NotificationDTO;
import com.br.foodfacil.records.PagamentoBody;
import com.br.foodfacil.dtos.PaymentReceiverDto;
import com.br.foodfacil.records.QrCode;
import com.br.foodfacil.services.NotificationService;
import com.br.foodfacil.services.impl.PixPaymentGatewayImpl;
import com.br.foodfacil.utils.AppUtils;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;


@CrossOrigin(origins = {"http://localhost:5173",
        "https://food-facil-painel-admin-8mcqkbvbs-eliezerbrasilians-projects.vercel.app",
        "https://food-facil-painel-admin.vercel.app","https://foodfacil-website.vercel.app"})
@RestController
@RequestMapping(AppUtils.baseUrl + "/pagamento")

public class PagamentoController {
    @Autowired
    NotificationService notificationService;

    @Autowired
    PixPaymentGatewayImpl pagamentoServiceImpl;

    @PostMapping()
    public String makePayment(@RequestBody PaymentReceiverDto paymentReceiverDto) {
        //processar, o pagamento
        //lancei o status como aprovado ou rejeitado no banco de dados
        //envio a notificação com base no resultado
        
        String imagePath = "https://firebasestorage.googleapis.com/v0/b/foodfacil-d0c86.appspot.com/o/app_resources%2Flogo.png?alt=media&token=9ed10677-f17b-4d0c-9159-9e770ad65875";

        notificationService.sendNotificationByToken(
                new NotificationDTO(paymentReceiverDto.deviceToken(),
                        "Pagamento aprovado", "Parabéns," + paymentReceiverDto.userName() + " identificamos que " +
                        "seu pagamento ocorreu com " +
                        "sucesso, " +
                        "seu pedido seguirá em preparo",
                        imagePath,
                        new HashMap<>(){{put("payment_status","approved");}}
                )
                );
        return "Pagamento aprovado";
    }

    @PostMapping("pix")
    ResponseEntity<QrCode> pagamentoPix(@RequestBody PagamentoBody pagamentoBody) throws MPException, MPApiException {
        System.out.println(pagamentoBody);

        //return ResponseEntity.ok().body(pagamentoBody);
        return  pagamentoServiceImpl.generatePixKey(pagamentoBody);
    }

    @PostMapping("mercadopago/notificacao")
    ResponseEntity<Object> notificacao(@RequestBody MercadoPagoNotificacaoRequestDto mercadoPagoNotificacaoRequestDto) throws MPException, MPApiException {

        System.out.println("recebido");
        System.out.println(mercadoPagoNotificacaoRequestDto);

        return pagamentoServiceImpl.checkPaymentStatus(mercadoPagoNotificacaoRequestDto);
    }
}
