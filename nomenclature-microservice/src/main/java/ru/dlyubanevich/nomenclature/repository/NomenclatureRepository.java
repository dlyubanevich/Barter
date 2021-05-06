package ru.dlyubanevich.nomenclature.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.dlyubanevich.nomenclature.domain.Nomenclature;

import java.util.List;

public interface NomenclatureRepository extends MongoRepository<Nomenclature, String> {

    List<Nomenclature> findAllByOwnerId(String id);
    List<Nomenclature> findAllByIdIn(List<String> ids);

}
