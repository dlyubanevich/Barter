package ru.dlyubanevich.deal.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import ru.dlyubanevich.deal.domain.Deal;
import ru.dlyubanevich.deal.model.DataDeal;
import ru.dlyubanevich.deal.model.DealNotification;

import java.time.LocalDateTime;

import static ru.dlyubanevich.deal.config.RabbitMQConfig.EXCHANGE_NAME;

@Service
@RequiredArgsConstructor
public class ProcessingServiceImpl implements ProcessingService {

    private final static String ROUTING_KEY_TO_NOTIFICATION_DEAL = "notification.deal";

    private final DealService dealService;
    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper objectMapper;

    @SneakyThrows
    @Override
    public String addDeal(DataDeal dataDeal) {

        int number = dealService.getNextDealNumber(dataDeal.getInitiator());

        Deal deal = new Deal();
        deal.setDateTime(LocalDateTime.now());
        deal.setInitiator(dataDeal.getInitiator());
        deal.setLocation(dataDeal.getLocation());
        deal.setPartner(dataDeal.getPartner());
        deal.setOfferRequest(dataDeal.getOfferRequest());
        deal.setOfferResponse(dataDeal.getOfferResponse());
        deal.setNumber(number);

        Deal savedDeal = dealService.addDeal(deal);
        DealNotification dealNotification = new DealNotification(savedDeal);

        rabbitTemplate.convertAndSend(
                EXCHANGE_NAME,
                ROUTING_KEY_TO_NOTIFICATION_DEAL,
                objectMapper.writeValueAsString(dealNotification)
        );

        return savedDeal.getId();
    }

    @Override
    public DataDeal getDeal(String id) {
        Deal deal = dealService.getDeal(id);
        return new DataDeal(
                deal.getLocation(),
                deal.getInitiator(),
                deal.getPartner(),
                deal.getOfferRequest(),
                deal.getOfferResponse()
        );
    }
}
