package com.tkornilowicz.assessment.localization;

import com.tkornilowicz.assessment.exception.LocalizationNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LocalizationService {
    @Autowired
    private LocalizationRepository localizationRepository;

    public List<Localization> getAllLocalizations() {
        return localizationRepository.findAll();
    }

    public Localization addNewLocalization(Localization localizationToAdd) {
        return localizationRepository.save(localizationToAdd);
    }

    public Localization updateLocalization(Long id, Localization localizationToUpdate) {
        Optional<Localization> optionalLocalization = localizationRepository.findById(id);

        if (!optionalLocalization.isPresent()) {
            throw new LocalizationNotFoundException(id);
        }

        localizationRepository.updateLocalization(
                id,
                localizationToUpdate.getStreetName(),
                localizationToUpdate.getNumber(),
                localizationToUpdate.getZipCode(),
                localizationToUpdate.getCity()
        );

        return localizationToUpdate;
    }

    public Localization deleteLocalization(Long id) {
        Optional<Localization> optionalLocalization = localizationRepository.findById(id);

        if (!optionalLocalization.isPresent()) {
            throw new LocalizationNotFoundException(id);
        }

        Localization localizationToDelete = optionalLocalization.get();
        localizationRepository.delete(localizationToDelete);

        return localizationToDelete;
    }
}
