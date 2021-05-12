package ru.dlyubanevich.offerprocessingservice.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import ru.dlyubanevich.offerprocessingservice.domain.offer.OfferRequest;
import ru.dlyubanevich.offerprocessingservice.domain.offer.OfferResponse;
import ru.dlyubanevich.offerprocessingservice.dto.DataRequest;
import ru.dlyubanevich.offerprocessingservice.dto.DataResponse;

import static ru.dlyubanevich.offerprocessingservice.config.RabbitMQConfig.EXCHANGE_NAME;

@Service
@RequiredArgsConstructor
public class ProcessingServiceImpl implements ProcessingService {

    private final static String ROUTING_KEY_TO_NOTIFICATION_REQUEST = "notification.offer.request";
    private final static String ROUTING_KEY_TO_NOTIFICATION_RESPONSE = "notification.offer.response";

    private final NomenclatureService nomenclatureService;
    private final OfferService offerService;
    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper objectMapper;

    @SneakyThrows
    @Override
    public OfferRequest processingRequest(DataRequest dataRequest) {

        var request = dataRequest.transformToOfferRequest();
        var items = nomenclatureService.addItems(request.getItems());
        request.setItems(items);

        var offer = offerService.addOfferRequest(request);
        rabbitTemplate.convertAndSend(
                EXCHANGE_NAME,
                ROUTING_KEY_TO_NOTIFICATION_REQUEST,
                objectMapper.writeValueAsString(offer)
        );

        return offer;

    }

    @SneakyThrows
    @Override
    public OfferResponse processingResponse(DataResponse dataResponse) {

        var response = dataResponse.transformToOfferResponse();
        var items = nomenclatureService.addItems(response.getItems());
        response.setItems(items);

        var offer = offerService.addOfferResponse(response);
        rabbitTemplate.convertAndSend(
                EXCHANGE_NAME,
                ROUTING_KEY_TO_NOTIFICATION_RESPONSE,
                objectMapper.writeValueAsString(offer)
        );

        return offer;

    }

}
