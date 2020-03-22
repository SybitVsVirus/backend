package de.wevsvirus.facade.impl;

import com.amazonaws.services.polly.AmazonPolly;
import com.amazonaws.services.polly.model.*;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import de.wevsvirus.facade.RKIRiskFacade;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.stream.Collectors;

@Service
public class DefaultRKIRiskFacade implements RKIRiskFacade {
    @Resource
    private AmazonPolly polly;

    @Resource
    private AmazonS3 amazonS3;

    @Value("${RISK_URL:https://www.rki.de/DE/Content/InfAZ/N/Neuartiges_Coronavirus/Risikogebiete.html}")
    private String riskUrl;

    @Value("${CONNECT_BUCKET:connect-e6ee651fbd79}")
    private String voiceBucketName;

    @Value("${CONNECT_BUCKET_RISKAREAS_KEY:riskareas.mp3}")
    private String voiceBucketKey;

    @Override
    public String updateRiskAudio() throws IOException {
        //get current riskareas
        final Document riskData = Jsoup.parse(new URL(riskUrl), 10000);
        Elements infoboxes = riskData.select("#main > div.text > div.infobox.breit");
        String text = infoboxes.stream()
                .map(element -> {
                    final String heading = element.select("h2").stream()
                            .map(Element::text)
                            .collect(Collectors.joining(" "));
                    final String laender = element.selectFirst("p").text();
                    return String.format("Aktuelle %s sind %s.", heading, laender);
                })
                .collect(Collectors.joining(" ,,, "));

        //TODO: do nothing if unchanged
        //request the tts
        SynthesizeSpeechRequest ssr = new SynthesizeSpeechRequest();
        ssr.setVoiceId(VoiceId.Marlene);
        ssr.setLanguageCode(LanguageCode.DeDE);
        ssr.setOutputFormat(OutputFormat.Mp3);
        ssr.setText(text);
        SynthesizeSpeechResult speechResult = polly.synthesizeSpeech(ssr);
        InputStream audioStream = speechResult.getAudioStream();
        //save to s3
        String bucketUrl = "nope";
        Bucket voiceBucket = amazonS3.listBuckets()
                .stream()
                .filter(bucket -> voiceBucketName.equals(bucket.getName()))
                .findFirst()
                .orElseGet(() -> amazonS3.createBucket(voiceBucketName));

        ObjectMetadata metadata = new ObjectMetadata();
        PutObjectRequest putObjectRequest = new PutObjectRequest(voiceBucketName, voiceBucketKey, audioStream, metadata);
        amazonS3.putObject(putObjectRequest);

        return bucketUrl;
    }
}
