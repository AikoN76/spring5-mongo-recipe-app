package guru.springframework.repositories;

import guru.springframework.bootstrap.RecipeBootstrap;
import guru.springframework.domain.UnitOfMeasure;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by jt on 6/17/17.
 */
@RunWith(SpringRunner.class)
@DataMongoTest
public class UnitOfMeasureRepositoryIT {

    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    RecipeRepository recipeRepository;
    @Autowired
    UnitOfMeasureRepository unitOfMeasureRepository;

    @Before
    public void setUp() {
        recipeRepository.deleteAll();
        unitOfMeasureRepository.deleteAll();
        categoryRepository.deleteAll();
        RecipeBootstrap recipeBootstrap = new RecipeBootstrap(categoryRepository, recipeRepository, unitOfMeasureRepository);
        recipeBootstrap.onApplicationEvent(null);
    }

    @Test
    public void findByDescription()  {

        Optional<UnitOfMeasure> uomOptional = unitOfMeasureRepository.findByDescription("Teaspoon");

        UnitOfMeasure uom = null;

        if(uomOptional.isPresent()){
            uom = uomOptional.get();
        }

        assertNotNull(uom);
        assertEquals("Teaspoon", uom.getDescription());
    }

    @Test
    public void findByDescriptionCup()  {

        Optional<UnitOfMeasure> uomOptional = unitOfMeasureRepository.findByDescription("Cup");

        UnitOfMeasure uom = null;

        if(uomOptional.isPresent()){
            uom = uomOptional.get();
        }
        assertNotNull(uom);
        assertEquals("Cup", uom.getDescription());
    }

}