public abstract class Card {


    int color;

	public Card(int c) {
		color = c;
	}

	public int getColor() {
		return color;
	}

	public void setColor(int c) {
		color = c;
	}

	public boolean equalsColor(Card c) {
		return color == c.getColor();
	}
	public boolean equals(Card c){
		return ((this.equalsColor(c)) || (this.getAngka().equals(c.getAngka())));
	}

    
	public String getColorString() {
		switch (color) {
		case Warna.HIJAU:
			return "Green";
		case Warna.MERAH:
			return "Red";
		case Warna.BIRU:
			return "Blue";
		case Warna.KUNING:
			return "Yellow";
		case Warna.BLACK:
			return "Wild";
		}
		return "Color";
	}
	
	public String toString() {
		if (getColorString() == "Wild"){
			return (getAngka());
		}
		return (getAngka() + " " + getColorString());
	}

	

	//abstract method
	public abstract String getAngka();
	public abstract String getType();
}
