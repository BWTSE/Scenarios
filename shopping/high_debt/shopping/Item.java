package salestracker;

public class Item {
	
	private int bc;
	private String n;
	private double p;
	private double q;
	
	public Item(int bc, String n, double p) {
		this.bc = bc;
		this.n = n;
		this.p = p;
		this.q = 1;
	}

	
	public int getbc() {
		return bc;
	}
	
	public String getn() {
		return n;
	}
	
	public double getp() {
		return p;
	}
	
	public double getq() {
		return q;
	}
	
	public void incq() {
		q += 1;
	}
	
	public void setq(double nq) {
		q = nq;
	}
	
	
	@Override
    public boolean equals(Object o) { 
    	  
        if (o == this) { 
            return true; 
        } 
  
        if (!(o instanceof Item)) { 
            return false; 
        } 

        Item i = (Item) o; 

        return n == i.getn();
    }
	
    @Override
    public int hashCode() {
        return this.bc;
    }
}
