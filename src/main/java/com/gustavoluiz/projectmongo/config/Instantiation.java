package com.gustavoluiz.projectmongo.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.gustavoluiz.projectmongo.domain.Post;
import com.gustavoluiz.projectmongo.domain.User;
import com.gustavoluiz.projectmongo.dto.AuthorDTO;
import com.gustavoluiz.projectmongo.dto.CommentDTO;
import com.gustavoluiz.projectmongo.repository.PostRepository;
import com.gustavoluiz.projectmongo.repository.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		
		userRepository.deleteAll();
		postRepository.deleteAll();
		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");
		userRepository.saveAll(Arrays.asList(maria, alex, bob));
		
		
		Post p1 = new Post(null, sdf.parse("17/04/2024"), "Partiu viagem", "Vou viajar para são paulo com o fabiano!", new AuthorDTO(maria));
		Post p2 = new Post(null, sdf.parse("16/04/2024"), "Bom dia", "Acordei feliz hoje!", new AuthorDTO(maria));
		
		CommentDTO c1 = new CommentDTO("Boa viagem mano!", sdf.parse("21/03/2024"), new AuthorDTO(alex));
		CommentDTO c2 = new CommentDTO("Aproveite", sdf.parse("13/03/2024"), new AuthorDTO(bob));
		CommentDTO c3 = new CommentDTO("Tenha ótimo dia!", sdf.parse("25/04/2024"), new AuthorDTO(alex));

		p1.getComments().addAll(Arrays.asList(c1, c2));
		p2.getComments().addAll(Arrays.asList(c3));
		
		postRepository.saveAll(Arrays.asList(p1, p2));
		
		maria.getPosts().addAll(Arrays.asList(p1, p2));
		userRepository.save(maria);
	}

	
	
}
