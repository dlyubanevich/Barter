package ru.dlyubanevich.nomenclature.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.dlyubanevich.nomenclature.domain.UserNomenclature;

import java.util.List;

public interface UserNomenclatureRepository extends MongoRepository<UserNomenclature, String> {

    List<UserNomenclature> findAllByUserId(String userId);

}
