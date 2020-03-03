package Rocket.Model;

public class Settings {

	public static float ratiokill, moddeltav, modcost, modtwr, mintwr, maxtwr, payload;
	public static int nbpop, nbmut, nbmaxft, nbmaxstages, nbgen, mindv, maxdv, mincost, maxcost;

	public Settings(float ratiokill, float moddeltav, float modcost, float modtwr, float mintwr, float maxtwr, float payload, int nbpop, int nbmut, int nbmaxft, int nbmaxstages, int nbgen, int mindv, int maxdv, int mincost, int maxcost){
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
		Settings.mindv =mindv;
		Settings.maxdv =maxdv;
		Settings.mincost =mincost;
		Settings.maxcost =maxcost;
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
				", mindv=" + mindv +
				", maxdv=" + maxdv +
				", mincost=" + mincost +
				", maxcost=" + maxcost +
				'}';
	}
}
