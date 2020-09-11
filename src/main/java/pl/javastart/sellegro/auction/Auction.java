package pl.javastart.sellegro.auction;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Auction {

    @Id
    private Long id;
    private String title;
    private String carMake;
    private String carModel;
    private String color;
    private BigDecimal price;
    private LocalDate endDate;

    public Auction(Long id, String title, String carMake, String carModel, String color, BigDecimal price, LocalDate endDate) {
        this.id = id;
        this.title = title;
        this.carMake = carMake;
        this.carModel = carModel;
        this.color = color;
        this.price = price;
        this.endDate = endDate;
    }
}
