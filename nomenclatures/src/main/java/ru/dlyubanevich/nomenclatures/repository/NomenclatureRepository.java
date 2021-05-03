package ru.dlyubanevich.nomenclatures.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.dlyubanevich.nomenclatures.domain.Nomenclature;

public interface NomenclatureRepository extends MongoRepository<Nomenclature, String> {
}
