package entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tag")
public class Tag {
	
	@Id
	private int id;
	
	@Column(name = "Tag")
	private String tag;
	
	
	
	public Tag() {}
	public Tag(int id, String tag) { this.id = id; this.tag = tag; }

	
	
	public int getId() { return id; }
	public void setId(int id) { this.id = id; }
	
	public String getTag() { return tag; }
	public void setTag(String tag) { this.tag = tag; }

	
	
	@Override
	public String toString() { return "articoloTag [id=" + id + ", tag=" + tag + "]"; }

}