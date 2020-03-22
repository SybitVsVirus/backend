package de.wevsvirus.data;

import de.wevsvirus.model.ArztModel;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;


@EnableScan
public interface ArztRepository extends CrudRepository<ArztModel, String> {
    Optional<ArztModel> findByUid(String uid);

    List<ArztModel> findByPlz(String plz);
}
