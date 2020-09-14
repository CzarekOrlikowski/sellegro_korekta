package pl.javastart.sellegro.auction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class AuctionService {

    public AuctionRepository auctionRepository;

    @Autowired
    public AuctionService(AuctionRepository auctionRepository) {
        this.auctionRepository = auctionRepository;
    }

    public List<Auction> auctions;

    private static final String[] ADJECTIVES = {"Niesamowity", "Jedyny taki", "IGŁA", "HIT", "Jak nowy",
            "Perełka", "OKAZJA", "Wyjątkowy"};


    public void getAuctions() {

        List<Auction> auctionList = auctionRepository.findAllBy();
        Random random = new Random();

        for (Auction auction : auctionList) {
            auction.setTitle(ADJECTIVES[random.nextInt(ADJECTIVES.length)] + " " + auction.getCarMake() + " " + auction.getCarModel());
            auctionRepository.save(auction);
        }
    }

    public List<Auction> find4MostExpensive() {
        return auctionRepository.find4MostExpensive();
    }

    public List<Auction> findAllForFilters(AuctionFilters auctionFilters) {
        return auctionRepository.findAll().stream()
                .filter(auction -> auctionFilters.getTitle() == null || auction.getTitle().toUpperCase().contains(auctionFilters.getTitle().toUpperCase()))
                .filter(auction -> auctionFilters.getCarMaker() == null || auction.getCarMake().toUpperCase().contains(auctionFilters.getCarMaker().toUpperCase()))
                .filter(auction -> auctionFilters.getCarModel() == null || auction.getCarModel().toUpperCase().contains(auctionFilters.getCarModel().toUpperCase()))
                .filter(auction -> auctionFilters.getColor() == null || auction.getColor().toUpperCase().contains(auctionFilters.getColor().toUpperCase()))
                .collect(Collectors.toList());
    }

    public List<Auction> findAllSorted(String sort) {

        switch (sort) {
            case "title":
                return auctionRepository.orderByTitle();
            case "price":
                return auctionRepository.orderByPrice();
            case "color":
                return auctionRepository.orderByColor();
            case "endDate":
                return auctionRepository.orderByEndDate();
        }
        return auctionRepository.findAll();
    }
}
