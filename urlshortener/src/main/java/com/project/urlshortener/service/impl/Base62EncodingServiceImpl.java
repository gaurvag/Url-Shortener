package com.project.urlshortener.service.impl;

import com.project.urlshortener.exception.ValidationException;
import com.project.urlshortener.service.EncodingService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Objects;

import static com.project.urlshortener.util.Constant.BASE_62;

@Service
@Slf4j
public class Base62EncodingServiceImpl implements EncodingService {

    @Override
    public String encodeValue(Long value) {
        if (Objects.nonNull(value)) {
            StringBuilder encodedValue = new StringBuilder(StringUtils.EMPTY);
            do{
                encodedValue.append(encodeBase10ToCharInBase(value % BASE_62));
                if(value != 0L) {
                    value /= BASE_62;
                }
            }while(value > 0L);
            return String.valueOf(encodedValue.reverse());
        }
        else{
            log.error("Base62EncodingServiceImpl:encodeValue value is Null");
            throw new ValidationException("Unable to convert Null value in base" + BASE_62);
        }
    }

    @Override
    public char encodeBase10ToCharInBase(Long value) {
        if(Objects.nonNull(value)) {
            if (value >= 0 && value <= 25) {
                return (char) ('a' + value);
            } else if (value >= 26 && value <= 51) {
                return (char) ('A' + (value - 26));
            } else if (value >= 52 && value <= 61) {
                return (char) ('0' + (value - 52));
            }
            else{
                throw new ValidationException("Unable to convert value:" + value + " to char in base" + BASE_62);
            }
        }
        else{
            log.error("Base62EncodingServiceImpl:encodeBase10ToCharInBase value is Null");
            throw new ValidationException("Unable to convert Null value to char in base" + BASE_62);
        }
    }
}
