package dev.codebaker.omni_rental.services.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

import java.net.URI;

@Configuration
public class AwsS3Config {


    @Bean
    public Region  region() {
        return Region.EU_WEST_1;
    }

    @Bean
    public S3Client s3Client(Region region , @Value("${aws.s3.access-key-id}") String  accessKeyId,@Value("${aws.s3.access-key-secret}")  String accessKeySecret) {
        System.out.println("AWS S3 Access Key Id: " + accessKeyId);
        System.out.println("AWS S3 Access Key Secret: " + accessKeySecret);
        return  S3Client.builder()
                .endpointOverride(URI.create("https://s3.cubbit.eu"))
                .credentialsProvider(StaticCredentialsProvider.create(
                        AwsBasicCredentials.create(accessKeyId, accessKeySecret)
                ))
                .region(region)
                .build();
    }
}
