package be.iccbxl.pid.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArtistService {
	@Autowired
	private ArtistRepository artistRepository;
	
	public List<Artist> getAllArtists() {
		List<Artist> artists = new ArrayList<>();
		
		artistRepository.findAll().forEach(artists::add);
		
		return artists;
	}
	
	public Artist getArtist(String id) {
		int indice = Integer.parseInt(id);
		
		return artistRepository.findById(indice);
	}

	public void addArtist(Artist artist) {
		artistRepository.save(artist);
	}

	public void updateArtist(Long id, Artist artist) {
		artistRepository.save(artist);
	}

	public void deleteArtist(Long id) {
		artistRepository.deleteById(id);
	}
}
