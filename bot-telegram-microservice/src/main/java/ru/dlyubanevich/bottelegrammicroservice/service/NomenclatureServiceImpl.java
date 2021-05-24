package ru.dlyubanevich.bottelegrammicroservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.dlyubanevich.bottelegrammicroservice.model.NomenclatureOptionModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class NomenclatureServiceImpl implements NomenclatureService {

    private final Map<String, NomenclatureOptionModel> options;
    private final List<String> types;

    public NomenclatureServiceImpl(){
        options = new HashMap<>();
        options.put("Овощи", new NomenclatureOptionModel("1","Овощи"));
        options.put("Фрукты", new NomenclatureOptionModel("2","Фрукты"));
        types = new ArrayList<>();
        types.add("Товар");
        types.add("Услуга");
    }

    @Override
    public Map<String, NomenclatureOptionModel> getNomenclatureOptions() {
        return options;
    }

    @Override
    public List<String> getNomenclatureTypes() {
        return types;
    }
}
