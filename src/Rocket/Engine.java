package Rocket;

public class Engine {

	public String name;
	public FuelType type;
	public float mass;
	public int thrust, isp, cost, lf, ox, sf;

	public Engine(String name, FuelType type, float mass, int thrust, int isp, int cost, int lf, int ox, int sf){
		this.name=name;
		this.type=type;
		this.mass=mass;
		this.thrust=thrust;
		this.isp=isp;
		this.cost=cost;
		this.lf=lf;
		this.ox=ox;
		this.sf=sf;
	}

	public float getTotalMass(){
		return (float) (mass + lf*0.005 + ox*0.005 + sf*0.0075);
	}

	@Override
	public String toString() {
		return "Engine{" +
				"name='" + name + '\'' +
				", type=" + type +
				", mass=" + mass +
				", thrust=" + thrust +
				", isp=" + isp +
				", cost=" + cost +
				", lf=" + lf +
				", ox=" + ox +
				", sf=" + sf +
				'}';
	}
}
