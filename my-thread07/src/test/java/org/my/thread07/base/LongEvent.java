package org.my.thread07.base;

//http://ifeve.com/disruptor-getting-started/
public class LongEvent { 
    private long id;
    private String name;
    
  

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

	@Override
	public String toString() {
		return "LongEvent [id=" + id + ", name=" + name + "]";
	} 
    
	
    
} 