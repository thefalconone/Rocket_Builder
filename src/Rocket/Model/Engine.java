package Rocket.Model;

public class Engine {

	public String name;
	public FuelType type;
	public float mass, thrust, lf, ox, sf;
	public int isp, cost;

	public Engine(String name, FuelType type, float mass, float thrust, int isp, int cost, int lf, int ox, int sf){
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
		return mass + lf*0.005f + ox*0.005f + sf*0.004f;
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
