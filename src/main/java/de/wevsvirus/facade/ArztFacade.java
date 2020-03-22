package de.wevsvirus.facade;

import de.wevsvirus.model.ArztModel;

import java.util.List;

public interface ArztFacade {
    List<ArztModel> findArztByPLZ(String plz);

    List<ArztModel> findByPhoneNumber(String phoneNumber);
}
