package com.tkornilowicz.assessment.position;

import com.tkornilowicz.assessment.exception.PositionNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PositionService {
    @Autowired
    private PositionRepository positionRepository;

    public List<Position> getAllPositions() {
        return positionRepository.findAll();
    }

    public Position addNewPosition(Position positionToAdd) {
        return positionRepository.save(positionToAdd);
    }

    public Position updatePosition(Long id, Position positionToUpdate) {
        Optional<Position> optionalPosition = positionRepository.findById(id);

        if (!optionalPosition.isPresent()) {
            throw new PositionNotFoundException(id);
        }

        positionRepository.updatePosition(id, positionToUpdate.getName());

        return positionToUpdate;
    }

    public Position deletePosition(Long id) {
        Optional<Position> optionalPosition = positionRepository.findById(id);

        if (!optionalPosition.isPresent()) {
            throw new PositionNotFoundException(id);
        }

        Position positionToDelete = optionalPosition.get();
        positionRepository.delete(positionToDelete);

        return positionToDelete;
    }
}
