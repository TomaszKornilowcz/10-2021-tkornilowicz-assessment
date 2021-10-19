package com.tkornilowicz.assessment.position;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/position")
public class PositionController {
    @Autowired
    PositionService positionService;

    @GetMapping
    public ResponseEntity getAllPositions() {
        List<Position> positions = positionService.getAllPositions();

        return new ResponseEntity(positions, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity addNewPosition(@RequestBody Position positionToAdd) {
        try {
            Position newPosition = positionService.addNewPosition(positionToAdd);
            return new ResponseEntity(newPosition, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity("cannot create a position", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("{id}")
    public ResponseEntity updatePosition(@PathVariable Long id, @RequestBody Position positionToUpdate) {
        try {
            Position updatedPosition = positionService.updatePosition(id, positionToUpdate);
            return new ResponseEntity(updatedPosition, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity deletePosition(@PathVariable Long id) {
        try {
            Position deletedPosition = positionService.deletePosition(id);
            return new ResponseEntity(deletedPosition, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
