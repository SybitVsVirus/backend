package de.wevsvirus.facade.impl;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.S3Object;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import de.wevsvirus.facade.CallStatisticFacade;
import de.wevsvirus.servlet.controller.data.CallStatisticDTO;
import de.wevsvirus.servlet.controller.data.CallStatisticUpdateDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.io.IOException;

@Component
public class DefaultCallStatisticFacade implements CallStatisticFacade {
    private static final Logger log = LoggerFactory.getLogger(DefaultCallStatisticFacade.class);

    private boolean loaded = false;

    private int numberOfCallers = 0;
    private int numberOfCoronaCases = 0;
    private int numberOfCallCenterRedirections = 0;

    @Resource
    private AmazonS3 amazonS3;

    @Value("${STATS_BUCKET:connect-e6ee651fbd79}")
    private String statsBucketName;

    @Value("${STATS_BUCKET_CURRENT_KEY:current.json}")
    private String statsBucketKey;

    @Override
    public void updateCallStatistic(final CallStatisticUpdateDTO callStatisticUpdateDTO) {
        boolean isCallOnly = true;
        if (callStatisticUpdateDTO.isCoronaCase()) {
            numberOfCoronaCases += 1;
            isCallOnly = false;
        }
        if (callStatisticUpdateDTO.isCallCenterRedirection()) {
            numberOfCallCenterRedirections += 1;
            isCallOnly = false;
        }
        if (isCallOnly) {
            numberOfCallers += 1;
        }
    }

    @Override
    public CallStatisticDTO getCallStatistics() {
        final CallStatisticDTO callStatisticDTO = new CallStatisticDTO();
        callStatisticDTO.setNumberOfCallers(numberOfCallers);
        callStatisticDTO.setNumberOfCoronaCases(numberOfCoronaCases);
        callStatisticDTO.setNumberOfCallCenterRedirections(numberOfCallCenterRedirections);
        return callStatisticDTO;
    }

    @Override
    public void saveStatistics() throws JsonProcessingException {
        if (!loaded) return;
        CallStatisticDTO data = getCallStatistics();
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        String result = mapper.writeValueAsString(data);
        amazonS3.putObject(statsBucketName, statsBucketKey, result);
    }


    @PostConstruct
    public void loadStatistics() throws IOException {
        S3Object object = amazonS3.getObject(statsBucketName, statsBucketKey);
        ObjectMapper mapper = new ObjectMapper();
        CallStatisticDTO stats = mapper.readValue(object.getObjectContent(), CallStatisticDTO.class);
        numberOfCallers += stats.getNumberOfCallers();
        numberOfCallCenterRedirections += stats.getNumberOfCallCenterRedirections();
        numberOfCoronaCases += stats.getNumberOfCoronaCases();
        loaded = true;
        log.info(String.format("Loaded old statistics: %d %d %d", numberOfCallers, numberOfCallCenterRedirections, numberOfCoronaCases));
    }


}
