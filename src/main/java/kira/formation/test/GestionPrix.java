package kira.formation.test;

public class GestionPrix {

    private final ArticleRepository repository;

    public GestionPrix(ArticleRepository repository) {
        this.repository = repository;
    }

    public double calculeTVA(double prixHT, double tva){
        return prixHT*(1+tva);
    }

    /**
     * Retourne l'article en fonction de son id.
     * Calcule automatiquement le prix TTC en fonction de la category et de la TVA.
     * @param id de l'article
     * @return l'article
     */
    public Article findById(String id){
        Article article = repository.findById(id);
        double tva = 0.0;
        switch (article.getCategory()){
            case ALCOOL:
            case LOISIR:
                tva=0.2;
                break;
            default:
               tva=0.05;
        }
        article.setPrixTTC(calculeTVA(article.getPrixHT(), tva));
        return article;
    }
}
