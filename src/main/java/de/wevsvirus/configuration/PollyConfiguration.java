package de.wevsvirus.configuration;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.polly.AmazonPolly;
import com.amazonaws.services.polly.AmazonPollyClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PollyConfiguration {
    @Bean
    public AmazonPolly polly(final AWSCredentialsProvider amazonAWSCredentialsProvider) {
        AmazonPollyClientBuilder polly = AmazonPollyClientBuilder.standard();
        polly.setCredentials(amazonAWSCredentialsProvider);
        polly.withRegion(Regions.EU_CENTRAL_1);
        return polly.build();
    }
}
