package ru.dlyubanevich.bottelegrammicroservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.dlyubanevich.bottelegrammicroservice.model.NomenclatureOptionModel;

import java.util.Map;

@Service
@RequiredArgsConstructor

public class NomenclatureServiceImpl implements NomenclatureService {

    @Override
    public Map<String, NomenclatureOptionModel> getNomenclatureOptions() {
        return null;
    }
}
