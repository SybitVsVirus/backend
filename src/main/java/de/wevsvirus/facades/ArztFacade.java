package de.wevsvirus.facades;

import de.wevsvirus.data.ArztRepository;
import de.wevsvirus.model.ArztModel;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArztFacade {

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
            return Collections.emptyList();
        }
    }
}
