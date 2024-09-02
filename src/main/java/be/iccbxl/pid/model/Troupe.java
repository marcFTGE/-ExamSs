package be.iccbxl.pid.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="troupes")
public class Troupe {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "The name must not be empty.")
    @Size(min=2, max=100, message = "The name must be between 2 and 100 characters long.")
    private String name;

    @OneToMany(mappedBy = "troupe", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Artist> artists = new ArrayList<>();

    protected Troupe() {}

    public Troupe(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Artist> getArtists() {
        return artists;
    }

    public Troupe addArtist(Artist artist) {
        if(!this.artists.contains(artist)) {
            this.artists.add(artist);
            artist.setTroupe(this);
        }

        return this;
    }

    public Troupe removeArtist(Artist artist) {
        if(this.artists.contains(artist)) {
            this.artists.remove(artist);
            artist.setTroupe(null);
        }

        return this;
    }

    @Override
    public String toString() {
        return name;
    }
}