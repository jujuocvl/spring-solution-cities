package monprojet.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import monprojet.entity.City;
import monprojet.entity.Country;

// This will be AUTO IMPLEMENTED by Spring 

public interface CountryRepository extends JpaRepository<Country, Integer> {

        // Méthode 1 -> renvoyer la population d'un pays à partir de son id
        @Query(value = " SELECT SUM(population) AS population "
                +" FROM City " 
                +" INNER JOIN Country ON City.country_id = Country.id "
                +" WHERE Country.id = :idPays",
                nativeQuery = true)
        public int nbPopulation(int idPays);
    
        // Méthode 2 -> renvoyer la liste avec le nom et la population des pays sans paramètres
        @Query(value = " SELECT Country.name as nom, SUM(City.population) AS population "
                +" FROM City "
                +" INNER JOIN Country ON City.country_id = Country.id "
                +" GROUP BY nom ",
                nativeQuery = true)
        public List<PPP> PopParPays();

}
