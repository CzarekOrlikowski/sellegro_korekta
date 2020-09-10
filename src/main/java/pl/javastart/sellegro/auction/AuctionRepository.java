package pl.javastart.sellegro.auction;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface AuctionRepository extends JpaRepository<Auction, Long> {

    List<Auction> findAllBy();
}
