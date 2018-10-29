package myMath;

import java.util.ArrayList;

public class Ex0 {

	public static void main(String[] args) {

		Monom m1 = new Monom (5, 3); //2x^2
		Monom m2 = new Monom (2, 2); //5x^2
		Monom m3 = new Monom (-2, 1); //5x^5

		Polynom p1 = new Polynom();
		p1.add(m1); p1.add(m2); p1.add(m3);
		System.out.println(p1);
		
//		System.out.println(p1.area(1, 4, 10));
		
		System.out.println(p1.root(-1, 0, 0.000001));
		
//		Polynom pd = (Polynom) p1.derivative();
//		System.out.println(pd);

		
	}
	
	

}
