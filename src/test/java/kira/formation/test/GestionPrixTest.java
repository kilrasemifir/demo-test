package kira.formation.test;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class GestionPrixTest {

    @Test
    public void doitRetournerLePrixTTC(){
        // Given
        ArticleRepository repository = Mockito.mock(ArticleRepository.class);
        GestionPrix gestionPrix = new GestionPrix(repository);
        // When
        double result = gestionPrix.calculeTVA(10,0.2);
        // Then
        assertEquals(12.0, result);
    }

    @ParameterizedTest
    @MethodSource("allArticleProvider")
    public void doitRetournerLArticleAvecLePrixTTC(Article article, double prixTTC){
        // Given
        ArticleRepository repository = Mockito.mock(ArticleRepository.class);
        Mockito.when(repository.findById(article.getId())).thenReturn(article);
        GestionPrix gestionPrix = new GestionPrix(repository);
        // When
        Article result = gestionPrix.findById(article.getId());
        // Then
        assertNotNull(result);
        assertEquals(prixTTC, result.getPrixTTC());
        assertEquals(article.getNom(), result.getNom());
    }

    public static Stream<Arguments> allArticleProvider() {
        return Stream.of(
                Arguments.of(new Article("alcool", "51", 10, ArticleCategory.ALCOOL), 12.0),
                Arguments.of(new Article("pommes", "golden", 1, ArticleCategory.NOURRITURE), 1.05),
                Arguments.of(new Article("ps5", "ps5", 500, ArticleCategory.LOISIR), 600)

        );
    }
}