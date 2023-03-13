package com.mjc.school.service;


import com.mjc.school.repository.factory.RepositoryFactory;
import com.mjc.school.repository.impl.NewsRepository;
import com.mjc.school.repository.model.data.NewsModel;
import com.mjc.school.service.dto.NewsDtoRequest;
import com.mjc.school.service.dto.NewsDtoResponse;
import com.mjc.school.service.exception.NotFoundException;
import com.mjc.school.service.impl.NewsService;
import com.mjc.school.service.interfaces.ModelMapper;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class NewsServiceTest {

    private Logger logger = Logger.getLogger(NewsServiceTest.class.getName());

    private ModelMapper modelMapper = Mappers.getMapper(ModelMapper.class);

    @InjectMocks
    private NewsService newsService;

    @Mock
    private NewsRepository newsRepository;

    private RepositoryFactory repositoryFactory = RepositoryFactory.getInstance();

    private List<NewsModel> readAll;

    @DisplayName("JUnit test for create method")
    @Test
    public void shouldCreateNewsModelSuccessFully() throws NotFoundException {

        NewsDtoRequest newsDtoRequest = new NewsDtoRequest(null, "Testing NewsService", "JUnit test", 20L);

        newsService.create(newsDtoRequest);

        Long testResult = newsService.readById(21L).id();

        assertEquals(21L, testResult);

    }

    @DisplayName("JUnit test for update method")
    @Test
    public void shouldUpdateNewsModelSuccessFully() throws NotFoundException, IllegalArgumentException {
        Long id = 20L;
        Long authorId = 19L;
        String title = "Testing Update Method";
        String content = "This is new content";
        NewsRepository newsRepository = new NewsRepository();
        NewsDtoRequest newsUpdateData = new NewsDtoRequest(id, title, content, authorId);

        assertNotNull(newsService.update(newsUpdateData));
        newsService.update(newsUpdateData);
        NewsModel updatedNewsModel = newsRepository.readById(id);

        assertEquals(title, updatedNewsModel.getTitle());
        assertEquals(content, updatedNewsModel.getContent());
        assertEquals(authorId, updatedNewsModel.getAuthorId());

    }


    @Nested
    class NewsServiceNested {
        @BeforeEach
        public void init() throws NotFoundException {
            logger.info("Prepare Data for testing");
            readAll = repositoryFactory.getNewsRepository().readAll();
        }


        @DisplayName("JUnit test for findAll method")
        @Test
        public void findAll() throws NotFoundException {
            List<NewsDtoResponse> newsDtoResponseList = newsService.readAll();

            assertEquals(newsDtoResponseList.size(), readAll.size());
        }

        @DisplayName("JUnit test for findById method")
        @Test
        public void findById() throws NotFoundException {
            Long expectedId = 1L;

            NewsDtoResponse expected = newsService.readById(1L);

            Long testResult = newsService.readById(1L).id();

            assertEquals(expectedId, testResult);

            assertNotNull(expected);

        }

        @DisplayName("JUnit test for deleteById method")
        @Test
        public void shouldBeDelete() throws NotFoundException, IllegalArgumentException {
            Long newsId = 1L;

            assertTrue(newsService.deleteById(newsId));
            assertNull(newsRepository.readById(newsId));
        }

        @AfterEach
        public void cleanData() {
            logger.info("Cleaning data for testing");
            readAll = null;
        }
    }
}
