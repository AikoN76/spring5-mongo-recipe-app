package guru.springframework.repositories.reactive;

import guru.springframework.domain.Category;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataMongoTest
public class CategoryReactiveRepositoryTest {

    @Autowired
    CateogoryReactiveRepository cateogoryReactiveRepository;

    @Before
    public void setUp(){
        cateogoryReactiveRepository.deleteAll().block();
    }

    @Test
    public void testSave() {
        Category category = new Category();
        category.setDescription("Foo");
        cateogoryReactiveRepository.save(category).block();
        Long count = cateogoryReactiveRepository.count().block();
        assertEquals(Long.valueOf(1L), count);
    }

    @Test
    public void testFindByDescription() {
        Category category = new Category();
        category.setDescription("Bar");
        cateogoryReactiveRepository.save(category).block();
        Category fetchedCategory = cateogoryReactiveRepository.findByDescription("Bar").block();
        assertEquals("Bar", fetchedCategory.getDescription());
    }
}
