package mc;

import java.util.Set;

public class Post {
	private Long id;
	private Long authorId;
	private String text;
	private Set<Commento> comments;

	public Post() {

	}

	public Set<Commento> getComments() {
		return comments;
	}

	public void setComments(Set<Commento> comments) {
		this.comments = comments;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getAuthorId() {
		return authorId;
	}

	public void setAuthorId(Long authorId) {
		this.authorId = authorId;
	}

	public String getText() {
		return text;
	}

	public Post(Long id, Long authorId, String text) {
		this.id = id;
		this.authorId = authorId;
		this.text = text;
	}

	public void setText(String text) {
		this.text = text;
	}

}
