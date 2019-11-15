package guru.springframework.repositories.reactive;

import guru.springframework.domain.UnitOfMeasure;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataMongoTest
public class UnitOfMeasureReactiveTest {

    public static final String EACH = "Each";
    @Autowired
    UnitOfMeasureReactiveRepository unitOfMeasureReactiveRepository;

    @Before
    public void setUp(){
        unitOfMeasureReactiveRepository.deleteAll().block();
    }

    @Test
    public void testSaveUom() {
        UnitOfMeasure unitOfMeasure = new UnitOfMeasure();
        unitOfMeasure.setDescription(EACH);

        unitOfMeasureReactiveRepository.save(unitOfMeasure).block();
        Long count = unitOfMeasureReactiveRepository.count().block();
        assertEquals(Long.valueOf(1L), count);
    }

    @Test
    public void testFindByDescription(){
        UnitOfMeasure unitOfMeasure = new UnitOfMeasure();
        unitOfMeasure.setDescription(EACH);
        unitOfMeasureReactiveRepository.save(unitOfMeasure).block();
        UnitOfMeasure unitOfMeasureFetched = (UnitOfMeasure) unitOfMeasureReactiveRepository.findByDescription(EACH).block();
        assertEquals(EACH, unitOfMeasureFetched.getDescription());

    }
}
