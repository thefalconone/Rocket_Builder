package Rocket.Model;

public class FuelTank {

	public String name;
	public float drymass, lf, ox, mo, cost;

	public FuelTank(String name, float drymass, float cost, float lf, float ox, float mo){
		this.name=name;
		this.drymass=drymass;
		this.cost=cost;
		this.lf=lf;
		this.ox=ox;
		this.mo=mo;
	}
	public float getTotalMass(){
		return drymass + lf*0.005f + ox*0.005f + mo*0.004f;
	}

	public float getLfMass(){
		return lf*0.005f;
	}

	public float getOxMass(){
		return ox*0.005f;
	}

	public float getMoMass(){
		return mo*0.004f;
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
