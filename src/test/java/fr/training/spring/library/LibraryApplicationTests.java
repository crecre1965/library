    package fr.training.spring.library;


    import static org.assertj.core.api.Assertions.assertThat;

    import org.junit.jupiter.api.BeforeEach;
    import org.junit.jupiter.api.DisplayName;
    import org.junit.jupiter.api.Test;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.boot.test.context.SpringBootTest;
    import org.springframework.boot.test.web.client.TestRestTemplate;
    import org.springframework.http.HttpStatus;
    import org.springframework.http.ResponseEntity;


    @SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
    @DisplayName("tp-spring-0")
    class LibraryApplicationTests {
        @Autowired
        private TestRestTemplate restTemplate;

        @Autowired
        private  LibraryDao libraryDao;


    @BeforeEach
    public void setUp(){
            libraryDao.deleteAll();

    }

        @Test
        @DisplayName("should return empty list when no libraries created beforehand")
        void getMapping_should_return_empty_list(){
            final ResponseEntity<Library[]> response=restTemplate.getForEntity("/api/library/lister-all", Library[].class);
            assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
            assertThat(response.getBody()).isEmpty();
        }

        @Test
        @DisplayName("should return 2 list when 2 librarie created beforehand")
        void getMapping_with_existing_library_shoud_return_filled_list(){
            libraryDao.save(new Library(10000,Type.ASSOCIATIVE,  new Address(54,"rue",91620,"nozay"),new Director("lolo","crecre")));
            libraryDao.save(new Library(10010,Type.ASSOCIATIVE,  new Address(54,"rue",91620,"nozay"),new Director("lolo","crecre")));
            final ResponseEntity<Library[]> response=restTemplate.getForEntity("/api/library/lister-all", Library[].class);
            assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
            assertThat(response.getBody()).hasSize(2);
        }


    }
