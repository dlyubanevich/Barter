package ru.dlyubanevich.nomenclatures.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.dlyubanevich.nomenclatures.domain.UserNomenclature;

import java.util.List;

public interface UserNomenclatureRepository extends MongoRepository<UserNomenclature, String> {

    List<UserNomenclature> findAllByUserId(String userId);

}
