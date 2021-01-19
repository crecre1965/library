package fr.training.spring.library.exposition.director;

import fr.training.spring.library.domain.library.Director;
import fr.training.spring.library.exposition.common.AbstractAdapter;
import org.springframework.stereotype.Component;

@Component
public class DirectorAdapter extends AbstractAdapter<DirectorDto, Director> {
    @Override
    public DirectorDto mapToDto(Director entity) {
        final DirectorDto directorDto = new DirectorDto();
        directorDto.setFirstname(entity.getFirstname());
        directorDto.setLastname(entity.getLastname());
        return directorDto;
    }

    @Override
    public Director mapToEntity(DirectorDto dto) {
        final Director director = new Director();
        director.setFirstname(dto.getFirstname());
        director.setLastname(dto.getLastname());
        return director;
    }
}
