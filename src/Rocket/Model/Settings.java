package Rocket.Model;

public class Settings {

	public static float ratiokill, moddeltav, modcost, modtwr, mintwr, maxtwr, payload;
	public static int nbpop, nbmut, nbmaxft, nbmaxstages, nbgen, dv, cost;

	public Settings(float ratiokill, float moddeltav, float modcost, float modtwr, float mintwr, float maxtwr, float payload, int nbpop, int nbmut, int nbmaxft, int nbmaxstages, int nbgen, int dv, int cost){
		Settings.ratiokill =ratiokill;
		Settings.moddeltav =moddeltav;
		Settings.modcost =modcost;
		Settings.modtwr =modtwr;
		Settings.mintwr =mintwr;
		Settings.maxtwr =maxtwr;
		Settings.payload =payload;
		Settings.nbpop =nbpop;
		Settings.nbmut =nbmut;
		Settings.nbmaxft =nbmaxft;
		Settings.nbmaxstages =nbmaxstages;
		Settings.nbgen =nbgen;
		Settings.dv =dv;
		Settings.cost =cost;
	}

	public Settings(){
		Settings.ratiokill = .5f;
		Settings.moddeltav = 1;
		Settings.modcost = 1;
		Settings.modtwr = 1;
		Settings.mintwr = 1;
		Settings.maxtwr = 5;
		Settings.payload = 4.32f;
		Settings.nbpop = 1000;
		Settings.nbmut = 30;
		Settings.nbmaxft = 3;
		Settings.nbmaxstages = 5;
		Settings.nbgen = 100;
		Settings.dv = 4000;
		Settings.cost = 0;
	}

	@Override
	public String toString() {
		return "Settings{" +
				"ratiokill=" + ratiokill +
				", moddeltav=" + moddeltav +
				", modcost=" + modcost +
				", modtwr=" + modtwr +
				", mintwr=" + mintwr +
				", maxtwr=" + maxtwr +
				", payload=" + payload +
				", nbpop=" + nbpop +
				", nbmut=" + nbmut +
				", nbmaxft=" + nbmaxft +
				", nbmaxstages=" + nbmaxstages +
				", nbgen=" + nbgen +
				", dv=" + dv +
				", cost=" + cost +
				'}';
	}
}
