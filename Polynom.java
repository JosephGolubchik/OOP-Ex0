package myMath;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.function.Predicate;

import myMath.Monom;
/**
 * This class represents a Polynom with add, multiply functionality, it also should support the following:
 * 1. Riemann's Integral: https://en.wikipedia.org/wiki/Riemann_integral
 * 2. Finding a numerical value between two values (currently support root only f(x)=0).
 * 3. Derivative
 * 
 * @author Boaz
 *
 */
public class Polynom implements Polynom_able{

	// ********** add your code below ***********
	
	ArrayList<Monom> list = new ArrayList<>();
	
	@Override
	public double f(double x) {
		double sum = 0;
		for (int i = 0; i < this.list.size(); i++) {
			sum += this.list.get(i).f(x);
		}
		return sum;
	}

	@Override
	public void add(Polynom_able p1) {
		Iterator<Monom> it = p1.iteretor();
		while(it.hasNext()) {
			Monom x = it.next();
			this.add(x);
		}
	}

	@Override
	public void add(Monom m1) {
		boolean added = false;
		Monom_Comperator cmp = new Monom_Comperator();
		for (int i = 0; i < this.list.size(); i++) {
			if (this.list.get(i).get_power()==m1.get_power()) {
				this.list.get(i).add(m1);
				added = true;
			}
		}
		if(!added) {
			list.add(m1);
			list.sort(cmp);
		}
		
	}

	@Override
	public void substract(Polynom_able p1) {
		Polynom temp = new Polynom();
		Iterator<Monom> it = p1.iteretor();
		while(it.hasNext()) {
			Monom x = it.next();
			Monom mNew = new Monom(-1 * x.get_coefficient(), x.get_power());
			temp.add(mNew);
		}
		this.add(temp);
		it = p1.iteretor();
		while(it.hasNext()) {
			Monom x = it.next();
			if (x.isZero()) this.list.remove(x);
		}
	}

	@Override
	public void multiply(Polynom_able p1) {
		Polynom sum = new Polynom();
		Iterator<Monom> it1 = this.iteretor();
		Iterator<Monom> it2 = p1.iteretor();
		while(it1.hasNext()) {
			Monom m1 = it1.next();
			while(it2.hasNext()) {
				Monom m2 = it2.next();
				Monom m3 = new Monom(m1.get_coefficient(),m1.get_power());
				m3.multiply(m2);
				sum.add(m3);
			}
			it2 = p1.iteretor();
		}
		Polynom copy = new Polynom();
		copy.add(this);
		
		this.substract(copy);
		this.add(sum);
		
	}

	@Override
	public boolean equals(Polynom_able p1) {
		Iterator<Monom> it1 = this.iteretor();
		Iterator<Monom> it2 = p1.iteretor();
		while(it1.hasNext() || it2.hasNext()) {
			if (it1.hasNext() && !it2.hasNext() || !it1.hasNext() && it2.hasNext())
				return false;
			Monom m1 = it1.next();
			Monom m2 = it2.next();
			if (!(m1.equals(m2))) {
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean isZero() {
		return this.list.size() == 0;
	}

	@Override
	public double root(double x0, double x1, double eps) {
		
		double y0 = f(x0);
		double y1 = f(x1);

		if(y0 * y1 > 0)
			System.err.println("f(x0) and f(x1) should have different signs");
		
		double x_diff = Math.abs(x1- x0);
		double y_diff = Math.abs(f(x0) - f(x1));
		
		if (x_diff > eps || y_diff > eps) {
			double x_mid = (x0 + x1) / 2;
			double y_mid = f(x_mid);
			
			if(y0 * y_mid <= 0)
				return root(x0, x_mid, eps);
			else
				return root(x_mid, x1, eps);
		}
		
		return x0;
	}

	@Override
	public Polynom_able copy() {
		Polynom copy = new Polynom();

		Iterator<Monom> it = this.iteretor();
		while(it.hasNext()) {
			Monom x = it.next();
			Monom mNew = new Monom(x.get_coefficient(), x.get_power());
			copy.add(mNew);
		}
		return copy;
	}

	@Override
	public Polynom_able derivative() {
		Polynom der = new Polynom();
		Iterator<Monom> it = this.iteretor();
		while (it.hasNext()) {
			Monom curr = it.next(); // Current Monom
			Monom cder = curr.derivative(); // Derivative of current Monom
			der.add(cder);
		}
		return der;
	}

	@Override
	public double area(double x0, double x1, double eps) {
		// eps is number of rectangles from x0 to x1
		double sum=0;
		double width = (x1-x0)/eps; // width of one rectangle
		for (double i = x0; i <= x1; i=i+width) {
			sum += width * Math.max(f(i), f(i+width));
		}
		return sum;
	}

	@Override
	public Iterator<Monom> iteretor() {
		return this.list.iterator();
	}

	/**
	 * If polynom is empty, return a string of the zero Monom
	 */
	public String toString() {
		String ans;
		if (isZero()) {
			ans = "0.0*X^0";
		}
		else {
			ans = list.get(0).toString();
			for (int i = 1; i < list.size(); i++) {
				ans = ans + " + " + list.get(i).toString();
			}
		}
		return ans;
	}
}
