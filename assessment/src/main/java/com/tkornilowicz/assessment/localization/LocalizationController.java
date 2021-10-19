package com.tkornilowicz.assessment.localization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/localization")
public class LocalizationController {
    @Autowired
    private LocalizationService localizationService;

    @GetMapping
    public ResponseEntity getAllLocalization() {
        List<Localization> localizations = localizationService.getAllLocalizations();

        return new ResponseEntity(localizations, HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity addNewLocalization(@RequestBody Localization localizationToAdd) {
        try {
            Localization newLocalization = localizationService.addNewLocalization(localizationToAdd);
            return new ResponseEntity(newLocalization, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity("cannot create a localization", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("{id}")
    public ResponseEntity updateLocalization(@PathVariable Long id, @RequestBody Localization localizationToUpdate) {
        try {
            Localization updatedLocalization = localizationService.updateLocalization(id, localizationToUpdate);
            return new ResponseEntity(updatedLocalization, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteLocalization(@PathVariable Long id) {
        try {
            Localization deletedLocalization = localizationService.deleteLocalization(id);
            return new ResponseEntity(deletedLocalization, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
