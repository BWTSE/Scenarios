import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

class Main {
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		Polymer first = null;
		Polymer last = null;
		while (s.hasNext()) {
			Polymer p = new Polymer(s.next().toCharArray()[0]);
			if (first == null) {
				first = p;
			}
			if (last != null) {
				last.insertAfter(p);
			}
			last = p;
		}

		Polymer p
	}

}

class Polymer {
	final char type;
	final boolean isCapital;
	Polymer prev;
	Polymer next;
	public Polymer(char c) {
		this.type = Character.toLowerCase(c);
		this.isCapital = Character.isUpperCase(c);
	}

	boolean reactsWith(Polymer other) {
		return this.type == other.type && this.isCapital != other.isCapital;
	}

	void insertAfter (Polymer other) {
		if (this.next != null) {
			this.next.prev = other;
			other.next = this.next;
		}
		other.prev = this;
		this.next = other;
	}

	void insertBefore (Polymer other) {
		if (this.prev != null) {
			this.prev.next = other;
			other.prev = this.prev;
		}
		other.next = this;
		this.prev = other;
	}
}