package pl.javastart.sellegro.auction;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class AuctionController {

    public AuctionService auctionService;

    public AuctionController(AuctionService auctionService) {
        this.auctionService = auctionService;
    }

//    @GetMapping("/auctions")
//    public String auctions(Model model,
//                           @RequestParam(required = false) String sort,
//                           AuctionFilters auctionFilters) {
//        List<Auction> auctions;
//        if (sort != null) {
//            auctions = auctionService.findAllSorted(sort);
//        } else {
//            auctions = auctionService.findAllForFilters(auctionFilters);
//        }
//
//        model.addAttribute("cars", auctions);
//        model.addAttribute("filters", auctionFilters);
//        return "auctions";
//    }


    @GetMapping("/auctions")
    public String auctionsNew(Model model,
                              @RequestParam(required = false) String sort,
                              @RequestParam(required = false) String title,
                              @RequestParam(required = false) String carMaker,
                              @RequestParam(required = false) String carModel,
                              @RequestParam(required = false) String color) {

        List<Auction> auctions;
        if (sort != null) {
            auctions = auctionService.findAllSorted(sort);
        } else if (title != null || carMaker != null || carModel != null || color != null) {
            auctions = auctionService.findAllForFilters(title, carMaker, carModel, color);
        } else {
            auctions =auctionService.findAllBy();
        }

        model.addAttribute("cars", auctions);
        model.addAttribute("title", title);
        model.addAttribute("carMaker", carMaker);
        model.addAttribute("carModel", carModel);
        model.addAttribute("color", color);

        return "auctions";
    }
}
