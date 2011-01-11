package mc;

public class Commento {
	private Long id;
	private Long postId;
	private Long authorId;
	private String text;
	

	public Commento() {
		
	}

	public Commento(Long id, Long postId, Long authorId, String text) {
		this.id = id;
		this.postId = postId;
		this.authorId = authorId;
		this.text = text;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getPostId() {
		return postId;
	}

	public void setPostId(Long postId) {
		this.postId = postId;
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

	public void setText(String text) {
		this.text = text;
	}
}
