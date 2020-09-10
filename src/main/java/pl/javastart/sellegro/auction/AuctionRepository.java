package pl.javastart.sellegro.auction;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface AuctionRepository extends JpaRepository<Auction, Long> {

    List<Auction> findAllBy();

    @Query(value="SELECT * FROM auction ORDER BY price DESC LIMIT 4", nativeQuery = true)
    List<Auction> find4MostExpensive();

}
