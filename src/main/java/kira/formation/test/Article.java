package kira.formation.test;

import lombok.Data;

@Data
public class Article {
    private String id;
    private String nom;
    private double prixHT;
    private ArticleCategory category;
    private double prixTTC;

    public Article(String id, String nom, double prixHT, ArticleCategory category) {
        this.id = id;
        this.nom = nom;
        this.prixHT = prixHT;
        this.category = category;
    }
}
