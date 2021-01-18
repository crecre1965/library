    package fr.training.spring.library.exposition;


    import static fr.training.spring.library.exposition.DatabaseTestHelper.NATIONAL_LIBRARY_MONTREUIL;
    import static fr.training.spring.library.exposition.DatabaseTestHelper.SCHOOL_LIBRARY_PARIS;
    import static org.assertj.core.api.Assertions.assertThat;

    import fr.training.spring.library.domain.exception.ErrorCodes;
    import fr.training.spring.library.domain.library.Library;
    import fr.training.spring.library.domain.library.Type;
    import fr.training.spring.library.exposition.address.AddressDto;
    import fr.training.spring.library.exposition.book.BookDto;
    import fr.training.spring.library.exposition.director.DirectorDto;
    import fr.training.spring.library.exposition.library.LibraryDto;
    import fr.training.spring.library.infrastructure.library.LibraryDAO;
    import fr.training.spring.library.infrastructure.library.LibraryJpa;
    import org.junit.jupiter.api.*;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.boot.test.context.SpringBootTest;
    import org.springframework.boot.test.web.client.TestRestTemplate;
    import org.springframework.http.HttpEntity;
    import org.springframework.http.HttpMethod;
    import org.springframework.http.HttpStatus;
    import org.springframework.http.ResponseEntity;

    import java.util.Optional;
    import java.util.stream.Collectors;


    @SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
    @DisplayName("tp-spring-0")
    class LibraryApplicationTests {
        @Autowired
        private TestRestTemplate restTemplate;

        @Autowired
        private LibraryDAO libraryDAO;

        @Autowired
        private DatabaseTestHelper databaseTestHelper;

// As long as we have some other integration tests, this is useless
        // @Test
        // public void contextLoads() {
        //
        // }

        @BeforeEach
        public void setUp() {
            databaseTestHelper.setup();
        }

        @AfterEach
        public void tearDown() {
            databaseTestHelper.tearDown();
        }

        @Test
        @DisplayName("should return empty list when no libraries created beforehand")
        void lister_all_case_1() {
            // --- Given
            databaseTestHelper.delelteAll();
            // --- When
            final ResponseEntity<Library[]> response = restTemplate.getForEntity("/api/library/lister-all", Library[].class);
            assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
            // --- Then
            assertThat(response.getBody()).isEmpty();
        }

        @Test
        @DisplayName("should return 5 list when 5 librarie created beforehand")
        void lister_all_case_2() {
            // --- Given
            // -- When
            final ResponseEntity<Library[]> response = restTemplate.getForEntity("/api/library/lister-all", Library[].class);
            // -- Then
            assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
            assertThat(response.getBody()).hasSize(5);
        }

        @Test
        @DisplayName("should return OK when I search a known library")
        void lister_by_id_case_1() {
            // --- Given
            final LibraryJpa dummyLibrary = databaseTestHelper.createDummyLibrary();
            System.out.println("");
            System.out.println(dummyLibrary.getId());
            System.out.println("");
            // -- When
//            final ResponseEntity<Library> response=restTemplate.getForEntity("api/library/lister-by-id/1" , Library.class);
            final ResponseEntity<LibraryDto> response = restTemplate.getForEntity("/api/library/lister-by-id/" + dummyLibrary.getId(), LibraryDto.class);
            // -- Then
            assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
            assertThat(response.getBody().getId()).isEqualTo(dummyLibrary.getId());
            assertThat(response.getBody().getBooks().size()).isEqualTo(dummyLibrary.getBooks().size());
        }

        @Test
        @DisplayName("should return not found when I search an unknown library")
        void lister_by_id_case_2() {
            // --- Given
            // -- When
            final ResponseEntity<String> response = restTemplate.getForEntity("/api/library/lister-by-id/1", String.class);
            // -- Then
            assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
            assertThat(response.getBody()).contains("ERR_0001");
        }

        @Test
        @DisplayName("Api POST:/libraries should return a status created with ID of created library when passing a correct library")
        void test_create_1() {
            // --------------- Given ---------------
            // Test data

            // --------------- When ---------------
            // I do a request on /libraries
            final LibraryDto mantional_library_montreuil_dto =
                    new LibraryDto(0, NATIONAL_LIBRARY_MONTREUIL.getType(),
                            new AddressDto(NATIONAL_LIBRARY_MONTREUIL.getAddress().getNumber(),
                                    NATIONAL_LIBRARY_MONTREUIL.getAddress().getStreet(),
                                    NATIONAL_LIBRARY_MONTREUIL.getAddress().getZipcode(),
                                    NATIONAL_LIBRARY_MONTREUIL.getAddress().getCity()),
                            new DirectorDto(
                                    NATIONAL_LIBRARY_MONTREUIL.getDirector().getFirstname(),
                                    NATIONAL_LIBRARY_MONTREUIL.getDirector().getLastname()),
                            NATIONAL_LIBRARY_MONTREUIL.getBooks().stream().map(book -> new BookDto(book.getIsbn(), book.getTitle(),
                                    book.getAuthor(), book.getNumberOfPages(), book.getGenre()))
                                    .collect(Collectors.toList()));
            final ResponseEntity<LibraryDto> response = restTemplate.postForEntity("/api/library/creer", mantional_library_montreuil_dto,
                    LibraryDto.class);

            // --------------- Then ---------------
            // I get a success code, and a new library in the database with the given ID
            assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
            final LibraryDto libraryDtoCreated = response.getBody();
            assertThat(libraryDtoCreated.getId()).isNotNull().isPositive();

            final Optional<LibraryJpa> libraryFromDB = libraryDAO.findById(libraryDtoCreated.getId());
            assertThat(libraryFromDB).isNotEmpty();

            // Due to equals method not being implemented, we would need to compare field by
            // fields...which is bad !
            // We'll talk about equality in DDD further in this course.
            // TODO : Check equality
            assertThat(libraryFromDB.get().getType()).isEqualTo(NATIONAL_LIBRARY_MONTREUIL.getType());

        }

        @Nested
        @DisplayName("Api /api/library/modifier ")
        class Test_update {
            @Test
            @DisplayName(" should update the library when passing on a correct ID")
            void test_update_1() {
                // --------------- Given ---------------
                final LibraryJpa dummyLibrary = databaseTestHelper.createDummyLibrary();
                final Long idOfCreatedLibrary = dummyLibrary.getId();

                // --------------- When ---------------
                final LibraryDto schoolLibraryParisDTO = new LibraryDto(SCHOOL_LIBRARY_PARIS.getType()
                        , new AddressDto(
                        SCHOOL_LIBRARY_PARIS.getAddress().getNumber(), SCHOOL_LIBRARY_PARIS.getAddress().getStreet(),
                        SCHOOL_LIBRARY_PARIS.getAddress().getZipcode(), SCHOOL_LIBRARY_PARIS.getAddress().getCity()),
                        new DirectorDto(SCHOOL_LIBRARY_PARIS.getDirector().getLastname(),
                                SCHOOL_LIBRARY_PARIS.getDirector().getFirstname()),
                        SCHOOL_LIBRARY_PARIS
                                .getBooks().stream().map(book -> new BookDto(book.getIsbn(), book.getTitle(),
                                book.getAuthor(), book.getNumberOfPages(), book.getGenre()))
                                .collect(Collectors.toList()));
                restTemplate.put("/api/library/modifier/" + idOfCreatedLibrary, schoolLibraryParisDTO);

                // --------------- Then ---------------


                final Optional<LibraryJpa> libraryFromDB = libraryDAO.findById(idOfCreatedLibrary);
                assertThat(libraryFromDB).isNotEmpty();

                // TODO : Check equality
                assertThat(libraryFromDB.get().getType()).isEqualTo(SCHOOL_LIBRARY_PARIS.getType());
            }
            @Test
            @DisplayName(" should send an error when passing on an incorrect ")
            void test_update_2() {
                // --------------- Given ---------------
                // Test data
                // --------------- When ---------------
                final LibraryDto schoolLibraryParisDTO = new LibraryDto(SCHOOL_LIBRARY_PARIS.getType()
                        , new AddressDto(
                        SCHOOL_LIBRARY_PARIS.getAddress().getNumber(), SCHOOL_LIBRARY_PARIS.getAddress().getStreet(),
                        SCHOOL_LIBRARY_PARIS.getAddress().getZipcode(), SCHOOL_LIBRARY_PARIS.getAddress().getCity()),
                        new DirectorDto(SCHOOL_LIBRARY_PARIS.getDirector().getLastname(),
                                SCHOOL_LIBRARY_PARIS.getDirector().getFirstname()),
                        SCHOOL_LIBRARY_PARIS
                                .getBooks().stream().map(book -> new BookDto(book.getIsbn(), book.getTitle(),
                                book.getAuthor(), book.getNumberOfPages(), book.getGenre()))
                                .collect(Collectors.toList()));


                final ResponseEntity<String> response = restTemplate.exchange("/api/library/modifier/" + Long.MAX_VALUE,
                        HttpMethod.PUT, new HttpEntity<>(schoolLibraryParisDTO), String.class);

                // --------------- Then ---------------
                assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
                assertThat(response.getBody()).contains(ErrorCodes.LIBRARY_NOT_FOUND);
            }
        }
        @Nested
        @DisplayName("Api/library/supprimer")
        class Test_delete {
            @Test
            @DisplayName(" should delete the library when passing on a correct ID")
            void test_delete_1() {
                // --------------- Given ---------------
                final LibraryJpa librarySaved = databaseTestHelper.createDummyLibrary();
                final Long idOfSavedLibrary = librarySaved.getId();

                // --------------- When ---------------
                restTemplate.delete("/api/library/supprimer/" + idOfSavedLibrary);

                // --------------- Then ---------------
                final Optional<LibraryJpa> libraryFromDB = libraryDAO.findById(idOfSavedLibrary);
                assertThat(libraryFromDB).isEmpty();
            }

            @Test
            @DisplayName(" should send an error when passing on an incorrect ID")
            void test_delete_2() {
                // --------------- Given ---------------
                // Test data

                // --------------- When ---------------
                final ResponseEntity<String> response = restTemplate.exchange("/api/library/supprimer/" + Long.MAX_VALUE,
                        HttpMethod.DELETE, null, String.class);

                // --------------- Then ---------------
                assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
                assertThat(response.getBody()).contains(ErrorCodes.LIBRARY_NOT_FOUND);
            }
        }
        @Test
        @DisplayName("Api GET:/libraries/type/{type} should return all NATIONAL libraries when passing NATIONAL as parameter")
        void test_list_with_filter_1() {
            // --------------- Given ---------------
            // Test data

            // --------------- When ---------------
            final ResponseEntity<LibraryDto[]> response = restTemplate.getForEntity("/api/library/lister-by-type/" + Type.NATIONALE,
                    LibraryDto[].class);

            // --------------- Then ---------------
            assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
            assertThat(response.getBody()).hasSize(2).allMatch(library -> library.getType().equals(Type.NATIONALE));
        }

        @Test
        @DisplayName("Api GET:/libraries/director/surname/{surname} should get all libraries ruled by Garfield when passing Garfield as parameter")
        void test_list_with_filter_2() {
            // --------------- Given ---------------
            // Test data

            // --------------- When ---------------
            final ResponseEntity<LibraryDto[]> response = restTemplate
                    .getForEntity("/api/library/lister-by-firstname/" + "Garfield", LibraryDto[].class);

            // --------------- Then ---------------
            assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
            assertThat(response.getBody()).hasSize(3).allMatch(library -> library.getDirector().getFirstname().equals("Garfield"));

        }
    }