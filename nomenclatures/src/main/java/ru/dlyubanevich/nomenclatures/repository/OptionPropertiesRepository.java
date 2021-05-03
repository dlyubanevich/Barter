package ru.dlyubanevich.nomenclatures.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.dlyubanevich.nomenclatures.domain.OptionProperties;

public interface OptionPropertiesRepository extends MongoRepository<OptionProperties, String> {
}
