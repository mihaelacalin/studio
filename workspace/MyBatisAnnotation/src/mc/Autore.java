package mc;

import java.util.Set;

public class Autore {
	private long id;
	private String name;
	private Set<Post> posts;
	private Set<Commento> comments;

	public Set<Commento> getComments() {
		return comments;
	}

	public void setComments(Set<Commento> comments) {
		this.comments = comments;
	}

	public Set<Post> getPosts() {
		return posts;
	}

	public void setPosts(Set<Post> posts) {
		this.posts = posts;
	}

	public Autore() {
	}

	public Autore(long id, String name, String email) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	private String email;
}
