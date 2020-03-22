package de.wevsvirus.facade.impl;

import de.wevsvirus.data.ArztRepository;
import de.wevsvirus.facade.ArztFacade;
import de.wevsvirus.model.ArztModel;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

@Service
public class DefaultArztFacade implements ArztFacade {

    @Resource
    private ArztRepository arztRepository;

    @Override
    public List<ArztModel> findArztByPLZ(final String plz) {
        String cleanPlz = StringUtils.trimAllWhitespace(plz);
        return   arztRepository.findByPlz(cleanPlz);
    }

    @Override
    public List<ArztModel> findByPhoneNumber(final String phoneNumber) {
        String cleanPhoneNumber = StringUtils.trimAllWhitespace(phoneNumber);
        return arztRepository.findByPhoneNumber(cleanPhoneNumber);

    }
}
