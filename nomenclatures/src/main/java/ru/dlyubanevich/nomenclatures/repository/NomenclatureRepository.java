package ru.dlyubanevich.nomenclatures.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.dlyubanevich.nomenclatures.domain.Nomenclature;

import java.util.List;

public interface NomenclatureRepository extends MongoRepository<Nomenclature, String> {

    List<Nomenclature> findAllByOwnerId(String id);
    List<Nomenclature> findAllByIdIn(List<String> ids);

}
