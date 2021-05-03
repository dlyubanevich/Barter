package ru.dlyubanevich.nomenclatures.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.dlyubanevich.nomenclatures.domain.NomenclatureOption;

public interface NomenclatureOptionRepository extends MongoRepository<NomenclatureOption, String> {
}
