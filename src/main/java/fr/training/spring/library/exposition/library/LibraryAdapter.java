package fr.training.spring.library.exposition.library;

import fr.training.spring.library.domain.book.Book;
import fr.training.spring.library.domain.library.Address;
import fr.training.spring.library.domain.library.Director;
import fr.training.spring.library.domain.library.Library;
import fr.training.spring.library.exposition.address.AddressAdapter;
import fr.training.spring.library.exposition.address.AddressDto;
import fr.training.spring.library.exposition.book.BookAdapter;
import fr.training.spring.library.exposition.common.AbstractAdapter;
import fr.training.spring.library.exposition.director.DirectorAdapter;
import fr.training.spring.library.exposition.director.DirectorDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LibraryAdapter extends AbstractAdapter<LibraryDto, Library> {

    @Autowired
    BookAdapter bookAdapter;

    @Autowired
    DirectorAdapter directorAdapter;

@Autowired
AddressAdapter addressAdapter;

    @Override
    public LibraryDto mapToDto(Library entity) {
        final LibraryDto libraryDto = new LibraryDto();
        libraryDto.setAddress(addressAdapter.mapToDto(entity.getAddress()));
        libraryDto.setDirector(directorAdapter.mapToDto(entity.getDirector()));
        libraryDto.setId(entity.getId());
        libraryDto.setType(entity.getType());
        libraryDto.setBooks(bookAdapter.mapToDtoList(entity.getBooks()));

        return libraryDto;
    }


    @Override
    public Library mapToEntity(LibraryDto dto) {
        final Library library = new Library();
        library.setAddress(addressAdapter.mapToEntity(dto.getAddress()));
        library.setDirector(directorAdapter.mapToEntity(dto.getDirector()));
        library.setId(dto.getId());
        library.setType(dto.getType());
        library.setBooks(bookAdapter.mapToEntityList(dto.getBooks()));
        return library;
    }

}
