package Rocket.Model;

public class FuelTank {

	public String name;
	public int cost, lf, ox, mo;
	public float drymass;

	public FuelTank(String name, float drymass, int cost, int lf, int ox, int mo){
		this.name=name;
		this.drymass=drymass;
		this.cost=cost;
		this.lf=lf;
		this.ox=ox;
		this.mo=mo;
	}
	public float getTotalMass(){
		return (float) (drymass + lf*0.005 + ox*0.005 + mo*0.004);
	}

	public float getLfMass(){
		return (float) (lf*0.005);
	}

	public float getOxMass(){
		return (float) (ox*0.005);
	}

	public float getMoMass(){
		return (float) (mo*0.004);
	}

	@Override
	public String toString() {
		return "FuelTank{" +
				"name='" + name + '\'' +
				", cost=" + cost +
				", lf=" + lf +
				", ox=" + ox +
				", mo=" + mo +
				", drymass=" + drymass +
				'}';
	}
}
