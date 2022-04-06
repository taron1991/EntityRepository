package EntityRepository;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "Ad")
public class Ad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "brand")
    private String brand;

    @Column(name = "url")
    private String url;

    @Column(name = "date")
    private LocalDate date;

    public Ad() {
    }

    public Ad(String name, String brand, String url, LocalDate date) {
        this.name = name;
        this.brand = brand;
        this.url = url;
        this.date = date;
    }

    @Override
    public String toString() {
        return "Ad{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", brand='" + brand + '\'' +
                ", url='" + url + '\'' +
                ", date=" + date +
                '}';
    }
}
