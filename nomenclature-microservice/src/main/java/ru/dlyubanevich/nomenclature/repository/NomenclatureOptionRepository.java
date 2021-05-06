package ru.dlyubanevich.nomenclature.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.dlyubanevich.nomenclature.domain.NomenclatureOption;

public interface NomenclatureOptionRepository extends MongoRepository<NomenclatureOption, String> {
}
