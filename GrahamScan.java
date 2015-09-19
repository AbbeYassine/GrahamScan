import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;
import java.util.Stack;

public class GrahamScan {
	static public int prod_vect(Point P1,Point P2,Point P3){
		return (int) ((P2.getX() - P1.getX())*(P3.getY() - P1.getY()) - (P3.getX() - P1.getX())*(P2.getY() - P1.getY()));
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Stack<Point> Pile = new Stack<Point>();
		Scanner in= new Scanner(System.in);
		
		int n=in.nextInt();
		ArrayList<Point> lp=new ArrayList<Point>();
		for(int i=0;i<n;i++){
			lp.add(new Point(in.nextDouble(),in.nextDouble()));
		}
		
		//Recherche Pivot
		 Point min1=lp.get(0);
		int imin=0;
		for(int i=1;i<lp.size();i++){
			if(min1.getY()>=lp.get(i).getY()){
				if(min1.getY()==lp.get(i).getY()){
					if(min1.getX()>lp.get(i).getX()){
						min1=lp.get(i);
						imin=i;
					}
				}else {
					min1=lp.get(i);
					imin=i;
				}
			}
		}
		final Point min=min1;
	
		lp.remove(imin);
		//lp liste contient les point sauf le pivot
		//il faut le trier par ordre croissant des angles 
		
		
		Collections.sort(lp, new Comparator<Point>() {

			@Override
			public int compare(Point o1, Point o2) {

				if (o1.getX() == min.getX() && o2.getX() == min.getX())
					return (int)Math.signum(o1.getY() - o2.getY());

				if (o1.getY() == min.getY() && o2.getY() == min.getY()) {
					return (int) Math.signum(o1.getX() - o2.getX());
				}

				if (o1.getY() == min.getY()) {
					return -1;
				}

				if (o2.getY() == min.getY()) {
					return -1;
				}

				double theta1 = Math.atan((double) (min.getX() - o1.getX())
						/ (o1.getY() - min.getY()));
				double theta2 = Math.atan((double) (min.getX() - o2.getX())
						/ (o2.getY() - min.getY()));
				 if((int) Math.signum(theta1 - theta2)==0){
					 return (int)Math.signum(o2.getY() - o1.getY());
				 }
				return (int) Math.signum(theta1 - theta2);
			}
		});
		
		
		//add le pivot dans le premier place 
		
		lp.add(0,min);
		
		
		//Calcul de l'enveloppe 
		Pile.push(lp.get(0));
		Pile.push(lp.get(1));
		for(int i=2;i<lp.size();i++){
			while(Pile.size()>=2 && prod_vect(Pile.get(Pile.size()-2), Pile.peek(), lp.get(i))<=0){
				Pile.pop();
			}
			Pile.push(lp.get(i));
			for(int j=0;j<Pile.size();j++){
				System.out.println(Pile.get(j).getX()+" "+Pile.get(j).getY());
			}
		
		}
		
		//Affichage de l'enveloppe
		for(int i=0;i<Pile.size();i++){
			System.out.println(Pile.get(i).getX()+" "+Pile.get(i).getY());
		}
	
		
		
		
		
		
		

	}
	
}
