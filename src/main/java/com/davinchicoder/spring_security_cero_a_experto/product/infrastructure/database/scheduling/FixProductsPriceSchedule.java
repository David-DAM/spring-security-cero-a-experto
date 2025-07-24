package com.davinchicoder.spring_security_cero_a_experto.product.infrastructure.database.scheduling;

import com.davinchicoder.spring_security_cero_a_experto.product.domain.port.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class FixProductsPriceSchedule {

    private final ProductRepository productRepository;

//    @Scheduled(fixedRate = 60000)
//    public void fixProductsPrice() {
//        log.info("Fixing products price");
//
//        productRepository.findAll().forEach(product -> {
//            product.setPrice(product.getPrice() * 1.1);
//            productRepository.upsert(product);
//        });
//
//        log.info("Finished fixing products price");
//    }

}
