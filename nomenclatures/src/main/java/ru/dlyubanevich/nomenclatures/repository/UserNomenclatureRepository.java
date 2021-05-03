package ru.dlyubanevich.nomenclatures.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.dlyubanevich.nomenclatures.domain.UserNomenclature;

public interface UserNomenclatureRepository extends MongoRepository<UserNomenclature, String> {
}
