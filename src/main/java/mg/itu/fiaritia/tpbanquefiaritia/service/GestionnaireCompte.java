/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mg.itu.fiaritia.tpbanquefiaritia.service;

import jakarta.annotation.sql.DataSourceDefinition;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import java.util.List;
import mg.itu.fiaritia.tpbanquefiaritia.entity.CompteBancaire;

/**
 * Façade pour gérer les comptes bancaire.
 *
 * @author raham
 */
@DataSourceDefinition(
        className = "com.mysql.cj.jdbc.MysqlDataSource",
        name = "java:app/jdbc/banque",
        serverName = "localhost",
        portNumber = 3307,
        user = "root",
        password = "root",
        databaseName = "banque",
        properties = {
            "useSSL=false",
            "allowPublicKeyRetrieval=true",
            "driverClass=com.mysql.cj.jdbc.Driver"
        }
)
@ApplicationScoped
public class GestionnaireCompte {

    @PersistenceContext(unitName = "banquePU")
    private EntityManager em;

    /**
     * Fait une nouvelle insertion: génèrera un INSERT SQL dans la base de
     * données
     *
     * @param c
     */
    @Transactional
    public void creerCompte(CompteBancaire c) {
        em.persist(c);
    }

    /**
     * Récuperer la liste des CompteBancaire : la requête correspond à un
     * "select *"
     *
     * @return List
     */
    public List<CompteBancaire> getAllComptes() {
        TypedQuery<CompteBancaire> query
                = em.createNamedQuery("CompteBancaire.findAll",
                        CompteBancaire.class);
        return query.getResultList();
    }
    
    /**
     * Rechercher un compte bancaire suivant son identifiant
     * @param id
     * @return CompteBancaire
     */
    public CompteBancaire findById(int id) {
        return em.find(CompteBancaire.class, id);
    }
}
