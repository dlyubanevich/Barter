package ru.dlyubanevich.nomenclatures.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.dlyubanevich.nomenclatures.domain.OptionProperty;

import java.util.List;

public interface OptionPropertyRepository extends MongoRepository<OptionProperty, String> {

    List<OptionProperty> findAllByOptionId(String optionId);

}
