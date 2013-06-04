/** @author Henrik Hansen <henrik.hansen@student.jus.uio.no>*/

/**Det eneste denne klassen gjoer, er aa starte programmet kalle paa forskjellige metoder i Data-klassen*/ 
class Oblig1 {
	public static void main(String[]args) {
	Data d = new Data();
	d.kjennskap();
	d.forelskelse();
	d.uvennskap();
	d.vennskap();
	d.skrivUt();
	} 
} // Slutt paa klassen "Oblig1".

/**Denne klassen oppretter tilstandspaastandene som spesifisert i oppgaven. Det gjoer den ved aa kalle paa metoder i Person-klassen*/

class Data {
		Person jeg = new Person("Ego", 3);
		Person emil = new Person("Emil", 2);
		Person lisa = new Person("Lisa", 2);
		Person ramzi = new Person("Ramzi", 3);

/**Legger til tilstandspaastandene om hvem som kjenner hvem(som spesifisert i oppgaven)*/
	public void kjennskap() {
		jeg.blirKjentMed(emil);
		jeg.blirKjentMed(lisa);
		jeg.blirKjentMed(ramzi);
		emil.blirKjentMed(jeg);
		emil.blirKjentMed(ramzi);
		lisa.blirKjentMed(emil);
		lisa.blirKjentMed(ramzi);
		ramzi.blirKjentMed(jeg);
		ramzi.blirKjentMed(emil);
		ramzi.blirKjentMed(lisa);
	}
/**Legger til tilstandspaastandene om hvem som er forelsket i hvem(som spesifisert i oppgaven)*/
	public void forelskelse() {
		emil.blirForelsketI(jeg);
		lisa.blirForelsketI(ramzi);
		ramzi.blirForelsketI(lisa);
	}
/**Legger til tilstandspaastandene om hvem som er uvenn med hvem(som spesifisert i oppgaven)*/
	public void uvennskap() {
		emil.blirUvennMed(lisa);
		lisa.blirUvennMed(jeg);
		ramzi.blirUvennMed(emil);
	}
/**Legger til tilstandspaastandene om hvem som er venn med hvem. I teorien det samme som kjennskap*/
	public void vennskap() {
		jeg.blirVennMed(emil);
		jeg.blirVennMed(lisa);
		jeg.blirVennMed(ramzi);
		emil.blirVennMed(jeg);
		emil.blirVennMed(ramzi);
		lisa.blirVennMed(emil);
		lisa.blirVennMed(ramzi);
		ramzi.blirVennMed(jeg);
		ramzi.blirVennMed(emil);
		ramzi.blirVennMed(lisa);
	}
        public void skrivUt() {
	    jeg.skrivUtAlt();
	    emil.skrivUtAlt();
	    lisa.skrivUtAlt();
	    ramzi.skrivUtAlt();
	    Person [] lisaKjenner = lisa.hentKjenninger();
	    System.out.println(lisaKjenner[1].hentBestevenn().hentNavn());
    }
} // Slutt paa klassen Data
/**Klassen Person som holder paa data om relasjonene i personnettverket vaart.
 * @param teller Brukes i sammenheng med metoden blirKjentMed(); for aa legge personen(p) inn paa rett indeks i kjenner-arrayet.
 * @param teller1 Brukes i sammenheng med metoden blirVennMed(); for aa legge personen(p) inn paa rett indeks i venner-arrayet.
 * @param kjenner Array som holder paa kjennskapet til innevaerende personobjekt.
 * @param likerikke peker til personobjekt som innevaerende personobjekt ikke liker(er uvenn med)
 * @param forelsketi peker til personobjekt som innevaerende personobjekt er forelsket i.
 * @param venner Array som holder paa vennene til innevaerende personobjekt. Valgte aa lage denne, da oppgaven sa du kunne lage egne variabler/objekter etc.
 * @param navn Navnet til innevaerende personobjekt
*/
class Person {
	private int teller, teller1;
	private String navn;
	private Person [] kjenner;
	private Person likerikke;
	private Person forelsketi;
	private Person [] venner;
/**Konstruktoeren til Person
 * @param n n settes lik navn
 * @param lengde Bestemmer lengden paa kjenner-array
*/
	Person(String n, int lengde) {
		navn = n;
		kjenner = new Person[lengde];
		venner = new Person[kjenner.length];
	}
/**Returnerer navnet paa innevaerende personobjekt(da navn er satt til private)*/
	public String hentNavn() {
		return navn;
	}
/** Returnerer true/false alt etter om innevaerende personobjekt kjenner p*/
	public boolean erKjentMed(Person p) {
	    boolean kjenner1 = false;
		for(int i = 0; i<kjenner.length; i++) {
		    if (p.hentNavn().equals(kjenner[i].hentNavn()))
				kjenner1 = true;
		}
		return kjenner1;
	}
/**Metode for aa bestemme vennskap til innevaerende personobjekt*/
	public void blirKjentMed(Person p) {
		if(!p.hentNavn().equals(navn)) {
			kjenner[teller] = p;
			teller++;
		}
	}
/**Metode for aa bestemme hvem innevaerende personobjekt blir forelsket i*/
	public void blirForelsketI(Person p) {
		if(!p.hentNavn().equals(navn)) {
			forelsketi = p;
		}
	}
/**Metode for aa bestemme hvem innevaerende personobjekt blir uvenn med*/
	public void blirUvennMed(Person p) {
		if(!p.hentNavn().equals(navn)) {
			likerikke = p;
		}
	}
/**Metode for aa sjekke om innevarende personobjekt er venn med p. Returnerer true/false*/
	public boolean erVennMed(Person p) {
		boolean venn = false;
		for(int i = 0; i<venner.length; i++) {
			if (p.hentNavn().equals(venner[i].hentNavn()))
				venn = true;
		}
		return venn;
	}
/**Metode for aa bestemme hvem innevarende personobjekt er venn med. Blir egentlig det samme som kjenner, saa if-setningene er egentlig unoedvendige.*/
	public void blirVennMed(Person p) {
		if (likerikke == null && !p.hentNavn().equals(navn)){
			venner[teller1] = p;
			teller1++;
		}
		if (likerikke != null && !p.hentNavn().equals(navn)) {
			if (!likerikke.hentNavn().equals(navn)) {
				venner[teller1] = p;
				teller1++;
			}
		}
	}
/**Skriver ut vennene til innevarende personobjekt*/
	public void skrivUtVenner() {
		System.out.println(navn + "'s venner er: ");
		for(int i = 0; i < venner.length; i++) {	
			System.out.println(venner[i].hentNavn());
		}
	}
/**Returnerer en peker til bestevennen til innevarende personobjekt(personen paa plass [0] i kjenner-arrayet.)
 * Slik jeg tolket oppgaven skulle dette peke paa et personobjekt, ikke kun paa et navn,
 * slik at navnet kan hentes med feks "System.out.println(jeg.hentBestevenn().hentNavn());"
 */
	public Person hentBestevenn()  {
		return kjenner[0];
	}
/**Returnerer innevarende person's kjenninger*/
	public Person[] hentKjenninger() {
		return kjenner;
	}
/**Skriver ut innevaerende persons kjenninger*/
	public void skrivUtKjenninger() {
		for (Person p: kjenner){
			if(p != null) {
				System.out.print(p.hentNavn() + " ");
			}
		}
		System.out.println("");
	}
/**Skriver ut "alt" om en innevaerende personsobjekt*/
	public void skrivUtAlt() {
		System.out.print(navn + " kjenner: ");
		skrivUtKjenninger();
		if (forelsketi != null)
			System.out.println(navn + " er forelsket i " + forelsketi.hentNavn());
		if (likerikke != null)
			System.out.println(navn + " liker ikke " + likerikke.hentNavn());
	}
} // Slutt paa klassen Person
