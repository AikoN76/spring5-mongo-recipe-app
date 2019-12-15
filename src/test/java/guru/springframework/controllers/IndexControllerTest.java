package guru.springframework.controllers;

import guru.springframework.services.RecipeService;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.ui.Model;
import org.thymeleaf.spring5.ISpringWebFluxTemplateEngine;
import org.thymeleaf.spring5.SpringWebFluxTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.reactive.ThymeleafReactiveViewResolver;
import org.thymeleaf.templatemode.TemplateMode;
import reactor.core.publisher.Flux;

import static org.mockito.Mockito.when;

/**
 * Created by jt on 6/17/17.
 */
@RunWith(SpringRunner.class)
@WebFluxTest
@Import(IndexController.class)
public class IndexControllerTest {

    WebTestClient webTestClient;

    @Autowired
    ApplicationContext applicationContext;

    @Mock
    RecipeService recipeService;

    @Mock
    Model model;

    @Autowired
    IndexController controller;

    @Before
    public void setUp() throws Exception {
        webTestClient = WebTestClient.bindToController(controller).build();
    }

    @Test
    @Ignore
    public void testMockMVC() throws Exception {
       // MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

        when(recipeService.getRecipes()).thenReturn(Flux.empty());

        //mockMvc.perform(get("/"))
        //        .andExpect(status().isOk())
        //        .andExpect(view().name("index"));

        webTestClient.get().uri("/")
                .exchange()
                .expectStatus().isOk()
                .expectBody();
    }

    @Test
    @Ignore
    public void getIndexPage() throws Exception {

//        //given
//        List<Recipe> recipes = new ArrayList<>();
//        recipes.add(new Recipe());
//
//        Recipe recipe = new Recipe();
//        recipe.setId("1");
//
//        recipes.add(recipe);
//
//        when(recipeService.getRecipes()).thenReturn(Flux.fromIterable(recipes));
//
//        ArgumentCaptor<List<Recipe>> argumentCaptor = ArgumentCaptor.forClass(List.class);
//
//        //when
//        String viewName = controller.getIndexPage(model);
//
//        //then
//        assertEquals("index", viewName);
//        verify(recipeService, times(1)).getRecipes();
//        verify(model, times(1)).addAttribute(eq("recipes"), argumentCaptor.capture());
//        List<Recipe> recipeList = argumentCaptor.getValue();
//        assertEquals(2, recipeList.size());
    }

    @Configuration
    static class WebConfig {

        @Bean
        public SpringResourceTemplateResolver defaultTemplateResolver(ApplicationContext applicationContext) {
            SpringResourceTemplateResolver resolver = new SpringResourceTemplateResolver();
            resolver.setApplicationContext(applicationContext);
            resolver.setPrefix("classpath:/templates/");
            resolver.setSuffix(".html");
            resolver.setTemplateMode(TemplateMode.HTML);
            resolver.setCharacterEncoding("UTF-8");
            resolver.setCacheable(false);
            resolver.setCheckExistence(true);
            return resolver;
        }

        @Bean
        public SpringWebFluxTemplateEngine templateEngine(SpringResourceTemplateResolver defaultTemplateResolver){
            SpringWebFluxTemplateEngine engine = new SpringWebFluxTemplateEngine();
            engine.addTemplateResolver(defaultTemplateResolver);
            return engine;
        }

        @Bean
        public ThymeleafReactiveViewResolver thymeleafReactiveViewResolver(final ISpringWebFluxTemplateEngine templateEngine){
            final ThymeleafReactiveViewResolver viewResolver = new ThymeleafReactiveViewResolver();
            viewResolver.setTemplateEngine(templateEngine);
            return viewResolver;
        }

    }

}