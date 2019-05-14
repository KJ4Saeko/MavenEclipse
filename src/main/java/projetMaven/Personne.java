package projetMaven;

public class Personne {

    //caracteristiques d'une personne
    String nom;
    String prenom;
    int age;
    String sexe;

    //constructeur par défaut
    public Personne(){
        nom = "";
        prenom = "";
        age = 0;
        sexe = "";
    }

    //Constructeur avec paramètres
    public Personne(String n, String p, int a, String s){
        setNom(n);
        setPrenom(p);
        setAge(a);
        setSexe(s);
    }

    /************** SETTERS **************/

    //Setter du nom d'une personne
    public void setNom(String _nom){
        this.nom = _nom;
    }

    //Setter du prenom d'une personne
    public void setPrenom(String _prenom){
        this.prenom = _prenom;
    }

    //Setter de l'age d'une personne
    public void setAge(int _age){
        this.age = _age;
    }

    //Setter du sexe d'une personne
    public void setSexe(String _sexe){
        this.sexe = _sexe;
    }

    /************** GETTERS **************/

    //Getter nom personne
    public String getNom(){
        return nom;
    }

    //Getter prenom personne
    public String getPrenom(){
        return prenom;
    }

    //Getter age personne
    public int getAge(){
        return age;
    }

    //Getter sexe personne
    public String getSexe(){
        return sexe;
    }

    public String toString(){
        return nom + " ," + prenom + " ," + age + " ," + sexe;
    }
}