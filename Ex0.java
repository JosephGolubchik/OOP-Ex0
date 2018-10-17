package myMath;

import java.util.ArrayList;

public class Ex0 {

	public static void main(String[] args) {

		Monom m1 = new Monom (2, 2); //2x^2
		Monom m2 = new Monom (5, 2); //5x^2
		Monom m3 = new Monom (5, 5); //5x^5
//		x.multiply(y);
//		System.out.println(x.toString());
//		ArrayList<Monom> list = new ArrayList<>();
//		Monom_Comperator cmp = new Monom_Comperator();
//		list.add(m1);
//		list.add(m2);
//		list.sort(cmp);

		Polynom p1 = new Polynom();
		p1.add(m3);
		System.out.println("p1: " + p1.toString());
		
		Polynom p2 = new Polynom();
		p2.add(m2);
//		System.out.println(p2.toString());
		
//		p1.substract(p2);
//		System.out.println(p1.toString());
		
//		p1.multiply(p2);
//		System.out.println(p1.toString());
		
//		System.out.println(p1.equals(p2));
		
		Polynom p3 = (Polynom) p1.copy();
		System.out.println("p3: " + p3.toString());
		
		Monom m4 = new Monom(2,5);
		p1.add(m4);
		
		System.out.println("p1: " + p1.toString());
		System.out.println("p3: " + p3.toString());
		
	}
	
	

}
