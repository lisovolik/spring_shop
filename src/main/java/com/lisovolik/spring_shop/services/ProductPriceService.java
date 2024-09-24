package com.lisovolik.spring_shop.services;

import com.lisovolik.spring_shop.entity.ProductPrice;
import com.lisovolik.spring_shop.mapper.ProductPriceMapper;
import com.lisovolik.spring_shop.models.LimitOffsetPageRequest;
import com.lisovolik.spring_shop.models.ServerResponseForList;
import com.lisovolik.spring_shop.models.dto.product_price.AddPriceDto;
import com.lisovolik.spring_shop.models.dto.product_price.PriceDto;
import com.lisovolik.spring_shop.repositories.PriceRepository;
import com.lisovolik.spring_shop.utils.Utils;
import com.lisovolik.spring_shop.validators.PriceValidator;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Created by Alexandr Lisovolik on  24.09.2024
 */

@Service
@AllArgsConstructor
public class ProductPriceService {
    private final PriceRepository priceRepository;
    private final ProductPriceMapper priceMapper;
    private final ProductService productService;


    public ResponseEntity<ServerResponseForList<PriceDto>> getAllPrices(int limit, int offset){
        Integer totalCount = (int)priceRepository.getCount();
        Pageable pageable = new LimitOffsetPageRequest(offset, limit, Sort.by(Sort.Order.desc("p.createdOn")));
        List<PriceDto> prices = priceMapper.toPriceResponseList(priceRepository.findAllPrices(pageable));
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ServerResponseForList<>(
                        totalCount,
                        limit,
                        offset,
                        prices
                ));
    }

    public ResponseEntity<PriceDto> addProductPrice(Long productId, AddPriceDto priceDto){
        PriceValidator.validate(priceDto);
        productService.findProductById(productId);
        PriceDto response = null;

        Optional<ProductPrice> optional = priceRepository.findLastByPriceId(productId);
        boolean needSave = true;

        if (optional.isPresent()){
            Double lastPrice = optional.get().getPrice();
            if (Math.abs(lastPrice - priceDto.getPrice()) < 0.1){
               needSave = false;
            }  else {
                response = priceMapper.toPriceResponse(optional.get());
            }
        }

        if (needSave){
            ProductPrice price = new ProductPrice();
            price.setPrice(priceDto.getPrice());
            price.setProductId(productId);
            price.setCreatedOn(Utils.getLocalDateTime());

            response = priceMapper.toPriceResponse(priceRepository.save(price));
        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }
}
