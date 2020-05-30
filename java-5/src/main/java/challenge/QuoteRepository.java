package challenge;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface QuoteRepository extends CrudRepository<Quote, Integer> {


    @Query(value = "SELECT * FROM scripts ORDER BY RAND() LIMIT 1", nativeQuery = true)
    Quote findRandomQuote();

    @Query(value = "SELECT * FROM scripts s WHERE s.actor = ?1 ORDER BY RAND() LIMIT 1", nativeQuery = true)
    Quote findRandomQuoteByActor(String actor);
}
