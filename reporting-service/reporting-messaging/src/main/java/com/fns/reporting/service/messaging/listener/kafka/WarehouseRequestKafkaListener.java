package com.fns.reporting.service.messaging.listener.kafka;


import com.fns.kafka.consumer.KafkaConsumer;
import com.fns.kafka.warehouse.avro.model.WarehouseReportingRequestAvroModel;
import com.fns.reporting.service.domain.port.input.message.listener.warehouse.WarehouseRequestMessageListener;
import lombok.extern.slf4j.Slf4j;
//import com.fns.kafka.warehouse.avro.model.PaymentStatus;
import com.fns.reporting.service.messaging.mapper.ReportMessagingDataMapper;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class WarehouseRequestKafkaListener implements KafkaConsumer<WarehouseReportingRequestAvroModel> {

    private final WarehouseRequestMessageListener warehouseRequestMessageListener;
    private final ReportMessagingDataMapper reportMessagingDataMapper;

    public WarehouseRequestKafkaListener(WarehouseRequestMessageListener warehouseRequestMessageListener,
                                        ReportMessagingDataMapper reportMessagingDataMapper) {
        this.warehouseRequestMessageListener = warehouseRequestMessageListener;
        this.reportMessagingDataMapper = reportMessagingDataMapper;
    }

    @Override
    @KafkaListener(id = "${kafka-consumer-config.payment-consumer-group-id}", topics = "${order-service.payment-response-topic-name}")
    public void receive(@Payload List<WarehouseReportingRequestAvroModel> messages,
                        @Header("KafkaHeaders.RECEIVED_MESSAGE_KEY") List<String> keys,
                        @Header("KafkaHeaders.RECEIVED_PARTITION_ID") List<Integer> partitions,
                        @Header(KafkaHeaders.OFFSET) List<Long> offsets) {
        log.info("{} number of payment responses received with keys:{}, partitions:{} and offsets: {}",
                messages.size(),
                keys.toString(),
                partitions.toString(),
                offsets.toString());

        messages.forEach(warehouseReportingRequestAvroModel -> {
            log.info("Processing successful warehouse reporting for warehouse id: {}", warehouseReportingRequestAvroModel.getWarehouseId());
                warehouseRequestMessageListener.warehouseRequestCompleted(reportMessagingDataMapper
                        .warehouseReportingAvroModelToWarehouseRequest(warehouseReportingRequestAvroModel));

//            log.info("Processing unsuccessful payment for order id: {}", warehouseReportingRequestAvroModel.getWarehouseId());
//            warehouseRequestMessageListener.warehouseRequestCanceled(reportMessagingDataMapper
//                    .warehouseReportingAvroModelToWarehouseRequest(warehouseReportingRequestAvroModel));
        });
    }

}
