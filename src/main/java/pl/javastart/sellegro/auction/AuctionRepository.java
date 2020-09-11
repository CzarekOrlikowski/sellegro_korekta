package pl.javastart.sellegro.auction;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface AuctionRepository extends JpaRepository<Auction, Long> {

    List<Auction> findAllBy();

    @Query(value = "SELECT * FROM auction ORDER BY price DESC LIMIT 4", nativeQuery = true)
    List<Auction> find4MostExpensive();

    @Query(value = "SELECT * FROM auction ORDER BY title ASC", nativeQuery = true)
    List<Auction> orderByTitle();

    @Query(value = "SELECT * FROM auction ORDER BY price ASC", nativeQuery = true)
    List<Auction> orderByPrice();

    @Query(value = "SELECT * FROM auction ORDER BY color ASC", nativeQuery = true)
    List<Auction> orderByColor();

    @Query(value = "SELECT * FROM auction ORDER BY end_date DESC", nativeQuery = true)
    List<Auction> orderByEndDate();

    @Query(value = "SELECT * FROM auction WHERE title =:title AND car_maker =:carMaker " +
            "AND car_model =:carModel AND car_color =:color", nativeQuery = true)
    List<Auction> findAllForFilters(@Param("title") String title,
                                    @Param("carMaker") String carMaker,
                                    @Param("carModel") String carModel,
                                    @Param("color") String color);

}
