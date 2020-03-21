package de.wevsvirus.facades;

import com.github.javafaker.Faker;
import de.wevsvirus.data.ArztRepository;
import de.wevsvirus.model.ArztModel;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArztFacade {

    private Faker faker = new Faker();

    @Resource
    private ArztRepository arztRepository;

    public List<ArztModel> findArztByPLZ(final String plz) {
        String cleanPlz = StringUtils.trimAllWhitespace(plz);

        List<ArztModel> aerzte = arztRepository.findByPlz(cleanPlz);
        if (aerzte.size() > 0) {
            return aerzte.stream()
                    .limit(10L)
                    .collect(Collectors.toList());
        } else {
            aerzte = new ArrayList<>(10);

            for (int i = 0; i < 10; i++) {
                ArztModel arzt = new ArztModel();
                arzt.setPlz(cleanPlz);
                arzt.setPhoneNumber(faker.phoneNumber().phoneNumber());
                arzt.setFreeTimeSlots(faker.number().numberBetween(0, 10));
                arzt.setCoronaTester(faker.bool().bool());
                aerzte.add(arzt);
            }

            return aerzte;
        }
    }
}